package com.chengyu.core.mapper;

import com.chengyu.core.model.OmsCar;
import com.chengyu.core.model.OmsCarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsCarMapper {
    long countByExample(OmsCarExample example);

    int deleteByExample(OmsCarExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OmsCar record);

    int insertSelective(OmsCar record);

    List<OmsCar> selectByExample(OmsCarExample example);

    OmsCar selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OmsCar record, @Param("example") OmsCarExample example);

    int updateByExample(@Param("record") OmsCar record, @Param("example") OmsCarExample example);

    int updateByPrimaryKeySelective(OmsCar record);

    int updateByPrimaryKey(OmsCar record);
}