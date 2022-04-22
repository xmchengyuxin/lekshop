package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberPunishment;
import com.chengyu.core.model.UmsMemberPunishmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberPunishmentMapper {
    long countByExample(UmsMemberPunishmentExample example);

    int deleteByExample(UmsMemberPunishmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsMemberPunishment record);

    int insertSelective(UmsMemberPunishment record);

    List<UmsMemberPunishment> selectByExample(UmsMemberPunishmentExample example);

    UmsMemberPunishment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsMemberPunishment record, @Param("example") UmsMemberPunishmentExample example);

    int updateByExample(@Param("record") UmsMemberPunishment record, @Param("example") UmsMemberPunishmentExample example);

    int updateByPrimaryKeySelective(UmsMemberPunishment record);

    int updateByPrimaryKey(UmsMemberPunishment record);
}