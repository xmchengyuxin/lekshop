package com.chengyu.core.domain.result;

import com.chengyu.core.model.OmsOrder;
import com.chengyu.core.model.OmsOrderDetail;
import com.chengyu.core.model.OmsOrderFreight;
import com.chengyu.core.model.OmsOrderFreightDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderFreightResult {

	private OmsOrderFreight orderFreight;
	
	private List<OmsOrderFreightDetail> freightDetailList;
}
