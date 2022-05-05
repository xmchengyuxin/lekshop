package com.chengyu.core.service.order;

import com.chengyu.core.domain.form.OrderCommentForm;
import com.chengyu.core.domain.form.OrderCommentSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OmsOrderComment;
import com.chengyu.core.model.OmsOrderDetail;
import com.chengyu.core.model.UmsMember;

import java.util.List;

/**
 * @title  评价
 * @author LeGreen
 * @date   2022/4/29
 */
public interface OrderCommentService {

	/**
	 * 评价列表
	 * @author LeGreen
	 * @date   2022/4/29
	 * @param  form
	 * @param  page
	 * @param  pageSize
	 * @return List<OmsOrderComment>
	 */
	List<OmsOrderComment> getCommentList(OrderCommentSearchForm form, Integer page, Integer pageSize);

	/**
	 * 订单完成后自动生成待评价订单
	 * @author LeGreen
	 * @date   2022/4/29
	 * @param  detailList
	 */
	void initComment(List<OmsOrderDetail> detailList);

	/**
	 * 评价
	 * @author LeGreen
	 * @date   2022/4/29
	 * @param  member
	 * @param  form
	 * @throws ServiceException 业务异常
	 */
	void addComment(UmsMember member, OrderCommentForm form) throws ServiceException;

	/**
	 * 修改评价
	 * @author LeGreen
	 * @date   2022/4/29
	 * @param  comment
	 */
	void updateComment(OmsOrderComment comment);

	/**
	 * 删除评价
	 * @author LeGreen
	 * @date   2022/4/29
	 * @param  commentId
	 */
	void deleteComment(Integer commentId);

	/**
	 * 到期自动好评
	 * @author LeGreen
	 * @date   2022/5/5
	 * @param  orderNo
	 */
	void autoComment(String orderNo);
}