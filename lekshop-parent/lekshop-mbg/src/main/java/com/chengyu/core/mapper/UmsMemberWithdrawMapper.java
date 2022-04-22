package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberWithdraw;
import com.chengyu.core.model.UmsMemberWithdrawExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberWithdrawMapper {
    long countByExample(UmsMemberWithdrawExample example);

    int deleteByExample(UmsMemberWithdrawExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsMemberWithdraw record);

    int insertSelective(UmsMemberWithdraw record);

    List<UmsMemberWithdraw> selectByExample(UmsMemberWithdrawExample example);

    UmsMemberWithdraw selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsMemberWithdraw record, @Param("example") UmsMemberWithdrawExample example);

    int updateByExample(@Param("record") UmsMemberWithdraw record, @Param("example") UmsMemberWithdrawExample example);

    int updateByPrimaryKeySelective(UmsMemberWithdraw record);

    int updateByPrimaryKey(UmsMemberWithdraw record);
}