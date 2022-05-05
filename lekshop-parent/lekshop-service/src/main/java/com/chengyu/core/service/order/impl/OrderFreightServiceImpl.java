package com.chengyu.core.service.order.impl;

import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.mapper.OmsOrderFreightDetailMapper;
import com.chengyu.core.mapper.OmsOrderFreightMapper;
import com.chengyu.core.model.OmsOrder;
import com.chengyu.core.model.OmsOrderFreight;
import com.chengyu.core.model.OmsOrderFreightDetail;
import com.chengyu.core.service.order.OrderFreightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderFreightServiceImpl implements OrderFreightService {
	
	@Autowired
	private OmsOrderFreightMapper orderFreightMapper;
	@Autowired
	private OmsOrderFreightDetailMapper orderFreightDetailMapper;


	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void initOrderFreight(OmsOrder order) {
		OmsOrderFreight orderFreight = new OmsOrderFreight();
		orderFreight.setOrderId(order.getId());
		orderFreight.setOrderNo(order.getOrderNo());
		orderFreight.setDeliveryType(order.getDeliveryType());
//		orderFreight.setDeliveryName();
		orderFreight.setDeliveryNo(order.getDeliveryNo());
		orderFreight.setStatus(CommonConstant.WAIT_INT);
		orderFreight.setAddTime(DateUtil.date());
		orderFreight.setUpdTime(orderFreight.getAddTime());
		orderFreightMapper.insert(orderFreight);

		OmsOrderFreightDetail detail = new OmsOrderFreightDetail();
		detail.setOrderId(order.getId());
		detail.setOrderNo(order.getOrderNo());
		detail.setOrderFreightId(orderFreight.getId());
		detail.setDeliveryNo(order.getDeliveryNo());
		detail.setDetail("包裹正在等待揽收");
		detail.setAddTime(DateUtil.date());
		detail.setUpdTime(detail.getAddTime());
		orderFreightDetailMapper.insert(detail);
	}

}