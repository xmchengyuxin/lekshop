package com.chengyu.core.mapper;

import com.chengyu.core.model.TradeStorageStock;
import com.chengyu.core.model.TradeStorageStockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TradeStorageStockMapper {
    long countByExample(TradeStorageStockExample example);

    int deleteByExample(TradeStorageStockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TradeStorageStock record);

    int insertSelective(TradeStorageStock record);

    List<TradeStorageStock> selectByExample(TradeStorageStockExample example);

    TradeStorageStock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TradeStorageStock record, @Param("example") TradeStorageStockExample example);

    int updateByExample(@Param("record") TradeStorageStock record, @Param("example") TradeStorageStockExample example);

    int updateByPrimaryKeySelective(TradeStorageStock record);

    int updateByPrimaryKey(TradeStorageStock record);
}