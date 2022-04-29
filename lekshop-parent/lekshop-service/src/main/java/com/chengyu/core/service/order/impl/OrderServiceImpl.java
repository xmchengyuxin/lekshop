package com.chengyu.core.service.order.impl;

import com.chengyu.core.domain.form.OrderAddForm;
import com.chengyu.core.domain.form.OrderDeliveryForm;
import com.chengyu.core.domain.form.OrderSearchForm;
import com.chengyu.core.domain.result.OrderPayResult;
import com.chengyu.core.domain.result.OrderResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.OmsOrderMapper;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberAddress;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @title  订单
 * @author LeGreen
 * @date   2022/4/29
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OmsOrderMapper orderMapper;

	@Override
	public CommonPage<OrderResult> getOrderList(OrderSearchForm form, Integer page, Integer pageSize) {
		return null;
	}

	@Override
	public OrderResult getOrder(Integer orderId) {
		return null;
	}

	@Override
	public OrderPayResult addOrder(UmsMember member, UmsMemberAddress address, List<OrderAddForm> addFormList) throws ServiceException {
		return null;
	}

	@Override
	public void paySus(String payOrderNo) {

	}

	@Override
	public String delivery(UmsShop shop, List<OrderDeliveryForm> list) throws ServiceException {
		return null;
	}

	@Override
	public void confirmOrder(UmsMember member, Integer orderId) throws ServiceException {

	}

	@Override
	public void updateOrderAddress(UmsMember member, Integer orderId, Integer addressId) throws ServiceException {

	}

	@Override
	public void cancelOrder(UmsMember member, Integer orderId) throws ServiceException {

	}

	@Override
	public void deleteOrder(UmsMember member, Integer orderId) throws ServiceException {

	}
}