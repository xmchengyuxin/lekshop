package com.chengyu.core.service.system.impl;

import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.mapper.SysWeixinConfigMapper;
import com.chengyu.core.model.SysWeixinConfig;
import com.chengyu.core.model.SysWeixinConfigExample;
import com.chengyu.core.service.system.WeixinConfigService;
import com.chengyu.core.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @title  微信配置
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class WeixinConfigServiceImpl implements WeixinConfigService {
	
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private SysWeixinConfigMapper weixinConfigMapper;

	@Override
	public void addWeixinConfig(SysWeixinConfig config) {
		config.setAddTime(new Date());
		config.setUpdTime(config.getAddTime());
		weixinConfigMapper.insert(config);
		redisUtil.delete(RedisEnums.WX_CONFIG_KEY.getKey());
	}

	@Override
	public void updateWeixinConfig(SysWeixinConfig config) {
		config.setUpdTime(new Date());
		weixinConfigMapper.updateByPrimaryKeySelective(config);
		redisUtil.delete(RedisEnums.WX_CONFIG_KEY.getKey());
	}

	@Override
	public void deleteWeixinConfig(Integer id) {
		weixinConfigMapper.deleteByPrimaryKey(id);
		redisUtil.delete(RedisEnums.WX_CONFIG_KEY.getKey());
	}

	@Override
	public List<SysWeixinConfig> getWeixinConfigList(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		SysWeixinConfigExample example = new SysWeixinConfigExample();
        return weixinConfigMapper.selectByExample(example);
	}

	@Override
	public SysWeixinConfig getWeixinConfig() {
		SysWeixinConfig config = (SysWeixinConfig)redisUtil.getRedisValue(RedisEnums.WX_CONFIG_KEY.getKey());
		if(config != null) {
			return config;
		}
		
		SysWeixinConfigExample example = new SysWeixinConfigExample();
		List<SysWeixinConfig> configList = weixinConfigMapper.selectByExample(example);
		if(configList == null || configList.size() <= 0) {
			return null;
		}
		
		config = configList.get(0);
		redisUtil.setRedisValue(RedisEnums.WX_CONFIG_KEY.getKey(), config);
		return config;
	}

}