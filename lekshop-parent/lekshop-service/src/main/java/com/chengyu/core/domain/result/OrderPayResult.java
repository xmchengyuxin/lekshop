package com.chengyu.core.domain.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderPayResult {

	@ApiModelProperty(value = "订单号")
	private List<String> orderNoList;

	@ApiModelProperty(value = "交易单号")
	private String payOrderNo;

	@ApiModelProperty(value = "支付金额")
	private BigDecimal amount;
}
