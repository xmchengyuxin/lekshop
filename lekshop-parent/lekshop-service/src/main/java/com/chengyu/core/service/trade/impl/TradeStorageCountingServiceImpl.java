package com.chengyu.core.service.trade.impl;

import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.form.TradeStockCountingSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.CustomStockCountingMapper;
import com.chengyu.core.mapper.TradeStorageStockCountingDetailMapper;
import com.chengyu.core.mapper.TradeStorageStockCountingMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.service.trade.TradeStorageCountingService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class TradeStorageCountingServiceImpl implements TradeStorageCountingService {

    @Autowired
    private TradeStorageStockCountingMapper storageStockCountingMapper;
    @Autowired
    private TradeStorageStockCountingDetailMapper storageStockCountingDetailMapper;
    @Autowired
    private CustomStockCountingMapper stockCountingCustomerMapper;

    @Override
    public List<TradeStorageStockCounting> getStockCountingList(TradeStockCountingSearchForm form, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        TradeStorageStockCountingExample example = new TradeStorageStockCountingExample();
        example.setOrderByClause("add_time desc");
        TradeStorageStockCountingExample.Criteria criteria = example.createCriteria();
        if(form.getAccountId() != null){
            criteria.andAccountIdEqualTo(form.getAccountId());
        }
        if(form.getStatus() != null){
            criteria.andStatusEqualTo(form.getStatus());
        }
        if(form.getDateFrom() != null && form.getDateTo() != null){
            criteria.andAddTimeBetween(form.getDateFrom(), form.getDateTo());
        }
        return storageStockCountingMapper.selectByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addStockCounting(UmsShopAccount member, List<TradeStorageStockCountingDetail> countingDetailList) throws ServiceException {
        int errorNum = 0;
        for(TradeStorageStockCountingDetail detail : countingDetailList) {
            if(detail.getCountingStock() == null){
                throw new ServiceException("请填写完整的盲盘数量");
            }
            detail.setStatus(Objects.equals(detail.getCountingStock(), detail.getStock()) ? CommonConstant.SUS_INT : CommonConstant.FAIL_INT);
            if(detail.getStatus() == CommonConstant.FAIL_INT) {
                errorNum++;
            }
        }
        TradeStorageStockCounting storageStockCounting = new TradeStorageStockCounting();
        storageStockCounting.setAccountId(member.getId());
        storageStockCounting.setAccountName(member.getRealname());
        storageStockCounting.setDeptId(member.getDeptId());
        storageStockCounting.setDeptName(member.getDeptName());
        storageStockCounting.setTotalNum(countingDetailList.size());
        storageStockCounting.setErrorNum(errorNum);
        storageStockCounting.setRightNum(storageStockCounting.getTotalNum() - storageStockCounting.getErrorNum());
        storageStockCounting.setStatus(storageStockCounting.getErrorNum() > 0 ? CommonConstant.NO_INT : CommonConstant.YES_INT);
        storageStockCountingMapper.insertSelective(storageStockCounting);

        stockCountingCustomerMapper.addCountingDetail(storageStockCounting.getId(), countingDetailList);
    }

    @Override
    public List<TradeStorageStockCountingDetail> getStockCountingDetailList(Integer stockCountingId, Integer status, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        TradeStorageStockCountingDetailExample example = new TradeStorageStockCountingDetailExample();
        TradeStorageStockCountingDetailExample.Criteria criteria = example.createCriteria();
        if(stockCountingId != null) {
            criteria.andCountingIdEqualTo(stockCountingId);
        }
        if(status != null){
            criteria.andStatusEqualTo(status);
        }
        return storageStockCountingDetailMapper.selectByExample(example);
    }
}