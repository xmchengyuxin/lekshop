package com.chengyu.core.service.pay.wxpay;

import com.chengyu.core.utils.StringUtils;
import com.github.wxpay.sdk.WXPayConfig;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyWxPayConfig implements WXPayConfig{

	private String appId;
	private String mchId;
	private String key;
	
	 /** 加载证书  这里证书需要到微信商户平台进行下载*/
    private byte[] certData;
	
    public MyWxPayConfig(String appId, String mchId, String key, String certPath) throws Exception {
    	this.appId = appId;
    	this.mchId = mchId;
    	this.key = key;
    	if(StringUtils.isNotBlank(certPath)){
	        //证书只是撤销订单时会使用
	        File file = new File(certPath);
	        InputStream certStream = new FileInputStream(file);
	        this.certData = new byte[(int) file.length()];
	        certStream.read(this.certData);
	        certStream.close();
    	}
    }
    
	@Override
	public String getAppID() {
		return appId;
	}

	@Override
	public String getMchID() {
		return mchId;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public InputStream getCertStream() {
		ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
	}

	@Override
	public int getHttpConnectTimeoutMs() {
		return 8000;
	}

	@Override
	public int getHttpReadTimeoutMs() {
		return 10000;
	}

	/**
	*这里的方法，实现必须如下
	*/
    IWXPayDomain getWXPayDomain() {
        IWXPayDomain iwxPayDomain = new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }
            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo("api.mch.weixin.qq.com", true);
            }
        };
        return iwxPayDomain;
    }
}
