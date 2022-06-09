package com.chengyu.core.service.pay.logic;

import com.chengyu.core.domain.form.PayBaseForm;
import com.chengyu.core.domain.form.RefundBaseForm;
import com.chengyu.core.model.SysZfbConfig;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.service.pay.alipay.AliPay;
import com.chengyu.core.service.pay.alipay.AliPayForm;
import com.chengyu.core.service.pay.alipay.AliRefundForm;
import com.chengyu.core.service.pay.manager.PayFactory;
import com.chengyu.core.util.ali.AliUtil;
import com.chengyu.core.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @title  支付宝支付
 * @author LeGreen
 * @date   2022/6/9
 */
@Service("aliPayLogic")
public class AliPayLogic extends PayFactory {


	@Override
	public String pay(UmsMember member, String orderNo, BigDecimal amount, PayBaseForm payBaseForm) throws Exception {
		SysZfbConfig config = AliUtil.config;
		AliPayForm aliForm = new AliPayForm();
		aliForm.setSubject(StringUtils.isNotBlank(payBaseForm.getBody()) ? payBaseForm.getBody() : "订单支付");
		aliForm.setBody("订单");
		aliForm.setOutTradeNo(orderNo);
		aliForm.setTotalAmount(amount);
		aliForm.setNotifyUrl(config.getPayNotifyUrl()+ payBaseForm.getPayNotifyUrl());
		return new AliPay().pay(aliForm);
	}

	@Override
	public String refund(RefundBaseForm refundBaseForm) throws Exception {
		AliRefundForm refundForm = new AliRefundForm();
		refundForm.setOutTradeNo(refundBaseForm.getOrderNo());
		refundForm.setOutRefundNo(refundBaseForm.getRefundNo());
		refundForm.setRefundAmount(refundBaseForm.getRefundAmount());
		return new AliPay().refund(refundForm);
	}
}
