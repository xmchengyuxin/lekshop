package com.chengyu.core.service.trade;


import com.chengyu.core.domain.form.TradeStockCountingSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.TradeStorageStockCounting;
import com.chengyu.core.model.TradeStorageStockCountingDetail;
import com.chengyu.core.model.UmsShopAccount;

import java.util.List;

/**
 * @title  盲盘
 * @author LeGreen
 * @date   2022/8/30
 */
public interface TradeStorageCountingService {

    /**
     * 盲盘查询
     * @author LeGreen
     * @date   2022/8/30
     * @param  form 查询表单
     * @param  page 分页
     * @param  pageSize 分页
     * @return List<TradeStorageStockCounting>
     */
    List<TradeStorageStockCounting> getStockCountingList(TradeStockCountingSearchForm form, Integer page, Integer pageSize);

    /**
     * 添加盲盘
     * @author LeGreen
     * @date   2022/8/30
     * @param  member 仓储文员
     * @param  countingDetailList 盲盘明细
     */
    void addStockCounting(UmsShopAccount member, List<TradeStorageStockCountingDetail> countingDetailList) throws ServiceException;

    /**
     * 盲盘明细
     * @author LeGreen
     * @date   2022/8/30
     * @param  stockCountingId 盲盘ID
     * @param  page 分页
     * @param  pageSize 分页
     * @return List<TradeStorageStockCountingDetail>
     */
    List<TradeStorageStockCountingDetail> getStockCountingDetailList(Integer stockCountingId, Integer status, Integer page, Integer pageSize);
}
