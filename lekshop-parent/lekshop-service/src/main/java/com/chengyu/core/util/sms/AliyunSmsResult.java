package com.chengyu.core.util.sms;

public class AliyunSmsResult{
	
	private String Message;
	
	private String Code;
	
	private String RequestId;
	
	private String BizId;

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getRequestId() {
		return RequestId;
	}

	public void setRequestId(String requestId) {
		RequestId = requestId;
	}

	public String getBizId() {
		return BizId;
	}

	public void setBizId(String bizId) {
		BizId = bizId;
	}
	
}