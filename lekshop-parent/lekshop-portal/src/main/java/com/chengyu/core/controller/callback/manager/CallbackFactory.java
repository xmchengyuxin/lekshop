package com.chengyu.core.controller.callback.manager;

import com.chengyu.core.exception.ServiceException;
import org.springframework.stereotype.Component;

/**
 * @类描述   	支付回调工厂
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
public abstract class CallbackFactory{
	
	public abstract void paySus(String orderNo) throws ServiceException;

	public abstract void refundSus(String orderNo) throws ServiceException;
}
