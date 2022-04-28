package com.chengyu.core.service.goods.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.domain.enums.GoodsEnums;
import com.chengyu.core.domain.form.GoodsPublishForm;
import com.chengyu.core.domain.form.GoodsSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.PmsGoodsMapper;
import com.chengyu.core.model.PmsGoods;
import com.chengyu.core.model.PmsGoodsExample;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.service.goods.GoodsService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @title  商品管理
 * @author LeGreen
 * @date   2022/4/28
 */
@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private PmsGoodsMapper goodsMapper;

	@Override
	public List<PmsGoods> getGoodsList(GoodsSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		PmsGoodsExample example = new PmsGoodsExample();
		if(StringUtils.isNotBlank(form.getSort())){
			example.setOrderByClause(form.getSort());
		}else{
			example.setOrderByClause("sort asc, weight desc, id desc");
		}
		PmsGoodsExample.Criteria criteria = example.createCriteria();
		criteria.andStatusNotEqualTo(GoodsEnums.GoodsStatus.DEL.getValue());
		if(form.getShopId() != null){
			criteria.andShopIdEqualTo(form.getShopId());
		}
		if(form.getCateId() != null){
			criteria.andCateIdEqualTo(form.getCateId());
		}
		if(form.getCatePid() != null){
			criteria.andCatePidEqualTo(form.getCatePid());
		}
		if(form.getCateTid() != null){
			criteria.andCateTidEqualTo(form.getCateTid());
		}
		if(form.getShopCateId() != null){
			criteria.andShopCateIdEqualTo(form.getShopCateId());
		}
		if(StringUtils.isNotBlank(form.getTitle())){
			criteria.andTitleLike("%"+form.getTitle()+"%");
		}
		if(form.getStatus() != null){
			criteria.andStatusEqualTo(form.getStatus());
		}
		if(form.getType() != null){
			criteria.andTypeEqualTo(form.getType());
		}
		return goodsMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void publishGoods(UmsShop shop, GoodsPublishForm publishForm) throws ServiceException {

	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void shangjiaGoods(List<Integer> goodsIdList) {
		PmsGoodsExample example = new PmsGoodsExample();
		example.createCriteria().andIdIn(goodsIdList);

		PmsGoods updateGoods = new PmsGoods();
		updateGoods.setStatus(GoodsEnums.GoodsStatus.SELL.getValue());
		goodsMapper.updateByExampleSelective(updateGoods, example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void xiajiaGoods(List<Integer> goodsIdList) {
		PmsGoodsExample example = new PmsGoodsExample();
		example.createCriteria().andIdIn(goodsIdList);

		PmsGoods updateGoods = new PmsGoods();
		updateGoods.setStatus(GoodsEnums.GoodsStatus.IN_WAREHOUSE.getValue());
		goodsMapper.updateByExampleSelective(updateGoods, example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteGoods(List<Integer> goodsIdList) {
		PmsGoodsExample example = new PmsGoodsExample();
		example.createCriteria().andIdIn(goodsIdList);

		PmsGoods updateGoods = new PmsGoods();
		updateGoods.setStatus(GoodsEnums.GoodsStatus.DEL.getValue());
		goodsMapper.updateByExampleSelective(updateGoods, example);
	}

	@Override
	public PmsGoods getGoods(Integer goodsId) {
		PmsGoodsExample example = new PmsGoodsExample();
		example.createCriteria().andIdEqualTo(goodsId).andStatusNotEqualTo(GoodsEnums.GoodsStatus.DEL.getValue());
		List<PmsGoods> goodsList = goodsMapper.selectByExample(example);
		return CollectionUtil.isNotEmpty(goodsList) ? goodsList.get(0) : null;
	}
}