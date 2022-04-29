package com.chengyu.core.service.order.impl;

import cn.hutool.core.date.DateUtil;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.OmsOrderMapper;
import com.chengyu.core.mapper.OmsOrderPriceLogMapper;
import com.chengyu.core.model.OmsOrder;
import com.chengyu.core.model.OmsOrderPriceLog;
import com.chengyu.core.model.OmsOrderPriceLogExample;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.service.order.OrderPriceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderPriceLogServiceImpl implements OrderPriceLogService {
	
	@Autowired
	private OmsOrderPriceLogMapper orderPriceLogMapper;
	@Autowired
	private OmsOrderMapper orderMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateOrderPrice(UmsShop shop, Integer orderId, BigDecimal editPrice) {
		OmsOrder order = orderMapper.selectByPrimaryKey(orderId);
		BigDecimal oldAmount = order.getPayPrice();
		BigDecimal subAmount = editPrice.subtract(oldAmount);

		OmsOrder updateOrder = new OmsOrder();
		updateOrder.setId(orderId);
		updateOrder.setPayPrice(editPrice);
		orderMapper.updateByPrimaryKeySelective(updateOrder);

		OmsOrderPriceLog orderPriceLog = new OmsOrderPriceLog();
		orderPriceLog.setOrderId(order.getId());
		orderPriceLog.setOrderNo(order.getOrderNo());
		orderPriceLog.setOldPrice(oldAmount);
		orderPriceLog.setNowPrice(editPrice);
		orderPriceLog.setEditPrice(subAmount);
		orderPriceLog.setEditId(shop.getId());
		orderPriceLog.setEditName(shop.getName());
		orderPriceLog.setAddTime(DateUtil.date());
		orderPriceLog.setUpdTime(orderPriceLog.getAddTime());
		orderPriceLogMapper.insert(orderPriceLog);
	}

	@Override
	public List<OmsOrderPriceLog> getOrderPriceLogByOrderId(Integer orderId) {
		OmsOrderPriceLogExample example = new OmsOrderPriceLogExample();
		example.setOrderByClause("add_time desc");
		example.createCriteria().andOrderIdEqualTo(orderId);
		return orderPriceLogMapper.selectByExample(example);
	}
	

}