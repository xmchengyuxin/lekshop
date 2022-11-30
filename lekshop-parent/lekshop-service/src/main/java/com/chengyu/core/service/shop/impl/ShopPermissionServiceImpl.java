package com.chengyu.core.service.shop.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.domain.result.ShopAccountOrgResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.*;
import com.chengyu.core.model.*;
import com.chengyu.core.service.org.OrgRoleService;
import com.chengyu.core.service.shop.ShopPermissionsService;
import com.chengyu.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ShopPermissionServiceImpl implements ShopPermissionsService {

    @Autowired
    private OrgMenuMapper menuMapper;
    @Autowired
    private OrgRoleMenuMapper roleMenuMapper;
    @Autowired
    private OrgDeptMapper deptMapper;
    @Autowired
    private UmsShopAccountRoleMapper accountRoleMapper;
    @Autowired
    private OrgRoleMapper roleMapper;
    @Autowired
    private UmsShopAccountMapper shopAccountMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private OrgRoleService roleService;

    @Override
    public void checkPermissions(UmsShopAccount account, String permission) throws ServiceException {
        OrgMenuExample example = new OrgMenuExample();
        example.createCriteria().andPermsEqualTo(permission);
        List<OrgMenu> menuList = menuMapper.selectByExample(example);
        if (CollectionUtil.isEmpty(menuList)) {
            throw new ServiceException("权限标识错误");
        }

        if (account.getRoleId() == null) {
            throw new ServiceException("查询不到用户当前的角色");
        }

        OrgRoleMenuExample roleMenuExample = new OrgRoleMenuExample();
        roleMenuExample.createCriteria().andRoleIdEqualTo(account.getRoleId()).andMenuIdEqualTo(menuList.get(0).getId());
        if (roleMenuMapper.countByExample(roleMenuExample) == 0) {
            throw new ServiceException("暂无权限");
        }
    }

    @Override
    public List<ShopAccountOrgResult> getAccountOrg(UmsShopAccount account) {
        UmsShopAccountRoleExample example = new UmsShopAccountRoleExample();
        example.createCriteria().andAccountIdEqualTo(account.getId());
        List<UmsShopAccountRole> roleList = accountRoleMapper.selectByExample(example);
        if(CollectionUtil.isEmpty(roleList)){
            return null;
        }
        List<ShopAccountOrgResult> memberOrgResultList = new ArrayList<>();
        for(UmsShopAccountRole memberRole: roleList){
            ShopAccountOrgResult result = new ShopAccountOrgResult();
            OrgDept dept = deptMapper.selectByPrimaryKey(memberRole.getDeptId());
            result.setDeptId(memberRole.getDeptId());
            result.setDeptName(dept.getDeptName());
            result.setRoleId(memberRole.getRoleId());
            result.setRoleName(roleMapper.selectByPrimaryKey(memberRole.getRoleId()).getRoleName());
            memberOrgResultList.add(result);
        }
        return memberOrgResultList;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
    public void changeRole(UmsShopAccount account, Integer roleId) throws ServiceException {
        UmsShopAccountRoleExample example = new UmsShopAccountRoleExample();
        example.createCriteria().andAccountIdEqualTo(account.getId()).andRoleIdEqualTo(roleId);
        List<UmsShopAccountRole> roleList = accountRoleMapper.selectByExample(example);
        if(CollectionUtil.isEmpty(roleList)){
            throw new ServiceException("您无权限进行此操作");
        }
        UmsShopAccountRole memberRole = roleList.get(0);
        OrgRole role = roleMapper.selectByPrimaryKey(memberRole.getRoleId());
        UmsShopAccount updateMember = new UmsShopAccount();
        updateMember.setId(account.getId());
        updateMember.setRoleId(roleId);
        updateMember.setRoleName(role.getRoleName());
        updateMember.setDeptId(role.getDeptId());
        updateMember.setDeptName(role.getDeptName());
        shopAccountMapper.updateByPrimaryKeySelective(updateMember);

        redisUtil.delete(account.getUsername());
    }

    @Override
    public Map<String, Object> getAccountCurrentOrg(UmsShopAccount account) {
        ShopAccountOrgResult result = new ShopAccountOrgResult();
        if(account.getDeptId() == null) {
            return new HashMap<>();
        }
        OrgDept dept = deptMapper.selectByPrimaryKey(account.getDeptId());
        result.setDeptId(account.getDeptId());
        result.setDeptName(dept.getDeptName());
        result.setRoleId(account.getRoleId());
        result.setRoleName(roleMapper.selectByPrimaryKey(account.getRoleId()).getRoleName());

        //获取当前所有的菜单按钮权限
        List<Integer> menuIdList = roleService.getMenusByRoleId(account.getRoleId());
        if(CollectionUtil.isNotEmpty(menuIdList)){
            OrgMenuExample example = new OrgMenuExample();
            example.createCriteria().andIdIn(menuIdList);
            List<OrgMenu> menuList = menuMapper.selectByExample(example);
            if(CollectionUtil.isNotEmpty(menuList)){
                result.setPermissions(menuList.stream().map(OrgMenu::getPerms).collect(Collectors.toList()));
            }
        }
        return BeanUtil.beanToMap(result);
    }

}