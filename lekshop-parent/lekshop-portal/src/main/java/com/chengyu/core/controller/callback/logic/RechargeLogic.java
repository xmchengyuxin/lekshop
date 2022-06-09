package com.chengyu.core.controller.callback.logic;

import com.chengyu.core.controller.callback.manager.CallbackFactory;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMemberRecharge;
import com.chengyu.core.service.member.MemberRechargeService;
import com.chengyu.core.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @title  充值成功
 * @author LeGreen
 * @date   2022/6/9
 */
@Service("rechargeLogic")
public class RechargeLogic extends CallbackFactory {

	@Autowired
	private MemberRechargeService rechargeService;

	@Override
	public void paySus(String orderNo) throws ServiceException {
		UmsMemberRecharge recharge = rechargeService.getRechargeLogByOrderNo(orderNo);
		if(Objects.equals(recharge.getStatus(), CommonConstant.WAIT)){
			rechargeService.rechargSusByCallback(recharge);
		}
	}

	@Override
	public void refundSus(String orderNo) {
		throw new UnsupportedOperationException();
	}

}
