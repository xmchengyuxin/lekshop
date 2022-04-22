package com.chengyu.core.service.content.impl;

import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.mapper.CmsFeedbackMapper;
import com.chengyu.core.model.CmsFeedback;
import com.chengyu.core.model.CmsFeedbackExample;
import com.chengyu.core.service.content.FeedbackService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @title  意见反馈
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private CmsFeedbackMapper feedbackMapper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addFeedback(CmsFeedback feedback) {
		feedback.setAddTime(new Date());
		feedback.setUpdTime(feedback.getAddTime());
		feedback.setStatus(CommonConstant.NO);
		feedbackMapper.insert(feedback);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void handleFeedback(Integer id) {
		CmsFeedback feedback = new CmsFeedback();
		feedback.setId(id);
		feedback.setStatus(CommonConstant.YES);
        feedbackMapper.updateByPrimaryKeySelective(feedback);
    }

	@Override
	public List<CmsFeedback> getFeedbackList(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
        CmsFeedbackExample example = new CmsFeedbackExample();
        example.setOrderByClause("add_time desc");
        return feedbackMapper.selectByExample(example);
	}
}