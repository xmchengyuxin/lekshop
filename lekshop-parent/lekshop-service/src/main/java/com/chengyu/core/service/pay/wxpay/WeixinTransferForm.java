package com.chengyu.core.service.pay.wxpay;

import com.wechat.pay.java.service.transferbatch.model.TransferDetailInput;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeixinTransferForm {
	
	private Integer totalNum;
	
	private long totalAmount;
	
	private String batchName;

	private String outBatchNo;

	private List<TransferDetailInput> transferDetailListList;

}