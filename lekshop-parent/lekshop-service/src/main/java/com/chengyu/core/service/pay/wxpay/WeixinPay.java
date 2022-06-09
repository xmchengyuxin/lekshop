package com.chengyu.core.service.pay.wxpay;

import cn.hutool.core.map.MapUtil;
import com.chengyu.core.domain.enums.PayEnum;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.SysWeixinConfig;
import com.chengyu.core.util.weixin.CertHttpUtil;
import com.chengyu.core.util.weixin.WechatUtil;
import com.chengyu.core.util.weixin.WeixinResponse;
import com.chengyu.core.utils.HttpUtil;
import com.chengyu.core.utils.JsonUtils;
import com.chengyu.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class WeixinPay {

	public static final String PAY_SUCCESS = "SUCCESS";
	public static final String PAY_USERPAYING = "USERPAYING";

	public String pay(WeixinPayForm form) throws Exception{
		String appId;
		String tradeType;
		boolean isApp;
		SysWeixinConfig config = WechatUtil.config;
		if(PayEnum.PayChannel.GZH.getValue().equals(form.getWxType())){
			appId = config.getGzhAppId();
			tradeType = "JSAPI";
			isApp = false;
		}else if(PayEnum.PayChannel.XCX.getValue().equals(form.getWxType())){
			appId = config.getXcxAppId();
			tradeType = "JSAPI";
			isApp = false;
		}else{
			appId = config.getKfAppId();
			tradeType = "APP";
			isApp = true;
		}
		Map<String,String> paramMap = new LinkedHashMap<>();
		paramMap.put("appid", appId);
		paramMap.put("body", form.getBody());
		paramMap.put("device_info", "WEB");
		paramMap.put("fee_type", "CNY");
		paramMap.put("limit_pay", "no_credit");
		paramMap.put("mch_id", config.getMchId());
		paramMap.put("nonce_str", StringUtils.genenrateUniqueInd());
		paramMap.put("notify_url", form.getNotifyUrl());
		if(!isApp){
			paramMap.put("openid", form.getOpenId());
		}
		paramMap.put("out_trade_no", form.getOutTradeNo());
		paramMap.put("sign_type", "MD5");
		paramMap.put("spbill_create_ip", form.getIp());
		paramMap.put("total_fee", String.valueOf(form.getTotalAmount().multiply(new BigDecimal(100)).intValue()));
		paramMap.put("trade_type", tradeType);
		System.out.println("=======签名参数开始==========");
		System.out.println(paramMap);
		System.out.println("=======签名参数结束==========");
		paramMap.put("sign", WechatUtil.MD5(paramMap, config.getMchKey()));
		//签名
		String xmlParams = getPayXmlString(paramMap, isApp);
		System.out.println("=======签名XML开始==========");
		System.out.println(xmlParams);
		System.out.println("=======签名XML结束==========");
		String message = HttpUtil.doPostByXml(WechatUtil.GEN_ORDER_URL, xmlParams);
	    WeixinResponse response = WechatUtil.xmlToBean(message, WeixinResponse.class); 
	    System.out.println("=======返回信息开始==========");
		System.out.println(message);
		 System.out.println("=======返回结束开始==========");
		
		if("SUCCESS".equals(response.getReturn_code())){
			if(StringUtils.isNotBlank(response.getResult_code()) && "SUCCESS".equals(response.getResult_code())){
				//返回公众号，小程序前端所需参数
				Map<String,String> result = new LinkedHashMap<>();
				if(PayEnum.PayChannel.GZH.getValue().equals(form.getWxType())
						|| PayEnum.PayChannel.XCX.getValue().equals(form.getWxType())){
					result.put("appId", appId);
					result.put("nonceStr", StringUtils.genenrateUniqueInd());
					result.put("package", "prepay_id="+response.getPrepay_id());
					result.put("signType", "MD5");
					result.put("timeStamp", String.valueOf(new Date().getTime()));
					result.put("paySign", WechatUtil.MD5(result, config.getMchKey()));
				}else{
					//签名顺序要按a,b,c的顺序签名
					result.put("appid", appId);
					result.put("noncestr", StringUtils.genenrateUniqueInd());
					result.put("package", "Sign=WXPay");
					result.put("partnerid", config.getMchId());
					result.put("prepayid", response.getPrepay_id());
					long timeStampSec = System.currentTimeMillis()/1000;
			        String timestamp = String.format("%010d", timeStampSec);
					result.put("timestamp", timestamp);
					result.put("sign", WechatUtil.MD5(result, config.getMchKey()));
				}
				return JsonUtils.object2JsonString(result);
			}else{
				throw new ServiceException("支付失败,失败原因:"+response.getErr_code_des());
			}
		}else{
			throw new ServiceException("支付失败,失败原因:"+response.getReturn_msg());
		}
	}
	
	public void refund(WeixinRefundForm form) throws ServiceException{
		String appId;
		boolean isApp;
		SysWeixinConfig config = WechatUtil.config;
		if(PayEnum.PayChannel.GZH.getValue().equals(form.getWxType())){
			appId = config.getGzhAppId();
			isApp = false;
		}else if(PayEnum.PayChannel.XCX.getValue().equals(form.getWxType())){
			appId = config.getXcxAppId();
			isApp = false;
		}else{
			appId = config.getKfAppId();
			isApp = true;
		}

		Map<String,String> paramMap = new LinkedHashMap<>();
		paramMap.put("appid", appId);
		paramMap.put("mch_id", config.getMchId());
		paramMap.put("nonce_str", StringUtils.genenrateUniqueInd());
		paramMap.put("notify_url", form.getNotifyUrl());
		paramMap.put("out_refund_no", form.getOutRefundNo());
		paramMap.put("out_trade_no", form.getOutTradeNo());
		paramMap.put("refund_fee", String.valueOf(form.getRefundFee().multiply(new BigDecimal(100)).intValue()));
		paramMap.put("sign_type", "MD5");
		paramMap.put("total_fee", String.valueOf(form.getTotalAmount().multiply(new BigDecimal(100)).intValue()));
		System.out.println("=======签名参数开始==========");
		System.out.println(paramMap.toString());
		System.out.println("=======签名参数结束==========");
		paramMap.put("sign", WechatUtil.MD5(paramMap, config.getMchKey()));
		//签名
		String xmlParams = getRefundXmlString(paramMap, isApp);
		System.out.println("=======签名XML开始==========");
		System.out.println(xmlParams);
		System.out.println("=======签名XML结束==========");
		String message = CertHttpUtil.postData(WechatUtil.REFUND_ORDER_URL, xmlParams, config.getMchId(), config.getCertPath());
	    WeixinRefundResponse response = WechatUtil.xmlToBean(message, WeixinRefundResponse.class); 
	    System.out.println("=======返回信息开始==========");
		System.out.println(message);
		 System.out.println("=======返回结束开始==========");
		
		if("SUCCESS".equals(response.getReturn_code())){
			if(StringUtils.isNotBlank(response.getResult_code()) && PAY_SUCCESS.equals(response.getResult_code())){
				//退款申请成功,结果通过退款查询接口查询 
			}else{
				throw new ServiceException("退款申请失败,失败原因:"+response.getErr_code_des());
			}
		}else{
			throw new ServiceException("退款申请失败,失败原因:"+response.getReturn_msg());
		}
	}
	
	/**
	 * @功能描述    获取支付XML格式
	 * @作者      LeGreen
	 * @创建时间  2019年1月2日
	 * @param 	  paramMap
	 * @return	  String
	 */
	private String getPayXmlString(Map<String,String> paramMap, boolean isApp){
		StringBuilder sb = new StringBuilder(); 
		sb.append("<xml>"); 
		sb.append("<appid><![CDATA["+MapUtil.getStr(paramMap, "appid")+"]]></appid>"); 
		sb.append("<body><![CDATA["+MapUtil.getStr(paramMap, "body")+"]]></body>"); 
		sb.append("<device_info><![CDATA["+MapUtil.getStr(paramMap, "device_info")+"]]></device_info>"); 
		sb.append("<fee_type><![CDATA["+MapUtil.getStr(paramMap, "fee_type")+"]]></fee_type>"); 
		sb.append("<limit_pay><![CDATA["+MapUtil.getStr(paramMap, "limit_pay")+"]]></limit_pay>"); 
		sb.append("<mch_id><![CDATA["+MapUtil.getStr(paramMap, "mch_id")+"]]></mch_id>"); 
		sb.append("<nonce_str><![CDATA["+MapUtil.getStr(paramMap, "nonce_str")+"]]></nonce_str>"); 
		sb.append("<notify_url><![CDATA["+MapUtil.getStr(paramMap, "notify_url")+"]]></notify_url>"); 
		if(!isApp){
			sb.append("<openid><![CDATA["+MapUtil.getStr(paramMap, "openid")+"]]></openid>");
		}
		sb.append("<out_trade_no><![CDATA["+MapUtil.getStr(paramMap, "out_trade_no")+"]]></out_trade_no>"); 
		sb.append("<sign_type><![CDATA["+MapUtil.getStr(paramMap, "sign_type")+"]]></sign_type>"); 
		sb.append("<spbill_create_ip><![CDATA["+MapUtil.getStr(paramMap, "spbill_create_ip")+"]]></spbill_create_ip>"); 
		sb.append("<total_fee><![CDATA["+MapUtil.getStr(paramMap, "total_fee")+"]]></total_fee>"); 
		sb.append("<trade_type><![CDATA["+MapUtil.getStr(paramMap, "trade_type")+"]]></trade_type>"); 
		sb.append("<sign><![CDATA["+MapUtil.getStr(paramMap, "sign")+"]]></sign>"); 
		sb.append("</xml>"); 
		return sb.toString();
	}
	
	private String getRefundXmlString(Map<String,String> paramMap, boolean isApp){
		StringBuilder sb = new StringBuilder(); 
		sb.append("<xml>"); 
		sb.append("<appid><![CDATA["+MapUtil.getStr(paramMap, "appid")+"]]></appid>");
		sb.append("<mch_id><![CDATA["+MapUtil.getStr(paramMap, "mch_id")+"]]></mch_id>"); 
		sb.append("<nonce_str><![CDATA["+MapUtil.getStr(paramMap, "nonce_str")+"]]></nonce_str>"); 
		sb.append("<notify_url><![CDATA["+MapUtil.getStr(paramMap, "notify_url")+"]]></notify_url>"); 
		sb.append("<out_refund_no><![CDATA["+MapUtil.getStr(paramMap, "out_refund_no")+"]]></out_refund_no>"); 
		sb.append("<out_trade_no><![CDATA["+MapUtil.getStr(paramMap, "out_trade_no")+"]]></out_trade_no>"); 
		sb.append("<refund_fee><![CDATA["+MapUtil.getStr(paramMap, "refund_fee")+"]]></refund_fee>"); 
		sb.append("<sign_type><![CDATA["+MapUtil.getStr(paramMap, "sign_type")+"]]></sign_type>"); 
		sb.append("<total_fee><![CDATA["+MapUtil.getStr(paramMap, "total_fee")+"]]></total_fee>"); 
		sb.append("<sign><![CDATA["+MapUtil.getStr(paramMap, "sign")+"]]></sign>"); 
		sb.append("</xml>"); 
		return sb.toString();
	}

}
