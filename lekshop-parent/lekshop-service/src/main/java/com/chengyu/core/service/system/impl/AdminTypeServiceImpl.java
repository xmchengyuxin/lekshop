package com.chengyu.core.service.system.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.SysAdminMapper;
import com.chengyu.core.mapper.SysAdminTypeMapper;
import com.chengyu.core.model.SysAdmin;
import com.chengyu.core.model.SysAdminExample;
import com.chengyu.core.model.SysAdminType;
import com.chengyu.core.model.SysAdminTypeExample;
import com.chengyu.core.service.system.AdminTypeService;
import com.chengyu.core.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @title  管理员类型
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class AdminTypeServiceImpl implements AdminTypeService {
	
	@Autowired
	private SysAdminTypeMapper adminTypeMapper;
	@Autowired
	private SysAdminMapper adminMapper;
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public void addAdminType(SysAdminType adminType) {
		adminType.setAddTime(new Date());
		adminType.setUpdTime(adminType.getAddTime());
		adminType.setStatus(CommonConstant.YES);
		adminTypeMapper.insertSelective(adminType);

		redisUtil.deletePattern("menuRole-");
	}

	@Override
	public void updateAdminType(SysAdminType adminType) {
		adminType.setUpdTime(new Date());
		adminTypeMapper.updateByPrimaryKeySelective(adminType);
		
		//更新管理员角色名称
		SysAdminExample example = new SysAdminExample();
		example.createCriteria().andTypeIdEqualTo(adminType.getId());
		SysAdmin admin = new SysAdmin();
		admin.setTypeName(adminType.getName());
		adminMapper.updateByExampleSelective(admin, example);

		redisUtil.deletePattern("menuRole-");
	}

	@Override
	public void deleteAdminType(Integer id) throws ServiceException{
		//判断是否有管理员
		SysAdminExample example = new SysAdminExample();
		example.createCriteria().andTypeIdEqualTo(id);
		long num = adminMapper.countByExample(example);
		if(num > 0) {
			throw new ServiceException("该角色底下已绑定管理员,请先解除绑定");
		}
		adminTypeMapper.deleteByPrimaryKey(id);

		redisUtil.deletePattern("menuRole-");
	}

	@Override
	public List<SysAdminType> getAdminTypeList(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		SysAdminTypeExample example = new SysAdminTypeExample();
        return adminTypeMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<Integer> getAdminTypeIds(Integer menuId) {
		List<Integer> adminTypeIds = (List<Integer>) redisUtil.getRedisValue("menuRole-"+menuId);
		if(CollectionUtil.isNotEmpty(adminTypeIds)){
			return adminTypeIds;
		}
		List<SysAdminType> list = this.getAdminTypeList(1, 9999);
		if(CollectionUtil.isEmpty(list)){
			return null;
		}
		adminTypeIds = new ArrayList<>();
		for(SysAdminType adminType : list){
			String[] menus = adminType.getMenus().split(CommonConstant.DH_REGEX);
			if(CollectionUtil.contains(Arrays.asList(menus), menuId.toString())){
				adminTypeIds.add(adminType.getId());
			}
		}
		redisUtil.setRedisValue("menuRole-"+menuId, adminTypeIds);
		return adminTypeIds;
	}


}