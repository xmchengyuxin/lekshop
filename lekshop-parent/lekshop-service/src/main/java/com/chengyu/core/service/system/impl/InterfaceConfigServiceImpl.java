package com.chengyu.core.service.system.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.domain.result.InterfaceConfig;
import com.chengyu.core.mapper.SysInterfaceConfigMapper;
import com.chengyu.core.model.SysInterfaceConfig;
import com.chengyu.core.model.SysInterfaceConfigExample;
import com.chengyu.core.service.system.InterfaceConfigService;
import com.chengyu.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterfaceConfigServiceImpl implements InterfaceConfigService {
	

	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private SysInterfaceConfigMapper interfaceConfigMapper;


	@Override
	public SysInterfaceConfig getConfig(String nid) {
		SysInterfaceConfig config = (SysInterfaceConfig) redisUtil.getRedisValue(RedisEnums.INTERFACE_CONFIG_KEY.getKey()+"-"+nid);
		if(config != null){
			return config;
		}
		config = this.getConfigNoCache(nid);
		redisUtil.setRedisValue(RedisEnums.INTERFACE_CONFIG_KEY.getKey()+"-"+nid, config);
		return config;
	}

	@Override
	public SysInterfaceConfig getConfigNoCache(String nid) {
		SysInterfaceConfigExample example = new SysInterfaceConfigExample();
		example.createCriteria().andNidEqualTo(nid);
		List<SysInterfaceConfig> list = interfaceConfigMapper.selectByExampleWithBLOBs(example);
		return CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
	}

	@Override
	public void updateConfig(SysInterfaceConfig config) {
		config.setUpdTime(DateUtil.date());
		if(config.getId() != null){
			interfaceConfigMapper.updateByPrimaryKeyWithBLOBs(config);
		}else{
			config.setAddTime(config.getUpdTime());
			interfaceConfigMapper.insertSelective(config);
		}
	}

	@Override
	public InterfaceConfig getParams(String nid) {
		SysInterfaceConfig config = this.getConfig(nid);
		return JSONUtil.toBean(config.getContent(), InterfaceConfig.class);
	}
}