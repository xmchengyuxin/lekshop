package com.chengyu.core.mapper;

import com.chengyu.core.model.OmsOrderInvoice;
import com.chengyu.core.model.OmsOrderInvoiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsOrderInvoiceMapper {
    long countByExample(OmsOrderInvoiceExample example);

    int deleteByExample(OmsOrderInvoiceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OmsOrderInvoice record);

    int insertSelective(OmsOrderInvoice record);

    List<OmsOrderInvoice> selectByExample(OmsOrderInvoiceExample example);

    OmsOrderInvoice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OmsOrderInvoice record, @Param("example") OmsOrderInvoiceExample example);

    int updateByExample(@Param("record") OmsOrderInvoice record, @Param("example") OmsOrderInvoiceExample example);

    int updateByPrimaryKeySelective(OmsOrderInvoice record);

    int updateByPrimaryKey(OmsOrderInvoice record);
}