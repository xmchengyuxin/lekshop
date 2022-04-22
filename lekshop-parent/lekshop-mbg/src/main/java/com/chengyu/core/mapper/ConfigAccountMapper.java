package com.chengyu.core.mapper;

import com.chengyu.core.model.ConfigAccount;
import com.chengyu.core.model.ConfigAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfigAccountMapper {
    long countByExample(ConfigAccountExample example);

    int deleteByExample(ConfigAccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConfigAccount record);

    int insertSelective(ConfigAccount record);

    List<ConfigAccount> selectByExample(ConfigAccountExample example);

    ConfigAccount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConfigAccount record, @Param("example") ConfigAccountExample example);

    int updateByExample(@Param("record") ConfigAccount record, @Param("example") ConfigAccountExample example);

    int updateByPrimaryKeySelective(ConfigAccount record);

    int updateByPrimaryKey(ConfigAccount record);
}