package com.chengyu.core.service.system.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.domain.result.MenuResult;
import com.chengyu.core.domain.result.MenuSingleResult;
import com.chengyu.core.mapper.SysAdminTypeMapper;
import com.chengyu.core.mapper.SysMenuMapper;
import com.chengyu.core.model.SysAdminType;
import com.chengyu.core.model.SysMenu;
import com.chengyu.core.model.SysMenuExample;
import com.chengyu.core.service.system.MenuService;
import com.chengyu.core.util.RedisUtil;
import com.chengyu.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @title  菜单
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private SysMenuMapper menuMapper;
	@Autowired
	private SysAdminTypeMapper adminTypeMapper;

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuResult> getMenuList() {
		List<MenuResult> menuResultList = (List<MenuResult>) redisUtil.getRedisValue(RedisEnums.MENU_KEY.getKey());
		if(menuResultList != null) {
			return menuResultList;
		}
		
		menuResultList = this.getMenuResultList(null);
		redisUtil.setRedisValue(RedisEnums.MENU_KEY.getKey(), menuResultList);
		return menuResultList;
	}
	
	@Override
	public List<MenuResult> getMenuListByTypeId(Integer typeId) {
		SysAdminType adminType = adminTypeMapper.selectByPrimaryKey(typeId);
		if(adminType == null || StringUtils.isBlank(adminType.getMenus())) {
			return null;
		}
		
		List<Integer> menuIds = new ArrayList<>();
		for(String menuId : adminType.getMenus().split(CommonConstant.DH_REGEX)){
			menuIds.add(Integer.valueOf(menuId));
		}
		return this.getMenuResultList(menuIds);
	}
	
	private List<MenuResult> getMenuResultList(List<Integer> menuIds){
		SysMenuExample example = new SysMenuExample();
		SysMenuExample.Criteria criteria = example.createCriteria(); 
		criteria.andLevelEqualTo(1);
		if(menuIds != null && !menuIds.isEmpty()){
			criteria.andIdIn(menuIds);
		}
		example.setOrderByClause("sort asc");
		List<SysMenu> topMenuList = menuMapper.selectByExample(example);
		
		List<MenuResult> menuResultList = new ArrayList<>();
		for(SysMenu topMenu : topMenuList){
			MenuResult result = new MenuResult();
			result.setId(topMenu.getId());
			result.setName(topMenu.getName());
			result.setUrl(topMenu.getUrl());
			
			example = new SysMenuExample();
			criteria = example.createCriteria(); 
			criteria.andLevelEqualTo(2);
			criteria.andPidEqualTo(topMenu.getId());
			if(menuIds != null && !menuIds.isEmpty()){
				criteria.andIdIn(menuIds);
			}
			example.setOrderByClause("sort asc");
			List<SysMenu> childMenuList = menuMapper.selectByExample(example);
			
			List<MenuResult> twoMenuList = new ArrayList<>();
			for(SysMenu twoMenu : childMenuList){
				MenuResult result1 = new MenuResult();
				result1.setId(twoMenu.getId());
				result1.setName(twoMenu.getName());
				result1.setUrl(twoMenu.getUrl());
				
				example = new SysMenuExample();
				criteria = example.createCriteria(); 
				criteria.andLevelEqualTo(3);
				criteria.andPidEqualTo(twoMenu.getId());
				if(menuIds != null && !menuIds.isEmpty()){
					criteria.andIdIn(menuIds);
				}
				example.setOrderByClause("sort asc");
				List<SysMenu> threeChildMenuList = menuMapper.selectByExample(example);
				
				if(threeChildMenuList != null && !threeChildMenuList.isEmpty()){
					List<MenuResult> threeMenuList = new ArrayList<>();
					for(SysMenu threeMenu : threeChildMenuList){
						MenuResult result2 = new MenuResult();
						result2.setId(threeMenu.getId());
						result2.setName(threeMenu.getName());
						result2.setUrl(threeMenu.getUrl());
						threeMenuList.add(result2);
					}
					result1.setChildren(threeMenuList);
				}
				twoMenuList.add(result1);
			}
			result.setChildren(twoMenuList);
			menuResultList.add(result);
		}
		return menuResultList;
	}

	@Override
	public MenuSingleResult getMenuById(Integer id) {
		MenuSingleResult result = new MenuSingleResult();
		
		SysMenu menu = menuMapper.selectByPrimaryKey(id);
		result.setMenu(menu);
		
		if(menu.getPid() != null && menu.getPid() != 0){
			SysMenu topMenu = menuMapper.selectByPrimaryKey(menu.getPid());
			result.setTopName(topMenu.getName());
		}
		return result;
	}

	@Override
	public void updateMenu(SysMenu menu) {
		if(menu.getId() != null){
			menu.setUpdTime(new Date());
			menuMapper.updateByPrimaryKeySelective(menu);
		}else{
			menu.setAddTime(new Date());
			menu.setUpdTime(menu.getAddTime());
			if(menu.getPid() == null){
				menu.setLevel(1);
			}else{
				SysMenu topMenu = menuMapper.selectByPrimaryKey(menu.getPid());
				menu.setLevel(topMenu.getLevel()+1);
			}
			menuMapper.insert(menu);
		}
		redisUtil.delete(RedisEnums.MENU_KEY.getKey());
		
	}

	@Override
	public void deleteMenu(Integer id) {
		menuMapper.deleteByPrimaryKey(id);
		
		SysMenuExample example = new SysMenuExample();
		example.createCriteria().andPidEqualTo(id);
		menuMapper.deleteByExample(example);

		redisUtil.delete(RedisEnums.MENU_KEY.getKey());
	}

	@Override
	public Integer getMenuIdByRouter(String router) {
		Integer menuId = (Integer) redisUtil.getRedisValue(router);
		if(menuId != null){
			return menuId;
		}
		SysMenuExample example = new SysMenuExample();
		example.createCriteria().andUrlEqualTo(router);
		List<SysMenu> list = menuMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(list)){
			return null;
		}
		redisUtil.setRedisValue(router, list.get(0).getId());
		return list.get(0).getId();
	}

}