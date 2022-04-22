package com.chengyu.core.mapper;

import com.chengyu.core.model.OmsOrderFreight;
import com.chengyu.core.model.OmsOrderFreightExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsOrderFreightMapper {
    long countByExample(OmsOrderFreightExample example);

    int deleteByExample(OmsOrderFreightExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OmsOrderFreight record);

    int insertSelective(OmsOrderFreight record);

    List<OmsOrderFreight> selectByExample(OmsOrderFreightExample example);

    OmsOrderFreight selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OmsOrderFreight record, @Param("example") OmsOrderFreightExample example);

    int updateByExample(@Param("record") OmsOrderFreight record, @Param("example") OmsOrderFreightExample example);

    int updateByPrimaryKeySelective(OmsOrderFreight record);

    int updateByPrimaryKey(OmsOrderFreight record);
}