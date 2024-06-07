package com.chengyu.core.service.trade;


import com.chengyu.core.model.TradeSupplier;

import java.util.List;

/**
 * @title  供应商
 * @author LeGreen
 * @date   2022/8/29
 */
public interface TradeSupplierService {

    /**
     * 供应商列表
     * @author LeGreen
     * @date   2022/8/29
     * @param  page 分页
     * @param  pageSize 分页
     * @return List<TradeSupplier>
     */
    List<TradeSupplier> getTradeSupplierList(Integer page, Integer pageSize);

    /**
     * 添加编辑供应商
     * @author LeGreen
     * @date   2022/8/29
     * @param  supplier 供应商信息
     */
    void addSupplier(TradeSupplier supplier);

    /**
     * 删除供应商
     * @author LeGreen
     * @date   2022/8/29
     * @param  idList 供应商ID
     */
    void deleteSupplier(List<Integer> idList);
}
