package com.chengyu.core.mapper;

import com.chengyu.core.model.TradeStorageStockCountingDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomStockCountingMapper {


	/**
	 * 添加盲盘详情
	 * @author LeGreen
	 * @date   2022/8/30
	 * @param  countingId 盲盘ID
	 * @param  countingDetailList 盲盘明细
	 */
	void addCountingDetail(@Param("countingId")Integer countingId, @Param("countingDetailList")List<TradeStorageStockCountingDetail> countingDetailList);
}