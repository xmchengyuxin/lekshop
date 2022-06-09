package com.chengyu.core.util.ali;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.SysZfbConfig;
import com.chengyu.core.service.system.ZfbConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AliUtil {
	
	public static final String ALIPAY_API="https://openapi.alipay.com/gateway.do";
	
	@Autowired 
	private ZfbConfigService zfbService;
	
	public static SysZfbConfig config;
	
	@PostConstruct
    public void init() {
		config = zfbService.getZfbConfig();
	}
	
	public static String getAppId() {
		return config.getAppId();
	}
	
	public static String getZfbPublicKey() {
		return config.getZfbPublicKey();
	}
	
	public static String getAppPublicKey() {
		return config.getAppPublicKey();
	}
	
	public static String getAppPrivateKey() {
		return config.getAppPrivateKey();
	}

	public static String getUserId() {
		return "";
	}
	
}