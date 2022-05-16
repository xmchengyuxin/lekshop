package com.chengyu.core.controller.callback.manager;

import com.chengyu.core.controller.callback.logic.OrderLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @类描述   	支付回调管理器
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
@Component
public class CallbackManager{
	
	public static final String CALLBACK_ORDER = "order";

	@Autowired
	private OrderLogic orderLogic;

	public CallbackFactory getPaySusFactory(String mode) {
		switch (mode) {
		case CALLBACK_ORDER:
			return orderLogic;
		default:
			return null;
		}
	}

}
