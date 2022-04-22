package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberSign;
import com.chengyu.core.model.UmsMemberSignExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberSignMapper {
    long countByExample(UmsMemberSignExample example);

    int deleteByExample(UmsMemberSignExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsMemberSign record);

    int insertSelective(UmsMemberSign record);

    List<UmsMemberSign> selectByExample(UmsMemberSignExample example);

    UmsMemberSign selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsMemberSign record, @Param("example") UmsMemberSignExample example);

    int updateByExample(@Param("record") UmsMemberSign record, @Param("example") UmsMemberSignExample example);

    int updateByPrimaryKeySelective(UmsMemberSign record);

    int updateByPrimaryKey(UmsMemberSign record);
}