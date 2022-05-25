package com.chengyu.core.mapper;

import com.chengyu.core.model.ImChatLog;
import com.chengyu.core.model.ImChatLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ImChatLogMapper {
    long countByExample(ImChatLogExample example);

    int deleteByExample(ImChatLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ImChatLog record);

    int insertSelective(ImChatLog record);

    List<ImChatLog> selectByExampleWithBLOBs(ImChatLogExample example);

    List<ImChatLog> selectByExample(ImChatLogExample example);

    ImChatLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ImChatLog record, @Param("example") ImChatLogExample example);

    int updateByExampleWithBLOBs(@Param("record") ImChatLog record, @Param("example") ImChatLogExample example);

    int updateByExample(@Param("record") ImChatLog record, @Param("example") ImChatLogExample example);

    int updateByPrimaryKeySelective(ImChatLog record);

    int updateByPrimaryKeyWithBLOBs(ImChatLog record);

    int updateByPrimaryKey(ImChatLog record);
}