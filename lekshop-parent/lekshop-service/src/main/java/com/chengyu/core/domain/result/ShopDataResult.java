package com.chengyu.core.domain.result;

import com.chengyu.core.model.UmsShop;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShopDataResult extends UmsShop {

    @ApiModelProperty(value = "会员数")
    private Long memberNum;

    @ApiModelProperty(value = "商品总件数")
    private Integer goodsNum;

    @ApiModelProperty(value = "商品SKU数")
    private Long goodsSkuNum;

    @ApiModelProperty(value = "成交总额")
    private BigDecimal businessAmount;

    @ApiModelProperty(value = "总订单笔数")
    private Integer orderNum;


}
