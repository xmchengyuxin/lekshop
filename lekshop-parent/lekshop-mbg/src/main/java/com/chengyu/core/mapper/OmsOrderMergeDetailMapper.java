package com.chengyu.core.mapper;

import com.chengyu.core.model.OmsOrderMergeDetail;
import com.chengyu.core.model.OmsOrderMergeDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsOrderMergeDetailMapper {
    long countByExample(OmsOrderMergeDetailExample example);

    int deleteByExample(OmsOrderMergeDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OmsOrderMergeDetail record);

    int insertSelective(OmsOrderMergeDetail record);

    List<OmsOrderMergeDetail> selectByExample(OmsOrderMergeDetailExample example);

    OmsOrderMergeDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OmsOrderMergeDetail record, @Param("example") OmsOrderMergeDetailExample example);

    int updateByExample(@Param("record") OmsOrderMergeDetail record, @Param("example") OmsOrderMergeDetailExample example);

    int updateByPrimaryKeySelective(OmsOrderMergeDetail record);

    int updateByPrimaryKey(OmsOrderMergeDetail record);
}