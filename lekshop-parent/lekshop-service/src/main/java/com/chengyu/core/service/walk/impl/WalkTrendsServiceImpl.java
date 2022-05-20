package com.chengyu.core.service.walk.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.TypeReference;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.TrendsEnums;
import com.chengyu.core.domain.form.TrendsForm;
import com.chengyu.core.domain.result.WalkTrendsCommentResult;
import com.chengyu.core.domain.result.WalkTrendsResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.mapper.*;
import com.chengyu.core.model.*;
import com.chengyu.core.service.goods.GoodsService;
import com.chengyu.core.service.walk.WalkTrendsService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WalkTrendsServiceImpl implements WalkTrendsService {
	
	@Autowired
    private WalkTrendsMapper trendsMapper;
    @Autowired
    private WalkTrendsCollectionMapper trendsCollectionMapper;
    @Autowired
    private WalkTrendsCommentMapper trendsCommentMapper;
	@Autowired
	private BaseMapper baseMapper;
	@Autowired
	private WalkTrendsGoodsMapper walkTrendsGoodsMapper;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private WalkTrendsCommentLikeMapper trendsCommentLikeMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void publishTrends(WalkMember walkMember, WalkTrends trends, List<Integer> goodsIdList) {
		trends.setWalkMemberId(walkMember.getId());
		trends.setWalkMemberName(walkMember.getNickname());
		trends.setWalkMemberHeadImg(walkMember.getHeadImg());
		trends.setWalkMemberUid(walkMember.getUid());
		trends.setSort(9999);
		trends.setWeight(0);
		trends.setViewNum(0);
		trends.setCollectionNum(0);
		trends.setCommentNum(0);
		trends.setStatus(CommonConstant.WAIT_INT);
		trends.setAddTime(DateUtil.date());
		trends.setUpdTime(trends.getAddTime());
		trendsMapper.insertSelective(trends);

		this.addTrendsGoods(trends, goodsIdList);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void editTrends(WalkTrends trends, List<Integer> goodsIdList) {
		trends.setUpdTime(DateUtil.date());
		trendsMapper.updateByPrimaryKeySelective(trends);

		WalkTrendsGoodsExample example = new WalkTrendsGoodsExample();
		example.createCriteria().andTrendsIdEqualTo(trends.getId());
		walkTrendsGoodsMapper.deleteByExample(example);

		this.addTrendsGoods(trends, goodsIdList);
	}

	private void addTrendsGoods(WalkTrends trends, List<Integer> goodsIdList){
		if(CollectionUtil.isNotEmpty(goodsIdList)){
			StringBuilder goodsImgs = new StringBuilder();
			for(Integer goodsId : goodsIdList){
				WalkTrendsGoods walkTrendsGoods = new WalkTrendsGoods();
				walkTrendsGoods.setTrendsId(trends.getId());
				PmsGoods goods = goodsService.getGoods(goodsId);
				walkTrendsGoods.setGoodsId(goods.getId());
				walkTrendsGoods.setGoodsName(goods.getTitle());
				walkTrendsGoods.setGoodsMainImg(goods.getMainImg());
				walkTrendsGoods.setPrice(goods.getPrice());
				walkTrendsGoods.setAddTime(trends.getAddTime());
				walkTrendsGoods.setUpdTime(trends.getAddTime());
				walkTrendsGoodsMapper.insertSelective(walkTrendsGoods);
				goodsImgs.append("|"+goods.getMainImg());
			}
			if(trends.getType() == TrendsEnums.TrendsType.GOODS.getValue()){
				//宝贝上新将宝贝主图插入到images
				WalkTrends updateTrends = new WalkTrends();
				updateTrends.setId(trends.getId());
				updateTrends.setImages(goodsImgs.substring(1));
				trendsMapper.updateByPrimaryKeySelective(updateTrends);
			}
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void verifyTrends(Integer id, Integer status, String reason) {
		WalkTrends trends = new WalkTrends();
		trends.setId(id);
		trends.setStatus(status);
		trends.setVerifyTime(DateUtil.date());
		trends.setReason(reason);
		trendsMapper.updateByPrimaryKeySelective(trends);
	}

	@Override
	public CommonPage<WalkTrendsResult> getTrendsList(TrendsForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		WalkTrendsExample example = new WalkTrendsExample();
		if(StringUtils.isNotBlank(form.getSort())){
			example.setOrderByClause(form.getSort());
		}else{
			example.setOrderByClause("add_time desc");
		}
		WalkTrendsExample.Criteria criteria = example.createCriteria();
		if(form.getWalkMemberId() != null){
			criteria.andWalkMemberIdEqualTo(form.getWalkMemberId());
		}
		if(form.getWalkMemberUid() != null){
			criteria.andWalkMemberUidEqualTo(form.getWalkMemberUid());
		}
		if(form.getType() != null){
			criteria.andTypeEqualTo(form.getType());
		}
		if(CollectionUtil.isNotEmpty(form.getTypeList())){
			criteria.andTypeIn(form.getTypeList());
		}
		if(form.getStatus() != null){
			criteria.andStatusEqualTo(form.getStatus());
		}
		List<WalkTrends> list = trendsMapper.selectByExampleWithBLOBs(example);

		List<WalkTrendsResult> trendsList = new ArrayList<>();
		for(WalkTrends walkTrends : list){
			WalkTrendsResult walkTrendsResult = new WalkTrendsResult();
			walkTrendsResult.setWalkTrends(walkTrends);

			WalkTrendsGoodsExample goodsExample = new WalkTrendsGoodsExample();
			goodsExample.createCriteria().andTrendsIdEqualTo(walkTrends.getId());
			walkTrendsResult.setTrendsGoodsList(walkTrendsGoodsMapper.selectByExample(goodsExample));
			trendsList.add(walkTrendsResult);
		}

		PageInfo pageInfo = new PageInfo<>(list);
		pageInfo.setList(trendsList);
		return CommonPage.restPage(pageInfo);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteTrends(String ids) {
		List<String> idsList = Arrays.asList(ids.split(CommonConstant.DH_REGEX));
		List<Integer> trendsIdList = Convert.convert(new TypeReference<List<Integer>>() {}, idsList);

		WalkTrendsExample example = new WalkTrendsExample();
		example.createCriteria().andIdIn(trendsIdList);
		trendsMapper.deleteByExample(example);

		//删除关注
		WalkTrendsCollectionExample collectionExample = new WalkTrendsCollectionExample();
		collectionExample.createCriteria().andTrendsIdIn(trendsIdList);
		trendsCollectionMapper.deleteByExample(collectionExample);

		//删除评论
		WalkTrendsCommentExample commentExample = new WalkTrendsCommentExample();
		commentExample.createCriteria().andTrendsIdIn(trendsIdList);
		trendsCommentMapper.deleteByExample(commentExample);
	}

	@Override
	public boolean isCollection(WalkMember viewMember, Integer trendId) {
		WalkTrendsCollectionExample example = new WalkTrendsCollectionExample();
		example.createCriteria().andViewMemberIdEqualTo(viewMember.getId()).andTrendsIdEqualTo(trendId);
		long num = trendsCollectionMapper.countByExample(example);
		return num > 0;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void collectOrCancelTrends(WalkMember viewMember, Integer trendsId) {
		WalkTrends trends = trendsMapper.selectByPrimaryKey(trendsId);
		if(this.isCollection(viewMember, trendsId)){
			//取消关注
			WalkTrendsCollectionExample example = new WalkTrendsCollectionExample();
			example.createCriteria().andViewMemberIdEqualTo(viewMember.getId()).andTrendsIdEqualTo(trendsId);
			trendsCollectionMapper.deleteByExample(example);

			//关注人数-1
			baseMapper.update("update walk_trends set collection_num = collection_num-1 where id = "+trendsId);
			baseMapper.update("update walk_member set like_num = like_num-1 where id = "+trends.getWalkMemberId());
		}else{
			//关注
			WalkTrendsCollection collection = new WalkTrendsCollection();
			collection.setViewMemberId(viewMember.getId());
			collection.setViewMemberName(viewMember.getNickname());
			collection.setViewMemberHeadImg(viewMember.getHeadImg());
			collection.setWalkMemberId(trends.getWalkMemberId());
			collection.setWalkMemberName(trends.getWalkMemberName());
			collection.setWalkMemberHeadImg(trends.getWalkMemberHeadImg());
			collection.setTrendsId(trends.getId());
			collection.setTrendsContent(trends.getContent());
			collection.setTrendsImages(trends.getImages());
			collection.setTrendsVideoUrl(trends.getVideoUrl());
			collection.setAddTime(DateUtil.date());
			collection.setUpdTime(collection.getAddTime());
			trendsCollectionMapper.insertSelective(collection);

			//关注人数+1
			baseMapper.update("update walk_trends set collection_num = collection_num+1 where id = "+trendsId);
			baseMapper.update("update walk_member set like_num = like_num+1 where id = "+trends.getWalkMemberId());
		}
	}

	@Override
	public List<WalkTrendsCollection> getMyCollectionTrends(Integer viewWalkMemberId, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		WalkTrendsCollectionExample example = new WalkTrendsCollectionExample();
		example.setOrderByClause("add_time desc");
		example.createCriteria().andViewMemberIdEqualTo(viewWalkMemberId);
		return trendsCollectionMapper.selectByExample(example);
	}

	@Override
	public List<WalkTrendsCollection> getMyGetCollectionTrends(Integer walkMemberId, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		WalkTrendsCollectionExample example = new WalkTrendsCollectionExample();
		example.setOrderByClause("add_time desc");
		example.createCriteria().andWalkMemberIdEqualTo(walkMemberId);
		return trendsCollectionMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addComment(WalkMember viewMember, Integer trendsId, Integer commentId, String content) {
		WalkTrendsComment comment = new WalkTrendsComment();
		comment.setViewMemberId(viewMember.getId());
		comment.setViewMemberName(viewMember.getNickname());
		comment.setViewMemberHeadImg(viewMember.getHeadImg());
		comment.setTrendsId(trendsId);

		WalkTrends trends = trendsMapper.selectByPrimaryKey(trendsId);
		comment.setWalkMemberId(trends.getWalkMemberId());
		comment.setWalkMemberName(trends.getWalkMemberName());
		comment.setWalkMemberHeadImg(trends.getWalkMemberHeadImg());
		comment.setTrendsId(trends.getId());
		comment.setContent(content);
		comment.setStatus(CommonConstant.YES_INT);
		if(commentId != null){
			comment.setType(2);
			comment.setPid(commentId);
			WalkTrendsComment parentComment = trendsCommentMapper.selectByPrimaryKey(commentId);
			if(parentComment.getTid() == null){
				comment.setTid(parentComment.getId());
			}else{
				comment.setTid(parentComment.getTid());
			}
			comment.setContent("@"+parentComment.getViewMemberName()+" "+comment.getContent());
		}else{
			comment.setType(1);
		}
		comment.setAddTime(DateUtil.date());
		comment.setUpdTime(comment.getAddTime());
		trendsCommentMapper.insertSelective(comment);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteComment(Integer commentId) {
		trendsCommentMapper.deleteByPrimaryKey(commentId);

		WalkTrendsCommentLikeExample example = new WalkTrendsCommentLikeExample();
		example.createCriteria().andCommentIdEqualTo(commentId);
		trendsCommentLikeMapper.deleteByExample(example);
	}

	@Override
	public boolean isLikeComment(WalkMember viewMember, Integer commentId) {
		WalkTrendsCommentLikeExample example = new WalkTrendsCommentLikeExample();
		example.createCriteria().andViewMemberIdEqualTo(viewMember.getId()).andCommentIdEqualTo(commentId);
		long num = trendsCommentLikeMapper.countByExample(example);
		return num > 0;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void likeOrCancelComment(WalkMember viewMember, Integer commentId) {
		if(this.isLikeComment(viewMember, commentId)){
			//取消关注
			WalkTrendsCommentLikeExample example = new WalkTrendsCommentLikeExample();
			example.createCriteria().andViewMemberIdEqualTo(viewMember.getId()).andCommentIdEqualTo(commentId);
			trendsCommentLikeMapper.deleteByExample(example);

			//关注人数-1
			baseMapper.update("update walk_trends_comment set like_num = like_num-1 where id = "+commentId);
		}else{
			//关注
			WalkTrendsCommentLike commentLike = new WalkTrendsCommentLike();
			commentLike.setViewMemberId(viewMember.getId());
			commentLike.setViewMemberName(viewMember.getNickname());
			commentLike.setViewMemberHeadImg(viewMember.getHeadImg());
			commentLike.setCommentId(commentId);
			commentLike.setAddTime(DateUtil.date());
			commentLike.setUpdTime(commentLike.getAddTime());
			trendsCommentLikeMapper.insertSelective(commentLike);

			//关注人数+1
			baseMapper.update("update walk_trends_comment set like_num = like_num+1 where id = "+commentId);
		}
	}

	@Override
	public CommonPage<WalkTrendsCommentResult> getTrendsCommentList(Integer trendsId, Integer viewWalkMemberId, Integer walkMemberId, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		WalkTrendsCommentExample example = new WalkTrendsCommentExample();
		example.setOrderByClause("add_time desc");
		WalkTrendsCommentExample.Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo(1);
		if(trendsId != null){
			criteria.andTrendsIdEqualTo(trendsId);
		}
		if(viewWalkMemberId != null){
			criteria.andViewMemberIdEqualTo(viewWalkMemberId);
		}
		if(walkMemberId != null){
			criteria.andWalkMemberIdEqualTo(walkMemberId);
		}
		List<WalkTrendsComment> list = trendsCommentMapper.selectByExampleWithBLOBs(example);

		List<WalkTrendsCommentResult> commentList = new ArrayList<>();
		for(WalkTrendsComment walkTrendsComment : list){
			WalkTrendsCommentResult walkTrendsCommentResult = new WalkTrendsCommentResult();
			walkTrendsCommentResult.setWalkTrendsComment(walkTrendsComment);

			example = new WalkTrendsCommentExample();
			example.setOrderByClause("add_time desc");
			example.createCriteria().andTidEqualTo(walkTrendsComment.getId()).andTypeEqualTo(2);
			walkTrendsCommentResult.setChilidCommentList(trendsCommentMapper.selectByExampleWithBLOBs(example));
			commentList.add(walkTrendsCommentResult);
		}

		PageInfo pageInfo = new PageInfo<>(list);
		pageInfo.setList(commentList);
		return CommonPage.restPage(pageInfo);
	}

	@Override
	public WalkTrends getTrendsById(Integer trendsId) {
		return trendsMapper.selectByPrimaryKey(trendsId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addViewNums(Integer trendsId, Integer num) {
		baseMapper.update("update walk_trends set view_num = view_num+"+num+" where id = "+trendsId);
	}

	@Override
	public WalkTrendsResult getTrendsDetail(Integer trendsId) {
		WalkTrendsResult result = new WalkTrendsResult();
		result.setWalkTrends(this.getTrendsById(trendsId));
		WalkTrendsGoodsExample goodsExample = new WalkTrendsGoodsExample();
		goodsExample.createCriteria().andTrendsIdEqualTo(trendsId);
		result.setTrendsGoodsList(walkTrendsGoodsMapper.selectByExample(goodsExample));
		return result;
	}
}