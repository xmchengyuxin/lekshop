package com.chengyu.core.domain.form;

import com.chengyu.core.domain.enums.PayEnum;
import com.chengyu.core.model.UmsMember;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RefundBaseForm {

	private String orderNo;
	private String refundNo;
	private BigDecimal orderAmount;
	private BigDecimal refundAmount;
	private String payChannel;
	private String refundNotifyUrl;
	private UmsMember member;
}
