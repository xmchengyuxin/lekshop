package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsShopFreightTemplate;
import com.chengyu.core.model.UmsShopFreightTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsShopFreightTemplateMapper {
    long countByExample(UmsShopFreightTemplateExample example);

    int deleteByExample(UmsShopFreightTemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsShopFreightTemplate record);

    int insertSelective(UmsShopFreightTemplate record);

    List<UmsShopFreightTemplate> selectByExample(UmsShopFreightTemplateExample example);

    UmsShopFreightTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsShopFreightTemplate record, @Param("example") UmsShopFreightTemplateExample example);

    int updateByExample(@Param("record") UmsShopFreightTemplate record, @Param("example") UmsShopFreightTemplateExample example);

    int updateByPrimaryKeySelective(UmsShopFreightTemplate record);

    int updateByPrimaryKey(UmsShopFreightTemplate record);
}