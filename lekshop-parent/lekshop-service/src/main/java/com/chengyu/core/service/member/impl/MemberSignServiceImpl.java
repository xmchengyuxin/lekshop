package com.chengyu.core.service.member.impl;

import com.chengyu.core.domain.enums.AccountEnums;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.CustomSignMapper;
import com.chengyu.core.mapper.UmsMemberSignMapper;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberSign;
import com.chengyu.core.model.UmsMemberSignExample;
import com.chengyu.core.service.funds.MemberPointLogService;
import com.chengyu.core.service.member.MemberSignService;
import com.chengyu.core.utils.DateUtil;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class MemberSignServiceImpl implements MemberSignService {
	
	@Autowired
	private UmsMemberSignMapper signMapper;
	@Autowired
	private CustomSignMapper customSignMapper;
	@Autowired
	private MemberPointLogService memberPointLogService;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addSign(UmsMember member) throws ServiceException {
		//判断今日是否签到
		Date todayBegin = DateUtil.timeParse(DateUtil.getCurrentDateStr() + " " +"00:00:00");
		Date todayEnd = DateUtil.timeParse(DateUtil.getCurrentDateStr() + " " +"23:59:59");
		
		UmsMemberSignExample example = new UmsMemberSignExample();
		example.createCriteria().andMemberIdEqualTo(member.getId()).andAddTimeBetween(todayBegin, todayEnd);
		long count = signMapper.countByExample(example);
		if(count > 0){
			throw new ServiceException("您今日已签到,明日再来,更多惊喜在等你!");
		}
			
		UmsMemberSign sign = new UmsMemberSign();
		sign.setMemberId(member.getId());
		sign.setMemberName(member.getNickname());
		sign.setHeadImg(member.getHeadImg());
		sign.setSignDate(new Date());
		sign.setAddTime(new Date());
		sign.setUpdTime(sign.getAddTime());
		signMapper.insertSelective(sign);

		memberPointLogService.inAccount(member, AccountEnums.MemberPointTypes.ACCOUNT_SIGN,
				StringUtils.genOrderNo(member.getId()), new BigDecimal(2), "签到获得积分", null);
	}

	@Override
	public List<UmsMemberSign> getSignPage(Integer memberId, String memberName, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		
		UmsMemberSignExample example = new UmsMemberSignExample();
		example.setOrderByClause("add_time desc");
		UmsMemberSignExample.Criteria criteria = example.createCriteria();
		if(memberId != null){
			criteria.andMemberIdEqualTo(memberId);
		}
		if(StringUtils.isNotBlank(memberName)){
			criteria.andMemberNameLike("%"+memberName+"%");
		}
		return signMapper.selectByExample(example);
	}

	@Override
	public Integer getSignDays(Integer memberId) {
		return customSignMapper.getSignDays(memberId);
	}
	
}