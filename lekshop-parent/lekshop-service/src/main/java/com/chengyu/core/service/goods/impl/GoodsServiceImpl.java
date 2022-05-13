package com.chengyu.core.service.goods.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.enums.GoodsEnums;
import com.chengyu.core.domain.form.GoodsPublishForm;
import com.chengyu.core.domain.form.GoodsSearchForm;
import com.chengyu.core.domain.result.GoodsResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.BaseMapper;
import com.chengyu.core.mapper.PmsGoodsGroupMapper;
import com.chengyu.core.mapper.PmsGoodsMapper;
import com.chengyu.core.mapper.PmsGoodsSkuMapper;
import com.chengyu.core.model.*;
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
	@Autowired
	private PmsGoodsSkuMapper goodsSkuMapper;
	@Autowired
	private BaseMapper baseMapper;
	@Autowired
	private PmsGoodsGroupMapper goodsGroupMapper;

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

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void reduceStock(Integer skuId, Integer num) {
		baseMapper.update("update pms_goods_sku set stock = stock-"+ num +" where id = "+skuId);
		//如果库存小于0， 则更新为0
		PmsGoodsSkuExample example = new PmsGoodsSkuExample();
		example.createCriteria().andIdEqualTo(skuId).andStockLessThan(0);
		PmsGoodsSku updateSku = new PmsGoodsSku();
		updateSku.setStock(0);
		goodsSkuMapper.updateByExampleSelective(updateSku, example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addStock(Integer skuId, Integer num) {
		baseMapper.update("update pms_goods_sku set stock = stock+"+ num +" where id = "+skuId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateGoods(PmsGoods goods) {
		goods.setUpdTime(DateUtil.date());
		goodsMapper.updateByPrimaryKeyWithBLOBs(goods);
	}

	@Override
	public GoodsResult getGoodsResult(Integer goodsId) {
		GoodsResult result = new GoodsResult();
		result.setGoods(this.getGoods(goodsId));

		//拼团规则
		PmsGoodsGroupExample groupExample = new PmsGoodsGroupExample();
		groupExample.setOrderByClause("num asc");
		groupExample.createCriteria().andGoodsIdEqualTo(goodsId);
		result.setGoodsGroupList(goodsGroupMapper.selectByExample(groupExample));
		return result;
	}
}