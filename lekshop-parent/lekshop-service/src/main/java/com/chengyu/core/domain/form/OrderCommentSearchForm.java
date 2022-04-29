package com.chengyu.core.domain.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCommentSearchForm {
	
	private Integer goodsId;
	
	private Integer detailId;
	
	private Integer memberId;
	
	private Integer status;

	private Integer shopId;

}