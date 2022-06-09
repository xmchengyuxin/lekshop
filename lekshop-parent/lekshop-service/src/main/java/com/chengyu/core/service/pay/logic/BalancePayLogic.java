package com.chengyu.core.service.pay.logic;

import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.AccountEnums;
import com.chengyu.core.domain.form.PayBaseForm;
import com.chengyu.core.domain.form.RefundBaseForm;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.service.funds.MemberAccountLogService;
import com.chengyu.core.service.pay.manager.PayFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @title  余额支付
 * @author LeGreen
 * @date   2022/6/9
 */
@Service("balancePayLogic")
public class BalancePayLogic extends PayFactory {

	@Autowired
	private MemberAccountLogService memberAccountLogService;

	@Override
	@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
	public String pay(UmsMember member, String orderNo, BigDecimal amount, PayBaseForm payBaseForm) throws Exception {
		memberAccountLogService.outAccount(member, payBaseForm.getMemberAccountTypes(), orderNo, amount, payBaseForm.getBody(), payBaseForm.getIp());
		return "noneedpay";
	}

	@Override
	public String refund(RefundBaseForm refundBaseForm) throws Exception {
		memberAccountLogService.inAccount(refundBaseForm.getMember(), AccountEnums.MemberAccountTypes.ACCOUNT_REFUND, refundBaseForm.getRefundNo(), refundBaseForm.getRefundAmount(),
				"退款成功", null);
		return CommonConstant.SUCCESS;
	}
}
