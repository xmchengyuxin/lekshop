package com.chengyu.core.domain.result;

import com.chengyu.core.model.PmsGoodsCate;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class GoodsCateResult extends PmsGoodsCate implements Serializable{

//	private boolean hasChildren;

	private Integer topId;
	private List<GoodsCateResult> children;
}
