package com.chengyu.core.mapper;

import com.chengyu.core.model.RepPlatformGoodsMonth;
import com.chengyu.core.model.RepPlatformGoodsMonthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepPlatformGoodsMonthMapper {
    long countByExample(RepPlatformGoodsMonthExample example);

    int deleteByExample(RepPlatformGoodsMonthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RepPlatformGoodsMonth record);

    int insertSelective(RepPlatformGoodsMonth record);

    List<RepPlatformGoodsMonth> selectByExample(RepPlatformGoodsMonthExample example);

    RepPlatformGoodsMonth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RepPlatformGoodsMonth record, @Param("example") RepPlatformGoodsMonthExample example);

    int updateByExample(@Param("record") RepPlatformGoodsMonth record, @Param("example") RepPlatformGoodsMonthExample example);

    int updateByPrimaryKeySelective(RepPlatformGoodsMonth record);

    int updateByPrimaryKey(RepPlatformGoodsMonth record);
}