package com.chengyu.core.service.system;

import com.chengyu.core.domain.result.MenuResult;
import com.chengyu.core.domain.result.MenuSingleResult;
import com.chengyu.core.model.SysMenu;

import java.util.List;

/**
 * @title  菜单管理
 * @author LeGreen
 * @date   2021/1/6
 */
public interface MenuService {
	
	/**
	 * 获取所有的菜单
	 * @author LeGreen
	 * @date   2021/1/6
	 * @return java.util.List<com.chengyu.core.domain.result.MenuResult>
	 */
	List<MenuResult> getMenuList();

	/**
	 * 根据角色获取所有的菜单
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  typeId
	 * @return java.util.List<com.chengyu.core.domain.result.MenuResult>
	 */
	List<MenuResult> getMenuListByTypeId(Integer typeId);

	/**
	 * 获取菜单详情
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 * @return com.chengyu.core.domain.result.MenuSingleResult
	 */
	MenuSingleResult getMenuById(Integer id);

	/**
	 * 更新菜单
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  menu
	 * @return void
	 */
	void updateMenu(SysMenu menu);

	/**
	 * 删除菜单
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 * @return void
	 */
	void deleteMenu(Integer id);

	/**
	 * 根据路由获取菜单ID
	 * @author LeGreen
	 * @date   2021/12/29
	 * @param  router
	 * @return java.lang.Integer
	 */
    Integer getMenuIdByRouter(String router);
}