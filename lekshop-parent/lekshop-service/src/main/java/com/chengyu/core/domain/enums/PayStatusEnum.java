package com.chengyu.core.domain.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {

	/**
	 * 支付状态
	 */
	PAY_SUCCESS("SUCCESS", "支付成功"),
	PAY_USERPAYING("USERPAYING", "支付中"),
	PAY_FAILED("FAILED", "支付失败"),
	PAY_REFUND("REFUND", "已退款");
	
	private String key;
    private String name;

    PayStatusEnum(String key, String name) {
    	this.key = key;
        this.name = name;
    }
}
