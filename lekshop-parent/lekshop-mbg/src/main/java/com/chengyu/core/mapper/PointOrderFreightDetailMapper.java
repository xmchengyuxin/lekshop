package com.chengyu.core.mapper;

import com.chengyu.core.model.PointOrderFreightDetail;
import com.chengyu.core.model.PointOrderFreightDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PointOrderFreightDetailMapper {
    long countByExample(PointOrderFreightDetailExample example);

    int deleteByExample(PointOrderFreightDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PointOrderFreightDetail record);

    int insertSelective(PointOrderFreightDetail record);

    List<PointOrderFreightDetail> selectByExample(PointOrderFreightDetailExample example);

    PointOrderFreightDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PointOrderFreightDetail record, @Param("example") PointOrderFreightDetailExample example);

    int updateByExample(@Param("record") PointOrderFreightDetail record, @Param("example") PointOrderFreightDetailExample example);

    int updateByPrimaryKeySelective(PointOrderFreightDetail record);

    int updateByPrimaryKey(PointOrderFreightDetail record);
}