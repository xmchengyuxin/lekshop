package com.chengyu.core.service.config.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.mapper.ConfigWithdrawMapper;
import com.chengyu.core.model.ConfigWithdraw;
import com.chengyu.core.model.ConfigWithdrawExample;
import com.chengyu.core.service.config.ConfigWithdrawService;
import com.chengyu.core.service.member.MemberGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @title  会员组提现设置
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class ConfigWithdrawServiceImpl implements ConfigWithdrawService {
	
	@Autowired
	private ConfigWithdrawMapper configWithdrawMapper;
	@Autowired
	private MemberGroupService memberGroupService;

	@Override
	public ConfigWithdraw getConfigWithdrawByGroupId(Integer groupId) {
		if(groupId == null){
			return null;
		}
		ConfigWithdrawExample example = new ConfigWithdrawExample();
		example.createCriteria().andGroupIdEqualTo(groupId);
		List<ConfigWithdraw> list = configWithdrawMapper.selectByExample(example);
		return CollectionUtil.isEmpty(list) ? null : list.get(0);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addConfigWithdraw(ConfigWithdraw config) {
		config.setUpdTime(DateUtil.date());
		config.setGroupName(memberGroupService.getGroupName(config.getGroupId()));
		if(config.getId() == null){
			config.setAddTime(config.getUpdTime());
			configWithdrawMapper.insertSelective(config);
		}else {
			configWithdrawMapper.updateByPrimaryKeySelective(config);
		}
	}
}