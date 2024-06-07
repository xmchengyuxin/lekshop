package com.chengyu.core.domain.form;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderMergeSearchForm {

	private String mergeOrderNo;

	private Integer shopId;

	private Integer status;

	private Date dateFrom;

	private Date dateTo;

	private List<Integer> statusList;

	private String statusArr;

	private String sort;
}