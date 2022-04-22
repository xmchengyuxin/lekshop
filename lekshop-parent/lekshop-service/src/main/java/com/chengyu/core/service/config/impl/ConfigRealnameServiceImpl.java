package com.chengyu.core.service.config.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.mapper.ConfigRealnameMapper;
import com.chengyu.core.model.ConfigRealname;
import com.chengyu.core.model.ConfigRealnameExample;
import com.chengyu.core.service.config.ConfigRealnameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @title  会员组实名认证设置
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class ConfigRealnameServiceImpl implements ConfigRealnameService {
	
	@Autowired
	private ConfigRealnameMapper configRealnameMapper;

	@Override
	public ConfigRealname getConfigRealnameByGroupId(Integer groupId) {
		ConfigRealnameExample example = new ConfigRealnameExample();
		example.createCriteria().andGroupIdEqualTo(groupId);
		List<ConfigRealname> list = configRealnameMapper.selectByExample(example);
		return CollectionUtil.isEmpty(list) ? null : list.get(0);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addConfigRealname(ConfigRealname config) {
		config.setUpdTime(DateUtil.date());
		if(config.getId() == null){
			config.setAddTime(config.getUpdTime());
			configRealnameMapper.insertSelective(config);
		}else {
			configRealnameMapper.updateByPrimaryKeySelective(config);
		}
	}
}