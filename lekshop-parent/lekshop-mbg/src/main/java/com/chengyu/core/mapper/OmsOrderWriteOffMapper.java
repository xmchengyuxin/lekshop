package com.chengyu.core.mapper;

import com.chengyu.core.model.OmsOrderWriteOff;
import com.chengyu.core.model.OmsOrderWriteOffExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsOrderWriteOffMapper {
    long countByExample(OmsOrderWriteOffExample example);

    int deleteByExample(OmsOrderWriteOffExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OmsOrderWriteOff record);

    int insertSelective(OmsOrderWriteOff record);

    List<OmsOrderWriteOff> selectByExample(OmsOrderWriteOffExample example);

    OmsOrderWriteOff selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OmsOrderWriteOff record, @Param("example") OmsOrderWriteOffExample example);

    int updateByExample(@Param("record") OmsOrderWriteOff record, @Param("example") OmsOrderWriteOffExample example);

    int updateByPrimaryKeySelective(OmsOrderWriteOff record);

    int updateByPrimaryKey(OmsOrderWriteOff record);
}