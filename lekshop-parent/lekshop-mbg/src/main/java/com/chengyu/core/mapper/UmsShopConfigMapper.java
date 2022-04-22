package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsShopConfig;
import com.chengyu.core.model.UmsShopConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsShopConfigMapper {
    long countByExample(UmsShopConfigExample example);

    int deleteByExample(UmsShopConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsShopConfig record);

    int insertSelective(UmsShopConfig record);

    List<UmsShopConfig> selectByExample(UmsShopConfigExample example);

    UmsShopConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsShopConfig record, @Param("example") UmsShopConfigExample example);

    int updateByExample(@Param("record") UmsShopConfig record, @Param("example") UmsShopConfigExample example);

    int updateByPrimaryKeySelective(UmsShopConfig record);

    int updateByPrimaryKey(UmsShopConfig record);
}