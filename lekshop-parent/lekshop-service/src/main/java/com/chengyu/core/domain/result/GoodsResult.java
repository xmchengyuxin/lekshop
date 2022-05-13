package com.chengyu.core.domain.result;

import com.chengyu.core.model.PmsGoods;
import com.chengyu.core.model.PmsGoodsCate;
import com.chengyu.core.model.PmsGoodsGroup;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class GoodsResult implements Serializable{

	private PmsGoods goods;

	private List<PmsGoodsGroup> goodsGroupList;

}
