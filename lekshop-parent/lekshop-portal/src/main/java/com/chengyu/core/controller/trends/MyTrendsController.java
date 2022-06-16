package com.chengyu.core.controller.trends;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.TrendsEnums;
import com.chengyu.core.domain.form.TrendsForm;
import com.chengyu.core.domain.result.WalkTrendsResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OmsOrderDetail;
import com.chengyu.core.model.WalkTrends;
import com.chengyu.core.model.WalkTrendsCollection;
import com.chengyu.core.service.order.OrderService;
import com.chengyu.core.service.walk.WalkTrendsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "我发布的动态")
@Controller
@RequestMapping("/member")
public class MyTrendsController extends UserBaseController {
	
	@Autowired
	private WalkTrendsService walkTrendsService;
	@Autowired
	private OrderService orderService;

	@ApiOperation(value = "我的动态列表")
	@ResponseBody
	@RequestMapping(value="/trends/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<WalkTrendsResult>> getList(TrendsForm form,
															  @RequestParam(value = "page", defaultValue = "1") Integer page,
															  @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize ) throws ServiceException {
		if(form.getWalkMemberId() == null){
			form.setWalkMemberId(getCurrentWalkMember().getId());
		}
		form.setTypeList(CollectionUtil.newArrayList(CollectionUtil.newArrayList(TrendsEnums.TrendsType.DISCOVERY.getValue(), TrendsEnums.TrendsType.VIDEO.getValue())));
		CommonPage<WalkTrendsResult> list = walkTrendsService.getTrendsList(form, page, pageSize);
		return CommonResult.success(list);
	}

	@OperationLog
	@ApiOperation(value = "发布动态")
	@ResponseBody
	@RequestMapping(value="/trends/publish", method=RequestMethod.POST)
	public CommonResult<String> add(WalkTrends trends, String goodsIds) throws ServiceException {
		List<Integer> goodsIdList = null;
		if(com.chengyu.core.utils.StringUtils.isNotBlank(goodsIds)){
			goodsIdList = Convert.convert(new TypeReference<List<Integer>>() {}, goodsIds.split(CommonConstant.DH_REGEX));
		}
		if(trends.getId() == null){
			walkTrendsService.publishTrends(getCurrentWalkMember(), trends, goodsIdList);
		}else{
			walkTrendsService.editTrends(trends, goodsIdList);
		}
		return CommonResult.success(null);
	}

	@ApiOperation(value = "已购买的商品列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "goodsName", value = "商品名称")
	})
	@ResponseBody
	@RequestMapping(value="/trends/getGoodsSelector", method=RequestMethod.GET)
	public CommonResult<List<OmsOrderDetail>> getGoodsSelector(String goodsName) throws ServiceException {
		List<OmsOrderDetail> detailList = orderService.getBuyGoodsList(getCurrentMemberId(), goodsName);
		return CommonResult.success(detailList);
	}

	@OperationLog
	@ApiOperation(value = "删除动态")
	@ResponseBody
	@RequestMapping(value="/trends/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) throws ServiceException {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		walkTrendsService.deleteTrends(ids);
		return CommonResult.success(null);
	}

	@ApiOperation(value = "我关注的动态列表")
	@ResponseBody
	@RequestMapping(value="/trends/getMyCollectionList", method=RequestMethod.GET)
	public CommonResult<CommonPage<WalkTrends>> getMyCollectionList(@RequestParam(value = "page", defaultValue = "1") Integer page,
															  @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize ) throws ServiceException {
		List<WalkTrendsCollection> list = walkTrendsService.getMyCollectionTrends(getCurrentWalkMember().getId(), page, pageSize);
		if(CollectionUtil.isEmpty(list)){
			return CommonResult.success(CommonPage.restPage(new ArrayList<>()));
		}
		List<WalkTrends> trendsList = new ArrayList<>();
		for(WalkTrendsCollection collection : list){
			trendsList.add(walkTrendsService.getTrendsById(collection.getTrendsId()));
		}
		return CommonResult.success(CommonPage.restPage(trendsList));
	}

	@ApiOperation(value = "动态详情")
	@ResponseBody
	@RequestMapping(value="/trends/getDetail", method=RequestMethod.GET)
	public CommonResult<WalkTrendsResult> getDetail(Integer trendsId) {
		WalkTrendsResult result = walkTrendsService.getTrendsDetail(trendsId);
		return CommonResult.success(result);
	}
}