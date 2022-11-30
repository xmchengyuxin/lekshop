package com.chengyu.core.service.goods.impl;

import com.alibaba.fastjson.JSONArray;
import com.chengyu.core.domain.enums.GoodsEnums;
import com.chengyu.core.domain.form.GoodsPublishSeckillForm;
import com.chengyu.core.domain.form.GoodsPublishSeckillPriceForm;
import com.chengyu.core.mapper.PmsGoodsMapper;
import com.chengyu.core.mapper.PmsGoodsSkuMapper;
import com.chengyu.core.model.PmsGoods;
import com.chengyu.core.model.PmsGoodsSku;
import com.chengyu.core.model.PmsGoodsSkuExample;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.service.goods.GoodsSeckillService;
import com.chengyu.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsSeckillServiceImpl implements GoodsSeckillService {

	@Autowired
	private PmsGoodsMapper goodsMapper;
	@Autowired
	private PmsGoodsSkuMapper goodsSkuMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addSeckillGoods(UmsShop shop, GoodsPublishSeckillForm publishForm) {
		PmsGoods updateGoods = new PmsGoods();
		updateGoods.setId(publishForm.getId());
		updateGoods.setType(GoodsEnums.GoodsType.SECKILL_GOODS.getValue());
		updateGoods.setSeckillBeginTime(publishForm.getSeckillBeginTime());
		updateGoods.setSeckillEndTime(publishForm.getSeckillEndTime());
		updateGoods.setSeckillLimitNum(publishForm.getSeckillLimitNum());

		if(StringUtils.isNotBlank(publishForm.getPriceFormList())) {
			List<GoodsPublishSeckillPriceForm> priceFormList = JSONArray.parseArray(publishForm.getPriceFormList(), GoodsPublishSeckillPriceForm.class);
			for(GoodsPublishSeckillPriceForm priceForm : priceFormList) {
				PmsGoodsSku sku = goodsSkuMapper.selectByPrimaryKey(priceForm.getSkuId());
				PmsGoodsSku updateSku = new PmsGoodsSku();
				updateSku.setId(priceForm.getSkuId());
				updateSku.setOriPrice(sku.getPrice());
				updateSku.setPrice(priceForm.getPrice());
				goodsSkuMapper.updateByPrimaryKeySelective(updateSku);
			}
			updateGoods.setPrice(priceFormList.get(0).getPrice());
		}
		goodsMapper.updateByPrimaryKeySelective(updateGoods);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteSeckill(List<Integer> goodsIdList) {
		//商品SKU恢复原价
		PmsGoodsSkuExample skuExample = new PmsGoodsSkuExample();
		skuExample.createCriteria().andGoodsIdIn(goodsIdList);
		List<PmsGoodsSku> skuList = goodsSkuMapper.selectByExample(skuExample);
		for(PmsGoodsSku sku : skuList) {
			PmsGoodsSku updateSku = new PmsGoodsSku();
			updateSku.setId(sku.getId());
			updateSku.setPrice(sku.getOriPrice());
			goodsSkuMapper.updateByPrimaryKeySelective(updateSku);
		}

		for(Integer goodsId : goodsIdList) {
			PmsGoods updateGoods = new PmsGoods();
			updateGoods.setId(goodsId);
			updateGoods.setType(GoodsEnums.GoodsType.NORMAL_GOODS.getValue());

			skuExample = new PmsGoodsSkuExample();
			skuExample.setOrderByClause("id asc");
			skuExample.createCriteria().andGoodsIdEqualTo(goodsId);
			List<PmsGoodsSku> skus = goodsSkuMapper.selectByExample(skuExample);
			updateGoods.setPrice(skus.get(0).getPrice());
			goodsMapper.updateByPrimaryKeySelective(updateGoods);
		}
	}
}