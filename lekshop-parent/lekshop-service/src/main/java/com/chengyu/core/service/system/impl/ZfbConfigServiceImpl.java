package com.chengyu.core.service.system.impl;

import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.mapper.SysZfbConfigMapper;
import com.chengyu.core.model.SysZfbConfig;
import com.chengyu.core.model.SysZfbConfigExample;
import com.chengyu.core.service.system.ZfbConfigService;
import com.chengyu.core.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @title  支付宝配置
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class ZfbConfigServiceImpl implements ZfbConfigService {
	

	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private SysZfbConfigMapper zfbConfigMapper;

	@Override
	public void addZfbConfig(SysZfbConfig config) {
		config.setAddTime(new Date());
		config.setUpdTime(config.getAddTime());
		zfbConfigMapper.insert(config);
		redisUtil.delete(RedisEnums.ZFB_CONFIG_KEY.getKey());
	}

	@Override
	public void updateZfbConfig(SysZfbConfig config) {
		config.setUpdTime(new Date());
		zfbConfigMapper.updateByPrimaryKeySelective(config);
		redisUtil.delete(RedisEnums.ZFB_CONFIG_KEY.getKey());
    }

	@Override
	public void deleteZfbConfig(Integer id) {
		zfbConfigMapper.deleteByPrimaryKey(id);
		redisUtil.delete(RedisEnums.ZFB_CONFIG_KEY.getKey());
	}

	@Override
	public List<SysZfbConfig> getZfbConfigList(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		SysZfbConfigExample example = new SysZfbConfigExample();
        return zfbConfigMapper.selectByExample(example);
	}

	@Override
	public SysZfbConfig getZfbConfig() {
		SysZfbConfig config = (SysZfbConfig)redisUtil.getRedisValue(RedisEnums.ZFB_CONFIG_KEY.getKey());
		if(config != null) {
			return config;
		}
		
		SysZfbConfigExample example = new SysZfbConfigExample();
		List<SysZfbConfig> configList = zfbConfigMapper.selectByExampleWithBLOBs(example);
		if(configList == null || configList.size() <= 0) {
			return null;
		}
		
		config = configList.get(0);
		redisUtil.setRedisValue(RedisEnums.ZFB_CONFIG_KEY.getKey(), config);
		return config;
	}
	
}