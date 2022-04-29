package com.chengyu.core.service.order;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OmsOrderPriceLog;
import com.chengyu.core.model.UmsShop;

import java.math.BigDecimal;
import java.util.List;

public interface OrderPriceLogService {

	/**
	 * 店铺修改价格
	 * @author LeGreen
	 * @date   2022/4/29
	 * @param  shop
	 * @param  orderId
	 * @param  editPrice
	 * @throws ServiceException 业务异常
	 */
	void updateOrderPrice(UmsShop shop, Integer orderId, BigDecimal editPrice) throws ServiceException;

	/**
	 * 订单价格修改记录
	 * @author LeGreen
	 * @date   2022/4/29
	 * @param  orderId
	 * @return List<OmsOrderPriceLog>
	 * @throws ServiceException 业务异常
	 */
	List<OmsOrderPriceLog> getOrderPriceLogByOrderId(Integer orderId);
	
}