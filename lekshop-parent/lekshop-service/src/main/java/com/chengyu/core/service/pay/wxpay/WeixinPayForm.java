package com.chengyu.core.service.pay.wxpay;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WeixinPayForm{
	
	private String body;
	
	private String ip;
	
	private String outTradeNo;
	
	private BigDecimal totalAmount;
	
	private String openId;
	
	private String notifyUrl;
	
	//app,xcx,gzh
	private String wxType;
	
	private String authCode;
	
	//
	private String deviceInfo;

}