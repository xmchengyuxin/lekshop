package com.chengyu.core.service.system.impl;

import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.mapper.SysAlismsConfigMapper;
import com.chengyu.core.model.SysAlismsConfig;
import com.chengyu.core.model.SysAlismsConfigExample;
import com.chengyu.core.service.system.AlismsConfigService;
import com.chengyu.core.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @title  阿里云短信
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class AlismsConfigServiceImpl implements AlismsConfigService {
	
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private SysAlismsConfigMapper alismsConfigMapper;

	@Override
	public void addAlismsConfig(SysAlismsConfig config) {
		config.setAddTime(new Date());
		config.setUpdTime(config.getAddTime());
		alismsConfigMapper.insert(config);
		redisUtil.delete(RedisEnums.ALISMS_KEY.getKey());
	}

	@Override
	public void updateAlismsConfig(SysAlismsConfig config) {
		config.setUpdTime(new Date());
		alismsConfigMapper.updateByPrimaryKeySelective(config);
		redisUtil.delete(RedisEnums.ALISMS_KEY.getKey());
    }

	@Override
	public void deleteAlismsConfig(Integer id) {
		alismsConfigMapper.deleteByPrimaryKey(id);
		redisUtil.delete(RedisEnums.ALISMS_KEY.getKey());
	}

	@Override
	public List<SysAlismsConfig> getAlismsConfigList(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		SysAlismsConfigExample example = new SysAlismsConfigExample();
        return alismsConfigMapper.selectByExample(example);
	}

	@Override
	public SysAlismsConfig getAlismsConfig() {
		SysAlismsConfig config = (SysAlismsConfig)redisUtil.getRedisValue(RedisEnums.ALISMS_KEY.getKey());
		if(config != null) {
			return config;
		}
		
		SysAlismsConfigExample example = new SysAlismsConfigExample();
		List<SysAlismsConfig> configList = alismsConfigMapper.selectByExample(example);
		if(configList == null || configList.size() <= 0) {
			return null;
		}
		
		config = configList.get(0);
		redisUtil.setRedisValue(RedisEnums.ALISMS_KEY.getKey(), config);
		return config;
	}
	
}