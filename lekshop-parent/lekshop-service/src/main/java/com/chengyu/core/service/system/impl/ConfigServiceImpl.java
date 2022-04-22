package com.chengyu.core.service.system.impl;

import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.SysConfigMapper;
import com.chengyu.core.model.SysConfig;
import com.chengyu.core.model.SysConfigExample;
import com.chengyu.core.service.system.ConfigService;
import com.chengyu.core.util.RedisUtil;
import com.chengyu.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title  系统参数配置
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class ConfigServiceImpl implements ConfigService {
	
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private SysConfigMapper configMapper;
	
	@Override
	public void addConfig(SysConfig config) {
		configMapper.insertSelective(config);
		String key = RedisEnums.CONFIG_KEY.getKey();
		redisUtil.delete(key, key+"-map");
	}

	@Override
	public void updateConfig(SysConfig config) {
		configMapper.updateByPrimaryKeySelective(config);
		String key = RedisEnums.CONFIG_KEY.getKey();
		redisUtil.delete(key, key+"-map");
	}

	@Override
	public void updateConfig(List<SysConfig> configList) {
		if(configList == null || configList.size() <= 0) {
			return;
		}
		
		for(SysConfig config : configList) {
			if(config.getId() == null) {
				configMapper.insertSelective(config);
			} else {
				configMapper.updateByPrimaryKeySelective(config);
			}
		}
		String key = RedisEnums.CONFIG_KEY.getKey();
		redisUtil.delete(key, key+"-map");
	}

	@Override
	public void deleteConfigByNid(String nid) {
		SysConfigExample example = new SysConfigExample();
		example.createCriteria().andNidEqualTo(nid);
		configMapper.deleteByExample(example);
		String key = RedisEnums.CONFIG_KEY.getKey();
		redisUtil.delete(key, key+"-map");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SysConfig> getAllConfigs() {
		String key = RedisEnums.CONFIG_KEY.getKey();
		List<SysConfig> configList = (List<SysConfig>)redisUtil.getRedisValue(key);
		if(configList != null && configList.size() > 0) {
			return configList;
		}
		
		SysConfigExample example = new SysConfigExample();
		configList = configMapper.selectByExample(example);
		if(configList == null || configList.size() <= 0) {
			return null;
		}
		redisUtil.setRedisValue(key, configList);
		return configList;
	}

	@Override
	public String getValueByNid(String nid) {
		SysConfig config = getAllConfigMap().get(nid);
		return config == null ? "" : config.getValue();
	}

	@Override
	public SysConfig getConfigByNid(String nid) {
		return getAllConfigMap().get(nid);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, SysConfig> getAllConfigMap() {
		String key = RedisEnums.CONFIG_KEY.getKey();
		Map<String, SysConfig> configMap = (Map<String, SysConfig>)redisUtil.getRedisValue(key+"-map");
		if(configMap != null && configMap.size() > 0) {
			return configMap;
		}
		
		List<SysConfig> configList = getAllConfigs();
		if(configList != null && configList.size() > 0) {
			configMap = new HashMap<>(16);
			for(SysConfig config : configList) {
				configMap.put(config.getNid(), config);
			}
		}
		
		redisUtil.setRedisValue(key+"-map", configMap);
		return configMap;
	}
	
	@Override
	public Map<String, String> getAllConfigValueMap() {
		Map<String, SysConfig> configMap = getAllConfigMap();
		Map<String, String> resultMap = new HashMap<>(16);
		for(String key : configMap.keySet()) {
			resultMap.put(key, configMap.get(key).getValue());
		}
		
		return resultMap;
	}
	
	@Override
	public Map<String, String> getAllConfigValueMapByNoCache() {
		List<SysConfig> list = getAllConfigsByNoCache();
		Map<String, String> resultMap = new HashMap<>(16);
		for(SysConfig config : list) {
			resultMap.put(config.getNid(), config.getValue());
		}
		
		return resultMap;
	}

	@Override
	public void validateWeihuOnlyLogin() throws ServiceException {
		String value = this.getValueByNid("weihu_no_login");
		if(StringUtils.isNotBlank(value) && CommonConstant.YES.equals(value)){
			throw new ServiceException("网站维护中, 暂无法进行其他操作");
		}
	}

	@Override
	public void validateWeihuNoTakeTask() throws ServiceException {
		String value = this.getValueByNid("weihu_no_take_task");
		if(StringUtils.isNotBlank(value) && CommonConstant.YES.equals(value)){
			throw new ServiceException("网站维护中, 暂无法进行接单");
		}
	}

	@Override
	public boolean isPlatformRight(String platform) {
		String currentPlatform = this.getValueByNid("platform_ind");
		return StringUtils.isNotBlank(currentPlatform) && platform.equals(currentPlatform);
	}

	@Override
	public List<SysConfig> getAllConfigsByNoCache() {
		SysConfigExample example = new SysConfigExample();
		return configMapper.selectByExample(example);
	}
}