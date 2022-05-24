package com.chengyu.core.service.member.impl;

import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.MemberNewsEnums;
import com.chengyu.core.domain.form.MemberNewsForm;
import com.chengyu.core.mapper.UmsMemberNewsMapper;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberNews;
import com.chengyu.core.model.UmsMemberNewsExample;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.service.member.MemberNewsService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @title  用户消息
 * @author LeGreen
 * @date   2021/1/7
 */
@Service
public class MemberNewsServiceImpl implements MemberNewsService {

	@Autowired
	private UmsMemberNewsMapper memberNewsMapper;
	
	@Override
	public List<UmsMemberNews> getMemberNewsList(Integer memberId, Integer type, String readStatus, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		
		UmsMemberNewsExample example = new UmsMemberNewsExample();
		UmsMemberNewsExample.Criteria criteria = example.createCriteria();
		if(memberId != null){
			criteria.andMemberIdEqualTo(memberId);
		}
		if(StringUtils.isNotBlank(readStatus)){
			criteria.andReadStatusEqualTo(readStatus);
		}
		if(type != null){
			criteria.andTypeEqualTo(type);
		}
		example.setOrderByClause("add_time desc");
		return memberNewsMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public void addMemberNews(UmsMember member, UmsShop shop, MemberNewsEnums.MemberNewsTypes newsType, String title, String img, String content) {
		this.insertNews(member, shop, newsType.getType(), title, img, content, null);
	}

	private void insertNews(UmsMember member, UmsShop shop, Integer type, String title, String img, String content, String turnParams){
		try {
			UmsMemberNews news = new UmsMemberNews();
			news.setMemberId(member.getId());
			news.setMemberName(member.getRealname());
			if(shop != null){
				news.setShopId(shop.getId());
				news.setShopName(shop.getName());
				news.setShopLogo(shop.getLogo());
			}
			news.setType(type);
			news.setTitle(title);
			news.setImg(img);
			news.setContent(content);
			news.setTurnParams(turnParams);
			news.setSendTime(new Date());
			news.setSendStatus(CommonConstant.WAIT);
			news.setReadStatus(CommonConstant.NO);
			news.setAddTime(news.getSendTime());
			news.setUpdTime(news.getSendTime());
			memberNewsMapper.insertSelective(news);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addMemberNews(UmsMember member, MemberNewsForm newsForm) {
		this.insertNews(member, newsForm.getShop(), newsForm.getType(), newsForm.getTitle(), newsForm.getImg(), newsForm.getContent(), newsForm.getTurnParams());
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void readById(Integer id) {
		UmsMemberNews news = new UmsMemberNews();
		news.setId(id);
		news.setReadStatus(CommonConstant.YES);
		news.setUpdTime(DateUtil.date());
		memberNewsMapper.updateByPrimaryKeySelective(news);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void readAll(Integer memberId, Integer type) {
		UmsMemberNews news = new UmsMemberNews();
		news.setReadStatus(CommonConstant.YES);
		news.setUpdTime(DateUtil.date());

		UmsMemberNewsExample example = new UmsMemberNewsExample();
		UmsMemberNewsExample.Criteria criteria = example.createCriteria();
		criteria.andMemberIdEqualTo(memberId);
		if(type != null){
			criteria.andTypeEqualTo(type);
		}
		memberNewsMapper.updateByExampleSelective(news, example);
	}

	@Override
	public Long countUnReadNews(Integer memberId, Integer type) {
		UmsMemberNewsExample example = new UmsMemberNewsExample();
		UmsMemberNewsExample.Criteria criteria = example.createCriteria();
		criteria.andMemberIdEqualTo(memberId).andReadStatusEqualTo(CommonConstant.NO);
		if(type != null){
			criteria.andTypeEqualTo(type);
		}
		return memberNewsMapper.countByExample(example);
	}
	

}