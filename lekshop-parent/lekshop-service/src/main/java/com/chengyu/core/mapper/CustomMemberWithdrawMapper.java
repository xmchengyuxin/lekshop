package com.chengyu.core.mapper;

import com.chengyu.core.domain.form.WithdrawSearchForm;
import com.chengyu.core.domain.result.WithdrawCountResult;
import org.apache.ibatis.annotations.Param;

public interface CustomMemberWithdrawMapper {

	WithdrawCountResult countWithdraw(@Param("form") WithdrawSearchForm form);
}