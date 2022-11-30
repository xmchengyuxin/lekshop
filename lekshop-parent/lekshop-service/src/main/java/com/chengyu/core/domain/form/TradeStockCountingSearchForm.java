package com.chengyu.core.domain.form;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TradeStockCountingSearchForm {

	private Integer accountId;

	private Date dateFrom;

	private Date dateTo;

	private Integer status;
}