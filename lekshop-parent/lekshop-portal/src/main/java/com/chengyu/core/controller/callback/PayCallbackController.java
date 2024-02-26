package com.chengyu.core.controller.callback;

import cn.hutool.json.JSONUtil;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.controller.callback.manager.CallbackManager;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.SysWeixinConfig;
import com.chengyu.core.service.pay.wxpay.WeixinPayV3;
import com.chengyu.core.util.weixin.WechatUtil;
import com.chengyu.core.util.weixin.WeixinResponse;
import com.chengyu.core.utils.StringUtils;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.exception.ValidationException;
import com.wechat.pay.java.core.notification.NotificationConfig;
import com.wechat.pay.java.core.notification.NotificationParser;
import com.wechat.pay.java.core.notification.RequestParam;
import com.wechat.pay.java.service.payments.model.Transaction;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @类描述   	支付回调
 * @作者   		LeGreen
 * @创建时间  	2020年3月21日
 * @版本   		1.00
 * @修改记录
 * <pre>
 *     版本          修改人    		修改日期 			修改内容描述
 *     -----------------------------------------------------------
 *     1.00   	LeGreen    	2020年3月21日             
 *     -----------------------------------------------------------
 * </pre>
 */
@Log4j2
@Controller
public class PayCallbackController extends UserBaseController {
	
	@Autowired
	private CallbackManager paySusManager;

    public CommonResult<String> paySusForZfb(HttpServletRequest request, HttpServletResponse response, String type) throws Exception{
		 //1.从支付宝回调的request域中取值 
		 //获取支付宝返回的参数集合
	     Map<String, String[]> aliParams = request.getParameterMap(); 
	     //用以存放转化后的参数集合
	     Map<String, String> conversionParams = new HashMap<String, String>(); 
	     for (Iterator<String> iter = aliParams.keySet().iterator(); iter.hasNext();) { 
		     String key = iter.next(); 
		     String[] values = aliParams.get(key); 
		     String valueStr = ""; 
		     for (int i = 0; i < values.length; i++) { 
		       valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ","; 
		     } 
		     // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化 
		     // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "uft-8"); 
		     conversionParams.put(key, valueStr); 
		}  
	     System.out.println("支付宝支付回调:"+conversionParams);
	    
//	    boolean flag = payService.checkPayNotify(conversionParams, PayService.PAY_TYPE_ALI);
    	String tradeStatus = conversionParams.get("trade_status");// 获取交易状态 
    	String outTradeNo = conversionParams.get("out_trade_no");//获取商户之前传给支付宝的订单号（商户系统的唯一订单号）
    	
    	if(tradeStatus.equals("TRADE_SUCCESS")) { 
    		//只处理支付成功的订单: 修改交易表状态,支付成功
    		paySusManager.getPaySusFactory(type).paySus(outTradeNo);
    		
    		String resStr = "success";
			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
			out.write(resStr.getBytes());
			out.flush();
			out.close();
    	}
		return CommonResult.success(null);
    }

	public String paySusForWx(HttpServletRequest request, String type) throws Exception{
		return paySusForWxV2(request, type);
	}

    public String paySusForWxV2(HttpServletRequest request, String type) throws Exception{
		InputStream inStream = request.getInputStream(); 
		int _buffer_size = 1024; 
		if (inStream != null) { 
			ByteArrayOutputStream outStream = new ByteArrayOutputStream(); 
			byte[] tempBytes = new byte[_buffer_size]; 
			int count = -1; 
			while ((count = inStream.read(tempBytes, 0, _buffer_size)) != -1) {
				outStream.write(tempBytes, 0, count); 
			} 
			tempBytes = null; outStream.flush(); 
			//将流转换成字符串 
			String result = new String(outStream.toByteArray(), "UTF-8"); 
			System.out.println("微信支付回调:"+result);
			WeixinResponse response = WechatUtil.xmlToBean(result, WeixinResponse.class);
			
			//通知成功
			if(StringUtils.isNotBlank(response.getReturn_code()) && CommonConstant.SUCCESS.equals(response.getReturn_code())){
				if(StringUtils.isNotBlank(response.getResult_code()) && CommonConstant.SUCCESS.equals(response.getResult_code())){
					//支付成功
					paySusManager.getPaySusFactory(type).paySus(response.getOut_trade_no());
				}
			}
			
		}
		
		return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }


	public String paySusForWxV3(HttpServletRequest request, String type) throws Exception{
		SysWeixinConfig weixinConfig = WechatUtil.config;
		// 构造 RequestParam
		RequestParam requestParam = new RequestParam.Builder()
				.serialNumber(weixinConfig.getMerchantSerialNumber())
				.nonce(StringUtils.genenrateUniqueInd())
				.signature("")
				.timestamp(String.valueOf(new Date().getTime()))
				.body("")
				.build();

		// 如果已经初始化了 RSAAutoCertificateConfig，可直接使用
		// 没有的话，则构造一个
		NotificationConfig config = new RSAAutoCertificateConfig.Builder()
				.merchantId(weixinConfig.getMchId())
				.privateKeyFromPath(weixinConfig.getPrivateKeyPath())
				.merchantSerialNumber(weixinConfig.getMerchantSerialNumber())
				.apiV3Key(weixinConfig.getApiV3Key())
				.build();

		// 初始化 NotificationParser
		NotificationParser parser = new NotificationParser(config);

		Transaction transaction;
		try {
			// 以支付通知回调为例，验签、解密并转换成 Transaction
			transaction = parser.parse(requestParam, Transaction.class);
		} catch (ValidationException e) {
			// 签名验证失败，返回 401 UNAUTHORIZED 状态码
			log.error("sign verification failed", e);
			return JSONUtil.toJsonStr(ResponseEntity.status(HttpStatus.UNAUTHORIZED));
		}

		// 如果处理失败，应返回 4xx/5xx 的状态码，例如 500 INTERNAL_SERVER_ERROR
		if (transaction == null || transaction.getTradeState() != Transaction.TradeStateEnum.SUCCESS) {
			return JSONUtil.toJsonStr(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR));
		}
		//支付成功
		paySusManager.getPaySusFactory(type).paySus(transaction.getOutTradeNo());
		// 处理成功，返回 200 OK 状态码
		return JSONUtil.toJsonStr(ResponseEntity.status(HttpStatus.OK));
	}
	
}