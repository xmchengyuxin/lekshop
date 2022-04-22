package com.chengyu.core.domain.form;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointSearchForm{
	
	private Integer memberId;
	
	private String memberName;
	
	private String type;
	
	private String types;
	
	private List<String> typeList;
	
	private String inOut;
	
	private Date dateFrom;
	
	private Date dateTo;

}