package com.chengyu.core.util.jiguang;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.SysJiguangConfig;
import com.chengyu.core.service.system.JiguangConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class JiguangUtil {
	
	@Autowired 
	private JiguangConfigService jiguangService;
	
	private static SysJiguangConfig config;
	
	@PostConstruct
    public void init() throws ServiceException {
		config = jiguangService.getJiguangConfig();
	}
	
	public static String getAppKey() {
		return config.getAppKey();
	}
	
	public static String getAppSecret() {
		return config.getAppSecret();
	}
	
}