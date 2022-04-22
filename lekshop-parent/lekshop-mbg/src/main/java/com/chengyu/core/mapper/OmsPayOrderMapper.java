package com.chengyu.core.mapper;

import com.chengyu.core.model.OmsPayOrder;
import com.chengyu.core.model.OmsPayOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsPayOrderMapper {
    long countByExample(OmsPayOrderExample example);

    int deleteByExample(OmsPayOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OmsPayOrder record);

    int insertSelective(OmsPayOrder record);

    List<OmsPayOrder> selectByExample(OmsPayOrderExample example);

    OmsPayOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OmsPayOrder record, @Param("example") OmsPayOrderExample example);

    int updateByExample(@Param("record") OmsPayOrder record, @Param("example") OmsPayOrderExample example);

    int updateByPrimaryKeySelective(OmsPayOrder record);

    int updateByPrimaryKey(OmsPayOrder record);
}