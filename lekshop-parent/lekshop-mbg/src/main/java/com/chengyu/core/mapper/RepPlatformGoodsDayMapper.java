package com.chengyu.core.mapper;

import com.chengyu.core.model.RepPlatformGoodsDay;
import com.chengyu.core.model.RepPlatformGoodsDayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepPlatformGoodsDayMapper {
    long countByExample(RepPlatformGoodsDayExample example);

    int deleteByExample(RepPlatformGoodsDayExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RepPlatformGoodsDay record);

    int insertSelective(RepPlatformGoodsDay record);

    List<RepPlatformGoodsDay> selectByExample(RepPlatformGoodsDayExample example);

    RepPlatformGoodsDay selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RepPlatformGoodsDay record, @Param("example") RepPlatformGoodsDayExample example);

    int updateByExample(@Param("record") RepPlatformGoodsDay record, @Param("example") RepPlatformGoodsDayExample example);

    int updateByPrimaryKeySelective(RepPlatformGoodsDay record);

    int updateByPrimaryKey(RepPlatformGoodsDay record);
}