package com.chengyu.core.domain.result;

import com.chengyu.core.model.OmsOrderRefund;
import com.chengyu.core.model.OmsOrderRefundLog;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRefundResult {

	private OmsOrderRefund refund;
	
	private List<OmsOrderRefundLog> refundLogList;
}
