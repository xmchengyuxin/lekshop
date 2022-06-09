package com.chengyu.core.service.order.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.MemberNewsEnums;
import com.chengyu.core.domain.enums.OrderEnums;
import com.chengyu.core.domain.form.MemberNewsForm;
import com.chengyu.core.domain.form.OrderGroupSearchForm;
import com.chengyu.core.domain.result.OrderGroupResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.CustomOrderGroupMapper;
import com.chengyu.core.mapper.OmsOrderGroupMapper;
import com.chengyu.core.mapper.OmsOrderGroupMemberMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.service.member.MemberNewsService;
import com.chengyu.core.service.member.MemberService;
import com.chengyu.core.service.order.OrderGroupService;
import com.chengyu.core.service.pay.PayService;
import com.chengyu.core.service.schedule.job.OrderGroupAutoCancelJob;
import com.chengyu.core.service.task.TaskTriggerService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderGroupServiceImpl implements OrderGroupService {

	@Autowired
	private OmsOrderGroupMapper assembleMapper;
	@Autowired
	private OmsOrderGroupMemberMapper assembleMemberMapper;
	@Autowired
	private CustomOrderGroupMapper customGroupMapper;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberNewsService memberNewsService;
	@Autowired
	private PayService payService;
	@Autowired
	private TaskTriggerService taskTriggerService;
	
	@Override
	public List<OmsOrderGroup> getOrderGroupList(OrderGroupSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		
		OmsOrderGroupExample example = new OmsOrderGroupExample();
		example.setOrderByClause("end_time asc");
		OmsOrderGroupExample.Criteria criteria = example.createCriteria();
		if(form.getShopId() != null){
			criteria.andShopIdEqualTo(form.getShopId());
		}
		if(CollectionUtil.isNotEmpty(form.getShopIdArr())){
			criteria.andShopIdIn(form.getShopIdArr());
		}
		if(form.getGoodsId() != null){
			criteria.andGoodsIdEqualTo(form.getGoodsId());
		}
		if(form.getStatus() != null){
			criteria.andStatusEqualTo(form.getStatus());
		}
		if(StringUtils.isNotBlank(form.getShopName())){
			criteria.andShopNameLike("%"+form.getShopName()+"%");
		}
		if(form.getDateFrom() != null && form.getDateTo() != null){
			criteria.andAddTimeBetween(form.getDateFrom(), form.getDateTo());
		}
		return assembleMapper.selectByExample(example);
	}

	@Override
	public List<OmsOrderGroupMember> getGroupMemberList(Integer orderGroupId, Integer limit) {
		OmsOrderGroupMemberExample example = new OmsOrderGroupMemberExample();
		example.setOrderByClause("add_time desc limit "+limit);
		example.createCriteria().andOrderGroupIdEqualTo(orderGroupId);
		return assembleMemberMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void launchGroup(OmsOrderDetail orderDetail) throws ServiceException {
		//判断是否已发起拼团
		OmsOrderGroupMemberExample example = new OmsOrderGroupMemberExample();
		example.createCriteria().andOrderNoEqualTo(orderDetail.getOrderNo()).andMemberIdEqualTo(orderDetail.getMemberId());
		long count = assembleMemberMapper.countByExample(example);
		if(count > 0){
			throw new ServiceException("order.group.launch");
		}

		OmsOrderGroup orderGroup = new OmsOrderGroup();
		orderGroup.setOrderId(orderDetail.getOrderId());
		orderGroup.setOrderNo(orderDetail.getOrderNo());
		orderGroup.setMemberId(orderDetail.getMemberId());
		orderGroup.setMemberName(orderDetail.getMemberName());
		orderGroup.setMemberHeadImg(orderDetail.getMemberHeadImg());
		orderGroup.setShopId(orderDetail.getShopId());
		orderGroup.setShopName(orderDetail.getShopName());
		orderGroup.setGoodsId(orderDetail.getGoodsId());
		orderGroup.setGoodsName(orderDetail.getGoodsName());
		orderGroup.setGoodsMainImg(orderDetail.getGoodsMainImg());
		orderGroup.setOriginalPrice(orderDetail.getBuyOriPrice());
		orderGroup.setGroupPrice(orderDetail.getBuyPrice());
		orderGroup.setGroupNum(orderDetail.getGroupNum());
		orderGroup.setHaveGroupNum(1);
		orderGroup.setGoodsGroupId(orderDetail.getGroupId());
		orderGroup.setStatus(CommonConstant.YES_INT);
		orderGroup.setAddTime(new Date());
		orderGroup.setEndTime(cn.hutool.core.date.DateUtil.offsetHour(orderGroup.getAddTime(), orderDetail.getGroupLimitHours()));
		orderGroup.setUpdTime(orderGroup.getAddTime());
		assembleMapper.insertSelective(orderGroup);
		Integer orderGroupId = orderGroup.getId();
		
		OmsOrderGroupMember assembleMember = new OmsOrderGroupMember();
		assembleMember.setOrderGroupId(orderGroupId);
		assembleMember.setOrderId(orderDetail.getOrderId());
		assembleMember.setOrderNo(orderDetail.getOrderNo());
		assembleMember.setMemberId(orderDetail.getMemberId());
		assembleMember.setMemberName(orderDetail.getMemberName());
		assembleMember.setMemberHeadImg(orderDetail.getMemberHeadImg());
		assembleMember.setPayStatus(CommonConstant.SUS_INT);
		assembleMember.setBuyNum(orderDetail.getBuyNum());
		assembleMember.setOriginalPrice(orderDetail.getBuyOriPrice());
		assembleMember.setPayPrice(orderDetail.getBuyPrice().multiply(new BigDecimal(orderDetail.getBuyNum())));
		assembleMember.setGoodsGroupId(orderDetail.getGroupId());
		assembleMember.setAddTime(cn.hutool.core.date.DateUtil.date());
		assembleMember.setUpdTime(assembleMember.getAddTime());
		assembleMemberMapper.insertSelective(assembleMember);
		
		//发团成功后添加拼团到期定时器
		taskTriggerService.addTrigger(OrderGroupAutoCancelJob.class, orderGroup.getEndTime(), orderGroup.getId().toString());
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void joinGroup(Integer orderGroupId, OmsOrderDetail orderDetail) throws ServiceException {
		OmsOrderGroup orderGroup = this.getOrderGroupById(orderGroupId);
		if(orderGroup == null){
			throw new ServiceException("order.group.notexist");
		}
		
		if(orderGroup.getStatus() == 2) {
			throw new ServiceException("order.group.sus");
		}
		if(orderGroup.getStatus() == 3){
			throw new ServiceException("order.group.fail");
		}
		if(orderGroup.getHaveGroupNum() >= orderGroup.getGroupNum()){
			throw new ServiceException("order.group.full");
		}
		if(orderGroup.getEndTime().before(new Date())) {
			throw new ServiceException("order.group.overtime");
		}
		
		//判断用户不能再参加自己的团
		if(orderGroup.getMemberId().equals(orderDetail.getMemberId())) {
			throw new ServiceException("order.group.myself");
		}
		
		OmsOrderGroup updaGroup = new OmsOrderGroup();
		updaGroup.setId(orderGroup.getId());
		updaGroup.setHaveGroupNum(orderGroup.getHaveGroupNum()+1);
		updaGroup.setUpdTime(new Date());
		if(updaGroup.getHaveGroupNum() >= orderGroup.getGroupNum()){
			updaGroup.setStatus(2);
		}
		assembleMapper.updateByPrimaryKeySelective(updaGroup);
		
		OmsOrderGroupMember assembleMember = new OmsOrderGroupMember();
		assembleMember.setOrderGroupId(orderGroupId);
		assembleMember.setOrderNo(orderDetail.getOrderNo());
		assembleMember.setMemberId(orderDetail.getMemberId());
		assembleMember.setMemberName(orderDetail.getMemberName());
		assembleMember.setMemberHeadImg(orderDetail.getMemberHeadImg());
		assembleMember.setPayStatus(1);
		assembleMember.setAddTime(cn.hutool.core.date.DateUtil.date());
		assembleMember.setUpdTime(assembleMember.getAddTime());
		assembleMemberMapper.insertSelective(assembleMember);

		if(orderGroup.getStatus() == 2){
			//拼团成功通知所有拼团人员
			List<OmsOrderGroupMember> assembleMemberList = this.getGroupMemberList(orderGroupId, 999);
			MemberNewsForm newsForm = new MemberNewsForm(MemberNewsEnums.MemberNewsTypes.NEWS_GROUP_SUS);
			UmsShop shop = new UmsShop();
			shop.setId(orderGroup.getShopId());
			shop.setName(orderGroup.getShopName());
			newsForm.setShop(shop);
			newsForm.initTurnParams("orderId", orderGroup.getOrderId().toString());
			newsForm.replace("#goodsName#", orderGroup.getGoodsName());
			newsForm.setImg(orderGroup.getGoodsMainImg());
			for(OmsOrderGroupMember oam : assembleMemberList){
				//拼团成功通知
				UmsMember member = memberService.getMemberById(oam.getMemberId());
				memberNewsService.addMemberNews(member, newsForm);
			}
		}
	}

	@Override
	public List<OmsOrderGroup> getOrderGroupPageByMemberId(Integer memberId, Integer status, Integer page,
			Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		return customGroupMapper.getOrderGroupList(memberId, status);
	}

	@Override
	public OmsOrderGroup getOrderGroupById(Integer orderGroupId) {
		return assembleMapper.selectByPrimaryKey(orderGroupId);
	}

	@Override
	public List<OmsOrderGroupMember> getGroupMemberByOrderNo(String orderNo) {
		OmsOrderGroupMemberExample example = new OmsOrderGroupMemberExample();
		example.setOrderByClause("add_time asc");
		example.createCriteria().andOrderNoEqualTo(orderNo);
		List<OmsOrderGroupMember> list = assembleMemberMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(list)) {
			return null;
		}
		
		example = new OmsOrderGroupMemberExample();
		example.createCriteria().andOrderGroupIdEqualTo(list.get(0).getOrderGroupId());
		return assembleMemberMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void doRefundForFailPintuan(OmsOrderGroup assemble) throws ServiceException {
		OmsOrderGroup updateGroup = new OmsOrderGroup();
		updateGroup.setId(assemble.getId());
		updateGroup.setStatus(OrderEnums.GroupStatus.FAIL.getValue());
		updateGroup.setUpdTime(new Date());
		assembleMapper.updateByPrimaryKeySelective(updateGroup);
		
		List<OmsOrderGroupMember> list = this.getGroupMemberList(assemble.getId(), 9999);

		MemberNewsForm newsForm = new MemberNewsForm(MemberNewsEnums.MemberNewsTypes.NEWS_GROUP_FAIL);
		OmsOrderGroup orderGroup = this.getOrderGroupById(assemble.getId());
		UmsShop shop = new UmsShop();
		shop.setId(orderGroup.getShopId());
		shop.setName(orderGroup.getShopName());
		newsForm.setShop(shop);
		newsForm.initTurnParams("orderId", orderGroup.getOrderId().toString());
		newsForm.replace("#goodsName#", orderGroup.getGoodsName());
		newsForm.setImg(orderGroup.getGoodsMainImg());
		for(OmsOrderGroupMember assembleMember : list){
			OmsOrderGroupMember updateGroupMember = new OmsOrderGroupMember();
			updateGroupMember.setId(assembleMember.getId());
			updateGroupMember.setPayStatus(3);
			updateGroupMember.setUpdTime(new Date());
			assembleMemberMapper.updateByPrimaryKeySelective(updateGroupMember);

			payService.cancelAndRefundOrder(assembleMember.getOrderNo());

			//拼团失败消息
			UmsMember member = memberService.getMemberById(assembleMember.getMemberId());
			memberNewsService.addMemberNews(member, newsForm);

		}
		
	}

	@Override
	public long countGroupNum(Integer goodsId) {
		return customGroupMapper.countGroupNum(goodsId);
	}

	@Override
	public boolean isInGroup(Integer goodsId) {
		OmsOrderGroupExample example = new OmsOrderGroupExample();
		example.createCriteria().andGoodsIdEqualTo(goodsId).andStatusEqualTo(1);
		long count = assembleMapper.countByExample(example);
		return count > 0;
	}

	@Override
	public boolean isGroupSus(String orderNo) {
		OmsOrderGroupMemberExample example = new OmsOrderGroupMemberExample();
		example.createCriteria().andOrderNoEqualTo(orderNo);
		List<OmsOrderGroupMember> assembleMember = assembleMemberMapper.selectByExample(example);

		OmsOrderGroup assemble = this.getOrderGroupById(assembleMember.get(0).getOrderGroupId());
		return assemble.getStatus() == 2;
	}

	@Override
	public OrderGroupResult getOrderGroupByOrderNo(String orderNo) {
		OrderGroupResult result = new OrderGroupResult();
		OmsOrderGroupExample example = new OmsOrderGroupExample();
		example.createCriteria().andOrderNoEqualTo(orderNo);
		List<OmsOrderGroup> list = assembleMapper.selectByExample(example);
		result.setOrderGroup(CollectionUtil.isNotEmpty(list) ? list.get(0) : null);
		result.setGroupMemberList(this.getGroupMemberByOrderNo(orderNo));
		return result;
	}

	@Override
	public void autoCancel(Integer orderGroupId) throws ServiceException {
		OmsOrderGroup orderGroup = this.getOrderGroupById(orderGroupId);
		if(orderGroup != null && orderGroup.getStatus() != OrderEnums.GroupStatus.SUS.getValue()){
			this.doRefundForFailPintuan(orderGroup);
		}
	}


}