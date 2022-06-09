package com.chengyu.core.service.pay.manager;

import com.chengyu.core.domain.form.PayBaseForm;
import com.chengyu.core.domain.form.RefundBaseForm;
import com.chengyu.core.model.UmsMember;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @title  支付工厂
 * @author LeGreen
 * @date   2022/6/9
 */
@Component
public abstract class PayFactory {

	/**
	 * 第三方支付
	 * @author LeGreen
	 * @date   2022/6/9
	 * @param  orderNo
	 * @param  amount
	 * @param  payBaseForm
	 * @return String
	 * @throws Exception 业务异常
	 */
	public abstract String pay(UmsMember member, String orderNo, BigDecimal amount, PayBaseForm payBaseForm) throws Exception;

	/**
	 * 第三方退款
	 * @author LeGreen
	 * @date   2022/6/9
	 * @param  refundBaseForm
	 * @throws Exception 业务异常
	 */
	public abstract String refund(RefundBaseForm refundBaseForm) throws Exception;
}
