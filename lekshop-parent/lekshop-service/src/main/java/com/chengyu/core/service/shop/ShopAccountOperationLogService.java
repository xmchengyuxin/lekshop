package com.chengyu.core.service.shop;

import com.chengyu.core.model.UmsShopAccount;
import com.chengyu.core.model.UmsShopAccountOperationLog;

import java.util.List;

/**
 * 子账号操作记录
 * @author LeGreen
 * @date   2021/1/7
 */
public interface ShopAccountOperationLogService {

	/**
	 * 添加操作记录
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  account
	 * @param  ip
	 * @param  remark
	 * @param  params
	 */
	void addOperationLog(UmsShopAccount account, String ip, String remark, String params);
	
	/**
	 * 获取操作记录
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  accountId
	 * @param  accountName
	 * @param  keyword
	 * @param  page
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.UmsMemberOperationLog>
	 */
	List<UmsShopAccountOperationLog> getOperationLogList(Integer accountId, String accountName, String keyword, Integer agentId, Integer page, Integer pageSize);

	/**
	 * 删除几天前的日志
	 * @author LeGreen
	 * @date   2021/1/22
	 * @param  days
	 */
	void deleteLogByDays(Integer days);
}