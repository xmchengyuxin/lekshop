package com.chengyu.core.service.member.impl;

import cn.hutool.core.date.DateUtil;
import com.chengyu.core.mapper.BaseMapper;
import com.chengyu.core.mapper.UmsMemberCollectionShopMapper;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberCollectionShop;
import com.chengyu.core.model.UmsMemberCollectionShopExample;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.service.member.MemberCollectShopService;
import com.chengyu.core.service.shop.ShopService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @title  店铺收藏
 * @author LeGreen
 * @date   2022/4/28
 */
@Service
public class MemberCollectShopServiceImpl implements MemberCollectShopService {
	
	@Autowired
	private UmsMemberCollectionShopMapper memberCollectionShopMapper;
	@Autowired
	private ShopService shopService;
	@Autowired
	private BaseMapper baseMapper;

	@Override
	public List<UmsMemberCollectionShop> getMemberCollectionShopList(Integer memberId, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		UmsMemberCollectionShopExample example = new UmsMemberCollectionShopExample();
		example.setOrderByClause("add_time desc");
		if(memberId != null){
			example.createCriteria().andMemberIdEqualTo(memberId);
		}
		return memberCollectionShopMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void collectOrNoShop(UmsMember member, Integer shopId) {
		if(this.isCollectShop(member, shopId)){
			//取消收藏
			UmsMemberCollectionShopExample example = new UmsMemberCollectionShopExample();
			example.createCriteria().andMemberIdEqualTo(member.getId()).andShopIdEqualTo(shopId);
			memberCollectionShopMapper.deleteByExample(example);

			baseMapper.update("update ums_shop set like_num = like_num-1 where id = "+shopId);
		}else{
			//收藏
			UmsMemberCollectionShop collectionShop = new UmsMemberCollectionShop();
			collectionShop.setMemberId(member.getId());
			collectionShop.setMemberName(member.getNickname());
			collectionShop.setHeadImg(member.getHeadImg());

			UmsShop shop = shopService.getShopById(shopId);
			collectionShop.setShopId(shop.getId());
			collectionShop.setShopName(shop.getName());
			collectionShop.setAddTime(DateUtil.date());
			collectionShop.setUpdTime(collectionShop.getAddTime());
			memberCollectionShopMapper.insertSelective(collectionShop);

			baseMapper.update("update ums_shop set like_num = like_num+1 where id = "+shopId);
		}
	}

	@Override
	public boolean isCollectShop(UmsMember member, Integer shopId) {
		if(member == null){
			return false;
		}
		UmsMemberCollectionShopExample example = new UmsMemberCollectionShopExample();
		example.createCriteria().andMemberIdEqualTo(member.getId()).andShopIdEqualTo(shopId);
		return memberCollectionShopMapper.countByExample(example) > 0;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteCollectionShop(Integer collectShopId) {
		memberCollectionShopMapper.deleteByPrimaryKey(collectShopId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteCollectionByShopId(Integer shopId) {
		UmsMemberCollectionShopExample example = new UmsMemberCollectionShopExample();
		example.createCriteria().andShopIdEqualTo(shopId);
		memberCollectionShopMapper.deleteByExample(example);
	}
}