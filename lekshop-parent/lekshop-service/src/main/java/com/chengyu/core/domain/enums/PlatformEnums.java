package com.chengyu.core.domain.enums;

import lombok.Getter;

/**
 * @title  平台标识
 * @author LeGreen
 * @date   2021/7/26
 */
@Getter
public enum PlatformEnums {
	/**
	 * 平台类型
	 */
	TUBOSHU("tuboshu"),
	ZBW("zbw"),
	JDXM("jdxm"),
	CHITU("chitu");

	private final String key;

	PlatformEnums(String key){
		this.key = key;
	}
}
