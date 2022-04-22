package com.chengyu.core.mapper;

import com.chengyu.core.model.OmsOrderPriceLog;
import com.chengyu.core.model.OmsOrderPriceLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsOrderPriceLogMapper {
    long countByExample(OmsOrderPriceLogExample example);

    int deleteByExample(OmsOrderPriceLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OmsOrderPriceLog record);

    int insertSelective(OmsOrderPriceLog record);

    List<OmsOrderPriceLog> selectByExample(OmsOrderPriceLogExample example);

    OmsOrderPriceLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OmsOrderPriceLog record, @Param("example") OmsOrderPriceLogExample example);

    int updateByExample(@Param("record") OmsOrderPriceLog record, @Param("example") OmsOrderPriceLogExample example);

    int updateByPrimaryKeySelective(OmsOrderPriceLog record);

    int updateByPrimaryKey(OmsOrderPriceLog record);
}