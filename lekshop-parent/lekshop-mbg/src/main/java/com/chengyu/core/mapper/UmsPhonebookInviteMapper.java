package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsPhonebookInvite;
import com.chengyu.core.model.UmsPhonebookInviteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsPhonebookInviteMapper {
    long countByExample(UmsPhonebookInviteExample example);

    int deleteByExample(UmsPhonebookInviteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsPhonebookInvite record);

    int insertSelective(UmsPhonebookInvite record);

    List<UmsPhonebookInvite> selectByExample(UmsPhonebookInviteExample example);

    UmsPhonebookInvite selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsPhonebookInvite record, @Param("example") UmsPhonebookInviteExample example);

    int updateByExample(@Param("record") UmsPhonebookInvite record, @Param("example") UmsPhonebookInviteExample example);

    int updateByPrimaryKeySelective(UmsPhonebookInvite record);

    int updateByPrimaryKey(UmsPhonebookInvite record);
}