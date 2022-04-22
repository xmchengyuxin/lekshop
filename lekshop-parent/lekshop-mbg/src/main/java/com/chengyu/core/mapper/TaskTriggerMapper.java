package com.chengyu.core.mapper;

import com.chengyu.core.model.TaskTrigger;
import com.chengyu.core.model.TaskTriggerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskTriggerMapper {
    long countByExample(TaskTriggerExample example);

    int deleteByExample(TaskTriggerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaskTrigger record);

    int insertSelective(TaskTrigger record);

    List<TaskTrigger> selectByExample(TaskTriggerExample example);

    TaskTrigger selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaskTrigger record, @Param("example") TaskTriggerExample example);

    int updateByExample(@Param("record") TaskTrigger record, @Param("example") TaskTriggerExample example);

    int updateByPrimaryKeySelective(TaskTrigger record);

    int updateByPrimaryKey(TaskTrigger record);
}