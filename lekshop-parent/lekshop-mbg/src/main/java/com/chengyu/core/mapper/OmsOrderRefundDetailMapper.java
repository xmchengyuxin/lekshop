package com.chengyu.core.mapper;

import com.chengyu.core.model.OmsOrderRefundDetail;
import com.chengyu.core.model.OmsOrderRefundDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsOrderRefundDetailMapper {
    long countByExample(OmsOrderRefundDetailExample example);

    int deleteByExample(OmsOrderRefundDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OmsOrderRefundDetail record);

    int insertSelective(OmsOrderRefundDetail record);

    List<OmsOrderRefundDetail> selectByExample(OmsOrderRefundDetailExample example);

    OmsOrderRefundDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OmsOrderRefundDetail record, @Param("example") OmsOrderRefundDetailExample example);

    int updateByExample(@Param("record") OmsOrderRefundDetail record, @Param("example") OmsOrderRefundDetailExample example);

    int updateByPrimaryKeySelective(OmsOrderRefundDetail record);

    int updateByPrimaryKey(OmsOrderRefundDetail record);
}