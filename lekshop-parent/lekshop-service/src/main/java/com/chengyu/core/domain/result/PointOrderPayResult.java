package com.chengyu.core.domain.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PointOrderPayResult {

	@ApiModelProperty(value = "订单号")
	private String orderNo;

	@ApiModelProperty(value = "支付金额")
	private BigDecimal amount;

	@ApiModelProperty(value = "支付积分")
	private BigDecimal point;
}
