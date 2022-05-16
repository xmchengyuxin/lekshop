package com.chengyu.core.controller.member;

import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.UmsMemberViewGoods;
import com.chengyu.core.service.member.MemberViewGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @title  我的足迹
 * @author LeGreen
 * @date   2022/5/16
 */
@Api(tags = "我的足迹")
@Controller
@RequestMapping("/member")
public class MemberViewGoodsController extends UserBaseController {
	
	@Autowired
	private MemberViewGoodsService memberViewGoodsService;
	
	@ApiOperation(value = "足迹列表")
	@ResponseBody
	@RequestMapping(value="/viewGoods/getList", method=RequestMethod.GET)
    public CommonResult<CommonPage<UmsMemberViewGoods>> getMemberViewGoodsList(Date date, Integer page, Integer pageSize) throws Exception{
		List<UmsMemberViewGoods> list = memberViewGoodsService.getMemberViewGoodsList(getCurrentMemberId(), date, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
    }
	
	@ResponseBody
	@ApiOperation(value = "清空足迹")
	@RequestMapping(value="/viewGoods/clear", method=RequestMethod.POST)
    public CommonResult<String> clear() throws Exception{
		memberViewGoodsService.deleteViewGoodsByMemberId(getCurrentMemberId());
		return CommonResult.success(null);
    }
	
	@ApiOperation(value = "删除足迹")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "足迹ID"),
	})
	@ResponseBody
	@RequestMapping(value="/viewGoods/delete", method=RequestMethod.POST)
    public CommonResult<String> delete(Integer id) {
		memberViewGoodsService.deleteViewGoods(id);
		return CommonResult.success(null);
    }
}