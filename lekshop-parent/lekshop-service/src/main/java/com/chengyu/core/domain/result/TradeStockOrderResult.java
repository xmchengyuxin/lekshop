package com.chengyu.core.domain.result;

import com.chengyu.core.model.TradeStorageStockOrder;
import com.chengyu.core.model.TradeStorageStockOrderDetail;
import lombok.Data;

import java.util.List;

@Data
public class TradeStockOrderResult {

    private TradeStorageStockOrder order;

    private List<TradeStorageStockOrderDetail> orderDetailList;
}
