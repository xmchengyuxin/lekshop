package com.chengyu.core.controller.callback.logic;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.controller.callback.manager.CallbackFactory;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OmsOrderRefund;
import com.chengyu.core.service.order.OrderRefundService;
import com.chengyu.core.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("refundLogic")
public class RefundLogic extends CallbackFactory {

	@Autowired
	private OrderRefundService orderRefundService;

	@Override
	public void paySus(String orderNo) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void refundSus(String orderNo) {
		OmsOrderRefund refund = orderRefundService.getOrderRefundByRefundNo(orderNo);
		if(CollectionUtil.contains(CollectionUtil.newArrayList(0,1,2,3,4),refund.getStatus())){
			orderRefundService.refundSusCallback(refund);
		}
	}

}
