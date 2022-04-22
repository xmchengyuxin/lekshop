package com.chengyu.core.service.system;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.SysAdminType;

import java.util.List;

/**
 * @title  管理员角色
 * @author LeGreen
 * @date   2021/1/6
 */
public interface AdminTypeService {

	/**
	 * 添加管理员角色
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  adminType
	 */
	void addAdminType(SysAdminType adminType);

	/**
	 * 更新管理员角色
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  adminType
	 */
	void updateAdminType(SysAdminType adminType);

	/**
	 * 删除管理员角色
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 * @throws ServiceException 业务异常
	 */
	void deleteAdminType(Integer id) throws ServiceException;

	/**
	 * 管理员角色分页
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  pageNum
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.SysAdminType>
	 */
	List<SysAdminType> getAdminTypeList(Integer pageNum, Integer pageSize);

	/**
	 * 获取拥有此菜单权限的角色
	 * @author LeGreen
	 * @date   2021/12/29
	 * @param  menuId
	 * @return java.util.List<java.lang.Integer>
	 */
	List<Integer> getAdminTypeIds(Integer menuId);
}