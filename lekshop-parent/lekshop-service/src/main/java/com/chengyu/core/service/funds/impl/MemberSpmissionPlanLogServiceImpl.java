package com.chengyu.core.service.funds.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.enums.AccountEnums.MemberSpmissionTypes;
import com.chengyu.core.domain.form.AccountSearchForm;
import com.chengyu.core.mapper.UmsMemberSpmissionPlanLogMapper;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberSpmissionPlanLog;
import com.chengyu.core.model.UmsMemberSpmissionPlanLogExample;
import com.chengyu.core.service.funds.MemberSpmissionPlanLogService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @title  账户佣金预计收入
 * @author LeGreen
 * @date   2021/12/3
 */
@Service
public class MemberSpmissionPlanLogServiceImpl implements MemberSpmissionPlanLogService {

	@Autowired
	private UmsMemberSpmissionPlanLogMapper memberSpmissionPlanLogMapper;

	@Override
	public List<UmsMemberSpmissionPlanLog> getAccountLog(AccountSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		UmsMemberSpmissionPlanLogExample example = new UmsMemberSpmissionPlanLogExample();
		example.setOrderByClause("add_time desc");
		UmsMemberSpmissionPlanLogExample.Criteria criteria = example.createCriteria();
		criteria.andStatusNotEqualTo(3);
		if(StringUtils.isNotBlank(form.getOrderNo())){
			criteria.andOrderNoLike("%"+form.getOrderNo()+"%");
		}
		if(form.getMemberId() != null){
			criteria.andMemberIdEqualTo(form.getMemberId());
		}
		if(form.getDevoteId() != null){
			criteria.andDevoteIdEqualTo(form.getDevoteId());
		}
		if(form.getDateFrom() != null && form.getDateTo() != null){
			criteria.andAddTimeBetween(form.getDateFrom(), form.getDateTo());
		}
		return memberSpmissionPlanLogMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addSpmissionPlanLog(UmsMember member, UmsMember devoteMember, MemberSpmissionTypes accountType, String orderNo, BigDecimal amount, String remark) {
		UmsMemberSpmissionPlanLog account = new UmsMemberSpmissionPlanLog();
		account.setType(accountType.getValue());
		account.setMemberId(member.getId());
		account.setMemberName(member.getCode());
		if(devoteMember != null){
			account.setDevoteId(devoteMember.getId());
			account.setDevoteName(devoteMember.getCode());
		}
		account.setOrderNo(orderNo);
		account.setAmount(amount);
		account.setRemark(remark);
		account.setStatus(0);
		account.setAddTime(DateUtil.date());
		account.setUpdTime(account.getAddTime());
		memberSpmissionPlanLogMapper.insertSelective(account);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void inAccount(String orderNo) {
		UmsMemberSpmissionPlanLogExample example = new UmsMemberSpmissionPlanLogExample();
		example.createCriteria().andOrderNoEqualTo(orderNo).andStatusEqualTo(0);

		UmsMemberSpmissionPlanLog updateLog = new UmsMemberSpmissionPlanLog();
		updateLog.setStatus(1);
		updateLog.setUpdTime(DateUtil.date());
		memberSpmissionPlanLogMapper.updateByExampleSelective(updateLog, example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void cancelAccount(String orderNo) {
		UmsMemberSpmissionPlanLogExample example = new UmsMemberSpmissionPlanLogExample();
		example.createCriteria().andOrderNoEqualTo(orderNo).andStatusEqualTo(0);

		UmsMemberSpmissionPlanLog updateLog = new UmsMemberSpmissionPlanLog();
		updateLog.setStatus(2);
		updateLog.setUpdTime(DateUtil.date());
		memberSpmissionPlanLogMapper.updateByExampleSelective(updateLog, example);
	}

	@Override
	public Map<String,Object> countPlanIncomeAndNum(AccountSearchForm form) {
		List<UmsMemberSpmissionPlanLog> list = this.getAccountLog(form, 1, 99999);
		Map<String,Object> result = new HashMap<>();
		if(CollectionUtil.isEmpty(list)){
			result.put("planIncome", BigDecimal.ZERO);
			result.put("planNum", 0);
			return result;
		}
		BigDecimal planIncome = list.stream().map(UmsMemberSpmissionPlanLog::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
		result.put("planIncome", planIncome);
		List<String> orderNos = list.stream().map(UmsMemberSpmissionPlanLog::getOrderNo).collect(Collectors.toList());
		result.put("planNum", CollUtil.distinct(orderNos).size());
		return result;
	}
}