package com.chengyu.core.mapper;

import com.chengyu.core.model.ConfigSearch;
import com.chengyu.core.model.ConfigSearchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfigSearchMapper {
    long countByExample(ConfigSearchExample example);

    int deleteByExample(ConfigSearchExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConfigSearch record);

    int insertSelective(ConfigSearch record);

    List<ConfigSearch> selectByExample(ConfigSearchExample example);

    ConfigSearch selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConfigSearch record, @Param("example") ConfigSearchExample example);

    int updateByExample(@Param("record") ConfigSearch record, @Param("example") ConfigSearchExample example);

    int updateByPrimaryKeySelective(ConfigSearch record);

    int updateByPrimaryKey(ConfigSearch record);
}