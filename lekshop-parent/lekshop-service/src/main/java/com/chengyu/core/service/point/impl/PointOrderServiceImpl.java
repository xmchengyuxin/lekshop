package com.chengyu.core.service.point.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.AccountEnums;
import com.chengyu.core.domain.enums.GoodsEnums;
import com.chengyu.core.domain.enums.MemberNewsEnums;
import com.chengyu.core.domain.enums.OrderEnums;
import com.chengyu.core.domain.form.MemberNewsForm;
import com.chengyu.core.domain.form.OrderDeliveryForm;
import com.chengyu.core.domain.form.OrderSearchForm;
import com.chengyu.core.domain.result.PointOrderPayResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.PointGoodsMapper;
import com.chengyu.core.mapper.PointOrderMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.service.config.ConfigOrderService;
import com.chengyu.core.service.funds.MemberPointLogService;
import com.chengyu.core.service.member.MemberAddressService;
import com.chengyu.core.service.member.MemberNewsService;
import com.chengyu.core.service.member.MemberService;
import com.chengyu.core.service.point.PointGoodsService;
import com.chengyu.core.service.point.PointOrderFreightService;
import com.chengyu.core.service.point.PointOrderService;
import com.chengyu.core.service.schedule.RedisDelayQueueEnum;
import com.chengyu.core.service.schedule.RedisDelayQueueUtil;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PointOrderServiceImpl implements PointOrderService {

	@Autowired
	private PointOrderMapper orderMapper;
	@Autowired
	private ConfigOrderService configOrderService;
	@Autowired
	private PointGoodsMapper goodsMapper;
	@Autowired
	private PointGoodsService goodsService;
	@Autowired
	private MemberAddressService memberAddressService;
	@Autowired
	private PointOrderFreightService orderFreightService;
	@Autowired
	private MemberNewsService memberNewsService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private RedisDelayQueueUtil redisDelayQueueUtil;
	@Autowired
	private MemberPointLogService memberPointLogService;

	@Override
	public List<PointOrder> getOrderList(OrderSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		PointOrderExample example = new PointOrderExample();
		if(StringUtils.isNotBlank(form.getSort())){
			example.setOrderByClause(form.getSort());
		}else{
			example.setOrderByClause("add_time desc");
		}
		PointOrderExample.Criteria criteria = example.createCriteria();
		criteria.andDelIndEqualTo(CommonConstant.NO_INT);
		if(StringUtils.isNotBlank(form.getOrderNo())){
			criteria.andOrderNoLike("%"+form.getOrderNo()+"%");
		}
		if(form.getMemberId() != null){
			criteria.andMemberIdEqualTo(form.getMemberId());
		}
		if(StringUtils.isNotBlank(form.getMemberName())){
			criteria.andMemberNameLike("%"+form.getMemberName()+"%");
		}
		if(form.getStatus() != null){
			criteria.andStatusEqualTo(form.getStatus());
		}
		if(CollectionUtil.isNotEmpty(form.getStatusList())){
			criteria.andStatusIn(form.getStatusList());
		}
		if(form.getDateFrom() != null && form.getDateTo() != null){
			criteria.andPayTimeBetween(form.getDateFrom(), form.getDateTo());
		}
		if(CollectionUtil.isNotEmpty(form.getOrderIdList())){
			criteria.andIdIn(form.getOrderIdList());
		}
		return orderMapper.selectByExample(example);
	}

	@Override
	public PointOrder getOrder(Integer orderId) {
		return orderMapper.selectByPrimaryKey(orderId);
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
	public PointOrderPayResult addOrder(UmsMember member, UmsMemberAddress address, Integer goodsId, Integer buyNum) throws ServiceException{
		if(address == null){
			throw new ServiceException("order.address");
		}
		//校验库存
		PointGoods goods = goodsMapper.selectByPrimaryKey(goodsId);
		if(goods.getStock() < buyNum){
			throw new ServiceException("order.stock.noenough", new String[]{"「"+goods.getTitle()+"」"});
		}
		//校验限购数量
		if(goods.getLimitExchange() != 0 && goods.getLimitExchange() < buyNum) {
			throw new ServiceException("order.goods.limit", new String[]{goods.getLimitExchange().toString()});
		}
		//生成支付订单
		ConfigOrder orderConfig = configOrderService.getConfigOrder();
		Date now = DateUtil.date();

		PointOrder order = new PointOrder();
		order.setOrderNo(StringUtils.genOrderNo(member.getId()));
		order.setMemberId(member.getId());
		order.setMemberName(member.getCode());
		order.setMemberHeadImg(member.getHeadImg());
		order.setGoodsId(goods.getId());
		order.setGoodsName(goods.getTitle());
		order.setGoodsMainImg(goods.getMainImg());
		order.setStockType(goods.getStockType());
		order.setBuyOriPrice(goods.getOriPrice());
		order.setBuyPrice(goods.getPrice());
		order.setBuyPoint(goods.getPoint());
		order.setBuyNum(buyNum);
		order.setBuyTotalPrice(NumberUtil.mul(order.getBuyPrice(), buyNum));
		order.setBuyTotalPoint(NumberUtil.mul(order.getBuyPoint(), buyNum));
		order.setBuyTime(now);
		order.setPayEndTime(DateUtil.offsetMinute(now, orderConfig.getAutoCancelMinutes()));
		order.setStatus(OrderEnums.OrderStatus.WAIT_PAY.getValue());
		order.setRefundStatus(OrderEnums.RefundStatus.UN_REFUND.getValue());
		//收货地址
		order.setReceiveName(address.getRealname());
		order.setReceivePhone(address.getPhone());
		order.setReceiveAddress(address.getProvince()+address.getCity()+address.getArea()+address.getAddress());
		order.setReceiveLng(address.getLng());
		order.setReceiveLat(address.getLat());
		order.setAddTime(now);
		order.setUpdTime(now);
		orderMapper.insertSelective(order);

		//如果是下单减库存的话,即可减库存
		if(order.getStockType() == GoodsEnums.StockType.ORDER_REDUCE.getValue()){
			goodsService.reduceStock(goodsId, buyNum);
		}

		//超时未支付自动取消
		redisDelayQueueUtil.addDelayQueue(order.getOrderNo(), order.getPayEndTime(), RedisDelayQueueEnum.POINT_ORDER_AUTO_CANCEL_JOB.getCode());

		PointOrderPayResult result = new PointOrderPayResult();
		result.setOrderNo(order.getOrderNo());
		result.setAmount(order.getBuyTotalPrice());
		result.setPoint(order.getBuyTotalPoint());
		return result;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void paySus(String orderNo) throws ServiceException {
		PointOrder order = this.getOrderByOrderNo(orderNo);
		if(order != null && order.getStatus() == OrderEnums.OrderStatus.WAIT_PAY.getValue()) {
			//减库存
			if(order.getStockType() == GoodsEnums.StockType.PAY_REDUCE.getValue()){
				goodsService.reduceStock(order.getGoodsId(), order.getBuyNum());
			}

			PointOrder updateOrder = new PointOrder();
			updateOrder.setId(order.getId());
			updateOrder.setStatus(OrderEnums.OrderStatus.WAIT_DELIVERY.getValue());
			updateOrder.setUpdTime(DateUtil.date());
			updateOrder.setPayTime(updateOrder.getUpdTime());
			orderMapper.updateByPrimaryKeySelective(updateOrder);

			//扣除积分
			UmsMember member = memberService.getMemberById(order.getMemberId());
			memberPointLogService.outAccount(member, AccountEnums.MemberPointTypes.ACCOUNT_EXCHANGE_GOODS, order.getOrderNo(), order.getBuyTotalPoint(), "兑换商品", null);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public String delivery(List<OrderDeliveryForm> list) throws ServiceException {
		int susNum = 0, errorNum = 0;
		Date now = DateUtil.date();
		ConfigOrder config = configOrderService.getConfigOrder();
		for(OrderDeliveryForm form : list){
			if(StringUtils.isBlank(form.getDeliveryType())){
				throw new ServiceException("order.delivery.no");
			}
			PointOrder order = orderMapper.selectByPrimaryKey(form.getOrderId());
			if(order.getStatus() != OrderEnums.OrderStatus.WAIT_DELIVERY.getValue() || StringUtils.isBlank(form.getDeliveryNo())){
				errorNum++;
			}else{
				susNum++;
				PointOrder updatedOrder = new PointOrder();
				updatedOrder.setId(order.getId());
				updatedOrder.setOrderNo(order.getOrderNo());
				updatedOrder.setDeliveryNo(form.getDeliveryNo());
				updatedOrder.setDeliveryTime(now);
				updatedOrder.setFinishExpiredTime(DateUtil.offsetDay(now, config.getAutoReceiveDay()));
				updatedOrder.setDeliveryType(form.getDeliveryType());
				updatedOrder.setStatus(OrderEnums.OrderStatus.WAIT_RECEIVE.getValue());
				orderMapper.updateByPrimaryKeySelective(updatedOrder);

				//添加物流信息
				orderFreightService.initOrderFreight(updatedOrder);
				//添加自动确认收货
				redisDelayQueueUtil.addDelayQueue(order.getOrderNo(), updatedOrder.getFinishExpiredTime(), RedisDelayQueueEnum.POINT_ORDER_AUTO_FINISH_JOB.getCode());

				//发货通知
				UmsMember member = memberService.getMemberById(order.getMemberId());
				MemberNewsForm newsForm = new MemberNewsForm(MemberNewsEnums.MemberNewsTypes.NEWS_DELIVERY_ORDER);
				newsForm.initTurnParams("orderId", order.getId().toString());
				newsForm.replace("#goodsName#", order.getGoodsName());
				newsForm.setImg(order.getGoodsMainImg());
				memberNewsService.addMemberNews(member, newsForm);
			}
		}
		return "批量发货成功"+susNum+"条, 失败"+errorNum+"条";
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void confirmOrder(UmsMember member, Integer orderId) throws ServiceException {
		PointOrder order = this.getOrderByMember(member, orderId);
		if(order.getStatus() != OrderEnums.OrderStatus.WAIT_RECEIVE.getValue()){
			throw new ServiceException("order.confirm");
		}
		this.finishOrder(order);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void finishOrder(PointOrder order) {
		ConfigOrder config = configOrderService.getConfigOrder();

		PointOrder updateOrder = new PointOrder();
		updateOrder.setId(order.getId());
		updateOrder.setFinishTime(DateUtil.date());
		updateOrder.setStatus(OrderEnums.OrderStatus.FINISHED.getValue());
		updateOrder.setRefundExpiredTime(DateUtil.offsetDay(updateOrder.getFinishTime(), config.getLastRefundDay()));
		orderMapper.updateByPrimaryKeySelective(updateOrder);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateOrderAddress(UmsMember member, Integer orderId, Integer addressId) throws ServiceException {
		PointOrder order = this.getOrderByMember(member, orderId);
		if(order.getStatus() != OrderEnums.OrderStatus.WAIT_DELIVERY.getValue()){
			throw new ServiceException("order.edit.address");
		}

		UmsMemberAddress address = memberAddressService.getAddressById(addressId);
		PointOrder updateOrder = new PointOrder();
		updateOrder.setId(orderId);
		updateOrder.setReceiveName(address.getRealname());
		updateOrder.setReceivePhone(address.getPhone());
		updateOrder.setReceiveAddress(address.getProvince()+address.getCity()+address.getArea()+address.getAddress());
		updateOrder.setReceiveLng(address.getLng());
		updateOrder.setReceiveLat(address.getLat());
		updateOrder.setUpdTime(DateUtil.date());
		orderMapper.updateByPrimaryKeySelective(updateOrder);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void cancelOrder(UmsMember member, Integer orderId) throws ServiceException {
		PointOrder order = this.getOrderByMember(member, orderId);
		if(order.getStatus() != OrderEnums.OrderStatus.WAIT_PAY.getValue()){
			throw new ServiceException("order.unpay.cancel");
		}

		//取消订单并恢复库存
		this.cancelOrderAndAddStock(order);
	}

	private void cancelOrderAndAddStock(PointOrder order){
		goodsService.addStock(order.getGoodsId(), order.getBuyNum());

		PointOrder updateOrder = new PointOrder();
		updateOrder.setId(order.getId());
		updateOrder.setStatus(OrderEnums.OrderStatus.CANCEL.getValue());
		updateOrder.setUpdTime(DateUtil.date());
		orderMapper.updateByPrimaryKeySelective(updateOrder);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteOrder(UmsMember member, Integer orderId) throws ServiceException {
		PointOrder order = this.getOrderByMember(member, orderId);
		if(order.getStatus() != OrderEnums.OrderStatus.CANCEL.getValue()){
			throw new ServiceException("order.cancel.delete");
		}
		PointOrder updateOrder = new PointOrder();
		updateOrder.setId(order.getId());
		updateOrder.setDelInd(CommonConstant.YES_INT);
		updateOrder.setUpdTime(DateUtil.date());
		orderMapper.updateByPrimaryKeySelective(updateOrder);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void autoCancelOrder(String orderNo) {
		PointOrderExample orderExample = new PointOrderExample();
		orderExample.createCriteria().andOrderNoEqualTo(orderNo).andStatusEqualTo(OrderEnums.OrderStatus.WAIT_PAY.getValue());
		List<PointOrder> orderList = orderMapper.selectByExample(orderExample);
		if(CollectionUtil.isEmpty(orderList)) {
			return;
		}

		PointOrder order = orderList.get(0);
		if(order.getStockType() == GoodsEnums.StockType.ORDER_REDUCE.getValue()){
			goodsService.addStock(order.getGoodsId(), order.getBuyNum());
		}
		PointOrder updateOrder = new PointOrder();
		updateOrder.setId(order.getId());
		updateOrder.setStatus(OrderEnums.OrderStatus.CANCEL.getValue());
		updateOrder.setUpdTime(DateUtil.date());
		orderMapper.updateByPrimaryKeySelective(updateOrder);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void autoFinishOrder(String orderNo) {
		PointOrder order = this.getOrderByOrderNo(orderNo);
		if(order != null && order.getStatus() == OrderEnums.OrderStatus.WAIT_RECEIVE.getValue()){
			//自动确认收货
			this.finishOrder(order);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteOrderByAdmin(List<Integer> idList) {
		PointOrderExample example = new PointOrderExample();
		example.createCriteria().andIdIn(idList);
		PointOrder updateOrder = new PointOrder();
		updateOrder.setDelInd(CommonConstant.YES_INT);
		orderMapper.updateByExampleSelective(updateOrder, example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateOrderPayMethod(String orderNo, String payMethod, String payChannel) {
		PointOrderExample orderExample = new PointOrderExample();
		orderExample.createCriteria().andOrderNoEqualTo(orderNo);
		PointOrder updateOrder = new PointOrder();
		updateOrder.setPayMethod(payMethod);
		updateOrder.setPayChannel(payChannel);
		orderMapper.updateByExampleSelective(updateOrder, orderExample);
	}

	@Override
	public PointOrder getOrderByOrderNo(String orderNo) {
		PointOrderExample example = new PointOrderExample();
		example.createCriteria().andOrderNoEqualTo(orderNo).andDelIndEqualTo(CommonConstant.NO_INT);
		List<PointOrder> list = orderMapper.selectByExample(example);
		return CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void cancelAndRefundOrderSus(PointOrder order) {
		if(CollectionUtil.contains(CollectionUtil.newArrayList(
				OrderEnums.OrderStatus.WAIT_DELIVERY.getValue(),
				OrderEnums.OrderStatus.WAIT_RECEIVE.getValue(),
				OrderEnums.OrderStatus.FINISHED.getValue()
				), order.getStatus())){
			this.cancelOrderAndAddStock(order);
		}
	}

	/**
	 * 根据用户获取订单
	 * @author LeGreen
	 * @date   2022/5/5
	 * @param  member 用户
	 * @param  orderId 订单ID
	 * @return PointOrder
	 */
	private PointOrder getOrderByMember(UmsMember member, Integer orderId) throws ServiceException {
		PointOrderExample example = new PointOrderExample();
		example.createCriteria().andMemberIdEqualTo(member.getId()).andIdEqualTo(orderId);
		List<PointOrder> list = orderMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(list)){
			throw new ServiceException("order.notexist");
		}
		return list.get(0);
	}

}