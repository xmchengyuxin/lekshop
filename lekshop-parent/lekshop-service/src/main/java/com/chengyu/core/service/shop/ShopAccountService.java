package com.chengyu.core.service.shop;

import com.chengyu.core.domain.result.ShopAccountRoleResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsShopAccount;
import com.chengyu.core.model.UmsShopAccountLoginLog;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @title  店铺子账号
 * @author LeGreen
 * @date   2022/9/15
 */
public interface ShopAccountService {
	
	/**
	 * 注册
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  account
	 * @throws ServiceException 业务异常
	 */
	void register(UmsShopAccount account) throws ServiceException;
	
	/**
	 * 更新子账号信息
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  account
	 * @throws ServiceException 业务异常
	 */
	void updateAccount(UmsShopAccount account) throws ServiceException;
	
	/**
	 * 子账号列表
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  shopId
	 * @param  page
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.UmsShopAccount>
	 */
	List<UmsShopAccount> getAccountList(Integer shopId, Integer page, Integer pageSize);
	
	/**
	 * 获取子账号信息
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  id
	 * @return com.chengyu.core.model.UmsShopAccount
	 */
	UmsShopAccount getAccountById(Integer id);
	
	/**
	 * 根据唯一标识获取子账号信息
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  username
	 * @return com.chengyu.core.model.UmsShopAccount
	 */
	UmsShopAccount getAccountByUsername(String username);
	
	/**
	 * 获取当前登录子账号
	 * @author LeGreen
	 * @date   2021/1/7
	 * @return com.chengyu.core.model.UmsShopAccount
	 */
	UmsShopAccount getCurrentAccount();
	
	/**
	 * 获取当前登录子账号,可为空
	 * @author LeGreen
	 * @date   2021/1/7
	 * @return com.chengyu.core.model.UmsShopAccount
	 */
	UmsShopAccount getCurrentAccountOrNull();
    
    /**
     * 获取springscurity子账号信息
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
	 * 获取上次登录记录
	 * @author LeGreen
	 * @date   2021/1/29
	 * @param  accountId
	 * @return com.chengyu.core.model.UmsShopAccountLoginLog
	 */
	UmsShopAccountLoginLog getLastLoginLog(Integer accountId);

	/**
	 * 添加角色
	 * @author LeGreen
	 * @date   2022/7/8
	 * @param  accountId 用户ID
	 * @param  roleIds 角色ID集合
	 */
	void addRole(Integer accountId, String roleIds);

	/**
	 * 删除用户角色
	 * @author LeGreen
	 * @date   2022/7/8
	 * @param  accountRoleId 用户角色ID
	 */
	void deleteAccountRole(Integer accountRoleId);

	/**
	 * 用户角色列表
	 * @author LeGreen
	 * @date   2022/7/8
	 * @param  accountId 用户ID
	 * @return List<AccountRoleResult>
	 */
	List<ShopAccountRoleResult> getRoleList(Integer accountId);

	/**
	 * 获取部门下的用户
	 * @author LeGreen
	 * @date   2022/7/14
	 * @param  deptId 部门ID
	 * @return List<UmsAccount>
	 */
	List<UmsShopAccount> getAccountListByDept(Integer deptId);
}