package com.chengyu.core.mapper;

import com.chengyu.core.model.WalkMember;
import com.chengyu.core.model.WalkMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WalkMemberMapper {
    long countByExample(WalkMemberExample example);

    int deleteByExample(WalkMemberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WalkMember record);

    int insertSelective(WalkMember record);

    List<WalkMember> selectByExample(WalkMemberExample example);

    WalkMember selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WalkMember record, @Param("example") WalkMemberExample example);

    int updateByExample(@Param("record") WalkMember record, @Param("example") WalkMemberExample example);

    int updateByPrimaryKeySelective(WalkMember record);

    int updateByPrimaryKey(WalkMember record);
}