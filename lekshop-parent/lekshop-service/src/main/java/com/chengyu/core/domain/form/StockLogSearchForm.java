package com.chengyu.core.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @title  查询表单
 * @author LeGreen
 * @date   2021/1/7
 */
@Getter
@Setter
public class StockLogSearchForm {

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;

    @ApiModelProperty(value = "SKUID")
    private Integer skuId;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "开始时间")
    private Date dateFrom;

    @ApiModelProperty(value = "结束时间")
    private Date dateTo;

    @ApiModelProperty(value = "收支类型 1收入 -1支出")
    private Integer inOut;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "类型集合")
    private List<Integer> typeList;

    @ApiModelProperty(value = "类型集合")
    private String types;

    @ApiModelProperty(value = "货位名称")
    private String spaceName;

    @ApiModelProperty(value = "仓库ID")
    private Integer storageId;

}