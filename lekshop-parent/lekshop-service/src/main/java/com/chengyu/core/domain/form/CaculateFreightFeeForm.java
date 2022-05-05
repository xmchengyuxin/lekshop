package com.chengyu.core.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CaculateFreightFeeForm {

	@ApiModelProperty(value = "运费模板")
	private Integer freightId;
	@ApiModelProperty(value = "购买数量")
	private Integer num;
	@ApiModelProperty(value = "重量")
	private BigDecimal weight;

	public CaculateFreightFeeForm(Integer freightId, Integer num, BigDecimal weight){
		this.freightId = freightId;
		this.num = num;
		this.weight = weight;
	}
}
