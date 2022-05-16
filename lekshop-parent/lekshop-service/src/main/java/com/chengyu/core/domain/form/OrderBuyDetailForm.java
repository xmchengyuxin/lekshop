package com.chengyu.core.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderBuyDetailForm {

	@ApiModelProperty(value = "店铺ID")
	private Integer shopId;
	@ApiModelProperty(value = "商品skuId")
	private Integer skuId;
	@ApiModelProperty(value = "购买数量")
	private Integer num;
}
