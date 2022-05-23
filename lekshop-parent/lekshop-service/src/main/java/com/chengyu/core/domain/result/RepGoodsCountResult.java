package com.chengyu.core.domain.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class RepGoodsCountResult implements Serializable {

    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "主图/主视频")
    private String mainImg;

    @ApiModelProperty(value = "销售数量")
    private Integer sellNum;

    @ApiModelProperty(value = "销售金额")
    private BigDecimal sellAmount;

    @ApiModelProperty(value = "好评数量")
    private Integer goodCommentNum;

    @ApiModelProperty(value = "中评数量")
    private Integer mediumCommentNum;

    @ApiModelProperty(value = "差评数量")
    private Integer badCommentNum;

    private static final long serialVersionUID = 1L;
}