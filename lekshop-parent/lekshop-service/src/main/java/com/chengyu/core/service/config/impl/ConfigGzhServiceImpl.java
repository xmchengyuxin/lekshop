package com.chengyu.core.service.config.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.mapper.ConfigGzhMapper;
import com.chengyu.core.model.ConfigGzh;
import com.chengyu.core.model.ConfigGzhExample;
import com.chengyu.core.service.config.ConfigGzhService;
import com.chengyu.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @title  公众号接口
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class ConfigGzhServiceImpl implements ConfigGzhService {
	
	@Autowired
	private ConfigGzhMapper configGzhMapper;
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public ConfigGzh getGzh() {
		ConfigGzh config = (ConfigGzh) redisUtil.getRedisValue(RedisEnums.GZH_CONFIG.getKey());
		if(config != null){
			return config;
		}
		ConfigGzhExample example = new ConfigGzhExample();
		List<ConfigGzh> list = configGzhMapper.selectByExample(example);
		config = CollectionUtil.isEmpty(list) ? null : list.get(0);
		if(config != null){
			redisUtil.setRedisValue(RedisEnums.GZH_CONFIG.getKey(), config);
		}
		return config;
	}

	@Override
	public ConfigGzh getGzhNoCache() {
		ConfigGzhExample example = new ConfigGzhExample();
		List<ConfigGzh> list = configGzhMapper.selectByExampleWithBLOBs(example);
		return CollectionUtil.isEmpty(list) ? null : list.get(0);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addConfigGzh(ConfigGzh gzh) {
		gzh.setUpdTime(DateUtil.date());
		if(gzh.getId() == null){
			gzh.setAddTime(gzh.getUpdTime());
			configGzhMapper.insertSelective(gzh);
		}else{
			configGzhMapper.updateByPrimaryKeyWithBLOBs(gzh);
		}
		redisUtil.delete(RedisEnums.GZH_CONFIG.getKey());
	}
}