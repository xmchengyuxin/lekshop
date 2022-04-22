package com.chengyu.core.mapper;

import com.chengyu.core.model.OmsPayOrderList;
import com.chengyu.core.model.OmsPayOrderListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsPayOrderListMapper {
    long countByExample(OmsPayOrderListExample example);

    int deleteByExample(OmsPayOrderListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OmsPayOrderList record);

    int insertSelective(OmsPayOrderList record);

    List<OmsPayOrderList> selectByExample(OmsPayOrderListExample example);

    OmsPayOrderList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OmsPayOrderList record, @Param("example") OmsPayOrderListExample example);

    int updateByExample(@Param("record") OmsPayOrderList record, @Param("example") OmsPayOrderListExample example);

    int updateByPrimaryKeySelective(OmsPayOrderList record);

    int updateByPrimaryKey(OmsPayOrderList record);
}