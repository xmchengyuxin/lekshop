package com.chengyu.core.mapper;

import org.apache.ibatis.annotations.Param;

public interface CustomChatMapper {

    Long countUnReadNum(@Param("memberId")Integer memberId);
}