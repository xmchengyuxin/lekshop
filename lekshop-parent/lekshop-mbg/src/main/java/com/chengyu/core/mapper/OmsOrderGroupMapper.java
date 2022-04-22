package com.chengyu.core.mapper;

import com.chengyu.core.model.OmsOrderGroup;
import com.chengyu.core.model.OmsOrderGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsOrderGroupMapper {
    long countByExample(OmsOrderGroupExample example);

    int deleteByExample(OmsOrderGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OmsOrderGroup record);

    int insertSelective(OmsOrderGroup record);

    List<OmsOrderGroup> selectByExample(OmsOrderGroupExample example);

    OmsOrderGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OmsOrderGroup record, @Param("example") OmsOrderGroupExample example);

    int updateByExample(@Param("record") OmsOrderGroup record, @Param("example") OmsOrderGroupExample example);

    int updateByPrimaryKeySelective(OmsOrderGroup record);

    int updateByPrimaryKey(OmsOrderGroup record);
}