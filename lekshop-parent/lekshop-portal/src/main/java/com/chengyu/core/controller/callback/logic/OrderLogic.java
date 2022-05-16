package com.chengyu.core.controller.callback.logic;

import com.chengyu.core.controller.callback.manager.CallbackFactory;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @类描述   	商品订单支付成功
 * @作者   		LeGreen
 * @创建时间  	2020年4月3日
 * @版本   		1.00
 * @修改记录
 * <pre>
 *     版本          修改人    		修改日期 			修改内容描述
 *     -----------------------------------------------------------
 *     1.00   	LeGreen    	2020年4月3日             
 *     -----------------------------------------------------------
 * </pre>
 */
@Service("paySusOrder")
public class OrderLogic extends CallbackFactory {

	@Autowired
	private OrderService orderService;

	@Override
	public void paySus(String orderNo) throws ServiceException {
		orderService.paySus(orderNo);
	}

}
