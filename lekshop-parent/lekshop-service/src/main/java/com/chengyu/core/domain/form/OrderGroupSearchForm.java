package com.chengyu.core.domain.form;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderGroupSearchForm {
	
	private String orderNo;
	
	private Integer memberId;
	
	private Integer shopId;
	
	private String shopName;
	
	private Integer goodsId;
	
	private Integer status;
	
	private Integer payStatus;
	
	private Date dateFrom;
	
	private Date dateTo;
	
	private List<Integer> shopIdArr;
	
}