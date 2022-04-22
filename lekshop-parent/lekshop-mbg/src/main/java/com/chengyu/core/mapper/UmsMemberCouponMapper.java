package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberCoupon;
import com.chengyu.core.model.UmsMemberCouponExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberCouponMapper {
    long countByExample(UmsMemberCouponExample example);

    int deleteByExample(UmsMemberCouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsMemberCoupon record);

    int insertSelective(UmsMemberCoupon record);

    List<UmsMemberCoupon> selectByExample(UmsMemberCouponExample example);

    UmsMemberCoupon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsMemberCoupon record, @Param("example") UmsMemberCouponExample example);

    int updateByExample(@Param("record") UmsMemberCoupon record, @Param("example") UmsMemberCouponExample example);

    int updateByPrimaryKeySelective(UmsMemberCoupon record);

    int updateByPrimaryKey(UmsMemberCoupon record);
}