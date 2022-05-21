package com.chengyu.core.service.member.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.MemberRemindEnums.AdminRemindTypes;
import com.chengyu.core.domain.enums.MemberRemindEnums.MemberRemindTypes;
import com.chengyu.core.mapper.UmsMemberRemindMapper;
import com.chengyu.core.model.SysAdmin;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberRemind;
import com.chengyu.core.model.UmsMemberRemindExample;
import com.chengyu.core.service.member.MemberRemindService;
import com.chengyu.core.service.shop.ShopService;
import com.chengyu.core.service.system.AdminService;
import com.chengyu.core.service.system.AdminTypeService;
import com.chengyu.core.service.system.MenuService;
import com.chengyu.core.util.netty.NettyPushUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title  消息提醒
 * @author LeGreen
 * @date   2021/12/28
 */
@Service
public class MemberRemindServiceImpl implements MemberRemindService {

	@Autowired
	private UmsMemberRemindMapper memberRemindMapper;
	@Autowired
	private MenuService menuService;
	@Autowired
	private AdminTypeService adminTypeService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private NettyPushUtil nettyPushUtil;
	@Autowired
	private ShopService shopService;

	@Override
	public void addMemberRemind(UmsMember member, MemberRemindTypes type, String content) {
		UmsMemberRemind remind = new UmsMemberRemind();
		remind.setType(type.getType());
		remind.setToType(1);
		remind.setMemberId(member.getId());
		remind.setTitle(type.getTitle());
		remind.setContent(content);
		remind.setStatus(CommonConstant.NO_INT);
		remind.setAddTime(DateUtil.date());
		remind.setUpdTime(remind.getAddTime());
		memberRemindMapper.insertSelective(remind);

		Map<String,String> extras = new HashMap<>(16);
		extras.put("reqId", remind.getId().toString());
		extras.put("rid", "member-"+member.getId().toString());
		extras.put("type", type.getType().toString());
		extras.put("content", content);
		extras.put("addTime", DateUtil.current(false)+"");
		nettyPushUtil.sendMsg(JSONUtil.toJsonStr(extras));
	}

	@Override
	public void addShopRemind(Integer shopId, MemberRemindTypes type, String content) {
		UmsMember member = shopService.getMemberByShopId(shopId);
		this.addMemberRemind(member, type, content);
	}

	@Override
	public void addAdminRemind(AdminRemindTypes type, String content) {
		//查询出有此权限的后台管理人员
		Integer menuId = menuService.getMenuIdByRouter(type.getRouter());
		List<Integer> adminTypeIds = adminTypeService.getAdminTypeIds(menuId);
		List<SysAdmin> adminList = adminService.getAllAdminByTypeIds(adminTypeIds);

		if(CollectionUtil.isNotEmpty(adminList)){
			for(SysAdmin admin : adminList){
				UmsMemberRemind remind = new UmsMemberRemind();
				remind.setType(type.getType());
				remind.setToType(2);
				remind.setMemberId(admin.getId());
				remind.setTitle(type.getTitle());
				remind.setContent(content);
				remind.setStatus(CommonConstant.NO_INT);
				remind.setAddTime(DateUtil.date());
				remind.setUpdTime(remind.getAddTime());
				memberRemindMapper.insertSelective(remind);

				Map<String,String> extras = new HashMap<>(16);
				extras.put("reqId", remind.getId().toString());
				extras.put("rid", "admin-"+admin.getId());
				extras.put("type", type.getType().toString());
				extras.put("content", content);
				extras.put("addTime", DateUtil.current(false)+"");
				nettyPushUtil.sendMsg(JSONUtil.toJsonStr(extras));
			}
		}
	}

	@Override
	public List<UmsMemberRemind> getRemindList(Integer memberId, Integer toType, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		UmsMemberRemindExample example = new UmsMemberRemindExample();
		example.setOrderByClause("add_time desc");
		example.createCriteria().andMemberIdEqualTo(memberId).andToTypeEqualTo(toType);
		return memberRemindMapper.selectByExample(example);
	}

	@Override
	public long countUnReadNum(Integer memberId, Integer toType) {
		UmsMemberRemindExample example = new UmsMemberRemindExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andToTypeEqualTo(toType).andStatusEqualTo(CommonConstant.NO_INT);
		return memberRemindMapper.countByExample(example);
	}

	@Override
	public void readAll(Integer memberId, Integer toType) {
		UmsMemberRemindExample example = new UmsMemberRemindExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andToTypeEqualTo(toType).andStatusEqualTo(CommonConstant.NO_INT);

		UmsMemberRemind updateRemind = new UmsMemberRemind();
		updateRemind.setStatus(CommonConstant.YES_INT);
		updateRemind.setUpdTime(DateUtil.date());
		memberRemindMapper.updateByExampleSelective(updateRemind, example);
	}

	public static void main(String[] args) {
		System.out.println(DateUtil.current(false)+"");
	}
}