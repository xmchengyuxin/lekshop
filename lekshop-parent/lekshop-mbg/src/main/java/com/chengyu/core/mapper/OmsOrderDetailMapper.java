package com.chengyu.core.mapper;

import com.chengyu.core.model.OmsOrderDetail;
import com.chengyu.core.model.OmsOrderDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsOrderDetailMapper {
    long countByExample(OmsOrderDetailExample example);

    int deleteByExample(OmsOrderDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OmsOrderDetail record);

    int insertSelective(OmsOrderDetail record);

    List<OmsOrderDetail> selectByExample(OmsOrderDetailExample example);

    OmsOrderDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OmsOrderDetail record, @Param("example") OmsOrderDetailExample example);

    int updateByExample(@Param("record") OmsOrderDetail record, @Param("example") OmsOrderDetailExample example);

    int updateByPrimaryKeySelective(OmsOrderDetail record);

    int updateByPrimaryKey(OmsOrderDetail record);
}