package com.chengyu.core.service.system.impl;

import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.mapper.SysApproveConfigMapper;
import com.chengyu.core.model.SysApproveConfig;
import com.chengyu.core.model.SysApproveConfigExample;
import com.chengyu.core.service.system.ApproveConfigService;
import com.chengyu.core.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @title  认证配置
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class ApproveConfigServiceImpl implements ApproveConfigService {
	

	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private SysApproveConfigMapper approveConfigMapper;

	@Override
	public void addApproveConfig(SysApproveConfig config) {
		config.setAddTime(new Date());
		config.setUpdTime(config.getAddTime());
		approveConfigMapper.insert(config);
		redisUtil.delete(RedisEnums.APPROVE_CONFIG_KEY.getKey());
	}

	@Override
	public void updateApproveConfig(SysApproveConfig config) {
		config.setUpdTime(new Date());
		approveConfigMapper.updateByPrimaryKeySelective(config);
		redisUtil.delete(RedisEnums.APPROVE_CONFIG_KEY.getKey());
    }

	@Override
	public void deleteApproveConfig(Integer id) {
		approveConfigMapper.deleteByPrimaryKey(id);
		redisUtil.delete(RedisEnums.APPROVE_CONFIG_KEY.getKey());
	}

	@Override
	public List<SysApproveConfig> getApproveConfigList(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		SysApproveConfigExample example = new SysApproveConfigExample();
        return approveConfigMapper.selectByExample(example);
	}

	@Override
	public SysApproveConfig getApproveConfig() {
		SysApproveConfig config = (SysApproveConfig)redisUtil.getRedisValue(RedisEnums.APPROVE_CONFIG_KEY.getKey());
		if(config != null) {
			return config;
		}
		
		SysApproveConfigExample example = new SysApproveConfigExample();
		List<SysApproveConfig> configList = approveConfigMapper.selectByExample(example);
		if(configList == null || configList.size() <= 0) {
			return null;
		}
		
		config = configList.get(0);
		redisUtil.setRedisValue(RedisEnums.APPROVE_CONFIG_KEY.getKey(), config);
		return config;
	}
	
}