package com.chengyu.core.service.pay.wxpay;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class WeixinRefundResponse {
	
	@XStreamAlias("appid")
	private String appid;
	
	@XStreamAlias("nonce_str")
	private String nonce_str;
	
	@XStreamAlias("sign")
	private String sign;
	
	@XStreamAlias("out_refund_no")
	private String out_refund_no;
	
	@XStreamAlias("refund_id")
	private String refund_id;
	
	@XStreamAlias("refund_fee")
	private String refund_fee;
	
	@XStreamAlias("settlement_refund_fee")
	private String settlement_refund_fee;
	
	@XStreamAlias("settlement_total_fee")
	private String settlement_total_fee;
	
	@XStreamAlias("return_code")
	private String return_code;
	
	@XStreamAlias("return_msg")
	private String return_msg;
	
	@XStreamAlias("result_code")
	private String result_code;
	
	@XStreamAlias("refund_account")
	private String refund_account;
	
	@XStreamAlias("err_code")
	private String err_code;
	
	@XStreamAlias("err_code_des")
	private String err_code_des;
	
	@XStreamAlias("cash_refund_fee")
	private String cash_refund_fee;
	
	@XStreamAlias("mch_id")
	private String mch_id;
	
	@XStreamAlias("coupon_type_$n")
	private String coupon_type_$n;
	
	@XStreamAlias("coupon_refund_fee")
	private String coupon_refund_fee;
	
	@XStreamAlias("coupon_refund_fee_$n")
	private String coupon_refund_fee_$n;
	
	@XStreamAlias("coupon_refund_count")
	private String coupon_refund_count;
	
	@XStreamAlias("coupon_refund_id_$n")
	private String coupon_refund_id_$n;
	
	@XStreamAlias("total_fee")
	private String total_fee;
	
	@XStreamAlias("cash_fee")
	private String cash_fee;
	
	@XStreamAlias("transaction_id")
	private String transaction_id;
	
	@XStreamAlias("out_trade_no")
	private String out_trade_no;
	
	@XStreamAlias("fee_type")
	private String fee_type;
	
	@XStreamAlias("cash_fee_type")
	private String cash_fee_type;
	
	@XStreamAlias("req_info")
	private String req_info;
	
	@XStreamAlias("refund_channel")
	private String refund_channel;
	
	@XStreamAlias("refund_recv_accout")
	private String refund_recv_accout;
	
	@XStreamAlias("refund_request_source")
	private String refund_request_source;
	
	@XStreamAlias("refund_status")
	private String refund_status;
	
	@XStreamAlias("success_time")
	private String success_time;
	
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}

	public String getRefund_id() {
		return refund_id;
	}

	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}

	public String getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(String refund_fee) {
		this.refund_fee = refund_fee;
	}

	public String getSettlement_refund_fee() {
		return settlement_refund_fee;
	}

	public void setSettlement_refund_fee(String settlement_refund_fee) {
		this.settlement_refund_fee = settlement_refund_fee;
	}

	public String getSettlement_total_fee() {
		return settlement_total_fee;
	}

	public void setSettlement_total_fee(String settlement_total_fee) {
		this.settlement_total_fee = settlement_total_fee;
	}

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}

	public String getCash_refund_fee() {
		return cash_refund_fee;
	}

	public void setCash_refund_fee(String cash_refund_fee) {
		this.cash_refund_fee = cash_refund_fee;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getCoupon_type_$n() {
		return coupon_type_$n;
	}

	public void setCoupon_type_$n(String coupon_type_$n) {
		this.coupon_type_$n = coupon_type_$n;
	}

	public String getCoupon_refund_fee() {
		return coupon_refund_fee;
	}

	public void setCoupon_refund_fee(String coupon_refund_fee) {
		this.coupon_refund_fee = coupon_refund_fee;
	}

	public String getCoupon_refund_fee_$n() {
		return coupon_refund_fee_$n;
	}

	public void setCoupon_refund_fee_$n(String coupon_refund_fee_$n) {
		this.coupon_refund_fee_$n = coupon_refund_fee_$n;
	}

	public String getCoupon_refund_count() {
		return coupon_refund_count;
	}

	public void setCoupon_refund_count(String coupon_refund_count) {
		this.coupon_refund_count = coupon_refund_count;
	}

	public String getCoupon_refund_id_$n() {
		return coupon_refund_id_$n;
	}

	public void setCoupon_refund_id_$n(String coupon_refund_id_$n) {
		this.coupon_refund_id_$n = coupon_refund_id_$n;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getCash_fee_type() {
		return cash_fee_type;
	}

	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}

	public String getReq_info() {
		return req_info;
	}

	public void setReq_info(String req_info) {
		this.req_info = req_info;
	}

	public String getRefund_channel() {
		return refund_channel;
	}

	public void setRefund_channel(String refund_channel) {
		this.refund_channel = refund_channel;
	}

	public String getRefund_account() {
		return refund_account;
	}

	public void setRefund_account(String refund_account) {
		this.refund_account = refund_account;
	}
	
	public String getRefund_recv_accout() {
		return refund_recv_accout;
	}

	public void setRefund_recv_accout(String refund_recv_accout) {
		this.refund_recv_accout = refund_recv_accout;
	}
	
	public String getRefund_request_source() {
		return refund_request_source;
	}

	public void setRefund_request_source(String refund_request_source) {
		this.refund_request_source = refund_request_source;
	}
	
	public String getRefund_status() {
		return refund_status;
	}

	public void setRefund_status(String refund_status) {
		this.refund_status = refund_status;
	}
	
	public String getSuccess_time() {
		return success_time;
	}

	public void setSuccess_time(String success_time) {
		this.success_time = success_time;
	}
	
}
