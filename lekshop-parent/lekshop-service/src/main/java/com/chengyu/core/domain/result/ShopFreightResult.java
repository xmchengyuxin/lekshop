package com.chengyu.core.domain.result;

import com.chengyu.core.model.UmsShopFreightTemplate;
import com.chengyu.core.model.UmsShopFreightTemplateArea;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ShopFreightResult implements Serializable{

	private static final long serialVersionUID = -1180905536460762559L;

	private UmsShopFreightTemplate template;
	
	private List<UmsShopFreightTemplateArea> areaList;

}
