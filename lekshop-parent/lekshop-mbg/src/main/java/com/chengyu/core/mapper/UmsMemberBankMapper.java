package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberBank;
import com.chengyu.core.model.UmsMemberBankExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberBankMapper {
    long countByExample(UmsMemberBankExample example);

    int deleteByExample(UmsMemberBankExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsMemberBank record);

    int insertSelective(UmsMemberBank record);

    List<UmsMemberBank> selectByExample(UmsMemberBankExample example);

    UmsMemberBank selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsMemberBank record, @Param("example") UmsMemberBankExample example);

    int updateByExample(@Param("record") UmsMemberBank record, @Param("example") UmsMemberBankExample example);

    int updateByPrimaryKeySelective(UmsMemberBank record);

    int updateByPrimaryKey(UmsMemberBank record);
}