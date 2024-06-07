package com.chengyu.core.service.trade.impl;

import com.chengyu.core.mapper.TradeStorageMapper;
import com.chengyu.core.model.TradeStorage;
import com.chengyu.core.model.TradeStorageExample;
import com.chengyu.core.service.trade.TradeStorageService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TradeStorageServiceImpl implements TradeStorageService {

    @Autowired
    private TradeStorageMapper tradeStorageMapper;

    @Override
    public List<TradeStorage> getTradeStorageList(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        TradeStorageExample example = new TradeStorageExample();
        return tradeStorageMapper.selectByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addStorage(TradeStorage storage) {
        if (storage.getId() == null) {
            tradeStorageMapper.insertSelective(storage);
        } else {
            tradeStorageMapper.updateByPrimaryKeySelective(storage);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteStorage(List<Integer> idList) {
        TradeStorageExample example = new TradeStorageExample();
        example.createCriteria().andIdIn(idList);
        tradeStorageMapper.deleteByExample(example);
    }
}