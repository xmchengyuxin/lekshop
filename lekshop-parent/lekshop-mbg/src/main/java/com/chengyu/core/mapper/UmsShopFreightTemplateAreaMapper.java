package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsShopFreightTemplateArea;
import com.chengyu.core.model.UmsShopFreightTemplateAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsShopFreightTemplateAreaMapper {
    long countByExample(UmsShopFreightTemplateAreaExample example);

    int deleteByExample(UmsShopFreightTemplateAreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsShopFreightTemplateArea record);

    int insertSelective(UmsShopFreightTemplateArea record);

    List<UmsShopFreightTemplateArea> selectByExample(UmsShopFreightTemplateAreaExample example);

    UmsShopFreightTemplateArea selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsShopFreightTemplateArea record, @Param("example") UmsShopFreightTemplateAreaExample example);

    int updateByExample(@Param("record") UmsShopFreightTemplateArea record, @Param("example") UmsShopFreightTemplateAreaExample example);

    int updateByPrimaryKeySelective(UmsShopFreightTemplateArea record);

    int updateByPrimaryKey(UmsShopFreightTemplateArea record);
}