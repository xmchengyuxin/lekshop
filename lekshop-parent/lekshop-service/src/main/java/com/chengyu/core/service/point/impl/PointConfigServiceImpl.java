package com.chengyu.core.service.point.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.mapper.PointConfigMapper;
import com.chengyu.core.model.PointConfig;
import com.chengyu.core.model.PointConfigExample;
import com.chengyu.core.service.point.PointConfigService;
import com.chengyu.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PointConfigServiceImpl implements PointConfigService {
	
	@Autowired
	private PointConfigMapper pointConfigMapper;
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public PointConfig getPointConfig() {
		PointConfig config = (PointConfig) redisUtil.getRedisValue(RedisEnums.POINT_CONFIG.getKey());
		if(config != null){
			return config;
		}
		PointConfigExample example = new PointConfigExample();
		List<PointConfig> list = pointConfigMapper.selectByExample(example);
		config = CollectionUtil.isEmpty(list) ? null : list.get(0);
		if(config != null){
			redisUtil.setRedisValue(RedisEnums.POINT_CONFIG.getKey(), config);
		}
		return config;
	}

	@Override
	public PointConfig getPointConfigNoCache() {
		PointConfigExample example = new PointConfigExample();
		List<PointConfig> list = pointConfigMapper.selectByExample(example);
		return CollectionUtil.isEmpty(list) ? null : list.get(0);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addPointConfig(PointConfig config) {
		config.setUpdTime(DateUtil.date());
		if(config.getId() == null){
			config.setAddTime(config.getUpdTime());
			pointConfigMapper.insertSelective(config);
		}else{
			pointConfigMapper.updateByPrimaryKeySelective(config);
		}
		redisUtil.delete(RedisEnums.POINT_CONFIG.getKey());
	}
}