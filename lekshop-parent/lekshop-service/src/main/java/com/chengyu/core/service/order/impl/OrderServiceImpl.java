package com.chengyu.core.service.order.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSONArray;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.*;
import com.chengyu.core.domain.form.*;
import com.chengyu.core.domain.result.OrderGroupResult;
import com.chengyu.core.domain.result.OrderPayResult;
import com.chengyu.core.domain.result.OrderResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.*;
import com.chengyu.core.model.*;
import com.chengyu.core.service.config.ConfigMissionService;
import com.chengyu.core.service.config.ConfigOrderService;
import com.chengyu.core.service.funds.MemberAccountLogService;
import com.chengyu.core.service.goods.GoodsService;
import com.chengyu.core.service.member.*;
import com.chengyu.core.service.order.*;
import com.chengyu.core.service.schedule.job.OrderAutoCancelJob;
import com.chengyu.core.service.schedule.job.OrderAutoCommentJob;
import com.chengyu.core.service.shop.ShopConfigService;
import com.chengyu.core.service.shop.ShopFreightService;
import com.chengyu.core.service.shop.ShopService;
import com.chengyu.core.service.task.TaskTriggerService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @title  订单
 * @author LeGreen
 * @date   2022/4/29
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OmsOrderMapper orderMapper;
	@Autowired
	private OmsOrderDetailMapper orderDetailMapper;
	@Autowired
	private PmsGoodsSkuMapper goodsSkuMapper;
	@Autowired
	private ConfigOrderService configOrderService;
	@Autowired
	private PmsGoodsMapper goodsMapper;
	@Autowired
	private MemberCouponService memberCouponService;
	@Autowired
	private ShopFreightService shopFreightService;
	@Autowired
	private ShopConfigService shopConfigService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private OmsPayOrderMapper payOrderMapper;
	@Autowired
	private TaskTriggerService taskTriggerService;
	@Autowired
	private MemberAddressService memberAddressService;
	@Autowired
	private OrderCommentService orderCommentService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private MemberAccountLogService memberAccountLogService;
	@Autowired
	private OrderFreightService orderFreightService;
	@Autowired
	private PmsGoodsGroupMapper goodsGroupMapper;
	@Autowired
	private CarService carService;
	@Autowired
	private OrderGroupService orderGroupService;
	@Autowired
	private MemberRemindService memberRemindService;
	@Autowired
	private MemberNewsService memberNewsService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private OmsOrderCommentMapper orderCommentMapper;
	@Autowired
	private OmsOrderRefundMapper orderRefundMapper;
	@Autowired
	private ConfigMissionService configMissionService;

	@Override
	public CommonPage<OrderResult> getOrderList(OrderSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		OmsOrderExample example = new OmsOrderExample();
		if(StringUtils.isNotBlank(form.getSort())){
			example.setOrderByClause(form.getSort());
		}else{
			example.setOrderByClause("add_time desc");
		}
		OmsOrderExample.Criteria criteria = example.createCriteria();
		criteria.andDelIndEqualTo(CommonConstant.NO_INT);
		if(StringUtils.isNotBlank(form.getOrderNo())){
			criteria.andOrderNoLike("%"+form.getOrderNo()+"%");
		}
		if(form.getMemberId() != null){
			criteria.andMemberIdEqualTo(form.getMemberId());
		}
		if(form.getShopId() != null){
			criteria.andShopIdEqualTo(form.getShopId());
		}
		if(StringUtils.isNotBlank(form.getMemberName())){
			criteria.andMemberNameLike("%"+form.getMemberName()+"%");
		}
		if(form.getStatus() != null){
			criteria.andStatusEqualTo(form.getStatus());
		}
		if(CollectionUtil.isNotEmpty(form.getStatusList())){
			criteria.andStatusIn(form.getStatusList());
		}
		if(form.getDateFrom() != null && form.getDateTo() != null){
			criteria.andPayTimeBetween(form.getDateFrom(), form.getDateTo());
		}
		if(form.getType() != null){
			criteria.andTypeEqualTo(form.getType());
		}
		List<OmsOrder> list = orderMapper.selectByExample(example);

		List<OrderResult> orderList = new ArrayList<>();
		for(OmsOrder order : list){
			OrderResult orderResult = new OrderResult();
			orderResult.setOrder(order);

			OmsOrderDetailExample detailExample = new OmsOrderDetailExample();
			detailExample.createCriteria().andOrderIdEqualTo(order.getId());
			orderResult.setOrderDetailList(orderDetailMapper.selectByExample(detailExample));

			if(order.getType() == GoodsEnums.GoodsType.GROUP_GOODS.getValue() && order.getGroupId() != -1){
				OrderGroupResult orderGroup = orderGroupService.getOrderGroupByOrderNo(order.getOrderNo());
				orderResult.setOrderGroup(orderGroup.getOrderGroup());
				orderResult.setGroupMemberList(orderGroup.getGroupMemberList());
			}
			orderList.add(orderResult);
		}

		PageInfo orderPage = new PageInfo<>(list);
		orderPage.setList(orderList);
		return CommonPage.restPage(orderPage);
	}

	@Override
	public OrderResult getOrder(Integer orderId) {
		OrderResult orderResult = new OrderResult();
		orderResult.setOrder(orderMapper.selectByPrimaryKey(orderId));

		OmsOrderDetailExample detailExample = new OmsOrderDetailExample();
		detailExample.createCriteria().andOrderIdEqualTo(orderId);
		orderResult.setOrderDetailList(orderDetailMapper.selectByExample(detailExample));
		return orderResult;
	}

	@Override
	@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
	public OrderPayResult addOrder(UmsMember member, UmsMemberAddress address, List<OrderAddForm> addFormList) throws ServiceException {
		if(address == null){
			throw new ServiceException("请选择收货地址");
		}
		//生成支付订单
		ConfigOrder orderConfig = configOrderService.getConfigOrder();
		Date now = DateUtil.date();
		OmsPayOrder payOrder = new OmsPayOrder();
		payOrder.setPayOrderNo(StringUtils.genTradeNo(member.getId()));
		payOrder.setMemberId(member.getId());
		payOrder.setMemberName(member.getCode());
		payOrder.setAddTime(now);
		payOrder.setUpdTime(now);
		payOrder.setPayEndTime(DateUtil.offsetMinute(now, orderConfig.getAutoCancelMinutes()));
		payOrder.setStatus(CommonConstant.WAIT_INT);

		List<String> orderNoList = new ArrayList<>();
		BigDecimal payAmount = BigDecimal.ZERO;
		for(OrderAddForm form : addFormList){
			List<OrderBuyDetailForm> buyDetailFormList = JSONArray.parseArray(form.getGoodsDetail(), OrderBuyDetailForm.class);
			if(CollectionUtil.isEmpty(buyDetailFormList)){
				throw new ServiceException("请选择购买的商品");
			}
			List<OmsOrderDetail> detailList = new ArrayList<>();
			List<Integer> goodsIdList = new ArrayList<>();
			List<Integer> goodsCateIdList = new ArrayList<>();
			List<CaculateFreightFeeForm> freightFeeFormList = new ArrayList<>();
			Integer type = null;
			for(OrderBuyDetailForm buyDetailForm : buyDetailFormList){
				PmsGoodsSku sku = goodsSkuMapper.selectByPrimaryKey(buyDetailForm.getSkuId());
				PmsGoods goods = goodsMapper.selectByPrimaryKey(sku.getGoodsId());
				type = goods.getType();
				//校验库存
				if(sku.getStock() < buyDetailForm.getNum()){
					throw new ServiceException("「"+goods.getTitle()+"」库存不足");
				}
				if(type == GoodsEnums.GoodsType.SECKILL_GOODS.getValue()){
					//秒杀商品的校验
					if(goods.getSeckillBeginTime().after(now)){
						throw new ServiceException("未到秒杀时间");
					}
					if(goods.getSeckillEndTime().before(now)){
						throw new ServiceException("秒杀已结束");
					}
					if(buyDetailForm.getNum() > goods.getSeckillLimitNum()){
						throw new ServiceException("商品限购"+goods.getSeckillLimitNum()+"件");
					}
				}else if(type == GoodsEnums.GoodsType.GROUP_GOODS.getValue()){
					//团购商品的校验
					if(goods.getGroupSingleBuy() == CommonConstant.NO_INT && form.getGroupId() == -1){
						throw new ServiceException("该商品不支持单独购买");
					}
					if(buyDetailForm.getNum() > goods.getGroupLimitBuy()){
						throw new ServiceException("商品限购"+goods.getGroupLimitBuy()+"件");
					}
				}
				OmsOrderDetail detail = new OmsOrderDetail();
				detail.setMemberId(member.getId());
				detail.setMemberName(member.getCode());
				detail.setMemberHeadImg(member.getHeadImg());
				detail.setShopId(goods.getShopId());
				detail.setShopName(goods.getShopName());
				detail.setGoodsId(goods.getId());
				detail.setGoodsSkuId(sku.getId());
				detail.setGoodsName(goods.getTitle());
				detail.setGoodsMainImg(goods.getMainImg());
				detail.setGoodsParamName(sku.getAttrSymbolName());
				detail.setBuyOriPrice(sku.getOriPrice());
				//如果是拼团模式
				if(goods.getType() == GoodsEnums.GoodsType.GROUP_GOODS.getValue()){
					detail.setGroupId(form.getGroupId());
					detail.setJoinGroupId(form.getJoinGroupId());
					if(form.getGroupId() == -1){
						//单独购买
						detail.setBuyPrice(sku.getOriPrice());
						//单独购买的话,订单为普通订单
						type = GoodsEnums.GoodsType.NORMAL_GOODS.getValue();
					}else if(form.getGroupId() == 0){
						//普通拼团
						detail.setGroupNum(goods.getGroupNum());
						detail.setBuyPrice(sku.getPrice());
					}else{
						//阶梯拼团
						PmsGoodsGroup group = goodsGroupMapper.selectByPrimaryKey(form.getGroupId());
						detail.setGroupNum(group.getNum());
						detail.setBuyPrice(NumberUtil.mul(sku.getPrice(), NumberUtil.div(group.getDiscounts(), 100)));
					}
					detail.setGroupLimitHours(goods.getGroupLimitHours());
				}else{
					detail.setBuyPrice(sku.getPrice());
				}
				detail.setBuyNum(buyDetailForm.getNum());
				detail.setStockType(goods.getStockType());
				detail.setRefundAmount(BigDecimal.ZERO);
				detail.setBuyTotalPrice(NumberUtil.mul(detail.getBuyPrice(), buyDetailForm.getNum()));
				detail.setStatus(OrderEnums.OrderStatus.WAIT_PAY.getValue());
				detail.setRefundStatus(OrderEnums.RefundStatus.UN_REFUND.getValue());
				detail.setRemark(form.getRemark());
				detail.setCommentStatus(OrderEnums.CommentStatus.WAIT_COMMENT.getValue());
				detail.setCommentExpiredTime(DateUtil.offsetDay(now, orderConfig.getAutoCommentDay()));
				detail.setAddTime(now);
				detail.setUpdTime(now);
				goodsIdList.add(goods.getId());
				goodsCateIdList.add(goods.getCateId());

				CaculateFreightFeeForm freightForm = new CaculateFreightFeeForm(goods.getFreightTemplateId(), buyDetailForm.getNum(), sku.getWeight());
				freightFeeFormList.add(freightForm);
				detailList.add(detail);
			}
			OmsOrder order = new OmsOrder();
			order.setPayOrderNo(payOrder.getPayOrderNo());
			order.setOrderNo(StringUtils.genOrderNo(member.getId()));
			order.setType(type);
			order.setGroupId(form.getGroupId());
			order.setMemberId(member.getId());
			order.setMemberName(member.getCode());
			order.setMemberNickname(member.getNickname());
			order.setMemberHeadImg(member.getHeadImg());
			order.setShopId(detailList.get(0).getShopId());
			order.setShopName(detailList.get(0).getShopName());
			order.setCouponId(form.getCouponId());
			order.setCouponStatus(form.getCouponId() == null ? CommonConstant.NO_INT : CommonConstant.YES_INT);
			//校验优惠券
			BigDecimal totalPrice = detailList.stream().map(OmsOrderDetail::getBuyTotalPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
			order.setTotalPrice(totalPrice);
			BigDecimal couponAmount = memberCouponService.validateCoupon(member, order.getCouponId(), totalPrice, goodsIdList, goodsCateIdList);
			order.setCouponAmount(couponAmount);
			//计算运费
			order.setFreightFee(this.caculateFreightFee(order.getShopId(), address, freightFeeFormList));
			order.setPayPrice(NumberUtil.sub(NumberUtil.add(order.getTotalPrice(), order.getFreightFee()), couponAmount));
			order.setRefundAmount(BigDecimal.ZERO);
			order.setBuyTime(now);
			order.setPayEndTime(payOrder.getPayEndTime());
			order.setStatus(OrderEnums.OrderStatus.WAIT_PAY.getValue());
			order.setRefundStatus(OrderEnums.RefundStatus.UN_REFUND.getValue());
			//收货地址
			order.setReceiveName(address.getRealname());
			order.setReceivePhone(address.getPhone());
			order.setReceiveAddress(address.getProvince()+address.getCity()+address.getArea()+address.getAddress());
			order.setReceiveLng(address.getLng());
			order.setReceiveLat(address.getLat());
			order.setRemark(form.getRemark());
			order.setAddTime(now);
			order.setUpdTime(now);
			//判断是新客单还是老客单
			OmsOrderExample example = new OmsOrderExample();
			example.createCriteria()
					.andMemberIdEqualTo(order.getMemberId())
					.andAddTimeLessThan(new Date()).andStatusIn(CollectionUtil.newArrayList(1,2,3));
			order.setNewOrOld(orderMapper.countByExample(example) > 0 ? 1 : 0);
			orderMapper.insertSelective(order);
			orderNoList.add(order.getOrderNo());
			payAmount = NumberUtil.add(payAmount, order.getPayPrice());

			for(OmsOrderDetail detail : detailList){
				detail.setOrderId(order.getId());
				detail.setOrderNo(order.getOrderNo());
				orderDetailMapper.insertSelective(detail);

				//如果是下单减库存的话,即可减库存
				if(detail.getStockType() == GoodsEnums.StockType.ORDER_REDUCE.getValue()){
					goodsService.reduceStock(detail.getGoodsSkuId(), detail.getBuyNum());
				}
			}

			if(StringUtils.isNotBlank(form.getCarIds())){
				carService.deleteCar(member, Convert.convert(new TypeReference<List<Integer>>() {}, form.getCarIds().split(CommonConstant.DH_REGEX)));
			}
		}
		payOrder.setAmount(payAmount);
		payOrderMapper.insertSelective(payOrder);

		//超时未支付自动取消
		taskTriggerService.addTrigger(OrderAutoCancelJob.class, payOrder.getPayEndTime(), payOrder.getPayOrderNo());

		OrderPayResult result = new OrderPayResult();
		result.setAmount(payOrder.getAmount());
		result.setPayOrderNo(payOrder.getPayOrderNo());
		result.setOrderNoList(orderNoList);
		return result;
	}

	/**
	 * 计算商品运费
	 * @author LeGreen
	 * @date   2022/5/5
	 * @param  address
	 * @param  freightFeeFormList
	 * @return BigDecimal
	 */
	@Override
	public BigDecimal caculateFreightFee(Integer shopId, UmsMemberAddress address, List<CaculateFreightFeeForm> freightFeeFormList) {
		//运费组合策略
		UmsShopConfig config = shopConfigService.getShopConfig(shopId);
		BigDecimal fee = BigDecimal.ZERO;
		for(CaculateFreightFeeForm feeForm : freightFeeFormList){
			BigDecimal amount = shopFreightService.caculateFreightFee(feeForm.getFreightId(), address.getProvinceCode(), address.getCityCode(), feeForm.getNum(), feeForm.getWeight());
			if(config.getDeliveryFeeType() == ShopEnums.DeliveryFeeType.ADD.getValue()){
				//运费叠加
				fee = NumberUtil.add(fee, amount);
			}else if(config.getDeliveryFeeType() == ShopEnums.DeliveryFeeType.LOWEST.getValue()){
				//取最低
				fee = fee.compareTo(amount) < 0 ? fee : amount;
			}else{
				//取最高
				fee = fee.compareTo(amount) > 0 ? fee : amount;
			}
		}
		return fee;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void paySus(String payOrderNo) throws ServiceException {
		OmsPayOrderExample payOrderExample = new OmsPayOrderExample();
		payOrderExample.createCriteria().andPayOrderNoEqualTo(payOrderNo).andStatusEqualTo(CommonConstant.WAIT_INT);
		List<OmsPayOrder> payOrderList = payOrderMapper.selectByExample(payOrderExample);
		if(CollectionUtil.isNotEmpty(payOrderList)){
			OmsPayOrder updatePayOrder = new OmsPayOrder();
			updatePayOrder.setId(payOrderList.get(0).getId());
			updatePayOrder.setStatus(CommonConstant.SUS_INT);
			updatePayOrder.setUpdTime(DateUtil.date());
			updatePayOrder.setPayTime(updatePayOrder.getUpdTime());
			payOrderMapper.updateByPrimaryKeySelective(updatePayOrder);

			//支付成功, 更新状态
			OmsOrderExample orderExample = new OmsOrderExample();
			orderExample.createCriteria().andPayOrderNoEqualTo(payOrderNo).andStatusEqualTo(OrderEnums.OrderStatus.WAIT_PAY.getValue());
			List<OmsOrder> orderList = orderMapper.selectByExample(orderExample);

			OmsOrderDetailExample detailExample = new OmsOrderDetailExample();
			detailExample.createCriteria().andOrderIdIn(orderList.stream().map(OmsOrder::getId).collect(Collectors.toList())).andStatusEqualTo(OrderEnums.OrderStatus.WAIT_PAY.getValue());
			List<OmsOrderDetail> detailList = orderDetailMapper.selectByExample(detailExample);

			for(OmsOrderDetail detail : detailList){
				//减库存
				if(detail.getStockType() == GoodsEnums.StockType.PAY_REDUCE.getValue()){
					goodsService.reduceStock(detail.getGoodsSkuId(), detail.getBuyNum());
				}
			}
			OmsOrder updateOrder = new OmsOrder();
			updateOrder.setStatus(OrderEnums.OrderStatus.WAIT_DELIVERY.getValue());
			updateOrder.setUpdTime(DateUtil.date());
			updateOrder.setPayTime(updateOrder.getUpdTime());
			orderMapper.updateByExampleSelective(updateOrder, orderExample);

			OmsOrderDetail updateDetail = new OmsOrderDetail();
			updateDetail.setStatus(OrderEnums.OrderStatus.WAIT_DELIVERY.getValue());
			updateDetail.setUpdTime(DateUtil.date());
			orderDetailMapper.updateByExampleSelective(updateDetail, detailExample);

			//更新优惠券状态
			for(OmsOrder order : orderList){
				if(order.getCouponStatus() == CommonConstant.YES_INT && order.getCouponId() != null){
					memberCouponService.useCoupon(order.getCouponId());
				}

				memberRemindService.addShopRemind(order.getShopId(), MemberRemindEnums.MemberRemindTypes.NEW_ORDER, "重要! 您有一笔新的订单,请及时发货~");
			}

			//拼团订单
			if(orderList.get(0).getType() == GoodsEnums.GoodsType.GROUP_GOODS.getValue()){
				OmsOrderDetail detail = detailList.get(0);
				if(detail.getJoinGroupId() == null){
					//发团
					orderGroupService.launchGroup(detail);
				}else{
					//参团
					orderGroupService.joinGroup(detail.getJoinGroupId(), detail);
				}
			}
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public String delivery(UmsShop shop, List<OrderDeliveryForm> list) throws ServiceException {
		int susNum = 0, errorNum = 0;
		Date now = DateUtil.date();
		ConfigOrder config = configOrderService.getConfigOrder();
		for(OrderDeliveryForm form : list){
			if(StringUtils.isBlank(form.getDeliveryType())){
				throw new ServiceException("请选择快递公司");
			}
			OmsOrder order = orderMapper.selectByPrimaryKey(form.getOrderId());
			if(order.getStatus() != OrderEnums.OrderStatus.WAIT_DELIVERY.getValue() || StringUtils.isBlank(form.getDeliveryNo())){
				errorNum++;
			}else if(order.getType() == GoodsEnums.GoodsType.GROUP_GOODS.getValue()
					&& order.getGroupId() != -1
					&& !orderGroupService.isGroupSus(order.getOrderNo())){
				//团购订单需要拼团成功才能发货
				errorNum++;
			}else{
				susNum++;
				OmsOrder updatedOrder = new OmsOrder();
				updatedOrder.setId(order.getId());
				updatedOrder.setOrderNo(order.getOrderNo());
				updatedOrder.setDeliveryNo(form.getDeliveryNo());
				updatedOrder.setDeliveryTime(now);
				updatedOrder.setFinishExpiredTime(DateUtil.offsetDay(now, config.getAutoReceiveDay()));
				updatedOrder.setDeliveryType(form.getDeliveryType());
				updatedOrder.setStatus(OrderEnums.OrderStatus.WAIT_RECEIVE.getValue());
				orderMapper.updateByPrimaryKeySelective(updatedOrder);

				OmsOrderDetailExample detailExample = new OmsOrderDetailExample();
				detailExample.createCriteria().andOrderIdEqualTo(order.getId()).andStatusEqualTo(OrderEnums.OrderStatus.WAIT_DELIVERY.getValue());
				OmsOrderDetail updateDetail = new OmsOrderDetail();
				updateDetail.setStatus(OrderEnums.OrderStatus.WAIT_RECEIVE.getValue());
				orderDetailMapper.updateByExampleSelective(updateDetail, detailExample);

				//添加物流信息
				orderFreightService.initOrderFreight(updatedOrder);
				//添加自动确认收货
				taskTriggerService.addTrigger(OrderAutoCancelJob.class, updatedOrder.getFinishExpiredTime(), order.getOrderNo());

				//发货通知
				UmsMember member = memberService.getMemberById(order.getMemberId());
				MemberNewsForm newsForm = new MemberNewsForm(MemberNewsEnums.MemberNewsTypes.NEWS_DELIVERY_ORDER);
				newsForm.setShop(shop);
				newsForm.initTurnParams("orderId", order.getId().toString());
				OmsOrderDetail firstGoods = this.getGoodsByOrderId(order.getId());
				newsForm.replace("#goodsName#", firstGoods.getGoodsName());
				newsForm.setImg(firstGoods.getGoodsMainImg());
				memberNewsService.addMemberNews(member, newsForm);
			}
		}
		return "批量发货成功"+susNum+"条, 失败"+errorNum+"条";
	}

	/**
	 * 查询第一条商品
	 * @author LeGreen
	 * @date   2022/5/25
	 * @param  orderId
	 * @return String
	 */
	private OmsOrderDetail getGoodsByOrderId(Integer orderId){
		OmsOrderDetailExample detailExample = new OmsOrderDetailExample();
		detailExample.setOrderByClause("add_time desc");
		detailExample.createCriteria().andOrderIdEqualTo(orderId);
		List<OmsOrderDetail> detailList = orderDetailMapper.selectByExample(detailExample);
		return detailList.get(0);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void confirmOrder(UmsMember member, Integer orderId) throws ServiceException {
		OmsOrder order = this.getOrderByMember(member, orderId);
		if(order.getStatus() != OrderEnums.OrderStatus.WAIT_RECEIVE.getValue()){
			throw new ServiceException("待收货订单才能确认收货");
		}
		this.finishOrder(order);
	}

	private void finishOrder(OmsOrder order) throws ServiceException {
		ConfigOrder config = configOrderService.getConfigOrder();

		OmsOrder updateOrder = new OmsOrder();
		updateOrder.setId(order.getId());
		updateOrder.setFinishTime(DateUtil.date());
		updateOrder.setStatus(OrderEnums.OrderStatus.FINISHED.getValue());
		updateOrder.setCommentExpiredTime(DateUtil.offsetDay(updateOrder.getFinishTime(), config.getAutoCommentDay()));
		updateOrder.setRefundExpiredTime(DateUtil.offsetDay(updateOrder.getFinishTime(), config.getLastRefundDay()));
		orderMapper.updateByPrimaryKeySelective(updateOrder);

		OmsOrderDetailExample detailExample = new OmsOrderDetailExample();
		detailExample.createCriteria().andOrderIdEqualTo(order.getId()).andStatusEqualTo(OrderEnums.OrderStatus.WAIT_RECEIVE.getValue());
		List<OmsOrderDetail> detailList = orderDetailMapper.selectByExample(detailExample);
		OmsOrderDetail updateDetail = new OmsOrderDetail();
		updateDetail.setStatus(OrderEnums.OrderStatus.FINISHED.getValue());
		updateDetail.setCommentExpiredTime(updateOrder.getCommentExpiredTime());
		orderDetailMapper.updateByExampleSelective(updateDetail, detailExample);

		//商家获得商品款项
		UmsMember shopMember = shopService.getMemberByShopId(order.getShopId());
		memberAccountLogService.inAccount(shopMember, AccountEnums.MemberAccountTypes.ACCOUNT_TRADE_IN, order.getOrderNo(), order.getPayPrice(),
				"商品交易收款", null);

		//分销收益
		UmsMember member = memberService.getMemberById(order.getMemberId());
		this.settleDistribution(member, order.getOrderNo(), order.getPayPrice());
		//添加自动评价定时器
		orderCommentService.initComment(detailList);
		taskTriggerService.addTrigger(OrderAutoCommentJob.class, updateOrder.getCommentExpiredTime(), order.getOrderNo());

		//订单评价提醒
		UmsShop shop = shopService.getShopById(order.getShopId());
		OmsOrderDetail firstGoods = detailList.get(0);
		MemberNewsForm newsForm = new MemberNewsForm(MemberNewsEnums.MemberNewsTypes.NEWS_COMMENT_REMIND);
		newsForm.setShop(shop);
		newsForm.initTurnParams("orderId", order.getId().toString());
		newsForm.replace("#goodsName#", firstGoods.getGoodsName());
		newsForm.setImg(firstGoods.getGoodsMainImg());
		memberNewsService.addMemberNews(member, newsForm);

		//订单完成消息
		newsForm = new MemberNewsForm(MemberNewsEnums.MemberNewsTypes.NEWS_FINISH_ORDER);
		newsForm.setShop(shopService.getShopById(order.getShopId()));
		newsForm.initTurnParams("orderId", order.getId().toString());
		newsForm.replace("#goodsName#", firstGoods.getGoodsName());
		newsForm.setImg(firstGoods.getGoodsMainImg());
		memberNewsService.addMemberNews(member, newsForm);
	}

	private void settleDistribution(UmsMember member, String orderNo, BigDecimal payPrice) throws ServiceException {
		if(member.getTjrId() != null) {
			UmsMember tjMember1 = memberService.getMemberById(member.getTjrId());
			ConfigMissionDetail buyerConfigMissionDetail = configMissionService.getConfigMissionDetail(tjMember1.getMissionConfigId(), tjMember1.getGroupId());
			if (buyerConfigMissionDetail != null && DateUtil.between(member.getAddTime(), DateUtil.date(), DateUnit.DAY) <= buyerConfigMissionDetail.getBuyerValidDay()) {
				BigDecimal reward = (buyerConfigMissionDetail.getMissionType() == null || buyerConfigMissionDetail.getMissionType() == 1) ?
						NumberUtil.mul(payPrice, buyerConfigMissionDetail.getBuyerFinishRateOne().divide(new BigDecimal(100)))
						: buyerConfigMissionDetail.getBuyerFinishRateOne();
				if (reward.compareTo(BigDecimal.ZERO) > 0) {
					memberAccountLogService.inAccount(tjMember1, AccountEnums.MemberAccountTypes.ACCOUNT_SPREAD, orderNo,
							reward,"下级消费获得佣金", null);
				}
				if (tjMember1.getTjrId() != null) {
					UmsMember tjMember2 = memberService.getMemberById(tjMember1.getTjrId());
					ConfigMissionDetail buyerConfigMissionDetail2 = configMissionService.getConfigMissionDetail(tjMember2.getMissionConfigId(), tjMember2.getGroupId());
					if (buyerConfigMissionDetail2 != null) {
						reward = (buyerConfigMissionDetail2.getMissionType() == null || buyerConfigMissionDetail2.getMissionType() == 1) ?
								NumberUtil.mul(reward, buyerConfigMissionDetail2.getBuyerFinishRateTwo().divide(new BigDecimal(100)))
								: buyerConfigMissionDetail2.getBuyerFinishRateTwo();
						if (reward.compareTo(BigDecimal.ZERO) > 0) {
							memberAccountLogService.inAccount(tjMember2, AccountEnums.MemberAccountTypes.ACCOUNT_SPREAD, orderNo,
									reward,"二级消费获得佣金", null);
						}
					}
					if (tjMember2.getTjrId() != null) {
						UmsMember tjMember3 = memberService.getMemberById(tjMember2.getTjrId());
						ConfigMissionDetail buyerConfigMissionDetail3 = configMissionService.getConfigMissionDetail(tjMember3.getMissionConfigId(), tjMember3.getGroupId());
						if (buyerConfigMissionDetail3 != null) {
							reward = (buyerConfigMissionDetail3.getMissionType() == null || buyerConfigMissionDetail3.getMissionType() == 1) ?
									NumberUtil.mul(reward, buyerConfigMissionDetail3.getBuyerFinishRateThree().divide(new BigDecimal(100)))
									: buyerConfigMissionDetail3.getBuyerFinishRateThree();
							if (reward.compareTo(BigDecimal.ZERO) > 0) {
								memberAccountLogService.inAccount(tjMember2, AccountEnums.MemberAccountTypes.ACCOUNT_SPREAD, orderNo,
										reward,"三级消费获得佣金", null);
							}
						}
					}
				}
			}
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateOrderAddress(UmsMember member, Integer orderId, Integer addressId) throws ServiceException {
		OmsOrder order = this.getOrderByMember(member, orderId);
		if(order.getStatus() != OrderEnums.OrderStatus.WAIT_DELIVERY.getValue()){
			throw new ServiceException("待发货订单才能修改地址");
		}

		UmsMemberAddress address = memberAddressService.getAddressById(addressId);
		OmsOrder updateOrder = new OmsOrder();
		updateOrder.setId(orderId);
		updateOrder.setReceiveName(address.getRealname());
		updateOrder.setReceivePhone(address.getPhone());
		updateOrder.setReceiveAddress(address.getProvince()+address.getCity()+address.getArea()+address.getAddress());
		updateOrder.setReceiveLng(address.getLng());
		updateOrder.setReceiveLat(address.getLat());
		updateOrder.setUpdTime(DateUtil.date());
		orderMapper.updateByPrimaryKeySelective(updateOrder);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void cancelOrder(UmsMember member, Integer orderId) throws ServiceException {
		OmsOrder order = this.getOrderByMember(member, orderId);
		if(order.getStatus() != OrderEnums.OrderStatus.WAIT_PAY.getValue()){
			throw new ServiceException("待支付订单才能取消");
		}

		OmsOrderDetailExample detailExample = new OmsOrderDetailExample();
		detailExample.createCriteria().andMemberIdEqualTo(member.getId()).andOrderIdEqualTo(order.getId()).andStatusEqualTo(OrderEnums.OrderStatus.WAIT_PAY.getValue());
		List<OmsOrderDetail> detailList = orderDetailMapper.selectByExample(detailExample);

		for(OmsOrderDetail detail : detailList){
			if(detail.getStockType() == GoodsEnums.StockType.ORDER_REDUCE.getValue()){
				goodsService.addStock(detail.getGoodsSkuId(), detail.getBuyNum());
			}
		}
		OmsOrder updateOrder = new OmsOrder();
		updateOrder.setId(orderId);
		updateOrder.setStatus(OrderEnums.OrderStatus.CANCEL.getValue());
		updateOrder.setUpdTime(DateUtil.date());
		orderMapper.updateByPrimaryKeySelective(updateOrder);

		OmsOrderDetail updateDetail = new OmsOrderDetail();
		updateDetail.setStatus(OrderEnums.OrderStatus.CANCEL.getValue());
		updateDetail.setUpdTime(DateUtil.date());
		orderDetailMapper.updateByExampleSelective(updateDetail, detailExample);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteOrder(UmsMember member, Integer orderId) throws ServiceException {
		OmsOrder order = this.getOrderByMember(member, orderId);
		if(order.getStatus() != OrderEnums.OrderStatus.CANCEL.getValue()){
			throw new ServiceException("已取消的订单才能删除");
		}
		OmsOrder updateOrder = new OmsOrder();
		updateOrder.setId(order.getId());
		updateOrder.setDelInd(CommonConstant.YES_INT);
		updateOrder.setUpdTime(DateUtil.date());
		orderMapper.updateByPrimaryKeySelective(updateOrder);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void autoCancelOrder(String payOrderNo) {
		OmsPayOrderExample payOrderExample = new OmsPayOrderExample();
		payOrderExample.createCriteria().andPayOrderNoEqualTo(payOrderNo).andStatusEqualTo(CommonConstant.WAIT_INT);
		OmsPayOrder updatePayOrder = new OmsPayOrder();
		updatePayOrder.setStatus(CommonConstant.FAIL_INT);
		updatePayOrder.setUpdTime(DateUtil.date());
		payOrderMapper.updateByExampleSelective(updatePayOrder, payOrderExample);

		OmsOrderExample orderExample = new OmsOrderExample();
		orderExample.createCriteria().andPayOrderNoEqualTo(payOrderNo).andStatusEqualTo(OrderEnums.OrderStatus.WAIT_PAY.getValue());
		List<OmsOrder> orderList = orderMapper.selectByExample(orderExample);

		OmsOrderDetailExample detailExample = new OmsOrderDetailExample();
		detailExample.createCriteria().andOrderIdIn(orderList.stream().map(OmsOrder::getId).collect(Collectors.toList())).andStatusEqualTo(OrderEnums.OrderStatus.WAIT_PAY.getValue());
		List<OmsOrderDetail> detailList = orderDetailMapper.selectByExample(detailExample);

		for(OmsOrderDetail detail : detailList){
			if(detail.getStockType() == GoodsEnums.StockType.ORDER_REDUCE.getValue()){
				goodsService.addStock(detail.getGoodsSkuId(), detail.getBuyNum());
			}
		}
		OmsOrder updateOrder = new OmsOrder();
		updateOrder.setStatus(OrderEnums.OrderStatus.CANCEL.getValue());
		updateOrder.setUpdTime(DateUtil.date());
		orderMapper.updateByExampleSelective(updateOrder, orderExample);

		OmsOrderDetail updateDetail = new OmsOrderDetail();
		updateDetail.setStatus(OrderEnums.OrderStatus.CANCEL.getValue());
		updateDetail.setUpdTime(DateUtil.date());
		orderDetailMapper.updateByExampleSelective(updateDetail, detailExample);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void autoFinishOrder(String orderNo) throws ServiceException {
		OmsOrderExample example = new OmsOrderExample();
		example.createCriteria().andOrderNoEqualTo(orderNo);
		List<OmsOrder> list = orderMapper.selectByExample(example);
		if(CollectionUtil.isNotEmpty(list)){
			OmsOrder order = list.get(0);
			if(order.getStatus() == OrderEnums.OrderStatus.WAIT_RECEIVE.getValue()){
				//自动确认收货
				this.finishOrder(order);
			}
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteOrderByAdmin(List<Integer> idList) {
		OmsOrderExample example = new OmsOrderExample();
		example.createCriteria().andIdIn(idList);
		OmsOrder updateOrder = new OmsOrder();
		updateOrder.setDelInd(CommonConstant.YES_INT);
		orderMapper.updateByExampleSelective(updateOrder, example);
	}

	@Override
	public List<OmsOrderDetail> getBuyGoodsList(Integer memberId, String goodsName) {
		OmsOrderDetailExample example = new OmsOrderDetailExample();
		example.setOrderByClause("add_time desc");
		OmsOrderDetailExample.Criteria criteria = example.createCriteria();
		criteria.andMemberIdEqualTo(memberId).andStatusIn(OrderEnums.OrderSusStatusList());
		if(StringUtils.isNotBlank(goodsName)){
			criteria.andGoodsNameLike("%"+goodsName+"%");
		}
		return orderDetailMapper.selectByExample(example);
	}

	@Override
	public Map<String, Object> count(Integer memberId) {
		Map<String, Object> result = new HashMap<>();
		//统计待发货，待收货，待评价，退款数量
		OmsOrderExample example = new OmsOrderExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andStatusEqualTo(OrderEnums.OrderStatus.WAIT_DELIVERY.getValue());
		result.put("waitDeliveryNum", orderMapper.countByExample(example));
		example = new OmsOrderExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andStatusEqualTo(OrderEnums.OrderStatus.WAIT_RECEIVE.getValue());
		result.put("waitReceiveNum", orderMapper.countByExample(example));

		OmsOrderCommentExample commentExample = new OmsOrderCommentExample();
		commentExample.createCriteria().andMemberIdEqualTo(memberId).andStatusEqualTo(CommonConstant.NO_INT);
		result.put("waitCommentNum", orderCommentMapper.countByExample(commentExample));

		OmsOrderRefundExample refundExample = new OmsOrderRefundExample();
		refundExample.createCriteria().andMemberIdEqualTo(memberId).andStatusIn(CollectionUtil.newArrayList(
				OrderEnums.RefundDetailStatus.APPLY.getValue(),
				OrderEnums.RefundDetailStatus.WAIT_BUYER_RETURN.getValue(),
				OrderEnums.RefundDetailStatus.BUYER_RETURNED.getValue(),
				OrderEnums.RefundDetailStatus.SERVICE_IN.getValue()));
		result.put("refundNum", orderRefundMapper.countByExample(refundExample));
		return result;
	}

	/**
	 * 根据用户获取订单
	 * @author LeGreen
	 * @date   2022/5/5
	 * @param  member
	 * @param  orderId
	 * @return OmsOrder
	 */
	private OmsOrder getOrderByMember(UmsMember member, Integer orderId) throws ServiceException {
		OmsOrderExample example = new OmsOrderExample();
		example.createCriteria().andMemberIdEqualTo(member.getId()).andIdEqualTo(orderId);
		List<OmsOrder> list = orderMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(list)){
			throw new ServiceException("订单不存在");
		}
		return list.get(0);
	}

}