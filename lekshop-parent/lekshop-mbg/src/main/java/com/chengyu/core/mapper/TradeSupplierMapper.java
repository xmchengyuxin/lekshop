package com.chengyu.core.mapper;

import com.chengyu.core.model.TradeSupplier;
import com.chengyu.core.model.TradeSupplierExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TradeSupplierMapper {
    long countByExample(TradeSupplierExample example);

    int deleteByExample(TradeSupplierExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TradeSupplier record);

    int insertSelective(TradeSupplier record);

    List<TradeSupplier> selectByExample(TradeSupplierExample example);

    TradeSupplier selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TradeSupplier record, @Param("example") TradeSupplierExample example);

    int updateByExample(@Param("record") TradeSupplier record, @Param("example") TradeSupplierExample example);

    int updateByPrimaryKeySelective(TradeSupplier record);

    int updateByPrimaryKey(TradeSupplier record);
}