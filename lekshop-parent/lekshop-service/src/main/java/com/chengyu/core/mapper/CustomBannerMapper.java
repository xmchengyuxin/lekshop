package com.chengyu.core.mapper;

import org.apache.ibatis.annotations.Param;

public interface CustomBannerMapper {
	
    int updateBannerNums(@Param("id") Integer id);
}