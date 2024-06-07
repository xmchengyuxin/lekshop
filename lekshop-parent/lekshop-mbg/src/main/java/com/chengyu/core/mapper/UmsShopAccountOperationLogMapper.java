package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsShopAccountOperationLog;
import com.chengyu.core.model.UmsShopAccountOperationLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsShopAccountOperationLogMapper {
    long countByExample(UmsShopAccountOperationLogExample example);

    int deleteByExample(UmsShopAccountOperationLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsShopAccountOperationLog record);

    int insertSelective(UmsShopAccountOperationLog record);

    List<UmsShopAccountOperationLog> selectByExampleWithBLOBs(UmsShopAccountOperationLogExample example);

    List<UmsShopAccountOperationLog> selectByExample(UmsShopAccountOperationLogExample example);

    UmsShopAccountOperationLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsShopAccountOperationLog record, @Param("example") UmsShopAccountOperationLogExample example);

    int updateByExampleWithBLOBs(@Param("record") UmsShopAccountOperationLog record, @Param("example") UmsShopAccountOperationLogExample example);

    int updateByExample(@Param("record") UmsShopAccountOperationLog record, @Param("example") UmsShopAccountOperationLogExample example);

    int updateByPrimaryKeySelective(UmsShopAccountOperationLog record);

    int updateByPrimaryKeyWithBLOBs(UmsShopAccountOperationLog record);

    int updateByPrimaryKey(UmsShopAccountOperationLog record);
}