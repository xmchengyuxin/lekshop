package com.chengyu.core.service.org;


import com.chengyu.core.domain.result.ShopMenuResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OrgMenu;

import java.util.List;

/**
 * @title  菜单
 * @author LeGreen
 * @date   2022/7/6
 */
public interface OrgMenuService {

    /**
     * 添加菜单
     * @author LeGreen
     * @date   2022/7/6
     * @param  menu 菜单信息
     */
    void addMenu(OrgMenu menu);

    /**
     * 删除菜单
     * @author LeGreen
     * @date   2022/7/6
     * @param  id 菜单ID
     * @throws ServiceException 业务异常
     */
    void deleteMenu(Integer id) throws ServiceException;

    /**
     * 修改菜单信息
     * @author LeGreen
     * @date   2022/7/6
     * @param  menu 菜单信息
     */
    void updateMenu(OrgMenu menu);

    /**
     * 获取所有的下级菜单
     * @author LeGreen
     * @date   2022/7/6
     * @return List<ShopMenuResult>
     */
    List<ShopMenuResult> getAllMenus();
}
