package com.chengyu.core.domain.form;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderCommentSearchForm {

	private Integer orderId;

	private Integer goodsId;
	
	private Integer detailId;
	
	private Integer memberId;

	//0待评价>>1已评价>>2已追评>>3已过期
	private Integer status;

	private List<Integer> statusList;

	private Integer shopId;

	/*1好评>>2中评>>3差评>>4有图*/
	private Integer goodsComment;

}