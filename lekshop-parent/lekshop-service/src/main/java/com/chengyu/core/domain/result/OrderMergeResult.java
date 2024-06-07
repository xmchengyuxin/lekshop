package com.chengyu.core.domain.result;

import com.chengyu.core.model.OmsOrderMerge;
import com.chengyu.core.model.OmsOrderMergeProcess;
import lombok.Data;

import java.util.List;

/**
 * @title  以下单人做区分的合并订单
 * @author LeGreen
 * @date   2022/8/22
 */
@Data
public class OrderMergeResult {

    private OmsOrderMerge orderMerge;

    private List<OrderMergeDetailResult> mergeDetailList;

    private List<OmsOrderMergeProcess> processList;
}
