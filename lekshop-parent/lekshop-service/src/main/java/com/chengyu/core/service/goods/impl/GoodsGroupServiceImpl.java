package com.chengyu.core.service.goods.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.domain.enums.GoodsEnums;
import com.chengyu.core.domain.form.GoodsPublishGroupForm;
import com.chengyu.core.mapper.PmsGoodsGroupMapper;
import com.chengyu.core.mapper.PmsGoodsMapper;
import com.chengyu.core.model.PmsGoods;
import com.chengyu.core.model.PmsGoodsExample;
import com.chengyu.core.model.PmsGoodsGroup;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.service.goods.GoodsGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsGroupServiceImpl implements GoodsGroupService {

	@Autowired
	private PmsGoodsMapper goodsMapper;
	@Autowired
	private PmsGoodsGroupMapper goodsGroupMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addGroupGoods(UmsShop shop, GoodsPublishGroupForm publishForm) {
		PmsGoods updateGoods = new PmsGoods();
		updateGoods.setId(publishForm.getId());
		updateGoods.setType(GoodsEnums.GoodsType.GROUP_GOODS.getValue());
		updateGoods.setGroupType(publishForm.getGroupType());
		updateGoods.setGroupNum(publishForm.getGroupNum());
		updateGoods.setGroupLimitBuy(publishForm.getGroupLimitBuy());
		updateGoods.setGroupLimitHours(publishForm.getGroupLimitHours());
		updateGoods.setGroupSingleBuy(publishForm.getGroupSingleBuy());
		goodsMapper.updateByPrimaryKeySelective(updateGoods);

		//团购
		if(CollectionUtil.isNotEmpty(publishForm.getGroupList())){
			for(PmsGoodsGroup group : publishForm.getGroupList()){
				group.setGoodsId(publishForm.getId());
				if(group.getId() == null){
					goodsGroupMapper.insertSelective(group);
				}else{
					goodsGroupMapper.updateByPrimaryKeySelective(group);
				}
			}
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteGroup(List<Integer> goodsIdList) {
		PmsGoodsExample example = new PmsGoodsExample();
		example.createCriteria().andIdIn(goodsIdList);

		PmsGoods updateGoods = new PmsGoods();
		updateGoods.setType(GoodsEnums.GoodsType.NORMAL_GOODS.getValue());
		goodsMapper.updateByExampleSelective(updateGoods, example);
	}
}