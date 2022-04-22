package com.chengyu.core.mapper;

import com.chengyu.core.model.ConfigGzh;
import com.chengyu.core.model.ConfigGzhExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfigGzhMapper {
    long countByExample(ConfigGzhExample example);

    int deleteByExample(ConfigGzhExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConfigGzh record);

    int insertSelective(ConfigGzh record);

    List<ConfigGzh> selectByExampleWithBLOBs(ConfigGzhExample example);

    List<ConfigGzh> selectByExample(ConfigGzhExample example);

    ConfigGzh selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConfigGzh record, @Param("example") ConfigGzhExample example);

    int updateByExampleWithBLOBs(@Param("record") ConfigGzh record, @Param("example") ConfigGzhExample example);

    int updateByExample(@Param("record") ConfigGzh record, @Param("example") ConfigGzhExample example);

    int updateByPrimaryKeySelective(ConfigGzh record);

    int updateByPrimaryKeyWithBLOBs(ConfigGzh record);

    int updateByPrimaryKey(ConfigGzh record);
}