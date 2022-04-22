package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberInvoice;
import com.chengyu.core.model.UmsMemberInvoiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberInvoiceMapper {
    long countByExample(UmsMemberInvoiceExample example);

    int deleteByExample(UmsMemberInvoiceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsMemberInvoice record);

    int insertSelective(UmsMemberInvoice record);

    List<UmsMemberInvoice> selectByExample(UmsMemberInvoiceExample example);

    UmsMemberInvoice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsMemberInvoice record, @Param("example") UmsMemberInvoiceExample example);

    int updateByExample(@Param("record") UmsMemberInvoice record, @Param("example") UmsMemberInvoiceExample example);

    int updateByPrimaryKeySelective(UmsMemberInvoice record);

    int updateByPrimaryKey(UmsMemberInvoice record);
}