package com.chengyu.core.service.order.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.domain.enums.OrderEnums;
import com.chengyu.core.domain.form.OrderSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.OmsOrderMapper;
import com.chengyu.core.mapper.OmsOrderWriteOffMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.service.order.OrderService;
import com.chengyu.core.service.order.OrderWriteOffService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderWriteOffServiceImpl implements OrderWriteOffService {

	@Autowired
	private OmsOrderWriteOffMapper orderWriteOffMapper;
	@Autowired
	private OmsOrderMapper orderMapper;
	@Autowired
	private OrderService orderService;

	@Override
	public List<OmsOrderWriteOff> getWriteOffOrderList(OrderSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		OmsOrderWriteOffExample example = new OmsOrderWriteOffExample();
		example.setOrderByClause("add_time desc");
		OmsOrderWriteOffExample.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(form.getVerifyCode())) {
			criteria.andVerifyCodeEqualTo(form.getVerifyCode());
		}
		return orderWriteOffMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
	public void writeOff(UmsShopAccount shopAccount, String verifyCode) throws ServiceException {
		//查询核销码是否存在
		OmsOrderExample example = new OmsOrderExample();
		example.createCriteria().andVerifyCodeEqualTo(verifyCode).andStatusIn(
				CollectionUtil.newArrayList(OrderEnums.OrderStatus.WAIT_DELIVERY.getValue(), OrderEnums.OrderStatus.WAIT_RECEIVE.getValue()));
		List<OmsOrder> orderList = orderMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(orderList)) {
			throw new ServiceException("核销码不存在, 请核查");
		}

		//记录核销
		OmsOrder order = orderList.get(0);
		OmsOrderWriteOff writeOff = new OmsOrderWriteOff();
		writeOff.setVerifyCode(verifyCode);
		writeOff.setOrderId(order.getId());
		writeOff.setOrderNo(order.getOrderNo());
		writeOff.setMemberId(order.getMemberId());
		writeOff.setMemberName(order.getMemberName());
		writeOff.setMemberHeadImg(order.getMemberHeadImg());
		writeOff.setAccountId(shopAccount.getId());
		writeOff.setAccountName(shopAccount.getRealname());
		writeOff.setShopId(shopAccount.getShopId());
		writeOff.setShopName(shopAccount.getShopName());
		writeOff.setBuyTotalPrice(order.getTotalPrice());
		orderWriteOffMapper.insertSelective(writeOff);

		//完成订单
		orderService.finishOrder(orderList.get(0));
	}
}