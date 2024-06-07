package com.chengyu.core.service.point.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.result.PointOrderFreightResult;
import com.chengyu.core.mapper.PointOrderFreightDetailMapper;
import com.chengyu.core.mapper.PointOrderFreightMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.service.point.PointOrderFreightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PointOrderFreightServiceImpl implements PointOrderFreightService {
	
	@Autowired
	private PointOrderFreightMapper orderFreightMapper;
	@Autowired
	private PointOrderFreightDetailMapper orderFreightDetailMapper;


	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void initOrderFreight(PointOrder order) {
		PointOrderFreight orderFreight = new PointOrderFreight();
		orderFreight.setOrderId(order.getId());
		orderFreight.setOrderNo(order.getOrderNo());
		orderFreight.setDeliveryType(order.getDeliveryType());
//		orderFreight.setDeliveryName();
		orderFreight.setDeliveryNo(order.getDeliveryNo());
		orderFreight.setStatus(CommonConstant.WAIT_INT);
		orderFreight.setAddTime(DateUtil.date());
		orderFreight.setUpdTime(orderFreight.getAddTime());
		orderFreightMapper.insert(orderFreight);

		PointOrderFreightDetail detail = new PointOrderFreightDetail();
		detail.setOrderId(order.getId());
		detail.setOrderNo(order.getOrderNo());
		detail.setOrderFreightId(orderFreight.getId());
		detail.setDeliveryNo(order.getDeliveryNo());
		detail.setDetail("包裹正在等待揽收");
		detail.setAddTime(DateUtil.date());
		detail.setUpdTime(detail.getAddTime());
		orderFreightDetailMapper.insertSelective(detail);
	}

	@Override
	public PointOrderFreightResult getOrderFreight(Integer orderId) {
		PointOrderFreightExample example = new PointOrderFreightExample();
		example.createCriteria().andOrderIdEqualTo(orderId);
		List<PointOrderFreight> list = orderFreightMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(list)){
			return null;
		}
		PointOrderFreightResult result = new PointOrderFreightResult();
		result.setOrderFreight(list.get(0));

		PointOrderFreightDetailExample detailExample = new PointOrderFreightDetailExample();
		detailExample.setOrderByClause("add_time desc");
		detailExample.createCriteria().andOrderFreightIdEqualTo(result.getOrderFreight().getId());
		result.setFreightDetailList(orderFreightDetailMapper.selectByExample(detailExample));
		return result;
	}

}