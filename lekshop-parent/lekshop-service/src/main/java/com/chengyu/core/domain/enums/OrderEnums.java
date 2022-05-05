package com.chengyu.core.domain.enums;

import lombok.Getter;

/**
 * @title  订单枚举
 * @author LeGreen
 * @date   2022/4/29
 */
public class OrderEnums {

	/**
	 * 订单状态枚举
	 */
	@Getter
	public enum OrderStatus{
		/**0待支付>>1待发货>>2待收货>>3已完成>>4已取消>>5退款**/
		WAIT_PAY(0),
		WAIT_DELIVERY(1),
		WAIT_RECEIVE(2),
		FINISHED(3),
		CANCEL(4),
		REFUNDED(5);

		private final int value;

		OrderStatus(int value){
			this.value = value;
		}
	}

	/**
	 * 订单退款状态枚举
	 */
	@Getter
	public enum RefundStatus{
		/**0未退款>>1退款中>>2退款成功>>3退款驳回**/
		UN_REFUND(0),
		REFUNDING(1),
		REFUND_SUS(2),
		REFUND_FAILED(3);

		private final int value;

		RefundStatus(int value){
			this.value = value;
		}
	}

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
		GOOD(1),
		MEDIUM(2),
		BAD(3);

		private final int value;

		CommentType(int value){
			this.value = value;
		}
	}

}
