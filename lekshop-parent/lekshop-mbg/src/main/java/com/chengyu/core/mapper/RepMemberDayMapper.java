package com.chengyu.core.mapper;

import com.chengyu.core.model.RepMemberDay;
import com.chengyu.core.model.RepMemberDayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepMemberDayMapper {
    long countByExample(RepMemberDayExample example);

    int deleteByExample(RepMemberDayExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RepMemberDay record);

    int insertSelective(RepMemberDay record);

    List<RepMemberDay> selectByExample(RepMemberDayExample example);

    RepMemberDay selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RepMemberDay record, @Param("example") RepMemberDayExample example);

    int updateByExample(@Param("record") RepMemberDay record, @Param("example") RepMemberDayExample example);

    int updateByPrimaryKeySelective(RepMemberDay record);

    int updateByPrimaryKey(RepMemberDay record);
}