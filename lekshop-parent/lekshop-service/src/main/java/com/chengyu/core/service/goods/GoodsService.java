package com.chengyu.core.service.goods;

import com.chengyu.core.domain.form.GoodsPublishForm;
import com.chengyu.core.domain.form.GoodsSearchForm;
import com.chengyu.core.domain.result.GoodsResult;
import com.chengyu.core.domain.result.GoodsSkuResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.PmsGoods;
import com.chengyu.core.model.PmsGoodsGroup;
import com.chengyu.core.model.PmsGoodsSku;
import com.chengyu.core.model.UmsShop;

import java.util.List;

/**
 * @title  商品
 * @author LeGreen
 * @date   2022/4/28
 */
public interface GoodsService {

	/**
	 * 商品列表
	 * @author LeGreen
	 * @date   2022/4/28
	 * @param  form
	 * @param  page
	 * @param  pageSize
	 * @return List<PmsGoods>
	 */
	List<PmsGoods> getGoodsList(GoodsSearchForm form, Integer page, Integer pageSize);

	/**
	 * 发布商品
	 * @author LeGreen
	 * @date   2022/4/28
	 * @param  shop
	 * @param  publishForm
	 * @throws ServiceException 业务异常
	 */
	void publishGoods(UmsShop shop, GoodsPublishForm publishForm) throws ServiceException;

	/**
	 * 上架商品
	 * @author LeGreen
	 * @date   2022/4/28
	 * @param  goodsIdList
	 */
	void shangjiaGoods(List<Integer> goodsIdList);

	/**
	 * 下架商品
	 * @author LeGreen
	 * @date   2022/4/28
	 * @param  goodsIdList
	 */
	void xiajiaGoods(List<Integer> goodsIdList);

	/**
	 * 删除商品
	 * @author LeGreen
	 * @date   2022/4/28
	 * @param  goodsIdList
	 */
	void deleteGoods(List<Integer> goodsIdList);

	/**
	 * 商品详情
	 * @author LeGreen
	 * @date   2022/4/28
	 * @param  goodsId
	 * @return PmsGoods
	 */
	PmsGoods getGoods(Integer goodsId);

	/**
	 * 减库存
	 * @author LeGreen
	 * @date   2022/5/5
	 * @param  skuId
	 * @param  num
	 */
	void reduceStock(Integer skuId, Integer num);

	/**
	 * 加库存
	 * @author LeGreen
	 * @date   2022/5/5
	 * @param  skuId
	 * @param  num
	 */
	void addStock(Integer skuId, Integer num);

	/**
	 * 更新商品
	 * @author LeGreen
	 * @date   2022/5/7
	 * @param  goods
	 */
	void updateGoods(PmsGoods goods);

	/**
	 * 获取商品详情
	 * @author LeGreen
	 * @date   2022/5/13
	 * @param  goodsId
	 * @return GoodsResult
	 */
	GoodsResult getGoodsResult(Integer goodsId);

	/**
	 * 获取商品的SKU组合
	 * @author LeGreen
	 * @date   2022/5/16
	 * @param  goodsId
	 * @return GoodsSkuResult
	 */
	GoodsSkuResult getGoodsSkuList(Integer goodsId);

	/**
	 * 获取商品SKU
	 * @author LeGreen
	 * @date   2022/5/16
	 * @param  skuId
	 * @return PmsGoodsSku
	 */
	PmsGoodsSku getGoodsSku(Integer skuId);

	/**
	 * 阶梯拼团规则
	 * @author LeGreen
	 * @date   2022/5/16
	 * @param  goodsId
	 * @return List<PmsGoodsGroup>
	 */
	List<PmsGoodsGroup> getGoodsGroupList(Integer goodsId);

	/**
	 * 获取阶梯拼团
	 * @author LeGreen
	 * @date   2022/5/16
	 * @param  groupId
	 * @return PmsGoodsGroup
	 */
	PmsGoodsGroup getGoodsGroup(Integer groupId);
}