package com.chengyu.core.service.order.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONUtil;
import com.chengyu.core.domain.enums.MemberNewsEnums;
import com.chengyu.core.domain.enums.MemberRemindEnums;
import com.chengyu.core.domain.enums.OrderEnums;
import com.chengyu.core.domain.form.MemberNewsForm;
import com.chengyu.core.domain.form.OrderRefundForm;
import com.chengyu.core.domain.form.OrderRefundSearchForm;
import com.chengyu.core.domain.result.ChatNotice;
import com.chengyu.core.domain.result.CustomerConstant;
import com.chengyu.core.domain.result.OrderRefundResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.OmsOrderDetailMapper;
import com.chengyu.core.mapper.OmsOrderMapper;
import com.chengyu.core.mapper.OmsOrderRefundLogMapper;
import com.chengyu.core.mapper.OmsOrderRefundMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.service.config.ConfigOrderService;
import com.chengyu.core.service.im.ChatService;
import com.chengyu.core.service.member.MemberNewsService;
import com.chengyu.core.service.member.MemberService;
import com.chengyu.core.service.order.OrderRefundService;
import com.chengyu.core.service.pay.PayService;
import com.chengyu.core.service.schedule.RedisDelayQueueEnum;
import com.chengyu.core.service.schedule.RedisDelayQueueUtil;
import com.chengyu.core.service.shop.ShopConfigService;
import com.chengyu.core.service.shop.ShopService;
import com.chengyu.core.utils.NumberUtils;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @title  订单售后
 * @author LeGreen
 * @date   2022/4/29
 */
@Service
public class OrderRefundServiceImpl implements OrderRefundService {

	@Autowired
	private OmsOrderRefundMapper orderRefundMapper;
	@Autowired
	private OmsOrderRefundLogMapper orderRefundLogMapper;
	@Autowired
	private OmsOrderDetailMapper orderDetailMapper;
	@Autowired
	private OmsOrderMapper orderMapper;
	@Autowired
	private ShopConfigService shopConfigService;
	@Autowired
	private ConfigOrderService configOrderService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberNewsService memberNewsService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private PayService payService;
	@Autowired
	private ChatService chatService;
	@Autowired
	private RedisDelayQueueUtil redisDelayQueueUtil;

	@Override
	public List<OmsOrderRefund> getRefundList(OrderRefundSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		OmsOrderRefundExample example = new OmsOrderRefundExample();
		if(StringUtils.isNotBlank(form.getSort())){
			example.setOrderByClause(form.getSort());
		}else {
			example.setOrderByClause("add_time desc");
		}
		OmsOrderRefundExample.Criteria criteria = example.createCriteria();
		if(form.getMemberId() != null){
			criteria.andMemberIdEqualTo(form.getMemberId());
		}
		if(StringUtils.isNotBlank(form.getMemberName())){
			criteria.andMemberNameLike("%"+form.getMemberName()+"%");
		}
		if(form.getShopId() != null){
			criteria.andShopIdEqualTo(form.getShopId());
		}
		if(StringUtils.isNotBlank(form.getRefdundNo())){
			criteria.andRefundNoLike("%"+form.getRefdundNo()+"%");
		}
		if(form.getStatus() != null){
			criteria.andStatusEqualTo(form.getStatus());
		}
		if(form.getDateFrom() != null && form.getDateTo() != null){
			criteria.andAddTimeBetween(form.getDateFrom(), form.getDateTo());
		}
		if(CollectionUtil.isNotEmpty(form.getStatusList())){
			criteria.andStatusIn(form.getStatusList());
		}
		return orderRefundMapper.selectByExample(example);
	}

	@Override
	public OrderRefundResult getOrderRefund(Integer refundId) {
		OrderRefundResult result = new OrderRefundResult();
		result.setRefund(orderRefundMapper.selectByPrimaryKey(refundId));

		OmsOrderRefundLogExample example = new OmsOrderRefundLogExample();
		example.setOrderByClause("add_time desc");
		example.createCriteria().andRefundIdEqualTo(refundId);
		result.setRefundLogList(orderRefundLogMapper.selectByExample(example));
		return result;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
	public void applyRefund(UmsMember member, OrderRefundForm form) throws ServiceException {
		OmsOrderDetail orderDetail = orderDetailMapper.selectByPrimaryKey(form.getOrderDetailId());
		if(!CollectionUtil.contains(CollectionUtil.newArrayList(
				OrderEnums.OrderStatus.WAIT_DELIVERY.getValue(),
				OrderEnums.OrderStatus.WAIT_RECEIVE.getValue(),
				OrderEnums.OrderStatus.FINISHED.getValue()),
				orderDetail.getStatus())){
			throw new ServiceException("order.refund.status.error");
		}
		if(orderDetail.getRefundStatus() != OrderEnums.RefundStatus.UN_REFUND.getValue()
		&& orderDetail.getRefundStatus() != OrderEnums.RefundStatus.REFUND_FAILED.getValue()){
			throw new ServiceException("order.refund.haveapply");
		}
		OmsOrder order = orderMapper.selectByPrimaryKey(orderDetail.getOrderId());
		if(orderDetail.getStatus() == OrderEnums.OrderStatus.FINISHED.getValue()){
			//查询售后截止时间
			if(order.getRefundExpiredTime() != null && order.getRefundExpiredTime().before(DateUtil.date())){
				throw new ServiceException("order.refund.expired");
			}
		}
		if(form.getRefundAmount().compareTo(orderDetail.getBuyTotalPrice()) > 0){
			throw new ServiceException("order.refund.most",new String[]{NumberUtils.format2(orderDetail.getBuyTotalPrice())});
		}
		OmsOrderRefund refund = new OmsOrderRefund();
		refund.setRefundNo("R"+StringUtils.genOrderNo(member.getId()));
		refund.setOrderId(orderDetail.getOrderId());
		refund.setDetailId(orderDetail.getId());
		refund.setMemberId(member.getId());
		refund.setMemberName(member.getCode());
		refund.setHeadImg(member.getHeadImg());
		refund.setRefundInd(form.getRefundInd());
		refund.setBuyTime(orderDetail.getAddTime());
		refund.setOriginalAmount(orderDetail.getBuyTotalPrice());
		refund.setShopId(orderDetail.getShopId());
		refund.setShopName(orderDetail.getShopName());
		refund.setRefundAmount(form.getRefundAmount());
		refund.setReason(form.getReason());
		refund.setImg(form.getImg());
		refund.setStatus(OrderEnums.RefundDetailStatus.APPLY.getValue());
		refund.setPayMethod(order.getPayMethod());
		refund.setRefundTime(DateUtil.date());
		refund.setGoodsId(orderDetail.getGoodsId());
		refund.setGoodsName(orderDetail.getGoodsName());
		refund.setGoodsMainImg(orderDetail.getGoodsMainImg());
		refund.setGoodsParamName(orderDetail.getGoodsParamName());
		refund.setAddTime(DateUtil.date());
		refund.setUpdTime(refund.getAddTime());
		//超过该时间未处理,自动同意
		ConfigOrder config = configOrderService.getConfigOrder();
		refund.setAutoAgreeTime(DateUtil.offsetDay(refund.getAddTime(), config.getAutoAgreeRefundDay()));
		orderRefundMapper.insertSelective(refund);

		//自动同意定时器
		redisDelayQueueUtil.addDelayQueue(refund.getRefundNo(), refund.getAutoAgreeTime(), RedisDelayQueueEnum.REFUND_AUTO_AGREE_JOB.getCode());
//		taskTriggerService.addTrigger(RefundAutoAgreeJob.class, refund.getAutoAgreeTime(), refund.getRefundNo());

		OmsOrderDetail updateDetail = new OmsOrderDetail();
		updateDetail.setId(orderDetail.getId());
		updateDetail.setRefundStatus(OrderEnums.RefundStatus.REFUNDING.getValue());
		orderDetailMapper.updateByPrimaryKeySelective(updateDetail);

		OmsOrder updateOrder = new OmsOrder();
		updateOrder.setId(order.getId());
		updateOrder.setRefundStatus(OrderEnums.RefundStatus.REFUNDING.getValue());
		orderMapper.updateByPrimaryKeySelective(updateOrder);

		//记录
		OmsOrderRefundLog log = new OmsOrderRefundLog();
		log.setRefundId(refund.getId());
		log.setRefundNo(refund.getRefundNo());
		log.setOperatorId(member.getId());
		log.setOperatorName(member.getCode());
		log.setHeadImg(member.getHeadImg());
		StringBuilder content = new StringBuilder();
		content.append("买家申请退款(").append(refund.getRefundInd() == 1 ? "仅退款" : "退货退款").append(")");
		content.append(",退款原因:").append(refund.getReason());
		content.append(",退款金额:").append(refund.getRefundAmount());
		log.setContent(content.toString());
		log.setAddTime(DateUtil.date());
		log.setUpdTime(log.getAddTime());
		orderRefundLogMapper.insert(log);

		ChatNotice notice = new ChatNotice();
		notice.setTitle("重要! 有用户申请售后,请及时处理~");
		notice.setContent(JSONUtil.toJsonStr(refund));
		notice.setType(MemberRemindEnums.MemberRemindTypes.WAIT_VERIFY_REFUND.getType());
		UmsShop shop = shopService.getShopById(order.getShopId());
		chatService.sendNoticeMsg(shop.getMemberId(), JSONUtil.toJsonStr(notice));
//		memberRemindService.addShopRemind(order.getShopId(), MemberRemindEnums.MemberRemindTypes.WAIT_VERIFY_REFUND, "重要! 有用户申请售后,请及时处理~");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void agreeRefund(UmsShop shop, Integer refundId) throws ServiceException {
		OmsOrderRefund refund = this.getRefundByShop(shop, refundId);
		if(refund.getStatus() != OrderEnums.RefundDetailStatus.APPLY.getValue()){
			throw new ServiceException("order.refund.agree.error");
		}
		//同意退款
		this.agreeRefundOrSalesReturn(refund);
		//记录
		OmsOrderRefundLog log = new OmsOrderRefundLog();
		log.setRefundId(refund.getId());
		log.setRefundNo(refund.getRefundNo());
		log.setOperatorId(shop.getId());
		log.setOperatorName(shop.getName());
		log.setHeadImg(shop.getLogo());
		StringBuilder content = new StringBuilder();
		content.append("卖家同意退款申请(").append(refund.getRefundInd() == 1 ? "仅退款" : "退货退款").append(")");
		log.setContent(content.toString());
		log.setAddTime(DateUtil.date());
		log.setUpdTime(log.getAddTime());
		orderRefundLogMapper.insert(log);
	}

	/**
	 * 退款成功更新原订单信息
	 * @author LeGreen
	 * @date   2022/5/6
	 * @param  refund
	 */
	private void refundSusForUpdateOrder(OmsOrderRefund refund){
		OmsOrderDetail updateDetail = new OmsOrderDetail();
		updateDetail.setId(refund.getDetailId());
		updateDetail.setRefundStatus(OrderEnums.RefundStatus.REFUND_SUS.getValue());
		updateDetail.setStatus(OrderEnums.OrderStatus.REFUNDED.getValue());
		updateDetail.setRefundAmount(refund.getRefundAmount());
		orderDetailMapper.updateByPrimaryKeySelective(updateDetail);

		OmsOrder order = orderMapper.selectByPrimaryKey(refund.getOrderId());
		OmsOrder updateOrder = new OmsOrder();
		updateOrder.setId(order.getId());
		updateOrder.setRefundAmount(NumberUtil.add(order.getRefundAmount(), refund.getRefundAmount()));
		//判断是全部退款还是部分退款
		OmsOrderDetailExample example = new OmsOrderDetailExample();
		example.createCriteria().andOrderIdEqualTo(order.getId()).andStatusIn(CollectionUtil.newArrayList(
				OrderEnums.OrderStatus.WAIT_DELIVERY.getValue(),
				OrderEnums.OrderStatus.WAIT_RECEIVE.getValue(),
				OrderEnums.OrderStatus.FINISHED.getValue()));
		long count = orderDetailMapper.countByExample(example);
		updateOrder.setRefundStatus(count > 0 ? 2 : 3);
		if(updateOrder.getRefundStatus() == 3){
			updateOrder.setStatus(OrderEnums.OrderStatus.CANCEL.getValue());
		}
		orderMapper.updateByPrimaryKeySelective(updateOrder);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void refuseRefund(UmsShop shop, Integer refundId, String reason) throws ServiceException {
		OmsOrderRefund refund = this.getRefundByShop(shop, refundId);
		if(refund.getStatus() != OrderEnums.RefundDetailStatus.APPLY.getValue()){
			throw new ServiceException("order.refund.refuse.error");
		}
		OmsOrderRefund updateRefund = new OmsOrderRefund();
		updateRefund.setId(refund.getId());
		updateRefund.setStatus(OrderEnums.RefundDetailStatus.SALES_REFUSE.getValue());
		orderRefundMapper.updateByPrimaryKeySelective(updateRefund);

		OmsOrderDetail updateDetail = new OmsOrderDetail();
		updateDetail.setId(refund.getDetailId());
		updateDetail.setRefundStatus(OrderEnums.RefundStatus.REFUND_FAILED.getValue());
		orderDetailMapper.updateByPrimaryKeySelective(updateDetail);

		OmsOrder updateOrder = new OmsOrder();
		updateOrder.setId(refund.getOrderId());
		updateOrder.setRefundStatus(OrderEnums.RefundStatus.REFUND_FAILED.getValue());
		orderMapper.updateByPrimaryKeySelective(updateOrder);

		//记录
		OmsOrderRefundLog log = new OmsOrderRefundLog();
		log.setRefundId(refund.getId());
		log.setRefundNo(refund.getRefundNo());
		log.setOperatorId(shop.getId());
		log.setOperatorName(shop.getName());
		log.setHeadImg(shop.getLogo());
		StringBuilder content = new StringBuilder();
		content.append("卖家拒绝退款申请(").append(refund.getRefundInd() == 1 ? "仅退款" : "退货退款").append(")");
		content.append(",拒绝原因:"+reason);
		log.setContent(content.toString());
		log.setAddTime(DateUtil.date());
		log.setUpdTime(log.getAddTime());
		orderRefundLogMapper.insert(log);

		//拒绝售后消息
		UmsMember member = memberService.getMemberById(refund.getMemberId());
		MemberNewsForm newsForm = new MemberNewsForm(MemberNewsEnums.MemberNewsTypes.NEWS_REFUSE_REFUND);
		newsForm.setShop(shopService.getShopById(refund.getShopId()));
		newsForm.initTurnParams("refundId", refund.getId().toString());
		newsForm.replace("#goodsName#", refund.getGoodsName());
		newsForm.setImg(refund.getGoodsMainImg());
		memberNewsService.addMemberNews(member, newsForm);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void applyCustomerServiceIn(UmsMember member, Integer refundId) throws ServiceException {
		OmsOrderRefund refund = this.getRefundByMember(member, refundId);
		if(refund.getStatus() != OrderEnums.RefundDetailStatus.SALES_REFUSE.getValue()){
			throw new ServiceException("order.refund.customer.error");
		}
		OmsOrderRefund updateRefund = new OmsOrderRefund();
		updateRefund.setId(refund.getId());
		updateRefund.setStatus(OrderEnums.RefundDetailStatus.SERVICE_IN.getValue());
		orderRefundMapper.updateByPrimaryKeySelective(updateRefund);

		OmsOrderDetail updateDetail = new OmsOrderDetail();
		updateDetail.setId(refund.getDetailId());
		updateDetail.setRefundStatus(OrderEnums.RefundStatus.REFUNDING.getValue());
		orderDetailMapper.updateByPrimaryKeySelective(updateDetail);

		OmsOrder updateOrder = new OmsOrder();
		updateOrder.setId(refund.getOrderId());
		updateOrder.setRefundStatus(OrderEnums.RefundStatus.REFUNDING.getValue());
		orderMapper.updateByPrimaryKeySelective(updateOrder);

		//记录
		OmsOrderRefundLog log = new OmsOrderRefundLog();
		log.setRefundId(refund.getId());
		log.setRefundNo(refund.getRefundNo());
		log.setOperatorId(member.getId());
		log.setOperatorName(member.getCode());
		log.setHeadImg(member.getHeadImg());
		log.setContent("买家申请客服介入");
		log.setAddTime(DateUtil.date());
		log.setUpdTime(log.getAddTime());
		orderRefundLogMapper.insert(log);

		ChatNotice notice = new ChatNotice();
		notice.setTitle("重要! 买家申请客服介入, 请及时处理~");
		notice.setContent(JSONUtil.toJsonStr(refund));
		notice.setType(MemberRemindEnums.AdminRemindTypes.WAIT_VERIFY_COMPLAINT.getType());
		chatService.sendNoticeMsg(CustomerConstant.ADMIN_MEMBER_ID, JSONUtil.toJsonStr(notice));
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void agreeRefundByCustomer(SysAdmin admin, Integer refundId) throws ServiceException {
		OmsOrderRefund refund = orderRefundMapper.selectByPrimaryKey(refundId);
		if(refund.getStatus() != OrderEnums.RefundDetailStatus.SERVICE_IN.getValue()){
			throw new ServiceException("order.refund.customer.join");
		}
		//同意退款
		this.agreeRefundOrSalesReturn(refund);
		//记录
		OmsOrderRefundLog log = new OmsOrderRefundLog();
		log.setRefundId(refund.getId());
		log.setRefundNo(refund.getRefundNo());
		log.setOperatorId(admin.getId());
		log.setOperatorName(admin.getName());
		log.setHeadImg(admin.getHeadImg());
		log.setContent("系统客服同意退款");
		log.setAddTime(DateUtil.date());
		log.setUpdTime(log.getAddTime());
		orderRefundLogMapper.insert(log);
	}

	/**
	 * 同意退款退货
	 * @author LeGreen
	 * @date   2022/5/6
	 * @param  refund
	 * @throws ServiceException 业务异常
	 */
	private void agreeRefundOrSalesReturn(OmsOrderRefund refund) throws ServiceException {
		if(refund.getRefundInd() == OrderEnums.RefundInd.ONLY_REFUND.getValue()){
			//仅退款，直接原路退款到账户
			payService.refund(refund.getRefundNo());
		}else{
			OmsOrderRefund updateRefund = new OmsOrderRefund();
			updateRefund.setId(refund.getId());
			//退款退货,退货地址
			updateRefund.setStatus(OrderEnums.RefundDetailStatus.WAIT_BUYER_RETURN.getValue());
			UmsShopConfig config = shopConfigService.getShopConfig(refund.getShopId());
			if(config == null || StringUtils.isBlank(config.getReturnName())){
				throw new ServiceException("order.refund.address");
			}
			updateRefund.setReturnName(config.getReturnName());
			updateRefund.setReturnPhone(config.getReturnPhone());
			updateRefund.setReturnAddress(config.getReturnAddress());

			//超过该时间未寄货,自动取消售后申请
			ConfigOrder orderConfig = configOrderService.getConfigOrder();
			updateRefund.setBuyerSendGoodsTime(DateUtil.offsetDay(DateUtil.date(), orderConfig.getBuyerRefundDay()));
			//自动同意定时器
			redisDelayQueueUtil.addDelayQueue(refund.getRefundNo(), updateRefund.getBuyerSendGoodsTime(), RedisDelayQueueEnum.REFUND_AUTO_CANCEL_JOB.getCode());
//			taskTriggerService.addTrigger(RefundAutoCancelJob.class, updateRefund.getBuyerSendGoodsTime(), refund.getRefundNo());
			orderRefundMapper.updateByPrimaryKeySelective(updateRefund);
		}

		//同意售后消息
		UmsMember member = memberService.getMemberById(refund.getMemberId());
		MemberNewsForm newsForm = new MemberNewsForm(MemberNewsEnums.MemberNewsTypes.NEWS_AGREE_REFUND);
		newsForm.setShop(shopService.getShopById(refund.getShopId()));
		newsForm.initTurnParams("refundId", refund.getId().toString());
		newsForm.replace("#goodsName#", refund.getGoodsName());
		newsForm.setImg(refund.getGoodsMainImg());
		memberNewsService.addMemberNews(member, newsForm);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void refuseRefundByCustomer(SysAdmin admin, Integer refundId, String reason) throws ServiceException {
		OmsOrderRefund refund = orderRefundMapper.selectByPrimaryKey(refundId);
		if(refund.getStatus() != OrderEnums.RefundDetailStatus.SERVICE_IN.getValue()){
			throw new ServiceException("order.refund.customer.join");
		}
		OmsOrderRefund updateRefund = new OmsOrderRefund();
		updateRefund.setId(refund.getId());
		updateRefund.setStatus(OrderEnums.RefundDetailStatus.CLOSED.getValue());
		orderRefundMapper.updateByPrimaryKeySelective(updateRefund);

		OmsOrderDetail updateDetail = new OmsOrderDetail();
		updateDetail.setId(refund.getDetailId());
		updateDetail.setRefundStatus(OrderEnums.RefundStatus.REFUND_FAILED.getValue());
		orderDetailMapper.updateByPrimaryKeySelective(updateDetail);

		OmsOrder updateOrder = new OmsOrder();
		updateOrder.setId(refund.getOrderId());
		updateOrder.setRefundStatus(OrderEnums.RefundStatus.REFUND_FAILED.getValue());
		orderMapper.updateByPrimaryKeySelective(updateOrder);

		//记录
		OmsOrderRefundLog log = new OmsOrderRefundLog();
		log.setRefundId(refund.getId());
		log.setRefundNo(refund.getRefundNo());
		log.setOperatorId(admin.getId());
		log.setOperatorName(admin.getName());
		log.setHeadImg(admin.getHeadImg());
		log.setContent("客服拒绝退款:"+reason);
		log.setAddTime(DateUtil.date());
		log.setUpdTime(log.getAddTime());
		orderRefundLogMapper.insert(log);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void salesReturnByMember(UmsMember member, Integer refundId, String deliveryType, String deliveryNo) throws ServiceException {
		OmsOrderRefund refund = this.getRefundByMember(member, refundId);
		if(refund.getStatus() != OrderEnums.RefundDetailStatus.WAIT_BUYER_RETURN.getValue()){
			throw new ServiceException("order.refund.wait.delivery");
		}
		OmsOrderRefund updateRefund = new OmsOrderRefund();
		updateRefund.setId(refund.getId());
		updateRefund.setStatus(OrderEnums.RefundDetailStatus.BUYER_RETURNED.getValue());
		updateRefund.setDeliveryType(deliveryType);
		updateRefund.setDeliveryNo(deliveryNo);
		//超过该时间未收货,自动收货
		ConfigOrder orderConfig = configOrderService.getConfigOrder();
		updateRefund.setSellerConfirmTime(DateUtil.offsetDay(DateUtil.date(), orderConfig.getSellerReceiveDay()));
		orderRefundMapper.updateByPrimaryKeySelective(updateRefund);

		//自动同意定时器
		redisDelayQueueUtil.addDelayQueue(refund.getRefundNo(), updateRefund.getSellerConfirmTime(), RedisDelayQueueEnum.REFUND_AUTO_CONFIRM_JOB.getCode());
//		taskTriggerService.addTrigger(RefundAutoConfirmJob.class, updateRefund.getSellerConfirmTime(), refund.getRefundNo());

		//记录
		OmsOrderRefundLog log = new OmsOrderRefundLog();
		log.setRefundId(refund.getId());
		log.setRefundNo(refund.getRefundNo());
		log.setOperatorId(member.getId());
		log.setOperatorName(member.getCode());
		log.setHeadImg(member.getHeadImg());
		log.setContent("买家已退货");
		log.setAddTime(DateUtil.date());
		log.setUpdTime(log.getAddTime());
		orderRefundLogMapper.insert(log);

		ChatNotice notice = new ChatNotice();
		notice.setTitle("重要! 买家已退货,请及时确认~");
		notice.setContent(JSONUtil.toJsonStr(refund));
		notice.setType(MemberRemindEnums.MemberRemindTypes.WAIT_CONFIRM_RECEIVE.getType());
		UmsShop shop = shopService.getShopById(refund.getShopId());
		chatService.sendNoticeMsg(shop.getMemberId(), JSONUtil.toJsonStr(notice));
//		memberRemindService.addShopRemind(refund.getShopId(), MemberRemindEnums.MemberRemindTypes.WAIT_CONFIRM_RECEIVE, "重要! 买家已退货,请及时确认~");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void confirmReceiveByShop(UmsShop shop, Integer refundId) throws ServiceException {
		OmsOrderRefund refund = this.getRefundByShop(shop, refundId);
		if(refund.getStatus() != OrderEnums.RefundDetailStatus.BUYER_RETURNED.getValue()){
			throw new ServiceException("order.refund.wait.receive");
		}
		OmsOrderRefund updateRefund = new OmsOrderRefund();
		updateRefund.setId(refund.getId());
		updateRefund.setStatus(OrderEnums.RefundDetailStatus.REFUND_SUS.getValue());
		orderRefundMapper.updateByPrimaryKeySelective(updateRefund);

		//退款
		payService.refund(refund.getRefundNo());

		this.refundSusForUpdateOrder(refund);

		//记录
		OmsOrderRefundLog log = new OmsOrderRefundLog();
		log.setRefundId(refund.getId());
		log.setRefundNo(refund.getRefundNo());
		log.setOperatorId(shop.getId());
		log.setOperatorName(shop.getName());
		log.setHeadImg(shop.getLogo());
		log.setContent("卖家已收货,退款成功");
		log.setAddTime(DateUtil.date());
		log.setUpdTime(log.getAddTime());
		orderRefundLogMapper.insert(log);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void autoAgreeRefund(String refundNo) throws ServiceException {
		OmsOrderRefund refund = this.getRefundByRefundNo(refundNo);
		if(refund != null && refund.getStatus() == OrderEnums.RefundDetailStatus.APPLY.getValue()){
			//同意退款
			this.agreeRefundOrSalesReturn(refund);
			//记录
			OmsOrderRefundLog log = new OmsOrderRefundLog();
			log.setRefundId(refund.getId());
			log.setRefundNo(refund.getRefundNo());
			log.setOperatorId(refund.getShopId());
			log.setOperatorName(refund.getShopName());
			log.setContent("商家超时未处理,自动同意退款");
			log.setAddTime(DateUtil.date());
			log.setUpdTime(log.getAddTime());
			orderRefundLogMapper.insert(log);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void autoCancelRefund(String refundNo) {
		OmsOrderRefund refund = this.getRefundByRefundNo(refundNo);
		if(refund != null && refund.getStatus() == OrderEnums.RefundDetailStatus.WAIT_BUYER_RETURN.getValue()){
			OmsOrderRefund updateRefund = new OmsOrderRefund();
			updateRefund.setId(refund.getId());
			updateRefund.setStatus(OrderEnums.RefundDetailStatus.CLOSED.getValue());
			orderRefundMapper.updateByPrimaryKeySelective(updateRefund);

			OmsOrderDetail updateDetail = new OmsOrderDetail();
			updateDetail.setId(refund.getDetailId());
			updateDetail.setRefundStatus(OrderEnums.RefundStatus.REFUND_FAILED.getValue());
			orderDetailMapper.updateByPrimaryKeySelective(updateDetail);

			OmsOrder updateOrder = new OmsOrder();
			updateOrder.setId(refund.getOrderId());
			updateOrder.setRefundStatus(OrderEnums.RefundStatus.REFUND_FAILED.getValue());
			orderMapper.updateByPrimaryKeySelective(updateOrder);

			//记录
			OmsOrderRefundLog log = new OmsOrderRefundLog();
			log.setRefundId(refund.getId());
			log.setRefundNo(refund.getRefundNo());
			log.setOperatorId(refund.getMemberId());
			log.setOperatorName(refund.getMemberName());
			log.setHeadImg(refund.getHeadImg());
			log.setContent("买家超时未寄货,退款申请自动取消");
			log.setAddTime(DateUtil.date());
			log.setUpdTime(log.getAddTime());
			orderRefundLogMapper.insert(log);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void autoConfirmRefund(String refundNo) {
		OmsOrderRefund refund = this.getRefundByRefundNo(refundNo);
		if(refund != null && refund.getStatus() == OrderEnums.RefundDetailStatus.BUYER_RETURNED.getValue()){
			OmsOrderRefund updateRefund = new OmsOrderRefund();
			updateRefund.setId(refund.getId());
			updateRefund.setStatus(OrderEnums.RefundDetailStatus.REFUND_SUS.getValue());
			orderRefundMapper.updateByPrimaryKeySelective(updateRefund);

			this.refundSusForUpdateOrder(refund);

			//记录
			OmsOrderRefundLog log = new OmsOrderRefundLog();
			log.setRefundId(refund.getId());
			log.setRefundNo(refund.getRefundNo());
			log.setOperatorId(refund.getShopId());
			log.setOperatorName(refund.getShopName());
			log.setContent("商家超时未处理,自动确认收货,退款成功");
			log.setAddTime(DateUtil.date());
			log.setUpdTime(log.getAddTime());
			orderRefundLogMapper.insert(log);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void cancelRefund(UmsMember member, Integer refundId) throws ServiceException {
		OmsOrderRefund refund = this.getRefundByMember(member, refundId);
		if(!CollectionUtil.newArrayList(
				OrderEnums.RefundDetailStatus.APPLY.getValue(),
				OrderEnums.RefundDetailStatus.WAIT_BUYER_RETURN.getValue(),
				OrderEnums.RefundDetailStatus.BUYER_RETURNED.getValue(),
				OrderEnums.RefundDetailStatus.SALES_REFUSE.getValue(),
				OrderEnums.RefundDetailStatus.SERVICE_IN.getValue()
		).contains(refund.getStatus())){
			throw new ServiceException("order.refund.status.error");
		}
		OmsOrderRefund updateRefund = new OmsOrderRefund();
		updateRefund.setId(refund.getId());
		updateRefund.setStatus(OrderEnums.RefundDetailStatus.CLOSED.getValue());
		orderRefundMapper.updateByPrimaryKeySelective(updateRefund);

		OmsOrderDetail updateDetail = new OmsOrderDetail();
		updateDetail.setId(refund.getDetailId());
		updateDetail.setRefundStatus(OrderEnums.RefundStatus.REFUND_FAILED.getValue());
		orderDetailMapper.updateByPrimaryKeySelective(updateDetail);

		OmsOrder updateOrder = new OmsOrder();
		updateOrder.setId(refund.getOrderId());
		updateOrder.setRefundStatus(OrderEnums.RefundStatus.REFUND_FAILED.getValue());
		orderMapper.updateByPrimaryKeySelective(updateOrder);

		//记录
		OmsOrderRefundLog log = new OmsOrderRefundLog();
		log.setRefundId(refund.getId());
		log.setRefundNo(refund.getRefundNo());
		log.setOperatorId(refund.getMemberId());
		log.setOperatorName(refund.getMemberName());
		log.setHeadImg(refund.getHeadImg());
		log.setContent("买家取消退款申请");
		log.setAddTime(DateUtil.date());
		log.setUpdTime(log.getAddTime());
		orderRefundLogMapper.insert(log);
	}

	@Override
	public OrderRefundResult getOrderRefundByDetailId(Integer detailId) {
		OmsOrderRefundExample example = new OmsOrderRefundExample();
		example.setOrderByClause("add_time desc");
		example.createCriteria().andDetailIdEqualTo(detailId);
		List<OmsOrderRefund> refundList = orderRefundMapper.selectByExample(example);

		if (CollectionUtil.isEmpty(refundList)) {
			return null;
		}
		OrderRefundResult result = new OrderRefundResult();
		result.setRefund(refundList.get(0));

		OmsOrderRefundLogExample logExample = new OmsOrderRefundLogExample();
		logExample.setOrderByClause("add_time desc");
		logExample.createCriteria().andRefundIdEqualTo(refundList.get(0).getId());
		result.setRefundLogList(orderRefundLogMapper.selectByExample(logExample));
		return result;
	}

	@Override
	public OmsOrderRefund getOrderRefundByRefundNo(String refundNo) {
		OmsOrderRefundExample example = new OmsOrderRefundExample();
		example.createCriteria().andRefundNoEqualTo(refundNo);
		List<OmsOrderRefund> refundList = orderRefundMapper.selectByExample(example);
		return CollectionUtil.isNotEmpty(refundList) ? refundList.get(0) : null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void refundSusCallback(OmsOrderRefund refund) {
		OmsOrderRefund updateRefund = new OmsOrderRefund();
		updateRefund.setId(refund.getId());
		updateRefund.setStatus(OrderEnums.RefundDetailStatus.REFUND_SUS.getValue());
		orderRefundMapper.updateByPrimaryKeySelective(updateRefund);

		this.refundSusForUpdateOrder(refund);
	}

	private OmsOrderRefund getRefundByShop(UmsShop shop, Integer refundId) throws ServiceException {
		OmsOrderRefundExample example = new OmsOrderRefundExample();
		example.createCriteria().andShopIdEqualTo(shop.getId()).andIdEqualTo(refundId);
		List<OmsOrderRefund> list = orderRefundMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(list)){
			throw new ServiceException("order.refund.wait.noexist");
		}
		return list.get(0);
	}

	private OmsOrderRefund getRefundByMember(UmsMember member, Integer refundId) throws ServiceException {
		OmsOrderRefundExample example = new OmsOrderRefundExample();
		example.createCriteria().andMemberIdEqualTo(member.getId()).andIdEqualTo(refundId);
		List<OmsOrderRefund> list = orderRefundMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(list)){
			throw new ServiceException("order.refund.wait.noexist");
		}
		return list.get(0);
	}

	private OmsOrderRefund getRefundByRefundNo(String refundNo){
		OmsOrderRefundExample example = new OmsOrderRefundExample();
		example.createCriteria().andRefundNoEqualTo(refundNo);
		List<OmsOrderRefund> list = orderRefundMapper.selectByExample(example);
		return CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
	}
}