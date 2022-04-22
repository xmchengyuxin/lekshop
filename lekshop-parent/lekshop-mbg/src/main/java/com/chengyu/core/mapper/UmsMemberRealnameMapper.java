package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberRealname;
import com.chengyu.core.model.UmsMemberRealnameExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberRealnameMapper {
    long countByExample(UmsMemberRealnameExample example);

    int deleteByExample(UmsMemberRealnameExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsMemberRealname record);

    int insertSelective(UmsMemberRealname record);

    List<UmsMemberRealname> selectByExample(UmsMemberRealnameExample example);

    UmsMemberRealname selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsMemberRealname record, @Param("example") UmsMemberRealnameExample example);

    int updateByExample(@Param("record") UmsMemberRealname record, @Param("example") UmsMemberRealnameExample example);

    int updateByPrimaryKeySelective(UmsMemberRealname record);

    int updateByPrimaryKey(UmsMemberRealname record);
}