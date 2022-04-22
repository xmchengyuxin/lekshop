package com.chengyu.core.domain.form;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MemberSearchForm{

	private Integer memberId;

	private Integer subId;

	private String code;
	
	private String realname;
	
	private String nickname;
	
	private String phone;
	
	private Integer method;
	
	private Integer status;

	private Integer phoneStatus;

	private Integer realnameStatus;

	private Integer bankStatus;

	private Integer videoStatus;

	private Integer shangbaoStatus;

	private Integer gzhFocusStatus;

	private Integer autoTakeOrderStatus;
	
	private Date dateFrom;
	
	private Date dateTo;
	
	private String sort;

	private  Integer tjrId;

	private Integer groupId;

	private Integer agentId;

	private Integer type;

	private String registerIp;

	private List<Integer> orderLimitList;

	private Integer staffId;

	private String qq;

}