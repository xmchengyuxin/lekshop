package com.chengyu.core.service.order.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.OrderEnums;
import com.chengyu.core.domain.form.OrderMergeSearchForm;
import com.chengyu.core.domain.form.OrderSearchForm;
import com.chengyu.core.domain.result.OrderMergeDetailResult;
import com.chengyu.core.domain.result.OrderMergeResult;
import com.chengyu.core.domain.result.OrderResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.*;
import com.chengyu.core.model.*;
import com.chengyu.core.service.order.OrderMergeService;
import com.chengyu.core.service.order.OrderService;
import com.chengyu.core.service.trade.TradeStorageStockService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderMergeServiceImpl implements OrderMergeService {

    @Autowired
    private OmsOrderMergeMapper orderMergeMapper;
    @Autowired
    private OmsOrderMergeDetailMapper orderMergeDetailMapper;
    @Autowired
    private OmsOrderMergeProcessMapper orderMergeProcessMapper;
    @Autowired
    private OmsOrderMapper orderMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private TradeStorageStockService tradeStorageStockService;
    @Autowired
    private OmsOrderDetailMapper orderDetailMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void mergeOrder(UmsShopAccount shopAccount, List<Integer> orderIdList) throws ServiceException {
        if (CollectionUtil.isEmpty(orderIdList)) {
            throw new ServiceException("请选择订单");
        }
        //校验订单
        //1.订单状态是否待发货 2.订单是否已被合并
        OmsOrderExample orderExample = new OmsOrderExample();
        orderExample.createCriteria().andIdIn(orderIdList).andStatusNotEqualTo(OrderEnums.OrderStatus.WAIT_DELIVERY.getValue());
        if (orderMapper.countByExample(orderExample) > 0) {
            throw new ServiceException("待发货订单才能进行合并, 请重新选择");
        }
        OmsOrderMergeDetailExample mergeDetailExample = new OmsOrderMergeDetailExample();
        mergeDetailExample.createCriteria().andOrderIdIn(orderIdList).andStatusEqualTo(CommonConstant.SUS_INT);
        if (orderMergeDetailMapper.countByExample(mergeDetailExample) > 0) {
            throw new ServiceException("一笔订单只能合并一次");
        }

        OmsOrderMerge orderMerge = new OmsOrderMerge();
        orderMerge.setMergeOrderNo("MG" + StringUtils.genOrderNo(shopAccount.getId()));
        orderMerge.setAccountId(shopAccount.getId());
        orderMerge.setAccountName(shopAccount.getRealname());
        orderMerge.setDeptId(shopAccount.getDeptId());
        orderMerge.setDeptName(shopAccount.getDeptName());
        orderMergeMapper.insertSelective(orderMerge);

        orderExample = new OmsOrderExample();
        orderExample.createCriteria().andIdIn(orderIdList);
        List<OmsOrder> orderList = orderMapper.selectByExample(orderExample);
        for (OmsOrder order : orderList) {
            OmsOrderMergeDetail detail = new OmsOrderMergeDetail();
            detail.setOrderMergeId(orderMerge.getId());
            detail.setOrderId(order.getId());
            detail.setStatus(CommonConstant.YES_INT);
            orderMergeDetailMapper.insertSelective(detail);
        }

        //将订单设置为已合并
        OmsOrderExample example = new OmsOrderExample();
        example.createCriteria().andIdIn(orderIdList);
        OmsOrder updateOrder = new OmsOrder();
        updateOrder.setMergeStatus(CommonConstant.YES_INT);
        orderMapper.updateByExampleSelective(updateOrder, example);

        this.addOrderProcess(orderMerge.getId(), "合并订单", null);
    }

    @Override
    public List<OmsOrderMerge> getOrderMergeList(OrderMergeSearchForm form, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        OmsOrderMergeExample example = new OmsOrderMergeExample();
        example.setOrderByClause("add_time desc");
        if (StringUtils.isNotBlank(form.getSort())) {
            example.setOrderByClause(form.getSort());
        }
        OmsOrderMergeExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(form.getMergeOrderNo())) {
            criteria.andMergeOrderNoEqualTo(form.getMergeOrderNo());
        }
        if (form.getShopId() != null) {
            criteria.andShopIdEqualTo(form.getShopId());
        }
        if (form.getStatus() != null) {
            criteria.andStatusEqualTo(form.getStatus());
        }
        if (CollectionUtil.isNotEmpty(form.getStatusList())) {
            criteria.andStatusIn(form.getStatusList());
        }
        if(StringUtils.isNotBlank(form.getStatusArr())){
            List<Integer> idList = Convert.convert(new TypeReference<List<Integer>>() {}, form.getStatusArr().split(CommonConstant.DH_REGEX));
            criteria.andStatusIn(idList);
        }
        if (form.getDateFrom() != null && form.getDateTo() != null) {
            criteria.andAddTimeBetween(form.getDateFrom(), form.getDateTo());
        }
        return orderMergeMapper.selectByExample(example);
    }

    @Override
    public OrderMergeResult getOrderMerge(Integer orderMergeId) {
        OrderMergeResult result = new OrderMergeResult();
        result.setOrderMerge(orderMergeMapper.selectByPrimaryKey(orderMergeId));

        OmsOrderMergeProcessExample processExample = new OmsOrderMergeProcessExample();
        processExample.setOrderByClause("add_time desc");
        processExample.createCriteria().andOrderMergeIdEqualTo(orderMergeId);
        result.setProcessList(orderMergeProcessMapper.selectByExample(processExample));

        //查询所有的订单ID
        OmsOrderMergeDetailExample detailExample = new OmsOrderMergeDetailExample();
        detailExample.createCriteria().andOrderMergeIdEqualTo(orderMergeId).andStatusEqualTo(CommonConstant.YES_INT);
        List<OmsOrderMergeDetail> detailList = orderMergeDetailMapper.selectByExample(detailExample);
        List<Integer> orderIdList = detailList.stream().map(OmsOrderMergeDetail::getOrderId).collect(Collectors.toList());

        OrderSearchForm form = new OrderSearchForm();
        form.setOrderIdList(orderIdList);
        List<OrderResult> orderResultList = orderService.getOrderList(form, 1, 9999).getList();
        Map<Integer, List<OrderResult>> orderMap = orderResultList.stream()
                .collect(Collectors.groupingBy(OrderResult::getMemberId));

        //以下单人做分组
        List<OrderMergeDetailResult> mergeDetailList = new ArrayList<>();
        for (Integer memberId : orderMap.keySet()) {
            OrderMergeDetailResult mergeDetailResult = new OrderMergeDetailResult();
            mergeDetailResult.setMemberId(memberId);
            mergeDetailResult.setOrderResultList(orderMap.get(memberId));
            mergeDetailResult.setMemberName(mergeDetailResult.getOrderResultList().get(0).getOrder().getMemberName());
            mergeDetailList.add(mergeDetailResult);
        }
        result.setMergeDetailList(mergeDetailList);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void distribution(UmsShopAccount account, Integer orderMergeId) throws ServiceException {
        OmsOrderMerge orderMerge = orderMergeMapper.selectByPrimaryKey(orderMergeId);
        if(orderMerge == null || orderMerge.getStatus() != 1){
            throw new ServiceException("已合并订单才能配货");
        }

        OmsOrderMerge updateOrderMerge = new OmsOrderMerge();
        updateOrderMerge.setId(orderMergeId);
        //已配货
        updateOrderMerge.setStatus(2);
        orderMergeMapper.updateByPrimaryKeySelective(updateOrderMerge);

        //将订单设置为已配货
        OmsOrderMergeDetailExample detailExample = new OmsOrderMergeDetailExample();
        detailExample.createCriteria().andOrderMergeIdEqualTo(orderMergeId).andStatusEqualTo(CommonConstant.YES_INT);
        List<OmsOrderMergeDetail> detailList = orderMergeDetailMapper.selectByExample(detailExample);

        List<Integer> orderIds = detailList.stream().map(OmsOrderMergeDetail::getOrderId).collect(Collectors.toList());
        OmsOrderExample example = new OmsOrderExample();
        example.createCriteria().andIdIn(orderIds);
        OmsOrder updateOrder = new OmsOrder();
        updateOrder.setMergeStatus(2);
        orderMapper.updateByExampleSelective(updateOrder, example);

        //扣除仓库库存
        OmsOrderDetailExample orderDetailExample = new OmsOrderDetailExample();
        orderDetailExample.createCriteria().andOrderIdIn(orderIds);
        List<OmsOrderDetail> orderDetailList = orderDetailMapper.selectByExample(orderDetailExample);
        for(OmsOrderDetail orderDetail : orderDetailList) {
            tradeStorageStockService.outStock(orderDetail.getOrderNo(), null, orderDetail.getGoodsSkuId(), orderDetail.getBuyNum(), "商城订单出库");
        }

        this.addOrderProcess(orderMergeId, account.getRealname()+"将订单置为已配货", null);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void sortOut(UmsShopAccount account, Integer orderMergeId) throws ServiceException {
        OmsOrderMerge orderMerge = orderMergeMapper.selectByPrimaryKey(orderMergeId);
        if(orderMerge == null || orderMerge.getStatus() != 2){
            throw new ServiceException("已配货订单才能分拣");
        }

        OmsOrderMerge updateOrderMerge = new OmsOrderMerge();
        updateOrderMerge.setId(orderMergeId);
        //已分拣
        updateOrderMerge.setStatus(3);
        orderMergeMapper.updateByPrimaryKeySelective(updateOrderMerge);

        //将订单设置为已分拣
        OmsOrderMergeDetailExample detailExample = new OmsOrderMergeDetailExample();
        detailExample.createCriteria().andOrderMergeIdEqualTo(orderMergeId).andStatusEqualTo(CommonConstant.YES_INT);
        List<OmsOrderMergeDetail> detailList = orderMergeDetailMapper.selectByExample(detailExample);

        OmsOrderExample example = new OmsOrderExample();
        example.createCriteria().andIdIn(detailList.stream().map(OmsOrderMergeDetail::getOrderId).collect(Collectors.toList()));
        OmsOrder updateOrder = new OmsOrder();
        updateOrder.setMergeStatus(3);
        orderMapper.updateByExampleSelective(updateOrder, example);

        this.addOrderProcess(orderMergeId, account.getRealname()+"将订单置为已分拣", null);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void cancel(UmsShopAccount account, Integer orderMergeId) throws ServiceException {
        OmsOrderMerge orderMerge = orderMergeMapper.selectByPrimaryKey(orderMergeId);
        if(orderMerge == null || orderMerge.getStatus() == 1){
            throw new ServiceException("已合并订单才能取消");
        }

        OmsOrderMerge updateOrderMerge = new OmsOrderMerge();
        updateOrderMerge.setId(orderMergeId);
        //已取消
        updateOrderMerge.setStatus(4);
        orderMergeMapper.updateByPrimaryKeySelective(updateOrderMerge);

        //将订单设置为已分拣
        OmsOrderMergeDetailExample detailExample = new OmsOrderMergeDetailExample();
        detailExample.createCriteria().andOrderMergeIdEqualTo(orderMergeId).andStatusEqualTo(CommonConstant.YES_INT);
        List<OmsOrderMergeDetail> detailList = orderMergeDetailMapper.selectByExample(detailExample);

        OmsOrderMergeDetail updateDetail = new OmsOrderMergeDetail();
        updateDetail.setStatus(CommonConstant.NO_INT);
        orderMergeDetailMapper.updateByExampleSelective(updateDetail, detailExample);

        OmsOrderExample example = new OmsOrderExample();
        example.createCriteria().andIdIn(detailList.stream().map(OmsOrderMergeDetail::getOrderId).collect(Collectors.toList()));
        OmsOrder updateOrder = new OmsOrder();
        updateOrder.setMergeStatus(CommonConstant.NO_INT);
        orderMapper.updateByExampleSelective(updateOrder, example);

        this.addOrderProcess(orderMergeId, account.getRealname()+"取消了配货单", null);
    }

    private void addOrderProcess(Integer orderMergeId, String title, String remark) {
        OmsOrderMergeProcess process = new OmsOrderMergeProcess();
        process.setOrderMergeId(orderMergeId);
        process.setTitle(title);
        process.setRemark(remark);
        orderMergeProcessMapper.insertSelective(process);
    }
}