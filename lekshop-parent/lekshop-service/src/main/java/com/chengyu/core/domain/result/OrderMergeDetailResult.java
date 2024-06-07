package com.chengyu.core.domain.result;

import lombok.Data;

import java.util.List;

/**
 * @title  以下单人做区分的合并订单
 * @author LeGreen
 * @date   2022/8/22
 */
@Data
public class OrderMergeDetailResult {

    private Integer memberId;

    private String memberName;

    private List<OrderResult> orderResultList;
}
