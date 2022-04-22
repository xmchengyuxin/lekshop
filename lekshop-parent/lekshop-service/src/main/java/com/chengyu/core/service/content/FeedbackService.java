package com.chengyu.core.service.content;

import com.chengyu.core.model.CmsFeedback;

import java.util.List;

/**
 * @title  意见反馈
 * @author LeGreen
 * @date   2021/1/6
 */
public interface FeedbackService {

	/**
	 * 添加意见反馈
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  feedback
	 */
	void addFeedback(CmsFeedback feedback);

	/**
	 * 处理意见反馈
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 */
	void handleFeedback(Integer id);

	/**
	 * 意见反馈列表
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  page
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.CmsFeedback>
	 */
	List<CmsFeedback> getFeedbackList(Integer page, Integer pageSize);
}