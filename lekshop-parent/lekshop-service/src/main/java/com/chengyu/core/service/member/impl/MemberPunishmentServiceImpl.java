package com.chengyu.core.service.member.impl;

import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.MemberTypes;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.UmsMemberPunishmentMapper;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberPunishment;
import com.chengyu.core.model.UmsMemberPunishmentExample;
import com.chengyu.core.service.member.MemberPunishmentService;
import com.chengyu.core.service.member.MemberService;
import com.chengyu.core.service.member.WeixinNoticeService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @title  会员处罚
 * @author LeGreen
 * @date   2021/1/30
 */
@Service
public class MemberPunishmentServiceImpl implements MemberPunishmentService {
	
	@Autowired
	private UmsMemberPunishmentMapper memberPunishmentMapper;
	@Autowired
	private MemberService memberService;
	@Autowired
	private WeixinNoticeService weixinNoticeService;

	@Override
	public List<UmsMemberPunishment> getPunishmentList(Integer memberId, String memberName, Integer status, Integer agentId, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		UmsMemberPunishmentExample example = new UmsMemberPunishmentExample();
		example.setOrderByClause("add_time desc");
		UmsMemberPunishmentExample.Criteria criteria = example.createCriteria();
		if(memberId != null){
			criteria.andMemberIdEqualTo(memberId);
		}
		if(StringUtils.isNotBlank(memberName)){
			criteria.andMemberNameLike("%"+memberName+"%");
		}
		if(status != null){
			criteria.andStatusEqualTo(status);
		}
		return memberPunishmentMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addPunishment(UmsMemberPunishment punishment) throws ServiceException {
		//判断用户名是否存在
		UmsMember member = memberService.getMemberByParams(punishment.getMemberName(), MemberTypes.CODE);
		if(member == null){
			throw new ServiceException("您输入的用户名不存在");
		}
		punishment.setMemberId(member.getId());
		punishment.setAddTime(DateUtil.date());
		punishment.setUpdTime(punishment.getAddTime());
		punishment.setExpiredTime(DateUtil.offsetDay(punishment.getAddTime(), punishment.getDays()));
		punishment.setStatus(CommonConstant.NO_INT);
		memberPunishmentMapper.insertSelective(punishment);

		weixinNoticeService.closeAccount(member, punishment.getContent());
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deletePunishment(Integer id) {
		memberPunishmentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public boolean isPunishmenting(Integer memberId) {
		UmsMemberPunishmentExample example = new UmsMemberPunishmentExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andStatusEqualTo(CommonConstant.NO_INT).andExpiredTimeGreaterThan(DateUtil.date());
		return memberPunishmentMapper.countByExample(example) > 0;
	}
}