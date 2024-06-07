package com.chengyu.core.mapper;

import com.chengyu.core.model.PointGoodsCate;
import com.chengyu.core.model.PointGoodsCateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PointGoodsCateMapper {
    long countByExample(PointGoodsCateExample example);

    int deleteByExample(PointGoodsCateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PointGoodsCate record);

    int insertSelective(PointGoodsCate record);

    List<PointGoodsCate> selectByExample(PointGoodsCateExample example);

    PointGoodsCate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PointGoodsCate record, @Param("example") PointGoodsCateExample example);

    int updateByExample(@Param("record") PointGoodsCate record, @Param("example") PointGoodsCateExample example);

    int updateByPrimaryKeySelective(PointGoodsCate record);

    int updateByPrimaryKey(PointGoodsCate record);
}