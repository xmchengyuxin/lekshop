package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsPhonebookMessage;
import com.chengyu.core.model.UmsPhonebookMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsPhonebookMessageMapper {
    long countByExample(UmsPhonebookMessageExample example);

    int deleteByExample(UmsPhonebookMessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsPhonebookMessage record);

    int insertSelective(UmsPhonebookMessage record);

    List<UmsPhonebookMessage> selectByExampleWithBLOBs(UmsPhonebookMessageExample example);

    List<UmsPhonebookMessage> selectByExample(UmsPhonebookMessageExample example);

    UmsPhonebookMessage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsPhonebookMessage record, @Param("example") UmsPhonebookMessageExample example);

    int updateByExampleWithBLOBs(@Param("record") UmsPhonebookMessage record, @Param("example") UmsPhonebookMessageExample example);

    int updateByExample(@Param("record") UmsPhonebookMessage record, @Param("example") UmsPhonebookMessageExample example);

    int updateByPrimaryKeySelective(UmsPhonebookMessage record);

    int updateByPrimaryKeyWithBLOBs(UmsPhonebookMessage record);

    int updateByPrimaryKey(UmsPhonebookMessage record);
}