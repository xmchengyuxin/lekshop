package com.chengyu.core.service.system;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.SysBankConfig;

import java.util.List;

public interface BankConfigService {
	
	public List<SysBankConfig> getBankList() throws ServiceException;
	
	public String getBankNo(String bank) throws ServiceException;
	
	public String getBankName(String bank) throws ServiceException;
}