package com.chengyu.core.service.pay.wxpay;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WeixinRefundForm{
	
	private String outRefundNo;
	
	private String outTradeNo;
	
	private BigDecimal totalAmount;
	
	private BigDecimal refundFee;
	
	private String notifyUrl;
	
	//app,xcx,gzh
	private String wxType;

}