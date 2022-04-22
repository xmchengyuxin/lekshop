package com.chengyu.core.domain.form;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RechargeSearchForm{
	
	private String orderNo;
	
	private Integer memberId;
	
	private String memberName;
	
	private String status;
	
	private List<String> statusList;
	
	private Date dateFrom;
	
	private Date dateTo;
	
	private String statusArr;
}