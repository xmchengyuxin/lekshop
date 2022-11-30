package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsShopAccountRole;
import com.chengyu.core.model.UmsShopAccountRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsShopAccountRoleMapper {
    long countByExample(UmsShopAccountRoleExample example);

    int deleteByExample(UmsShopAccountRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsShopAccountRole record);

    int insertSelective(UmsShopAccountRole record);

    List<UmsShopAccountRole> selectByExample(UmsShopAccountRoleExample example);

    UmsShopAccountRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsShopAccountRole record, @Param("example") UmsShopAccountRoleExample example);

    int updateByExample(@Param("record") UmsShopAccountRole record, @Param("example") UmsShopAccountRoleExample example);

    int updateByPrimaryKeySelective(UmsShopAccountRole record);

    int updateByPrimaryKey(UmsShopAccountRole record);
}