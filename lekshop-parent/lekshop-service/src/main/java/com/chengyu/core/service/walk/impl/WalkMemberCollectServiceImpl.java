package com.chengyu.core.service.walk.impl;

import cn.hutool.core.date.DateUtil;
import com.chengyu.core.mapper.BaseMapper;
import com.chengyu.core.mapper.WalkMemberCollectionMapper;
import com.chengyu.core.mapper.WalkMemberMapper;
import com.chengyu.core.model.WalkMember;
import com.chengyu.core.model.WalkMemberCollection;
import com.chengyu.core.model.WalkMemberCollectionExample;
import com.chengyu.core.service.walk.WalkMemberCollectService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WalkMemberCollectServiceImpl implements WalkMemberCollectService {
	
	@Autowired
	private WalkMemberCollectionMapper walkMemberCollectionMapper;
	@Autowired
	private WalkMemberMapper walkMemberMapper;
	@Autowired
	private BaseMapper baseMapper;

	@Override
	public List<WalkMemberCollection> getWalkMemberCollectionList(Integer viewWalkMemberId, Integer walkMemberId, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		WalkMemberCollectionExample example = new WalkMemberCollectionExample();
		example.setOrderByClause("add_time desc");
		if(viewWalkMemberId != null){
			example.createCriteria().andViewMemberIdEqualTo(viewWalkMemberId);
		}
		if(walkMemberId != null){
			example.createCriteria().andWalkMemberIdEqualTo(walkMemberId);
		}
		return walkMemberCollectionMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void collectOrNoWalkMember(WalkMember viewWalkMember, Integer walkMemberId) {
		if(this.isCollectWalkMember(viewWalkMember, walkMemberId)){
			//取消收藏
			WalkMemberCollectionExample example = new WalkMemberCollectionExample();
			example.createCriteria().andViewMemberIdEqualTo(viewWalkMember.getId()).andWalkMemberIdEqualTo(walkMemberId);
			walkMemberCollectionMapper.deleteByExample(example);

			baseMapper.update("update walk_member set fans_num = fans_num-1 where id = "+walkMemberId);
			baseMapper.update("update walk_member set focus_num = focus_num-1 where id = "+viewWalkMember.getId());
		}else{
			//收藏
			WalkMemberCollection collection = new WalkMemberCollection();
			collection.setViewMemberId(viewWalkMember.getId());
			collection.setViewMemberName(viewWalkMember.getNickname());
			collection.setViewMemberHeadImg(viewWalkMember.getHeadImg());

			WalkMember  walkMember = walkMemberMapper.selectByPrimaryKey(walkMemberId);
			collection.setWalkMemberId(walkMember.getId());
			collection.setWalkMemberHeadImg(walkMember.getHeadImg());
			collection.setWalkMemberName(walkMember.getNickname());
			collection.setAddTime(DateUtil.date());
			collection.setUpdTime(collection.getAddTime());
			walkMemberCollectionMapper.insertSelective(collection);

			baseMapper.update("update walk_member set fans_num = fans_num+1 where id = "+walkMemberId);
			baseMapper.update("update walk_member set focus_num = focus_num+1 where id = "+viewWalkMember.getId());
		}
	}

	@Override
	public boolean isCollectWalkMember(WalkMember walkMember, Integer walkMemberId) {
		if(walkMember == null){
			return false;
		}
		WalkMemberCollectionExample example = new WalkMemberCollectionExample();
		example.createCriteria().andViewMemberIdEqualTo(walkMember.getId()).andWalkMemberIdEqualTo(walkMemberId);
		return walkMemberCollectionMapper.countByExample(example) > 0;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteCollectionWalkMember(Integer collectId) {
		walkMemberCollectionMapper.deleteByPrimaryKey(collectId);
	}

}