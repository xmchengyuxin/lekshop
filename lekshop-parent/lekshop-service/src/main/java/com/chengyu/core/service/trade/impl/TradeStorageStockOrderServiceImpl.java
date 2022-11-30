package com.chengyu.core.service.trade.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.form.TradeStockSearchForm;
import com.chengyu.core.domain.result.TradeStockEditResult;
import com.chengyu.core.domain.result.TradeStockOrderResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.*;
import com.chengyu.core.model.*;
import com.chengyu.core.service.trade.TradeStorageStockOrderService;
import com.chengyu.core.service.trade.TradeStorageStockService;
import com.chengyu.core.util.ValidateUtil;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TradeStorageStockOrderServiceImpl implements TradeStorageStockOrderService {

    @Autowired
    private TradeStorageStockOrderMapper tradeStorageStockOrderMapper;
    @Autowired
    private TradeStorageStockOrderDetailMapper tradeStorageStockOrderDetailMapper;
    @Autowired
    private TradeStorageStockEditMapper tradeStorageStockEditMapper;
    @Autowired
    private TradeStorageStockEditProcessMapper tradeStorageStockEditProcessMapper;
    @Autowired
    private TradeStorageStockService tradeStorageStockService;
    @Autowired
    private TradeStorageMapper tradeStorageMapper;
    @Autowired
    private PmsGoodsMapper goodsMapper;
    @Autowired
    private PmsGoodsSkuMapper goodsSkuMapper;
    @Autowired
    private TradeStorageStockMapper tradeStorageStockMapper;

    @Override
    public List<TradeStorageStockOrder> getStorageStockOrderList(TradeStockSearchForm form, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        TradeStorageStockOrderExample example = new TradeStorageStockOrderExample();
        example.setOrderByClause("add_time desc");
        TradeStorageStockOrderExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(form.getOrderNo())){
            criteria.andOrderNoEqualTo(form.getOrderNo());
        }
        if(StringUtils.isNotBlank(form.getBuyOrderNo())){
            criteria.andBuyOrderNoEqualTo(form.getBuyOrderNo());
        }
        if(StringUtils.isNotBlank(form.getSupplier())){
            criteria.andSupplierEqualTo(form.getSupplier());
        }
        return tradeStorageStockOrderMapper.selectByExample(example);
    }

    @Override
    public TradeStockOrderResult getStockOrderResult(Integer orderId) {
        TradeStockOrderResult result = new TradeStockOrderResult();
        result.setOrder(tradeStorageStockOrderMapper.selectByPrimaryKey(orderId));

        TradeStorageStockOrderDetailExample example = new TradeStorageStockOrderDetailExample();
        example.setOrderByClause("id asc");
        example.createCriteria().andOrderIdEqualTo(orderId);
        result.setOrderDetailList(tradeStorageStockOrderDetailMapper.selectByExample(example));
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addStockOrder(UmsShop shop, TradeStorageStockOrder order, List<TradeStorageStockOrderDetail> orderDetailList) throws ServiceException {
        if(CollectionUtil.isEmpty(orderDetailList)) {
            throw new ServiceException("请添加入库商品");
        }
        order.setOrderNo(StringUtils.genOrderNo(shop.getId()));
        order.setShopId(shop.getId());
        order.setShopName(shop.getName());
        tradeStorageStockOrderMapper.insertSelective(order);

        for(TradeStorageStockOrderDetail detail : orderDetailList){
            ValidateUtil.validateNull(new Object[]{detail.getStorageId(), detail.getSpaceName(), detail.getInNum(), detail.getBuyPrice(), detail.getDiscount(), detail.getTotalPrice()},
                    "请选择仓库", "请填写货位", "请填写进货数量", "请填写进价", "请填写折扣", "请填写总价");
            detail.setOrderId(order.getId());
            detail.setOrderNo(order.getOrderNo());
            detail.setShopId(shop.getId());
            detail.setShopName(shop.getName());
            TradeStorage storage = tradeStorageMapper.selectByPrimaryKey(detail.getStorageId());
            detail.setStorageName(storage.getName());

            PmsGoodsSku sku = goodsSkuMapper.selectByPrimaryKey(detail.getSkuId());
            PmsGoods goods = goodsMapper.selectByPrimaryKey(sku.getGoodsId());
            detail.setCateId(goods.getCateId());
            detail.setCateName(goods.getCateName());
            detail.setGoodsId(goods.getId());
            detail.setGoodsName(goods.getTitle());
            detail.setSkuId(sku.getId());
            detail.setSkuName(sku.getAttrSymbolName());
            tradeStorageStockOrderDetailMapper.insertSelective(detail);

            StringBuilder remark = new StringBuilder();
            remark.append("手动入库:");
            remark.append("采购单号-").append(order.getBuyOrderNo()).append("#");
            remark.append("供应商-").append(order.getSupplier()).append("#");
            remark.append("订单金额-").append(order.getOrderAmount()).append("#");
            remark.append("实付-").append(order.getRealAmount()).append("#");
            remark.append("欠款-").append(order.getOweAmount()).append("#");
            remark.append("是否到票-").append(order.getTicketStatus() == CommonConstant.YES_INT ? "是" : "否").append("#");
            remark.append("货位-").append(detail.getSpaceName()).append("#");
            remark.append("进价-").append(detail.getBuyPrice()).append("#");
            remark.append("折扣-").append(detail.getDiscount()).append("#");
            remark.append("总价-").append(detail.getTotalPrice());
            tradeStorageStockService.inStock(order.getOrderNo(), detail.getStorageId(), detail.getSpaceNo(), detail.getSpaceName(), detail.getSkuId(), detail.getInNum(), remark.toString());
        }


    }

    @Override
    public List<TradeStorageStockEdit> getStorageStockEditList(TradeStockSearchForm form, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        TradeStorageStockEditExample example = new TradeStorageStockEditExample();
        example.setOrderByClause("add_time desc");
        TradeStorageStockEditExample.Criteria criteria = example.createCriteria();
        if(form.getGoodsId() != null){
            criteria.andGoodsIdEqualTo(form.getGoodsId());
        }
        if(StringUtils.isNotBlank(form.getGoodsName())){
            criteria.andGoodsNameLike("%"+form.getGoodsName()+"%");
        }
        return tradeStorageStockEditMapper.selectByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addStockEdit(TradeStorageStockEdit stockEdit) throws ServiceException {
        stockEdit.setEditNo("SE"+StringUtils.genOrderNo(null));
        TradeStorage storage = tradeStorageMapper.selectByPrimaryKey(stockEdit.getStorageId());
        stockEdit.setStorageName(storage.getName());
        //查询原仓库商品
        TradeStorageStockExample example = new TradeStorageStockExample();
        TradeStorageStockExample.Criteria criteria = example.createCriteria();
        criteria.andSkuIdEqualTo(stockEdit.getSkuId()).andStorageIdEqualTo(stockEdit.getStorageId());
        List<TradeStorageStock> list = tradeStorageStockMapper.selectByExample(example);
        TradeStorageStock storageStock = CollectionUtil.isEmpty(list) ? null : list.get(0);
        if(storageStock == null) {
            throw new ServiceException("仓库中查无此商品");
        }
        stockEdit.setGoodsId(storageStock.getGoodsId());
        stockEdit.setGoodsName(storageStock.getGoodsName());
        stockEdit.setBarCode(storageStock.getBarCode());
        stockEdit.setSkuId(storageStock.getSkuId());
        stockEdit.setSkuName(storageStock.getSkuName());
        tradeStorageStockEditMapper.insertSelective(stockEdit);

        if(stockEdit.getEditNum() > 0){
            tradeStorageStockService.inStock(stockEdit.getEditNo(), stockEdit.getStorageId(), null, null, stockEdit.getSkuId(), stockEdit.getEditNum(), "手动修改库存");
        } else if(stockEdit.getEditNum() < 0){
            tradeStorageStockService.outStock(stockEdit.getEditNo(), stockEdit.getStorageId(), stockEdit.getGoodsId(), stockEdit.getEditNum(), "手动修改库存");
        }

        this.addOrderProcess(stockEdit.getId(), "修改库存", null);
    }

    @Override
    public TradeStockEditResult getStorageStockEditResult(Integer id) {
        TradeStockEditResult result = new TradeStockEditResult();
        result.setStockEdit(tradeStorageStockEditMapper.selectByPrimaryKey(id));

        TradeStorageStockEditProcessExample processExample = new TradeStorageStockEditProcessExample();
        processExample.setOrderByClause("add_time desc");
        processExample.createCriteria().andStockEditIdEqualTo(id);
        result.setProcessList(tradeStorageStockEditProcessMapper.selectByExample(processExample));
        return result;
    }

    private void addOrderProcess(Integer stockEditId, String title, String remark) {
        TradeStorageStockEditProcess process = new TradeStorageStockEditProcess();
        process.setStockEditId(stockEditId);
        process.setTitle(title);
        process.setRemark(remark);
        tradeStorageStockEditProcessMapper.insertSelective(process);
    }
}