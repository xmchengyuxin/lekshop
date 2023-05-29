package com.chengyu.core.controller.content;

import java.util.List;

import com.chengyu.core.component.DemoStationForbid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.CmsArticle;
import com.chengyu.core.service.content.ArticleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "ArticleController", description = "文章管理")
@Controller
@RequestMapping("/system")
public class ArticleController extends AdminBaseController {
	
	@Autowired
	private ArticleService articleService;
	
	@ApiOperation(value = "文章列表")
	@ResponseBody
	@RequestMapping(value="/article/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<CmsArticle>> getList(
			Integer cateId, Integer type, String title,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws Exception {
		
		List<CmsArticle> list = articleService.getArticleList(cateId, type, title, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}
	
	@ApiOperation(value = "文章详情")
	@ResponseBody
	@RequestMapping(value="/article/get", method=RequestMethod.GET)
	public CommonResult<CmsArticle> get(
			Integer id) throws Exception {
		return CommonResult.success(articleService.getArticleById(id));
	}
	
	@OperationLog
	@ApiOperation(value = "添加编辑文章")
	@ResponseBody
	@RequestMapping(value="/article/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(CmsArticle article) throws Exception {
		if(article.getId() != null && article.getId() > 0L) {
			articleService.updateArticle(article);
		} else {
			articleService.addArticle(article);
		}
		
		return CommonResult.success(null);
	}

	@DemoStationForbid
	@OperationLog
	@ApiOperation(value = "删除文章")
	@ResponseBody
	@RequestMapping(value="/article/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) throws Exception {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		
		for(String id : ids.split(",")){
			articleService.deleteArticleById(Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}
}