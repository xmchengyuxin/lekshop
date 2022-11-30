package com.chengyu.core.service.trade;


import com.chengyu.core.model.TradeStorage;

import java.util.List;

/**
 * @title  仓库
 * @author LeGreen
 * @date   2022/8/29
 */
public interface TradeStorageService {

    /**
     * 仓库列表
     * @author LeGreen
     * @date   2022/8/29
     * @param  page 分页
     * @param  pageSize 分页
     * @return List<TradeStorage>
     */
    List<TradeStorage> getTradeStorageList(Integer page, Integer pageSize);

    /**
     * 添加编辑仓库
     * @author LeGreen
     * @date   2022/8/29
     * @param  storage 仓库信息
     */
    void addStorage(TradeStorage storage);

    /**
     * 删除仓库
     * @author LeGreen
     * @date   2022/8/29
     * @param  idList 仓库ID
     */
    void deleteStorage(List<Integer> idList);
}
