package com.chengyu.core.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @title  报表查询表单
 * @author LeGreen
 * @date   2021/1/8
 */
@Getter
@Setter
public class ReportSearchForm {

    @ApiModelProperty(value = "用户")
    private Integer memberId;

    @ApiModelProperty(value = "用户")
    private String memberName;

    @ApiModelProperty(value = "开始时间")
    private Date dateFrom;

    @ApiModelProperty(value = "结束时间")
    private Date dateTo;

    @ApiModelProperty(value = "分站ID")
    private Integer subId;

    @ApiModelProperty(value = "分站名称")
    private String subName;

    @ApiModelProperty(value = "代理ID")
    private Integer agentId;

    @ApiModelProperty(value = "代理名称")
    private String agentName;

    @ApiModelProperty(value = "店铺ID")
    private Integer shopId;

    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "仅查询有数据")
    private boolean onlyNum;
}