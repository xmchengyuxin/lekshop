package com.chengyu.core.service.funds.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.AccountEnums;
import com.chengyu.core.domain.form.AccountSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.CustomMemberAccountMapper;
import com.chengyu.core.mapper.UmsMemberSpmissionLogMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.service.config.ConfigWithdrawService;
import com.chengyu.core.service.funds.MemberAccountLogService;
import com.chengyu.core.service.funds.MemberSpmissionLogService;
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
 * @title  账户推广佣金资金明细
 * @author LeGreen
 * @date   2021/1/7
 */
@Service
public class MemberSpmissionLogServiceImpl implements MemberSpmissionLogService {
	
	@Autowired
	private UmsMemberSpmissionLogMapper spmissionLogMapper;
	@Autowired
	private CustomMemberAccountMapper customAccountMapper;
	@Autowired
	private MemberAccountService accountService;
	@Autowired
	private ConfigWithdrawService configWithdrawService;
	@Autowired
	private MemberAccountLogService memberAccountLogService;
	
	@Override
	public List<UmsMemberSpmissionLog> getAccountLog(AccountSearchForm form, Integer page, Integer pageSize){
		PageHelper.startPage(page, pageSize);
		
		UmsMemberSpmissionLogExample example = new UmsMemberSpmissionLogExample();
		example.setOrderByClause("add_time desc, id desc");
		UmsMemberSpmissionLogExample.Criteria criteria = example.createCriteria();
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
		return spmissionLogMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void inAccount(UmsMember member, UmsMember devoteMember, AccountEnums.MemberSpmissionTypes accountType, String orderNo, BigDecimal amount, String remark, String ip) {
		UmsMemberAccount memberAccount = accountService.getMemberAccount(member.getId());
		BigDecimal beforeAmount = memberAccount.getSpmission();
		BigDecimal afterAmount = NumberUtil.add(beforeAmount, amount);
		
		customAccountMapper.inSpMission(member.getId(), amount.doubleValue());

		this.insertLog(member, devoteMember, accountType, CommonConstant.IN, orderNo, amount, beforeAmount, afterAmount, remark, ip);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void outAccount(UmsMember member, AccountEnums.MemberSpmissionTypes accountType, String orderNo, BigDecimal amount, String remark, String ip) throws ServiceException {
		UmsMemberAccount memberAccount = accountService.getMemberAccount(member.getId());
		BigDecimal beforeAmount = memberAccount.getSpmission();
		if(beforeAmount.compareTo(amount) < 0){
			throw new ServiceException("推广佣金不足,扣款失败!");
		}
		BigDecimal afterAmount = NumberUtil.sub(beforeAmount, amount);
		
		customAccountMapper.outSpMission(member.getId(), amount.doubleValue());
		this.insertLog(member, null, accountType, CommonConstant.OUT, orderNo, amount, beforeAmount, afterAmount, remark, ip);
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addAccountLog(UmsMemberSpmissionLog account) {
		account.setAddTime(DateUtil.date());
		account.setUpdTime(account.getAddTime());
		spmissionLogMapper.insertSelective(account);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void freezeAccount(UmsMember member, AccountEnums.MemberSpmissionTypes accountType, String orderNo, BigDecimal amount, String remark, String ip) throws ServiceException {
		UmsMemberAccount memberAccount = accountService.getMemberAccount(member.getId());
		BigDecimal beforeAmount = memberAccount.getSpmission();
		if(beforeAmount.compareTo(amount) < 0){
			throw new ServiceException("推广佣金不足,冻结失败!");
		}
		BigDecimal afterAmount = NumberUtil.sub(beforeAmount, amount);
		
		customAccountMapper.freezeSpMission(member.getId(), amount.doubleValue());
		this.insertLog(member, null, accountType, CommonConstant.OUT, orderNo, amount, beforeAmount, afterAmount, remark, ip);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void unfreezeAccount(UmsMember member, AccountEnums.MemberSpmissionTypes accountType, String orderNo, BigDecimal amount, String remark, String ip) throws ServiceException {
		UmsMemberAccount memberAccount = accountService.getMemberAccount(member.getId());
		if(memberAccount.getFreezeSpmission().compareTo(amount) < 0){
			throw new ServiceException("推广佣金不足,解冻失败!");
		}
		BigDecimal beforeAmount = memberAccount.getSpmission();
		BigDecimal afterAmount = NumberUtil.add(beforeAmount, amount);
		
		customAccountMapper.unfreezeSpMission(member.getId(), amount.doubleValue());
		this.insertLog(member, null, accountType, CommonConstant.IN, orderNo, amount, beforeAmount, afterAmount, remark, ip);
	}

	@Override
	public BigDecimal countIncome(AccountSearchForm form) {
		BigDecimal amount =  customAccountMapper.countSpmissionIncome(form);
		return amount == null ? BigDecimal.ZERO : amount;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void spmissionToBalance(UmsMember member, BigDecimal spmission) throws ServiceException {
		//校验是否开启此功能
		ConfigWithdraw config = configWithdrawService.getConfigWithdrawByGroupId(member.getGroupId());
		if(config == null || config.getMissionToBalance() == CommonConstant.NO_INT){
			throw new ServiceException("您所在的会员组暂不支持此功能");
		}

		String orderNo = "D"+StringUtils.genOrderNo(member.getId());
		memberAccountLogService.inAccount(member, AccountEnums.MemberAccountTypes.ACCOUNT_TRANSFER_BALANCE, orderNo, spmission, "推广佣金兑换余额", null);
		this.outAccount(member, AccountEnums.MemberSpmissionTypes.ACCOUNT_TRANSFER_BALANCE, orderNo, spmission, "推广佣金兑换余额", null);
	}

	private void insertLog(
			UmsMember member, UmsMember devoteMember, AccountEnums.MemberSpmissionTypes accountType,
		    Integer inOut, String orderNo, BigDecimal amount,
		    BigDecimal beforeAmount, BigDecimal afterAmount, String remark,
			String ip) {
		UmsMemberSpmissionLog account = new UmsMemberSpmissionLog();
		account.setType(accountType.getValue());
		account.setInOut(inOut);
		account.setMemberId(member.getId());
		account.setMemberName(member.getCode());
		if(devoteMember != null){
			account.setDevoteId(devoteMember.getId());
			account.setDevoteName(devoteMember.getCode());
		}
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