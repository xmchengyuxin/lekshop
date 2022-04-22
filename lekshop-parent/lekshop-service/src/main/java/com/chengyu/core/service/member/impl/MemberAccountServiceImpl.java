package com.chengyu.core.service.member.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.UmsMemberAccountMapper;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberAccount;
import com.chengyu.core.model.UmsMemberAccountExample;
import com.chengyu.core.model.UmsMemberGroup;
import com.chengyu.core.service.member.MemberAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @title  会员账户
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class MemberAccountServiceImpl implements MemberAccountService {
	
	@Autowired
	private UmsMemberAccountMapper memberAccountMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void initAccount(UmsMember member, UmsMemberGroup memberGroup) throws ServiceException {
		UmsMemberAccount account = this.getMemberAccount(member.getId());
		if(account != null){
			throw new ServiceException("会员钱包已存在, 初始化失败");
		}
		account = new UmsMemberAccount();
		account.setMemberId(member.getId());
		account.setMemberName(member.getCode());
		account.setAmount(memberGroup.getInitAmount());
		account.setFreezeAmount(BigDecimal.ZERO);
		account.setMission(memberGroup.getInitMission());
		account.setFreezeMission(BigDecimal.ZERO);
		account.setSpmission(BigDecimal.ZERO);
		account.setFreezeSpmission(BigDecimal.ZERO);
		account.setPoint(memberGroup.getInitPoint());
		account.setFreezePoint(BigDecimal.ZERO);
		account.setAddTime(DateUtil.date());
		account.setUpdTime(account.getAddTime());
		memberAccountMapper.insertSelective(account);
	}

	@Override
	public UmsMemberAccount getMemberAccount(Integer memberId) {
		UmsMemberAccountExample example = new UmsMemberAccountExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		List<UmsMemberAccount> list = memberAccountMapper.selectByExample(example);
		return CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
	}
}