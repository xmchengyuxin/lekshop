package com.chengyu.core.mapper;

import com.chengyu.core.model.WalkMemberCollection;
import com.chengyu.core.model.WalkMemberCollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WalkMemberCollectionMapper {
    long countByExample(WalkMemberCollectionExample example);

    int deleteByExample(WalkMemberCollectionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WalkMemberCollection record);

    int insertSelective(WalkMemberCollection record);

    List<WalkMemberCollection> selectByExample(WalkMemberCollectionExample example);

    WalkMemberCollection selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WalkMemberCollection record, @Param("example") WalkMemberCollectionExample example);

    int updateByExample(@Param("record") WalkMemberCollection record, @Param("example") WalkMemberCollectionExample example);

    int updateByPrimaryKeySelective(WalkMemberCollection record);

    int updateByPrimaryKey(WalkMemberCollection record);
}