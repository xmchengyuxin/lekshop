package com.chengyu.core.service.member;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberRel;

import java.util.List;

/**
 * @title  用户关系接口
 * @author LeGreen
 * @date   2021/1/7
 */
public interface MemberRelService {

	/**
	 * 根据上下级建立层级关系
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  upMemberId
	 * @param  downMemberId
	 * @throws ServiceException 业务异常
	 */
	void addMemberRel(Integer upMemberId, Integer downMemberId) throws ServiceException;
	
	/**
	 * 根据用户ID获取所有的上级
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId
	 * @return java.util.List<com.chengyu.core.model.UmsMemberRel>
	 * @throws ServiceException 业务异常
	 */
	List<UmsMemberRel> getAllUpMemberByMemberId(Integer memberId) throws ServiceException;
	
	/**
	 * 根据几级分销获取分销用户,排列为[一级分销,二级分销...]
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId
	 * @param  fenxiaoLevel
	 * @return java.util.List<com.chengyu.core.model.UmsMember>
	 * @throws ServiceException 业务异常
	 */
	List<UmsMember> getUpMemberByFenxiaoLevel(Integer memberId, Integer fenxiaoLevel) throws ServiceException;
	
	/**
	 * 根据用户编号获取所有的下级
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId
	 * @return java.util.List<com.chengyu.core.model.UmsMember>
	 * @throws ServiceException 业务异常
	 */
	List<UmsMember> getAllDownMemberByMemberId(Integer memberId) throws ServiceException;

	/**
	 * 按级别获取下级
	 * @author LeGreen
	 * @date   2021/1/9
	 * @param  currentMemberId
	 * @param  level
	 * @param  page
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.UmsMemberRel>
	 */
	List<UmsMemberRel> getDownMemberByLevel(Integer currentMemberId, Integer level, Integer page, Integer pageSize);

	/**
	 * 查询直推/二级/三级人数
	 * @author LeGreen
	 * @date   2021/1/9
	 * @param  memberId
	 * @return long
	 */
	long countDownNum(Integer memberId, Integer level);

	/**
	 * 获取所有的下级直推ID
	 * @author LeGreen
	 * @date   2021/12/8
	 * @param  memberId
	 * @return java.util.List<java.lang.Integer>
	 * @throws ServiceException 业务异常
	 */
	List<Integer> getAllDownMemberIds(Integer memberId);

}