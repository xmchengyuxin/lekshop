package com.chengyu.core.controller.callback.manager;

import com.chengyu.core.controller.callback.logic.OrderLogic;
import com.chengyu.core.controller.callback.logic.RechargeLogic;
import com.chengyu.core.controller.callback.logic.RefundLogic;
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
	public static final String CALLBACK_RECHARGE = "recharge";
	public static final String CALLBACK_REFUND = "refund";

	@Autowired
	private OrderLogic orderLogic;
	@Autowired
	private RechargeLogic rechargeLogic;
	@Autowired
	private RefundLogic refundLogic;

	public CallbackFactory getPaySusFactory(String mode) {
		switch (mode) {
		case CALLBACK_ORDER:
			return orderLogic;
		case CALLBACK_RECHARGE:
			return rechargeLogic;
		case CALLBACK_REFUND:
			return refundLogic;
		default:
			return null;
		}
	}

}
