package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberMissionLog;
import com.chengyu.core.model.UmsMemberMissionLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberMissionLogMapper {
    long countByExample(UmsMemberMissionLogExample example);

    int deleteByExample(UmsMemberMissionLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberMissionLog record);

    int insertSelective(UmsMemberMissionLog record);

    List<UmsMemberMissionLog> selectByExample(UmsMemberMissionLogExample example);

    UmsMemberMissionLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsMemberMissionLog record, @Param("example") UmsMemberMissionLogExample example);

    int updateByExample(@Param("record") UmsMemberMissionLog record, @Param("example") UmsMemberMissionLogExample example);

    int updateByPrimaryKeySelective(UmsMemberMissionLog record);

    int updateByPrimaryKey(UmsMemberMissionLog record);
}