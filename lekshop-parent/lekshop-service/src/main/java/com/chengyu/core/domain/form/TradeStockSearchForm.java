package com.chengyu.core.domain.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeStockSearchForm {

	private Integer storageId;

	private Integer goodsId;

	private Integer cateId;

	private String cateName;

	private String goodsName;

	//以下为入库单查询参数
	private String orderNo;

	private String buyOrderNo;

	private String supplier;

	//以下为修改库存查询参数
	private Integer status;
}