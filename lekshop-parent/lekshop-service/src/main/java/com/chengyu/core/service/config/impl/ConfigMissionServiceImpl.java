package com.chengyu.core.service.config.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.mapper.ConfigMissionDetailMapper;
import com.chengyu.core.mapper.ConfigMissionMapper;
import com.chengyu.core.model.ConfigMission;
import com.chengyu.core.model.ConfigMissionDetail;
import com.chengyu.core.model.ConfigMissionDetailExample;
import com.chengyu.core.model.ConfigMissionExample;
import com.chengyu.core.service.config.ConfigMissionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @title  佣金组
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class ConfigMissionServiceImpl implements ConfigMissionService {
	
	@Autowired
	private ConfigMissionMapper configMissionMapper;
	@Autowired
	private ConfigMissionDetailMapper configMissionDetailMapper;

	@Override
	public List<ConfigMission> getConfigMissionList(Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		ConfigMissionExample example = new ConfigMissionExample();
		example.setOrderByClause("sort asc");
		return configMissionMapper.selectByExample(example);
	}

	@Override
	public List<ConfigMission> getAllConfigMission() {
		ConfigMissionExample example = new ConfigMissionExample();
		example.setOrderByClause("sort asc");
		example.createCriteria().andStatusEqualTo(CommonConstant.YES_INT);
		return configMissionMapper.selectByExample(example);
	}

	@Override
	public ConfigMission getDefaultConfigMission() {
		ConfigMissionExample example = new ConfigMissionExample();
		example.setOrderByClause("sort asc");
		example.createCriteria().andStatusEqualTo(CommonConstant.YES_INT).andDefaultStatusEqualTo(CommonConstant.YES_INT);
		List<ConfigMission> list = configMissionMapper.selectByExample(example);
		return CollectionUtil.isEmpty(list) ? null : list.get(0);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addConfigMission(ConfigMission config) {
		//判断是否有默认佣金组
		ConfigMission defaultConfig = this.getDefaultConfigMission();
		if (defaultConfig == null){
			config.setDefaultStatus(CommonConstant.YES_INT);
		}
		if(config.getDefaultStatus() == CommonConstant.YES_INT){
			//更新其他佣金组为非默认
			ConfigMissionExample example = new ConfigMissionExample();
			example.createCriteria().andDefaultStatusEqualTo(CommonConstant.YES_INT);
			ConfigMission updateConfig = new ConfigMission();
			updateConfig.setDefaultStatus(CommonConstant.NO_INT);
			configMissionMapper.updateByExampleSelective(updateConfig, example);
		}

		config.setUpdTime(DateUtil.date());
		if(config.getId() == null){
			config.setAddTime(config.getUpdTime());
			configMissionMapper.insertSelective(config);
		}else{
			configMissionMapper.updateByPrimaryKeySelective(config);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteConfigMission(Integer id) {
		configMissionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public ConfigMissionDetail getConfigMissionDetail(Integer missionConfigId, Integer groupId) {
		if(missionConfigId == null || groupId == null){
			return null;
		}
		ConfigMissionDetailExample example = new ConfigMissionDetailExample();
		example.createCriteria().andMissionConfigIdEqualTo(missionConfigId).andGroupIdEqualTo(groupId);
		List<ConfigMissionDetail> list = configMissionDetailMapper.selectByExample(example);
		return CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addConfigMissionDetail(ConfigMissionDetail detail) {
		detail.setUpdTime(DateUtil.date());
		if(detail.getId() == null){
			detail.setAddTime(detail.getUpdTime());
			configMissionDetailMapper.insertSelective(detail);
		}else{
			configMissionDetailMapper.updateByPrimaryKeySelective(detail);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateParams(ConfigMission config) {
		config.setUpdTime(DateUtil.date());
		configMissionMapper.updateByPrimaryKeySelective(config);
	}
}