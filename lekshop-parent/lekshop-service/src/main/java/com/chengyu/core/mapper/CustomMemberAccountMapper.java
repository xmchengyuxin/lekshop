package com.chengyu.core.mapper;

import com.chengyu.core.domain.form.AccountSearchForm;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @title  会员钱包
 * @author LeGreen
 * @date   2021/1/7
 */
public interface CustomMemberAccountMapper {

	/**
	 * 余额入账
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId 用户ID
	 * @param  amount 金额
	 */
	void inAmount(@Param("memberId") Integer memberId, @Param("amount") Double amount);

	/**
	 * 余额出账
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId
	 * @param  amount
	 */
	void outAmount(@Param("memberId") Integer memberId, @Param("amount") Double amount);

	/**
	 * 余额冻结
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId
	 * @param  amount
	 */
	void freezeAmount(@Param("memberId") Integer memberId, @Param("amount") Double amount);

	/**
	 * 余额解冻
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId
	 * @param  amount
	 */
	void unfreezeAmount(@Param("memberId") Integer memberId, @Param("amount") Double amount);

	/**
	 * 佣金入账
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId
	 * @param  amount
	 */
	void inMission(@Param("memberId") Integer memberId, @Param("amount") Double amount);

	/**
	 * 佣金出账
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId
	 * @param  amount
	 */
	void outMission(@Param("memberId") Integer memberId, @Param("amount") Double amount);

	/**
	 * 佣金冻结
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId
	 * @param  amount
	 */
	void freezeMission(@Param("memberId") Integer memberId, @Param("amount") Double amount);

	/**
	 * 佣金解冻
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId
	 * @param  amount
	 */
	void unfreezeMission(@Param("memberId") Integer memberId, @Param("amount") Double amount);

	/**
	 * 推广佣金入账
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId
	 * @param  amount
	 */
	void inSpMission(@Param("memberId") Integer memberId, @Param("amount") Double amount);

	/**
	 * 推广佣金出账
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId
	 * @param  amount
	 */
	void outSpMission(@Param("memberId") Integer memberId, @Param("amount") Double amount);

	/**
	 * 推广佣金冻结
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId
	 * @param  amount
	 */
	void freezeSpMission(@Param("memberId") Integer memberId, @Param("amount") Double amount);

	/**
	 * 推广佣金解冻
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId
	 * @param  amount
	 */
	void unfreezeSpMission(@Param("memberId") Integer memberId, @Param("amount") Double amount);

	/**
	 * 积分入账
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId
	 * @param  amount
	 */
	void inPoint(@Param("memberId") Integer memberId, @Param("amount") Double amount);

	/**
	 * 积分出账
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId
	 * @param  amount
	 */
	void outPoint(@Param("memberId") Integer memberId, @Param("amount") Double amount);

	/**
	 * 积分冻结
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId
	 * @param  amount
	 */
	void freezePoint(@Param("memberId") Integer memberId, @Param("amount") Double amount);

	/**
	 * 积分解冻
	 * @author LeGreen
	 * @date   2021/1/7
	 * @param  memberId
	 * @param  amount
	 */
	void unfreezePoint(@Param("memberId") Integer memberId, @Param("amount") Double amount);

	/**
	 * 统计推广佣金
	 * @author LeGreen
	 * @date   2021/1/9
	 * @param  form
	 * @return java.math.BigDecimal
	 */
    BigDecimal countSpmissionIncome(@Param("form") AccountSearchForm form);

    /**
     * 今日总收入, 总收入, 总支出, 总充值, 总提现
     * @author LeGreen
     * @date   2021/5/29
     * @param  memberId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    Map<String, Object> count(@Param("memberId")Integer memberId);
}