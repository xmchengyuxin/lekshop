package com.chengyu.core.domain.result;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @title  后台首页统计参数
 * @author LeGreen
 * @date   2021/1/28
 */
@Getter
@Setter
public class AdminIndexCountResult {

    private String name;

    private Integer num;

    private BigDecimal amount;

    private String router;

    private String tips;

    @Getter
    public enum IndexCountEnums{
        /**
         * 后台首页统计参数
         * 待处理提现, 待付款， 待发货， 待收货， 售后订单， 总订单数，总订单金额， 商品总访问数，在售SKU总量
         */
        WITHDRAW_VERIFY("withdrawVerifyNum","待处理提现", "/fund/withdraw"),
        WAIT_PAY("waitPayNum","待付款", "/order/list"),
        WAIT_DELIVERY("waitDeliveryNum","待发货", "/order/list"),
        WAIT_RECEIVE("waitReceiveNum","待收货", "/order/list"),
        REFUND_ORDER("refundNum","售后订单", "/order/refund"),
        TOTAL_ORDER("totalOrderNum","总订单数", "/order/list"),
        ORDER_AMOUNT("orderAmount","总订单金额", "/order/list"),
        GOODS_VIEW_NUM("goodsViewNum","商品总访问数", "/goods/list"),
        SKU_NUM("skuNum","在售SKU总量", "/goods/list");

        private final String key;
        private final String name;
        private final String router;

        IndexCountEnums(String key, String name, String router){
            this.key = key;
            this.name = name;
            this.router = router;
        }
    }

    @Getter
    public enum IndexAmountEnums{
        /**
         * 后台首页统计参数
         */
        TODAY_WITHDRAW_AMOUNT("todayWithdrawAmount", "今日审核提现", "已提现"),
        /*YESTODAY_WITHDRAW_AMOUNT("yestodayWithdrawAmount", "昨日审核提现", "已提现"),*/
        RECHARGE_AMOUNT("rechargeAmount", "充值总额", "已充值"),
        WITHDRAW_AMOUNT("withdrawAmount", "提现总额", "已提现"),
        TOTAL_BALANCE("totalBalance","总余额", "未提现");
        /*TOTAL_MISSION("totalMission","总佣金", "未提现"),
        TOTAL_SPMISSION("totalSpmission","总推广佣金", "未提现");*/

        private final String key;
        private final String name;
        private final String tips;

        IndexAmountEnums(String key, String name, String tips){
            this.key = key;
            this.name = name;
            this.tips = tips;
        }
    }
}
