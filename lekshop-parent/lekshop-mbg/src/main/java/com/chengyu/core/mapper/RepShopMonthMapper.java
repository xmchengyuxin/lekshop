package com.chengyu.core.mapper;

import com.chengyu.core.model.RepShopMonth;
import com.chengyu.core.model.RepShopMonthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepShopMonthMapper {
    long countByExample(RepShopMonthExample example);

    int deleteByExample(RepShopMonthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RepShopMonth record);

    int insertSelective(RepShopMonth record);

    List<RepShopMonth> selectByExample(RepShopMonthExample example);

    RepShopMonth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RepShopMonth record, @Param("example") RepShopMonthExample example);

    int updateByExample(@Param("record") RepShopMonth record, @Param("example") RepShopMonthExample example);

    int updateByPrimaryKeySelective(RepShopMonth record);

    int updateByPrimaryKey(RepShopMonth record);
}