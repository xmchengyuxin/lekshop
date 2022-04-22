package com.chengyu.core.mapper;

import com.chengyu.core.model.ConfigMissionDetail;
import com.chengyu.core.model.ConfigMissionDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfigMissionDetailMapper {
    long countByExample(ConfigMissionDetailExample example);

    int deleteByExample(ConfigMissionDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConfigMissionDetail record);

    int insertSelective(ConfigMissionDetail record);

    List<ConfigMissionDetail> selectByExample(ConfigMissionDetailExample example);

    ConfigMissionDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConfigMissionDetail record, @Param("example") ConfigMissionDetailExample example);

    int updateByExample(@Param("record") ConfigMissionDetail record, @Param("example") ConfigMissionDetailExample example);

    int updateByPrimaryKeySelective(ConfigMissionDetail record);

    int updateByPrimaryKey(ConfigMissionDetail record);
}