package com.chengyu.core.service.trade;


import com.chengyu.core.domain.form.StockLogSearchForm;
import com.chengyu.core.domain.form.TradeStockSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.TradeStorageStock;
import com.chengyu.core.model.TradeStorageStockLog;

import java.util.List;

/**
 * @title  仓库库存
 * @author LeGreen
 * @date   2022/8/29
 */
public interface TradeStorageStockService {

    /**
     * 查询仓库商品库存
     * @author LeGreen
     * @date   2022/8/29
     * @param  form 查询表单
     * @param  page 分页
     * @param  pageSize 分页
     * @return List<TradeStorageStock>
     */
    List<TradeStorageStock> getStockList(TradeStockSearchForm form, Integer page, Integer pageSize);

    /**
     * 入库
     * @author LeGreen
     * @date   2022/8/29
     * @param  storageId 仓库ID
     * @param  goodsSkuId 商品ID
     * @param  num 数量
     */
    void inStock(String orderNo, Integer storageId, String spaceNo, String spaceName, Integer goodsSkuId, Integer num, String remark);

    /**
     * 出库
     * @author LeGreen
     * @date   2022/8/29
     * @param  storageId 仓库ID
     * @param  goodsSkuId 商品ID
     * @param  num 数量
     * @throws ServiceException 业务异常
     */
    void outStock(String orderNo, Integer storageId, Integer goodsSkuId, Integer num, String remark) throws ServiceException;

    /**
     * 更新预警库存
     * @author LeGreen
     * @date   2022/8/30
     * @param  stockList 预警库存列表
     */
    void updateWarnStock(List<TradeStorageStock> stockList);

    /**
     * 出入库记录
     * @author LeGreen
     * @date   2022/9/20
     * @param  form 查询表单
     * @param  page 分页
     * @param  pageSize 分页
     * @return List<TradeStorageStockLog>
     */
    List<TradeStorageStockLog> getStockLogList(StockLogSearchForm form, Integer page, Integer pageSize);
}
