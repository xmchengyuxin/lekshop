package com.chengyu.core.controller.content;

import com.chengyu.core.component.DemoStationForbid;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.CmsArticleCate;
import com.chengyu.core.service.content.ArticleCateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "文章分类")
@Controller
@RequestMapping("/system")
public class ArticleCateController extends AdminBaseController {

	@Autowired
	private ArticleCateService articleCateService;

	@ApiOperation(value = "文章分类列表")
	@ResponseBody
	@RequestMapping(value="/articleCate/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<CmsArticleCate>> getList(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws Exception {

		List<CmsArticleCate> list = articleCateService.getArticleCateList(page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "文章分类选择器")
	@ResponseBody
	@RequestMapping(value="/articleCate/getSelector", method=RequestMethod.GET)
	public CommonResult<List<CmsArticleCate>> getSelector() throws Exception {
		List<CmsArticleCate> list = articleCateService.getAllArticleCates();
		return CommonResult.success(list);
	}
	
	@OperationLog
	@ApiOperation(value = "添加编辑文章分类")
	@ResponseBody
	@RequestMapping(value="/articleCate/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(CmsArticleCate articleCate) throws Exception {
		if(articleCate.getId() != null) {
			articleCateService.updateArticleCate(articleCate);
		} else {
			articleCateService.addArticleCate(articleCate);
		}
		
		return CommonResult.success(null);
	}

	@DemoStationForbid
	@OperationLog
	@ApiOperation(value = "删除文章分类")
	@ResponseBody
	@RequestMapping(value="/articleCate/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) throws Exception {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		
		for(String id : ids.split(",")){
			articleCateService.deleteArticleCateById(Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}
}