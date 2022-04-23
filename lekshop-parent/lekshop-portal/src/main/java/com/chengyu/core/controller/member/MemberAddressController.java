package com.chengyu.core.controller.member;

import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberAddress;
import com.chengyu.core.service.member.MemberAddressService;
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

/**
 * @title  用户收货地址
 * @author LeGreen
 * @date   2022/4/23
 */
@Api(tags = "收货地址", description = "MemberAddressController")
@Controller
@RequestMapping("/member")
public class MemberAddressController extends UserBaseController {
	
	@Autowired
	private MemberAddressService addressService;
	
	@ApiOperation(value = "收货地址列表")
	@RequestMapping(value={"/address/getList"}, method=RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsMemberAddress>> getList(Integer page, Integer pageSize) throws Exception{
		UmsMember member = getCurrentMember();
		List<UmsMemberAddress> list = addressService.getAddressList(member.getId(), page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
    }
	
	@ApiOperation(value = "添加收货地址")
	@RequestMapping(value={"/address/save"}, method=RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> saveAddress(UmsMemberAddress address) throws Exception{
		UmsMember member = getCurrentMember();
		address.setMemberId(member.getId());
		address.setMemberName(member.getCode());
		addressService.addAddress(address);
		return CommonResult.success(null);
    }

	@ApiOperation(value = "编辑收货地址")
	@RequestMapping(value={"/address/update"}, method=RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> updateAddress(UmsMemberAddress address) throws Exception{
		address.setMemberId(getCurrentMember().getId());
		addressService.updateAddress(address);
		return CommonResult.success(null);
    }
	
	@ApiOperation(value = "删除收货地址")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "收货地址ID"),
	})
	@RequestMapping(value={"/address/delete"}, method=RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> deleteAddress(Integer id) throws Exception{
		addressService.deleteAddress(id);
		return CommonResult.success(null);
    }
	
	@ApiOperation(value = "获取默认收货地址")
	@RequestMapping(value={"/address/getDefaltAddress"}, method=RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsMemberAddress> getDefaltAddress() throws Exception{
		UmsMember member = getCurrentMember();
		return CommonResult.success(addressService.getDefaultAddressByMemberId(member.getId()));
    }
	
}