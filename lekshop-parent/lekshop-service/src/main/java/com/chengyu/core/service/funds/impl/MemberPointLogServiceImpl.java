package com.chengyu.core.service.funds.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.AccountEnums;
import com.chengyu.core.domain.form.AccountSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.CustomMemberAccountMapper;
import com.chengyu.core.mapper.UmsMemberPointLogMapper;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberAccount;
import com.chengyu.core.model.UmsMemberPointLog;
import com.chengyu.core.model.UmsMemberPointLogExample;
import com.chengyu.core.service.funds.MemberPointLogService;
import com.chengyu.core.service.member.MemberAccountService;
import com.chengyu.core.service.member.MemberGroupService;
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
 * @title  账户积分资金明细
 * @author LeGreen
 * @date   2021/1/7
 */
@Service
public class MemberPointLogServiceImpl implements MemberPointLogService {
	
	@Autowired
	private UmsMemberPointLogMapper pointLogMapper;
	@Autowired
	private CustomMemberAccountMapper customAccountMapper;
	@Autowired
	private MemberAccountService accountService;
	@Autowired
	private MemberGroupService memberGroupService;
	
	@Override
	public List<UmsMemberPointLog> getAccountLog(AccountSearchForm form, Integer page, Integer pageSize){
		PageHelper.startPage(page, pageSize);
		
		UmsMemberPointLogExample example = new UmsMemberPointLogExample();
		example.setOrderByClause("add_time desc, id desc");
		UmsMemberPointLogExample.Criteria criteria = example.createCriteria();
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
		return pointLogMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void inAccount(UmsMember member, AccountEnums.MemberPointTypes accountType, String orderNo, BigDecimal amount, String remark, String ip) {
		UmsMemberAccount memberAccount = accountService.getMemberAccount(member.getId());
		BigDecimal beforeAmount = memberAccount.getPoint();
		BigDecimal afterAmount = NumberUtil.add(beforeAmount, amount);
		
		customAccountMapper.inPoint(member.getId(), amount.doubleValue());

		this.insertLog(member, accountType, CommonConstant.IN, orderNo, amount, beforeAmount, afterAmount, remark, ip);

		memberGroupService.upLevel(member);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void outAccount(UmsMember member, AccountEnums.MemberPointTypes accountType, String orderNo, BigDecimal amount, String remark, String ip) throws ServiceException {
		UmsMemberAccount memberAccount = accountService.getMemberAccount(member.getId());
		BigDecimal beforeAmount = memberAccount.getPoint();
		if(beforeAmount.compareTo(amount) < 0){
			throw new ServiceException("积分不足,扣款失败!");
		}
		BigDecimal afterAmount = NumberUtil.sub(beforeAmount, amount);
		
		customAccountMapper.outPoint(member.getId(), amount.doubleValue());
		this.insertLog(member, accountType, CommonConstant.OUT, orderNo, amount, beforeAmount, afterAmount, remark, ip);
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addAccountLog(UmsMemberPointLog account) {
		account.setAddTime(DateUtil.date());
		account.setUpdTime(account.getAddTime());
		pointLogMapper.insertSelective(account);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void freezeAccount(UmsMember member, AccountEnums.MemberPointTypes accountType, String orderNo, BigDecimal amount, String remark, String ip) throws ServiceException {
		UmsMemberAccount memberAccount = accountService.getMemberAccount(member.getId());
		BigDecimal beforeAmount = memberAccount.getPoint();
		if(beforeAmount.compareTo(amount) < 0){
			throw new ServiceException("积分不足,冻结失败!");
		}
		BigDecimal afterAmount = NumberUtil.sub(beforeAmount, amount);
		
		customAccountMapper.freezePoint(member.getId(), amount.doubleValue());
		this.insertLog(member, accountType, CommonConstant.OUT, orderNo, amount, beforeAmount, afterAmount, remark, ip);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void unfreezeAccount(UmsMember member, AccountEnums.MemberPointTypes accountType, String orderNo, BigDecimal amount, String remark, String ip) throws ServiceException {
		UmsMemberAccount memberAccount = accountService.getMemberAccount(member.getId());
		if(memberAccount.getFreezePoint().compareTo(amount) < 0){
			throw new ServiceException("积分不足,解冻失败!");
		}
		BigDecimal beforeAmount = memberAccount.getPoint();
		BigDecimal afterAmount = NumberUtil.add(beforeAmount, amount);
		
		customAccountMapper.unfreezePoint(member.getId(), amount.doubleValue());
		this.insertLog(member, accountType, CommonConstant.IN, orderNo, amount, beforeAmount, afterAmount, remark, ip);
	}

	private void insertLog(
			UmsMember member, AccountEnums.MemberPointTypes accountType,
		    Integer inOut, String orderNo, BigDecimal amount,
		    BigDecimal beforeAmount, BigDecimal afterAmount, String remark,
			String ip) {
		UmsMemberPointLog account = new UmsMemberPointLog();
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