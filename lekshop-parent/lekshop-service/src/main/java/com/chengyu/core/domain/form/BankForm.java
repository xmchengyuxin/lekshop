package com.chengyu.core.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankForm{

	@ApiModelProperty(value = "姓名")
	private String accountName;

	@ApiModelProperty(value = "银行名称")
	private String bank;

	@ApiModelProperty(value = "支行名称")
	private String bankSub;

	@ApiModelProperty(value = "银行卡号")
	private String bankAccount;

	@ApiModelProperty(value = "收款二维码")
	private String qrCode;
}