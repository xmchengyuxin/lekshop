package com.chengyu.core.mapper;

import com.chengyu.core.model.PmsGoodsCate;
import com.chengyu.core.model.PmsGoodsCateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsGoodsCateMapper {
    long countByExample(PmsGoodsCateExample example);

    int deleteByExample(PmsGoodsCateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PmsGoodsCate record);

    int insertSelective(PmsGoodsCate record);

    List<PmsGoodsCate> selectByExample(PmsGoodsCateExample example);

    PmsGoodsCate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PmsGoodsCate record, @Param("example") PmsGoodsCateExample example);

    int updateByExample(@Param("record") PmsGoodsCate record, @Param("example") PmsGoodsCateExample example);

    int updateByPrimaryKeySelective(PmsGoodsCate record);

    int updateByPrimaryKey(PmsGoodsCate record);
}