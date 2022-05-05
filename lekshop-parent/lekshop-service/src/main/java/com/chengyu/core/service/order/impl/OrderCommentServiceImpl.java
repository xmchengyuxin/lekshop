package com.chengyu.core.service.order.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.OrderEnums;
import com.chengyu.core.domain.form.OrderCommentForm;
import com.chengyu.core.domain.form.OrderCommentSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.BaseMapper;
import com.chengyu.core.mapper.OmsOrderCommentMapper;
import com.chengyu.core.mapper.OmsOrderDetailMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.service.order.OrderCommentService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderCommentServiceImpl implements OrderCommentService {
	
	@Autowired
	private OmsOrderCommentMapper orderCommentMapper;
	@Autowired
	private BaseMapper baseMapper;
	@Autowired
	private OmsOrderDetailMapper orderDetailMapper;


	@Override
	public List<OmsOrderComment> getCommentList(OrderCommentSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		OmsOrderCommentExample example = new OmsOrderCommentExample();
		example.setOrderByClause("add_time desc");
		OmsOrderCommentExample.Criteria criteria = example.createCriteria();
		if(form.getGoodsId() != null){
			criteria.andGoodsIdEqualTo(form.getGoodsId());
		}
		if(form.getDetailId() != null){
			criteria.andDetailIdEqualTo(form.getDetailId());
		}
		if(form.getMemberId() != null){
			criteria.andMemberIdEqualTo(form.getMemberId());
		}
		if(form.getStatus() != null){
			criteria.andStatusEqualTo(form.getStatus());
		}
		if(form.getShopId() != null){
			criteria.andShopIdEqualTo(form.getShopId());
		}
		return orderCommentMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void initComment(List<OmsOrderDetail> detailList) {
		Date now = DateUtil.date();
		for(OmsOrderDetail detail : detailList){
			OmsOrderComment comment = new OmsOrderComment();
			comment.setDetailId(detail.getId());
			comment.setGoodsId(detail.getGoodsId());
			comment.setGoodsName(detail.getGoodsName());
			comment.setGoodsMainImg(detail.getGoodsMainImg());
			comment.setGoodsParamName(detail.getGoodsParamName());
			comment.setShopId(detail.getShopId());
			comment.setShopName(detail.getShopName());
			comment.setMemberId(detail.getMemberId());
			comment.setMemberName(detail.getMemberName());
			comment.setMemberHeadImg(detail.getMemberHeadImg());
			comment.setStatus(OrderEnums.CommentStatus.WAIT_COMMENT.getValue());
			comment.setAddTime(now);
			comment.setUpdTime(now);
			orderCommentMapper.insert(comment);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addComment(UmsMember member, OrderCommentForm form) throws ServiceException {
		OmsOrderComment comment = orderCommentMapper.selectByPrimaryKey(form.getId());
		if(comment == null || comment.getStatus() != OrderEnums.CommentStatus.WAIT_COMMENT.getValue()){
			throw new ServiceException("非待评价状态");
		}
		this.comment(comment, form);
	}

	private void comment(OmsOrderComment comment, OrderCommentForm form){
		OmsOrderComment updateComment = new OmsOrderComment();
		updateComment.setId(comment.getId());
		updateComment.setAnonymousStatus(form.getAnonymousStatus());
		updateComment.setContent(form.getContent());
		updateComment.setImg(form.getImg());
		updateComment.setGoodsComment(form.getGoodsComment());
		updateComment.setGoodsStarNum(form.getGoodsStarNum());
		updateComment.setDeliveryStarNum(form.getDeliveryStarNum());
		updateComment.setShopStarNum(form.getShopStarNum());
		updateComment.setLogisticStarNum(form.getLogisticStarNum());
		updateComment.setAvgStarNum(NumberUtil.div(NumberUtil.add(form.getGoodsStarNum(), form.getDeliveryStarNum(), form.getShopStarNum(),form.getLogisticStarNum()), 4));
		updateComment.setStatus(OrderEnums.CommentStatus.COMMENTED.getValue());
		updateComment.setUpdTime(DateUtil.date());
		orderCommentMapper.updateByPrimaryKeySelective(updateComment);

		//更新商品评价的数量
		StringBuilder updateSql = new StringBuilder();
		updateSql.append("update pms_goods set comment_num = comment_num+1");
		if(form.getGoodsComment() == OrderEnums.CommentType.GOOD.getValue()){
			updateSql.append(" ,good_comment_num=good_comment_num+1 ");
		}else if(form.getGoodsComment() == OrderEnums.CommentType.BAD.getValue()){
			updateSql.append(" ,bad_comment_num=bad_comment_num+1 ");
		}
		updateSql.append(" where id="+comment.getGoodsId());
		baseMapper.update(updateSql.toString());

		//更新店铺的评分
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateComment(OmsOrderComment comment) {
		comment.setUpdTime(DateUtil.date());
		orderCommentMapper.updateByPrimaryKey(comment);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteComment(Integer commentId) {
		orderCommentMapper.deleteByPrimaryKey(commentId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void autoComment(String orderNo) {
		OmsOrderDetailExample example = new OmsOrderDetailExample();
		example.createCriteria().andOrderNoEqualTo(orderNo);
		List<OmsOrderDetail> detailList = orderDetailMapper.selectByExample(example);

		if(CollectionUtil.isNotEmpty(detailList)){
			OmsOrderCommentExample commentExample = new OmsOrderCommentExample();
			commentExample.createCriteria().andDetailIdIn(detailList.stream().map(OmsOrderDetail::getId).collect(Collectors.toList()))
					.andStatusEqualTo(OrderEnums.CommentStatus.WAIT_COMMENT.getValue());
			List<OmsOrderComment> commentList = orderCommentMapper.selectByExample(commentExample);
			if(CollectionUtil.isNotEmpty(commentList)){
				//自动评价
				for(OmsOrderComment comment : commentList){
					OrderCommentForm form = new OrderCommentForm();
					form.setAnonymousStatus(CommonConstant.YES_INT);
					form.setContent("评价方未及时做出评价,系统默认好评!");
					form.setGoodsComment(OrderEnums.CommentType.GOOD.getValue());
					BigDecimal good = new BigDecimal(5);
					form.setGoodsStarNum(good);
					form.setDeliveryStarNum(good);
					form.setShopStarNum(good);
					form.setLogisticStarNum(good);
					this.comment(comment, form);
				}
			}
		}
	}
}