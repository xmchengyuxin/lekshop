package com.chengyu.core.service.pay.wxpay;

import cn.hutool.json.JSONUtil;
import com.chengyu.core.domain.enums.PayEnum;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.SysWeixinConfig;
import com.chengyu.core.util.weixin.WechatUtil;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.exception.HttpException;
import com.wechat.pay.java.core.exception.MalformedMessageException;
import com.wechat.pay.java.service.payments.app.AppServiceExtension;
import com.wechat.pay.java.service.payments.app.model.PrepayWithRequestPaymentResponse;
import com.wechat.pay.java.service.payments.h5.H5Service;
import com.wechat.pay.java.service.payments.h5.model.*;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.payments.jsapi.model.Payer;
import com.wechat.pay.java.service.payments.nativepay.NativePayService;
import com.wechat.pay.java.service.refund.RefundService;
import com.wechat.pay.java.service.refund.model.CreateRequest;
import com.wechat.pay.java.service.refund.model.Refund;
import com.wechat.pay.java.service.refund.model.Status;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;

@Log4j2
public class WeixinPay {

	public static final String SUCCESS = "SUCCESS";

	public static H5Service h5Service;
	public static AppServiceExtension appService;
	public static JsapiServiceExtension jsapiService;
	public static NativePayService nativePayService;
	public static RSAAutoCertificateConfig rsaConfig;
	public static RefundService refundService;

	private RSAAutoCertificateConfig getRSAConfig(SysWeixinConfig weixinConfig){
		if(rsaConfig == null) {
			// 初始化商户配置
			rsaConfig = new RSAAutoCertificateConfig.Builder()
				.merchantId(weixinConfig.getMchId())
				// 使用 com.wechat.pay.java.core.util 中的函数从本地文件中加载商户私钥，商户私钥会用来生成请求的签名
				.privateKeyFromPath(weixinConfig.getPrivateKeyPath())
				.merchantSerialNumber(weixinConfig.getMerchantSerialNumber())
				.apiV3Key(weixinConfig.getApiV3Key())
				.build();
		}
		return rsaConfig;
	}

	private AppServiceExtension getAppServiceInstance(SysWeixinConfig weixinConfig) {
		if(appService == null) {
			// 初始化服务
			appService = new AppServiceExtension.Builder().config(this.getRSAConfig(weixinConfig)).build();
		}
		return appService;
	}

	private JsapiServiceExtension getJsapiServiceInstance(SysWeixinConfig weixinConfig) {
		if(jsapiService == null) {
			// 初始化服务
			jsapiService = new JsapiServiceExtension.Builder().config(this.getRSAConfig(weixinConfig)).build();
		}
		return jsapiService;
	}

	private NativePayService getNativeServiceInstance(SysWeixinConfig weixinConfig) {
		if(nativePayService == null) {
			// 初始化服务
			nativePayService = new NativePayService.Builder().config(this.getRSAConfig(weixinConfig)).build();
		}
		return nativePayService;
	}

	private H5Service getH5ServiceInstance(SysWeixinConfig weixinConfig) {
		if(h5Service == null) {
			// 初始化服务
			h5Service = new H5Service.Builder().config(this.getRSAConfig(weixinConfig)).build();
		}
		return h5Service;
	}

	private RefundService getRefundServiceInstance(SysWeixinConfig weixinConfig) {
		if(refundService == null) {
			// 初始化服务
			refundService = new RefundService.Builder().config(this.getRSAConfig(weixinConfig)).build();
		}
		return refundService;
	}

	public String pay(WeixinPayForm form) throws Exception{
		if(PayEnum.PayChannel.H5.getValue().equals(form.getWxType())){
			return payByH5(form);
		} else if(PayEnum.PayChannel.XCX.getValue().equals(form.getWxType()) || PayEnum.PayChannel.GZH.getValue().equals(form.getWxType())){
			return payByJsapi(form);
		} else if(PayEnum.PayChannel.APP.getValue().equals(form.getWxType())){
			return payByApp(form);
		} else if(PayEnum.PayChannel.NATIVE.getValue().equals(form.getWxType())){
			return payByNative(form);
		}
		return null;
	}

	/**
	 * @title  微信H5支付
	 * @author LeGreen
	 * @date   2023/7/10
	 */
	private String payByH5(WeixinPayForm form) {
		SysWeixinConfig config = WechatUtil.config;
		PrepayRequest request = new PrepayRequest();
		// 调用request.setXxx(val)设置所需参数，具体参数可见Request定义
		request.setAppid(config.getKfAppId());
		request.setMchid(config.getMchId());
		request.setDescription(form.getBody());
		request.setOutTradeNo(form.getOutTradeNo());
		request.setNotifyUrl(config.getNotifyUrl());
		Amount amount = new Amount();
		amount.setTotal(form.getTotalAmount().multiply(new BigDecimal(100)).intValue());
		request.setAmount(amount);
		SceneInfo sceneInfo = new SceneInfo();
		sceneInfo.setPayerClientIp(form.getIp());
		H5Info h5Info = new H5Info();
		h5Info.setType("WAP");
		sceneInfo.setH5Info(h5Info);
		request.setSceneInfo(sceneInfo);
		// 调用接口
		try {
			PrepayResponse response = getH5ServiceInstance(config).prepay(request);
			return response.getH5Url();
		} catch (HttpException e) { // 发送HTTP请求失败
			// 调用e.getHttpRequest()获取请求打印日志或上报监控，更多方法见HttpException定义
			log.info(e.getHttpRequest());
		} catch (com.wechat.pay.java.core.exception.ServiceException e) { // 服务返回状态小于200或大于等于300，例如500
			// 调用e.getResponseBody()获取返回体打印日志或上报监控，更多方法见ServiceException定义
			log.info(e.getResponseBody());
		} catch (MalformedMessageException e) { // 服务返回成功，返回体类型不合法，或者解析返回体失败
			// 调用e.getMessage()获取信息打印日志或上报监控，更多方法见MalformedMessageException定义
			log.info(e.getMessage());
		}
		return null;
	}

	/**
	 * @title  App支付
	 * @author LeGreen
	 * @date   2023/7/10
	 */
	private String payByApp(WeixinPayForm form) {
		SysWeixinConfig config = WechatUtil.config;
		com.wechat.pay.java.service.payments.app.model.PrepayRequest request = new com.wechat.pay.java.service.payments.app.model.PrepayRequest();
		// 调用request.setXxx(val)设置所需参数，具体参数可见Request定义
		request.setAppid(config.getKfAppId());
		request.setMchid(config.getMchId());
		request.setDescription(form.getBody());
		request.setOutTradeNo(form.getOutTradeNo());
		request.setNotifyUrl(config.getPayNotifyUrl());
		com.wechat.pay.java.service.payments.app.model.Amount amount = new com.wechat.pay.java.service.payments.app.model.Amount();
		amount.setTotal(form.getTotalAmount().multiply(new BigDecimal(100)).intValue());
		request.setAmount(amount);
		com.wechat.pay.java.service.payments.app.model.SceneInfo sceneInfo = new com.wechat.pay.java.service.payments.app.model.SceneInfo();
		sceneInfo.setPayerClientIp(form.getIp());
		request.setSceneInfo(sceneInfo);
		// 调用接口
		try {
			PrepayWithRequestPaymentResponse response = getAppServiceInstance(config).prepayWithRequestPayment(request);
			return JSONUtil.toJsonStr(response);
		} catch (HttpException e) { // 发送HTTP请求失败
			// 调用e.getHttpRequest()获取请求打印日志或上报监控，更多方法见HttpException定义
			log.info(e.getHttpRequest());
		} catch (com.wechat.pay.java.core.exception.ServiceException e) { // 服务返回状态小于200或大于等于300，例如500
			// 调用e.getResponseBody()获取返回体打印日志或上报监控，更多方法见ServiceException定义
			log.info(e.getResponseBody());
		} catch (MalformedMessageException e) { // 服务返回成功，返回体类型不合法，或者解析返回体失败
			// 调用e.getMessage()获取信息打印日志或上报监控，更多方法见MalformedMessageException定义
			log.info(e.getMessage());
		}
		return null;
	}

	/**
	 * @title  Jsapi支付
	 * @author LeGreen
	 * @date   2023/7/10
	 */
	private String payByJsapi(WeixinPayForm form) {
		SysWeixinConfig config = WechatUtil.config;
		boolean isGzh = PayEnum.PayChannel.GZH.getValue().equals(form.getWxType());
		com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest request = new com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest();
		// 调用request.setXxx(val)设置所需参数，具体参数可见Request定义
		request.setAppid(isGzh ? config.getGzhAppId() : config.getXcxAppId());
		request.setMchid(config.getMchId());
		request.setDescription(form.getBody());
		request.setOutTradeNo(form.getOutTradeNo());
		request.setNotifyUrl(config.getNotifyUrl());
		com.wechat.pay.java.service.payments.jsapi.model.Amount amount = new com.wechat.pay.java.service.payments.jsapi.model.Amount();
		amount.setTotal(form.getTotalAmount().multiply(new BigDecimal(100)).intValue());
		request.setAmount(amount);
		com.wechat.pay.java.service.payments.jsapi.model.SceneInfo sceneInfo = new com.wechat.pay.java.service.payments.jsapi.model.SceneInfo();
		sceneInfo.setPayerClientIp(form.getIp());
		request.setSceneInfo(sceneInfo);
		Payer payer = new Payer();
		payer.setOpenid(form.getOpenId());
		request.setPayer(payer);
		// 调用接口
		try {
			com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse response = getJsapiServiceInstance(config).prepayWithRequestPayment(request);
			return JSONUtil.toJsonStr(response);
		} catch (HttpException e) { // 发送HTTP请求失败
			// 调用e.getHttpRequest()获取请求打印日志或上报监控，更多方法见HttpException定义
			log.info(e.getHttpRequest());
		} catch (com.wechat.pay.java.core.exception.ServiceException e) { // 服务返回状态小于200或大于等于300，例如500
			// 调用e.getResponseBody()获取返回体打印日志或上报监控，更多方法见ServiceException定义
			log.info(e.getResponseBody());
		} catch (MalformedMessageException e) { // 服务返回成功，返回体类型不合法，或者解析返回体失败
			// 调用e.getMessage()获取信息打印日志或上报监控，更多方法见MalformedMessageException定义
			log.info(e.getMessage());
		}
		return null;
	}

	/**
	 * @title  Native支付
	 * @author LeGreen
	 * @date   2023/7/10
	 */
	private String payByNative(WeixinPayForm form) {
		SysWeixinConfig config = WechatUtil.config;
		com.wechat.pay.java.service.payments.nativepay.model.PrepayRequest request = new com.wechat.pay.java.service.payments.nativepay.model.PrepayRequest();
		// 调用request.setXxx(val)设置所需参数，具体参数可见Request定义
		request.setAppid(config.getKfAppId());
		request.setMchid(config.getMchId());
		request.setDescription(form.getBody());
		request.setOutTradeNo(form.getOutTradeNo());
		request.setNotifyUrl(config.getNotifyUrl());
		com.wechat.pay.java.service.payments.nativepay.model.Amount amount = new com.wechat.pay.java.service.payments.nativepay.model.Amount();
		amount.setTotal(form.getTotalAmount().multiply(new BigDecimal(100)).intValue());
		request.setAmount(amount);
		com.wechat.pay.java.service.payments.nativepay.model.SceneInfo sceneInfo = new com.wechat.pay.java.service.payments.nativepay.model.SceneInfo();
		sceneInfo.setPayerClientIp(form.getIp());
		request.setSceneInfo(sceneInfo);
		// 调用接口
		try {
			com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse response = getNativeServiceInstance(config).prepay(request);
			return response.getCodeUrl();
		} catch (HttpException e) { // 发送HTTP请求失败
			// 调用e.getHttpRequest()获取请求打印日志或上报监控，更多方法见HttpException定义
			log.info(e.getHttpRequest());
		} catch (com.wechat.pay.java.core.exception.ServiceException e) { // 服务返回状态小于200或大于等于300，例如500
			// 调用e.getResponseBody()获取返回体打印日志或上报监控，更多方法见ServiceException定义
			log.info(e.getResponseBody());
		} catch (MalformedMessageException e) { // 服务返回成功，返回体类型不合法，或者解析返回体失败
			// 调用e.getMessage()获取信息打印日志或上报监控，更多方法见MalformedMessageException定义
			log.info(e.getMessage());
		}
		return null;
	}

	public void refund(WeixinRefundForm form) throws ServiceException{
		CreateRequest request = new CreateRequest();
		request.setOutTradeNo(form.getOutTradeNo());
		request.setOutRefundNo(form.getOutRefundNo());
		com.wechat.pay.java.service.refund.model.AmountReq amount = new com.wechat.pay.java.service.refund.model.AmountReq();
		amount.setRefund(form.getRefundFee().multiply(new BigDecimal(100)).longValue());
		amount.setTotal(form.getTotalAmount().multiply(new BigDecimal(100)).longValue());
		amount.setCurrency("CNY");
		request.setAmount(amount);
		try {
			Refund refund = getRefundServiceInstance(WechatUtil.config).create(request);
			if(Status.ABNORMAL.equals(refund.getStatus())) {
				throw new ServiceException("退款申请失败");
			}
		} catch (HttpException e) { // 发送HTTP请求失败
			// 调用e.getHttpRequest()获取请求打印日志或上报监控，更多方法见HttpException定义
			log.info(e.getHttpRequest());
		} catch (com.wechat.pay.java.core.exception.ServiceException e) { // 服务返回状态小于200或大于等于300，例如500
			// 调用e.getResponseBody()获取返回体打印日志或上报监控，更多方法见ServiceException定义
			log.info(e.getResponseBody());
		} catch (MalformedMessageException e) { // 服务返回成功，返回体类型不合法，或者解析返回体失败
			// 调用e.getMessage()获取信息打印日志或上报监控，更多方法见MalformedMessageException定义
			log.info(e.getMessage());
		}
	}

}
