package com.chengyu.core.service.pay.alipay;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AliPayForm{
	
	private String body;
	
	private String subject;
	
	private String outTradeNo;
	
	private BigDecimal totalAmount;
	
	private String notifyUrl;
	
	private String authCode;
	
	private String storeId;
}