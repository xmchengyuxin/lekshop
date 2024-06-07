package com.chengyu.core.service.goods.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.result.GoodsRelateResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.mapper.PmsGoodsMapper;
import com.chengyu.core.mapper.PmsGoodsRelateMapper;
import com.chengyu.core.model.PmsGoodsExample;
import com.chengyu.core.model.PmsGoodsRelate;
import com.chengyu.core.model.PmsGoodsRelateExample;
import com.chengyu.core.service.goods.GoodsRelateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsRelateServiceImpl implements GoodsRelateService {

	@Autowired
	private PmsGoodsRelateMapper goodsRelateMapper;
	@Autowired
	private PmsGoodsMapper goodsMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addGoodsRelate(PmsGoodsRelate relate) {
		if(relate.getId() == null) {
			goodsRelateMapper.insertSelective(relate);
		} else {
			goodsRelateMapper.updateByPrimaryKeySelective(relate);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteGoodsRelate(List<Integer> idList) {
		PmsGoodsRelateExample example = new PmsGoodsRelateExample();
		example.createCriteria().andIdIn(idList);
		goodsRelateMapper.deleteByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateSort(Integer relateId, Integer sort) {
		PmsGoodsRelate updateRelate = new PmsGoodsRelate();
		updateRelate.setId(relateId);
		updateRelate.setSort(sort);
		goodsRelateMapper.updateByPrimaryKeySelective(updateRelate);
	}

	@Override
	public CommonPage<GoodsRelateResult> getGoodsRelateList(Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		PmsGoodsRelateExample example = new PmsGoodsRelateExample();
		example.setOrderByClause("sort asc");
		List<PmsGoodsRelate> list = goodsRelateMapper.selectByExample(example);
		List<GoodsRelateResult> relateResults = new ArrayList<>();
		for(PmsGoodsRelate relate : list){
			GoodsRelateResult relateResult = new GoodsRelateResult();
			relateResult.setRelate(relate);

			List<Integer> goodsIds = Convert.convert(new TypeReference<List<Integer>>() {}, relate.getGoodsIds().split(CommonConstant.DH_REGEX));
			PmsGoodsExample goodsExample = new PmsGoodsExample();
			goodsExample.setOrderByClause("sort asc");
			goodsExample.createCriteria().andIdIn(goodsIds);
			relateResult.setGoodsList(goodsMapper.selectByExample(goodsExample));
			relateResults.add(relateResult);
		}

		PageInfo pageInfo = new PageInfo<>(list);
		pageInfo.setList(relateResults);
		return CommonPage.restPage(pageInfo);
	}
}