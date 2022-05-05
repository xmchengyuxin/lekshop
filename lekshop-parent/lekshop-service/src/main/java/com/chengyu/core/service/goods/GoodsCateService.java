package com.chengyu.core.service.goods;

import com.chengyu.core.domain.result.GoodsCateResult;
import com.chengyu.core.model.PmsGoodsCate;

import java.util.List;

/**
 * @title  商品商品分类
 * @author LeGreen
 * @date   2022/4/25
 */
public interface GoodsCateService {

	/**
	 * 添加商品分类
	 * @author LeGreen
	 * @date   2022/4/28
	 * @param  pid
	 * @param  name
	 * @param  sort
	 * @param  img
	 * @param  target
	 */
	void addGoodsCate(Integer pid, String name, Integer sort, String img, String target);

	/**
	 * 更新商品分类
	 * @author LeGreen
	 * @date   2022/4/25
	 * @param  cate
	 */
	void updateGoodsCate(PmsGoodsCate cate);

	/**
	 * 删除商品分类
	 * @author LeGreen
	 * @date   2022/4/25
	 * @param  id
	 */
	void deleteGoodsCate(Integer id);

	/**
	 * 获取商品分类
	 * @author LeGreen
	 * @date   2022/4/25
	 * @return List<GoodsCateResult>
	 */
	List<GoodsCateResult> getGoodsCateResults(Integer status);
}