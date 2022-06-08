package com.chengyu.core.service.funds.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.AccountEnums;
import com.chengyu.core.domain.form.AccountSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.CustomMemberAccountMapper;
import com.chengyu.core.mapper.UmsMemberMissionLogMapper;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberAccount;
import com.chengyu.core.model.UmsMemberMissionLog;
import com.chengyu.core.model.UmsMemberMissionLogExample;
import com.chengyu.core.service.funds.MemberMissionLogService;
import com.chengyu.core.service.member.MemberAccountService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @title  账户佣金资金明细
 * @author LeGreen
 * @date   2021/1/7
 */
@Service
public class MemberMissionLogServiceImpl implements MemberMissionLogService {

	@Autowired
	private UmsMemberMissionLogMapper missionLogMapper;
	@Autowired
	private CustomMemberAccountMapper customAccountMapper;
	@Autowired
	private MemberAccountService accountService;

	@Override
	public List<UmsMemberMissionLog> getAccountLog(AccountSearchForm form, Integer page, Integer pageSize){
		PageHelper.startPage(page, pageSize);

		UmsMemberMissionLogExample example = new UmsMemberMissionLogExample();
		example.setOrderByClause("add_time desc, id desc");
		UmsMemberMissionLogExample.Criteria criteria = example.createCriteria();
		if(form.getMemberId() != null){
			criteria.andMemberIdEqualTo(form.getMemberId());
		}
		if(StringUtils.isNotBlank(form.getMemberName())){
			criteria.andMemberNameLike("%"+form.getMemberName()+"%");
		}
		if(form.getInOut() != null){
			criteria.andInOutEqualTo(form.getInOut());
		}
		if(StringUtils.isNotBlank(form.getOrderNo())){
			criteria.andOrderNoLike("%"+form.getOrderNo()+"%");
		}
		if(form.getType() != null){
			criteria.andTypeEqualTo(form.getType());
		}
		if(CollectionUtil.isNotEmpty(form.getTypeList())){
			criteria.andTypeIn(form.getTypeList());
		}
		if(StringUtils.isNotBlank(form.getTypes())){
			List<String> types = Arrays.asList(form.getTypes().split(CommonConstant.DH_REGEX));
			criteria.andTypeIn(types.stream().map(Integer::parseInt).collect(Collectors.toList()));
		}
		if(form.getDateFrom() != null && form.getDateTo() != null){
			criteria.andAddTimeBetween(form.getDateFrom(), form.getDateTo());
		}
		return missionLogMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void inAccount(UmsMember member, AccountEnums.MemberMissionTypes accountType, String orderNo, BigDecimal amount, String remark, String ip) {
		UmsMemberAccount memberAccount = accountService.getMemberAccount(member.getId());
		BigDecimal beforeAmount = memberAccount.getMission();
		BigDecimal afterAmount = NumberUtil.add(beforeAmount, amount);

		customAccountMapper.inMission(member.getId(), amount.doubleValue());

		this.insertLog(member, accountType, CommonConstant.IN, orderNo, amount, beforeAmount, afterAmount, remark, ip);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void outAccount(UmsMember member, AccountEnums.MemberMissionTypes accountType, String orderNo, BigDecimal amount, String remark, String ip) throws ServiceException {
		if(amount.compareTo(BigDecimal.ZERO) <= 0){
			return;
		}
		UmsMemberAccount memberAccount = accountService.getMemberAccount(member.getId());
		BigDecimal beforeAmount = memberAccount.getMission();
		if(beforeAmount.compareTo(amount) < 0){
			throw new ServiceException("member.mission.noenough");
		}
		BigDecimal afterAmount = NumberUtil.sub(beforeAmount, amount);

		customAccountMapper.outMission(member.getId(), amount.doubleValue());
		this.insertLog(member, accountType, CommonConstant.OUT, orderNo, amount, beforeAmount, afterAmount, remark, ip);
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addAccountLog(UmsMemberMissionLog account) {
		account.setAddTime(DateUtil.date());
		account.setUpdTime(account.getAddTime());
		missionLogMapper.insertSelective(account);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void freezeAccount(UmsMember member, AccountEnums.MemberMissionTypes accountType, String orderNo, BigDecimal amount, String remark, String ip) throws ServiceException {
		UmsMemberAccount memberAccount = accountService.getMemberAccount(member.getId());
		BigDecimal beforeAmount = memberAccount.getMission();
		if(beforeAmount.compareTo(amount) < 0){
			throw new ServiceException("member.mission.noenough");
		}
		BigDecimal afterAmount = NumberUtil.sub(beforeAmount, amount);

		customAccountMapper.freezeMission(member.getId(), amount.doubleValue());
		this.insertLog(member, accountType, CommonConstant.OUT, orderNo, amount, beforeAmount, afterAmount, remark, ip);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void unfreezeAccount(UmsMember member, AccountEnums.MemberMissionTypes accountType, String orderNo, BigDecimal amount, String remark, String ip) throws ServiceException {
		UmsMemberAccount memberAccount = accountService.getMemberAccount(member.getId());
		if(memberAccount.getFreezeMission().compareTo(amount) < 0){
			throw new ServiceException("member.mission.noenough");
		}
		BigDecimal beforeAmount = memberAccount.getAmount();
		BigDecimal afterAmount = NumberUtil.add(beforeAmount, amount);

		customAccountMapper.unfreezeMission(member.getId(), amount.doubleValue());
		this.insertLog(member, accountType, CommonConstant.IN, orderNo, amount, beforeAmount, afterAmount, remark, ip);
	}

	private void insertLog(
			UmsMember member, AccountEnums.MemberMissionTypes accountType,
		    Integer inOut, String orderNo, BigDecimal amount,
		    BigDecimal beforeAmount, BigDecimal afterAmount, String remark,
			String ip) {
		UmsMemberMissionLog account = new UmsMemberMissionLog();
		account.setType(accountType.getValue());
		account.setInOut(inOut);
		account.setMemberId(member.getId());
		account.setMemberName(member.getCode());
		account.setOrderNo(orderNo);
		account.setAmount(amount);
		account.setAmountBefore(beforeAmount);
		account.setAmountAfter(afterAmount);
		account.setRemark(remark);
		account.setOperateIp(ip);
		account.setOperateIpAddress("");
		this.addAccountLog(account);
	}
}