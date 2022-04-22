package com.chengyu.core.service.member.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.UmsMemberRelMapper;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberRel;
import com.chengyu.core.model.UmsMemberRelExample;
import com.chengyu.core.service.member.MemberRelService;
import com.chengyu.core.service.member.MemberService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @title  用户推荐关系
 * @author LeGreen
 * @date   2021/1/7
 */
@Service
public class MemberRelServiceImpl implements MemberRelService {

	@Autowired
	private MemberService memberService;
	
	@Autowired 
	private UmsMemberRelMapper memberRelMapper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addMemberRel(Integer upMemberId, Integer downMemberId) {
		Date now = new Date();
		UmsMemberRel rel = new UmsMemberRel();
		rel.setType(1);
		rel.setUpMemberId(upMemberId);
		rel.setDownMemberId(downMemberId);
		rel.setAddTime(now);
		memberRelMapper.insert(rel);
		
		//查询出upMemberId的所有上级
		List<UmsMemberRel> memberRelList = this.getAllUpMemberByMemberId(upMemberId);
		if(memberRelList != null && !memberRelList.isEmpty()){
			for(UmsMemberRel memberRel : memberRelList){
				rel = new UmsMemberRel();
				rel.setType(memberRel.getType()+1);
				rel.setUpMemberId(memberRel.getUpMemberId());
				rel.setDownMemberId(downMemberId);
				rel.setAddTime(now);
				memberRelMapper.insert(rel);
			}
		}
		
	}

	@Override
	public List<UmsMemberRel> getAllUpMemberByMemberId(Integer memberId) {
		UmsMemberRelExample example = new UmsMemberRelExample();
		example.createCriteria().andDownMemberIdEqualTo(memberId);
		return memberRelMapper.selectByExample(example);
	}

	@Override
	public List<UmsMember> getUpMemberByFenxiaoLevel(Integer buerId, Integer fenxiaoLevel) throws ServiceException {
		UmsMemberRelExample example = new UmsMemberRelExample();
		example.createCriteria().andDownMemberIdEqualTo(buerId);
		example.setOrderByClause("type asc limit "+fenxiaoLevel);
		List<UmsMemberRel> relList = memberRelMapper.selectByExample(example);
			
		List<UmsMember> memberList = new ArrayList<>();
		if(relList != null && !relList.isEmpty()){
			for(UmsMemberRel rel : relList){
				UmsMember member = memberService.getMemberById(rel.getUpMemberId());
				memberList.add(member);
			}
		}
			
		return memberList;
	}

	@Override
	public List<UmsMember> getAllDownMemberByMemberId(Integer memberId) throws ServiceException {
		UmsMemberRelExample example = new UmsMemberRelExample();
		example.createCriteria().andUpMemberIdEqualTo(memberId);
		example.setOrderByClause("add_time desc");
		List<UmsMemberRel> relList = memberRelMapper.selectByExample(example);
			
		List<UmsMember> memberList = new ArrayList<>();
		if(relList != null && !relList.isEmpty()){
			for(UmsMemberRel rel : relList){
				UmsMember member = memberService.getMemberById(rel.getDownMemberId());
				memberList.add(member);
			}
		}
		
		return memberList;
	}

	@Override
	public List<UmsMemberRel> getDownMemberByLevel(Integer memberId, Integer level, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		UmsMemberRelExample example = new UmsMemberRelExample();
		example.createCriteria().andUpMemberIdEqualTo(memberId).andTypeEqualTo(level);
		example.setOrderByClause("add_time desc");
		return memberRelMapper.selectByExample(example);
	}

	@Override
	public long countDownNum(Integer memberId, Integer level) {
		UmsMemberRelExample example = new UmsMemberRelExample();
		example.createCriteria().andUpMemberIdEqualTo(memberId).andTypeEqualTo(level);
		return memberRelMapper.countByExample(example);
	}

	@Override
	public List<Integer> getAllDownMemberIds(Integer memberId) {
		UmsMemberRelExample example = new UmsMemberRelExample();
		example.createCriteria().andUpMemberIdEqualTo(memberId).andTypeEqualTo(1);
		example.setOrderByClause("add_time desc");
		List<UmsMemberRel> relList = memberRelMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(relList)){
			return null;
		}
		return relList.stream().map(UmsMemberRel::getDownMemberId).collect(Collectors.toList());
	}


}