package com.chengyu.core.domain.form;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BailSearchForm{
	
	private Integer riderId;
	
	private String riderName;
	
	private Date dateFrom;
	
	private Date dateTo;
	
	private String orderNo;
	
	private String status;
	
	private Integer brandId;
}