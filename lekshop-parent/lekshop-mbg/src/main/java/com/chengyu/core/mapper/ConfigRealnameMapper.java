package com.chengyu.core.mapper;

import com.chengyu.core.model.ConfigRealname;
import com.chengyu.core.model.ConfigRealnameExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfigRealnameMapper {
    long countByExample(ConfigRealnameExample example);

    int deleteByExample(ConfigRealnameExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConfigRealname record);

    int insertSelective(ConfigRealname record);

    List<ConfigRealname> selectByExample(ConfigRealnameExample example);

    ConfigRealname selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConfigRealname record, @Param("example") ConfigRealnameExample example);

    int updateByExample(@Param("record") ConfigRealname record, @Param("example") ConfigRealnameExample example);

    int updateByPrimaryKeySelective(ConfigRealname record);

    int updateByPrimaryKey(ConfigRealname record);
}