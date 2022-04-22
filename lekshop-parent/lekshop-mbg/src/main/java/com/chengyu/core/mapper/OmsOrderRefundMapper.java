package com.chengyu.core.mapper;

import com.chengyu.core.model.OmsOrderRefund;
import com.chengyu.core.model.OmsOrderRefundExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsOrderRefundMapper {
    long countByExample(OmsOrderRefundExample example);

    int deleteByExample(OmsOrderRefundExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OmsOrderRefund record);

    int insertSelective(OmsOrderRefund record);

    List<OmsOrderRefund> selectByExample(OmsOrderRefundExample example);

    OmsOrderRefund selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OmsOrderRefund record, @Param("example") OmsOrderRefundExample example);

    int updateByExample(@Param("record") OmsOrderRefund record, @Param("example") OmsOrderRefundExample example);

    int updateByPrimaryKeySelective(OmsOrderRefund record);

    int updateByPrimaryKey(OmsOrderRefund record);
}