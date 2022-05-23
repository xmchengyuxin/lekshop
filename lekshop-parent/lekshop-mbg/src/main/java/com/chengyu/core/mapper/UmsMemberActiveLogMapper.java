package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberActiveLog;
import com.chengyu.core.model.UmsMemberActiveLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberActiveLogMapper {
    long countByExample(UmsMemberActiveLogExample example);

    int deleteByExample(UmsMemberActiveLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberActiveLog record);

    int insertSelective(UmsMemberActiveLog record);

    List<UmsMemberActiveLog> selectByExample(UmsMemberActiveLogExample example);

    UmsMemberActiveLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsMemberActiveLog record, @Param("example") UmsMemberActiveLogExample example);

    int updateByExample(@Param("record") UmsMemberActiveLog record, @Param("example") UmsMemberActiveLogExample example);

    int updateByPrimaryKeySelective(UmsMemberActiveLog record);

    int updateByPrimaryKey(UmsMemberActiveLog record);
}