package com.chengyu.core.service.trade.impl;

import com.chengyu.core.mapper.TradeSupplierMapper;
import com.chengyu.core.model.TradeSupplier;
import com.chengyu.core.model.TradeSupplierExample;
import com.chengyu.core.service.trade.TradeSupplierService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TradeSupplierServiceImpl implements TradeSupplierService {

    @Autowired
    private TradeSupplierMapper tradeSupplierMapper;

    @Override
    public List<TradeSupplier> getTradeSupplierList(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        TradeSupplierExample example = new TradeSupplierExample();
        return tradeSupplierMapper.selectByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addSupplier(TradeSupplier supplier) {
        if (supplier.getId() == null) {
            tradeSupplierMapper.insertSelective(supplier);
        } else {
            tradeSupplierMapper.updateByPrimaryKeySelective(supplier);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteSupplier(List<Integer> idList) {
        TradeSupplierExample example = new TradeSupplierExample();
        example.createCriteria().andIdIn(idList);
        tradeSupplierMapper.deleteByExample(example);
    }
}