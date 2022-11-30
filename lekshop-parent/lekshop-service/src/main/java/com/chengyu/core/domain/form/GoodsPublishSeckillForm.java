package com.chengyu.core.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GoodsPublishSeckillForm {

	@ApiModelProperty(value = "主键ID")
	private Integer id;

	@ApiModelProperty(value = "秒杀开始时间")
	private Date seckillBeginTime;

	@ApiModelProperty(value = "秒杀结束时间")
	private Date seckillEndTime;

	@ApiModelProperty(value = "秒杀限购数量")
	private Integer seckillLimitNum;

	@ApiModelProperty(value = "[{skuId:SKUID, price:秒杀价}]")
	private String priceFormList;

}