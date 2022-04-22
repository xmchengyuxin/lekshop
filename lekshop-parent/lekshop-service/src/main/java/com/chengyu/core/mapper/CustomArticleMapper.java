package com.chengyu.core.mapper;

import org.apache.ibatis.annotations.Param;

public interface CustomArticleMapper {
	
    int updateArticleNums(@Param("id") Integer id);
}