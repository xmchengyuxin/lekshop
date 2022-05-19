package com.chengyu.core.mapper;

import com.chengyu.core.model.WalkTrendsCollection;
import com.chengyu.core.model.WalkTrendsCollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WalkTrendsCollectionMapper {
    long countByExample(WalkTrendsCollectionExample example);

    int deleteByExample(WalkTrendsCollectionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WalkTrendsCollection record);

    int insertSelective(WalkTrendsCollection record);

    List<WalkTrendsCollection> selectByExampleWithBLOBs(WalkTrendsCollectionExample example);

    List<WalkTrendsCollection> selectByExample(WalkTrendsCollectionExample example);

    WalkTrendsCollection selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WalkTrendsCollection record, @Param("example") WalkTrendsCollectionExample example);

    int updateByExampleWithBLOBs(@Param("record") WalkTrendsCollection record, @Param("example") WalkTrendsCollectionExample example);

    int updateByExample(@Param("record") WalkTrendsCollection record, @Param("example") WalkTrendsCollectionExample example);

    int updateByPrimaryKeySelective(WalkTrendsCollection record);

    int updateByPrimaryKeyWithBLOBs(WalkTrendsCollection record);

    int updateByPrimaryKey(WalkTrendsCollection record);
}