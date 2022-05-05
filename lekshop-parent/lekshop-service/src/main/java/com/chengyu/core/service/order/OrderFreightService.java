package com.chengyu.core.service.order;

import com.chengyu.core.model.OmsOrder;

public interface OrderFreightService {

	/**
	 * 发货时生成首条物流信息
	 * @author LeGreen
	 * @date   2022/5/5
	 * @param  order
	 */
	void initOrderFreight(OmsOrder order);
}