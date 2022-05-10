package com.chengyu.core.service.goods.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.result.GoodsCateResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.PmsGoodsCateMapper;
import com.chengyu.core.model.PmsGoodsCate;
import com.chengyu.core.model.PmsGoodsCateExample;
import com.chengyu.core.service.goods.GoodsCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @title  商品分类
 * @author LeGreen
 * @date   2022/4/25
 */
@Service
public class GoodsCateServiceImpl implements GoodsCateService {

	@Autowired
	private PmsGoodsCateMapper goodsCateMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addGoodsCate(Integer pid, String name, Integer sort, String img, String target) {
		PmsGoodsCate cate = new PmsGoodsCate();
		if(pid == null){
			cate.setPid(0);
			cate.setLevel(1);
		}else{
			PmsGoodsCate shopCate = goodsCateMapper.selectByPrimaryKey(pid);
			cate.setLevel(shopCate.getLevel()+1);
			cate.setPid(shopCate.getId());
		}
		cate.setName(name);
		cate.setSort(sort);
		cate.setImg(img);
		cate.setStatus(CommonConstant.YES_INT);
		cate.setAddTime(DateUtil.date());
		cate.setUpdTime(cate.getAddTime());
		goodsCateMapper.insert(cate);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateGoodsCate(PmsGoodsCate cate) {
		cate.setUpdTime(DateUtil.date());
		goodsCateMapper.updateByPrimaryKeySelective(cate);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteGoodsCate(Integer id) throws ServiceException {
		PmsGoodsCate cate = goodsCateMapper.selectByPrimaryKey(id);
		PmsGoodsCateExample example = new PmsGoodsCateExample();
		example.createCriteria().andPidEqualTo(cate.getId());
		if(goodsCateMapper.countByExample(example) > 0){
			throw new ServiceException("请先删除下级分类");
		}
		goodsCateMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<GoodsCateResult> getGoodsCateResults(Integer status) {
		PmsGoodsCateExample example = new PmsGoodsCateExample();
		PmsGoodsCateExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("sort asc");
		criteria.andLevelEqualTo(1);
		if(status != null){
			criteria.andStatusEqualTo(status);
		}
		List<PmsGoodsCate> topCateList = goodsCateMapper.selectByExample(example);

		List<GoodsCateResult> cateResultList = new ArrayList<>();
		for(PmsGoodsCate topCate : topCateList){
			GoodsCateResult result = new GoodsCateResult();
			BeanUtil.copyProperties(topCate, result);

			example = new PmsGoodsCateExample();
			criteria = example.createCriteria();
			criteria.andLevelEqualTo(2);
			if(status != null){
				criteria.andStatusEqualTo(status);
			}
			criteria.andPidEqualTo(topCate.getId());
			example.setOrderByClause("sort asc");
			List<PmsGoodsCate> childCateList = goodsCateMapper.selectByExample(example);
//			result.setHasChildren(CollectionUtil.isNotEmpty(childCateList));

			List<GoodsCateResult> twoCateList = new ArrayList<>();
			for(PmsGoodsCate twoCate : childCateList){
				GoodsCateResult result1 = new GoodsCateResult();
				BeanUtil.copyProperties(twoCate, result1);

				example = new PmsGoodsCateExample();
				criteria = example.createCriteria();
				criteria.andLevelEqualTo(3);
				criteria.andPidEqualTo(twoCate.getId());
				if(status != null){
					criteria.andStatusEqualTo(status);
				}
				example.setOrderByClause("sort asc");
				List<PmsGoodsCate> threeChildCateList = goodsCateMapper.selectByExample(example);
				result1.setTopId(topCate.getId());
//				result1.setHasChildren(CollectionUtil.isNotEmpty(threeChildCateList));

				if(CollectionUtil.isNotEmpty(threeChildCateList)){
					List<GoodsCateResult> threeCateList = new ArrayList<>();
					for(PmsGoodsCate threeCate : threeChildCateList){
						GoodsCateResult result2 = new GoodsCateResult();
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
}