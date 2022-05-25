package com.chengyu.core.domain.enums;

import lombok.Getter;

/**
 * @title  消息提醒枚举
 * @author LeGreen
 * @date   2021/12/28
 */
public class MemberRemindEnums {

	@Getter
	public enum MemberRemindTypes{
		/**
		 * 商家提醒通知
		 */
		WITHDRAW_SUS("提现到账", 1),
		NEW_ORDER("新订单待发货", 2),
		WAIT_VERIFY_REFUND("等待审核售后申请", 3),
		WAIT_CONFIRM_RECEIVE("买家已退货,等待确认收货", 4),
		CHAT("聊天信息", 5);

		private final String title;
		private final Integer type;

		MemberRemindTypes(String title, Integer type){
			this.title = title;
			this.type = type;
		}

	}

	@Getter
	public enum AdminRemindTypes{
		/**
		 * 后台提醒通知
		 */
		WAIT_VERIFY_REALNAME("待审核实名", 1, "/member/realname"),
		WAIT_VERIFY_BUYER("待审核买号", 2, "/buyVerify/taobaoVerify"),
		WAIT_VERIFY_BANK("待审核银行", 3, "/member/bank"),
		WAIT_VERIFY_RECHARGE("待审核充值", 4, "/fund/recharge"),
		WAIT_VERIFY_WITHDRAW("待审核提现", 5, "/fund/withdraw"),
		WAIT_VERIFY_COMPLAINT("待处理申诉", 6, "/sellerVerify/complaintVerify"),
		WAIT_VERIFY_SHOP("待审核店铺", 7, "/sellerVerify/sellerVerify"),
		WAIT_VERIFY_TASK("待审核任务", 8, "/sellerVerify/sellerTaskVerify");

		private final String title;
		private final Integer type;
		private final String router;

		AdminRemindTypes(String title, Integer type, String router){
			this.title = title;
			this.type = type;
			this.router = router;
		}

	}
	
}
