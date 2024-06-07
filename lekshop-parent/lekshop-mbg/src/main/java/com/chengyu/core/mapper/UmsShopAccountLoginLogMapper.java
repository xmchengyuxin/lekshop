package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsShopAccountLoginLog;
import com.chengyu.core.model.UmsShopAccountLoginLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsShopAccountLoginLogMapper {
    long countByExample(UmsShopAccountLoginLogExample example);

    int deleteByExample(UmsShopAccountLoginLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsShopAccountLoginLog record);

    int insertSelective(UmsShopAccountLoginLog record);

    List<UmsShopAccountLoginLog> selectByExample(UmsShopAccountLoginLogExample example);

    UmsShopAccountLoginLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsShopAccountLoginLog record, @Param("example") UmsShopAccountLoginLogExample example);

    int updateByExample(@Param("record") UmsShopAccountLoginLog record, @Param("example") UmsShopAccountLoginLogExample example);

    int updateByPrimaryKeySelective(UmsShopAccountLoginLog record);

    int updateByPrimaryKey(UmsShopAccountLoginLog record);
}