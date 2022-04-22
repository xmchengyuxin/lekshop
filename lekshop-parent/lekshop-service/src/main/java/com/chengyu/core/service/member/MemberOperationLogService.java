package com.chengyu.core.service.member;

import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberOperationLog;

import java.util.List;

/**
 * 用户操作记录
 * @author LeGreen
 * @date   2021/1/7
 */
public interface MemberOperationLogService {

	/**
	 * 添加操作记录
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  member
	 * @param  ip
	 * @param  remark
	 * @param  params
	 */
	void addMemberOperationLog(UmsMember member, String ip, String remark, String params);
	
	/**
	 * 获取操作记录
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId
	 * @param  memberName
	 * @param  keyword
	 * @param  page
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.UmsMemberOperationLog>
	 */
	List<UmsMemberOperationLog> getMemberOperationLogList(Integer memberId, String memberName, String keyword, Integer agentId, Integer page, Integer pageSize);

	/**
	 * 删除几天前的日志
	 * @author LeGreen
	 * @date   2021/1/22
	 * @param  days
	 */
	void deleteLogByDays(Integer days);
}