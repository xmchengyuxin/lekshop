package com.chengyu.core.service.pay.alipay;

import cn.hutool.json.JSONUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundAccountQueryRequest;
import com.alipay.api.request.AlipayFundTransUniTransferRequest;
import com.alipay.api.response.AlipayFundAccountQueryResponse;
import com.alipay.api.response.AlipayFundTransUniTransferResponse;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.SysZfbConfig;
import com.chengyu.core.service.system.ZfbConfigService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Log4j2
@Component
public class AliPay {

	protected static final Logger LOG = LoggerFactory.getLogger(AliPay.class);
	
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 加密类型
    public static String SIGNTYPE = "RSA2";

    private AlipayClient alipayClient;

	@Autowired
	private ZfbConfigService zfbConfigService;

	private SysZfbConfig getConfig(){
		return zfbConfigService.getZfbConfig();
	}

	private AlipayClient getAlipayClient(SysZfbConfig config) throws AlipayApiException {
		if(alipayClient == null){
			CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
			certAlipayRequest.setServerUrl("https://openapi.alipay.com/gateway.do");  //gateway:支付宝网关（固定）https://openapi.alipay.com/gateway.do
			certAlipayRequest.setAppId(config.getAppId());  //APPID 即创建应用后生成,详情见创建应用并获取 APPID
			certAlipayRequest.setPrivateKey(config.getAppPrivateKey());  //开发者应用私钥，由开发者自己生成
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

	public String queryBalance() throws Exception {
		SysZfbConfig config = this.getConfig();
		if(config == null || config.getAppId() == null){
			throw new ServiceException("您尚未开通支付宝转账功能, 详询软件服务商");
		}
		AlipayClient alipayClient = this.getAlipayClient(config);
		AlipayFundAccountQueryRequest request = new AlipayFundAccountQueryRequest();
		request.setBizContent("{" +
				"  \"alipay_user_id\":\""+config.getAddBy()+"\"," +
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
		SysZfbConfig config = this.getConfig();
		AlipayClient alipayClient = this.getAlipayClient(config);
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
