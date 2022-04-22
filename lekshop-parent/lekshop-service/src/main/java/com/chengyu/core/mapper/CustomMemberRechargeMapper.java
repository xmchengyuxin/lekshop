package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberRecharge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomMemberRechargeMapper {

	List<UmsMemberRecharge> getRechargeTemplateList(@Param("memberId") Integer memberId);
}