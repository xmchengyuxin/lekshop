package com.chengyu.core.mapper;

import com.chengyu.core.model.OmsOrderMerge;
import com.chengyu.core.model.OmsOrderMergeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsOrderMergeMapper {
    long countByExample(OmsOrderMergeExample example);

    int deleteByExample(OmsOrderMergeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OmsOrderMerge record);

    int insertSelective(OmsOrderMerge record);

    List<OmsOrderMerge> selectByExample(OmsOrderMergeExample example);

    OmsOrderMerge selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OmsOrderMerge record, @Param("example") OmsOrderMergeExample example);

    int updateByExample(@Param("record") OmsOrderMerge record, @Param("example") OmsOrderMergeExample example);

    int updateByPrimaryKeySelective(OmsOrderMerge record);

    int updateByPrimaryKey(OmsOrderMerge record);
}