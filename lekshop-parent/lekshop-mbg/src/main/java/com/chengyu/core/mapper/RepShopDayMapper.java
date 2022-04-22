package com.chengyu.core.mapper;

import com.chengyu.core.model.RepShopDay;
import com.chengyu.core.model.RepShopDayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepShopDayMapper {
    long countByExample(RepShopDayExample example);

    int deleteByExample(RepShopDayExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RepShopDay record);

    int insertSelective(RepShopDay record);

    List<RepShopDay> selectByExample(RepShopDayExample example);

    RepShopDay selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RepShopDay record, @Param("example") RepShopDayExample example);

    int updateByExample(@Param("record") RepShopDay record, @Param("example") RepShopDayExample example);

    int updateByPrimaryKeySelective(RepShopDay record);

    int updateByPrimaryKey(RepShopDay record);
}