package com.chengyu.core.controller.member;

import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberInvoice;
import com.chengyu.core.service.member.MemberInvoiceService;
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
 * @类描述   	用户发票
 * @作者   		LeGreen
 * @创建时间  	2020年3月21日
 * @版本   		1.00
 * @修改记录
 * <pre>
 *     版本          修改人    		修改日期 			修改内容描述
 *     -----------------------------------------------------------
 *     1.00   	LeGreen    	2020年3月21日             
 *     -----------------------------------------------------------
 * </pre>
 */
@Api(tags = "用户发票管理", description = "MemberInvoiceController")
@Controller
@RequestMapping("/member")
public class MemberInvoiceController extends UserBaseController {
	
	@Autowired
	private MemberInvoiceService memberInvoiceService;

	@ApiOperation(value = "发票抬头列表")
	@RequestMapping(value={"/invoice/getList"}, method=RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsMemberInvoice>> getList(Integer page, Integer pageSize) throws Exception{
		UmsMember member = getCurrentMember();
		
		List<UmsMemberInvoice> list = memberInvoiceService.getInvoiceList(member.getId(), page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
    }
	
	@ApiOperation(value = "获取发票抬头详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "invoiceId", value = "发票抬头ID"),
	})
	@RequestMapping(value={"/invoice/get"}, method=RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsMemberInvoice> get(Integer invoiceId) throws Exception{
		return CommonResult.success(memberInvoiceService.getInvoiceById(invoiceId));
    }
	
	@ApiOperation(value = "添加编辑发票抬头")
	@RequestMapping(value={"/invoice/editSubmit"}, method=RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> editSubmit(UmsMemberInvoice invoice) throws Exception{
		UmsMember member = memberService.getCurrentMember();
		invoice.setMemberId(member.getId());
		invoice.setMemberName(member.getCode());
		if(invoice.getId() != null){
			memberInvoiceService.updateInvoice(invoice);
		}else{
			memberInvoiceService.addInvoice(invoice);
		}
		return CommonResult.success(null);
    }
	
	@ApiOperation(value = "删除发票抬头")
	@RequestMapping(value={"/invoice/delete"}, method=RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> delete(Integer id) throws Exception{
		memberInvoiceService.deleteInvoice(id);
		return CommonResult.success(null);
    }
	
}