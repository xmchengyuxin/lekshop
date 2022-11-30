package com.chengyu.core.service.trade;


import com.chengyu.core.domain.form.TradeStockSearchForm;
import com.chengyu.core.domain.result.TradeStockEditResult;
import com.chengyu.core.domain.result.TradeStockOrderResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.TradeStorageStockEdit;
import com.chengyu.core.model.TradeStorageStockOrder;
import com.chengyu.core.model.TradeStorageStockOrderDetail;
import com.chengyu.core.model.UmsShop;

import java.util.List;

/**
 * @title  入库
 * @author LeGreen
 * @date   2022/8/29
 */
public interface TradeStorageStockOrderService {

    /**
     * 入库单查询
     * @author LeGreen
     * @date   2022/8/29
     * @param  form 查询表单
     * @param  page 分页
     * @param  pageSize 分页
     * @return List<TradeStorageStockOrder>
     */
    List<TradeStorageStockOrder> getStorageStockOrderList(TradeStockSearchForm form, Integer page, Integer pageSize);

    /**
     * 入库单明细
     * @author LeGreen
     * @date   2022/9/18
     * @param  orderId 订单ID
     * @return TradeStockOrderResult
     */
    TradeStockOrderResult getStockOrderResult(Integer orderId);

    /**
     * 添加入库单
     * @author LeGreen
     * @date   2022/8/29
     * @param  shop 当前店铺
     */
    void addStockOrder(UmsShop shop, TradeStorageStockOrder order, List<TradeStorageStockOrderDetail> orderDetailList) throws ServiceException;

    /**
     * 修改库存列表
     * @author LeGreen
     * @date   2022/8/29
     * @param  form 查询表单
     * @param  page 分页
     * @param  pageSize 分页
     * @return List<TradeStorageStockEdit>
     */
    List<TradeStorageStockEdit> getStorageStockEditList(TradeStockSearchForm form, Integer page, Integer pageSize);

    /**
     * 添加库存修改单
     * @author LeGreen
     * @date   2022/8/29
     * @param  stockEdit 库存修改单
     */
    void addStockEdit(TradeStorageStockEdit stockEdit) throws ServiceException;

    /**
     * 获取修改单详情
     * @author LeGreen
     * @date   2022/8/29
     * @param  id 修改单ID
     * @return TradeStockEditResult
     */
    TradeStockEditResult getStorageStockEditResult(Integer id);

}
