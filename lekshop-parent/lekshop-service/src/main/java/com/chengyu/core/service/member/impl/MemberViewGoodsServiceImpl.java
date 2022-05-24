package com.chengyu.core.service.member.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.mapper.PmsGoodsMapper;
import com.chengyu.core.mapper.UmsMemberViewGoodsMapper;
import com.chengyu.core.model.PmsGoods;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberViewGoods;
import com.chengyu.core.model.UmsMemberViewGoodsExample;
import com.chengyu.core.service.member.MemberViewGoodsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @title  浏览商品
 * @author LeGreen
 * @date   2022/4/28
 */
@Service
public class MemberViewGoodsServiceImpl implements MemberViewGoodsService {
	
	@Autowired
	private UmsMemberViewGoodsMapper memberViewGoodsMapper;
	@Autowired
	private PmsGoodsMapper goodsMapper;

	@Override
	public List<UmsMemberViewGoods> getMemberViewGoodsList(Integer memberId, Date date, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		UmsMemberViewGoodsExample example = new UmsMemberViewGoodsExample();
		example.setOrderByClause("add_time desc");
		UmsMemberViewGoodsExample.Criteria criteria = example.createCriteria();
		if(memberId != null){
			criteria.andMemberIdEqualTo(memberId);
		}
		if(date != null){
			criteria.andDateEqualTo(date);
		}
		return memberViewGoodsMapper.selectByExample(example);
	}

	@Override
	public void viewGoods(UmsMember member, Integer goodsId) {
		if(member == null){
			return ;
		}
		UmsMemberViewGoodsExample example = new UmsMemberViewGoodsExample();
		example.setOrderByClause("upd_time desc");
		example.createCriteria().andMemberIdEqualTo(member.getId()).andGoodsIdEqualTo(goodsId).andDateEqualTo(DateUtil.date());
		List<UmsMemberViewGoods> list = memberViewGoodsMapper.selectByExample(example);
		if(CollectionUtil.isNotEmpty(list)){
			//更新浏览时间
			UmsMemberViewGoods updateViewGoods = new UmsMemberViewGoods();
			updateViewGoods.setId(list.get(0).getId());
			updateViewGoods.setUpdTime(DateUtil.date());
			updateViewGoods.setDate(DateUtil.date());
			memberViewGoodsMapper.updateByPrimaryKeySelective(updateViewGoods);
		}else{
			UmsMemberViewGoods viewGoods = new UmsMemberViewGoods();
			viewGoods.setMemberId(member.getId());
			viewGoods.setMemberName(member.getNickname());
			viewGoods.setHeadImg(member.getHeadImg());
			viewGoods.setDate(DateUtil.date());

			PmsGoods goods = goodsMapper.selectByPrimaryKey(goodsId);
			viewGoods.setGoodsId(goods.getId());
			viewGoods.setGoodsName(goods.getTitle());
			viewGoods.setGoodsMainImg(goods.getMainImg());
			viewGoods.setGoodsCateId(goods.getCatePid());
			viewGoods.setPrice(goods.getPrice());
			viewGoods.setAddTime(DateUtil.date());
			viewGoods.setUpdTime(viewGoods.getAddTime());
			memberViewGoodsMapper.insert(viewGoods);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteViewGoods(Integer viewGoodsId) {
		memberViewGoodsMapper.deleteByPrimaryKey(viewGoodsId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteViewGoodsByMemberId(Integer memberId) {
		UmsMemberViewGoodsExample example = new UmsMemberViewGoodsExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		memberViewGoodsMapper.deleteByExample(example);
	}

	@Override
	public List<Integer> getMemberViewGoodsCateIdList(Integer memberId) {
		Date now = DateUtil.date();
		UmsMemberViewGoodsExample example = new UmsMemberViewGoodsExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andDateBetween(DateUtil.offsetDay(now, -90), now);
		List<UmsMemberViewGoods> list = memberViewGoodsMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(list)){
			return null;
		}
		return list.stream().map(UmsMemberViewGoods::getGoodsCateId).distinct().collect(Collectors.toList());
	}

}