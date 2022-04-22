package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsPhonebookLog;
import com.chengyu.core.model.UmsPhonebookLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsPhonebookLogMapper {
    long countByExample(UmsPhonebookLogExample example);

    int deleteByExample(UmsPhonebookLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsPhonebookLog record);

    int insertSelective(UmsPhonebookLog record);

    List<UmsPhonebookLog> selectByExample(UmsPhonebookLogExample example);

    UmsPhonebookLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsPhonebookLog record, @Param("example") UmsPhonebookLogExample example);

    int updateByExample(@Param("record") UmsPhonebookLog record, @Param("example") UmsPhonebookLogExample example);

    int updateByPrimaryKeySelective(UmsPhonebookLog record);

    int updateByPrimaryKey(UmsPhonebookLog record);
}