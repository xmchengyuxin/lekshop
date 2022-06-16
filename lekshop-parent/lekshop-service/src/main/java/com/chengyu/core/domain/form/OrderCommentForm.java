package com.chengyu.core.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderCommentForm {

	@ApiModelProperty(value = "详情ID")
	private Integer detailId;

	@ApiModelProperty(value = "待评价ID")
	private Integer id;

	@ApiModelProperty(value = "是否匿名")
	private Integer anonymousStatus;
	
	@ApiModelProperty(value = "内容")
	private String content;

	@ApiModelProperty(value = "图片")
	private String img;

	@ApiModelProperty(value = "1好评>>2中评>>3差评")
	private Integer goodsComment;

	@ApiModelProperty(value = "描述相符")
	private BigDecimal goodsStarNum;

	@ApiModelProperty(value = "发货速度")
	private BigDecimal deliveryStarNum;

	@ApiModelProperty(value = "服务态度")
	private BigDecimal shopStarNum;

	@ApiModelProperty(value = "物流服务")
	private BigDecimal logisticStarNum;
}
