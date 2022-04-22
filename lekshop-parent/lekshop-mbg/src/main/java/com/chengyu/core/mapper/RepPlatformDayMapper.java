package com.chengyu.core.mapper;

import com.chengyu.core.model.RepPlatformDay;
import com.chengyu.core.model.RepPlatformDayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepPlatformDayMapper {
    long countByExample(RepPlatformDayExample example);

    int deleteByExample(RepPlatformDayExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RepPlatformDay record);

    int insertSelective(RepPlatformDay record);

    List<RepPlatformDay> selectByExample(RepPlatformDayExample example);

    RepPlatformDay selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RepPlatformDay record, @Param("example") RepPlatformDayExample example);

    int updateByExample(@Param("record") RepPlatformDay record, @Param("example") RepPlatformDayExample example);

    int updateByPrimaryKeySelective(RepPlatformDay record);

    int updateByPrimaryKey(RepPlatformDay record);
}