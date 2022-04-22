package com.chengyu.core.mapper;

import com.chengyu.core.model.RepPlatformMonth;
import com.chengyu.core.model.RepPlatformMonthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepPlatformMonthMapper {
    long countByExample(RepPlatformMonthExample example);

    int deleteByExample(RepPlatformMonthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RepPlatformMonth record);

    int insertSelective(RepPlatformMonth record);

    List<RepPlatformMonth> selectByExample(RepPlatformMonthExample example);

    RepPlatformMonth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RepPlatformMonth record, @Param("example") RepPlatformMonthExample example);

    int updateByExample(@Param("record") RepPlatformMonth record, @Param("example") RepPlatformMonthExample example);

    int updateByPrimaryKeySelective(RepPlatformMonth record);

    int updateByPrimaryKey(RepPlatformMonth record);
}