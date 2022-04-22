package com.chengyu.core.controller.content;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.CmsNotice;
import com.chengyu.core.service.content.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @title  公告弹窗
 * @author LeGreen
 * @date   2021/1/15
 */
@Api(tags = "公告弹窗")
@Controller
@RequestMapping("/system")
public class NoticeController extends AdminBaseController {
	
	@Autowired
	private NoticeService noticeService;
	
	@ApiOperation(value = "公告弹窗列表")
	@ResponseBody
	@RequestMapping(value="/notice/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<CmsNotice>> getList(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
		List<CmsNotice> list = noticeService.getNoticeList(null, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "获取公告弹窗详情")
	@ResponseBody
	@RequestMapping(value="/notice/get", method=RequestMethod.GET)
	public CommonResult<CmsNotice> get(Integer noticeId) {
		CmsNotice notice = noticeService.getNoticeById(noticeId);
		return CommonResult.success(notice);
	}
	
	@OperationLog
	@ApiOperation(value = "编辑广告")
	@ResponseBody
	@RequestMapping(value="/notice/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(CmsNotice notice) throws Exception {
		noticeService.addNotice(notice);
		return CommonResult.success(null);
	}

}