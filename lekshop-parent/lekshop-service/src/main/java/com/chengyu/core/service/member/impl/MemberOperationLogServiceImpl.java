package com.chengyu.core.service.member.impl;

import cn.hutool.core.date.DateUtil;
import com.chengyu.core.mapper.UmsMemberOperationLogMapper;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberOperationLog;
import com.chengyu.core.model.UmsMemberOperationLogExample;
import com.chengyu.core.service.member.MemberOperationLogService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @title  用户操作记录
 * @author LeGreen
 * @date   2021/1/7
 */
@Service
public class MemberOperationLogServiceImpl implements MemberOperationLogService {

	@Autowired
	private UmsMemberOperationLogMapper memberOperationLogMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addMemberOperationLog(UmsMember member, String ip, String remark, String params) {
		UmsMemberOperationLog log = new UmsMemberOperationLog();
		log.setMemberId(member.getId());
		log.setMemberName(member.getCode());
		log.setIp(ip);
		log.setRemark(remark);
		log.setParams(params);
		log.setAddTime(DateUtil.date());
		memberOperationLogMapper.insertSelective(log);
	}

	@Override
	public List<UmsMemberOperationLog> getMemberOperationLogList(Integer memberId, String memberName, String keyword,  Integer agentId, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		UmsMemberOperationLogExample example = new UmsMemberOperationLogExample();
		example.setOrderByClause("add_time desc");
		UmsMemberOperationLogExample.Criteria criteria = example.createCriteria();
		if(memberId != null){
			criteria.andMemberIdEqualTo(memberId);
		}
		if(StringUtils.isNotBlank(memberName)){
			criteria.andMemberNameLike("%"+memberName+"%");
		}
		if(StringUtils.isNotBlank(keyword)){
			criteria.andRemarkLike("%"+keyword+"%");
		}
		return memberOperationLogMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public void deleteLogByDays(Integer days) {
		Date now = DateUtil.date();
		Date deleteTime = DateUtil.offsetDay(DateUtil.beginOfDay(now), -days);
		UmsMemberOperationLogExample example = new UmsMemberOperationLogExample();
		example.createCriteria().andAddTimeLessThanOrEqualTo(deleteTime);
		memberOperationLogMapper.deleteByExample(example);
	}
}