package com.chengyu.core.service.point.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.GoodsEnums;
import com.chengyu.core.domain.form.GoodsSearchForm;
import com.chengyu.core.domain.form.PointGoodsPublishForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.BaseMapper;
import com.chengyu.core.mapper.PointGoodsMapper;
import com.chengyu.core.model.PointGoods;
import com.chengyu.core.model.PointGoodsExample;
import com.chengyu.core.service.point.PointGoodsCateService;
import com.chengyu.core.service.point.PointGoodsService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PointGoodsServiceImpl implements PointGoodsService {

	@Autowired
	private PointGoodsMapper goodsMapper;
	@Autowired
	private BaseMapper baseMapper;
	@Autowired
	private PointGoodsCateService goodsCateService;

	@Override
	public List<PointGoods> getGoodsList(GoodsSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		PointGoodsExample example = new PointGoodsExample();
		if(StringUtils.isNotBlank(form.getSort())){
			example.setOrderByClause(form.getSort());
		}else{
			example.setOrderByClause("sort asc, weight desc, id desc");
		}
		PointGoodsExample.Criteria criteria = example.createCriteria();
		criteria.andStatusNotEqualTo(GoodsEnums.GoodsStatus.DEL.getValue());
		if(form.getCateId() != null){
			criteria.andCateIdEqualTo(form.getCateId());
		}
		if(form.getCatePid() != null){
			criteria.andCatePidEqualTo(form.getCatePid());
		}
		if(form.getCateTid() != null){
			criteria.andCateTidEqualTo(form.getCateTid());
		}
		if(StringUtils.isNotBlank(form.getTitle())){
			criteria.andTitleLike("%"+form.getTitle()+"%");
		}
		if(form.getStatus() != null){
			criteria.andStatusEqualTo(form.getStatus());
		}
		if(CollectionUtil.isNotEmpty(form.getCatePidList())){
			criteria.andCatePidIn(form.getCatePidList());
		}
		if(form.getMinAmount() != null){
			criteria.andPriceGreaterThanOrEqualTo(form.getMinAmount());
		}
		if(form.getMaxAmount() != null){
			criteria.andPriceLessThanOrEqualTo(form.getMaxAmount());
		}
		return goodsMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void publishGoods(PointGoodsPublishForm publishForm) throws ServiceException {
		PointGoods goods = new PointGoods();
		BeanUtil.copyProperties(publishForm, goods);
		Integer tempGoodsId = goods.getId();
		String[] cateIdList = publishForm.getGoodsCateIds().split(CommonConstant.DH_REGEX);
		goods.setCatePid(Integer.parseInt(cateIdList[0]));
		if(cateIdList.length >=2 ){
			goods.setCateTid(Integer.parseInt(cateIdList[1]));
		}
		if(cateIdList.length >=3){
			goods.setCateId(Integer.parseInt(cateIdList[2]));
		}
		goods.setCateIds(publishForm.getGoodsCateIds());
		goods.setCateName(goodsCateService.getGoodsCateName(CollectionUtil.newArrayList(goods.getCatePid(), goods.getCateTid(), goods.getCateId())));
		goods.setSort(9999);
		goods.setWeight(0);
		goods.setUpdTime(DateUtil.date());
		if(goods.getId() == null){
			goods.setAddTime(goods.getUpdTime());
			goodsMapper.insertSelective(goods);
		}else{
			goodsMapper.updateByPrimaryKeySelective(goods);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void shangjiaGoods(List<Integer> goodsIdList) {
		PointGoodsExample example = new PointGoodsExample();
		example.createCriteria().andIdIn(goodsIdList);

		PointGoods updateGoods = new PointGoods();
		updateGoods.setStatus(GoodsEnums.GoodsStatus.SELL.getValue());
		goodsMapper.updateByExampleSelective(updateGoods, example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void xiajiaGoods(List<Integer> goodsIdList) {
		PointGoodsExample example = new PointGoodsExample();
		example.createCriteria().andIdIn(goodsIdList);

		PointGoods updateGoods = new PointGoods();
		updateGoods.setStatus(GoodsEnums.GoodsStatus.IN_WAREHOUSE.getValue());
		goodsMapper.updateByExampleSelective(updateGoods, example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteGoods(List<Integer> goodsIdList) {
		PointGoodsExample example = new PointGoodsExample();
		example.createCriteria().andIdIn(goodsIdList);

		PointGoods updateGoods = new PointGoods();
		updateGoods.setStatus(GoodsEnums.GoodsStatus.DEL.getValue());
		goodsMapper.updateByExampleSelective(updateGoods, example);
	}

	@Override
	public PointGoods getGoods(Integer goodsId) {
		PointGoodsExample example = new PointGoodsExample();
		example.createCriteria().andIdEqualTo(goodsId).andStatusNotEqualTo(GoodsEnums.GoodsStatus.DEL.getValue());
		List<PointGoods> goodsList = goodsMapper.selectByExampleWithBLOBs(example);
		return CollectionUtil.isNotEmpty(goodsList) ? goodsList.get(0) : null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void reduceStock(Integer goodsId, Integer num) {
		baseMapper.update("update point_goods set stock = stock-"+ num +" where id = "+goodsId);
		//如果库存小于0， 则更新为0
		PointGoodsExample example = new PointGoodsExample();
		example.createCriteria().andIdEqualTo(goodsId).andStockLessThan(0);
		PointGoods updateStock = new PointGoods();
		updateStock.setStock(0);
		goodsMapper.updateByExampleSelective(updateStock, example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addStock(Integer goodsId, Integer num) {
		baseMapper.update("update point_goods set stock = stock+"+ num +" where id = "+goodsId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateGoods(PointGoods goods) {
		goods.setUpdTime(DateUtil.date());
		goodsMapper.updateByPrimaryKeyWithBLOBs(goods);
	}
}