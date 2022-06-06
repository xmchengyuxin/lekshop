package com.chengyu.core.domain.enums;

import lombok.Getter;

/**
 * @title  平台标识
 * @author LeGreen
 * @date   2021/7/26
 */
@Getter
public enum InterfaceEnums {
	/**
	 * 阿里平台
	 */
	ALI("ali"),
	/**
	 * 万维易网
	 */
	WWYW("wwyw"),
	/**
	 * 极光
	 */
	JIGUANG("jg"),
	/**
	 * 七牛云
	 */
	QINIU("qiniu"),
	/**
	 * 淘发客
	 */
	TAOFAKE("taofake"),
	/**
	 * 微信
	 */
	WEIXIN("wx"),
	/**
	 * 支付宝
	 */
	ZFB("zfb"),
	/**
	 * 京东万象
	 */
	JDWX("jdwx");

	private final String key;

	InterfaceEnums(String key){
		this.key = key;
	}
}
