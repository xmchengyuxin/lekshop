package com.chengyu.core.service.funds.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.AccountEnums;
import com.chengyu.core.domain.enums.AccountEnums.MemberAccountTypes;
import com.chengyu.core.domain.enums.MemberTypes;
import com.chengyu.core.domain.form.AccountSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.CustomMemberAccountMapper;
import com.chengyu.core.mapper.UmsMemberAccountLogMapper;
import com.chengyu.core.mapper.UmsMemberAccountMapper;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberAccount;
import com.chengyu.core.model.UmsMemberAccountLog;
import com.chengyu.core.model.UmsMemberAccountLogExample;
import com.chengyu.core.service.funds.MemberAccountLogService;
import com.chengyu.core.service.member.MemberAccountService;
import com.chengyu.core.service.member.MemberService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @title  账户余额资金明细
 * @author LeGreen
 * @date   2021/1/7
 */
@Service
public class MemberAccountLogServiceImpl implements MemberAccountLogService {
	
	@Autowired
	private UmsMemberAccountLogMapper accountLogMapper;
	@Autowired
	private CustomMemberAccountMapper customAccountMapper;
	@Autowired
	private MemberAccountService accountService;
	@Autowired
	private UmsMemberAccountMapper accountMapper;
	@Autowired
	private MemberService memberService;
	
	@Override
	public List<UmsMemberAccountLog> getAccountLog(AccountSearchForm form, Integer page, Integer pageSize){
		PageHelper.startPage(page, pageSize);
		UmsMemberAccountLogExample example = new UmsMemberAccountLogExample();
		example.setOrderByClause("add_time desc, id desc");
		UmsMemberAccountLogExample.Criteria criteria = example.createCriteria();
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
		return accountLogMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void inAccount(UmsMember member, AccountEnums.MemberAccountTypes accountType, String orderNo, BigDecimal amount, String remark, String ip) {
		UmsMemberAccount memberAccount = accountService.getMemberAccount(member.getId());
		BigDecimal beforeAmount = memberAccount.getAmount();
		BigDecimal afterAmount = NumberUtil.add(beforeAmount, amount);
		
		customAccountMapper.inAmount(member.getId(), amount.doubleValue());

		this.insertLog(member, accountType, CommonConstant.IN, orderNo, amount, beforeAmount, afterAmount, remark, ip);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void outAccount(UmsMember member, AccountEnums.MemberAccountTypes accountType, String orderNo, BigDecimal amount, String remark, String ip) throws ServiceException {
		if(amount.compareTo(BigDecimal.ZERO) <= 0){
			return;
		}
		UmsMemberAccount memberAccount = accountService.getMemberAccount(member.getId());
		BigDecimal beforeAmount = memberAccount.getAmount();
		if(beforeAmount.compareTo(amount) < 0){
			throw new ServiceException("余额不足,扣款失败!");
		}
		BigDecimal afterAmount = NumberUtil.sub(beforeAmount, amount);
		
		customAccountMapper.outAmount(member.getId(), amount.doubleValue());
		this.insertLog(member, accountType, CommonConstant.OUT, orderNo, amount, beforeAmount, afterAmount, remark, ip);
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addAccountLog(UmsMemberAccountLog account) {
		account.setAddTime(DateUtil.date());
		account.setUpdTime(account.getAddTime());
		accountLogMapper.insertSelective(account);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void freezeAccount(UmsMember member, AccountEnums.MemberAccountTypes accountType, String orderNo, BigDecimal amount, String remark, String ip) throws ServiceException {
		UmsMemberAccount memberAccount = accountService.getMemberAccount(member.getId());
		BigDecimal beforeAmount = memberAccount.getAmount();
		if(beforeAmount.compareTo(amount) < 0){
			throw new ServiceException("余额不足,冻结失败!");
		}
		BigDecimal afterAmount = NumberUtil.sub(beforeAmount, amount);
		
		customAccountMapper.freezeAmount(member.getId(), amount.doubleValue());
		this.insertLog(member, accountType, CommonConstant.OUT, orderNo, amount, beforeAmount, afterAmount, remark, ip);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void unfreezeAccount(UmsMember member, AccountEnums.MemberAccountTypes accountType, String orderNo, BigDecimal amount, String remark, String ip) throws ServiceException {
		UmsMemberAccount memberAccount = accountService.getMemberAccount(member.getId());
		if(memberAccount.getFreezeAmount().compareTo(amount) < 0){
			throw new ServiceException("冻结金额不足,解冻失败!");
		}
		BigDecimal beforeAmount = memberAccount.getAmount();
		BigDecimal afterAmount = NumberUtil.add(beforeAmount, amount);
		
		customAccountMapper.unfreezeAmount(member.getId(), amount.doubleValue());
		this.insertLog(member, accountType, CommonConstant.IN, orderNo, amount, beforeAmount, afterAmount, remark, ip);
	}

	@Override
	public Map<String, Object> count(Integer memberId) {
		//今日总收入, 总收入, 总支出, 总充值, 总提现
		return customAccountMapper.count(memberId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void transferAmount(Integer memberId, String uid, BigDecimal amount) throws ServiceException {
		if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
			throw new ServiceException("请输入正确的金额");
		}
		UmsMember toMember = memberService.getMemberByParams(uid, MemberTypes.UID);
		if(toMember == null){
			throw new ServiceException("转入用户不存在");
		}
		if(toMember.getId().equals(memberId)){
			throw new ServiceException("转入用户不能是自己");
		}
		UmsMember transferMember = memberService.getMemberById(memberId);
		String orderNo = StringUtils.genOrderNo(memberId);
		this.outAccount(transferMember, AccountEnums.MemberAccountTypes.ACCOUNT_TRANSFER_OUT, orderNo, amount, "本金转出", null);
		this.inAccount(toMember, AccountEnums.MemberAccountTypes.ACCOUNT_TRANSFER_IN, orderNo, amount, "本金转入", null);
	}

	@Override
	public void beat(BigDecimal amount) {
		List<UmsMember> memberList = memberService.getMemberList(null, 1, 999999);
		String orderNo = StringUtils.genOrderNo(null);
		for(UmsMember member : memberList){
			this.inAccount(member, MemberAccountTypes.ACCOUNT_ADMIN_IN, orderNo, amount, "Reward", null);
		}
	}

	private void insertLog(
			UmsMember member, AccountEnums.MemberAccountTypes accountType,
		    Integer inOut, String orderNo, BigDecimal amount,
		    BigDecimal beforeAmount, BigDecimal afterAmount, String remark,
			String ip) {
		UmsMemberAccountLog account = new UmsMemberAccountLog();
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