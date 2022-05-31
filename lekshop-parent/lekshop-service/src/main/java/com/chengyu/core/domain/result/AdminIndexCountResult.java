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
         * 身份证待审核, 待审核银行, 待审核充值, 待审核提现, 待处理申诉, 待审核买号, 待审核店铺, 总任务数量, 已完成任务
         * 淘宝未接数量, 淘宝已完成数量, 淘宝总任务, 京东未接数量, 京东已完成, 京东总任务, 拼多多未接, 拼多多已完成, 拼多多总任务
         */
        IDCARD_VERIFY("idcardVerifyNum", "待审核身份证", "/member/realname"),
        BANK_VERIFY("bankVerifyNum", "待审核银行", "/member/bank"),
        RECHARGE_VERIFY("rechargeVerifyNum","待审核充值", "/fund/recharge"),
        WITHDRAW_VERIFY("withdrawVerifyNum","待审核提现", "/fund/withdraw");

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
         * 充值总额, 提现总额, 所有用户账号总余额, 所有用户账号总发布点, 所有用户账号总佣金, 所有任务总金额, 所有任务服务费
         * 未完成任务发布点共计, 所有任务发布点共计, 所有任务实付金额, 未完成任务实付金额共计, 所有加入商保服务, 未收货评价审核任务金额
         * 未接手和未付款任务金额, 未接手和未付款任务发布点
         */
        TODAY_WITHDRAW_AMOUNT("todayWithdrawAmount", "今日审核提现", "已提现"),
        YESTODAY_WITHDRAW_AMOUNT("yestodayWithdrawAmount", "昨日审核提现", "已提现"),
        RECHARGE_AMOUNT("rechargeAmount", "充值总额", "总收入"),
        WITHDRAW_AMOUNT("withdrawAmount", "提现总额", "已提现"),
        TOTAL_BALANCE("totalBalance","总余额", "未提现"),
        TOTAL_MISSION("totalMission","总佣金", "未提现"),
        TOTAL_SPMISSION("totalSpmission","总推广佣金", "未提现");

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
