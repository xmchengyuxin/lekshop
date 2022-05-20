package com.chengyu.core.domain.enums;

import lombok.Getter;

/**
 * @title  动态枚举
 * @author LeGreen
 * @date   2022/5/20
 */
public class TrendsEnums {

	/**
	 * 店铺入驻状态枚举
	 */
	@Getter
	public enum TrendsType{
		/**1短视频>>2宝贝上新>>3种草>>4买家秀**/
		VIDEO(1),
		GOODS(2),
		DISCOVERY(3),
		BUYERSHOW(4);

		private final int value;

		TrendsType(int value){
			this.value = value;
		}
	}

}
