package com.chengyu.core.service.shop;


import com.chengyu.core.domain.result.ShopAccountOrgResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsShopAccount;

import java.util.List;
import java.util.Map;

/**
 * @title  用户权限
 * @author LeGreen
 * @date   2022/7/8
 */
public interface ShopPermissionsService {

    /**
     * 校验用户权限
     * @author LeGreen
     * @date   2022/7/8
     * @param  account 当前用户
     * @param  permission 权限标识
     * @throws ServiceException 业务异常
     */
    void checkPermissions(UmsShopAccount account, String permission) throws ServiceException;

    /**
     * 查询用户的所有角色
     * @author LeGreen
     * @date   2022/7/18
     * @param  account 当前用户
     * @return List<ShopAccountOrgResult>
     */
    List<ShopAccountOrgResult> getAccountOrg(UmsShopAccount account);

    /**
     * 切换用户角色
     * @author LeGreen
     * @date   2022/7/18
     * @param  account 当前用户
     * @param  roleId 角色ID
     * @throws ServiceException 业务异常
     */
    void changeRole(UmsShopAccount account, Integer roleId) throws ServiceException;

    /**
     * 获取用户的当前角色信息
     * @author LeGreen
     * @date   2022/7/19
     * @param  account 用户
     * @return Map<String,Object>
     */
    Map<String, Object> getAccountCurrentOrg(UmsShopAccount account);
}
