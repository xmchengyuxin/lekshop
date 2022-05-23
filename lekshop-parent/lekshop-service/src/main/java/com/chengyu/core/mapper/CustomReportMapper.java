package com.chengyu.core.mapper;

import com.chengyu.core.domain.result.RebuyCountResult;
import com.chengyu.core.domain.result.RepGoodsCountResult;
import com.chengyu.core.model.RepPlatformDay;
import com.chengyu.core.model.RepShopDay;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CustomReportMapper {
	
	RepShopDay getReportShopDayData(
			@Param("shopId") Integer shopId,
			@Param("beginTime") Date beginTime,
			@Param("endTime") Date endTime
			);
	
	RepPlatformDay getReportPlatformDayData(
			@Param("beginTime") Date beginTime,
			@Param("endTime") Date endTime
			);
	
	void genMemberDayReport(
			@Param("beginTime") Date beginTime,
			@Param("endTime") Date endTime);

	void updateConversion(@Param("type") String type);
	
	void genShopMonthReport(
			@Param("beginTime") Date beginTime,
			@Param("endTime") Date endTime,
			@Param("month") String month);
	
	void genShopGoodsMonthReport(
			@Param("beginTime") Date beginTime,
			@Param("endTime") Date endTime,
			@Param("month") String month);
	
	void genPlatformMonthReport(
			@Param("beginTime") Date beginTime,
			@Param("endTime") Date endTime,
			@Param("month") String month);
	
	void genPlatformGoodsMonthReport(
			@Param("beginTime") Date beginTime,
			@Param("endTime") Date endTime,
			@Param("month") String month);
	
	void genMemberMonthReport(
			@Param("beginTime") Date beginTime,
			@Param("endTime") Date endTime,
			@Param("month") String month);

	List<RepGoodsCountResult> getRepGoodsCountResult(@Param("shopId") Integer shopId,
													 @Param("beginTime") Date beginTime,
													 @Param("endTime") Date endTime);

	RebuyCountResult getRebuyCount(@Param("shopId") Integer shopId,
								   @Param("goodsId") Integer goodsId,
								   @Param("beginTime") Date beginTime,
								   @Param("endTime") Date endTime);
}