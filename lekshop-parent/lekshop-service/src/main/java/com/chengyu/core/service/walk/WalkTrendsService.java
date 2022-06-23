package com.chengyu.core.service.walk;

import com.chengyu.core.domain.form.TrendsForm;
import com.chengyu.core.domain.result.WalkTrendsCommentResult;
import com.chengyu.core.domain.result.WalkTrendsResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.WalkMember;
import com.chengyu.core.model.WalkTrends;
import com.chengyu.core.model.WalkTrendsCollection;
import com.chengyu.core.model.WalkTrendsComment;

import java.util.List;

/**
 * @title  动态管理
 * @author LeGreen
 * @date   2022/5/19
 */
public interface WalkTrendsService {
	
	/**
	 * 发布动态
	 * @author LeGreen
	 * @date   2022/5/19
	 * @param  walkMember
	 * @param  trends
	 * @throws ServiceException 业务异常
	 */
	void publishTrends(WalkMember walkMember, WalkTrends trends, List<Integer> goodsIdList) throws ServiceException;
	
	/**
	 * 修改动态
	 * @author LeGreen
	 * @date   2022/5/19
	 * @param  trends
	 * @throws ServiceException 业务异常
	 */
	void editTrends(WalkTrends trends, List<Integer> goodsIdList) throws ServiceException;
	
	/**
	 * 审核动态
	 * @author LeGreen
	 * @date   2022/5/19
	 * @param  id
	 * @param  status
	 * @param  reason
	 * @throws ServiceException 业务异常
	 */
	void verifyTrends(Integer id, Integer status, String reason) throws ServiceException;
	
	/**
	 * 动态列表
	 * @author LeGreen
	 * @date   2022/5/19
	 * @param  form
	 * @param  page
	 * @param  pageSize
	 * @return List<WalkTrends>
	 * @throws ServiceException 业务异常
	 */
	CommonPage<WalkTrendsResult> getTrendsList(TrendsForm form, Integer page, Integer pageSize);
	
	/**
	 * 删除动态
	 * @author LeGreen
	 * @date   2022/5/19
	 * @param  ids
	 * @return void
	 * @throws ServiceException 业务异常
	 */
	void deleteTrends(String ids) throws ServiceException;
	
	/**
	 * 是否点赞
	 * @author LeGreen
	 * @date   2022/5/19
	 * @param  viewMember
	 * @param  trendId
	 * @return boolean
	 * @throws ServiceException 业务异常
	 */
	boolean isCollection(WalkMember viewMember, Integer trendId);
	
	/**
	 * 点赞或取消点赞
	 * @author LeGreen
	 * @date   2022/5/19
	 * @param  viewMember
	 * @param  trendId
	 * @throws ServiceException 业务异常
	 */
	void collectOrCancelTrends(WalkMember viewMember, Integer trendId);
	
	/**
	 * 我点赞的动态
	 * @author LeGreen
	 * @date   2022/5/19
	 * @param  viewWalkMemberId
	 * @param  page
	 * @param  pageSize
	 * @return List<WalkTrendsCollection>
	 * @throws ServiceException 业务异常
	 */
	List<WalkTrendsCollection> getMyCollectionTrends(Integer viewWalkMemberId, Integer page, Integer pageSize) throws ServiceException;
	
	/**
	 * 我获赞的动态
	 * @author LeGreen
	 * @date   2022/5/19
	 * @param  walkMemberId
	 * @param  page
	 * @param  pageSize
	 * @return List<WalkTrendsCollection>
	 * @throws ServiceException 业务异常
	 */
	List<WalkTrendsCollection> getMyGetCollectionTrends(Integer walkMemberId, Integer page, Integer pageSize) throws ServiceException;

	/**
	 * 评论
	 * @author LeGreen
	 * @date   2022/5/19
	 * @param  viewMember
	 * @param  trendsId
	 * @param  content
	 * @throws ServiceException 业务异常
	 */
	WalkTrendsComment addComment(WalkMember viewMember, Integer trendsId, Integer commentId, String content);
	
	/**
	 * 删除评论
	 * @author LeGreen
	 * @date   2022/5/19
	 * @param  commentId
	 * @throws ServiceException 业务异常
	 */
	void deleteComment(Integer commentId) throws ServiceException;

	/**
	 * 是否点赞评论
	 * @author LeGreen
	 * @date   2022/5/19
	 * @param  viewMember
	 * @param  commentId
	 * @return boolean
	 */
	boolean isLikeComment(WalkMember viewMember, Integer commentId);

	/**
	 * 点赞或取消点赞评论
	 * @author LeGreen
	 * @date   2022/5/19
	 * @param  viewMember
	 * @param  commentId
	 * @throws ServiceException 业务异常
	 */
	void likeOrCancelComment(WalkMember viewMember, Integer commentId);
	
	/**
	 * 评论列表
	 * @author LeGreen
	 * @date   2022/5/19
	 * @param  trendsId
	 * @param  page
	 * @param  pageSize
	 * @return List<WalkTrendsComment>
	 */
	CommonPage<WalkTrendsCommentResult> getTrendsCommentList(Integer trendsId, WalkMember currentMember, Integer viewWalkMemberId, Integer walkMemberId, Integer page, Integer pageSize);
	
	/**
	 * 获取动态详情
	 * @author LeGreen
	 * @date   2022/5/19
	 * @param  trendsId
	 * @return WalkTrends
	 * @throws ServiceException 业务异常
	 */
	WalkTrends getTrendsById(Integer trendsId);
	
	/**
	 * 增加浏览数量
	 * @author LeGreen
	 * @date   2022/5/19
	 * @param  trendsId
	 * @param  num
	 * @throws ServiceException 业务异常
	 */
	void addViewNums(Integer trendsId, Integer num);

	/**
	 * 动态详情
	 * @author LeGreen
	 * @date   2022/5/20
	 * @param  trendsId
	 * @return WalkTrendsResult
	 */
	WalkTrendsResult getTrendsDetail(Integer trendsId);

	/**
	 * 删除关注
	 * @author LeGreen
	 * @date   2022/6/18
	 * @param  collectionId
	 */
    void deleteCollectionTrends(WalkMember member, Integer trendsId);
}