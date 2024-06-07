package com.chengyu.core.mapper;

import com.chengyu.core.model.TradeStorageStockOrder;
import com.chengyu.core.model.TradeStorageStockOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TradeStorageStockOrderMapper {
    long countByExample(TradeStorageStockOrderExample example);

    int deleteByExample(TradeStorageStockOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TradeStorageStockOrder record);

    int insertSelective(TradeStorageStockOrder record);

    List<TradeStorageStockOrder> selectByExample(TradeStorageStockOrderExample example);

    TradeStorageStockOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TradeStorageStockOrder record, @Param("example") TradeStorageStockOrderExample example);

    int updateByExample(@Param("record") TradeStorageStockOrder record, @Param("example") TradeStorageStockOrderExample example);

    int updateByPrimaryKeySelective(TradeStorageStockOrder record);

    int updateByPrimaryKey(TradeStorageStockOrder record);
}