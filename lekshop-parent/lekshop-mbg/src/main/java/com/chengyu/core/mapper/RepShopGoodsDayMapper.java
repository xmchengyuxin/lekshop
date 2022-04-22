package com.chengyu.core.mapper;

import com.chengyu.core.model.RepShopGoodsDay;
import com.chengyu.core.model.RepShopGoodsDayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepShopGoodsDayMapper {
    long countByExample(RepShopGoodsDayExample example);

    int deleteByExample(RepShopGoodsDayExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RepShopGoodsDay record);

    int insertSelective(RepShopGoodsDay record);

    List<RepShopGoodsDay> selectByExample(RepShopGoodsDayExample example);

    RepShopGoodsDay selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RepShopGoodsDay record, @Param("example") RepShopGoodsDayExample example);

    int updateByExample(@Param("record") RepShopGoodsDay record, @Param("example") RepShopGoodsDayExample example);

    int updateByPrimaryKeySelective(RepShopGoodsDay record);

    int updateByPrimaryKey(RepShopGoodsDay record);
}