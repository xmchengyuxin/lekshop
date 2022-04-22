package com.chengyu.core.service.system.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.mapper.SysBankConfigMapper;
import com.chengyu.core.model.SysBankConfig;
import com.chengyu.core.model.SysBankConfigExample;
import com.chengyu.core.service.system.BankConfigService;
import com.chengyu.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankConfigServiceImpl implements BankConfigService {
	
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private SysBankConfigMapper bankConfigMapper;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SysBankConfig> getBankList() {
		List<SysBankConfig> configList = (List<SysBankConfig>)redisUtil.getRedisValue(RedisEnums.BANK_CONFIG_KEY.getKey());
		if(CollectionUtil.isNotEmpty(configList)) {
			return configList;
		}
			
		SysBankConfigExample example = new SysBankConfigExample();
		example.setOrderByClause("sort asc");
		configList = bankConfigMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(configList)) {
			return null;
		}
		
		redisUtil.setRedisValue(RedisEnums.BANK_CONFIG_KEY.getKey(), configList);
		return configList;
	}

	@Override
	public String getBankNo(String bank) {
		List<SysBankConfig> configList = this.getBankList();
		if(configList != null && !configList.isEmpty()){
			for(SysBankConfig config : configList){
				if(bank.equals(config.getBank())){
					return config.getBankNo();
				}
			}
		}
		return null;
	}

	@Override
	public String getBankName(String bank) {
		List<SysBankConfig> configList = this.getBankList();
		if(configList != null && !configList.isEmpty()){
			for(SysBankConfig config : configList){
				if(bank.equals(config.getBank())){
					return config.getBankName();
				}
			}
		}
		return null;
	}

}