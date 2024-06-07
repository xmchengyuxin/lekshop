package com.chengyu.core.service.point;

import com.chengyu.core.domain.result.GoodsCateResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.PointGoodsCate;

import java.util.List;

/**
 * @title  积分商品分类
 * @author LeGreen
 * @date   2022/4/25
 */
public interface PointGoodsCateService {

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
	void updateGoodsCate(PointGoodsCate cate);

	/**
	 * 删除商品分类
	 * @author LeGreen
	 * @date   2022/4/25
	 * @param  id
	 */
	void deleteGoodsCate(Integer id) throws ServiceException;

	/**
	 * 获取商品分类
	 * @author LeGreen
	 * @date   2022/4/25
	 * @return List<GoodsCateResult>
	 */
	List<GoodsCateResult> getGoodsCateResults(Integer status);

	/**
	 * 获取商品分类名称
	 * @author LeGreen
	 * @date   2022/5/14
	 * @param  cateIdList
	 * @return String
	 */
	String getGoodsCateName(List<Integer> cateIdList);

	/**
	 * 根据上级分类查询下级分类
	 * @author LeGreen
	 * @date   2022/5/14
	 * @param  pid
	 * @return List<PointGoodsCate>
	 */
	List<PointGoodsCate> getListByPid(Integer pid);
}