package com.chengyu.core.domain.form;

import com.chengyu.core.domain.enums.AccountEnums;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayBaseForm{

	private String payMethod;
	private String applicationType;
	private String ip;
	private String subMchId;
	private String body;
	private String payNotifyUrl;
	private AccountEnums.MemberAccountTypes memberAccountTypes;

	public PayBaseForm(String payMethod, String applicationType, String ip){
		this.payMethod = payMethod;
		this.applicationType = applicationType;
		this.ip = ip;
	}
}
