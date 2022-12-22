package com.chengyu.core.service.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum RedisDelayQueueEnum {

    ORDER_AUTO_CANCEL_JOB("ORDER_AUTO_CANCEL_JOB","订单超时未支付自动取消", "orderAutoCancelJob"),
    ORDER_AUTO_COMMENT_JOB("ORDER_AUTO_COMMENT_JOB","自动好评", "orderAutoCommentJob"),
    ORDER_AUTO_FINISH_JOB("ORDER_AUTO_FINISH_JOB","订单自动确认收货", "orderAutoFinishJob"),
    ORDER_GROUP_AUTO_CANCEL_JOB("ORDER_GROUP_AUTO_CANCEL_JOB","拼团失败自动取消", "orderGroupAutoCancelJob"),
    REFUND_AUTO_AGREE_JOB("REFUND_AUTO_AGREE_JOB","售后申请超时未处理自动同意", "refundAutoAgreeJob"),
    REFUND_AUTO_CANCEL_JOB("REFUND_AUTO_CANCEL_JOB","买家超时未寄货自动取消售后申请", "refundAutoCancelJob"),
    REFUND_AUTO_CONFIRM_JOB("REFUND_AUTO_CONFIRM_JOB","卖家超时未确认收货自动确认", "refundAutoConfirmJob"),
    ROBOT_ANSWER("ROBOT_ANSWER","自动回复", "robotAnswerJob");



    /**
     * 延迟队列 Redis Key
     */
    private String code;

    /**
     * 描述
     */
    private String name;

    /**
     * 延迟队列具体业务实现的 Bean
     * 可通过 Spring 的上下文获取
     */
    private String beanId;
}
