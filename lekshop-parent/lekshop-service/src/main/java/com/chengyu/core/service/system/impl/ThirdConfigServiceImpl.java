package com.chengyu.core.service.system.impl;

import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.mapper.SysThirdConfigMapper;
import com.chengyu.core.model.SysThirdConfig;
import com.chengyu.core.model.SysThirdConfigExample;
import com.chengyu.core.service.system.ThirdConfigService;
import com.chengyu.core.util.RedisUtil;
import com.chengyu.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ThirdConfigServiceImpl implements ThirdConfigService {
	
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private SysThirdConfigMapper configMapper;

	@Override
	public List<SysThirdConfig> getAllConfig() {
		SysThirdConfigExample example = new SysThirdConfigExample();
		return configMapper.selectByExample(example);
	}

	@Override
	public String getThirdConfigByNid(String nid) {
		String value = (String) redisUtil.getRedisValue(RedisEnums.THIRD_CONFIG.getKey()+"-"+nid);
		if(StringUtils.isBlank(value)){
			SysThirdConfigExample example = new SysThirdConfigExample();
			example.createCriteria().andNidEqualTo(nid);
			List<SysThirdConfig> list = configMapper.selectByExample(example);
			value = list.get(0).getValue();
			redisUtil.setRedisValue(RedisEnums.THIRD_CONFIG.getKey()+"-"+nid, value);
		}
		return value;
	}

	@Override
	public void updateConfig(SysThirdConfig config) {
		configMapper.updateByPrimaryKeySelective(config);
		SysThirdConfig haveConfig = configMapper.selectByPrimaryKey(config.getId());
		redisUtil.delete(RedisEnums.THIRD_CONFIG.getKey()+"-"+haveConfig.getNid());
	}
}