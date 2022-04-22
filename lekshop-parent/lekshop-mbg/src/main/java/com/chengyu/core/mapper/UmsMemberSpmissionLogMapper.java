package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberSpmissionLog;
import com.chengyu.core.model.UmsMemberSpmissionLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberSpmissionLogMapper {
    long countByExample(UmsMemberSpmissionLogExample example);

    int deleteByExample(UmsMemberSpmissionLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberSpmissionLog record);

    int insertSelective(UmsMemberSpmissionLog record);

    List<UmsMemberSpmissionLog> selectByExample(UmsMemberSpmissionLogExample example);

    UmsMemberSpmissionLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsMemberSpmissionLog record, @Param("example") UmsMemberSpmissionLogExample example);

    int updateByExample(@Param("record") UmsMemberSpmissionLog record, @Param("example") UmsMemberSpmissionLogExample example);

    int updateByPrimaryKeySelective(UmsMemberSpmissionLog record);

    int updateByPrimaryKey(UmsMemberSpmissionLog record);
}