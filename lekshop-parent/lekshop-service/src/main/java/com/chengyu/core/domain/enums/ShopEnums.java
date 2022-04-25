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

	/**
	 * 优惠券枚举
	 */
	@Getter
	public enum CouponType{
		/**满减券**/
		FULL_REDUCE_COUPON(1),
		/**折扣券**/
		DISCOUNT_COUPON(2);

		private final int value;

		CouponType(int value){
			this.value = value;
		}
	}

	/**
	 * 优惠券可用范围枚举
	 */
	@Getter
	public enum CouponUseType{
		/**全部商品**/
		ALL_GOODS(1),
		/**指定商品**/
		SOME_GOODS(2),
		/**指定分类**/
		SOME_CATE(3);

		private final int value;

		CouponUseType(int value){
			this.value = value;
		}
	}

	/**
	 * 优惠券有效时间类型
	 */
	@Getter
	public enum CouponValidityType{
		/**长期有效**/
		LONG_TERM(1),
		/**有限天数**/
		LIMIT_DAYS(2),
		/**固定时间**/
		FIXED_TIME(3);

		private final int value;

		CouponValidityType(int value){
			this.value = value;
		}
	}

	/**
	 * 优惠券有效时间类型
	 */
	@Getter
	public enum CouponStatus{
		/**发放中**/
		SENDING(1),
		/**已领完**/
		END(2),
		/**已过期**/
		EXPIRED(3);

		private final int value;

		CouponStatus(int value){
			this.value = value;
		}
	}

	/**
	 * 计价方式
	 */
	@Getter
	public enum FreightValuteType{
		/**按件数**/
		BY_NUMBER(1),
		/**按重量**/
		BY_WEIGHT(2),
		/**按体积**/
		BY_BULK(3);

		private final int value;

		FreightValuteType(int value){
			this.value = value;
		}
	}
}
