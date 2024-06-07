package com.chengyu.core.domain.result;

import com.chengyu.core.model.PmsGoodsSku;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GoodsStockResult extends PmsGoodsSku {

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品名称")
    private String cateName;

}