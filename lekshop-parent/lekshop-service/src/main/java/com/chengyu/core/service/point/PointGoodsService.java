package com.chengyu.core.service.point;

import com.chengyu.core.domain.form.GoodsSearchForm;
import com.chengyu.core.domain.form.PointGoodsPublishForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.PointGoods;

import java.util.List;

/**
 * @title  积分商城商品
 * @author LeGreen
 * @date   2023/1/4
 */
public interface PointGoodsService {

	/**
	 * 商品列表
	 * @author LeGreen
	 * @date   2022/4/28
	 * @param  form
	 * @param  page
	 * @param  pageSize
	 * @return List<PmsGoods>
	 */
	List<PointGoods> getGoodsList(GoodsSearchForm form, Integer page, Integer pageSize);

	/**
	 * 发布商品
	 * @author LeGreen
	 * @date   2022/4/28
	 * @param  publishForm
	 * @throws ServiceException 业务异常
	 */
	void publishGoods(PointGoodsPublishForm publishForm) throws ServiceException;

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
	PointGoods getGoods(Integer goodsId);

	/**
	 * 减库存
	 * @author LeGreen
	 * @date   2022/5/5
	 * @param  goodsId
	 * @param  num
	 */
	void reduceStock(Integer goodsId, Integer num);

	/**
	 * 加库存
	 * @author LeGreen
	 * @date   2022/5/5
	 * @param  goodsId
	 * @param  num
	 */
	void addStock(Integer goodsId, Integer num);

	/**
	 * 更新商品
	 * @author LeGreen
	 * @date   2022/5/7
	 * @param  goods
	 */
	void updateGoods(PointGoods goods);

}