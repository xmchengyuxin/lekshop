package com.chengyu.core.service.goods.impl;

import cn.hutool.core.date.DateUtil;
import com.chengyu.core.mapper.PmsGoodsParamsMapper;
import com.chengyu.core.model.PmsGoodsParams;
import com.chengyu.core.model.PmsGoodsParamsExample;
import com.chengyu.core.service.goods.GoodsParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @title  商品属性
 * @author LeGreen
 * @date   2022/4/25
 */
@Service
public class GoodsParamsServiceImpl implements GoodsParamsService {

	@Autowired
	private PmsGoodsParamsMapper goodsParamsMapper;

	@Override
	public List<PmsGoodsParams> getGoodsParamsList(Integer goodsId) {
		PmsGoodsParamsExample example = new PmsGoodsParamsExample();
		example.setOrderByClause("sort asc");
		example.createCriteria().andGoodsIdEqualTo(goodsId);
		return goodsParamsMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addGoodsParams(PmsGoodsParams goodsParams) {
		goodsParams.setUpdTime(DateUtil.date());
		if(goodsParams.getId() == null){
			goodsParamsMapper.insert(goodsParams);
		}else{
			goodsParamsMapper.updateByPrimaryKeySelective(goodsParams);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteGoodsParams(Integer id) {
		goodsParamsMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteGoodsParamsByGoodsId(Integer goodsId) {
		PmsGoodsParamsExample example = new PmsGoodsParamsExample();
		example.createCriteria().andGoodsIdEqualTo(goodsId);
		goodsParamsMapper.deleteByExample(example);
	}
}