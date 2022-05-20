package com.chengyu.core.mapper;

import com.chengyu.core.model.WalkTrendsCommentLike;
import com.chengyu.core.model.WalkTrendsCommentLikeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WalkTrendsCommentLikeMapper {
    long countByExample(WalkTrendsCommentLikeExample example);

    int deleteByExample(WalkTrendsCommentLikeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WalkTrendsCommentLike record);

    int insertSelective(WalkTrendsCommentLike record);

    List<WalkTrendsCommentLike> selectByExample(WalkTrendsCommentLikeExample example);

    WalkTrendsCommentLike selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WalkTrendsCommentLike record, @Param("example") WalkTrendsCommentLikeExample example);

    int updateByExample(@Param("record") WalkTrendsCommentLike record, @Param("example") WalkTrendsCommentLikeExample example);

    int updateByPrimaryKeySelective(WalkTrendsCommentLike record);

    int updateByPrimaryKey(WalkTrendsCommentLike record);
}