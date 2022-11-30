package com.chengyu.core.service.org;


import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OrgRole;

import java.util.List;

/**
 * @title  角色
 * @author LeGreen
 * @date   2022/7/6
 */
public interface OrgRoleService {

    /**
     * 添加修改角色
     * @author LeGreen
     * @date   2022/7/6
     * @param  role 角色信息
     * @param  menuIds 菜单权限
     */
    void addRole(OrgRole role, String menuIds);

    /**
     * 删除角色
     * @author LeGreen
     * @date   2022/7/6
     * @param  id 角色ID
     * @throws ServiceException 业务异常
     */
    void deleteRole(Integer id) throws ServiceException;

    /**
     * 查询该部门下所有的角色
     * @author LeGreen
     * @date   2022/7/6
     * @param  deptId 部门ID
     * @return List<OrgRole>
     */
    List<OrgRole> getAllRolesByDeptId(Integer shopId, Integer deptId);

    /**
     * 角色列表
     * @author LeGreen
     * @date   2022/7/7
     * @param  deptId 部门ID
     * @param  page 分页信息
     * @param  pageSize 分页信息
     * @return List<OrgRole>
     */
    List<OrgRole> getRoleList(Integer shopId, Integer deptId, Integer page, Integer pageSize);

    /**
     * 查询角色的关联菜单
     * @author LeGreen
     * @date   2022/7/7
     * @param  roleId 角色ID
     * @return List<Integer>
     */
    List<Integer> getMenusByRoleId(Integer roleId);
}
