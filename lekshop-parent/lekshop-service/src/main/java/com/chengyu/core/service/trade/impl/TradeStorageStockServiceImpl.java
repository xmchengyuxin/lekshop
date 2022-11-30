package com.chengyu.core.service.trade.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.form.StockLogSearchForm;
import com.chengyu.core.domain.form.TradeStockSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.*;
import com.chengyu.core.model.*;
import com.chengyu.core.service.trade.TradeStorageStockService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradeStorageStockServiceImpl implements TradeStorageStockService {

    @Autowired
    private TradeStorageStockMapper tradeStorageStockMapper;
    @Autowired
    private TradeStorageMapper tradeStorageMapper;
    @Autowired
    private PmsGoodsMapper goodsMapper;
    @Autowired
    private PmsGoodsSkuMapper goodsSkuMapper;
    @Autowired
    private BaseMapper baseMapper;
    @Autowired
    private TradeStorageStockLogMapper tradeStorageStockLogMapper;

    @Override
    public List<TradeStorageStock> getStockList(TradeStockSearchForm form, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        TradeStorageStockExample example = new TradeStorageStockExample();
        TradeStorageStockExample.Criteria criteria = example.createCriteria();
        if(form.getStorageId() != null){
            criteria.andStorageIdEqualTo(form.getStorageId());
        }
        if(form.getGoodsId() != null){
            criteria.andGoodsIdEqualTo(form.getGoodsId());
        }
        if(form.getCateId() != null){
            criteria.andCateIdEqualTo(form.getCateId());
        }
        if(StringUtils.isNotBlank(form.getCateName())){
            criteria.andCateNameEqualTo(form.getCateName());
        }
        if(StringUtils.isNotBlank(form.getGoodsName())){
            criteria.andGoodsNameLike("%"+form.getGoodsName()+"%");
        }
        return tradeStorageStockMapper.selectByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void inStock(String orderNo, Integer storageId, String spaceNo, String spaceName, Integer goodsSkuId, Integer num, String remark) {
        TradeStorageStock storageStock = this.getStorageStock(storageId, goodsSkuId);
        if(storageStock == null){
            //初始化库存
            storageStock = new TradeStorageStock();
            TradeStorage storage = tradeStorageMapper.selectByPrimaryKey(storageId);
            storageStock.setStorageId(storageId);
            storageStock.setStorageName(storage.getName());
            storageStock.setSpaceName(spaceName);
            storageStock.setSpaceNo(spaceNo);
            PmsGoodsSku sku = goodsSkuMapper.selectByPrimaryKey(goodsSkuId);
            PmsGoods goods = goodsMapper.selectByPrimaryKey(sku.getGoodsId());
            storageStock.setCateId(goods.getCateId());
            storageStock.setCateName(goods.getCateName());
            storageStock.setGoodsId(goods.getId());
            storageStock.setGoodsName(goods.getTitle());
            storageStock.setSkuId(sku.getId());
            storageStock.setSkuName(sku.getAttrSymbolName());
            storageStock.setStock(Math.abs(num));
            tradeStorageStockMapper.insertSelective(storageStock);
        }else{
            baseMapper.update("update trade_storage_stock set stock = stock+"+Math.abs(num)+" where id ="+storageStock.getId());
        }
        storageStock.setStock(0);
        this.addStockLog(orderNo, CommonConstant.IN, Math.abs(num), storageStock, remark);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void outStock(String orderNo, Integer storageId, Integer goodsSkuId, Integer num, String remark) throws ServiceException {
        TradeStorageStock storageStock = this.getStorageStock(storageId, goodsSkuId);
        if(storageStock == null || storageStock.getStock() < Math.abs(num)) {
            throw new ServiceException("库存不足");
        }
        baseMapper.update("update trade_storage_stock set stock = stock-"+Math.abs(num)+" where id ="+storageStock.getId());

        this.addStockLog(orderNo, CommonConstant.OUT, num, storageStock, remark);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateWarnStock(List<TradeStorageStock> stockList) {
        for(TradeStorageStock storageStock : stockList) {
            TradeStorageStock updateStock = new TradeStorageStock();
            updateStock.setId(storageStock.getId());
            updateStock.setWarnStock(storageStock.getWarnStock());
            updateStock.setMaxStock(storageStock.getMaxStock());
            tradeStorageStockMapper.updateByPrimaryKeySelective(updateStock);
        }
    }

    @Override
    public List<TradeStorageStockLog> getStockLogList(StockLogSearchForm form, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        TradeStorageStockLogExample example = new TradeStorageStockLogExample();
        example.setOrderByClause("id desc");
        TradeStorageStockLogExample.Criteria criteria = example.createCriteria();
        if(form.getInOut() != null){
            criteria.andInOutEqualTo(form.getInOut());
        }
        if(StringUtils.isNotBlank(form.getOrderNo())){
            criteria.andOrderNoLike("%"+form.getOrderNo()+"%");
        }
        if(StringUtils.isNotBlank(form.getGoodsName())){
            criteria.andGoodsNameLike("%"+form.getGoodsName()+"%");
        }
        if(StringUtils.isNotBlank(form.getSpaceName())){
            criteria.andSpaceNameEqualTo(form.getSpaceName());
        }
        if(form.getStorageId() != null){
            criteria.andStorageIdEqualTo(form.getStorageId());
        }
        if(form.getType() != null){
            criteria.andTypeEqualTo(form.getType());
        }
        if(form.getGoodsId() != null){
            criteria.andGoodsIdEqualTo(form.getGoodsId());
        }
        if(form.getSkuId() != null){
            criteria.andSkuIdEqualTo(form.getSkuId());
        }
        if(CollectionUtil.isNotEmpty(form.getTypeList())){
            criteria.andTypeIn(form.getTypeList());
        }
        if(StringUtils.isNotBlank(form.getTypes())){
            List<String> types = Arrays.asList(form.getTypes().split(CommonConstant.DH_REGEX));
            criteria.andTypeIn(types.stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        if(form.getDateFrom() != null && form.getDateTo() != null){
            criteria.andAddTimeBetween(form.getDateFrom(), form.getDateTo());
        }
        return tradeStorageStockLogMapper.selectByExample(example);
    }

    private TradeStorageStock getStorageStock(Integer storageId, Integer goodsSkuId){
        TradeStorageStockExample example = new TradeStorageStockExample();
        TradeStorageStockExample.Criteria criteria = example.createCriteria();
        criteria.andSkuIdEqualTo(goodsSkuId);
        if(storageId != null) {
            criteria.andStorageIdEqualTo(storageId);
        }
        List<TradeStorageStock> list = tradeStorageStockMapper.selectByExample(example);
        return CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
    }

    private void addStockLog(String orderNo, Integer inOut, Integer num, TradeStorageStock stock, String remark){
        TradeStorageStockLog log = new TradeStorageStockLog();
        log.setInOut(inOut);
        log.setOrderNo(orderNo);
        log.setStorageId(stock.getStorageId());
        log.setStorageName(stock.getStorageName());
        log.setSpaceName(stock.getSpaceName());
        log.setSpaceNo(stock.getSpaceNo());
        log.setGoodsId(stock.getGoodsId());
        log.setGoodsName(stock.getGoodsName());
        log.setSkuId(stock.getSkuId());
        log.setSkuName(stock.getSkuName());
        log.setNum(num);
        log.setNumBefore(stock.getStock());
        log.setNumAfter(stock.getStock()+(num*inOut));
        log.setRemark(remark);
        log.setShopId(stock.getShopId());
        log.setShopName(stock.getShopName());
        tradeStorageStockLogMapper.insertSelective(log);
    }
}