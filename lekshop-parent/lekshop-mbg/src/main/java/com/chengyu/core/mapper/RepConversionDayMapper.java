package com.chengyu.core.mapper;

import com.chengyu.core.model.RepConversionDay;
import com.chengyu.core.model.RepConversionDayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepConversionDayMapper {
    long countByExample(RepConversionDayExample example);

    int deleteByExample(RepConversionDayExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RepConversionDay record);

    int insertSelective(RepConversionDay record);

    List<RepConversionDay> selectByExample(RepConversionDayExample example);

    RepConversionDay selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RepConversionDay record, @Param("example") RepConversionDayExample example);

    int updateByExample(@Param("record") RepConversionDay record, @Param("example") RepConversionDayExample example);

    int updateByPrimaryKeySelective(RepConversionDay record);

    int updateByPrimaryKey(RepConversionDay record);
}