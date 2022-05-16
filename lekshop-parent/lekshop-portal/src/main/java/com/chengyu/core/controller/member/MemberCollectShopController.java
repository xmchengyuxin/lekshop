package com.chengyu.core.controller.member;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMemberCollectionShop;
import com.chengyu.core.service.member.MemberCollectShopService;
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

@Api(tags = "店铺收藏")
@Controller
@RequestMapping("/member")
public class MemberCollectShopController extends UserBaseController {
	
	@Autowired
	private MemberCollectShopService memberCollectShopService;

	@ApiOperation(value = "店铺收藏列表")
	@ResponseBody
	@RequestMapping(value={"/collectShop/getList"}, method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberCollectionShop>> getList(Integer page, Integer pageSize) throws ServiceException {
		List<UmsMemberCollectionShop> list = memberCollectShopService.getMemberCollectionShopList(getCurrentMemberId(), page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}


	@OperationLog
	@ApiOperation(value = "关注取关店铺")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "goodsId", value = "店铺ID")
	})
	@ResponseBody
	@RequestMapping(value={"/collectShop/collectOrNoShop"}, method=RequestMethod.POST)
	public CommonResult<String> collectOrNoShop(Integer shopId) throws Exception {
		memberCollectShopService.collectOrNoShop(getCurrentMember(), shopId);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "删除收藏的店铺")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID"),
	})
	@ResponseBody
	@RequestMapping(value={"/collectShop/delete"}, method=RequestMethod.POST)
	public CommonResult<String> delete(Integer id) {
		memberCollectShopService.deleteCollectionShop(id);
		return CommonResult.success(null);
	}

}