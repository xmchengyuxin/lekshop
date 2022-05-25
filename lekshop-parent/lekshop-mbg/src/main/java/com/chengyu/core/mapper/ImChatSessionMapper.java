package com.chengyu.core.mapper;

import com.chengyu.core.model.ImChatSession;
import com.chengyu.core.model.ImChatSessionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ImChatSessionMapper {
    long countByExample(ImChatSessionExample example);

    int deleteByExample(ImChatSessionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ImChatSession record);

    int insertSelective(ImChatSession record);

    List<ImChatSession> selectByExample(ImChatSessionExample example);

    ImChatSession selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ImChatSession record, @Param("example") ImChatSessionExample example);

    int updateByExample(@Param("record") ImChatSession record, @Param("example") ImChatSessionExample example);

    int updateByPrimaryKeySelective(ImChatSession record);

    int updateByPrimaryKey(ImChatSession record);
}