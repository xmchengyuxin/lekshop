package com.chengyu.core.mapper;

import com.chengyu.core.model.TradeStorageStockCountingDetail;
import com.chengyu.core.model.TradeStorageStockCountingDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TradeStorageStockCountingDetailMapper {
    long countByExample(TradeStorageStockCountingDetailExample example);

    int deleteByExample(TradeStorageStockCountingDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TradeStorageStockCountingDetail record);

    int insertSelective(TradeStorageStockCountingDetail record);

    List<TradeStorageStockCountingDetail> selectByExample(TradeStorageStockCountingDetailExample example);

    TradeStorageStockCountingDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TradeStorageStockCountingDetail record, @Param("example") TradeStorageStockCountingDetailExample example);

    int updateByExample(@Param("record") TradeStorageStockCountingDetail record, @Param("example") TradeStorageStockCountingDetailExample example);

    int updateByPrimaryKeySelective(TradeStorageStockCountingDetail record);

    int updateByPrimaryKey(TradeStorageStockCountingDetail record);
}