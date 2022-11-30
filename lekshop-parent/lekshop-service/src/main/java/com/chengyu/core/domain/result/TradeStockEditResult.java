package com.chengyu.core.domain.result;

import com.chengyu.core.model.TradeStorageStockEdit;
import com.chengyu.core.model.TradeStorageStockEditProcess;
import lombok.Data;

import java.util.List;

/**
 * @title  库存修改单
 * @author LeGreen
 * @date   2022/8/29
 */
@Data
public class TradeStockEditResult {

    private TradeStorageStockEdit stockEdit;

    private List<TradeStorageStockEditProcess> processList;
}
