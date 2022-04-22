package com.chengyu.core.service.funds;

import com.chengyu.core.domain.enums.AccountEnums;
import com.chengyu.core.domain.form.AccountSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberMissionLog;

import java.math.BigDecimal;
import java.util.List;

/**
 * @title  发布点账户
 * @author LeGreen
 * @date   2021/1/7
 */
public interface MemberMissionLogService {

	/**
	 * 查询资金记录
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  form 查询表单
	 * @param  page 起始页
	 * @param  pageSize 条数
	 * @return java.util.List<com.chengyu.core.model.UmsMemberAccountLog>
	 */
	List<UmsMemberMissionLog> getAccountLog(AccountSearchForm form, Integer page, Integer pageSize);

	/**
	 * 入账
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  member 用户
	 * @param  accountType 资金类型
	 * @param  orderNo 订单号
	 * @param  amount 金额
	 * @param  remark 备注
	 * @param  ip IP地址
	 * @throws ServiceException 业务异常
	 */
	void inAccount(UmsMember member, AccountEnums.MemberMissionTypes accountType, String orderNo, BigDecimal amount, String remark, String ip) throws ServiceException;

	/**
	 * 出账
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  member 用户
	 * @param  accountType 资金类型
	 * @param  orderNo 订单号
	 * @param  amount 金额
	 * @param  remark 备注
	 * @param  ip IP地址
	 * @throws ServiceException 业务异常
	 */
	void outAccount(UmsMember member, AccountEnums.MemberMissionTypes accountType, String orderNo, BigDecimal amount, String remark, String ip) throws ServiceException;

	/**
	 * 冻结
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  member 用户
	 * @param  accountType 资金类型
	 * @param  orderNo 订单号
	 * @param  amount 金额
	 * @param  remark 备注
	 * @param  ip IP地址
	 * @throws ServiceException 业务异常
	 */
	void freezeAccount(UmsMember member, AccountEnums.MemberMissionTypes accountType, String orderNo, BigDecimal amount, String remark, String ip) throws ServiceException;

	/**
	 * 解冻
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  member 用户
	 * @param  accountType 资金类型
	 * @param  orderNo 订单号
	 * @param  amount 金额
	 * @param  remark 备注
	 * @param  ip IP地址
	 * @throws ServiceException 业务异常
	 */
	void unfreezeAccount(UmsMember member, AccountEnums.MemberMissionTypes accountType, String orderNo, BigDecimal amount, String remark, String ip) throws ServiceException;
}