package com.chengyu.core.mapper;

import org.apache.ibatis.annotations.Param;

public interface CustomAdvertMapper {
	
    int updateAdvertNums(@Param("id") Integer id);
}