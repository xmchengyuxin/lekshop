package com.chengyu.core.service.config.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.AccountEnums;
import com.chengyu.core.mapper.ConfigAccountMapper;
import com.chengyu.core.mapper.CustomConfigAccountMapper;
import com.chengyu.core.model.ConfigAccount;
import com.chengyu.core.model.ConfigAccountExample;
import com.chengyu.core.service.config.ConfigAccountService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @title  系统收款账户
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class ConfigAccountServiceImpl implements ConfigAccountService {
	
	@Autowired
	private ConfigAccountMapper configAccountMapper;
	@Autowired
	private CustomConfigAccountMapper customConfigAccountMapper;

	@Override
	public ConfigAccount getAccountByType(AccountEnums.AccountTypes type) {
		return this.getAccountByType(type.getValue());
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addConfigAccount(ConfigAccount account) {
		account.setUpdTime(DateUtil.date());
		if(account.getId() == null){
			account.setAddTime(account.getUpdTime());
			configAccountMapper.insertSelective(account);
		}else{
			configAccountMapper.updateByPrimaryKeySelective(account);
		}
	}

	@Override
	public List<ConfigAccount> getAccountList(Integer type, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		ConfigAccountExample example = new ConfigAccountExample();
		example.setOrderByClause("total_income desc");
		ConfigAccountExample.Criteria criteria = example.createCriteria();
		if(type != null){
			criteria.andTypeEqualTo(type);
		}
		return configAccountMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteAccount(Integer id) {
		configAccountMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateAmount(Integer accountId, BigDecimal amount, boolean isUpdateToday) {
		if(amount.compareTo(BigDecimal.ZERO) > 0){
			//收款
			customConfigAccountMapper.addAmount(accountId, amount.abs().doubleValue(), isUpdateToday);
		}else{
			//扣款
			customConfigAccountMapper.reduceAmount(accountId, amount.abs().doubleValue(), isUpdateToday);
		}
	}

	@Override
	public void clearTodayIncome() {
		ConfigAccount updateAccount = new ConfigAccount();
		updateAccount.setTodayIncome(BigDecimal.ZERO);
		ConfigAccountExample example = new ConfigAccountExample();
		example.createCriteria().andIdGreaterThan(0);
		configAccountMapper.updateByExampleSelective(updateAccount, example);
	}

	@Override
	public ConfigAccount getAccountByType(Integer type){
		ConfigAccountExample example = new ConfigAccountExample();
		example.setOrderByClause("today_income asc");
		example.createCriteria().andTypeEqualTo(type).andStatusEqualTo(CommonConstant.YES_INT);
		List<ConfigAccount> list = configAccountMapper.selectByExample(example);
		return CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
	}
}