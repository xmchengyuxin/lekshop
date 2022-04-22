package com.chengyu.core.service.member.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.mapper.UmsMemberGroupMapper;
import com.chengyu.core.mapper.UmsMemberMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.service.member.MemberAccountService;
import com.chengyu.core.service.member.MemberGroupService;
import com.chengyu.core.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @title  会员组别
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class MemberGroupServiceImpl implements MemberGroupService {
	
	@Autowired
	private UmsMemberGroupMapper memberGroupMapper;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private UmsMemberMapper memberMapper;
	@Autowired
	private MemberAccountService accountService;

	@Override
	public List<UmsMemberGroup> getMemberGroupList(Integer type, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		UmsMemberGroupExample example = new UmsMemberGroupExample();
		example.setOrderByClause("sort asc");
		UmsMemberGroupExample.Criteria criteria = example.createCriteria();
		if(type != null){
			criteria.andTypeEqualTo(type);
		}
		return memberGroupMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addMemberGroup(UmsMemberGroup group) {
		//判断是否有默认会员组
		UmsMemberGroup defaultGroup;
		if(group.getType() == CommonConstant.BUYER){
			defaultGroup = this.getDefaultBuyerGroup();
		}else {
			defaultGroup = this.getDefaultSellerGroup();
		}
		if (defaultGroup == null){
			group.setDefaultStatus(CommonConstant.YES_INT);
		}
		if(group.getDefaultStatus() == CommonConstant.YES_INT){
			//更新其他会员组为非默认
			UmsMemberGroupExample example = new UmsMemberGroupExample();
			example.createCriteria().andDefaultStatusEqualTo(CommonConstant.YES_INT).andTypeEqualTo(group.getType());
			UmsMemberGroup updateGroup = new UmsMemberGroup();
			updateGroup.setDefaultStatus(CommonConstant.NO_INT);
			memberGroupMapper.updateByExampleSelective(updateGroup, example);
		}

		group.setUpdTime(DateUtil.date());
		if(group.getId() == null){
			group.setAddTime(group.getAddTime());
			memberGroupMapper.insertSelective(group);
		}else{
			memberGroupMapper.updateByPrimaryKeySelective(group);

			//统一更新用户的会员组名称
			UmsMemberExample example = new UmsMemberExample();
			example.createCriteria().andGroupIdEqualTo(group.getId());
			UmsMember updateMember = new UmsMember();
			updateMember.setGroupName(group.getName());
			memberMapper.updateByExampleSelective(updateMember, example);
		}

		redisUtil.delete(
				RedisEnums.MEMBER_GROUP_KEY.getKey(),
				RedisEnums.MEMBER_GROUP_KEY.getKey()+"-"+CommonConstant.BUYER,
				RedisEnums.MEMBER_GROUP_KEY.getKey()+"-"+CommonConstant.SELLER);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteGroup(Integer groupId) {
		memberGroupMapper.deleteByPrimaryKey(groupId);
		redisUtil.delete(
				RedisEnums.MEMBER_GROUP_KEY.getKey(),
				RedisEnums.MEMBER_GROUP_KEY.getKey()+"-"+CommonConstant.BUYER,
				RedisEnums.MEMBER_GROUP_KEY.getKey()+"-"+CommonConstant.SELLER);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UmsMemberGroup> getAllBuyerGroupList() {
		List<UmsMemberGroup> list = (List<UmsMemberGroup>) redisUtil.getRedisValue(RedisEnums.MEMBER_GROUP_KEY.getKey()+"-"+CommonConstant.BUYER);
		if(CollectionUtil.isNotEmpty(list)){
			return list;
		}

		UmsMemberGroupExample example = new UmsMemberGroupExample();
		example.setOrderByClause("sort asc");
		example.createCriteria().andTypeEqualTo(CommonConstant.BUYER).andStatusEqualTo(CommonConstant.YES_INT);
		list = memberGroupMapper.selectByExample(example);

		redisUtil.setRedisValue(RedisEnums.MEMBER_GROUP_KEY.getKey()+"-"+CommonConstant.BUYER, list);
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UmsMemberGroup> getAllSellerGroupList() {
		List<UmsMemberGroup> list = (List<UmsMemberGroup>) redisUtil.getRedisValue(RedisEnums.MEMBER_GROUP_KEY.getKey()+"-"+CommonConstant.SELLER);
		if(CollectionUtil.isNotEmpty(list)){
			return list;
		}

		UmsMemberGroupExample example = new UmsMemberGroupExample();
		example.setOrderByClause("sort asc");
		example.createCriteria().andTypeEqualTo(CommonConstant.SELLER).andStatusEqualTo(CommonConstant.YES_INT);
		list = memberGroupMapper.selectByExample(example);

		redisUtil.setRedisValue(RedisEnums.MEMBER_GROUP_KEY.getKey()+"-"+CommonConstant.SELLER, list);
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UmsMemberGroup> getAllGroupList() {
		List<UmsMemberGroup> list = (List<UmsMemberGroup>) redisUtil.getRedisValue(RedisEnums.MEMBER_GROUP_KEY.getKey());
		if(CollectionUtil.isNotEmpty(list)){
			return list;
		}

		UmsMemberGroupExample example = new UmsMemberGroupExample();
		example.setOrderByClause("type asc, sort asc");
		example.createCriteria().andStatusEqualTo(CommonConstant.YES_INT);
		list = memberGroupMapper.selectByExample(example);

		redisUtil.setRedisValue(RedisEnums.MEMBER_GROUP_KEY.getKey(), list);
		return list;
	}

	@Override
	public UmsMemberGroup getDefaultBuyerGroup() {
		UmsMemberGroupExample example = new UmsMemberGroupExample();
		example.createCriteria().andTypeEqualTo(CommonConstant.BUYER).andStatusEqualTo(CommonConstant.YES_INT).andDefaultStatusEqualTo(CommonConstant.YES_INT);
		List<UmsMemberGroup> list = memberGroupMapper.selectByExample(example);
		return CollectionUtil.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public UmsMemberGroup getDefaultSellerGroup() {
		UmsMemberGroupExample example = new UmsMemberGroupExample();
		example.createCriteria().andTypeEqualTo(CommonConstant.SELLER).andStatusEqualTo(CommonConstant.YES_INT).andDefaultStatusEqualTo(CommonConstant.YES_INT);
		List<UmsMemberGroup> list = memberGroupMapper.selectByExample(example);
		return CollectionUtil.isEmpty(list) ? null : list.get(0);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateStatus(Integer id, Integer status) {
		UmsMemberGroup updateMemberGroup = new UmsMemberGroup();
		updateMemberGroup.setId(id);
		updateMemberGroup.setStatus(status);
		memberGroupMapper.updateByPrimaryKeySelective(updateMemberGroup);

		redisUtil.delete(
				RedisEnums.MEMBER_GROUP_KEY.getKey(),
				RedisEnums.MEMBER_GROUP_KEY.getKey()+"-"+CommonConstant.BUYER,
				RedisEnums.MEMBER_GROUP_KEY.getKey()+"-"+CommonConstant.SELLER);
	}

	@Override
	public String getGroupName(Integer groupId) {
		if(groupId == null){
			return "";
		}
		return memberGroupMapper.selectByPrimaryKey(groupId).getName();
	}

	@Override
	public UmsMemberGroup getGroup(Integer groupId) {
		return memberGroupMapper.selectByPrimaryKey(groupId);
	}

	@Override
	public void upLevel(UmsMember member) {
		UmsMemberGroup group = this.getGroup(member.getGroupId());
		//查询出上一级
		UmsMemberGroupExample example = new UmsMemberGroupExample();
		example.createCriteria().andTypeEqualTo(member.getType()).andStatusEqualTo(CommonConstant.YES_INT)
				.andLevelEqualTo(group.getLevel()+1);
		List<UmsMemberGroup> list = memberGroupMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(list)){
			return;
		}
		UmsMemberGroup upGroup = list.get(0);
		if(upGroup.getUpPoint() == null || upGroup.getUpPoint().compareTo(BigDecimal.ZERO) <= 0){
			return;
		}

		//积分入账了, 判断是否可升级
		UmsMemberAccount memberAccount = accountService.getMemberAccount(member.getId());
		if(memberAccount.getPoint().compareTo(upGroup.getUpPoint()) >= 0){
			UmsMember updateMember = new UmsMember();
			updateMember.setId(member.getId());
			updateMember.setGroupId(upGroup.getId());
			updateMember.setGroupName(upGroup.getName());
			updateMember.setGroupIcon(upGroup.getIcon());
			memberMapper.updateByPrimaryKeySelective(updateMember);
		}

	}
}