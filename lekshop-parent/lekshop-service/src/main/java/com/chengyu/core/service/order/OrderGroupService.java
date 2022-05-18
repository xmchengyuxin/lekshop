package com.chengyu.core.service.order;

import com.chengyu.core.domain.form.OrderGroupSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OmsOrderDetail;
import com.chengyu.core.model.OmsOrderGroup;
import com.chengyu.core.model.OmsOrderGroupMember;

import java.util.List;

/**
 * @title  拼团
 * @author LeGreen
 * @date   2022/5/16
 */
public interface OrderGroupService {

	/**
	 * 拼购列表
	 * @author LeGreen
	 * @date   2022/5/16
	 * @param  form
	 * @param  page
	 * @param  pageSize
	 * @return List<OmsOrderGroup>
	 * @throws ServiceException 业务异常
	 */
	List<OmsOrderGroup> getOrderGroupList(OrderGroupSearchForm form, Integer page, Integer pageSize);

	/**
	 * 查询用户的拼团列表
	 * @author LeGreen
	 * @date   2022/5/16
	 * @param  memberId
	 * @param  status
	 * @param  page
	 * @param  pageSize
	 * @return List<OmsOrderGroup>
	 * @throws ServiceException 业务异常
	 */
	List<OmsOrderGroup> getOrderGroupPageByMemberId(Integer memberId, Integer status, Integer page, Integer pageSize) throws ServiceException;
	
	/**
	 * 获取拼购成员
	 * @author LeGreen
	 * @date   2022/5/16
	 * @param  orderGroupId
	 * @param  limit
	 * @return List<OmsOrderGroupMember>
	 * @throws ServiceException 业务异常
	 */
	List<OmsOrderGroupMember> getGroupMemberList(Integer orderGroupId, Integer limit) throws ServiceException;
	
	/**
	 * 发起拼团
	 * @author LeGreen
	 * @date   2022/5/16
	 * @param  orderDetail
	 * @throws ServiceException 业务异常
	 */
	void launchGroup(OmsOrderDetail orderDetail) throws ServiceException;
	
	/**
	 * 我要参团
	 * @author LeGreen
	 * @date   2022/5/16
	 * @param  orderGroupId
	 * @param  orderDetail
	 * @throws ServiceException 业务异常
	 */
	void joinGroup(Integer orderGroupId, OmsOrderDetail orderDetail) throws ServiceException;
	
	/**
	 * 获取拼购详情
	 * @author LeGreen
	 * @date   2022/5/16
	 * @param  orderGroupId
	 * @return OmsOrderGroup
	 * @throws ServiceException 业务异常
	 */
	OmsOrderGroup getOrderGroupById(Integer orderGroupId) throws ServiceException;

	/**
	 * 根据订单号获取拼团成员
	 * @author LeGreen
	 * @date   2022/5/16
	 * @param  orderNo
	 * @return List<OmsOrderGroupMember>
	 * @throws ServiceException 业务异常
	 */
	List<OmsOrderGroupMember> getGroupMemberByOrderNo(String orderNo) throws ServiceException;

	/**
	 * 拼团失败退款
	 * @author LeGreen
	 * @date   2022/5/16
	 * @param  group
	 * @return void
	 * @throws ServiceException 业务异常
	 */
	void doRefundForFailPintuan(OmsOrderGroup group) throws ServiceException;
	
	/**
	 * 统计某个商品的参团人数
	 * @author LeGreen
	 * @date   2022/5/16
	 * @param  goodsId
	 * @return long
	 * @throws ServiceException 业务异常
	 */
	long countGroupNum(Integer goodsId);
	
	/**
	 * 判断是否有正在拼团的商品
	 * @author LeGreen
	 * @date   2022/5/16
	 * @param  goodsId
	 * @return boolean
	 * @throws ServiceException 业务异常
	 */
	boolean isInGroup(Integer goodsId) throws ServiceException;

	/**
	 * 判断该笔团购是否成功
	 * @author LeGreen
	 * @date   2022/5/18
	 * @param  orderNo
	 * @return boolean
	 */
	boolean isGroupSus(String orderNo);
	
}