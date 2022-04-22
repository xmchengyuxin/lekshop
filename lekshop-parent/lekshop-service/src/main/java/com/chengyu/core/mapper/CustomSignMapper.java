package com.chengyu.core.mapper;

import org.apache.ibatis.annotations.Param;

public interface CustomSignMapper {
	
	Integer getSignDays(@Param("memberId") Integer memberId);
}