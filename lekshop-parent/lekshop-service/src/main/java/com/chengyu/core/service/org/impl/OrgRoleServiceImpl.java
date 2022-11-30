package com.chengyu.core.service.org.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.TypeReference;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.*;
import com.chengyu.core.model.*;
import com.chengyu.core.service.org.OrgRoleService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrgRoleServiceImpl implements OrgRoleService {

    @Autowired
    private OrgRoleMenuMapper roleMenuMapper;
    @Autowired
    private OrgRoleMapper roleMapper;
    @Autowired
    private OrgDeptMapper deptMapper;
    @Autowired
    private UmsShopAccountRoleMapper accountRoleMapper;
    @Autowired
    private CustomerOrgRoleMapper roleCustomerMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addRole(OrgRole role, String menuIds) {
        OrgDept dept = deptMapper.selectByPrimaryKey(role.getDeptId());
        role.setDeptName(dept.getDeptName());
        role.setUpdTime(DateUtil.date());
        if (role.getId() == null) {
            role.setAddTime(role.getUpdTime());
            roleMapper.insertSelective(role);
        } else {
            roleMapper.updateByPrimaryKeySelective(role);

            OrgRoleMenuExample example = new OrgRoleMenuExample();
            example.createCriteria().andRoleIdEqualTo(role.getId());
            roleMenuMapper.deleteByExample(example);
        }
        if(StringUtils.isNotBlank(menuIds)) {
            roleCustomerMapper.addRoleMenuList(role.getId(), Convert.convert(new TypeReference<List<Integer>>() {
            }, menuIds.split(CommonConstant.DH_REGEX)));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteRole(Integer id) throws ServiceException {
        //判断是否有用户在用此角色
        UmsShopAccountRoleExample example = new UmsShopAccountRoleExample();
        example.createCriteria().andRoleIdEqualTo(id);
        if (accountRoleMapper.countByExample(example) > 0) {
            throw new ServiceException("当前有账号使用此角色,请先移除");
        }
        //逻辑删除
        /*OrgRole updateRole = new OrgRole();
        updateRole.setDelInd(CommonConstant.YES_INT);
        updateRole.setUpdTime(DateUtil.date());
        roleMapper.updateByPrimaryKeySelective(updateRole);*/
        roleMapper.deleteByPrimaryKey(id);

        //删除角色菜单
        OrgRoleMenuExample roleMenuExample = new OrgRoleMenuExample();
        roleMenuExample.createCriteria().andRoleIdEqualTo(id);
        roleMenuMapper.deleteByExample(roleMenuExample);
    }

    @Override
    public List<OrgRole> getAllRolesByDeptId(Integer shopId, Integer deptId) {
        OrgRoleExample example = new OrgRoleExample();
        example.setOrderByClause("sort asc");
        OrgRoleExample.Criteria criteria = example.createCriteria();
        criteria.andDelIndEqualTo(CommonConstant.NO_INT);
        if (deptId != null) {
            criteria.andDeptIdEqualTo(deptId);
        }
        if(shopId != null) {
            criteria.andShopIdEqualTo(shopId);
        }
        return roleMapper.selectByExample(example);
    }

    @Override
    public List<OrgRole> getRoleList(Integer shopId, Integer deptId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        return getAllRolesByDeptId(shopId, deptId);
    }

    @Override
    public List<Integer> getMenusByRoleId(Integer roleId) {
        OrgRoleMenuExample example = new OrgRoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<OrgRoleMenu> menuList = roleMenuMapper.selectByExample(example);
        if(CollectionUtil.isEmpty(menuList)){
            return null;
        }
        return menuList.stream().map(OrgRoleMenu::getMenuId).collect(Collectors.toList());
    }
}