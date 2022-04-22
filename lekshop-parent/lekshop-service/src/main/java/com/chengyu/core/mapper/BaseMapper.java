package com.chengyu.core.mapper;

import org.apache.ibatis.annotations.Param;

public interface BaseMapper {

    void update(@Param("updateParams") String updateParams);
}