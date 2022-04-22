package com.chengyu.core.service.config.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.mapper.ConfigOrderMapper;
import com.chengyu.core.model.ConfigOrder;
import com.chengyu.core.model.ConfigOrderExample;
import com.chengyu.core.service.config.ConfigOrderService;
import com.chengyu.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @title  订单配置接口
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class ConfigOrderServiceImpl implements ConfigOrderService {
	
	@Autowired
	private ConfigOrderMapper configOrderMapper;
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public ConfigOrder getConfigOrder() {
		ConfigOrder config = (ConfigOrder) redisUtil.getRedisValue(RedisEnums.CONFIG_ORDER.getKey());
		if(config != null){
			return config;
		}
		ConfigOrderExample example = new ConfigOrderExample();
		List<ConfigOrder> list = configOrderMapper.selectByExample(example);
		config = CollectionUtil.isEmpty(list) ? null : list.get(0);
		if(config != null){
			redisUtil.setRedisValue(RedisEnums.CONFIG_ORDER.getKey(), config);
		}
		return config;
	}

	@Override
	public ConfigOrder getConfigOrderNoCache() {
		ConfigOrderExample example = new ConfigOrderExample();
		List<ConfigOrder> list = configOrderMapper.selectByExample(example);
		return CollectionUtil.isEmpty(list) ? null : list.get(0);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addConfigOrder(ConfigOrder config) {
		config.setUpdTime(DateUtil.date());
		if(config.getId() == null){
			config.setAddTime(config.getUpdTime());
			configOrderMapper.insertSelective(config);
		}else{
			configOrderMapper.updateByPrimaryKeySelective(config);
		}
		redisUtil.delete(RedisEnums.CONFIG_ORDER.getKey());
	}
}