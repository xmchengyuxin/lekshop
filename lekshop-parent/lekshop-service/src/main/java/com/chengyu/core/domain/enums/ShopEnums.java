package com.chengyu.core.domain.enums;

import lombok.Getter;

/**
 * @title  店铺枚举
 * @author LeGreen
 * @date   2022/4/23
 */
public class ShopEnums {

	/**
	 * 店铺入驻状态枚举
	 */
	@Getter
	public enum ShopStatus{
		/**提交资质**/
		SUBMIT_CERT(0),
		/**完善店铺**/
		SUBMIT_SHOP(1),
		/**提交审核**/
		SUBMIT_VERIFY(2),
		/**通过**/
		SUCCESS(3),
		/**驳回**/
		FAIL(4);

		private final int value;

		ShopStatus(int value){
			this.value = value;
		}
	}

	/**
	 * 店铺主页样式枚举
	 */
	@Getter
	public enum CateStyle{
		/**一级类目-大图**/
		ONE_BIG_IMG(1),
		/**一级类目-九宫格**/
		ONE_NINE_IMG(2),
		/**二级类目**/
		TWO_CATE(3),
		/**多级类目样式**/
		MUL_CATE(4);

		private final int value;

		CateStyle(int value){
			this.value = value;
		}
	}

}
