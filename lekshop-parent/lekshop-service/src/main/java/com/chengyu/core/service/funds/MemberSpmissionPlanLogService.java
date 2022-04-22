package com.chengyu.core.service.funds;

import com.chengyu.core.domain.enums.AccountEnums;
import com.chengyu.core.domain.form.AccountSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberSpmissionPlanLog;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @title  预计推广佣金
 * @author LeGreen
 * @date   2021/12/3
 */
public interface MemberSpmissionPlanLogService {
	
	/**
	 * 查询预计佣金记录
	 * @author LeGreen
	 * @date   2021/12/3
	 * @param  form
	 * @param  page
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.UmsMemberSpmissionPlanLog>
	 * @throws ServiceException 业务异常
	 */
	List<UmsMemberSpmissionPlanLog> getAccountLog(AccountSearchForm form, Integer page, Integer pageSize);

	/**
	 * 添加推广佣金预计收入
	 * @author LeGreen
	 * @date   2021/12/3
	 * @param  member
	 * @param  devoteMember
	 * @param  accountType
	 * @param  orderNo
	 * @param  amount
	 * @param  remark
	 */
	void addSpmissionPlanLog(UmsMember member, UmsMember devoteMember, AccountEnums.MemberSpmissionTypes accountType, String orderNo, BigDecimal amount, String remark);

	/**
	 * 入账
	 * @author LeGreen
	 * @date   2021/12/3
	 * @param  orderNo
	 */
	void inAccount(String orderNo);

	/**
	 * 取消入账
	 * @author LeGreen
	 * @date   2021/12/3
	 * @param  orderNo
	 */
	void cancelAccount(String orderNo);

	/**
	 * 统计预计收入和单量
	 * @author LeGreen
	 * @date   2021/12/3
	 * @param  form
	 * @return java.math.BigDecimal
	 */
	Map<String,Object> countPlanIncomeAndNum(AccountSearchForm form);

}