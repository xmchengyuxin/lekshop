package com.chengyu.core.domain.enums;

import lombok.Getter;

/**
 * @title  订单枚举
 * @author LeGreen
 * @date   2022/4/29
 */
public class OrderEnums {

	/**
	 * 评价状态枚举
	 */
	@Getter
	public enum CommentStatus{
		/**0待评价>>1已评价>>2已追评>>3已过期**/
		WAIT_COMMENT(0),
		COMMENTED(1),
		ADD_COMMENTED(2),
		EXPIRED(3);

		private final int value;

		CommentStatus(int value){
			this.value = value;
		}
	}

	/**
	 * 评价类型枚举
	 */
	@Getter
	public enum CommentType{
		/**1好评>>2中评>>3差评**/
		GOOD(0),
		MEDIUM(1),
		BAD(2);

		private final int value;

		CommentType(int value){
			this.value = value;
		}
	}

}
