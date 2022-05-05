package com.chengyu.core.service.shop;

import com.chengyu.core.domain.form.ShopSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsShop;

import java.util.List;

/**
 * @title  店铺接口
 * @author LeGreen
 * @date   2022/4/28
 */
public interface ShopService {

	/**
	 * 店铺列表
	 * @author LeGreen
	 * @date   2022/4/28
	 * @param  form
	 * @param  page
	 * @param  pageSize
	 * @return List<UmsShop>
	 */
	List<UmsShop> getShopList(ShopSearchForm form, Integer page, Integer pageSize);

	/**
	 * 更新店铺信息
	 * @author LeGreen
	 * @date   2022/4/28
	 * @param  shop
	 */
	void updateShop(UmsShop shop);

	/**
	 * 删除店铺
	 * @author LeGreen
	 * @date   2022/4/28
	 * @param  shopId
	 * @throws ServiceException 业务异常
	 */
	void deleteShop(Integer shopId) throws ServiceException;

	/**
	 * 店铺详情
	 * @author LeGreen
	 * @date   2022/4/28
	 * @param  shopId
	 * @return UmsShop
	 */
	UmsShop getShopById(Integer shopId);

	/**
	 * 根据用户ID获取店铺
	 * @author LeGreen
	 * @date   2022/4/28
	 * @param  memberId
	 * @return UmsShop
	 */
	UmsShop getShopByMemberId(Integer memberId);

	/**
	 * 获取店铺的用户
	 * @author LeGreen
	 * @date   2022/5/5
	 * @param  shopId
	 * @return UmsMember
	 */
	UmsMember getMemberByShopId(Integer shopId);
}