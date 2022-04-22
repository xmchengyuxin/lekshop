package com.chengyu.core.mapper;

import com.chengyu.core.model.QrtzPausedTriggerGrps;
import com.chengyu.core.model.QrtzPausedTriggerGrpsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QrtzPausedTriggerGrpsMapper {
    long countByExample(QrtzPausedTriggerGrpsExample example);

    int deleteByExample(QrtzPausedTriggerGrpsExample example);

    int deleteByPrimaryKey(@Param("schedName") String schedName, @Param("triggerGroup") String triggerGroup);

    int insert(QrtzPausedTriggerGrps record);

    int insertSelective(QrtzPausedTriggerGrps record);

    List<QrtzPausedTriggerGrps> selectByExample(QrtzPausedTriggerGrpsExample example);

    int updateByExampleSelective(@Param("record") QrtzPausedTriggerGrps record, @Param("example") QrtzPausedTriggerGrpsExample example);

    int updateByExample(@Param("record") QrtzPausedTriggerGrps record, @Param("example") QrtzPausedTriggerGrpsExample example);
}