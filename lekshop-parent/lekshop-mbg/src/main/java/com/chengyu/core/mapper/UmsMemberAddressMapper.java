package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberAddress;
import com.chengyu.core.model.UmsMemberAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberAddressMapper {
    long countByExample(UmsMemberAddressExample example);

    int deleteByExample(UmsMemberAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsMemberAddress record);

    int insertSelective(UmsMemberAddress record);

    List<UmsMemberAddress> selectByExample(UmsMemberAddressExample example);

    UmsMemberAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsMemberAddress record, @Param("example") UmsMemberAddressExample example);

    int updateByExample(@Param("record") UmsMemberAddress record, @Param("example") UmsMemberAddressExample example);

    int updateByPrimaryKeySelective(UmsMemberAddress record);

    int updateByPrimaryKey(UmsMemberAddress record);
}