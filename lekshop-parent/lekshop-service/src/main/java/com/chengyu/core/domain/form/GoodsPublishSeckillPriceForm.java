package com.chengyu.core.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GoodsPublishSeckillPriceForm {

	@ApiModelProperty(value = "SKUID")
	private Integer skuId;

	@ApiModelProperty(value = "秒杀价格")
	private BigDecimal price;
}