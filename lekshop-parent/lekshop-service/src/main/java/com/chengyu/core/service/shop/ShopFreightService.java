package com.chengyu.core.service.shop;

import com.chengyu.core.domain.result.ShopFreightResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.model.UmsShopFreightTemplate;
import com.chengyu.core.model.UmsShopFreightTemplateArea;

import java.math.BigDecimal;
import java.util.List;

/**
 * @title  运费模板
 * @author LeGreen
 * @date   2022/4/25
 */
public interface ShopFreightService {

	/**
	 * 获取运费模板列表
	 * @author LeGreen
	 * @date   2022/4/25
	 * @param  shopId
	 * @param  name
	 * @param  page
	 * @param  pageSize
	 * @return List<UmsShopFreightTemplate>
	 */
	List<UmsShopFreightTemplate> getFreightTemplateList(Integer shopId, String name, Integer page, Integer pageSize);

	/**
	 * 添加运费模板
	 * @author LeGreen
	 * @date   2022/4/25
	 * @param  shop
	 * @param  template
	 * @param  areaList
	 * @return Integer
	 * @throws ServiceException 业务异常
	 */
	Integer addFreightTemplate(UmsShop shop, UmsShopFreightTemplate template, List<UmsShopFreightTemplateArea> areaList) throws ServiceException;

	/**
	 * 删除运费模板
	 * @author LeGreen
	 * @date   2022/4/25
	 * @param  id
	 * @throws ServiceException 业务异常
	 */
	void deleteFreightTemplate(Integer id);

	/**
	 * 获取店铺下所有的运费模板
	 * @author LeGreen
	 * @date   2022/4/25
	 * @param  shopId
	 * @return List<UmsShopFreightTemplate>
	 */
	List<UmsShopFreightTemplate> getAllFreightTemplate(Integer shopId);

	/**
	 * 获取运费模板详情
	 * @author LeGreen
	 * @date   2022/4/25
	 * @param  id
	 * @return ShopFreightResult
	 */
	ShopFreightResult getFreightTemplate(Integer id) throws ServiceException;

	/**
	 * 计算运费
	 * @author LeGreen
	 * @date   2022/4/25
	 * @param  freightId
	 * @param  provinceCode
	 * @param  cityCode
	 * @param  num
	 * @param  weight
	 * @return BigDecimal
	 */
	BigDecimal caculateFreightFee(Integer freightId, String provinceCode, String cityCode, Integer num, BigDecimal weight);

	/**
	 * 获取运费详情
	 * @author LeGreen
	 * @date   2022/5/16
	 * @param  freightId
	 * @return UmsShopFreightTemplate
	 */
	UmsShopFreightTemplate getShopFreightById(Integer freightId);
}