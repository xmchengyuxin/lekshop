package com.chengyu.core.service.system.impl;

import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.mapper.SysJiguangConfigMapper;
import com.chengyu.core.model.SysJiguangConfig;
import com.chengyu.core.model.SysJiguangConfigExample;
import com.chengyu.core.service.system.JiguangConfigService;
import com.chengyu.core.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JiguangConfigServiceImpl implements JiguangConfigService {
	
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private SysJiguangConfigMapper jiguangConfigMapper;

	@Override
	public void addJiguangConfig(SysJiguangConfig config) {
		config.setAddTime(new Date());
		config.setUpdTime(config.getAddTime());
		jiguangConfigMapper.insert(config);
		redisUtil.delete(RedisEnums.JIGUANG_CONFIG_KEY.getKey());
	}

	@Override
	public void updateJiguangConfig(SysJiguangConfig config) {
		config.setUpdTime(new Date());
		jiguangConfigMapper.updateByPrimaryKeySelective(config);
		redisUtil.delete(RedisEnums.JIGUANG_CONFIG_KEY.getKey());
	}

	@Override
	public void deleteJiguangConfig(Integer id) {
		jiguangConfigMapper.deleteByPrimaryKey(id);
		redisUtil.delete(RedisEnums.JIGUANG_CONFIG_KEY.getKey());
	}

	@Override
	public List<SysJiguangConfig> getJiguangConfigList(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		SysJiguangConfigExample example = new SysJiguangConfigExample();
        return jiguangConfigMapper.selectByExample(example);
	}

	@Override
	public SysJiguangConfig getJiguangConfig() {
		List<SysJiguangConfig> configList = this.getAllJiguangConfigs();
		if(configList == null || configList.isEmpty()) {
			return null;
		}
		return configList.get(0);
	}
	
	@Override
    @SuppressWarnings("unchecked")
	public List<SysJiguangConfig> getAllJiguangConfigs() {
		List<SysJiguangConfig> configList = (List<SysJiguangConfig>)redisUtil.getRedisValue(RedisEnums.JIGUANG_CONFIG_KEY.getKey());
		if(configList != null && configList.size() > 0) {
			return configList;
		}

		SysJiguangConfigExample example = new SysJiguangConfigExample();
		configList = jiguangConfigMapper.selectByExample(example);
		if(configList == null || configList.size() <= 0) {
			return null;
		}

		redisUtil.setRedisValue(RedisEnums.JIGUANG_CONFIG_KEY.getKey(), configList);
		return configList;
	}

}