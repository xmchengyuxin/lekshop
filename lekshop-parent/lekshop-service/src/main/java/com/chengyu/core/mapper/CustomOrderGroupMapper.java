package com.chengyu.core.mapper;

import com.chengyu.core.model.OmsOrderGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomOrderGroupMapper {
	
	int getOrderGroupCount(
			@Param("memberId") Integer memberId,
			@Param("status") Integer status
	);
	

	List<OmsOrderGroup> getOrderGroupList(
			@Param("memberId") Integer memberId,
			@Param("status") Integer status);
	
	Long countGroupNum(@Param("goodsId") Integer goodsId);
}