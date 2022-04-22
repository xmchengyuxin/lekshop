package com.chengyu.core.controller.content;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.CmsAgreement;
import com.chengyu.core.service.content.AgreementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "AgreementController", description = "协议管理")
@Controller
@RequestMapping("/system")
public class AgreementController extends AdminBaseController {
	
	@Autowired
	private AgreementService agreementService;
	
	@ApiOperation(value = "协议列表")
	@ResponseBody
	@RequestMapping(value="/agreement/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<CmsAgreement>> getList(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws Exception {
		List<CmsAgreement> list = agreementService.getAgreementList(page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}
	
	@OperationLog
	@ApiOperation(value = "编辑协议")
	@ResponseBody
	@RequestMapping(value="/agreement/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(CmsAgreement agreement) throws Exception {
		if(agreement.getId() != null && agreement.getId() > 0L) {
			agreementService.updateAgreement(agreement);
		}
		
		return CommonResult.success(null);
	}
	
	@OperationLog
	@ApiOperation(value = "隐藏协议")
	@ResponseBody
	@RequestMapping(value="/agreement/updateAgreementStatus", method=RequestMethod.POST)
	public CommonResult<String> updateAdvertStatus(Integer id, String status) throws Exception {
		CmsAgreement agreement = new CmsAgreement();
		agreement.setId(id);
		agreement.setStatus(status);
		agreementService.updateAgreement(agreement);
		
		return CommonResult.success(null);
	}
	
}