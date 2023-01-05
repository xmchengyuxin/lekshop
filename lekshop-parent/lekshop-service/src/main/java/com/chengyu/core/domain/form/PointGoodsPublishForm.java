package com.chengyu.core.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PointGoodsPublishForm {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "商品编号")
    private String goodsCode;

    @ApiModelProperty(value = "一级分类ID")
    private Integer catePid;

    @ApiModelProperty(value = "二级分类ID")
    private Integer cateTid;

    @ApiModelProperty(value = "分类ID")
    private Integer cateId;

    private String cateIds;

    @ApiModelProperty(value = "类目名称")
    private String cateName;

    @ApiModelProperty(value = "商品ID集合")
    private String goodsCateIds;

    @ApiModelProperty(value = "商品标题")
    private String title;

    @ApiModelProperty(value = "产品描述")
    private String description;

    @ApiModelProperty(value = "主图")
    private String mainImg;

    @ApiModelProperty(value = "视频")
    private String mainVideo;

    @ApiModelProperty(value = "商品图片")
    private String goodsImg;

    @ApiModelProperty(value = "兑换方式>>1积分>>2积分+现金")
    private Integer exchangeMethod;

    @ApiModelProperty(value = "原价")
    private BigDecimal oriPrice;

    @ApiModelProperty(value = "兑换积分")
    private BigDecimal point;

    @ApiModelProperty(value = "兑换现金")
    private BigDecimal price;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "权重")
    private Integer weight;

    @ApiModelProperty(value = "1上架>>2仓库>>3删除")
    private Integer status;

    @ApiModelProperty(value = "库存计算方式>>1下单减库存>>2付款减库存")
    private Integer stockType;

    @ApiModelProperty(value = "限购")
    private Integer limitExchange;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "详情")
    private String detail;
}