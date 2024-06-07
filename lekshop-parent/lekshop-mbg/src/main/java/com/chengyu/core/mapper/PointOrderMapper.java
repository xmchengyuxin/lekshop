package com.chengyu.core.mapper;

import com.chengyu.core.model.PointOrder;
import com.chengyu.core.model.PointOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PointOrderMapper {
    long countByExample(PointOrderExample example);

    int deleteByExample(PointOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PointOrder record);

    int insertSelective(PointOrder record);

    List<PointOrder> selectByExample(PointOrderExample example);

    PointOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PointOrder record, @Param("example") PointOrderExample example);

    int updateByExample(@Param("record") PointOrder record, @Param("example") PointOrderExample example);

    int updateByPrimaryKeySelective(PointOrder record);

    int updateByPrimaryKey(PointOrder record);
}