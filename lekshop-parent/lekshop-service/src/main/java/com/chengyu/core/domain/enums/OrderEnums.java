package com.chengyu.core.domain.enums;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Getter;

import java.util.List;

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

	public static List<Integer> OrderSusStatusList(){
		return CollectionUtil.newArrayList(OrderStatus.WAIT_DELIVERY.getValue(), OrderStatus.WAIT_RECEIVE.getValue(), OrderStatus.FINISHED.getValue());
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
	 * 退款详情状态枚举
	 */
	@Getter
	public enum RefundDetailStatus{
		/**0申请退款>>1等待买家退货>>2买家已退货>>3商家已拒绝>>4平台介入>>5退款完成>>6退款关闭**/
		APPLY(0),
		WAIT_BUYER_RETURN(1),
		BUYER_RETURNED(2),
		SALES_REFUSE(3),
		SERVICE_IN(4),
		REFUND_SUS(5),
		CLOSED(6);

		private final int value;

		RefundDetailStatus(int value){
			this.value = value;
		}
	}

	/**
	 * 退款类型枚举
	 */
	@Getter
	public enum RefundInd{
		/**1仅退款 2退货退款**/
		ONLY_REFUND(1),
		REFUND_AND_SALES_RETURN(2);

		private final int value;

		RefundInd(int value){
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

	/**
	 * 拼团状态枚举
	 */
	@Getter
	public enum GroupStatus{
		/**进行中 2成功 3失败**/
		ING(1),
		SUS(2),
		FAIL(3);

		private final int value;

		GroupStatus(int value){
			this.value = value;
		}
	}

}
