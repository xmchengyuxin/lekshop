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
		NEWS_TASK("任务消息", 1, "https://qiniuimg.wurun.net/icon/chat.png", ""),
		NEWS_WITHDRAW("提现消息", 2, "https://qiniuimg.wurun.net/icon/withdraw.png", ""),
		NEWS_PUNISHMENT("惩罚通知", 3, "https://qiniuimg.wurun.net/icon/fine.png", ""),
		NEWS_COMPLAINT("申诉通知", 4, "https://qiniuimg.wurun.net/icon/complain.png", ""),
		NEWS_RECHARGE("充值消息", 5, "https://qiniuimg.wurun.net/icon/overtime.png", ""),
		NEWS_USER("私信消息", 6, "https://qiniuimg.wurun.net/icon/overtime.png", "");
		/**NEWS_APPEAL("申诉结果通知", 6, "https://qiniuimg.wurun.net/icon/breakrule.png", ""),
		NEWS_TRAIN("培训结果通知", 7, "https://qiniuimg.wurun.net/icon/study.png", ""),
		NEWS_INSURANCE("保险通知", 8, "https://qiniuimg.wurun.net/icon/insurance.png", ""),
		NEWS_HEALTH_EXPIRED("健康证过期通知", 9, "https://qiniuimg.wurun.net/icon/healthcard.png", ""),
		NEWS_REFUND("退款通知", 10, "https://qiniuimg.wurun.net/icon/refund.png", ""),
		NEWS_BAIL_NOENOUGH("保证金不足通知", 11, "https://qiniuimg.wurun.net/icon/bail.png", ""),
		NEWS_ACTIVITY("活动通知", 12, "https://qiniuimg.wurun.net/icon/activity.png", ""),
		NEWS_EXCEPTION("异常审核通知", 13, "https://qiniuimg.wurun.net/icon/activity.png", "");**/
		
		private final String typeName;
		private final Integer type;
		private final String img;
		private final String turnPage;
		
		MemberNewsTypes(String typeName, Integer type, String img, String turnPage){
			this.typeName = typeName;
			this.type = type;
			this.img = img;
			this.turnPage = turnPage;
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
