package com.chengyu.core.mapper;

import com.chengyu.core.domain.form.AccountSearchForm;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @title  收款账户
 * @author LeGreen
 * @date   2021/1/7
 */
public interface CustomConfigAccountMapper {

	void addAmount(@Param("accountId") Integer accountId, @Param("amount") Double amount, @Param("isUpdateToday") boolean isUpdateToday);

	void reduceAmount(@Param("accountId") Integer accountId, @Param("amount") Double amount, @Param("isUpdateToday") boolean isUpdateToday);

}