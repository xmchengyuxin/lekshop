package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberImeid;
import com.chengyu.core.model.UmsMemberImeidExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberImeidMapper {
    long countByExample(UmsMemberImeidExample example);

    int deleteByExample(UmsMemberImeidExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsMemberImeid record);

    int insertSelective(UmsMemberImeid record);

    List<UmsMemberImeid> selectByExample(UmsMemberImeidExample example);

    UmsMemberImeid selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsMemberImeid record, @Param("example") UmsMemberImeidExample example);

    int updateByExample(@Param("record") UmsMemberImeid record, @Param("example") UmsMemberImeidExample example);

    int updateByPrimaryKeySelective(UmsMemberImeid record);

    int updateByPrimaryKey(UmsMemberImeid record);
}