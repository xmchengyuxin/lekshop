package com.chengyu.core.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @title  商家后台首页统计
 * @author LeGreen
 * @date   2022/5/18
 */
public interface CustomShopIndexMapper {

	Map<String, Object> countGoods(@Param("shopId") Integer shopId);

	Map<String,Object> countOrder(@Param("shopId") Integer shopId, @Param("beginDate")Date beginDate, @Param("endDate")Date endDate);

	List<Map<String, Object>> countOrderNumByLastTenDays(@Param("shopId") Integer shopId);

	Map<String,Object> countWaitDealOrderNum(@Param("shopId") Integer shopId);

	Map<String,Object> countCollectShopNum(@Param("shopId") Integer shopId);

	Map<String,Object> countCollectGoodsNum(@Param("shopId") Integer shopId);

	Map<String,Object> countCarNum(@Param("shopId") Integer shopId);

	List<Map<String, Object>> getGoodsSellRankList(@Param("shopId") Integer shopId, @Param("beginDate")Date beginDate, @Param("endDate")Date endDate);
}