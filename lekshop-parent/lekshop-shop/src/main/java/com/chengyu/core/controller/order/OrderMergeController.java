package com.chengyu.core.controller.order;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.chengyu.core.component.DataPermissions;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.form.OrderMergeSearchForm;
import com.chengyu.core.domain.result.OrderMergeResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OmsOrderMerge;
import com.chengyu.core.service.order.OrderMergeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "订单配货")
@Controller
@RequestMapping("/member")
public class OrderMergeController extends ShopBaseController {

    @Autowired
    private OrderMergeService orderMergeService;

    @ApiOperation(value = "配货订单列表")
    @ResponseBody
    @RequestMapping(value = {"/orderMerge/getList"}, method = RequestMethod.GET)
    @DataPermissions
    public CommonResult<CommonPage<OmsOrderMerge>> getList(OrderMergeSearchForm form,
                                                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                           @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
        List<OmsOrderMerge> list = orderMergeService.getOrderMergeList(form, page, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation(value = "配货订单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderMergeId", value = "订单配货ID")
    })
    @ResponseBody
    @RequestMapping(value = {"/orderMerge/getDetail"}, method = RequestMethod.GET)
    public CommonResult<OrderMergeResult> getDetail(Integer orderMergeId) {
        OrderMergeResult result = orderMergeService.getOrderMerge(orderMergeId);
        return CommonResult.success(result);
    }

    @OperationLog
    @ApiOperation(value = "合并订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderIds", value = "订单集合")
    })
    @ResponseBody
    @RequestMapping(value = {"/orderMerge/merge"}, method = RequestMethod.POST)
    public CommonResult<String> mergeOrder(String orderIds) throws ServiceException {
        List<Integer> orderIdList = Convert.convert(new TypeReference<List<Integer>>() {},orderIds.split(CommonConstant.DH_REGEX));
        orderMergeService.mergeOrder(getCurrentAccount(), orderIdList);
        return CommonResult.success(null);
    }

    @OperationLog
    @ApiOperation(value = "配货")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderMergeId", value = "订单配货ID"),
    })
    @ResponseBody
    @RequestMapping(value = {"/orderMerge/distribution"}, method = RequestMethod.POST)
    public CommonResult<String> distribution(Integer orderMergeId) throws ServiceException {
        orderMergeService.distribution(getCurrentAccount(), orderMergeId);
        return CommonResult.success(null);
    }

    @OperationLog
    @ApiOperation(value = "分拣")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderMergeId", value = "订单配货ID"),
    })
    @ResponseBody
    @RequestMapping(value = {"/orderMerge/sortOut"}, method = RequestMethod.POST)
    public CommonResult<String> sortOut(Integer orderMergeId) throws ServiceException {
        orderMergeService.sortOut(getCurrentAccount(), orderMergeId);
        return CommonResult.success(null);
    }

    @OperationLog
    @ApiOperation(value = "取消配货")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderMergeId", value = "订单配货ID"),
    })
    @ResponseBody
    @RequestMapping(value = {"/orderMerge/cancel"}, method = RequestMethod.POST)
    public CommonResult<String> cancel(Integer orderMergeId) throws ServiceException {
        orderMergeService.cancel(getCurrentAccount(), orderMergeId);
        return CommonResult.success(null);
    }
}