package com.chengyu.core.mapper;

import com.chengyu.core.model.ConfigWithdraw;
import com.chengyu.core.model.ConfigWithdrawExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfigWithdrawMapper {
    long countByExample(ConfigWithdrawExample example);

    int deleteByExample(ConfigWithdrawExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConfigWithdraw record);

    int insertSelective(ConfigWithdraw record);

    List<ConfigWithdraw> selectByExample(ConfigWithdrawExample example);

    ConfigWithdraw selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConfigWithdraw record, @Param("example") ConfigWithdrawExample example);

    int updateByExample(@Param("record") ConfigWithdraw record, @Param("example") ConfigWithdrawExample example);

    int updateByPrimaryKeySelective(ConfigWithdraw record);

    int updateByPrimaryKey(ConfigWithdraw record);
}