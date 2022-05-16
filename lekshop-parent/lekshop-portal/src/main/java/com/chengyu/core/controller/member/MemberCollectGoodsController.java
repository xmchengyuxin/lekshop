package com.chengyu.core.controller.member;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMemberCollectionGoods;
import com.chengyu.core.service.member.MemberCollectGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "商品收藏")
@Controller
@RequestMapping("/member")
public class MemberCollectGoodsController extends UserBaseController {
	
	@Autowired
	private MemberCollectGoodsService memberCollectGoodsService;

	@ApiOperation(value = "商品收藏列表")
	@ResponseBody
	@RequestMapping(value={"/collectGoods/getList"}, method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberCollectionGoods>> getList(Integer page, Integer pageSize) throws ServiceException {
		List<UmsMemberCollectionGoods> list = memberCollectGoodsService.getMemberCollectionGoodsList(getCurrentMemberId(), page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}


	@OperationLog
	@ApiOperation(value = "关注取关商品")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "goodsId", value = "商品ID")
	})
	@ResponseBody
	@RequestMapping(value={"/collectGoods/collectOrNoGoods"}, method=RequestMethod.POST)
	public CommonResult<String> collectOrNoGoods(Integer goodsId) throws Exception {
		memberCollectGoodsService.collectOrNoGoods(getCurrentMember(), goodsId);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "删除收藏的商品")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID"),
	})
	@ResponseBody
	@RequestMapping(value={"/collectGoods/delete"}, method=RequestMethod.POST)
	public CommonResult<String> delete(Integer id) {
		memberCollectGoodsService.deleteCollectionGoods(id);
		return CommonResult.success(null);
	}

}