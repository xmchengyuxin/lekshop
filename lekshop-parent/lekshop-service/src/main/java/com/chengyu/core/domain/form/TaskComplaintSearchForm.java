package com.chengyu.core.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @title  任务申诉查询表单
 * @author LeGreen
 * @date   2021/1/8
 */
@Getter
@Setter
public class TaskComplaintSearchForm {

    @ApiModelProperty(value = "用户")
    private Integer memberId;

    @ApiModelProperty(value = "1接单任务>>2流量任务")
    private Integer taskType;

    @ApiModelProperty(value = "用户")
    private String memberName;

    @ApiModelProperty(value = "用户")
    private Integer toMemberId;

    @ApiModelProperty(value = "用户")
    private String toMemberName;

    @ApiModelProperty(value = "任务编号")
    private String taskNo;

    @ApiModelProperty(value = "受理状态>>0否>>1是")
    private Integer verifyStatus;

    @ApiModelProperty(value = "代理")
    private Integer agentId;

    @ApiModelProperty(value = "分站")
    private Integer subId;

    @ApiModelProperty(value = "员工列表")
    private Integer staffId;

    @ApiModelProperty(value = "时间区间")
    private Date dateFrom;

    @ApiModelProperty(value = "时间区间")
    private Date dateTo;
}