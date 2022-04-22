package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsShopService;
import com.chengyu.core.model.UmsShopServiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsShopServiceMapper {
    long countByExample(UmsShopServiceExample example);

    int deleteByExample(UmsShopServiceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsShopService record);

    int insertSelective(UmsShopService record);

    List<UmsShopService> selectByExample(UmsShopServiceExample example);

    UmsShopService selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsShopService record, @Param("example") UmsShopServiceExample example);

    int updateByExample(@Param("record") UmsShopService record, @Param("example") UmsShopServiceExample example);

    int updateByPrimaryKeySelective(UmsShopService record);

    int updateByPrimaryKey(UmsShopService record);
}