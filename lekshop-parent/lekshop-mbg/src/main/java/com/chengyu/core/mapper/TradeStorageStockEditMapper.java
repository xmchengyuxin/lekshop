package com.chengyu.core.mapper;

import com.chengyu.core.model.TradeStorageStockEdit;
import com.chengyu.core.model.TradeStorageStockEditExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TradeStorageStockEditMapper {
    long countByExample(TradeStorageStockEditExample example);

    int deleteByExample(TradeStorageStockEditExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TradeStorageStockEdit record);

    int insertSelective(TradeStorageStockEdit record);

    List<TradeStorageStockEdit> selectByExample(TradeStorageStockEditExample example);

    TradeStorageStockEdit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TradeStorageStockEdit record, @Param("example") TradeStorageStockEditExample example);

    int updateByExample(@Param("record") TradeStorageStockEdit record, @Param("example") TradeStorageStockEditExample example);

    int updateByPrimaryKeySelective(TradeStorageStockEdit record);

    int updateByPrimaryKey(TradeStorageStockEdit record);
}