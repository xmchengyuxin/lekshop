package com.chengyu.core.domain.result;

import com.chengyu.core.model.PmsGoodsSku;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class GoodsSkuResult implements Serializable{

	private List<GoodsAttrKeyResult> attrKeyResultList;

	private List<PmsGoodsSku> skuList;
}
