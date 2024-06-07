package com.chengyu.core.domain.form;

import com.chengyu.core.model.PmsGoods;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GoodsImportForm extends PmsGoods implements Serializable{

	private String oriPrices;

	private String prices;

	private String stocks;

	private String skuNos;

	private String weights;
}
