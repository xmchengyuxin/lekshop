package com.chengyu.core.domain.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WithdrawCountResult {

	@ApiModelProperty(value = "提款金额")
	private BigDecimal amount;
	@ApiModelProperty(value = "实际到账金额")
	private BigDecimal totalAmount;
	@ApiModelProperty(value = "手续费")
	private BigDecimal fee;
}
