package com.chengyu.core.domain.enums;

import lombok.Getter;

/**
 * @title  任务枚举
 * @author LeGreen
 * @date   2021/1/11
 */
public class TaskEnums {

	/**
	 * 货比方式枚举
	 */
	@Getter
	public enum GoodsCompareType{
		/**自由货比**/
		FREE_COMPARE(1),
		/**搜图货比**/
		SEARCH_IMG(2),
		/**找相似货比**/
		SEARCH_LIKE(3),
		/**独立关键词货比**/
		LONELY_KEYWORD(4),
		/**搜店铺货比**/
		SEARCH_SHOP(5);

		private final int value;

		GoodsCompareType(int value){
			this.value = value;
		}
	}

	/**
	 * 返款方式枚举
	 */
	@Getter
	public enum RebatesType{
		/**平台担保押款（收货后返本佣）**/
		PLATFORM_AFTER_RECEIVE(1),
		/**平台担保押款（下单后立返本金）**/
		PLATFORM_BEFORE_RECEIVE(2),
		/**商家自行返款（收货后返本佣)**/
		SHOP_AFTER_RECEIVE(3),
		/**商家自行返款（下单后立返本金）**/
		SHOP_BEFORE_RECEIVE(4),
		/**商家先款（商家先转账给买手）**/
		SHOP_FIRST(5);

		private final int value;

		RebatesType(int value){
			this.value = value;
		}
	}

	/**
	 * 垫付任务状态枚举
	 */
	@Getter
	public enum AdvancesStatus{
		/**状态>>-1待支付>>0待审核>>1等待接手>>2等待支付>>3待发货/待返款>>4待收货>>5已收货评价>>6已完成>>7已取消>>8已锁定>>9已下架**/
		STATUS_WAIT_PAY(-1),
		STATUS_WAIT_VERIFY(0),
		STATUS_WAIT_BUYER(1),
		STATUS_WAIT_BUYER_PAY(2),
		STATUS_WAIT_DELIVERY(3),
		STATUS_WAIT_RECEIVE(4),
		STATUS_WAIT_COMMENT(5),
		STATUS_FINISH(6),
		STATUS_CANCEL(7),
		STATUS_LOCK(8),
		STATUS_XIAJIA(9),
		STATUS_REJECT(10),
		STATUS_WAIT_ADD_COMMENT(11);

		private final int value;

		AdvancesStatus(int value){
			this.value = value;
		}
	}

	/**
	 * 流量任务状态枚举
	 */
	@Getter
	public enum TrafficStatus{
		/**状态>>0待支付>>1进行中>>2已到期>>3已完成>>4已锁定>>5已下架**/
		STATUS_WAIT_PAY(0),
		STATUS_ING(1),
		STATUS_EXPIRED(2),
		STATUS_FINISH(3),
		STATUS_LOCK(4),
		STATUS_XIAJIA(5);

		private final int value;

		TrafficStatus(int value){
			this.value = value;
		}
	}

	/**
	 * 流量任务买家状态枚举
	 */
	@Getter
	public enum TrafficBuyerStatus{
		/**状态>>0待做单>>1待审核>>2已完成>>3已关闭**/
		STATUS_WAIT_DO(0),
		STATUS_WAIT_VERIFY(1),
		STATUS_FINISH(2),
		STATUS_CLOSE(3);

		private final int value;

		TrafficBuyerStatus(int value){
			this.value = value;
		}
	}

	@Getter
	public enum OrderLimit{
		/**派单限制>>0无限制>>1仅派浏览>>2仅派垫付单>>3垫付限金额>>4禁止派单**/
		NO_LIMIT(0),
		ONLY_TRAFFIC(1),
		ONLY_ADVANCES(2),
		ADVANCES_LIMIT_AMOUNT(3),
		LIMIT_ORDER(4);

		private final int value;

		OrderLimit(int value){
			this.value = value;
		}
	}


	@Getter
	public enum TaskEvidence{
		/**任务凭证**/
		COLLECT_SHOP_GOODS("collect_shop_goods", "宝贝收藏凭证"),
		COLLECT_SHOP("collect_shop", "店铺收藏凭证"),
		GOODS_COMPARE("goods_compare", "货比3家的足迹凭证"),
		SEARCH_KEYWORD("search_keyword", "搜索关键词凭证"),
		VIEW_OTHER_GOODS("view_ohther_goods", "店内浏览商品凭证"),
		STAY_PAGES("stay_pages", "购买前页面停留凭证"),
		VIEW_DOWN("view_down", "完整浏览到底部凭证"),
		VIEW_SEARCH_QA("view_search_qa", "浏览找问题答案凭证"),
		GOODS_COMMENT_IMG("goods_comment_img", "好评晒图凭证"),
		GOODS_COMMENT_VIDEO("goods_comment_video", "好评晒视频凭证"),
		ADD_GOODS_COMMENT("add_goods_comment", "追加好评凭证"),
		AFTER_GOODS_COMMENT_DEL("after_goods_comment_del", "好评后删除订单凭证"),
		BUY_OHTER_GOODS("buy_ohter_goods", "拍下竞争对手商品凭证"),
		ADD_CAR("add_car", "加购物车凭证"),
		ASK("ask", "问大家凭证"),
		SHARE_GOODS("share_goods", "分享宝贝凭证"),
		NEED_WW_CHAT("need_ww_chat", "拍前需要旺旺聊天凭证"),
		COLLECT_LIVER("collect_liver", "关注主播"),
		COLLECT_VIDEO("collect_video", "点赞视频"),
		REFUND("refund", "退款凭证");

		private final String nid;
		private final String name;

		TaskEvidence(String nid, String name){
			this.nid = nid;
			this.name = name;
		}
	}

	@Getter
	public enum RejectStatus{
		/**状态>>0未驳回>>1商家提交驳回>>2支付驳回>>3发货驳回**/
		STATUS_UN_REJECT(0),
		STATUS_SHOP_REJECT(1),
		STATUS_PAY_REJECT(2),
		STATUS_DELIVERY_REJECT(3);

		private final int value;

		RejectStatus(int value){
			this.value = value;
		}
	}

	public static String getTaskEvidenceNameByNid(String nid){
		for(TaskEvidence te : TaskEvidence.values()){
			if(te.getNid().equals(nid)){
				return te.getName();
			}
		}
		return null;
	}
}
