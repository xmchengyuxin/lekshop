package com.chengyu.core.mapper;

import com.chengyu.core.model.TradeStorage;
import com.chengyu.core.model.TradeStorageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TradeStorageMapper {
    long countByExample(TradeStorageExample example);

    int deleteByExample(TradeStorageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TradeStorage record);

    int insertSelective(TradeStorage record);

    List<TradeStorage> selectByExample(TradeStorageExample example);

    TradeStorage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TradeStorage record, @Param("example") TradeStorageExample example);

    int updateByExample(@Param("record") TradeStorage record, @Param("example") TradeStorageExample example);

    int updateByPrimaryKeySelective(TradeStorage record);

    int updateByPrimaryKey(TradeStorage record);
}