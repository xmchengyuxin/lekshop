package com.chengyu.core.controller.callback;

import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.controller.callback.manager.CallbackManager;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.SysWeixinConfig;
import com.chengyu.core.service.pay.wxpay.WeixinRefundResponse;
import com.chengyu.core.util.weixin.WechatUtil;
import com.chengyu.core.utils.MD5Util;
import com.chengyu.core.utils.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Base64;


@Controller
public class RefundCallbackController extends UserBaseController {
	
    private static Cipher cipher = null;  //解码器
	
	@Autowired
	private CallbackManager callbackManager;
	
    public CommonResult<String> refundSusForZfb(HttpServletRequest request,HttpServletResponse response, String type) throws Exception{
    	//支付宝退款目前没有使用异步退款,无需回调
		return CommonResult.success(null);
    }
	
    public String refundSusForWx(HttpServletRequest request, String type) throws Exception{
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
			String result = outStream.toString("UTF-8");
			System.out.println("微信退款回调:"+result);
			WeixinRefundResponse response = WechatUtil.xmlToBean(result, WeixinRefundResponse.class);
			
			String reqInfo = response.getReq_info();
			
			init();
			Base64.Decoder decoder = Base64.getDecoder();
	        byte[] base64ByteArr = decoder.decode(reqInfo);
	 
	        String jmResult = new String(cipher.doFinal(base64ByteArr));
	        System.out.println("解密结果:{}" + jmResult);
	        String jmr = jmResult.replaceAll("<root>", "<xml>").replaceAll("</root>", "</xml>");
	        WeixinRefundResponse notifyResponse = WechatUtil.xmlToBean(jmr, WeixinRefundResponse.class);
	        
//	        <out_refund_no><![CDATA[1577366921140506]]></out_refund_no>
//	        <out_trade_no><![CDATA[1577366881535619]]></out_trade_no>
//	        <refund_account><![CDATA[REFUND_SOURCE_RECHARGE_FUNDS]]></refund_account>
//	        <refund_fee><![CDATA[2]]></refund_fee>
//	        <refund_id><![CDATA[50300402742019122613860618766]]></refund_id>
//	        <refund_recv_accout><![CDATA[鏀粯鐢ㄦ埛闆堕挶]]></refund_recv_accout>
//	        <refund_request_source><![CDATA[API]]></refund_request_source>
//	        <refund_status><![CDATA[SUCCESS]]></refund_status>
//	        <settlement_refund_fee><![CDATA[2]]></settlement_refund_fee>
//	        <settlement_total_fee><![CDATA[2]]></settlement_total_fee>
//	        <success_time><![CDATA[2019-12-26 21:28:56]]></success_time>
//	        <total_fee><![CDATA[2]]></total_fee>
//	        <transaction_id><![CDATA[4200000452201912268470524028]]></transaction_id>
			//通知成功
			if(StringUtils.isNotBlank(notifyResponse.getRefund_status()) && "SUCCESS".equals(notifyResponse.getRefund_status())){
					System.out.println("退款单号："+notifyResponse.getOut_refund_no());
					callbackManager.getPaySusFactory(type).refundSus(notifyResponse.getOut_refund_no());
				}
		}
		
		return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }
	
    public static void init() {
		SysWeixinConfig config = WechatUtil.config;
        String key = MD5Util.MD5Encode(config.getMchKey(), "");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Security.addProvider(new BouncyCastleProvider());
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }
    
}