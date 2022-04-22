package com.chengyu.core.mapper;

import com.chengyu.core.model.ConfigOrder;
import com.chengyu.core.model.ConfigOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfigOrderMapper {
    long countByExample(ConfigOrderExample example);

    int deleteByExample(ConfigOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConfigOrder record);

    int insertSelective(ConfigOrder record);

    List<ConfigOrder> selectByExample(ConfigOrderExample example);

    ConfigOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConfigOrder record, @Param("example") ConfigOrderExample example);

    int updateByExample(@Param("record") ConfigOrder record, @Param("example") ConfigOrderExample example);

    int updateByPrimaryKeySelective(ConfigOrder record);

    int updateByPrimaryKey(ConfigOrder record);
}