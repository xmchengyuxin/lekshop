package com.chengyu.core.domain.result;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberAccountCountResult {

	@ApiModelProperty(value = "预计收入")
	private BigDecimal planIncome;
	
	@ApiModelProperty(value = "预计支出")
	private BigDecimal planOutcome;
}
