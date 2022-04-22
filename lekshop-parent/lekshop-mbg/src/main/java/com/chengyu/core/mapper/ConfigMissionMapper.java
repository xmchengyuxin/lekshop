package com.chengyu.core.mapper;

import com.chengyu.core.model.ConfigMission;
import com.chengyu.core.model.ConfigMissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfigMissionMapper {
    long countByExample(ConfigMissionExample example);

    int deleteByExample(ConfigMissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConfigMission record);

    int insertSelective(ConfigMission record);

    List<ConfigMission> selectByExample(ConfigMissionExample example);

    ConfigMission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConfigMission record, @Param("example") ConfigMissionExample example);

    int updateByExample(@Param("record") ConfigMission record, @Param("example") ConfigMissionExample example);

    int updateByPrimaryKeySelective(ConfigMission record);

    int updateByPrimaryKey(ConfigMission record);
}