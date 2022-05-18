package com.chengyu.core.service.member.impl;

import cn.hutool.core.date.DateUtil;
import com.chengyu.core.mapper.BaseMapper;
import com.chengyu.core.mapper.PmsGoodsMapper;
import com.chengyu.core.mapper.UmsMemberCollectionGoodsMapper;
import com.chengyu.core.model.PmsGoods;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberCollectionGoods;
import com.chengyu.core.model.UmsMemberCollectionGoodsExample;
import com.chengyu.core.service.member.MemberCollectGoodsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @title  商品收藏
 * @author LeGreen
 * @date   2022/4/28
 */
@Service
public class MemberCollectGoodsServiceImpl implements MemberCollectGoodsService {
	
	@Autowired
	private UmsMemberCollectionGoodsMapper memberCollectionGoodsMapper;
	@Autowired
	private PmsGoodsMapper goodsMapper;
	@Autowired
	private BaseMapper baseMapper;

	@Override
	public List<UmsMemberCollectionGoods> getMemberCollectionGoodsList(Integer memberId, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		UmsMemberCollectionGoodsExample example = new UmsMemberCollectionGoodsExample();
		example.setOrderByClause("add_time desc");
		if(memberId != null){
			example.createCriteria().andMemberIdEqualTo(memberId);
		}
		return memberCollectionGoodsMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void collectOrNoGoods(UmsMember member, Integer goodsId) {
		if(this.isCollectGoods(member, goodsId)){
			//取消收藏
			UmsMemberCollectionGoodsExample example = new UmsMemberCollectionGoodsExample();
			example.createCriteria().andMemberIdEqualTo(member.getId()).andGoodsIdEqualTo(goodsId);
			memberCollectionGoodsMapper.deleteByExample(example);

			baseMapper.update("update pms_goods set collection_num = collection_num-1 where id = "+goodsId);
		}else{
			//收藏
			UmsMemberCollectionGoods collectionGoods = new UmsMemberCollectionGoods();
			collectionGoods.setMemberId(member.getId());
			collectionGoods.setMemberName(member.getNickname());
			collectionGoods.setHeadImg(member.getHeadImg());

			PmsGoods goods = goodsMapper.selectByPrimaryKey(goodsId);
			collectionGoods.setShopId(goods.getShopId());
			collectionGoods.setShopName(goods.getShopName());
			collectionGoods.setGoodsId(goods.getId());
			collectionGoods.setGoodsName(goods.getTitle());
			collectionGoods.setGoodsMainImg(goods.getMainImg());
			collectionGoods.setPrice(goods.getPrice());
			collectionGoods.setAddTime(DateUtil.date());
			collectionGoods.setUpdTime(collectionGoods.getAddTime());
			memberCollectionGoodsMapper.insertSelective(collectionGoods);

			baseMapper.update("update pms_goods set collection_num = collection_num+1 where id = "+goodsId);
		}
	}

	@Override
	public boolean isCollectGoods(UmsMember member, Integer goodsId) {
		if(member == null){
			return false;
		}
		UmsMemberCollectionGoodsExample example = new UmsMemberCollectionGoodsExample();
		example.createCriteria().andMemberIdEqualTo(member.getId()).andGoodsIdEqualTo(goodsId);
		return memberCollectionGoodsMapper.countByExample(example) > 0;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteCollectionGoods(Integer collectGoodsId) {
		memberCollectionGoodsMapper.deleteByPrimaryKey(collectGoodsId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteCollectionByGoodsId(Integer goodsId) {
		UmsMemberCollectionGoodsExample example = new UmsMemberCollectionGoodsExample();
		example.createCriteria().andGoodsIdEqualTo(goodsId);
		memberCollectionGoodsMapper.deleteByExample(example);
	}
}