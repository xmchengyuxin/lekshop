package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberRel;
import com.chengyu.core.model.UmsMemberRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberRelMapper {
    long countByExample(UmsMemberRelExample example);

    int deleteByExample(UmsMemberRelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsMemberRel record);

    int insertSelective(UmsMemberRel record);

    List<UmsMemberRel> selectByExample(UmsMemberRelExample example);

    UmsMemberRel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsMemberRel record, @Param("example") UmsMemberRelExample example);

    int updateByExample(@Param("record") UmsMemberRel record, @Param("example") UmsMemberRelExample example);

    int updateByPrimaryKeySelective(UmsMemberRel record);

    int updateByPrimaryKey(UmsMemberRel record);
}