package com.chengyu.core.service.member;

import com.chengyu.core.domain.enums.MemberNewsEnums;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberNews;

import java.util.List;

/**
 * 系统消息
 * @author LeGreen
 * @date   2021/1/7
 */
public interface MemberNewsService {

	/**
	 * 获取系统消息列表
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId 用户ID
	 * @param  type 消息类型
	 * @param  readStatus 0未读  1已读
	 * @param  page
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.UmsMemberNews>
	 * @throws ServiceException 业务异常
	 */
	List<UmsMemberNews> getMemberNewsList(Integer memberId, Integer type, String readStatus, Integer page, Integer pageSize) throws ServiceException;
	
	/**
	 * 发消息
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  member
	 * @param  newsType
	 * @param  title
	 * @param  img
	 * @param  content
	 * @throws ServiceException 业务异常
	 */
	void addMemberNews(UmsMember member, MemberNewsEnums.MemberNewsTypes newsType, String title, String img, String content);
	
	/**
	 * 统计未读
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId
	 * @param  type
	 * @return java.lang.Long
	 * @throws ServiceException 业务异常
	 */
	Long countUnReadNews(Integer memberId, Integer type) throws ServiceException;
	
	/**
	 * 已读
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  id
	 * @throws ServiceException 业务异常
	 */
	void readById(Integer id) throws ServiceException;
	
	/**
	 * 全部已读
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId
	 * @param  type
	 * @throws ServiceException 业务异常
	 */
	void readAll(Integer memberId, Integer type) throws ServiceException;
}