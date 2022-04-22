package com.chengyu.core.mapper;

import com.chengyu.core.model.WalkTrendsComment;
import com.chengyu.core.model.WalkTrendsCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WalkTrendsCommentMapper {
    long countByExample(WalkTrendsCommentExample example);

    int deleteByExample(WalkTrendsCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WalkTrendsComment record);

    int insertSelective(WalkTrendsComment record);

    List<WalkTrendsComment> selectByExampleWithBLOBs(WalkTrendsCommentExample example);

    List<WalkTrendsComment> selectByExample(WalkTrendsCommentExample example);

    WalkTrendsComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WalkTrendsComment record, @Param("example") WalkTrendsCommentExample example);

    int updateByExampleWithBLOBs(@Param("record") WalkTrendsComment record, @Param("example") WalkTrendsCommentExample example);

    int updateByExample(@Param("record") WalkTrendsComment record, @Param("example") WalkTrendsCommentExample example);

    int updateByPrimaryKeySelective(WalkTrendsComment record);

    int updateByPrimaryKeyWithBLOBs(WalkTrendsComment record);

    int updateByPrimaryKey(WalkTrendsComment record);
}