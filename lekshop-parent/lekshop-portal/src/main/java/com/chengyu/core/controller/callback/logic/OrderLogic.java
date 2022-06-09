package com.chengyu.core.controller.callback.logic;

import com.chengyu.core.controller.callback.manager.CallbackFactory;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OmsOrder;
import com.chengyu.core.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderLogic")
public class OrderLogic extends CallbackFactory {

	@Autowired
	private OrderService orderService;

	@Override
	public void paySus(String orderNo) throws ServiceException {
		orderService.paySus(orderNo);
	}

	@Override
	public void refundSus(String orderNo) {
		OmsOrder order = orderService.getOrderByOrderNo(orderNo);
		orderService.cancelAndRefundOrderSus(order);
	}

}
