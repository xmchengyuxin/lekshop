package com.chengyu.core.controller.car;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OmsCar;
import com.chengyu.core.service.order.CarService;
import com.chengyu.core.utils.StringUtils;
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
import java.util.Map;

@Api(tags = "购物车")
@Controller
@RequestMapping("/member")
public class CarController extends UserBaseController {
	
	@Autowired
	private CarService carService;

	@ApiOperation(value = "购物车列表")
	@ResponseBody
	@RequestMapping(value={"/car/getList"}, method=RequestMethod.GET)
	public CommonResult<Map<Integer, List<OmsCar>>> getList() throws ServiceException {
		Map<Integer, List<OmsCar>> map = carService.getCarList(getCurrentMemberId());
		return CommonResult.success(map);
	}

	@ApiOperation(value = "统计购物车宝贝数量")
	@ResponseBody
	@RequestMapping(value={"/car/count"}, method=RequestMethod.GET)
	public CommonResult<Long> count() throws ServiceException {
		Long num = carService.countCarNum(getCurrentMemberId());
		return CommonResult.success(num);
	}

	@OperationLog
	@ApiOperation(value = "将商品加入购物车")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "goodsId", value = "店铺ID"),
		@ApiImplicitParam(name = "attrSymbolPath", value = "商品规格(按顺序/隔开)"),
		@ApiImplicitParam(name = "num", value = "数量")
	})
	@ResponseBody
	@RequestMapping(value={"/car/addCar"}, method=RequestMethod.POST)
	public CommonResult<String> addCar(Integer goodsId, String attrSymbolPath, Integer num) throws Exception {
		carService.addCar(getCurrentMember(), goodsId, attrSymbolPath, num);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "购物车加减数量")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "carId", value = "购物车ID"),
			@ApiImplicitParam(name = "num", value = "数量")
	})
	@ResponseBody
	@RequestMapping(value={"/car/addCarNum"}, method=RequestMethod.POST)
	public CommonResult<String> addCarNum(Integer carId, Integer num) throws Exception {
		carService.addCarNum(getCurrentMember(), carId, num);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "删除购物车")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID"),
	})
	@ResponseBody
	@RequestMapping(value={"/car/delete"}, method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) throws ServiceException {
		if(StringUtils.isBlank(ids)){
			return CommonResult.success(null);
		}
		List<Integer> carIdList  = Convert.convert(new TypeReference<List<Integer>>() {}, ids.split(CommonConstant.DH_REGEX));;
		carService.deleteCar(getCurrentMember(), carIdList);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "清空购物车")
	@ResponseBody
	@RequestMapping(value={"/car/clear"}, method=RequestMethod.POST)
	public CommonResult<String> clearCarByMember() throws ServiceException {
		carService.clearCarByMember(getCurrentMember());
		return CommonResult.success(null);
	}

}