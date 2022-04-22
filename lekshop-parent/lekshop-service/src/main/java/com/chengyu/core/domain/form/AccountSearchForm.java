package com.chengyu.core.domain.form;

import cn.hutool.core.date.DateUtil;
import com.chengyu.core.exception.ServiceException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @title  账户资金查询表单
 * @author LeGreen
 * @date   2021/1/7
 */
@Getter
@Setter
public class AccountSearchForm {

    @ApiModelProperty(value = "代理")
    private Integer agentId;

    @ApiModelProperty(value = "分站")
    private Integer subId;

    @ApiModelProperty(value = "代理")
    private String agentName;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "用户")
    private Integer memberId;

    @ApiModelProperty(value = "用户")
    private Integer devoteId;

    @ApiModelProperty(value = "用户")
    private String memberName;

    @ApiModelProperty(value = "开始时间")
    private Date dateFrom;

    @ApiModelProperty(value = "结束时间")
    private Date dateTo;

    @ApiModelProperty(value = "收支类型 1收入 -1支出")
    private Integer inOut;

    @ApiModelProperty(value = "资金类型")
    private Integer type;

    @ApiModelProperty(value = "资金类型集合")
    private List<Integer> typeList;

    @ApiModelProperty(value = "资金类型集合")
    private String types;

    public void validateDayRange() throws ServiceException {
        if (dateFrom == null) {
            throw new ServiceException("请选择开始日期");
        }
        if (dateTo == null) {
            throw new ServiceException("请选择结束日期");
        }
        if (dateFrom.after(dateTo)) {
            throw new ServiceException("结束日期不能早于开始日期");
        }
        if (DateUtil.betweenDay(dateFrom, dateTo, true) > 31) {
            throw new ServiceException("查询日期跨度不能超过31天");
        }
    }
}