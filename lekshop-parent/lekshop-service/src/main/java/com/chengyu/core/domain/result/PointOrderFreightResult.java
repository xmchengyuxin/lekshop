package com.chengyu.core.domain.result;

import com.chengyu.core.model.PointOrderFreight;
import com.chengyu.core.model.PointOrderFreightDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PointOrderFreightResult {

	private PointOrderFreight orderFreight;
	
	private List<PointOrderFreightDetail> freightDetailList;
}
