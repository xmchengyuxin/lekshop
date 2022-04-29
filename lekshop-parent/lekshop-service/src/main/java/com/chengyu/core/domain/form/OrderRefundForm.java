package com.chengyu.core.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderRefundForm {

	@ApiModelProperty(value = "详情ID")
	private Integer orderDetailId;

	@ApiModelProperty(value = "1仅退款 2退货退款")
	private Integer refundInd;
	
	@ApiModelProperty(value = "退款金额")
	private BigDecimal refundAmount;

	@ApiModelProperty(value = "原因")
	private String reason;

	@ApiModelProperty(value = "凭证")
	private String img;
}
