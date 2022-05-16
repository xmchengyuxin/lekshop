package com.chengyu.core.service.shop;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsShopService;

import java.util.List;

/**
 * @title  店铺服务
 * @author LeGreen
 * @date   2022/4/23
 */
public interface ShopServiceService {

	/**
	 * 添加店铺服务
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  service
	 */
	void addShopService(UmsShopService service) ;

	/**
	 * 更新店铺服务
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  service
	 */
	void updateShopService(UmsShopService service) ;

	/**
	 * 删除店铺服务
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 */
	void deleteShopServiceById(Integer id) ;

	/**
	 * 根据ID获取店铺服务
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 * @return com.chengyu.core.model.UmsShopService
	 */
	UmsShopService getShopServiceById(Integer id) ;

	/**
	 * 店铺服务列表
	 * @author LeGreen
	 * @date   2021/1/6
	 * @return java.util.List<com.chengyu.core.model.UmsShopService>
	 */
	List<UmsShopService> getAllShopServices();

	/**
	 * 店铺服务分页
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  page
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.UmsShopService>
	 * @throws ServiceException 业务异常
	 */
	List<UmsShopService> getShopServiceList(Integer status, Integer page, Integer pageSize);

	/**
	 * 获取服务名称集合
	 * @author LeGreen
	 * @date   2022/5/16
	 * @param  serviceIds
	 * @return String
	 */
    String getShopServiceName(List<Integer> serviceIds);
}