package com.chengyu.core.service.member;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMemberAddress;

import java.util.List;

/**
 * @类描述   	用户收货地址
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
public interface MemberAddressService {

	/**
	 * @功能描述		添加地址
	 * @作者			LeGreen
	 * @创建时间		2020年3月18日
	 * @param 		address
	 * @throws 		ServiceException
	 */
	void addAddress(UmsMemberAddress address) throws ServiceException;
	
	/**
	 * @功能描述		更新地址
	 * @作者			LeGreen
	 * @创建时间		2020年3月18日
	 * @param 		address
	 * @throws 		ServiceException
	 */
	void updateAddress(UmsMemberAddress address) throws ServiceException;
	
	/**
	 * @功能描述		获取默认地址
	 * @作者			LeGreen
	 * @创建时间		2020年3月18日
	 * @param 		memberId
	 * @return
	 * @throws 		ServiceException
	 */
	UmsMemberAddress getDefaultAddressByMemberId(Integer memberId) throws ServiceException;
	
	/**
	 * @功能描述		根据ID获取地址
	 * @作者			LeGreen
	 * @创建时间		2020年3月18日
	 * @param 		id
	 * @return
	 * @throws 		ServiceException
	 */
	UmsMemberAddress getAddressById(Integer id) throws ServiceException;
	
	/**
	 * @功能描述    地址列表
	 * @作者      LeGreen
	 * @创建时间  2018年12月22日
	 * @param 	  memberId
	 * @param 	  page
	 * @param 	  pageSize
	 * @return
	 * @throws 	  ServiceException
	 */
	List<UmsMemberAddress> getAddressList(Integer memberId, Integer page, Integer pageSize) throws ServiceException;

	/**
	 * @功能描述    删除地址
	 * @作者      LeGreen
	 * @创建时间  2018年12月25日
	 * @param 	  id
	 */
	void deleteAddress(Integer id) throws ServiceException;
}