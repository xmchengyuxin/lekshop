package com.chengyu.core.service.goods;

import com.chengyu.core.model.PmsGoodsParams;

import java.util.List;

/**
 * @title  商品参数
 * @author LeGreen
 * @date   2022/4/25
 */
public interface GoodsParamsService {

	/**
	 * 商品属性列表
	 * @author LeGreen
	 * @date   2022/4/28
	 * @param  goodsId
	 * @return List<PmsGoodsParams>
	 */
	List<PmsGoodsParams> getGoodsParamsList(Integer goodsId);

	/**
	 * 添加修改商品属性
	 * @author LeGreen
	 * @date   2022/4/28
	 * @param  goodsParams
	 */
	void addGoodsParams(PmsGoodsParams goodsParams);

	/**
	 * 删除商品属性
	 * @author LeGreen
	 * @date   2022/4/28
	 * @param  id
	 */
	void deleteGoodsParams(Integer id);

	/**
	 * 删除商品属性
	 * @author LeGreen
	 * @date   2022/4/28
	 * @param  goodsId
	 */
	void deleteGoodsParamsByGoodsId(Integer goodsId);
}