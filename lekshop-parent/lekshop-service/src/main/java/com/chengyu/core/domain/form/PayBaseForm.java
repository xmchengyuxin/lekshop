package com.chengyu.core.domain.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayBaseForm{

	private String payMethod;
	private String applicationType;
	private String ip;
	private String subMchId;
	
	public PayBaseForm(String payMethod, String applicationType, String ip, String subMchId){
		this.payMethod = payMethod;
		this.applicationType = applicationType;
		this.ip = ip;
		this.subMchId = subMchId;
	}
}
