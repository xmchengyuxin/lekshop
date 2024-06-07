package com.chengyu.core.mapper;

import com.chengyu.core.model.TradeStorageStockEditProcess;
import com.chengyu.core.model.TradeStorageStockEditProcessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TradeStorageStockEditProcessMapper {
    long countByExample(TradeStorageStockEditProcessExample example);

    int deleteByExample(TradeStorageStockEditProcessExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TradeStorageStockEditProcess record);

    int insertSelective(TradeStorageStockEditProcess record);

    List<TradeStorageStockEditProcess> selectByExample(TradeStorageStockEditProcessExample example);

    TradeStorageStockEditProcess selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TradeStorageStockEditProcess record, @Param("example") TradeStorageStockEditProcessExample example);

    int updateByExample(@Param("record") TradeStorageStockEditProcess record, @Param("example") TradeStorageStockEditProcessExample example);

    int updateByPrimaryKeySelective(TradeStorageStockEditProcess record);

    int updateByPrimaryKey(TradeStorageStockEditProcess record);
}