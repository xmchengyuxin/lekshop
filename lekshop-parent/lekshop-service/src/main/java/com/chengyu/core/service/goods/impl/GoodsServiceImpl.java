package com.chengyu.core.service.goods.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.GoodsEnums;
import com.chengyu.core.domain.form.GoodsPublishForm;
import com.chengyu.core.domain.form.GoodsSearchForm;
import com.chengyu.core.domain.result.GoodsAttrKeyResult;
import com.chengyu.core.domain.result.GoodsResult;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		if(StringUtils.isNotBlank(form.getTitle())){
			criteria.andTitleLike("%"+form.getTitle()+"%");
		}
		if(form.getStatus() != null){
			criteria.andStatusEqualTo(form.getStatus());
		}
		if(form.getType() != null){
			criteria.andTypeEqualTo(form.getType());
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
		goods.setCateName(goodsCateService.getGoodsCateName(CollectionUtil.newArrayList(goods.getCatePid(), goods.getCateTid(), goods.getCateId())));

		String[] shopCateIdList = publishForm.getShopCateIds().split(CommonConstant.DH_REGEX);
		goods.setShopCateId(Integer.parseInt(shopCateIdList[shopCateIdList.length -1]));
		UmsShopCate shopCate = shopCateService.getShopCateById(goods.getShopCateId());
		goods.setShopCateName(shopCate.getName());
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
		PmsGoodsAttrKeyExample keyExample = new PmsGoodsAttrKeyExample();
		keyExample.setOrderByClause("id asc");
		keyExample.createCriteria().andGoodsIdEqualTo(goodsId);
		List<PmsGoodsAttrKey> keyList = goodsAttrKeyMapper.selectByExample(keyExample);

		List<GoodsAttrKeyResult> keyResultList = new ArrayList<>();
		for(PmsGoodsAttrKey attrKey : keyList){
			PmsGoodsAttrValExample valExample = new PmsGoodsAttrValExample();
			valExample.setOrderByClause("id asc");
			valExample.createCriteria().andAttrKeyIdEqualTo(attrKey.getId());
			List<PmsGoodsAttrVal> valList = goodsAttrValMapper.selectByExample(valExample);

			GoodsAttrKeyResult keyResult = new GoodsAttrKeyResult();
			keyResult.setAttrKey(attrKey.getName());
			keyResult.setAttrValueList(JSONArray.toJSONString(valList.stream().map(PmsGoodsAttrVal::getValue).collect(Collectors.toList())));
			keyResultList.add(keyResult);
		}
		result.setAttrKeyList(JSONArray.toJSONString(keyResultList));

		PmsGoodsSkuExample skuExample = new PmsGoodsSkuExample();
		skuExample.setOrderByClause("id asc");
		skuExample.createCriteria().andGoodsIdEqualTo(goodsId);
		List<PmsGoodsSku> skuList = goodsSkuMapper.selectByExample(skuExample);
		List<List<String>> skuListArray = new ArrayList<>();
		for(PmsGoodsSku sku : skuList){
			List<String> skuArray = new ArrayList<>();
			for(String val : sku.getAttrSymbolName().split(CommonConstant.SLASH_REGEX)){
				skuArray.add(val);
			}
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
}