package com.chengyu.core.service.walk.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.mapper.WalkMemberMapper;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.WalkMember;
import com.chengyu.core.model.WalkMemberExample;
import com.chengyu.core.service.walk.WalkMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WalkMemberServiceImpl implements WalkMemberService {

	@Autowired
	private WalkMemberMapper walkMemberMapper;

	@Override
	@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addWalkMember(UmsMember member) {
		WalkMember walkMember = this.getWalkMemberByMember(member.getId());
		if(walkMember == null){
			walkMember = new WalkMember();
			walkMember.setUid(member.getUid()*6);
			walkMember.setMemberId(member.getId());
			walkMember.setMemberName(member.getCode());
			walkMember.setMemberHeadImg(member.getHeadImg());
			walkMember.setType(CommonConstant.BUYER);
			walkMember.setGender(member.getGender());
			walkMember.setNickname(member.getNickname());
			walkMember.setBirthday(member.getBirthday());
			walkMember.setAge(member.getAge());
			walkMember.setPersonSign(member.getPersonSign());
			walkMember.setStatus(CommonConstant.YES_INT);
			walkMember.setAddTime(DateUtil.date());
			walkMember.setUpdTime(walkMember.getAddTime());
			walkMemberMapper.insertSelective(walkMember);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateWalkMember(WalkMember walkMember) {
		walkMember.setUpdTime(DateUtil.date());
		walkMemberMapper.updateByPrimaryKeySelective(walkMember);
	}

	@Override
	public WalkMember getWalkMemberByMember(Integer memberId) {
		WalkMemberExample example = new WalkMemberExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		List<WalkMember> list = walkMemberMapper.selectByExample(example);
		return CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
	}
}