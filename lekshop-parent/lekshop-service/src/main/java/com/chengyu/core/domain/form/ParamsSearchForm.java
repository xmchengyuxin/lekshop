package com.chengyu.core.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


/**
 * @title  多元化参数表单
 * @author LeGreen
 * @date   2021/1/9
 */
@Getter
@Setter
public class ParamsSearchForm {

    @ApiModelProperty(value = "" +
            "config_account>>系统收款账号[accountType:收款账户类型>>1支付宝>>2微信>>3财付通>>4网银]" +
            "config_black>>黑名单设置" +
            "config_buyseller>>买卖号设置[shopCateId:电商类别ID]" +
            "config_gzh>>公众号设置" +
            "config_invite_code>>邀请码设置/" +
            "config_mission>>佣金设置[shopCateId:电商类别ID]" +
            "config_realname>>实名认证设置" +
            "config_recharge_card>>充值卡设置" +
            "config_shangbao>>商保设置" +
            "config_shop_cate>>电商网站类别" +
            "config_shop_target>>商品标签" +
            "config_task_buy>>接手任务参数设置[shopCateId:电商类别ID,type:1接单任务>>2流量任务]" +
            "config_task_colum>>任务栏目[taskType:1接单任务>>2流量任务]" +
            "config_task_fee>>发布点资费参数设置[shopCateId:电商类别ID,taskType:1接单任务>>2流量任务]" +
            "config_task_reward>>任务额外奖励设置[shopCateId:电商类别ID,taskType:1接单任务>>2流量任务]" +
            "config_task_sell>>发布任务参数设置[shopCateId:电商类别ID,taskType:1接单任务>>2流量任务]" +
            "config_task_timeout>>任务超时设置[shopCateId:电商类别ID,taskType:1接单任务>>2流量任务]" +
            "config_task_warning>>任务安全预警设置[taskType:1接单任务>>2流量任务]" +
            "config_withdraw>>提现设置", required = true)
    @NotNull(message = "请求参数KEY不能为空")
    private String key;

    @ApiModelProperty(value = "收款账户类型>>1支付宝>>2微信>>3财付通>>4网银")
    private Integer accountType;

    @ApiModelProperty(value = "会员组ID")
    private Integer groupId;

    @ApiModelProperty(value = "电商类别ID")
    private Integer shopCateId;

    @ApiModelProperty(value = "佣金组ID")
    private Integer missionConfigId;

    @ApiModelProperty(value = "1接单任务>>2流量任务")
    private Integer taskType;
}
