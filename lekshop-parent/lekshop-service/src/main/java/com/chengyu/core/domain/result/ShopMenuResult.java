package com.chengyu.core.domain.result;

import com.chengyu.core.model.OrgMenu;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ShopMenuResult extends OrgMenu implements Serializable{

	private static final long serialVersionUID = 754476682411611363L;

	private String parentName;

	private List<ShopMenuResult> children;
}
