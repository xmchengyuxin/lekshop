package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberNews;
import com.chengyu.core.model.UmsMemberNewsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberNewsMapper {
    long countByExample(UmsMemberNewsExample example);

    int deleteByExample(UmsMemberNewsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsMemberNews record);

    int insertSelective(UmsMemberNews record);

    List<UmsMemberNews> selectByExampleWithBLOBs(UmsMemberNewsExample example);

    List<UmsMemberNews> selectByExample(UmsMemberNewsExample example);

    UmsMemberNews selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsMemberNews record, @Param("example") UmsMemberNewsExample example);

    int updateByExampleWithBLOBs(@Param("record") UmsMemberNews record, @Param("example") UmsMemberNewsExample example);

    int updateByExample(@Param("record") UmsMemberNews record, @Param("example") UmsMemberNewsExample example);

    int updateByPrimaryKeySelective(UmsMemberNews record);

    int updateByPrimaryKeyWithBLOBs(UmsMemberNews record);

    int updateByPrimaryKey(UmsMemberNews record);
}