//package com.chengyu.core.service.pay.wxpay;
//
//import cn.hutool.core.map.MapUtil;
//import com.chengyu.core.exception.ServiceException;
//import com.chengyu.core.service.pay.PayService;
//import com.chengyu.core.service.pay.isp.IspConstant;
//import com.chengyu.core.util.weixin.CertHttpUtil;
//import com.chengyu.core.util.weixin.WechatUtil;
//import com.chengyu.core.util.weixin.WeixinResponse;
//import com.chengyu.core.utils.HttpUtil;
//import com.chengyu.core.utils.JsonUtils;
//import com.chengyu.core.utils.StringUtils;
//import com.github.wxpay.sdk.WXPay;
//import com.github.wxpay.sdk.WXPayUtil;
//import org.dom4j.Document;
//import org.dom4j.DocumentHelper;
//import org.dom4j.Element;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.math.BigDecimal;
//import java.util.*;
//
//public class IspWeixinPay {
//
//	protected static final Logger LOG = LoggerFactory.getLogger(IspWeixinPay.class);
//	public static final String PAY_SUCCESS = "SUCCESS";
//	public static final String PAY_USERPAYING = "USERPAYING";
//
//	public String pay(WeixinPayForm form) throws Exception{
//		String appId = IspConstant.WX_ISP_APP_ID;
//		String mchId = IspConstant.WX_ISP_MCH_ID;
//		String subAppid = "";
//		String tradeType = "";
//		boolean isApp;
//		switch (form.getWxType()) {
//		case PayService.APPLICATION_TYPE_GZH:
//			subAppid = WechatUtil.getGzhAppId();
//			tradeType = "JSAPI";
//			isApp = false;
//			break;
//		case PayService.APPLICATION_TYPE_XCX:
//			subAppid = WechatUtil.getXcxAppId();
//			tradeType = "JSAPI";
//			isApp = false;
//			break;
//		case PayService.APPLICATION_TYPE_APP:
//			subAppid = WechatUtil.getKfAppId();
//			tradeType = "APP";
//			isApp = true;
//			break;
//		default:
//			subAppid = WechatUtil.getKfAppId();
//			tradeType = "APP";
//			isApp = true;
//			break;
//		}
//		Map<String,String> paramMap = new LinkedHashMap<>();
//		paramMap.put("appid", appId);
//		paramMap.put("body", form.getBody());
//		paramMap.put("device_info", "WEB");
//		paramMap.put("fee_type", "CNY");
//		paramMap.put("limit_pay", "no_credit");
//		paramMap.put("mch_id", mchId);
//		paramMap.put("nonce_str", StringUtils.genenrateUniqueInd());
//		paramMap.put("notify_url", form.getNotifyUrl());
//		paramMap.put("out_trade_no", form.getOutTradeNo());
//		paramMap.put("sign_type", "MD5");
//		paramMap.put("spbill_create_ip", form.getIp());
//		paramMap.put("sub_appid", subAppid);
//		paramMap.put("sub_mch_id", WechatUtil.getMchId());
//		if(!isApp){
//			paramMap.put("sub_openid", form.getOpenId());
//		}
//		paramMap.put("total_fee", String.valueOf(form.getTotalAmount().multiply(new BigDecimal(100)).intValue()));
//		paramMap.put("trade_type", tradeType);
//		System.out.println("=======签名参数开始==========");
//		System.out.println(paramMap.toString());
//		System.out.println("=======签名参数结束==========");
//		paramMap.put("sign", WechatUtil.MD5(paramMap));
//		//签名
//		String xmlParams = getPayXmlString(paramMap, isApp);
//		System.out.println("=======签名XML开始==========");
//		System.out.println(xmlParams);
//		System.out.println("=======签名XML结束==========");
//		String message = HttpUtil.doPostByXml(WechatUtil.GEN_ORDER_URL, xmlParams);
//	    WeixinResponse response = WechatUtil.xmlToBean(message, WeixinResponse.class);
//	    System.out.println("=======返回信息开始==========");
//		System.out.println(message);
//		 System.out.println("=======返回结束开始==========");
//
//		if("SUCCESS".equals(response.getReturn_code())){
//			if(StringUtils.isNotBlank(response.getResult_code()) && "SUCCESS".equals(response.getResult_code())){
//				//返回公众号，小程序前端所需参数
//				Map<String,String> result = new LinkedHashMap<>();
//				if(PayService.APPLICATION_TYPE_GZH.equals(form.getWxType())
//						|| PayService.APPLICATION_TYPE_XCX.equals(form.getWxType())){
//					result.put("appId", subAppid);
//					result.put("nonceStr", StringUtils.genenrateUniqueInd());
//					result.put("package", "prepay_id="+response.getPrepay_id());
//					result.put("signType", "MD5");
//					result.put("timeStamp", String.valueOf(new Date().getTime()));
//					result.put("paySign", WechatUtil.MD5(result));
//				}else{
//					//签名顺序要按a,b,c的顺序签名
//					result.put("appid", subAppid);
//					result.put("noncestr", StringUtils.genenrateUniqueInd());
//					result.put("package", "Sign=WXPay");
//					result.put("partnerid", WechatUtil.getMchId());
//					result.put("prepayid", response.getPrepay_id());
//					long timeStampSec = System.currentTimeMillis()/1000;
//			        String timestamp = String.format("%010d", timeStampSec);
//					result.put("timestamp", timestamp);
//					result.put("sign", WechatUtil.MD5(result));
//				}
//				return JsonUtils.object2JsonString(result);
//			}else{
//				throw new ServiceException("支付失败,失败原因:"+response.getErr_code_des());
//			}
//		}else{
//			throw new ServiceException("支付失败,失败原因:"+response.getReturn_msg());
//		}
//	}
//
//	public void refund(WeixinRefundForm form) throws ServiceException{
//		String appId = IspConstant.WX_ISP_APP_ID;
//		String mchId = IspConstant.WX_ISP_MCH_ID;
//		String subAppid = "";
//		boolean isApp;
//		switch (form.getWxType()) {
//		case PayService.APPLICATION_TYPE_GZH:
//			subAppid = WechatUtil.getGzhAppId();
//			isApp = false;
//			break;
//		case PayService.APPLICATION_TYPE_XCX:
//			subAppid = WechatUtil.getXcxAppId();
//			isApp = false;
//			break;
//		case PayService.APPLICATION_TYPE_APP:
//			subAppid = WechatUtil.getKfAppId();
//			isApp = true;
//			break;
//		default:
//			subAppid = WechatUtil.getKfAppId();
//			isApp = true;
//			break;
//		}
//		Map<String,String> paramMap = new LinkedHashMap<>();
//		paramMap.put("appid", appId);
//		paramMap.put("mch_id", mchId);
//		paramMap.put("nonce_str", StringUtils.genenrateUniqueInd());
//		paramMap.put("notify_url", form.getNotifyUrl());
//		paramMap.put("out_refund_no", form.getOutRefundNo());
//		paramMap.put("out_trade_no", form.getOutTradeNo());
//		paramMap.put("refund_fee", String.valueOf(form.getRefundFee().multiply(new BigDecimal(100)).intValue()));
//		paramMap.put("sign_type", "MD5");
//		paramMap.put("sub_appid", subAppid);
//		paramMap.put("sub_mch_id", WechatUtil.getMchId());
//		paramMap.put("total_fee", String.valueOf(form.getTotalAmount().multiply(new BigDecimal(100)).intValue()));
//		System.out.println("=======签名参数开始==========");
//		System.out.println(paramMap.toString());
//		System.out.println("=======签名参数结束==========");
//		paramMap.put("sign", WechatUtil.MD5(paramMap));
//		//签名
//		String xmlParams = getRefundXmlString(paramMap, isApp);
//		System.out.println("=======签名XML开始==========");
//		System.out.println(xmlParams);
//		System.out.println("=======签名XML结束==========");
//		String message = CertHttpUtil.postData(WechatUtil.REFUND_ORDER_URL, xmlParams, IspConstant.WX_ISP_MCH_ID, IspConstant.WX_CERT_PATH);
//	    WeixinRefundResponse response = WechatUtil.xmlToBean(message, WeixinRefundResponse.class);
//	    System.out.println("=======返回信息开始==========");
//		System.out.println(message);
//		 System.out.println("=======返回结束开始==========");
//
//		if("SUCCESS".equals(response.getReturn_code())){
//			if(StringUtils.isNotBlank(response.getResult_code()) && "SUCCESS".equals(response.getResult_code())){
//				//退款申请成功,结果通过退款查询接口查询
//			}else{
//				throw new ServiceException("退款申请失败,失败原因:"+response.getErr_code_des());
//			}
//		}else{
//			throw new ServiceException("退款申请失败,失败原因:"+response.getReturn_msg());
//		}
//	}
//
//	/**
//	 * @功能描述    获取支付XML格式
//	 * @作者      LeGreen
//	 * @创建时间  2019年1月2日
//	 * @param 	  paramMap
//	 * @return	  String
//	 */
//	private String getPayXmlString(Map<String,String> paramMap, boolean isApp){
//		StringBuilder sb = new StringBuilder();
//		sb.append("<xml>");
//		sb.append("<appid><![CDATA["+MapUtil.getStr(paramMap, "appid")+"]]></appid>");
//		sb.append("<body><![CDATA["+MapUtil.getStr(paramMap, "body")+"]]></body>");
//		sb.append("<device_info><![CDATA["+MapUtil.getStr(paramMap, "device_info")+"]]></device_info>");
//		sb.append("<fee_type><![CDATA["+MapUtil.getStr(paramMap, "fee_type")+"]]></fee_type>");
//		sb.append("<limit_pay><![CDATA["+MapUtil.getStr(paramMap, "limit_pay")+"]]></limit_pay>");
//		sb.append("<mch_id><![CDATA["+MapUtil.getStr(paramMap, "mch_id")+"]]></mch_id>");
//		sb.append("<nonce_str><![CDATA["+MapUtil.getStr(paramMap, "nonce_str")+"]]></nonce_str>");
//		sb.append("<notify_url><![CDATA["+MapUtil.getStr(paramMap, "notify_url")+"]]></notify_url>");
//		sb.append("<out_trade_no><![CDATA["+MapUtil.getStr(paramMap, "out_trade_no")+"]]></out_trade_no>");
//		sb.append("<sign_type><![CDATA["+MapUtil.getStr(paramMap, "sign_type")+"]]></sign_type>");
//		sb.append("<spbill_create_ip><![CDATA["+MapUtil.getStr(paramMap, "spbill_create_ip")+"]]></spbill_create_ip>");
//		sb.append("<sub_appid><![CDATA["+MapUtil.getStr(paramMap, "sub_appid")+"]]></sub_appid>");
//		sb.append("<sub_mch_id><![CDATA["+MapUtil.getStr(paramMap, "sub_mch_id")+"]]></sub_mch_id>");
//		if(!isApp){
//			sb.append("<sub_openid><![CDATA["+MapUtil.getStr(paramMap, "sub_openid")+"]]></sub_openid>");
//		}
//		sb.append("<total_fee><![CDATA["+MapUtil.getStr(paramMap, "total_fee")+"]]></total_fee>");
//		sb.append("<trade_type><![CDATA["+MapUtil.getStr(paramMap, "trade_type")+"]]></trade_type>");
//		sb.append("<sign><![CDATA["+MapUtil.getStr(paramMap, "sign")+"]]></sign>");
//		sb.append("</xml>");
//		return sb.toString();
//	}
//
//	private String getRefundXmlString(Map<String,String> paramMap, boolean isApp){
//		StringBuilder sb = new StringBuilder();
//		sb.append("<xml>");
//		sb.append("<appid><![CDATA["+MapUtil.getStr(paramMap, "appid")+"]]></appid>");
//		sb.append("<mch_id><![CDATA["+MapUtil.getStr(paramMap, "mch_id")+"]]></mch_id>");
//		sb.append("<nonce_str><![CDATA["+MapUtil.getStr(paramMap, "nonce_str")+"]]></nonce_str>");
//		sb.append("<notify_url><![CDATA["+MapUtil.getStr(paramMap, "notify_url")+"]]></notify_url>");
//		sb.append("<out_refund_no><![CDATA["+MapUtil.getStr(paramMap, "out_refund_no")+"]]></out_refund_no>");
//		sb.append("<out_trade_no><![CDATA["+MapUtil.getStr(paramMap, "out_trade_no")+"]]></out_trade_no>");
//		sb.append("<refund_fee><![CDATA["+MapUtil.getStr(paramMap, "refund_fee")+"]]></refund_fee>");
//		sb.append("<sign_type><![CDATA["+MapUtil.getStr(paramMap, "sign_type")+"]]></sign_type>");
//		sb.append("<sub_appid><![CDATA["+MapUtil.getStr(paramMap, "sub_appid")+"]]></sub_appid>");
//		sb.append("<sub_mch_id><![CDATA["+MapUtil.getStr(paramMap, "sub_mch_id")+"]]></sub_mch_id>");
//		sb.append("<total_fee><![CDATA["+MapUtil.getStr(paramMap, "total_fee")+"]]></total_fee>");
//		sb.append("<sign><![CDATA["+MapUtil.getStr(paramMap, "sign")+"]]></sign>");
//		sb.append("</xml>");
//		return sb.toString();
//	}
//
//	public String payByScanCode(WeixinPayForm wxForm) throws Exception{
//		  MyWxPayConfig config = new MyWxPayConfig(WechatUtil.getKfAppId(), WechatUtil.getMchId(), WechatUtil.getMchKey(), null);
//	       WXPay wxpay = new WXPay(config);
//	       Map<String, String> map = new HashMap<>(16);
//	       map.put("attach", "收银机支付");
//	       map.put("auth_code", wxForm.getAuthCode());
//	       map.put("body", wxForm.getBody());
//	       map.put("device_info", wxForm.getDeviceInfo());
//	       map.put("nonce_str", WXPayUtil.generateNonceStr());
//	       map.put("out_trade_no", wxForm.getOutTradeNo());
//	       map.put("spbill_create_ip", wxForm.getIp());
//	       map.put("total_fee", String.valueOf(wxForm.getTotalAmount().multiply(new BigDecimal(100)).intValue()));
//	       //生成签名
//	       String sign = WXPayUtil.generateSignature(map, config.getKey());
//	       map.put("sign", sign);
//	       String mapToXml = null;
//	       try {
//	       	   //调用微信的扫码支付接口
//	           Map<String, String> resp = wxpay.microPay(map);
//	           mapToXml = WXPayUtil.mapToXml(resp);
//	       } catch (Exception e) {
//	           e.printStackTrace();
//	           LOG.error("微信支付失败"+ e);
//	       }
//	       //判断支付是否成功
//	       String return_code = null;
//	       String result_code = null;
//	       String err_code_des = null;
//	       String err_code = null;
//	     //获取Document对象（主要是获取支付接口的返回信息）
//	       Document doc = DocumentHelper.parseText(mapToXml);
//	       //获取对象的根节点<xml>
//	       Element rootElement = doc.getRootElement();
//	       //获取对象的子节点
//	       List<Element> elements = rootElement.elements();
//	       for (Element element : elements) {
//	           if(element.getName().equals("return_code")){
//	               return_code = element.getTextTrim();
//	           } else if(element.getName().equals("result_code")){
//	               result_code = element.getTextTrim();
//	           } else if(element.getName().equals("err_code_des")){
//	               err_code_des = element.getTextTrim();
//	           } else if(element.getName().equals("err_code")){
//	               err_code = element.getTextTrim();
//	           }
//	       }
//	       if(PAY_SUCCESS.equals(return_code) && PAY_SUCCESS.equals(result_code)){
//	           LOG.info("微信免密支付成功！");
//	           return PAY_SUCCESS;
//	       } else if (PAY_USERPAYING.equals(err_code)){
//	           for(int i = 0; i < 3; i++){
//	               Thread.sleep(10000);
//	               Map<String, String> data = new HashMap<>(16);
//	               data.put("out_trade_no", wxForm.getOutTradeNo());
//	               //调用微信的查询接口
//	               Map<String, String> orderQuery = wxpay.orderQuery(data);
//	               String orderResp = WXPayUtil.mapToXml(orderQuery);
//	               String trade_state = null;
//	               //获取Document对象
//	               Document orderDoc = DocumentHelper.parseText(orderResp);
//	               //获取对象的根节点<xml>
//	               Element rootElement1 = orderDoc.getRootElement();
//	               //获取对象的子节点
//	               List<Element> elements1 = rootElement1.elements();
//	               for (Element element : elements1) {
//	                   if(element.getName().equals("trade_state")){
//	                       trade_state = element.getTextTrim();
//	                   }
//	               }
//	               if(PAY_SUCCESS.equals(trade_state)){
//	            	   LOG.info("微信加密支付成功！");
//	                   return PAY_SUCCESS;
//	               }
//	               LOG.info("正在支付" + orderResp);
//	           }
//	       }
//	       LOG.error("微信支付失败！");
//	       return err_code_des;
//	}
//}
