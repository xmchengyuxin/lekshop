package com.chengyu.core.mapper;

import com.chengyu.core.model.PointOrderFreight;
import com.chengyu.core.model.PointOrderFreightExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PointOrderFreightMapper {
    long countByExample(PointOrderFreightExample example);

    int deleteByExample(PointOrderFreightExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PointOrderFreight record);

    int insertSelective(PointOrderFreight record);

    List<PointOrderFreight> selectByExample(PointOrderFreightExample example);

    PointOrderFreight selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PointOrderFreight record, @Param("example") PointOrderFreightExample example);

    int updateByExample(@Param("record") PointOrderFreight record, @Param("example") PointOrderFreightExample example);

    int updateByPrimaryKeySelective(PointOrderFreight record);

    int updateByPrimaryKey(PointOrderFreight record);
}