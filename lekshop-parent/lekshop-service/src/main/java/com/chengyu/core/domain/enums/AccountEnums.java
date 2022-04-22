package com.chengyu.core.domain.enums;

import lombok.Getter;

/**
 * @title  资金类枚举
 * @author LeGreen
 * @date   2020/12/31
 */
public class AccountEnums {

	/**
	 * 账户类型枚举
	 */
	@Getter
	public enum AccountTypes{
		/**支付宝**/
		ZFB(1),
		/**微信**/
		WEIXIN(2),
		/**财付通**/
		CAIFUTONG(3),
		/**银行**/
		BANK(3),
		/**网银**/
		WANGYIN(4);

		private final Integer value;

		AccountTypes(Integer value){
			this.value = value;
		}
	}

	@Getter
	public enum WithdrawTypes{
		/**银行**/
		BANK(1),
		/**支付宝**/
		ZFB(2),
		/**微信**/
		WEIXIN(3);
		private final Integer value;

		WithdrawTypes(Integer value){
			this.value = value;
		}
	}


	@Getter
	public enum AccountMethod{
		/**账户余额**/
		BALANCE(1),
		/**佣金余额**/
		MISSION(2),
		/**积分**/
		POINT(3),
		/**发布点**/
		PUBLISH_POINT(4);

		private final Integer value;

		AccountMethod(Integer value){
			this.value = value;
		}
	}

	@Getter
	public enum MemberAccountTypes{
		/**
		 * 余额账户枚举类型
		 */
		ACCOUNT_BLOCK("拉黑费用", 1),
		ACCOUNT_RECHARGE("充值", 2),
		ACCOUNT_FIRST_SELLER("首次审核店铺奖励", 3),
		ACCOUNT_FIRST_BUYER("首次审核买号奖励", 4),
		ACCOUNT_REALNAME_FEE("实名认证超次数扣费", 5),
		ACCOUNT_BANK_FEE("银行卡认证超次数扣费", 6),
		ACCOUNT_TRANSFER_BALANCE("兑换余额", 7),
		ACCOUNT_WITHDRAW_FREEZE("提现冻结", 8),
		ACCOUNT_WITHDRAW_UNFREEZE("提现解冻", 9),
		ACCOUNT_WITHDRAW_REDUCE("提现成功扣除", 10),
		ACCOUNT_BUY_PUBLISH_POINT("购买发布点", 11),
		ACCOUNT_QUIT_SHANGBAO("退出商保入账", 12),
		ACCOUNT_BUY_SHANGBAO("购买商保", 13),
		ACCOUNT_PUBLISH_TASK("发布任务扣费", 14),
		ACCOUNT_DELETE_TASK("删除任务退费", 15),
		ACCOUNT_BACK_PRINCIPAL("做任务返还本金", 16),
		ACCOUNT_ADMIN_IN("管理员手动增加", 17),
		ACCOUNT_ADMIN_OUT("管理员手动减少", 18),
		ACCOUNT_WUCHA_IN("金额误差退给店铺", 19),
		ACCOUNT_WUCHA_OUT("店铺补交金额误差", 20),
		ACCOUNT_VALIDATE_ACCOUNT_OUT("照妖镜支出", 21),
		ACCOUNT_WANGWANG_FOOT("旺旺足迹打标支出", 22),
		ACCOUNT_BUY_GIFT("购买礼品代发", 23),
		ACCOUNT_TASK_MISSION("任务佣金", 24),
		ACCOUNT_ADD_TASK_COMMENT("添加评价扣费", 25),
		ACCOUNT_ADD_TASK_ZHUI_COMMENT("添加追评扣费", 26),
		ACCOUNT_SPREAD_PUBLISH_TASK("推荐发布任务奖励", 27),
		ACCOUNT_AGENT_SPREAD("代理奖励", 28),
		ACCOUNT_TASK_REWARD("任务额外奖励", 29),
		ACCOUNT_GIFT_FAIL("礼品代发失败退回金额", 30),
		ACCOUNT_TRANSFER_OUT("本金转出", 31),
		ACCOUNT_TRANSFER_IN("本金转入", 32),
		ACCOUNT_TRANSFER_REBATE("商返改平台返款", 33),
		ACCOUNT_DELETE_TASK_FINE("删除任务退费罚款", 34),
		ACCOUNT_SHOP_BACK_AMOUNT("商家自行返款", 35),
		ACCOUNT_DELETE_TASK_BACK_WUCHA("删除任务退误差", 36),
		ACCOUNT_DELETE_TASK_ADD_WUCHA("删除任务补误差", 37);

		private final String name;
		private final Integer value;

		MemberAccountTypes(String name, Integer value){
			this.name = name;
			this.value = value;
		}
	}

	@Getter
	public enum MemberMissionTypes{
		/**
		 * 发布点账户枚举类型
		 */
		ACCOUNT_BUY_PUBLISH_POINT("购买发布点", 1),
		ACCOUNT_TRANSFER_BALANCE("兑换余额", 2),
		ACCOUNT_PUBLISH_TASK("发布任务扣费", 3),
		ACCOUNT_DELETE_TASK("删除任务退费", 4),
		ACCOUNT_TASK_MISSION("任务佣金", 5),
		ACCOUNT_ADMIN_IN("管理员手动增加", 6),
		ACCOUNT_ADMIN_OUT("管理员手动减少", 7),
		ACCOUNT_ADD_TASK_COMMENT("添加评价扣费", 8),
		ACCOUNT_ADD_TASK_ZHUI_COMMENT("添加追评扣费", 9),
		ACCOUNT_TASK_REWARD("任务额外奖励", 10);

		private final String name;
		private final Integer value;

		MemberMissionTypes(String name, Integer value){
			this.name = name;
			this.value = value;
		}
	}

	@Getter
	public enum MemberSpmissionTypes{
		/**
		 * 推广佣金账户枚举类型
		 */
		ACCOUNT_FIRST_SELLER("首次审核店铺奖励", 1),
		ACCOUNT_FIRST_BUYER("首次审核买号奖励", 2),
		ACCOUNT_FINISH_TASK("推荐完成任务奖励", 3),
		ACCOUNT_PUBLISH_TASK("推荐发布任务奖励", 4),
		ACCOUNT_AGENT_SPREAD("代理奖励", 5),
		ACCOUNT_TASK_MISSION("任务佣金", 6),
		ACCOUNT_TRANSFER_BALANCE("兑换余额", 7),
		ACCOUNT_WITHDRAW_FREEZE("提现冻结", 8),
		ACCOUNT_WITHDRAW_UNFREEZE("提现解冻", 9),
		ACCOUNT_WITHDRAW_REDUCE("提现成功扣除", 10),
		ACCOUNT_ADMIN_IN("管理员手动增加", 11),
		ACCOUNT_ADMIN_OUT("管理员手动减少", 12),
		ACCOUNT_TASK_REWARD("任务额外奖励", 13),
		ACCOUNT_DELETE_TASK_FINE("删除任务退费罚款", 14),
		ACCOUNT_COMMENT_REWARD("评价或追评奖励补发", 15),
		ACCOUNT_DASHIFU_REWARD("大师傅奖励", 16);

		private final String name;
		private final Integer value;

		MemberSpmissionTypes(String name, Integer value){
			this.name = name;
			this.value = value;
		}
	}

	@Getter
	public enum MemberPointTypes{
		/**
		 * 积分账户枚举类型
		 */
		ACCOUNT_SIGN("签到", 1),
		ACCOUNT_ADMIN_IN("管理员手动增加", 2),
		ACCOUNT_ADMIN_OUT("管理员手动减少", 3),
		ACCOUNT_FINISH_TASK("完成任务", 4);

		private final String name;
		private final Integer value;

		MemberPointTypes(String name, Integer value){
			this.name = name;
			this.value = value;
		}
	}

	@Getter
	public enum StationAccountTypes{
		/**
		 * 余额账户枚举类型
		 */
		ACCOUNT_MISSION("佣金提成", 1),
		ACCOUNT_ADMIN_IN("管理员手动增加", 2),
		ACCOUNT_ADMIN_OUT("管理员手动减少", 3),
		ACCOUNT_WITHDRAW_FREEZE("提现冻结", 8),
		ACCOUNT_WITHDRAW_UNFREEZE("提现解冻", 9),
		ACCOUNT_WITHDRAW_REDUCE("提现成功扣除", 10);

		private final String name;
		private final Integer value;

		StationAccountTypes(String name, Integer value){
			this.name = name;
			this.value = value;
		}
	}

	@Getter
	public enum AgentAccountTypes{
		/**
		 * 余额账户枚举类型
		 */
		ACCOUNT_MISSION("佣金提成", 1),
		ACCOUNT_ADMIN_IN("管理员手动增加", 2),
		ACCOUNT_ADMIN_OUT("管理员手动减少", 3),
		ACCOUNT_WITHDRAW_FREEZE("提现冻结", 8),
		ACCOUNT_WITHDRAW_UNFREEZE("提现解冻", 9),
		ACCOUNT_WITHDRAW_REDUCE("提现成功扣除", 10);

		private final String name;
		private final Integer value;

		AgentAccountTypes(String name, Integer value){
			this.name = name;
			this.value = value;
		}
	}

}
