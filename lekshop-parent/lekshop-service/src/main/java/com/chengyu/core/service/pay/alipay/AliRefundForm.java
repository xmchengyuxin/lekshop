package com.chengyu.core.service.pay.alipay;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AliRefundForm{
	
	//退款单号
	private String outRefundNo;
	
	//支付订单号
	private String outTradeNo;
	
	//退款金额
	private BigDecimal refundAmount;

}