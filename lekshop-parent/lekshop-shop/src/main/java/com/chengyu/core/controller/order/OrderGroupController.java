package com.chengyu.core.controller.order;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.form.OrderGroupSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OmsOrderGroup;
import com.chengyu.core.model.OmsOrderGroupMember;
import com.chengyu.core.service.order.OrderGroupService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "拼团活动")
@Controller
@RequestMapping("/member")
public class OrderGroupController extends ShopBaseController {
	
	@Autowired
	private OrderGroupService assembleService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "拼团列表")
	@ResponseBody
	@RequestMapping(value="/assemble/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<Map<String,Object>>> getList(OrderGroupSearchForm form, Integer page, Integer pageSize) throws Exception {
		List<OmsOrderGroup> assemblePage = assembleService.getOrderGroupList(form, page, pageSize);
		List<Map<String,Object>> list = Convert.convert(new TypeReference<List<Map<String,Object>>>() {}, assemblePage);
		if(list != null && !list.isEmpty()){
			for(Map<String,Object> assemble : list){
				assemble.put("memberList", assembleService.getGroupMemberList(MapUtil.getInt(assemble, "id"), 5));
			}
		}
		PageInfo pageInfo = new PageInfo<>(assemblePage);
		pageInfo.setList(list);
		return CommonResult.success(CommonPage.restPage(pageInfo));
	}
	
	@ApiOperation(value = "拼团成员")
	@ResponseBody
	@RequestMapping(value="/assemble/getMemberList", method=RequestMethod.GET)
	public CommonResult<Map<String,Object>> getMemberList(String orderNo) throws ServiceException {
		Map<String,Object> result = new HashMap<>();
		List<OmsOrderGroupMember> list = assembleService.getGroupMemberByOrderNo(orderNo);
		result.put("assembleMemberList", list);
		
		if(list != null && !list.isEmpty()){
			OmsOrderGroupMember orderAssembleMember = list.get(0);
			OmsOrderGroup assemble = assembleService.getOrderGroupById(orderAssembleMember.getOrderGroupId());
			BigDecimal percent = NumberUtil.div(assemble.getHaveGroupNum(), assemble.getGroupNum(), 2).multiply(new BigDecimal(100));
			result.put("assemblePercent", percent);
		}
		return CommonResult.success(result);
	}
}