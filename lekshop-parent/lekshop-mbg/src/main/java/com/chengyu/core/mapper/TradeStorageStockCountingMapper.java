package com.chengyu.core.mapper;

import com.chengyu.core.model.TradeStorageStockCounting;
import com.chengyu.core.model.TradeStorageStockCountingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TradeStorageStockCountingMapper {
    long countByExample(TradeStorageStockCountingExample example);

    int deleteByExample(TradeStorageStockCountingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TradeStorageStockCounting record);

    int insertSelective(TradeStorageStockCounting record);

    List<TradeStorageStockCounting> selectByExample(TradeStorageStockCountingExample example);

    TradeStorageStockCounting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TradeStorageStockCounting record, @Param("example") TradeStorageStockCountingExample example);

    int updateByExample(@Param("record") TradeStorageStockCounting record, @Param("example") TradeStorageStockCountingExample example);

    int updateByPrimaryKeySelective(TradeStorageStockCounting record);

    int updateByPrimaryKey(TradeStorageStockCounting record);
}