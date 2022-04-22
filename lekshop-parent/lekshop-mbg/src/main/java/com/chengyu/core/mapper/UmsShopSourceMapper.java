package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsShopSource;
import com.chengyu.core.model.UmsShopSourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsShopSourceMapper {
    long countByExample(UmsShopSourceExample example);

    int deleteByExample(UmsShopSourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsShopSource record);

    int insertSelective(UmsShopSource record);

    List<UmsShopSource> selectByExample(UmsShopSourceExample example);

    UmsShopSource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsShopSource record, @Param("example") UmsShopSourceExample example);

    int updateByExample(@Param("record") UmsShopSource record, @Param("example") UmsShopSourceExample example);

    int updateByPrimaryKeySelective(UmsShopSource record);

    int updateByPrimaryKey(UmsShopSource record);
}