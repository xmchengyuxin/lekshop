package com.chengyu.core.domain.result;

import com.chengyu.core.model.PmsGoods;
import com.chengyu.core.model.PmsGoodsGroup;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class GoodsThirdResult implements Serializable{

	private String title;

	private Integer type = 1;

	private String mainImg;

	private String goodsImg;

	private String description;

	private String detail;

	//attrKeyList: [{"attrKey":"颜色","attrValue":"","attrValueList":["黑","白"]},{"attrKey":"尺寸","attrValue":"","attrValueList":["大","小"]}]
	private String attrKeyList;

	//skuList: [["黑","大","","222","999","888","",""],["黑","小","","222","999","888","",""],["白","大","","222","999","888","",""],["白","小","","222","999","888","",""]]
	private String skuList;
}
