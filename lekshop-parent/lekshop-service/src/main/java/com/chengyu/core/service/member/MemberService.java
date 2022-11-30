package com.chengyu.core.service.member;

import com.chengyu.core.domain.enums.MemberTypes;
import com.chengyu.core.domain.form.MemberSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberLoginLog;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @title  用户管理
 * @author LeGreen
 * @date   2021/1/7
 */
public interface MemberService {
	
	/**
	 * 注册
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  member
	 * @throws ServiceException 业务异常
	 */
	void register(UmsMember member, boolean isShop) throws ServiceException;
	
	/**
	 * 更新用户信息
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  member
	 * @throws ServiceException 业务异常
	 */
	void updateMember(UmsMember member) throws ServiceException;
	
	/**
	 * 用户列表
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  form
	 * @param  page
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.UmsMember>
	 * @throws ServiceException 业务异常
	 */
	List<UmsMember> getMemberList(MemberSearchForm form, Integer page, Integer pageSize);
	
	/**
	 * 获取用户信息
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  id
	 * @return com.chengyu.core.model.UmsMember
	 */
	UmsMember getMemberById(Integer id);
	
	/**
	 * 根据唯一标识获取用户信息
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  params
	 * @param  type
	 * @return com.chengyu.core.model.UmsMember
	 */
	UmsMember getMemberByParams(String params, MemberTypes type);
	
	/**
	 * 获取当前登录用户
	 * @author LeGreen
	 * @date   2021/1/7
	 * @return com.chengyu.core.model.UmsMember
	 */
	UmsMember getCurrentMember();
	
	/**
	 * 获取当前登录用户,可为空
	 * @author LeGreen
	 * @date   2021/1/7
	 * @return com.chengyu.core.model.UmsMember
	 */
	UmsMember getCurrentMemberOrNull();
    
    /**
     * 获取springscurity用户信息
     * @author LeGreen
     * @date   2021/1/7
     * @param  username
     * @return org.springframework.security.core.userdetails.UserDetails
     */
	UserDetails loadUserByUsername(String username);
	
	/**
	 * 登录后获取token
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  username
	 * @param  password
	 * @return java.lang.String
	 * @throws ServiceException 业务异常
	 */
	String login(String username, String password, String ip) throws ServiceException;
	
	/**
	 * 免密码登录
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  username
	 * @return java.lang.String
	 * @throws ServiceException 业务异常
	 */
	String loginByNoPassword(String username) throws ServiceException;
	
	/**
	 * 绑定手机
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  deleteId
	 * @param  member
	 * @throws ServiceException 业务异常
	 */
	void bindPhone(Integer deleteId, UmsMember member) throws ServiceException;

	/**
	 * 获取上次登录记录
	 * @author LeGreen
	 * @date   2021/1/29
	 * @param  memberId
	 * @return com.chengyu.core.model.UmsMemberLoginLog
	 */
	UmsMemberLoginLog getLastLoginLog(Integer memberId);

	/**
	 * 修改邀请人
	 * @author LeGreen
	 * @date   2021/5/29
	 * @param  memberId
	 * @param  inviteUid
	 * @throws ServiceException 业务异常
	 */
    void updateInvite(Integer memberId, String inviteUid) throws ServiceException;

    /**
     * 限制提现
     * @author LeGreen
     * @date   2021/6/21
     * @param  memberId
     * @param  status
     * @throws ServiceException 业务异常
     */
    void limitWithdraw(Integer memberId, Integer status) throws ServiceException;

}