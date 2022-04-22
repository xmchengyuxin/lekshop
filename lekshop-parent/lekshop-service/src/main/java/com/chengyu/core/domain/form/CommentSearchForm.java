package com.chengyu.core.domain.form;

import cn.hutool.core.date.DateUtil;
import com.chengyu.core.exception.ServiceException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @title  评价查询类
 * @author LeGreen
 * @date   2021/1/27
 */
@Getter
@Setter
public class CommentSearchForm {

    @ApiModelProperty(value = "1来自买家>>2来自商家>>3我给他人的评价")
    private Integer type;

    @ApiModelProperty(value = "用户")
    private Integer memberId;

    @ApiModelProperty(value = "用户")
    private String memberName;

    @ApiModelProperty(value = "用户")
    private String toMemberName;

    @ApiModelProperty(value = "任务编号")
    private String taskNo;

    @ApiModelProperty(value = "开始时间")
    private Date dateFrom;

    @ApiModelProperty(value = "结束时间")
    private Date dateTo;

    @ApiModelProperty(value = "代理")
    private Integer agentId;

    @ApiModelProperty(value = "分站ID")
    private Integer subId;

}