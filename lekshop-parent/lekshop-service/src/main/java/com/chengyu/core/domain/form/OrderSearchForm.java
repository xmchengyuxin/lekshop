package com.chengyu.core.domain.form;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderSearchForm {
	
	private Integer memberId;
	
	private String memberName;
	
	private String orderNo;

	private String verifyCode;
	
	private Integer status;
	
	private Date dateFrom;
	
	private Date dateTo;
	
	private List<Integer> statusList;
	
	private String sort;
	
	private Integer shopId;

	private Integer type;

	private Integer commentStatus;

	private List<Integer> orderIdList;

	private Integer mergeStatus;

	private Integer salesId;


}