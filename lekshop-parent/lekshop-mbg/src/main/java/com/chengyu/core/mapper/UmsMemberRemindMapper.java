package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberRemind;
import com.chengyu.core.model.UmsMemberRemindExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberRemindMapper {
    long countByExample(UmsMemberRemindExample example);

    int deleteByExample(UmsMemberRemindExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberRemind record);

    int insertSelective(UmsMemberRemind record);

    List<UmsMemberRemind> selectByExample(UmsMemberRemindExample example);

    UmsMemberRemind selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsMemberRemind record, @Param("example") UmsMemberRemindExample example);

    int updateByExample(@Param("record") UmsMemberRemind record, @Param("example") UmsMemberRemindExample example);

    int updateByPrimaryKeySelective(UmsMemberRemind record);

    int updateByPrimaryKey(UmsMemberRemind record);
}