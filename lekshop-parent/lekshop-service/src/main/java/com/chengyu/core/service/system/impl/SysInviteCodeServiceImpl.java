package com.chengyu.core.service.system.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.mapper.SysInviteCodeMapper;
import com.chengyu.core.model.SysInviteCode;
import com.chengyu.core.model.SysInviteCodeExample;
import com.chengyu.core.service.system.SysInviteCodeService;
import com.chengyu.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @title  邀请码
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class SysInviteCodeServiceImpl implements SysInviteCodeService {

	@Autowired
	private SysInviteCodeMapper sysInviteCodeMapper;

	@Override
	@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
	public String genInviteCode() {
		String inviteCode = StringUtils.getSixCode();
		while (this.getConfigByInviteCode(inviteCode) != null){
			inviteCode = StringUtils.getSixCode();
		}

		SysInviteCode config = new SysInviteCode();
		config.setInviteCode(inviteCode);
		config.setAddTime(DateUtil.date());
		sysInviteCodeMapper.insertSelective(config);

		return inviteCode;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateUserId(String inviteCode, Integer type, Integer userId) {
		SysInviteCodeExample example = new SysInviteCodeExample();
		example.createCriteria().andInviteCodeEqualTo(inviteCode);

		SysInviteCode updateSys = new SysInviteCode();
		updateSys.setType(type);
		updateSys.setUserId(userId);
		sysInviteCodeMapper.updateByExampleSelective(updateSys, example);
	}

	@Override
	public SysInviteCode getConfigByInviteCode(String inviteCode) {
		SysInviteCodeExample example = new SysInviteCodeExample();
		example.createCriteria().andInviteCodeEqualTo(inviteCode);
		List<SysInviteCode> list = sysInviteCodeMapper.selectByExample(example);
		return CollectionUtil.isEmpty(list) ? null : list.get(0);
	}
}