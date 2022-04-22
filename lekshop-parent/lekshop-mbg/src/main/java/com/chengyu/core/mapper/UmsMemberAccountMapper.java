package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberAccount;
import com.chengyu.core.model.UmsMemberAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberAccountMapper {
    long countByExample(UmsMemberAccountExample example);

    int deleteByExample(UmsMemberAccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsMemberAccount record);

    int insertSelective(UmsMemberAccount record);

    List<UmsMemberAccount> selectByExample(UmsMemberAccountExample example);

    UmsMemberAccount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsMemberAccount record, @Param("example") UmsMemberAccountExample example);

    int updateByExample(@Param("record") UmsMemberAccount record, @Param("example") UmsMemberAccountExample example);

    int updateByPrimaryKeySelective(UmsMemberAccount record);

    int updateByPrimaryKey(UmsMemberAccount record);
}