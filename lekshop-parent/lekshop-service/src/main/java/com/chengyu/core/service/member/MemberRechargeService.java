package com.chengyu.core.service.member;

import com.chengyu.core.domain.form.RechargeSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberRecharge;

import java.math.BigDecimal;
import java.util.List;

/**
 * @title  用户充值
 * @author LeGreen
 * @date   2021/1/7
 */
public interface MemberRechargeService {
	
	/**
	 * 充值列表
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  form
	 * @param  page
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.UmsMemberRecharge>
	 */
	List<UmsMemberRecharge> getRechargeLog(RechargeSearchForm form, Integer page, Integer pageSize);
	
	/**
	 * 充值
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  member
	 * @param  paymethod
	 * @param  amount
	 * @param  remitBank
	 * @param  remitRealname
	 * @param  remitAccount
	 * @param  img
	 * @return java.lang.String
	 * @throws ServiceException 业务异常
	 */
	String recharge(UmsMember member, String paymethod, BigDecimal amount, String remitBank, String remitRealname, String remitAccount, String img) throws ServiceException;

	/**
	 * 审核充值
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  id
	 * @param  verifyName
	 * @param  status
	 * @param  remark
	 * @throws ServiceException 业务异常
	 */
	void verifyRecharge(Integer id, String verifyName, Integer status, String remark) throws ServiceException;

	/**
	 * 取消充值
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  orderNo
	 * @throws ServiceException 业务异常
	 */
	void cancel(String orderNo) throws ServiceException;

	/**
	 * 发布点兑换资金
	 * @author LeGreen
	 * @date   2021/1/9
	 * @param  member
	 * @param  publishPoint
	 * @throws ServiceException 业务异常
	 */
    void pointToBalance(UmsMember member, BigDecimal publishPoint) throws ServiceException;

	/**
	 * 获取历史充值模板
	 * @author LeGreen
	 * @date   2021/2/4
	 * @param  memberId
	 * @return java.util.List<com.chengyu.core.model.UmsMemberRecharge>
	 */
    List<UmsMemberRecharge> getRechargeTemplateList(Integer memberId);

    /**
     * 在线充值
     * @author LeGreen
     * @date   2021/12/21
     * @param  member
     * @param  payMethod
     * @param  amount
     * @return java.lang.String
     * @throws ServiceException 业务异常
     */
    String rechargeOnline(UmsMember member, String payMethod, BigDecimal amount) throws ServiceException;

    /**
     * 获取充值记录
     * @author LeGreen
     * @date   2021/12/21
     * @param  orderNo
     * @return com.chengyu.core.model.UmsMemberRecharge
     */
	UmsMemberRecharge getRechargeLogByOrderNo(String orderNo);

	/**
	 * 充值成功回调
	 * @author LeGreen
	 * @date   2021/12/21
	 */
	void rechargSusByCallback(UmsMemberRecharge recharge) throws ServiceException;
}