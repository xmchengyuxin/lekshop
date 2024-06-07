package com.chengyu.core.service.org.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.domain.result.ShopMenuResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.OrgMenuMapper;
import com.chengyu.core.mapper.OrgRoleMenuMapper;
import com.chengyu.core.model.OrgMenu;
import com.chengyu.core.model.OrgMenuExample;
import com.chengyu.core.model.OrgRoleMenuExample;
import com.chengyu.core.service.org.OrgMenuService;
import com.chengyu.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrgMenuServiceImpl implements OrgMenuService {

    @Autowired
    private OrgMenuMapper menuMapper;
    @Autowired
    private OrgRoleMenuMapper roleMenuMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addMenu(OrgMenu menu) {
        if (menu.getParentId() != null && menu.getParentId() != 0) {
            OrgMenu parentMenu = menuMapper.selectByPrimaryKey(menu.getParentId());
            menu.setTreeIds(parentMenu.getTreeIds() + "," + parentMenu.getId());
        } else {
            menu.setParentId(0);
            menu.setTreeIds("0");
        }
        menu.setAddTime(DateUtil.date());
        menu.setUpdTime(menu.getAddTime());
        menuMapper.insertSelective(menu);

        redisUtil.delete(RedisEnums.MENU.getKey());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteMenu(Integer id) throws ServiceException {
        OrgMenu menu = menuMapper.selectByPrimaryKey(id);
        //如果有下级或者，该部门下有角色，无法删除
        OrgMenuExample example = new OrgMenuExample();
        example.createCriteria().andParentIdEqualTo(id);
        if (menuMapper.countByExample(example) > 0) {
            throw new ServiceException("请先删除下级菜单");
        }
        menuMapper.deleteByPrimaryKey(id);

        //删除关联此菜单的权限
        OrgRoleMenuExample roleMenuExample = new OrgRoleMenuExample();
        roleMenuExample.createCriteria().andMenuIdEqualTo(id);
        roleMenuMapper.deleteByExample(roleMenuExample);

        redisUtil.delete(RedisEnums.MENU.getKey());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateMenu(OrgMenu menu) {
        OrgMenu updateMenu = new OrgMenu();
        updateMenu.setId(menu.getId());
        updateMenu.setMenuName(menu.getMenuName());
        updateMenu.setSort(menu.getSort());
        updateMenu.setUrl(menu.getUrl());
        updateMenu.setMenuType(menu.getMenuType());
        updateMenu.setPerms(menu.getPerms());
        updateMenu.setUpdTime(DateUtil.date());
        menuMapper.updateByPrimaryKeySelective(updateMenu);

        redisUtil.delete(RedisEnums.MENU.getKey());
    }

    @Override
    public List<ShopMenuResult> getAllMenus() {
        List<ShopMenuResult> resultList = (List<ShopMenuResult>) redisUtil.getRedisValue(RedisEnums.MENU.getKey());
        if(CollectionUtil.isNotEmpty(resultList)){
            return resultList;
        }
        List<OrgMenu> list = this.getMenuList();
        if (CollectionUtil.isEmpty(list)) {
            return null;
        }
        //将菜单转换成Result
        List<ShopMenuResult> menuList = list.stream().map((menu) -> {
            ShopMenuResult result = new ShopMenuResult();
            BeanUtil.copyProperties(menu, result);
            result.setParentName(getMenuName(menu.getParentId()));
            return result;
        }).collect(Collectors.toList());
        //使用filter去进行拦截，进行判断
        resultList = menuList.stream().filter(menu -> 0 == menu.getParentId())
                .peek(menu -> menu.setChildren(createChildList(menu, menuList)))
                .sorted(Comparator.comparing(ShopMenuResult::getSort))
                .collect(Collectors.toList());
        redisUtil.setRedisValue(RedisEnums.MENU.getKey(), resultList);
        return resultList;
    }

    private static List<ShopMenuResult> createChildList(ShopMenuResult menu, List<ShopMenuResult> menuList) {
        return menuList.stream().filter(model -> menu.getId().equals(model.getParentId()))
                .peek(model -> model.setChildren(createChildList(model, menuList)))
                .sorted((Comparator.comparing(ShopMenuResult::getSort))).collect(Collectors.toList());
    }

    private List<OrgMenu> getMenuList() {
        OrgMenuExample example = new OrgMenuExample();
        example.setOrderByClause("sort asc");
        return menuMapper.selectByExample(example);
    }

    private String getMenuName(Integer id){
        if(id == null || id == 0){
            return "";
        }else{
            return menuMapper.selectByPrimaryKey(id).getMenuName();
        }
    }
}