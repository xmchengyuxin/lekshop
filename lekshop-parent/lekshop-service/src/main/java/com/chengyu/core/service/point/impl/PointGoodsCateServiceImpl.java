package com.chengyu.core.service.point.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.result.GoodsCateResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.PointGoodsCateMapper;
import com.chengyu.core.mapper.PointGoodsCateMapper;
import com.chengyu.core.model.PointGoodsCate;
import com.chengyu.core.model.PointGoodsCateExample;
import com.chengyu.core.model.PointGoodsCate;
import com.chengyu.core.service.goods.GoodsCateService;
import com.chengyu.core.service.point.PointGoodsCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PointGoodsCateServiceImpl implements PointGoodsCateService {

	@Autowired
	private PointGoodsCateMapper goodsCateMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addGoodsCate(Integer pid, String name, Integer sort, String img, String target) {
		PointGoodsCate cate = new PointGoodsCate();
		if(pid == null){
			cate.setPid(0);
			cate.setLevel(1);
		}else{
			PointGoodsCate shopCate = goodsCateMapper.selectByPrimaryKey(pid);
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
	public void updateGoodsCate(PointGoodsCate cate) {
		cate.setUpdTime(DateUtil.date());
		goodsCateMapper.updateByPrimaryKeySelective(cate);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteGoodsCate(Integer id) throws ServiceException {
		PointGoodsCate cate = goodsCateMapper.selectByPrimaryKey(id);
		PointGoodsCateExample example = new PointGoodsCateExample();
		example.createCriteria().andPidEqualTo(cate.getId());
		if(goodsCateMapper.countByExample(example) > 0){
			throw new ServiceException("请先删除下级分类");
		}
		goodsCateMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<GoodsCateResult> getGoodsCateResults(Integer status) {
		PointGoodsCateExample example = new PointGoodsCateExample();
		PointGoodsCateExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("sort asc");
		criteria.andLevelEqualTo(1);
		if(status != null){
			criteria.andStatusEqualTo(status);
		}
		List<PointGoodsCate> topCateList = goodsCateMapper.selectByExample(example);

		List<GoodsCateResult> cateResultList = new ArrayList<>();
		for(PointGoodsCate topCate : topCateList){
			GoodsCateResult result = new GoodsCateResult();
			BeanUtil.copyProperties(topCate, result);

			example = new PointGoodsCateExample();
			criteria = example.createCriteria();
			criteria.andLevelEqualTo(2);
			if(status != null){
				criteria.andStatusEqualTo(status);
			}
			criteria.andPidEqualTo(topCate.getId());
			example.setOrderByClause("sort asc");
			List<PointGoodsCate> childCateList = goodsCateMapper.selectByExample(example);
//			result.setHasChildren(CollectionUtil.isNotEmpty(childCateList));

			List<GoodsCateResult> twoCateList = new ArrayList<>();
			for(PointGoodsCate twoCate : childCateList){
				GoodsCateResult result1 = new GoodsCateResult();
				BeanUtil.copyProperties(twoCate, result1);

				example = new PointGoodsCateExample();
				criteria = example.createCriteria();
				criteria.andLevelEqualTo(3);
				criteria.andPidEqualTo(twoCate.getId());
				if(status != null){
					criteria.andStatusEqualTo(status);
				}
				example.setOrderByClause("sort asc");
				List<PointGoodsCate> threeChildCateList = goodsCateMapper.selectByExample(example);
				result1.setTopId(topCate.getId());
//				result1.setHasChildren(CollectionUtil.isNotEmpty(threeChildCateList));

				if(CollectionUtil.isNotEmpty(threeChildCateList)){
					List<GoodsCateResult> threeCateList = new ArrayList<>();
					for(PointGoodsCate threeCate : threeChildCateList){
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

	@Override
	public String getGoodsCateName(List<Integer> cateIdList) {
		PointGoodsCateExample example = new PointGoodsCateExample();
		example.setOrderByClause("level asc");
		example.createCriteria().andIdIn(cateIdList);
		List<PointGoodsCate> list = goodsCateMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(list)){
			return null;
		}
		return list.stream().map(PointGoodsCate::getName).collect(Collectors.joining("/"));
	}

	@Override
	public List<PointGoodsCate> getListByPid(Integer pid) {
		PointGoodsCateExample example = new PointGoodsCateExample();
		example.setOrderByClause("sort asc");
		PointGoodsCateExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(CommonConstant.YES_INT);
		if(pid != null){
			criteria.andPidEqualTo(pid);
		}
		return goodsCateMapper.selectByExample(example);
	}
}