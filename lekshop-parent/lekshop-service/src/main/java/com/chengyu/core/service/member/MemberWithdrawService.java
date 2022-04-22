package com.chengyu.core.service.member;

import com.chengyu.core.domain.form.BankForm;
import com.chengyu.core.domain.form.WithdrawSearchForm;
import com.chengyu.core.domain.result.WithdrawCountResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberWithdraw;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @title  用户提现
 * @author LeGreen
 * @date   2021/1/7
 */
public interface MemberWithdrawService {
	
	/**
	 * 提现列表
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  form
	 * @param  page
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.UmsMemberWithdraw>
	 */
	List<UmsMemberWithdraw> getWithdrawLog(WithdrawSearchForm form, Integer page, Integer pageSize);
	
	/**
	 * 提现
	 * @author LeGreen
	 * @date   2021/1/9
	 * @param  member
	 * @param  type
	 * @param  method
	 * @param  amount
	 * @param  bankForm
	 * @return java.lang.Integer
	 */
	Integer withdraw(UmsMember member, Integer type, Integer method, BigDecimal amount, BankForm bankForm) throws ServiceException;
	
	/**
	 * 审核提现
	 * @param id
	 * @param status
	 * @param verifyName
	 * @param remark
	 * @throws ServiceException
	 */
	void verifyWithdraw(Integer id, Integer status, String verifyName, String remark) throws ServiceException;

	/**
	 * 批量审核提现
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  ids
	 * @param  status
	 * @param  code
	 * @param  remark
	 * @return java.lang.String
	 * @throws ServiceException 业务异常
	 */
	String batchVerify(String ids, Integer status, String code, String remark) throws ServiceException;
	
	/**
	 * 统计提现金额
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  form
	 * @return com.chengyu.core.domain.result.WithdrawCountResult
	 * @throws ServiceException 业务异常
	 */
	WithdrawCountResult countWithdraw(WithdrawSearchForm form) throws ServiceException;
	
	/**
	 * 统计提现次数
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId
	 * @param  beginTime
	 * @param  endTime
	 * @return long
	 * @throws ServiceException 业务异常
	 */
	long countWithdrawNums(Integer memberId, Date beginTime, Date endTime) throws ServiceException;

	/**
	 * 批量设为处理中
	 * @author LeGreen
	 * @date   2021/3/4
	 * @param  ids
	 * @return java.lang.String
	 * @throws ServiceException 业务异常
	 */
    String batchSetDoing(String ids) throws ServiceException;

    /**
     * 后台提现
     * @author LeGreen
     * @date   2021/5/29
     * @param  member
     * @param  type
     * @param  method
     * @param  amount
     * @param  bankForm
     * @throws ServiceException 业务异常
     */
    void withdrawByAdmin(UmsMember member, Integer type, Integer method, BigDecimal amount, BankForm bankForm) throws ServiceException;

    /**
     * 驳回所有正在提现的订单
     * @author LeGreen
     * @date   2021/6/21
     * @param  memberId
     * @throws ServiceException 业务异常
     */
    void rejectAllWithdraw(Integer memberId) throws ServiceException;

    /**
     * 支付宝批量转账
     * @author LeGreen
     * @date   2021/10/27
     * @param  ids
     * @param  operationName
     * @return java.lang.String
     * @throws ServiceException 业务异常
     */
	String batchVerifySusByZfb(String ids, String operationName) throws ServiceException;

	/**
	 * 查询支付宝余额
	 * @author LeGreen
	 * @date   2021/10/27
	 * @return java.lang.String
	 * @throws ServiceException 业务异常
	 */
	String queryZfbBalance() throws Exception;
}