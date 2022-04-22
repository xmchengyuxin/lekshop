package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberRecharge;
import com.chengyu.core.model.UmsMemberRechargeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberRechargeMapper {
    long countByExample(UmsMemberRechargeExample example);

    int deleteByExample(UmsMemberRechargeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsMemberRecharge record);

    int insertSelective(UmsMemberRecharge record);

    List<UmsMemberRecharge> selectByExample(UmsMemberRechargeExample example);

    UmsMemberRecharge selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsMemberRecharge record, @Param("example") UmsMemberRechargeExample example);

    int updateByExample(@Param("record") UmsMemberRecharge record, @Param("example") UmsMemberRechargeExample example);

    int updateByPrimaryKeySelective(UmsMemberRecharge record);

    int updateByPrimaryKey(UmsMemberRecharge record);
}