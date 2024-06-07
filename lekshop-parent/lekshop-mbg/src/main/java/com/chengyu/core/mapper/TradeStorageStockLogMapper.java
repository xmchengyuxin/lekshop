package com.chengyu.core.mapper;

import com.chengyu.core.model.TradeStorageStockLog;
import com.chengyu.core.model.TradeStorageStockLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TradeStorageStockLogMapper {
    long countByExample(TradeStorageStockLogExample example);

    int deleteByExample(TradeStorageStockLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TradeStorageStockLog record);

    int insertSelective(TradeStorageStockLog record);

    List<TradeStorageStockLog> selectByExample(TradeStorageStockLogExample example);

    TradeStorageStockLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TradeStorageStockLog record, @Param("example") TradeStorageStockLogExample example);

    int updateByExample(@Param("record") TradeStorageStockLog record, @Param("example") TradeStorageStockLogExample example);

    int updateByPrimaryKeySelective(TradeStorageStockLog record);

    int updateByPrimaryKey(TradeStorageStockLog record);
}