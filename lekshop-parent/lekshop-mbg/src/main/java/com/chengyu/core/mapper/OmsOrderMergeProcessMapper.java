package com.chengyu.core.mapper;

import com.chengyu.core.model.OmsOrderMergeProcess;
import com.chengyu.core.model.OmsOrderMergeProcessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsOrderMergeProcessMapper {
    long countByExample(OmsOrderMergeProcessExample example);

    int deleteByExample(OmsOrderMergeProcessExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OmsOrderMergeProcess record);

    int insertSelective(OmsOrderMergeProcess record);

    List<OmsOrderMergeProcess> selectByExample(OmsOrderMergeProcessExample example);

    OmsOrderMergeProcess selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OmsOrderMergeProcess record, @Param("example") OmsOrderMergeProcessExample example);

    int updateByExample(@Param("record") OmsOrderMergeProcess record, @Param("example") OmsOrderMergeProcessExample example);

    int updateByPrimaryKeySelective(OmsOrderMergeProcess record);

    int updateByPrimaryKey(OmsOrderMergeProcess record);
}