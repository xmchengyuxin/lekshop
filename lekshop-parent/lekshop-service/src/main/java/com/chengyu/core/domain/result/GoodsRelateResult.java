package com.chengyu.core.domain.result;

import com.chengyu.core.model.PmsGoods;
import com.chengyu.core.model.PmsGoodsRelate;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class GoodsRelateResult implements Serializable{

	private PmsGoodsRelate relate;

	private List<PmsGoods> goodsList;
}
