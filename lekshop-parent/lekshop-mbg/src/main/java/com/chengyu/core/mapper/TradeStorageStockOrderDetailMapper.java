package com.chengyu.core.mapper;

import com.chengyu.core.model.TradeStorageStockOrderDetail;
import com.chengyu.core.model.TradeStorageStockOrderDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TradeStorageStockOrderDetailMapper {
    long countByExample(TradeStorageStockOrderDetailExample example);

    int deleteByExample(TradeStorageStockOrderDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TradeStorageStockOrderDetail record);

    int insertSelective(TradeStorageStockOrderDetail record);

    List<TradeStorageStockOrderDetail> selectByExample(TradeStorageStockOrderDetailExample example);

    TradeStorageStockOrderDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TradeStorageStockOrderDetail record, @Param("example") TradeStorageStockOrderDetailExample example);

    int updateByExample(@Param("record") TradeStorageStockOrderDetail record, @Param("example") TradeStorageStockOrderDetailExample example);

    int updateByPrimaryKeySelective(TradeStorageStockOrderDetail record);

    int updateByPrimaryKey(TradeStorageStockOrderDetail record);
}