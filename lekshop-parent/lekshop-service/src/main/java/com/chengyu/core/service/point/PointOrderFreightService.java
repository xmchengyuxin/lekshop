package com.chengyu.core.service.point;

import com.chengyu.core.domain.result.PointOrderFreightResult;
import com.chengyu.core.model.PointOrder;

public interface PointOrderFreightService {

	/**
	 * 发货时生成首条物流信息
	 * @author LeGreen
	 * @date   2022/5/5
	 * @param  order
	 */
	void initOrderFreight(PointOrder order);

	/**
	 * 获取物流详情
	 * @author LeGreen
	 * @date   2022/5/11
	 * @param  orderId
	 * @return PointOrderFreightResult
	 */
	PointOrderFreightResult getOrderFreight(Integer orderId);
}