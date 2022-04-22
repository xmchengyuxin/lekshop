package com.chengyu.core.util.weixin;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class WeixinOrderResponse {
	
	@XStreamAlias("appid")
	private String appid;
	
	@XStreamAlias("device_info")
	private String device_info;
	
	@XStreamAlias("nonce_str")
	private String nonce_str;
	
	@XStreamAlias("sign")
	private String sign;
	
	@XStreamAlias("sign_type")
	private String sign_type;
	
	@XStreamAlias("settlement_total_fee")
	private String settlement_total_fee;
	
	@XStreamAlias("coupon_fee")
	private String coupon_fee;
	
	@XStreamAlias("coupon_count")
	private String coupon_count;
	
	@XStreamAlias("coupon_type_$n")
	private String coupon_type_$n;
	
	@XStreamAlias("coupon_id_$n")
	private String coupon_id_$n;
	
	@XStreamAlias("coupon_fee_$n")
	private String coupon_fee_$n;
	
	@XStreamAlias("attach")
	private String attach;
	
	@XStreamAlias("code_url")
	private String code_url;
	
	@XStreamAlias("return_code")
	private String return_code;
	
	@XStreamAlias("return_msg")
	private String return_msg;
	
	@XStreamAlias("result_code")
	private String result_code;
	
	@XStreamAlias("err_code")
	private String err_code;
	
	@XStreamAlias("err_code_des")
	private String err_code_des;
	
	@XStreamAlias("mch_billno")
	private String mch_billno;
	
	@XStreamAlias("mch_id")
	private String mch_id;
	
	@XStreamAlias("wxappid")
	private String wxappid;
	
	@XStreamAlias("re_openid")
	private String re_openid;
	
	@XStreamAlias("total_amount")
	private String total_amount;
	
	@XStreamAlias("send_listid")
	private String send_listid;
	
	@XStreamAlias("prepay_id")
	private String prepay_id;
	
	@XStreamAlias("trade_type")
	private String trade_type;
	
	@XStreamAlias("openid")
	private String openid;
	
	@XStreamAlias("is_subscribe")
	private String is_subscribe;
	
	@XStreamAlias("bank_type")
	private String bank_type;
	
	@XStreamAlias("total_fee")
	private String total_fee;
	
	@XStreamAlias("cash_fee")
	private String cash_fee;
	
	@XStreamAlias("transaction_id")
	private String transaction_id;
	
	@XStreamAlias("out_trade_no")
	private String out_trade_no;
	
	@XStreamAlias("time_end")
	private String time_end;
	
	@XStreamAlias("fee_type")
	private String fee_type;
	
	@XStreamAlias("cash_fee_type")
	private String cash_fee_type;
	
	@XStreamAlias("trade_state")
	private String trade_state;
	
	@XStreamAlias("trade_state_desc")
	private String trade_state_desc;
	
	
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
	public String getMch_billno() {
		return mch_billno;
	}
	public void setMch_billno(String mch_billno) {
		this.mch_billno = mch_billno;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getWxappid() {
		return wxappid;
	}
	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}
	public String getRe_openid() {
		return re_openid;
	}
	public void setRe_openid(String re_openid) {
		this.re_openid = re_openid;
	}
	public String getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}
	public String getSend_listid() {
		return send_listid;
	}
	public void setSend_listid(String send_listid) {
		this.send_listid = send_listid;
	}
	public String getPrepay_id() {
		return prepay_id;
	}
	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
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
	public String getCode_url() {
		return code_url;
	}
	public void setCode_url(String code_url) {
		this.code_url = code_url;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getIs_subscribe() {
		return is_subscribe;
	}
	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}
	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
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
	public String getTime_end() {
		return time_end;
	}
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getSettlement_total_fee() {
		return settlement_total_fee;
	}
	public void setSettlement_total_fee(String settlement_total_fee) {
		this.settlement_total_fee = settlement_total_fee;
	}
	public String getCoupon_fee() {
		return coupon_fee;
	}
	public void setCoupon_fee(String coupon_fee) {
		this.coupon_fee = coupon_fee;
	}
	public String getCoupon_count() {
		return coupon_count;
	}
	public void setCoupon_count(String coupon_count) {
		this.coupon_count = coupon_count;
	}
	public String getCoupon_type_$n() {
		return coupon_type_$n;
	}
	public void setCoupon_type_$n(String coupon_type_$n) {
		this.coupon_type_$n = coupon_type_$n;
	}
	public String getCoupon_id_$n() {
		return coupon_id_$n;
	}
	public void setCoupon_id_$n(String coupon_id_$n) {
		this.coupon_id_$n = coupon_id_$n;
	}
	public String getCoupon_fee_$n() {
		return coupon_fee_$n;
	}
	public void setCoupon_fee_$n(String coupon_fee_$n) {
		this.coupon_fee_$n = coupon_fee_$n;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getCash_fee_type() {
		return cash_fee_type;
	}
	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}
	public String getTrade_state() {
		return trade_state;
	}
	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}
	public String getTrade_state_desc() {
		return trade_state_desc;
	}
	public void setTrade_state_desc(String trade_state_desc) {
		this.trade_state_desc = trade_state_desc;
	}
	
}
