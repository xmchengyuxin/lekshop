package com.chengyu.core.domain.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class RebuyCountResult implements Serializable{
	
	private static final long serialVersionUID = -874558803489073006L;
	
	@ApiModelProperty(value = "购买总人数")
	private Integer buyManNum;
	@ApiModelProperty(value = "购买2次以上人数")
	private Integer buyTwoMannum;
	@ApiModelProperty(value = "复购销量")
	private Integer rebuyNum;
	@ApiModelProperty(value = "复购率")
	private BigDecimal rebuyRate;
}