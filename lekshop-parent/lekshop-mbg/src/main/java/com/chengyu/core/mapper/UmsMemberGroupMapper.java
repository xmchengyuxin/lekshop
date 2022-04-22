package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberGroup;
import com.chengyu.core.model.UmsMemberGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberGroupMapper {
    long countByExample(UmsMemberGroupExample example);

    int deleteByExample(UmsMemberGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsMemberGroup record);

    int insertSelective(UmsMemberGroup record);

    List<UmsMemberGroup> selectByExample(UmsMemberGroupExample example);

    UmsMemberGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsMemberGroup record, @Param("example") UmsMemberGroupExample example);

    int updateByExample(@Param("record") UmsMemberGroup record, @Param("example") UmsMemberGroupExample example);

    int updateByPrimaryKeySelective(UmsMemberGroup record);

    int updateByPrimaryKey(UmsMemberGroup record);
}