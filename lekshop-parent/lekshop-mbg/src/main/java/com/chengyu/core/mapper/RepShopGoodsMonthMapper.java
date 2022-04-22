package com.chengyu.core.mapper;

import com.chengyu.core.model.RepShopGoodsMonth;
import com.chengyu.core.model.RepShopGoodsMonthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepShopGoodsMonthMapper {
    long countByExample(RepShopGoodsMonthExample example);

    int deleteByExample(RepShopGoodsMonthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RepShopGoodsMonth record);

    int insertSelective(RepShopGoodsMonth record);

    List<RepShopGoodsMonth> selectByExample(RepShopGoodsMonthExample example);

    RepShopGoodsMonth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RepShopGoodsMonth record, @Param("example") RepShopGoodsMonthExample example);

    int updateByExample(@Param("record") RepShopGoodsMonth record, @Param("example") RepShopGoodsMonthExample example);

    int updateByPrimaryKeySelective(RepShopGoodsMonth record);

    int updateByPrimaryKey(RepShopGoodsMonth record);
}