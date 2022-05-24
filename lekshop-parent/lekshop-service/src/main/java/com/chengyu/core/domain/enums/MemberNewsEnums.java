package com.chengyu.core.domain.enums;

import lombok.Getter;

/**
 * @title  用户消息枚举
 * @author LeGreen
 * @date   2021/1/7
 */
public class MemberNewsEnums {

	@Getter
	public enum MemberNewsTypes{
		NEWS_DELIVERY_ORDER(1, "您的订单已发货", "订单[#goodsName#]已发货,您可以在订单列表查询物流信息。"),
		NEWS_DELIVERY_DETAIL(2, "您的订单物流状态发生了变化", "订单[#goodsName#]的物流有了新变化,点击查看详情>>"),
		NEWS_FINISH_ORDER(3, "您的订单已完成", "订单[#goodsName#]已配送完成,期待您分享商品评价与购物新得。"),
		NEWS_AGREE_REFUND(4, "商家同意了您的退款申请", "卖家已经同意了您的订单[#goodsName#]退款申请,退款金额将原路返回,请实时关注您的账户变动。"),
		NEWS_REFUSE_REFUND(5, "商家驳回了您的退款申请", "卖家驳回了您的订单[#goodsName#]退款申请,如有疑问您可以申请客服介入,点击查看详情>>"),
		NEWS_RECHARGE( 6, "您已充值成功", "您已充值成功,请实时关注您的账户变动。"),
		NEWS_WITHDRAW_SUS(7, "您已提现成功", "您已提现成功,请实时关注您的账户变动。"),
		NEWS_WITHDRAW_FAIL(8, "您的提现失败了", "您的提现失败了,点击查看失败原因>>"),
		NEWS_USER(9, "您有一条新私信", ""),
		NEWS_COMMENT_REMIND(10, "评价提醒", "您买的[#goodsName#]感受如何? 期待您分享商品评价与购物新的。点击评价>>"),
		NEWS_GROUP_SUS(11, "您已拼团成功", "您的订单[#goodsName#]已拼团成功, 请您耐心等待卖家发货。点击查看详情>>"),
		NEWS_GROUP_FAIL(12, "您的拼团失败了", "您的订单[#goodsName#]拼团超时失败了, 金额将原路返回,请实时关注您的账户变动。点击查看详情>>");

		private final Integer type;
		private final String title;
		private final String content;
		
		MemberNewsTypes(Integer type, String title, String content){
			this.type = type;
			this.title = title;
			this.content = content;
		}
		
	}


	@Getter
	public enum WeixinNoticeTypes{
		/**
		 * 微信模板消息通知
		 * 成为会员通知----新会员绑定通知
		 * 派单成功提醒----接单成功通知
		 * 订单失败通知----订单审核不成功
		 * 确认收货提醒----催收货
		 * 订单评价提醒----催评价
		 * 审核失败通知----评价审核不成功
		 * 未付款订单即将关闭提醒----订单超时
		 * 账号禁用通知----平台处罚通知
		 * 提现成功通知----钱提现成功
		 * 提现审核失败通知----钱提现失败
		 */
		NOTICE_GRAB_ORDER("派单成功通知", "1"),
		NOTICE_REJECT_ORDER("订单失败通知", "2"),
		NOTICE_MAKE_RECEIVE_ORDER("催收货通知", "3"),
		NOTICE_MAKE_COMMENT("催评价通知", "4"),
		NOTICE_REJECT_COMMENT("评价审核不成功", "5"),
		NOTICE_ORDER_WILL_CLOSE("未付款订单即将关闭提醒", "6"),
		NOTICE_ACCOUNT_CLOSE("平台处罚通知", "7"),
		NOTICE_WITHDRAW_SUS("提现成功通知", "8"),
		NOTICE_WITHDRAW_FAIL("提现失败通知", "9");

		private final String typeName;
		private final String templateId;

		WeixinNoticeTypes(String typeName, String templateId){
			this.typeName = typeName;
			this.templateId = templateId;
		}

	}
	
}
