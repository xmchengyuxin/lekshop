package com.chengyu.core.mapper;

import com.chengyu.core.model.OmsOrderGroupMember;
import com.chengyu.core.model.OmsOrderGroupMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsOrderGroupMemberMapper {
    long countByExample(OmsOrderGroupMemberExample example);

    int deleteByExample(OmsOrderGroupMemberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OmsOrderGroupMember record);

    int insertSelective(OmsOrderGroupMember record);

    List<OmsOrderGroupMember> selectByExample(OmsOrderGroupMemberExample example);

    OmsOrderGroupMember selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OmsOrderGroupMember record, @Param("example") OmsOrderGroupMemberExample example);

    int updateByExample(@Param("record") OmsOrderGroupMember record, @Param("example") OmsOrderGroupMemberExample example);

    int updateByPrimaryKeySelective(OmsOrderGroupMember record);

    int updateByPrimaryKey(OmsOrderGroupMember record);
}