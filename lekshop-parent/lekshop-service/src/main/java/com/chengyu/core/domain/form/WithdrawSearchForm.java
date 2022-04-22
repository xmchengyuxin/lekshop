package com.chengyu.core.domain.form;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithdrawSearchForm{
	
	private Integer agentId;
	
	private String agentName;

	private Integer memberId;

	private List<Integer> memberIdList;

	private Integer subId;

	private String subName;
	
	private Integer type;

	private Integer method;
	
	private String orderNo;
	
	private String memberName;

	private String tjrMemberName;
	
	private String status;
	
	private Date dateFrom;
	
	private Date dateTo;
	
	private List<String> statusList;
	
	private String bankAccount;

	private Integer staffId;

	private List<Integer> idList;
}