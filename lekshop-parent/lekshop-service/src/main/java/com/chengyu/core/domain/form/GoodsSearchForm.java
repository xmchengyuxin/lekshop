package com.chengyu.core.domain.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsSearchForm {

	private Integer shopId;

	private Integer catePid;

	private Integer cateTid;

	private Integer cateId;
	
	private Integer shopCateId;
	
	private String title;

	private Integer type;

	private String sort;

	private Integer status;

}