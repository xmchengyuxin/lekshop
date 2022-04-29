package com.chengyu.core.domain.form;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderRefundSearchForm {
	
	private Integer memberId;
	
	private String memberName;

	private Integer shopId;
	
	private String refdundNo;
	
	private Integer status;
	
	private Date dateFrom;
	
	private Date dateTo;
	
	private List<Integer> statusList;
	
	private String sort;
}