package com.chengyu.core.mapper;

import com.chengyu.core.model.OmsOrderRefundLog;
import com.chengyu.core.model.OmsOrderRefundLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsOrderRefundLogMapper {
    long countByExample(OmsOrderRefundLogExample example);

    int deleteByExample(OmsOrderRefundLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OmsOrderRefundLog record);

    int insertSelective(OmsOrderRefundLog record);

    List<OmsOrderRefundLog> selectByExample(OmsOrderRefundLogExample example);

    OmsOrderRefundLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OmsOrderRefundLog record, @Param("example") OmsOrderRefundLogExample example);

    int updateByExample(@Param("record") OmsOrderRefundLog record, @Param("example") OmsOrderRefundLogExample example);

    int updateByPrimaryKeySelective(OmsOrderRefundLog record);

    int updateByPrimaryKey(OmsOrderRefundLog record);
}