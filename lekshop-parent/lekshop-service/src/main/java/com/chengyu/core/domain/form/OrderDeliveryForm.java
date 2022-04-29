package com.chengyu.core.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDeliveryForm {

	@ApiModelProperty(value = "快递类型")
	private String deliveryType;

	@ApiModelProperty(value = "订单ID")
	private Integer orderId;
	
	@ApiModelProperty(value = "快递单号")
	private String deliveryNo;
}
