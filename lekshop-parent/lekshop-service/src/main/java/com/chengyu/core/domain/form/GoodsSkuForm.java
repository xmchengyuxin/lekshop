package com.chengyu.core.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class GoodsSkuForm {

	private String goodsAttrKey;

	private List<String> goodsAttrValList;



}