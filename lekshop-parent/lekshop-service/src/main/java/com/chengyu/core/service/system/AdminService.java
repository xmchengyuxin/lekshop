package com.chengyu.core.service.system;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.SysAdmin;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @title  管理员
 * @author LeGreen
 * @date   2021/1/6
 */
public interface AdminService {

	/**
	 * 添加管理员
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  admin
	 */
	void addAdmin(SysAdmin admin);

	/**
	 * 更新管理员
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  admin
	 */
	void updateAdmin(SysAdmin admin);

	/**
	 * 删除管理员
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 * @return void
	 * @throws ServiceException
	 */
	void deleteAdmin(Integer id) throws ServiceException;

	/**
	 * 管理员列表
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  typeId
	 * @param  name
	 * @param  pageNum
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.SysAdmin>
	 */
	List<SysAdmin> getAdminList(Integer typeId, String name, Integer pageNum, Integer pageSize);

	/**
	 * 根据用户名获取管理员
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  username
	 * @return com.chengyu.core.model.SysAdmin
	 */
	SysAdmin getAdminByUsername(String username);

	/**
	 * 获取SPRING SCURITY的UserDetails
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  username
	 * @return org.springframework.security.core.userdetails.UserDetails
	 */
	UserDetails loadUserByUsername(String username);
	
	/**
	 * 登录
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  username
	 * @param  password
	 * @param  ip
	 * @return java.lang.String
	 */
	String login(String username, String password, String ip);

	/**
	 * 根据角色获取所有的管理员
	 * @author LeGreen
	 * @date   2021/12/29
	 * @param  adminTypeIds
	 * @return java.util.List<com.chengyu.core.model.SysAdmin>
	 */
	List<SysAdmin> getAllAdminByTypeIds(List<Integer> adminTypeIds);
}