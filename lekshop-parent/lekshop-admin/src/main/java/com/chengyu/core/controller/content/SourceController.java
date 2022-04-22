package com.chengyu.core.controller.content;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.CmsSource;
import com.chengyu.core.model.CmsSourceCate;
import com.chengyu.core.service.content.SourceCateService;
import com.chengyu.core.service.content.SourceService;
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

@Api(tags = "SourceController", description = "素材管理")
@Controller
@RequestMapping("/system")
public class SourceController extends AdminBaseController {
	
	@Autowired
	private SourceCateService sourceCateService;
	@Autowired
	private SourceService sourceService;
	
	@ApiOperation(value = "素材分类列表")
	@ResponseBody
	@RequestMapping(value="/sourceCate/getList", method=RequestMethod.GET)
	public CommonResult<List<CmsSourceCate>> getList() throws Exception {
		List<CmsSourceCate> list = sourceCateService.getAllSourceCates();
		if(CollectionUtil.isNotEmpty(list)){
			for(CmsSourceCate cate : list){
				//统计分类下有几张图片
				long num = sourceService.countNums(cate.getId());
				cate.setName(cate.getName()+"("+num+")");
			}
		}
		return CommonResult.success(list);
	}
	
	@OperationLog
	@ApiOperation(value = "编辑分类")
	@ResponseBody
	@RequestMapping(value="/sourceCate/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(CmsSourceCate cate) throws Exception {
		if(cate.getId() != null && cate.getId() > 0L) {
			sourceCateService.updateSourceCate(cate);
		}else{
			sourceCateService.addSourceCate(cate);
		}
		
		return CommonResult.success(null);
	}
	
	@OperationLog
	@ApiOperation(value = "删除分类")
	@ResponseBody
	@RequestMapping(value="/sourceCate/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) throws Exception {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		
		for(String id : ids.split(",")){
			sourceCateService.deleteSourceCateById(Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}
	
	@ApiOperation(value = "素材列表")
	@ResponseBody
	@RequestMapping(value="/source/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<CmsSource>> getList(
			Integer cateId,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws Exception {
		
		List<CmsSource> list = sourceService.getSourceList(cateId, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}
	
	@OperationLog
	@ApiOperation(value = "上传素材")
	@ResponseBody
	@RequestMapping(value="/source/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(String sources, Integer cateId, String type) throws Exception {
		sourceService.addSources(sources, cateId, type);
		return CommonResult.success(null);
	}
	
	@OperationLog
	@ApiOperation(value = "移动素材分组")
	@ResponseBody
	@RequestMapping(value="/source/moveCate", method=RequestMethod.POST)
	public CommonResult<String> moveCate(Integer id, Integer cateId) throws Exception {
		sourceService.moveCate(id, cateId);
		return CommonResult.success(null);
	}
	
	@OperationLog
	@ApiOperation(value = "删除素材")
	@ResponseBody
	@RequestMapping(value="/source/delete", method=RequestMethod.POST)
	public CommonResult<String> deleteSource(String ids) throws Exception {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		
		for(String id : ids.split(",")){
			sourceService.deleteSourceById(Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}
}