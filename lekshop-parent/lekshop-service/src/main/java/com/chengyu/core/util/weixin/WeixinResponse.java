package com.chengyu.core.util.weixin;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeixinResponse {
	
	@XStreamAlias("appid")
	private String appid;
	
	@XStreamAlias("sub_appid")
	private String sub_appid;
	
	@XStreamAlias("sub_mch_id")
	private String sub_mch_id;
	
	@XStreamAlias("sub_is_subscribe")
	private String sub_is_subscribe;
	
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
	
	@XStreamAlias("sub_openid")
	private String sub_openid;
	
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
	
}
