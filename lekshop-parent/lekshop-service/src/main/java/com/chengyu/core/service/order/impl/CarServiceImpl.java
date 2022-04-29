package com.chengyu.core.service.order.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.BaseMapper;
import com.chengyu.core.mapper.OmsCarMapper;
import com.chengyu.core.mapper.PmsGoodsSkuMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.service.goods.GoodsService;
import com.chengyu.core.service.order.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @title  购物车
 * @author LeGreen
 * @date   2022/4/29
 */
@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private OmsCarMapper carMapper;
	@Autowired
	private PmsGoodsSkuMapper goodsSkuMapper;
	@Autowired
	private BaseMapper baseMapper;
	@Autowired
	private GoodsService goodsService;

	@Override
	@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addCar(UmsMember member, Integer goodsId, String attrSymbolPath, Integer num) throws ServiceException {
		//查询SKU是否存在
		PmsGoodsSkuExample example = new PmsGoodsSkuExample();
		example.createCriteria().andGoodsIdEqualTo(goodsId).andAttrSymbolPathEqualTo(attrSymbolPath);
		List<PmsGoodsSku> skuList = goodsSkuMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(skuList)){
			throw new ServiceException("请选择正确的规格");
		}
		//判断是否已在购物车
		PmsGoodsSku sku = skuList.get(0);
		OmsCarExample carExample = new OmsCarExample();
		carExample.createCriteria().andMemberIdEqualTo(member.getId()).andGoodsIdEqualTo(goodsId).andGoodsSkuIdEqualTo(sku.getId());
		List<OmsCar> carList = carMapper.selectByExample(carExample);
		if(CollectionUtil.isNotEmpty(carList)){
			//在原有的购物车上增加数量
			baseMapper.update("update oms_car set num = num+"+num+" where id = "+carList.get(0).getId());
		}else{
			//新建购物车
			OmsCar newCar = new OmsCar();
			newCar.setMemberId(member.getId());
			newCar.setMemberName(member.getCode());
			PmsGoods goods = goodsService.getGoods(goodsId);
			newCar.setShopId(goods.getShopId());
			newCar.setShopName(goods.getShopName());
			newCar.setGoodsId(goods.getId());
			newCar.setGoodsSkuId(sku.getId());
			newCar.setGoodsName(goods.getTitle());
			newCar.setGoodsMainImg(goods.getMainImg());
			newCar.setGoodsParamName(sku.getAttrSymbolName());
			newCar.setBuyPrice(sku.getPrice());
			newCar.setNum(num);
			newCar.setAddTime(DateUtil.date());
			newCar.setUpdTime(newCar.getAddTime());
			carMapper.insert(newCar);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addCarNum(UmsMember member, Integer carId, Integer addNum) {
		if(addNum >= 0){
			baseMapper.update("update oms_car set num = num+"+ Math.abs(addNum)+" where id = "+carId);
		}else{
			OmsCar car = carMapper.selectByPrimaryKey(carId);
			if(car.getNum() <= Math.abs(addNum)){
				carMapper.deleteByPrimaryKey(carId);
			}else{
				baseMapper.update("update oms_car set num = num-"+ Math.abs(addNum)+" where id = "+carId);
			}
		}
	}

	@Override
	public Map<Integer, List<OmsCar>> getCarList(Integer memberId) {
		OmsCarExample carExample = new OmsCarExample();
		carExample.setOrderByClause("add_time desc");
		carExample.createCriteria().andMemberIdEqualTo(memberId);
		List<OmsCar> carList = carMapper.selectByExample(carExample);
		if(CollectionUtil.isEmpty(carList)){
			return null;
		}
		return carList.stream()
				.collect(Collectors.groupingBy(OmsCar::getShopId));
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void clearCarByMember(UmsMember member) {
		OmsCarExample example = new OmsCarExample();
		example.createCriteria().andMemberIdEqualTo(member.getId());
		carMapper.deleteByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteCar(UmsMember member, List<Integer> carIdList) {
		if(CollectionUtil.isNotEmpty(carIdList)){
			OmsCarExample example = new OmsCarExample();
			example.createCriteria().andMemberIdEqualTo(member.getId()).andIdIn(carIdList);
			carMapper.deleteByExample(example);
		}
	}

	@Override
	public Long countCarNum(Integer memberId) {
		OmsCarExample example = new OmsCarExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		return carMapper.countByExample(example);
	}
}