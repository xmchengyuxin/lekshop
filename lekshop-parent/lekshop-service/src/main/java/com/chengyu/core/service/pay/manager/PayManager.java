package com.chengyu.core.service.pay.manager;

import com.chengyu.core.domain.enums.PayEnum;
import com.chengyu.core.service.pay.logic.AliPayLogic;
import com.chengyu.core.service.pay.logic.BalancePayLogic;
import com.chengyu.core.service.pay.logic.WeixinPayLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @title  支付管理器
 * @author LeGreen
 * @date   2022/6/9
 */
@Component
public class PayManager {
	
	@Autowired
	private WeixinPayLogic weixinPayLogic;
	@Autowired
	private AliPayLogic aliPayLogic;
	@Autowired
	private BalancePayLogic balancePayLogic;

	public PayFactory getPayFactory(String mode) {
		if(PayEnum.PayMethod.ALI_PAY.getValue().equals(mode)){
			return aliPayLogic;
		}else if(PayEnum.PayMethod.WX_PAY.getValue().equals(mode)){
			return weixinPayLogic;
		}else if(PayEnum.PayMethod.BALANCE.getValue().equals(mode)){
			return balancePayLogic;
		}
		return null;
	}

}
