package com.chengyu.core.domain.result;

import com.chengyu.core.model.UmsShopCate;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ShopCateResult extends UmsShopCate implements Serializable{

	private List<ShopCateResult> children;
}
