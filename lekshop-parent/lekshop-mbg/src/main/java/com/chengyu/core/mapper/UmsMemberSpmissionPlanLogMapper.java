package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberSpmissionPlanLog;
import com.chengyu.core.model.UmsMemberSpmissionPlanLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberSpmissionPlanLogMapper {
    long countByExample(UmsMemberSpmissionPlanLogExample example);

    int deleteByExample(UmsMemberSpmissionPlanLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberSpmissionPlanLog record);

    int insertSelective(UmsMemberSpmissionPlanLog record);

    List<UmsMemberSpmissionPlanLog> selectByExample(UmsMemberSpmissionPlanLogExample example);

    UmsMemberSpmissionPlanLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsMemberSpmissionPlanLog record, @Param("example") UmsMemberSpmissionPlanLogExample example);

    int updateByExample(@Param("record") UmsMemberSpmissionPlanLog record, @Param("example") UmsMemberSpmissionPlanLogExample example);

    int updateByPrimaryKeySelective(UmsMemberSpmissionPlanLog record);

    int updateByPrimaryKey(UmsMemberSpmissionPlanLog record);
}