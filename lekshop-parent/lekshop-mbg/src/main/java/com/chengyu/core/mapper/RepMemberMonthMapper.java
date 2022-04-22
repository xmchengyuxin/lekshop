package com.chengyu.core.mapper;

import com.chengyu.core.model.RepMemberMonth;
import com.chengyu.core.model.RepMemberMonthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepMemberMonthMapper {
    long countByExample(RepMemberMonthExample example);

    int deleteByExample(RepMemberMonthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RepMemberMonth record);

    int insertSelective(RepMemberMonth record);

    List<RepMemberMonth> selectByExample(RepMemberMonthExample example);

    RepMemberMonth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RepMemberMonth record, @Param("example") RepMemberMonthExample example);

    int updateByExample(@Param("record") RepMemberMonth record, @Param("example") RepMemberMonthExample example);

    int updateByPrimaryKeySelective(RepMemberMonth record);

    int updateByPrimaryKey(RepMemberMonth record);
}