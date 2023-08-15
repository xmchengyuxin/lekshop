package com.chengyu.core.service.pay.logic;

import com.chengyu.core.domain.enums.PayEnum;
import com.chengyu.core.domain.form.PayBaseForm;
import com.chengyu.core.domain.form.RefundBaseForm;
import com.chengyu.core.model.SysWeixinConfig;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.service.pay.manager.PayFactory;
import com.chengyu.core.service.pay.wxpay.WeixinPayForm;
import com.chengyu.core.service.pay.wxpay.WeixinPayV3;
import com.chengyu.core.service.pay.wxpay.WeixinRefundForm;
import com.chengyu.core.util.weixin.WechatUtil;
import com.chengyu.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @title  微信支付
 * @author LeGreen
 * @date   2022/6/9
 */
@Service("weixinPayLogic")
public class WeixinPayLogic extends PayFactory {

	@Autowired
	private WeixinPayV3 weixinPayV3;

	@Override
	public String pay(UmsMember member, String orderNo, BigDecimal amount, PayBaseForm payBaseForm) throws Exception {
		SysWeixinConfig config = WechatUtil.config;
		WeixinPayForm wxForm = new WeixinPayForm();
		wxForm.setBody(StringUtils.isNotBlank(payBaseForm.getBody()) ? payBaseForm.getBody() : "订单支付");
		wxForm.setIp(payBaseForm.getIp());
		wxForm.setOutTradeNo(orderNo);
		wxForm.setTotalAmount(amount);
		wxForm.setWxType(payBaseForm.getApplicationType());
		wxForm.setOpenId(this.getMemberOpenId(member, payBaseForm.getApplicationType()));
		wxForm.setNotifyUrl(config.getPayNotifyUrl()+ payBaseForm.getPayNotifyUrl());
		return weixinPayV3.pay(wxForm);
	}

	@Override
	public String refund(RefundBaseForm refundBaseForm) throws Exception {
		SysWeixinConfig config = WechatUtil.config;
		WeixinRefundForm refundForm = new WeixinRefundForm();
		refundForm.setWxType(refundBaseForm.getPayChannel());
		refundForm.setOutRefundNo(refundBaseForm.getRefundNo());
		refundForm.setOutTradeNo(refundBaseForm.getOrderNo());
		refundForm.setRefundFee(refundBaseForm.getRefundAmount());
		refundForm.setTotalAmount(refundBaseForm.getOrderAmount());
		refundForm.setNotifyUrl(config.getPayNotifyUrl()+refundBaseForm.getRefundNotifyUrl());
		weixinPayV3.refund(refundForm);
		return null;
	}

	private String getMemberOpenId(UmsMember member, String applicationType){
		if(PayEnum.PayChannel.GZH.getValue().equals(applicationType)){
			return member.getGzhOpenId();
		}else if(PayEnum.PayChannel.XCX.getValue().equals(applicationType)){
			return member.getXcxOpenId();
		}else{
			return member.getKfOpenId();
		}
	}
}
