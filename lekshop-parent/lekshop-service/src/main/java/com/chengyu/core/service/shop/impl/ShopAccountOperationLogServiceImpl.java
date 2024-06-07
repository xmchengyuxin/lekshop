package com.chengyu.core.service.shop.impl;

import cn.hutool.core.date.DateUtil;
import com.chengyu.core.mapper.UmsShopAccountOperationLogMapper;
import com.chengyu.core.model.UmsShopAccount;
import com.chengyu.core.model.UmsShopAccountOperationLog;
import com.chengyu.core.model.UmsShopAccountOperationLogExample;
import com.chengyu.core.service.shop.ShopAccountOperationLogService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @title  子账号操作记录
 * @author LeGreen
 * @date   2021/1/7
 */
@Service
public class ShopAccountOperationLogServiceImpl implements ShopAccountOperationLogService {

	@Autowired
	private UmsShopAccountOperationLogMapper shopAccountOperationLogMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addOperationLog(UmsShopAccount account, String ip, String remark, String params) {
		UmsShopAccountOperationLog log = new UmsShopAccountOperationLog();
		log.setAccountId(account.getId());
		log.setAccountName(account.getRealname());
		log.setIp(ip);
		log.setRemark(remark);
		log.setParams(params);
		log.setAddTime(DateUtil.date());
		shopAccountOperationLogMapper.insertSelective(log);
	}

	@Override
	public List<UmsShopAccountOperationLog> getOperationLogList(Integer accountId, String accountName, String keyword, Integer agentId, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		UmsShopAccountOperationLogExample example = new UmsShopAccountOperationLogExample();
		example.setOrderByClause("add_time desc");
		UmsShopAccountOperationLogExample.Criteria criteria = example.createCriteria();
		if(accountId != null){
			criteria.andAccountIdEqualTo(accountId);
		}
		if(StringUtils.isNotBlank(accountName)){
			criteria.andAccountNameLike("%"+accountName+"%");
		}
		if(StringUtils.isNotBlank(keyword)){
			criteria.andRemarkLike("%"+keyword+"%");
		}
		return shopAccountOperationLogMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public void deleteLogByDays(Integer days) {
		Date now = DateUtil.date();
		Date deleteTime = DateUtil.offsetDay(DateUtil.beginOfDay(now), -days);
		UmsShopAccountOperationLogExample example = new UmsShopAccountOperationLogExample();
		example.createCriteria().andAddTimeLessThanOrEqualTo(deleteTime);
		shopAccountOperationLogMapper.deleteByExample(example);
	}
}