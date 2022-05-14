package com.chengyu.core.service.shop.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.result.ShopCateResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.UmsShopCateMapper;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.model.UmsShopCate;
import com.chengyu.core.model.UmsShopCateExample;
import com.chengyu.core.service.shop.ShopCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @title  店铺分类
 * @author LeGreen
 * @date   2022/4/25
 */
@Service
public class ShopCateServiceImpl implements ShopCateService {

	@Autowired
	private UmsShopCateMapper shopCateMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addShopCate(UmsShop shop, Integer pid, String name, Integer sort, String img) {
		UmsShopCate cate = new UmsShopCate();
		cate.setShopId(shop.getId());
		cate.setShopName(shop.getName());
		if(pid == null){
			cate.setPid(0);
			cate.setLevel(1);
		}else{
			UmsShopCate shopCate = shopCateMapper.selectByPrimaryKey(pid);
			cate.setLevel(shopCate.getLevel()+1);
			cate.setPid(shopCate.getId());
		}
		cate.setName(name);
		cate.setSort(sort);
		cate.setImg(img);
		cate.setStatus(CommonConstant.YES_INT);
		cate.setAddTime(DateUtil.date());
		cate.setUpdTime(cate.getAddTime());
		shopCateMapper.insert(cate);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateShopCate(UmsShopCate cate) {
		cate.setUpdTime(DateUtil.date());
		shopCateMapper.updateByPrimaryKeySelective(cate);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteShopCate(Integer id) throws ServiceException {
		UmsShopCate cate = shopCateMapper.selectByPrimaryKey(id);
		UmsShopCateExample example = new UmsShopCateExample();
		example.createCriteria().andPidEqualTo(cate.getId());
		if(shopCateMapper.countByExample(example) > 0){
			throw new ServiceException("请先删除下级分类");
		}
		shopCateMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<ShopCateResult> getShopCateResults(Integer shopId, Integer status) {
		UmsShopCateExample example = new UmsShopCateExample();
		UmsShopCateExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("sort asc");
		criteria.andLevelEqualTo(1).andShopIdEqualTo(shopId);
		if(status != null){
			criteria.andStatusEqualTo(status);
		}
		List<UmsShopCate> topCateList = shopCateMapper.selectByExample(example);

		List<ShopCateResult> cateResultList = new ArrayList<>();
		for(UmsShopCate topCate : topCateList){
			ShopCateResult result = new ShopCateResult();
			BeanUtil.copyProperties(topCate, result);

			example = new UmsShopCateExample();
			criteria = example.createCriteria();
			criteria.andLevelEqualTo(2).andShopIdEqualTo(shopId);
			if(status != null){
				criteria.andStatusEqualTo(status);
			}
			criteria.andPidEqualTo(topCate.getId());
			example.setOrderByClause("sort asc");
			List<UmsShopCate> childCateList = shopCateMapper.selectByExample(example);

			List<ShopCateResult> twoCateList = new ArrayList<>();
			for(UmsShopCate twoCate : childCateList){
				ShopCateResult result1 = new ShopCateResult();
				BeanUtil.copyProperties(twoCate, result1);

				example = new UmsShopCateExample();
				criteria = example.createCriteria();
				criteria.andLevelEqualTo(3).andShopIdEqualTo(shopId);
				criteria.andPidEqualTo(twoCate.getId());
				if(status != null){
					criteria.andStatusEqualTo(status);
				}
				example.setOrderByClause("sort asc");
				List<UmsShopCate> threeChildCateList = shopCateMapper.selectByExample(example);
				result1.setTopId(topCate.getId());

				if(CollectionUtil.isNotEmpty(threeChildCateList)){
					List<ShopCateResult> threeCateList = new ArrayList<>();
					for(UmsShopCate threeCate : threeChildCateList){
						ShopCateResult result2 = new ShopCateResult();
						BeanUtil.copyProperties(threeCate, result2);
						result2.setTopId(topCate.getId());
						threeCateList.add(result2);
					}
					result1.setChildren(threeCateList);
				}
				twoCateList.add(result1);
			}
			result.setChildren(twoCateList);
			cateResultList.add(result);
		}
		return cateResultList;
	}

	@Override
	public List<UmsShopCate> getAllShopCateByLevel(Integer shopId) {
		List<UmsShopCate> newList = new ArrayList<>();
		List<UmsShopCate> topCateList = getCateListByLevel(shopId, 1, null);
		for(UmsShopCate cate : topCateList){
			newList.add(cate);
			List<UmsShopCate> twoCateList = getCateListByLevel(shopId, 2, cate.getId());
			for(UmsShopCate twoCate : twoCateList){
				twoCate.setName("    ┗ "+twoCate.getName());
				newList.add(twoCate);
				List<UmsShopCate> threeCateList = getCateListByLevel(shopId, 3, twoCate.getId());
				for(UmsShopCate threeCate : threeCateList){
					threeCate.setName("       ┝ "+threeCate.getName());
					newList.add(threeCate);
				}
			}
		}
		return newList;
	}

	@Override
	public UmsShopCate getShopCateById(Integer shopCateId) {
		return shopCateMapper.selectByPrimaryKey(shopCateId);
	}

	List<UmsShopCate> getCateListByLevel(Integer shopId, Integer level, Integer pid){
		UmsShopCateExample example = new UmsShopCateExample();
		UmsShopCateExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("sort asc");
		criteria.andLevelEqualTo(level).andShopIdEqualTo(shopId).andStatusEqualTo(CommonConstant.YES_INT);
		if(pid != null){
			criteria.andPidEqualTo(pid);
		}
		return shopCateMapper.selectByExample(example);
	}
}