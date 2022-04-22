package com.chengyu.core.mapper;

import com.chengyu.core.model.OmsOrderFreightDetail;
import com.chengyu.core.model.OmsOrderFreightDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsOrderFreightDetailMapper {
    long countByExample(OmsOrderFreightDetailExample example);

    int deleteByExample(OmsOrderFreightDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OmsOrderFreightDetail record);

    int insertSelective(OmsOrderFreightDetail record);

    List<OmsOrderFreightDetail> selectByExample(OmsOrderFreightDetailExample example);

    OmsOrderFreightDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OmsOrderFreightDetail record, @Param("example") OmsOrderFreightDetailExample example);

    int updateByExample(@Param("record") OmsOrderFreightDetail record, @Param("example") OmsOrderFreightDetailExample example);

    int updateByPrimaryKeySelective(OmsOrderFreightDetail record);

    int updateByPrimaryKey(OmsOrderFreightDetail record);
}