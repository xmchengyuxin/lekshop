package com.chengyu.core.mapper;

import java.util.List;
import java.util.Map;

/**
 * @title  后台首页统计
 * @author LeGreen
 * @date   2021/1/28
 */
public interface CustomAdminIndexMapper {

	/**
	 * 统计会员数量
	 * @author LeGreen
	 * @date   2021/1/28
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 */
	Map<String, Object> countMember();

	List<Map<String, Object>> countMemberNumByLastTenDays();

    Map<String, Object> countVerifyIdCardNum();

	Map<String, Object> countVerifyBankNum();

	Map<String, Object> countVerifyRechargeNum();

	Map<String, Object> countVerifyWithdrawNum();

	Map<String, Object> countRechargeAmount();

	Map<String, Object> countWithdrawAmount();

	Map<String, Object> countMemberAccount();

	Map<String, Object> CountGoods();
}