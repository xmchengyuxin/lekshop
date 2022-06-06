package com.chengyu.core.domain.enums;

import lombok.Getter;

/**
 * @title  第三方接口标识
 * @author LeGreen
 * @date   2021/10/22
 */
@Getter
public enum ThirdEnums {
	/**
	 * 第三方接口标识
	 */
	UPLOAD("upload"),
	IDCARD("idcard"),
	BANK("bank"),
	GOODS("goods");

	private final String key;

	ThirdEnums(String key){
		this.key = key;
	}
}
