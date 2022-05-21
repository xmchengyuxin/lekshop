package com.chengyu.core.mapper;

import com.chengyu.core.model.OmsOrderCommentLeft;
import com.chengyu.core.model.OmsOrderCommentLeftExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsOrderCommentLeftMapper {
    long countByExample(OmsOrderCommentLeftExample example);

    int deleteByExample(OmsOrderCommentLeftExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OmsOrderCommentLeft record);

    int insertSelective(OmsOrderCommentLeft record);

    List<OmsOrderCommentLeft> selectByExample(OmsOrderCommentLeftExample example);

    OmsOrderCommentLeft selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OmsOrderCommentLeft record, @Param("example") OmsOrderCommentLeftExample example);

    int updateByExample(@Param("record") OmsOrderCommentLeft record, @Param("example") OmsOrderCommentLeftExample example);

    int updateByPrimaryKeySelective(OmsOrderCommentLeft record);

    int updateByPrimaryKey(OmsOrderCommentLeft record);
}