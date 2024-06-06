package com.chengyu.core.service.order.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.DeliveryEnums;
import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.domain.result.OrderFreightResult;
import com.chengyu.core.mapper.OmsOrderFreightDetailMapper;
import com.chengyu.core.mapper.OmsOrderFreightMapper;
import com.chengyu.core.mapper.OmsOrderMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.service.order.OrderFreightService;
import com.chengyu.core.util.RedisUtil;
import com.chengyu.core.util.kuaidi.Kuaidi100Util;
import com.chengyu.core.util.kuaidi.QueryDeliveryData;
import com.chengyu.core.util.kuaidi.QueryDeliveryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class OrderFreightServiceImpl implements OrderFreightService {
	
	@Autowired
	private OmsOrderFreightMapper orderFreightMapper;
	@Autowired
	private OmsOrderFreightDetailMapper orderFreightDetailMapper;
	@Autowired
	private OmsOrderMapper orderMapper;
	@Autowired
	private RedisUtil redisUtil;


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

	@Override
	public OrderFreightResult getOrderFreight(Integer orderId) {
		OmsOrderFreightExample example = new OmsOrderFreightExample();
		example.createCriteria().andOrderIdEqualTo(orderId);
		List<OmsOrderFreight> list = orderFreightMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(list)){
			return null;
		}
		OmsOrderFreight freight = list.get(0);
		OrderFreightResult result = new OrderFreightResult();
		result.setOrderFreight(freight);

		OmsOrderFreightDetailExample detailExample = new OmsOrderFreightDetailExample();
		detailExample.setOrderByClause("add_time desc");
		detailExample.createCriteria().andOrderFreightIdEqualTo(result.getOrderFreight().getId());
		List<OmsOrderFreightDetail> detailList = orderFreightDetailMapper.selectByExample(detailExample);
		if(CollectionUtil.isEmpty(detailList)) {
			detailList = new ArrayList<>();
		}
		//快递100实时查询物流
		try {
			List<QueryDeliveryData> trackData = (List<QueryDeliveryData>) redisUtil.getRedisValue(RedisEnums.KUAIDI_KEY.getKey()+freight.getDeliveryNo());
			if(CollectionUtil.isEmpty(trackData)) {
				OmsOrder order = orderMapper.selectByPrimaryKey(orderId);
				QueryDeliveryResponse kuaidiResponse = Kuaidi100Util.queryTrack(freight.getDeliveryNo(), order.getReceivePhone(), getKuaidi100CompanyName(freight.getDeliveryType()));
				if("200".equals(kuaidiResponse.getStatus())) {
					trackData = kuaidiResponse.getData();
					redisUtil.setRedisValue(RedisEnums.KUAIDI_KEY.getKey() + freight.getDeliveryNo(), trackData, 1L, TimeUnit.HOURS);

					OmsOrderFreight updateFreight = new OmsOrderFreight();
					updateFreight.setId(freight.getId());
					//0在途，1揽收，2疑难，3签收，4退签，5派件，8清关，14拒签
					if("3".equals(kuaidiResponse.getState())) {
						updateFreight.setStatus(2);
					} else {
						updateFreight.setStatus(1);
					}
					orderFreightMapper.updateByPrimaryKeySelective(updateFreight);
				}
			}

			for(QueryDeliveryData data : trackData) {
				OmsOrderFreightDetail detail = new OmsOrderFreightDetail();
				detail.setOrderId(freight.getOrderId());
				detail.setOrderNo(freight.getOrderNo());
				detail.setOrderFreightId(freight.getId());
				detail.setDeliveryNo(freight.getDeliveryNo());
				detail.setDetail(data.getContext());
				detail.setAddTime(DateUtil.parseDateTime(data.getTime()));
				detailList.add(detail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.setFreightDetailList(detailList);
		return result;
	}

	private String getKuaidi100CompanyName(String deliveryType) {
		for(DeliveryEnums.DeliveryType delivery : DeliveryEnums.DeliveryType.values()) {
			if(delivery.getName().equals(deliveryType)) {
				return delivery.getKey();
			}
		}
		return null;
	}

}