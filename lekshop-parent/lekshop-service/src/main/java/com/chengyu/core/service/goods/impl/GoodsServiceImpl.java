package com.chengyu.core.service.goods.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.GoodsEnums;
import com.chengyu.core.domain.form.GoodsImportForm;
import com.chengyu.core.domain.form.GoodsPublishForm;
import com.chengyu.core.domain.form.GoodsSearchForm;
import com.chengyu.core.domain.result.*;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.*;
import com.chengyu.core.model.*;
import com.chengyu.core.service.goods.GoodsCateService;
import com.chengyu.core.service.goods.GoodsService;
import com.chengyu.core.service.shop.ShopCateService;
import com.chengyu.core.util.ValidateUtil;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @title  商品管理
 * @author LeGreen
 * @date   2022/4/28
 */
@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private PmsGoodsMapper goodsMapper;
	@Autowired
	private PmsGoodsSkuMapper goodsSkuMapper;
	@Autowired
	private BaseMapper baseMapper;
	@Autowired
	private PmsGoodsGroupMapper goodsGroupMapper;
	@Autowired
	private GoodsCateService goodsCateService;
	@Autowired
	private ShopCateService shopCateService;
	@Autowired
	private PmsGoodsAttrKeyMapper goodsAttrKeyMapper;
	@Autowired
	private PmsGoodsAttrValMapper goodsAttrValMapper;
	@Autowired
	private CustomGoodsMapper customGoodsMapper;
	@Autowired
	private TradeStorageStockMapper tradeStorageStockMapper;

	@Override
	public List<PmsGoods> getGoodsList(GoodsSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		PmsGoodsExample example = new PmsGoodsExample();
		if(StringUtils.isNotBlank(form.getSort())){
			example.setOrderByClause(form.getSort());
		}else{
			example.setOrderByClause("sort asc, weight desc, id desc");
		}
		PmsGoodsExample.Criteria criteria = example.createCriteria();
		criteria.andStatusNotEqualTo(GoodsEnums.GoodsStatus.DEL.getValue());
		if(form.getQueryPlatformGoods()) {
			criteria.andShopIdEqualTo(0);
		} else {
			criteria.andShopIdNotEqualTo(0);
		}
		if(form.getShopId() != null){
			criteria.andShopIdEqualTo(form.getShopId());
		}
		if(form.getCateId() != null){
			criteria.andCateIdEqualTo(form.getCateId());
		}
		if(form.getCatePid() != null){
			criteria.andCatePidEqualTo(form.getCatePid());
		}
		if(form.getCateTid() != null){
			criteria.andCateTidEqualTo(form.getCateTid());
		}
		if(form.getShopCateId() != null){
			criteria.andShopCateIdEqualTo(form.getShopCateId());
		}
		if(form.getShopCatePid() != null){
			criteria.andShopCatePidEqualTo(form.getShopCatePid());
		}
		if(form.getShopCateTid() != null){
			criteria.andShopCateTidEqualTo(form.getShopCateTid());
		}
		if(StringUtils.isNotBlank(form.getTitle())){
			criteria.andTitleLike("%"+form.getTitle()+"%");
		}
		if(form.getStatus() != null){
			criteria.andStatusEqualTo(form.getStatus());
		}
		if(form.getType() != null){
			criteria.andTypeEqualTo(form.getType());
		}
		if(CollectionUtil.isNotEmpty(form.getCatePidList())){
			criteria.andCatePidIn(form.getCatePidList());
		}
		if(form.getMinAmount() != null){
			criteria.andPriceGreaterThanOrEqualTo(form.getMinAmount());
		}
		if(form.getMaxAmount() != null){
			criteria.andPriceLessThanOrEqualTo(form.getMaxAmount());
		}
		return goodsMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void publishGoods(UmsShop shop, GoodsPublishForm publishForm) throws ServiceException {
		PmsGoods goods = new PmsGoods();
		BeanUtil.copyProperties(publishForm, goods);
		Integer tempGoodsId = goods.getId();
		goods.setShopId(shop.getId());
		goods.setShopName(shop.getName());
		String[] cateIdList = publishForm.getGoodsCateIds().split(CommonConstant.DH_REGEX);
		goods.setCatePid(Integer.parseInt(cateIdList[0]));
		if(cateIdList.length >=2 ){
			goods.setCateTid(Integer.parseInt(cateIdList[1]));
		}
		if(cateIdList.length >=3){
			goods.setCateId(Integer.parseInt(cateIdList[2]));
		}
		goods.setCateIds(publishForm.getGoodsCateIds());
		goods.setCateName(goodsCateService.getGoodsCateName(CollectionUtil.newArrayList(goods.getCatePid(), goods.getCateTid(), goods.getCateId())));

		//商品库商品不需要此字段
		if(shop.getId() != 0) {
			String[] shopCateIdList = publishForm.getShopCateIds().split(CommonConstant.DH_REGEX);
			goods.setShopCatePid(Integer.parseInt(shopCateIdList[0]));
			if(shopCateIdList.length >=2 ){
				goods.setShopCateTid(Integer.parseInt(shopCateIdList[1]));
			}
			if(shopCateIdList.length >=3){
				goods.setShopCateId(Integer.parseInt(shopCateIdList[2]));
			}
			goods.setShopCateIds(publishForm.getShopCateIds());
			goods.setShopCateName(shopCateService.getGoodsCateName(CollectionUtil.newArrayList(goods.getShopCatePid(), goods.getShopCateTid(), goods.getShopCateId())));
		}
		goods.setSort(9999);
		goods.setWeight(0);
		goods.setUpdTime(DateUtil.date());
		if(goods.getId() == null){
			goods.setAddTime(goods.getUpdTime());
			goodsMapper.insertSelective(goods);
		}else{
			goodsMapper.updateByPrimaryKeySelective(goods);
		}

		//团购
		if(CollectionUtil.isNotEmpty(publishForm.getGroupList())){
			for(PmsGoodsGroup group : publishForm.getGroupList()){
				group.setGoodsId(goods.getId());
				if(group.getId() == null){
					goodsGroupMapper.insertSelective(group);
				}else{
					goodsGroupMapper.updateByPrimaryKeySelective(group);
				}
			}
		}

		//SKU
		if(StringUtils.isBlank(publishForm.getAttrKeyList()) || StringUtils.isBlank(publishForm.getSkuList())){
			throw new ServiceException("请填写完整的产品属性");
		}
		List<Integer> extraDelKeyId = new ArrayList<>();
		List<GoodsAttrKeyResult> attrKeyList = JSONArray.parseArray(publishForm.getAttrKeyList(), GoodsAttrKeyResult.class);
		Map<String,Integer> tempMap = new HashMap<>();
		int keyIndex = 0;
		for(GoodsAttrKeyResult attrKey : attrKeyList){
			//KEY
			if(StringUtils.isBlank(attrKey.getAttrKey()) || StringUtils.isBlank(attrKey.getAttrValueList())){
				throw new ServiceException("请填写完整的产品属性");
			}
			PmsGoodsAttrKey goodsAttrKey = this.getGoodsAttrKey(tempGoodsId, attrKey.getAttrKey());
			if(goodsAttrKey == null){
				goodsAttrKey = new PmsGoodsAttrKey();
			}
			Integer tempKeyId = goodsAttrKey.getId();
			goodsAttrKey.setGoodsId(goods.getId());
			goodsAttrKey.setName(attrKey.getAttrKey());
			if(goodsAttrKey.getId() == null){
				goodsAttrKeyMapper.insertSelective(goodsAttrKey);
			}else{
				goodsAttrKeyMapper.updateByPrimaryKeySelective(goodsAttrKey);
			}
			//VALUE
			List<String> valueList = JSONArray.parseArray(attrKey.getAttrValueList(), String.class);
			for(String value : valueList){
				if(StringUtils.isBlank(value)){
					throw new ServiceException("请填写完整的产品属性");
				}
				PmsGoodsAttrVal goodsAttrVal = this.getGoodsAttrValue(tempGoodsId, tempKeyId, value);
				if(goodsAttrVal == null){
					goodsAttrVal = new PmsGoodsAttrVal();
				}
				goodsAttrVal.setGoodsId(goods.getId());
				goodsAttrVal.setAttrKeyId(goodsAttrKey.getId());
				goodsAttrVal.setAttrKeyName(goodsAttrKey.getName());
				goodsAttrVal.setValue(value);
				if(goodsAttrVal.getId() == null){
					goodsAttrValMapper.insertSelective(goodsAttrVal);
				}else{
					goodsAttrValMapper.updateByPrimaryKeySelective(goodsAttrVal);
				}
				tempMap.put(keyIndex+"-"+value, goodsAttrVal.getId());
			}
			keyIndex++;
			extraDelKeyId.add(goodsAttrKey.getId());
		}

		List<String[]> skuList = JSONArray.parseArray(publishForm.getSkuList(), String[].class);
		if(CollectionUtil.isEmpty(skuList)){
			throw new ServiceException("请填写完整的产品属性");
		}
		List<String> extraDelSku = new ArrayList<>();
		for(String[] skuArr : skuList){
			int length = skuArr.length;
			ValidateUtil.validateNull(new Object[]{skuArr[length-3], skuArr[length-4], skuArr[length-5], skuArr[length-6]},
					"重量不能为空", "库存不能为空", "现价不能为空", "原价不能为空");

			//attrKeyList: [{"attrKey":"颜色","attrValue":"","attrValueList":["黑","白"]},{"attrKey":"尺寸","attrValue":"","attrValueList":["大","小"]}]
			//skuList: [["黑","大","","222","999","888","",""],["黑","小","","222","999","888","",""],["白","大","","222","999","888","",""],["白","小","","222","999","888","",""]]
			StringBuilder attrSymbolPath = new StringBuilder();
			StringBuilder attrSymbolName = new StringBuilder();
			for(int i = 0; i < length-6; i++){
				attrSymbolPath.append("/");
				attrSymbolPath.append(tempMap.get(i+"-"+skuArr[i]));

				attrSymbolName.append("/");
				attrSymbolName.append(skuArr[i]);
			}
			PmsGoodsSku goodsSku = this.getGoodsSku(tempGoodsId, attrSymbolPath.substring(1));
			if(goodsSku == null){
				goodsSku = new PmsGoodsSku();
			}
			goodsSku.setGoodsId(goods.getId());
			goodsSku.setAttrSymbolPath(attrSymbolPath.substring(1));
			goodsSku.setAttrSymbolName(attrSymbolName.substring(1));
			goodsSku.setOriPrice(new BigDecimal(skuArr[length-6]));
			goodsSku.setPrice(new BigDecimal(skuArr[length-5]));
			goodsSku.setStock(Integer.parseInt(skuArr[length-4]));
			goodsSku.setWeight(new BigDecimal(skuArr[length-3]));
			goodsSku.setSkuNo(skuArr[length-2]);
			goodsSku.setImg(skuArr[length-1]);
			if(StringUtils.isBlank(goodsSku.getImg())){
				goodsSku.setImg(goods.getMainImg());
			}
			if(StringUtils.isBlank(goodsSku.getSkuNo())){
				goodsSku.setSkuNo("LEK"+StringUtils.genOrderNo(0));
			}
			if(goodsSku.getId() == null){
				goodsSkuMapper.insertSelective(goodsSku);
			}else{
				goodsSkuMapper.updateByPrimaryKeySelective(goodsSku);
			}
			extraDelSku.add(goodsSku.getAttrSymbolPath());
		}
		PmsGoods updateGoods = new PmsGoods();
		updateGoods.setId(goods.getId());
		updateGoods.setPrice(new BigDecimal(skuList.get(0)[skuList.get(0).length-5]));
		goodsMapper.updateByPrimaryKeySelective(updateGoods);

		//删除多余的SKU
		PmsGoodsAttrKeyExample delKeyExample = new PmsGoodsAttrKeyExample();
		delKeyExample.createCriteria().andGoodsIdEqualTo(goods.getId()).andIdNotIn(extraDelKeyId);
		goodsAttrKeyMapper.deleteByExample(delKeyExample);

		PmsGoodsAttrValExample delValExample = new PmsGoodsAttrValExample();
		delValExample.createCriteria().andGoodsIdEqualTo(goods.getId()).andAttrKeyIdNotIn(extraDelKeyId);
		goodsAttrValMapper.deleteByExample(delValExample);

		PmsGoodsSkuExample skuExample = new PmsGoodsSkuExample();
		skuExample.createCriteria().andGoodsIdEqualTo(goods.getId()).andAttrSymbolPathNotIn(extraDelSku);
		goodsSkuMapper.deleteByExample(skuExample);
	}

	private PmsGoodsAttrKey getGoodsAttrKey(Integer goodsId, String attrKey){
		if(goodsId == null){
			return null;
		}
		PmsGoodsAttrKeyExample example = new PmsGoodsAttrKeyExample();
		example.createCriteria().andGoodsIdEqualTo(goodsId).andNameEqualTo(attrKey);
		List<PmsGoodsAttrKey> list = goodsAttrKeyMapper.selectByExample(example);
		return CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
	}

	private PmsGoodsAttrVal getGoodsAttrValue(Integer goodsId, Integer attrId, String attrValue){
		if(goodsId == null || attrId == null){
			return null;
		}
		PmsGoodsAttrValExample example = new PmsGoodsAttrValExample();
		example.createCriteria().andGoodsIdEqualTo(goodsId).andAttrKeyIdEqualTo(attrId).andValueEqualTo(attrValue);
		List<PmsGoodsAttrVal> list = goodsAttrValMapper.selectByExample(example);
		return CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
	}

	private PmsGoodsSku getGoodsSku(Integer goodsId, String attrSymbolPath){
		if(goodsId == null){
			return null;
		}
		PmsGoodsSkuExample example = new PmsGoodsSkuExample();
		example.createCriteria().andGoodsIdEqualTo(goodsId).andAttrSymbolPathEqualTo(attrSymbolPath);
		List<PmsGoodsSku> list = goodsSkuMapper.selectByExample(example);
		return CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void shangjiaGoods(List<Integer> goodsIdList) {
		PmsGoodsExample example = new PmsGoodsExample();
		example.createCriteria().andIdIn(goodsIdList);

		PmsGoods updateGoods = new PmsGoods();
		updateGoods.setStatus(GoodsEnums.GoodsStatus.SELL.getValue());
		goodsMapper.updateByExampleSelective(updateGoods, example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void xiajiaGoods(List<Integer> goodsIdList) {
		PmsGoodsExample example = new PmsGoodsExample();
		example.createCriteria().andIdIn(goodsIdList);

		PmsGoods updateGoods = new PmsGoods();
		updateGoods.setStatus(GoodsEnums.GoodsStatus.IN_WAREHOUSE.getValue());
		goodsMapper.updateByExampleSelective(updateGoods, example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteGoods(List<Integer> goodsIdList) {
		PmsGoodsExample example = new PmsGoodsExample();
		example.createCriteria().andIdIn(goodsIdList);

		PmsGoods updateGoods = new PmsGoods();
		updateGoods.setStatus(GoodsEnums.GoodsStatus.DEL.getValue());
		goodsMapper.updateByExampleSelective(updateGoods, example);
	}

	@Override
	public PmsGoods getGoods(Integer goodsId) {
		PmsGoodsExample example = new PmsGoodsExample();
		example.createCriteria().andIdEqualTo(goodsId).andStatusNotEqualTo(GoodsEnums.GoodsStatus.DEL.getValue());
		List<PmsGoods> goodsList = goodsMapper.selectByExampleWithBLOBs(example);
		return CollectionUtil.isNotEmpty(goodsList) ? goodsList.get(0) : null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void reduceStock(Integer skuId, Integer num) {
		baseMapper.update("update pms_goods_sku set stock = stock-"+ num +" where id = "+skuId);
		//如果库存小于0， 则更新为0
		PmsGoodsSkuExample example = new PmsGoodsSkuExample();
		example.createCriteria().andIdEqualTo(skuId).andStockLessThan(0);
		PmsGoodsSku updateSku = new PmsGoodsSku();
		updateSku.setStock(0);
		goodsSkuMapper.updateByExampleSelective(updateSku, example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addStock(Integer skuId, Integer num) {
		baseMapper.update("update pms_goods_sku set stock = stock+"+ num +" where id = "+skuId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateGoods(PmsGoods goods) {
		goods.setUpdTime(DateUtil.date());
		goodsMapper.updateByPrimaryKeyWithBLOBs(goods);
	}

	@Override
	public GoodsResult getGoodsResult(Integer goodsId) {
		GoodsResult result = new GoodsResult();
		result.setGoods(this.getGoods(goodsId));

		//拼团规则
		PmsGoodsGroupExample groupExample = new PmsGoodsGroupExample();
		groupExample.setOrderByClause("num asc");
		groupExample.createCriteria().andGoodsIdEqualTo(goodsId);
		result.setGoodsGroupList(goodsGroupMapper.selectByExample(groupExample));

		//Sku组合
		//attrKeyList: [{"attrKey":"颜色","attrValue":"","attrValueList":["黑","白"]},{"attrKey":"尺寸","attrValue":"","attrValueList":["大","小"]}]
		//skuList: [["黑","大","","222","999","888","",""],["黑","小","","222","999","888","",""],["白","大","","222","999","888","",""],["白","小","","222","999","888","",""]]
		List<PmsGoodsAttrKey> keyList = this.getGoodsAttrKeyList(goodsId);

		List<GoodsAttrKeyResult> keyResultList = new ArrayList<>();
		for(PmsGoodsAttrKey attrKey : keyList){
			List<PmsGoodsAttrVal> valList = this.getGoodsAttrValueList(attrKey.getId());

			GoodsAttrKeyResult keyResult = new GoodsAttrKeyResult();
			keyResult.setAttrKey(attrKey.getName());
			keyResult.setAttrValueList(JSONArray.toJSONString(valList.stream().map(PmsGoodsAttrVal::getValue).collect(Collectors.toList())));
			keyResultList.add(keyResult);
		}
		result.setAttrKeyList(JSONArray.toJSONString(keyResultList));

		List<PmsGoodsSku> skuList = this.getPmsGoodsSkuList(goodsId);
		List<List<String>> skuListArray = new ArrayList<>();
		for(PmsGoodsSku sku : skuList){
			List<String> skuArray = new ArrayList<>(Arrays.asList(sku.getAttrSymbolName().split(CommonConstant.SLASH_REGEX)));
			skuArray.add(sku.getOriPrice().toString());
			skuArray.add(sku.getPrice().toString());
			skuArray.add(sku.getStock().toString());
			skuArray.add(sku.getWeight().toString());
			skuArray.add(sku.getSkuNo());
			skuArray.add(sku.getImg());
			skuListArray.add(skuArray);
		}
		result.setSkuList(JSONArray.toJSONString(skuListArray));
		return result;
	}

	@Override
	public GoodsSkuResult getGoodsSkuList(Integer goodsId) {
		GoodsSkuResult result = new GoodsSkuResult();
		List<PmsGoodsAttrKey> keyList = this.getGoodsAttrKeyList(goodsId);

		List<GoodsAttrKeyResult> keyResultList = new ArrayList<>();
		for(PmsGoodsAttrKey attrKey : keyList){
			List<PmsGoodsAttrVal> valList = this.getGoodsAttrValueList(attrKey.getId());

			GoodsAttrKeyResult keyResult = new GoodsAttrKeyResult();
			keyResult.setAttrKey(attrKey.getName());
			keyResult.setValList(valList);
			keyResultList.add(keyResult);
		}
		result.setAttrKeyResultList(keyResultList);

		List<PmsGoodsSku> skuList = this.getPmsGoodsSkuList(goodsId);
		result.setSkuList(skuList);
		return result;
	}

	@Override
	public PmsGoodsSku getGoodsSku(Integer skuId) {
		return goodsSkuMapper.selectByPrimaryKey(skuId);
	}

	@Override
	public List<PmsGoodsGroup> getGoodsGroupList(Integer goodsId) {
		PmsGoodsGroupExample groupExample = new PmsGoodsGroupExample();
		groupExample.setOrderByClause("num asc");
		groupExample.createCriteria().andGoodsIdEqualTo(goodsId);
		return goodsGroupMapper.selectByExample(groupExample);
	}

	@Override
	public PmsGoodsGroup getGoodsGroup(Integer groupId) {
		return goodsGroupMapper.selectByPrimaryKey(groupId);
	}

	@Override
	public List<GoodsStockResult> getGoodsStockList(GoodsSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		return customGoodsMapper.getGoodsStockList(form);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateGoodsStock(List<PmsGoodsSku> stockList) {
		for(PmsGoodsSku sku : stockList) {
			PmsGoodsSku updateSku = new PmsGoodsSku();
			updateSku.setId(sku.getId());
			updateSku.setStock(sku.getStock());
			goodsSkuMapper.updateByPrimaryKeySelective(updateSku);
		}
	}

	@Override
	public void synStock(Integer shopId) {
		//查询仓库所有库存
		TradeStorageStockExample example = new TradeStorageStockExample();
		example.createCriteria().andShopIdEqualTo(shopId);
		List<TradeStorageStock> stockList = tradeStorageStockMapper.selectByExample(example);
		if(CollectionUtil.isNotEmpty(stockList)) {
			for(TradeStorageStock stock : stockList) {
				PmsGoodsSku updateSku = new PmsGoodsSku();
				updateSku.setId(stock.getSkuId());
				updateSku.setStock(stock.getStock());
				goodsSkuMapper.updateByPrimaryKeySelective(updateSku);
			}
		}

	}

	@Override
	public List<GoodsExportResult> getExportList(GoodsSearchForm form, Integer page, Integer pageSize) {
		List<PmsGoods> goodsList = this.getGoodsList(form, page, pageSize);
		if(CollectionUtil.isEmpty(goodsList)) {
			return null;
		}
		List<Integer> goodsIds = goodsList.stream().map(PmsGoods::getId).collect(Collectors.toList());

		PmsGoodsSkuExample example = new PmsGoodsSkuExample();
		example.setOrderByClause("id asc");
		example.createCriteria().andGoodsIdIn(goodsIds);
		List<PmsGoodsSku> skuList = goodsSkuMapper.selectByExample(example);
		Map<Integer, List<PmsGoodsSku>> skuMap = skuList.stream().collect(Collectors.groupingBy(PmsGoodsSku::getGoodsId));

		List<GoodsExportResult> list = new ArrayList<>();
		for(PmsGoods goods : goodsList) {
			GoodsExportResult result = new GoodsExportResult();
			BeanUtil.copyProperties(goods, result);
			result.setSkuList(skuMap.get(goods.getId()));
			list.add(result);
		}
		return list;
	}

	@Override
	public void importGoods(UmsShop shop, Integer freightId, List<GoodsImportForm> goodsList) {
		Map<String, Integer> cateMap = new HashMap<>();
		for(GoodsImportForm form : goodsList) {
			form.setShopId(shop.getId());
			form.setShopName(shop.getName());
			form.setType(GoodsEnums.GoodsType.NORMAL_GOODS.getValue());
			form.setStatus(GoodsEnums.GoodsStatus.SELL.getValue());
			form.setFreightTemplateId(freightId);
			form.setPrice(new BigDecimal(form.getPrices().split(CommonConstant.SLASH_REGEX)[0]));
			String[] cateNames = form.getCateName().split(CommonConstant.SLASH_REGEX);
			List<Integer> cateIds = new ArrayList<>();
			form.setCatePid(this.getCateIdByName(cateMap, cateNames[0], 0));
			cateIds.add(form.getCatePid());
			if(cateNames.length >=2 ){
				form.setCateTid(this.getCateIdByName(cateMap, cateNames[1], form.getCatePid()));
				cateIds.add(form.getCateTid());
			}
			if(cateNames.length >=3){
				form.setCateId(this.getCateIdByName(cateMap, cateNames[2], form.getCateTid()));
				cateIds.add(form.getCateId());
			}
			form.setCateIds(StringUtils.join(cateIds, ","));
			form.setSort(9999);
			form.setWeight(0);
			form.setUpdTime(DateUtil.date());
		}
		customGoodsMapper.insertGoodsList(goodsList);

		for(GoodsImportForm form : goodsList) {
			PmsGoodsAttrKey goodsAttrKey = new PmsGoodsAttrKey();
			goodsAttrKey.setGoodsId(form.getId());
			goodsAttrKey.setName("规格");
			goodsAttrKeyMapper.insertSelective(goodsAttrKey);

			PmsGoodsAttrVal goodsAttrVal = new PmsGoodsAttrVal();
			goodsAttrVal.setGoodsId(form.getId());
			goodsAttrVal.setAttrKeyId(goodsAttrKey.getId());
			goodsAttrVal.setAttrKeyName(goodsAttrKey.getName());
			goodsAttrVal.setValue("默认");
			goodsAttrValMapper.insertSelective(goodsAttrVal);

			PmsGoodsSku sku = new PmsGoodsSku();
			sku.setGoodsId(form.getId());
			sku.setAttrSymbolPath(goodsAttrVal.getId().toString());
			sku.setAttrSymbolName(goodsAttrVal.getValue());
			sku.setPrice(form.getPrice());
			sku.setOriPrice(sku.getPrice());
			sku.setStock(1000);
			sku.setImg(form.getMainImg());
			goodsSkuMapper.insertSelective(sku);
		}
	}


	private Integer getCateIdByName(Map<String, Integer> cateMap, String name, Integer parentId) {
		if(cateMap.get(name+"-"+parentId) != null) {
			return cateMap.get(name+"-"+parentId);
		}else {
			//创建新分类
			Integer cateId = goodsCateService.addGoodsCate(parentId == 0 ? null : parentId, name, 99, null, null);
			cateMap.put(name+"-"+parentId, cateId);
			return cateId;
		}
	}

	private List<PmsGoodsAttrKey> getGoodsAttrKeyList(Integer goodsId){
		PmsGoodsAttrKeyExample keyExample = new PmsGoodsAttrKeyExample();
		keyExample.setOrderByClause("id asc");
		keyExample.createCriteria().andGoodsIdEqualTo(goodsId);
		return goodsAttrKeyMapper.selectByExample(keyExample);
	}

	private List<PmsGoodsAttrVal> getGoodsAttrValueList(Integer attrKeyId){
		PmsGoodsAttrValExample valExample = new PmsGoodsAttrValExample();
		valExample.setOrderByClause("id asc");
		valExample.createCriteria().andAttrKeyIdEqualTo(attrKeyId);
		return goodsAttrValMapper.selectByExample(valExample);
	}

	private List<PmsGoodsSku> getPmsGoodsSkuList(Integer goodsId){
		PmsGoodsSkuExample skuExample = new PmsGoodsSkuExample();
		skuExample.setOrderByClause("id asc");
		skuExample.createCriteria().andGoodsIdEqualTo(goodsId);
		return goodsSkuMapper.selectByExample(skuExample);
	}
}