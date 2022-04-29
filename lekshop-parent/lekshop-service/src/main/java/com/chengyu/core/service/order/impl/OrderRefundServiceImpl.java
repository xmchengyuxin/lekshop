package com.chengyu.core.service.order.impl;

import com.chengyu.core.domain.form.OrderRefundForm;
import com.chengyu.core.domain.form.OrderRefundSearchForm;
import com.chengyu.core.domain.result.OrderRefundResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OmsOrderRefund;
import com.chengyu.core.model.SysAdmin;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.service.order.OrderRefundService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @title  订单售后
 * @author LeGreen
 * @date   2022/4/29
 */
@Service
public class OrderRefundServiceImpl implements OrderRefundService {


	@Override
	public List<OmsOrderRefund> getRefundList(OrderRefundSearchForm form, Integer page, Integer pageSize) {
		return null;
	}

	@Override
	public OrderRefundResult getOrderRefund(Integer refundId) {
		return null;
	}

	@Override
	public void applyRefund(UmsMember member, OrderRefundForm form) throws ServiceException {

	}

	@Override
	public void agreeRefund(UmsShop shop, Integer refundId) throws ServiceException {

	}

	@Override
	public void refuseRefund(UmsShop shop, Integer refundId, String reason) throws ServiceException {

	}

	@Override
	public void applyCustomerServiceIn(UmsMember member, Integer refundId) throws ServiceException {

	}

	@Override
	public void agreeRefundByCustomer(SysAdmin admin, Integer refundId) throws ServiceException {

	}

	@Override
	public void refuseRefundByCustomer(SysAdmin admin, Integer refundId, String reason) throws ServiceException {

	}

	@Override
	public void salesReturnByMember(UmsMember member, Integer refundId, String deliveryType, String deliveryNo) throws ServiceException {

	}

	@Override
	public void confirmReceiveByShop(UmsShop shop, Integer refundId) throws ServiceException {

	}
}