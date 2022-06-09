package com.chengyu.core.service.pay.alipay;

import cn.hutool.json.JSONUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.util.ali.AliUtil;
import com.chengyu.core.utils.NumberUtils;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Map;

@Log4j2
public class AliPay {

	protected static final Logger LOG = LoggerFactory.getLogger(AliPay.class);
	
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 加密类型
    public static String SIGNTYPE = "RSA2";

    private AlipayClient alipayClient;
	public static final String PAY_SUCCESS = "SUCCESS";
	public static final String PAY_USERPAYING = "USERPAYING";
	public static final String PAY_FAIL = "FAIL";

	private AlipayClient getAlipayClient() throws AlipayApiException {
		if(alipayClient == null){
			CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
			certAlipayRequest.setServerUrl(AliUtil.ALIPAY_API);  //gateway:支付宝网关（固定）https://openapi.alipay.com/gateway.do
			certAlipayRequest.setAppId(AliUtil.getAppId());  //APPID 即创建应用后生成,详情见创建应用并获取 APPID
			certAlipayRequest.setPrivateKey(AliUtil.getAppPrivateKey());  //开发者应用私钥，由开发者自己生成
			certAlipayRequest.setFormat(FORMAT);  //参数返回格式，只支持 json 格式
			certAlipayRequest.setCharset(CHARSET);  //请求和签名使用的字符编码格式，支持 GBK和 UTF-8
			certAlipayRequest.setSignType(SIGNTYPE);  //商户生成签名字符串所使用的签名算法类型，目前支持 RSA2 和 RSA，推荐商家使用 RSA2。
			certAlipayRequest.setCertPath("C:/java/prod/cert/appCertPublicKey.crt"); //应用公钥证书路径（app_cert_path 文件绝对路径）
			certAlipayRequest.setAlipayPublicCertPath("C:/java/prod/cert/alipayCertPublicKey_RSA2.crt"); //支付宝公钥证书文件路径（alipay_cert_path 文件绝对路径）
			certAlipayRequest.setRootCertPath("C:/java/prod/cert/alipayRootCert.crt");  //支付宝CA根证书文件路径（alipay_root_cert_path 文件绝对路径）
			alipayClient = new DefaultAlipayClient(certAlipayRequest);
		}
		return alipayClient;
	}

	public String pay(AliPayForm form){
		//实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient(AliUtil.ALIPAY_API, AliUtil.getAppId(), AliUtil.getAppPrivateKey(), FORMAT, CHARSET, AliUtil.getZfbPublicKey(), SIGNTYPE);
		//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody(form.getBody());
		model.setSubject(form.getSubject());
		model.setOutTradeNo(form.getOutTradeNo());
//		model.setTimeoutExpress("30m");
		model.setTotalAmount(NumberUtils.format2(form.getTotalAmount()));
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl(form.getNotifyUrl());
		try {
			//这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
			return response.getBody();
		} catch (AlipayApiException e) {
			LOG.error("调用支付宝失败,失败原因:"+e.getErrMsg());
			e.printStackTrace();
		}
		return null;
	}


	public boolean checkPayNotify(Map<String, String> conversionParams) {
		try {
			return AlipaySignature.rsaCheckV1(conversionParams, AliUtil.getAppPublicKey(), CHARSET, SIGNTYPE);
		} catch (AlipayApiException e) {
			LOG.error("调用支付宝失败,失败原因:"+e.getErrMsg());
			e.printStackTrace();
		}
		return false;
	}


	public String refund(AliRefundForm refundForm) throws ServiceException {
		AlipayClient alipayClient = new DefaultAlipayClient(AliUtil.ALIPAY_API, AliUtil.getAppId(), AliUtil.getAppPrivateKey(), FORMAT, CHARSET, AliUtil.getZfbPublicKey(), SIGNTYPE);

		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		request.setBizContent("{" +
				"\"out_trade_no\":\""+refundForm.getOutTradeNo()+"\"," +
				"\"refund_amount\":"+NumberUtils.format2(refundForm.getRefundAmount())+"," +
				"\"refund_reason\":\"正常退款\"," +
				"\"out_request_no\":\""+refundForm.getOutRefundNo()+"\"" +
				"  }");
		AlipayTradeRefundResponse response = null;
		try {
			response = alipayClient.execute(request);
		} catch (AlipayApiException e) {
			LOG.error("调用支付宝退款失败,失败原因1:"+e);
		}
		if(response != null && response.isSuccess()){
			System.out.println("支付宝退款成功");
			return "SUCCESS";
		} else {
			LOG.error("调用支付宝退款失败,失败原因2:"+response);
			throw new ServiceException("退款失败,失败原因："+response.getSubMsg());
		}

	}

	public String queryTrade(AlipayClient alipayClient, String tradeNo){
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();//创建API对应的request类
		request.setBizContent("{" +
				"    \"trade_no\":\""+tradeNo+"\"}"); //设置业务参数
		AlipayTradeQueryResponse response = null;
		try {
			response = alipayClient.execute(request);
		} catch (AlipayApiException e) {
			return PAY_FAIL;
		}
		if(response != null && response.isSuccess()){
			//查询交易
			return response.getTradeStatus();
		} else {
			return PAY_FAIL;
		}
	}

	public String queryBalance() throws Exception {
		AlipayClient alipayClient = this.getAlipayClient();
		AlipayFundAccountQueryRequest request = new AlipayFundAccountQueryRequest();
		request.setBizContent("{" +
				"  \"alipay_user_id\":\""+AliUtil.getUserId()+"\"," +
				"  \"account_type\":\"ACCTRANS_ACCOUNT\"" +
				"}");
		AlipayFundAccountQueryResponse response = alipayClient.certificateExecute(request);
		log.info("查询支付宝余额返回参数>>"+ JSONUtil.toJsonStr(response));
		if(response.isSuccess()){
			return response.getAvailableAmount();
		} else {
			throw new ServiceException("查询支付宝余额失败");
		}
	}

	public String transferToUser(String orderNo, BigDecimal transAmount, String zfbAccount, String realname) throws AlipayApiException {
		AlipayClient alipayClient = this.getAlipayClient();
		AlipayFundTransUniTransferRequest request = new AlipayFundTransUniTransferRequest();
		request.setBizContent("{" +
				"\"out_biz_no\":\""+orderNo+"\"," +
				"\"trans_amount\":\""+transAmount.toString()+"\"," +
				"\"product_code\":\"TRANS_ACCOUNT_NO_PWD\"," +
				"\"biz_scene\":\"DIRECT_TRANSFER\"," +
				"\"order_title\":\"提现\"," +
				"\"payee_info\":{" +
				"\"identity\":\""+zfbAccount+"\"," +
				"\"identity_type\":\"ALIPAY_LOGON_ID\"," +
				"\"name\":\""+realname+"\"" +
				" },"+
				"\"remark\":\"单笔转账\"" +
				"  }");
		AlipayFundTransUniTransferResponse response = alipayClient.certificateExecute(request);
		log.info("支付宝转账返回参数>>"+ JSONUtil.toJsonStr(response));
		if(response.isSuccess()){
			System.out.println("调用成功");
			if("SUCCESS".equals(response.getStatus())){
				return null;
			}else{
				return response.getSubMsg();
			}
		} else {
			System.out.println("调用失败");
			return response.getSubMsg();
		}
	}
}
