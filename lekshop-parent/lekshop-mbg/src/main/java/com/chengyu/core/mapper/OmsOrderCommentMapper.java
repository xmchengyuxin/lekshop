package com.chengyu.core.mapper;

import com.chengyu.core.model.OmsOrderComment;
import com.chengyu.core.model.OmsOrderCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsOrderCommentMapper {
    long countByExample(OmsOrderCommentExample example);

    int deleteByExample(OmsOrderCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OmsOrderComment record);

    int insertSelective(OmsOrderComment record);

    List<OmsOrderComment> selectByExample(OmsOrderCommentExample example);

    OmsOrderComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OmsOrderComment record, @Param("example") OmsOrderCommentExample example);

    int updateByExample(@Param("record") OmsOrderComment record, @Param("example") OmsOrderCommentExample example);

    int updateByPrimaryKeySelective(OmsOrderComment record);

    int updateByPrimaryKey(OmsOrderComment record);
}