package com.chengyu.core.mapper;

import com.chengyu.core.model.PmsGoodsCate;
import com.chengyu.core.model.RepConversionDay;
import com.chengyu.core.model.RepPlatformGoodsDay;
import com.chengyu.core.model.RepShopGoodsDay;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface CustomReportLogMapper {
	
	RepConversionDay getRepConversionDay(
			@Param("beginTime") Date beginTime,
			@Param("endTime") Date endTime);
	
	BigDecimal countBussinessAmount(
			@Param("shopId")Integer shopId, 
			@Param("beginTime")Date beginTime, 
			@Param("endTime")Date endTime);
	
	BigDecimal countGoodsRefundAmount(
			@Param("shopId")Integer shopId, 
			@Param("beginTime")Date beginTime, 
			@Param("endTime")Date endTime);
	
	List<PmsGoodsCate> getGoodsCateListByShopId(
			@Param("shopId")Integer shopId, 
			@Param("beginTime")Date beginTime, 
			@Param("endTime")Date endTime);
	
	List<PmsGoodsCate> getGoodsCateListByPlatform(
			@Param("beginTime")Date beginTime,
			@Param("endTime")Date endTime);
	
	List<RepShopGoodsDay> countShopGoodsByCateId(
			@Param("shopId")Integer shopId, 
			@Param("cateId")Integer cateId, 
			@Param("beginTime")Date beginTime, 
			@Param("endTime")Date endTime);
	
	List<RepPlatformGoodsDay> countPlatformGoodsByCateId(
			@Param("cateId")Integer cateId,
			@Param("beginTime")Date beginTime, 
			@Param("endTime")Date endTime);
}