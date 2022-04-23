package com.chengyu.core.service.member;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMemberInvoice;

import java.util.List;

/**
 * @类描述   	用户发票
 * @作者   		LeGreen
 * @创建时间  	2020年3月18日
 * @版本   		1.00
 * @修改记录
 * <pre>
 *     版本          修改人    		修改日期 			修改内容描述
 *     -----------------------------------------------------------
 *     1.00   	LeGreen    	2020年3月18日             
 *     -----------------------------------------------------------
 * </pre>
 */
public interface MemberInvoiceService {

	/**
	 * @功能描述		添加发票抬头
	 * @作者			LeGreen
	 * @创建时间		2020年3月18日
	 * @param 		invoice
	 * @throws 		ServiceException
	 */
	void addInvoice(UmsMemberInvoice invoice) throws ServiceException;
	
	/**
	 * @功能描述		更新发票抬头
	 * @作者			LeGreen
	 * @创建时间		2020年3月18日
	 * @param 		invoice
	 * @throws 		ServiceException
	 */
	void updateInvoice(UmsMemberInvoice invoice) throws ServiceException;
	
	/**
	 * @功能描述		获取默认发票抬头
	 * @作者			LeGreen
	 * @创建时间		2020年3月18日
	 * @param 		memberId
	 * @return
	 * @throws 		ServiceException
	 */
	UmsMemberInvoice getDefaultInvoiceByMemberId(Integer memberId) throws ServiceException;
	
	/**
	 * @功能描述		根据ID获取发票抬头
	 * @作者			LeGreen
	 * @创建时间		2020年3月18日
	 * @param 		id
	 * @return
	 * @throws 		ServiceException
	 */
	UmsMemberInvoice getInvoiceById(Integer id) throws ServiceException;
	
	/**
	 * @功能描述    发票抬头列表
	 * @作者      LeGreen
	 * @创建时间  2018年12月22日
	 * @param 	  memberId
	 * @param 	  page
	 * @param 	  pageSize
	 * @return
	 * @throws 	  ServiceException
	 */
	List<UmsMemberInvoice> getInvoiceList(Integer memberId, Integer page, Integer pageSize) throws ServiceException;

	/**
	 * @功能描述    删除发票抬头
	 * @作者      LeGreen
	 * @创建时间  2018年12月25日
	 * @param 	  id
	 */
	void deleteInvoice(Integer id) throws ServiceException;
}