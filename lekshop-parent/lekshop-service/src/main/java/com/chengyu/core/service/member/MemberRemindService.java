package com.chengyu.core.service.member;

import com.chengyu.core.domain.enums.MemberRemindEnums;
import com.chengyu.core.domain.enums.MemberRemindEnums.AdminRemindTypes;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberRemind;
import com.chengyu.core.model.UmsShop;

import java.util.List;

/**
 * @title  消息提醒推送
 * @author LeGreen
 * @date   2021/12/28
 */
public interface MemberRemindService {

	/**
	 * 推送用户提醒
	 * @author LeGreen
	 * @date   2021/12/28
	 * @param  member
	 * @param  type
	 * @param  content
	 */
	void addMemberRemind(UmsMember member, MemberRemindEnums.MemberRemindTypes type, String content);

	/**
	 * 推送商家提醒
	 * @author LeGreen
	 * @date   2022/5/21
	 * @param  shopId
	 * @param  type
	 * @param  content
	 */
	void addShopRemind(Integer shopId, MemberRemindEnums.MemberRemindTypes type, String content);

	/**
	 * 推送后台提醒
	 * @author LeGreen
	 * @date   2021/12/28
	 * @param  type
	 * @param  content
	 */
	void addAdminRemind(AdminRemindTypes type, String content);

	/**
	 * 提醒列表
	 * @author LeGreen
	 * @date   2021/12/28
	 * @param  memberId
	 * @param  page
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.UmsMemberRemind>
	 */
	List<UmsMemberRemind> getRemindList(Integer memberId, Integer toType, Integer page, Integer pageSize);

	/**
	 * 获取未读数量
	 * @author LeGreen
	 * @date   2021/12/28
	 * @param  memberId
	 * @return long
	 */
	long countUnReadNum(Integer memberId, Integer toType);

	/**
	 * 已读
	 * @author LeGreen
	 * @date   2021/12/28
	 * @param  memberId
	 */
	void readAll(Integer memberId, Integer toType);
}