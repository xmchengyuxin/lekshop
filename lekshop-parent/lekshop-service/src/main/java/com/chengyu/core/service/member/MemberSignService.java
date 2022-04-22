package com.chengyu.core.service.member;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberSign;

import java.util.List;

/**
 * @类描述   	签到记录接口
 * @作者   		LeGreen
 * @创建时间  	2019年2月26日
 * @版本   		1.00
 * @修改记录
 * <pre>
 *     版本          修改人    		修改日期 			修改内容描述
 *     -----------------------------------------------------------
 *     1.00   	LeGreen    	2019年2月26日             
 *     -----------------------------------------------------------
 * </pre>
 */
public interface MemberSignService {
	
	public void addSign(UmsMember member) throws ServiceException;
	
	public List<UmsMemberSign> getSignPage(Integer memberId, String memberName, Integer page, Integer pageSize) throws ServiceException;

	public Integer getSignDays(Integer memberId) throws ServiceException;
}