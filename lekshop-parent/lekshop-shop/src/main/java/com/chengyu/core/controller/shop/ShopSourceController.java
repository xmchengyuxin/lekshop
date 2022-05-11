package com.chengyu.core.controller.shop;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.model.UmsShopSource;
import com.chengyu.core.model.UmsShopSourceCate;
import com.chengyu.core.service.shop.ShopSourceCateService;
import com.chengyu.core.service.shop.ShopSourceService;
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

@Api(tags = "店铺素材管理")
@Controller
@RequestMapping("/member")
public class ShopSourceController extends ShopBaseController {
	
	@Autowired
	private ShopSourceCateService sourceCateService;
	@Autowired
	private ShopSourceService sourceService;
	
	@ApiOperation(value = "素材分类列表")
	@ResponseBody
	@RequestMapping(value="/sourceCate/getList", method=RequestMethod.GET)
	public CommonResult<List<UmsShopSourceCate>> getList() throws Exception {
		List<UmsShopSourceCate> list = sourceCateService.getAllSourceCates(getCurrentShop().getId());
		if(CollectionUtil.isNotEmpty(list)){
			for(UmsShopSourceCate cate : list){
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
	public CommonResult<String> editSubmit(UmsShopSourceCate cate) throws Exception {
		if(cate.getId() != null && cate.getId() > 0L) {
			sourceCateService.updateSourceCate(getCurrentShop(), cate);
		}else{
			sourceCateService.addSourceCate(getCurrentShop(), cate);
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
		UmsShop shop = getCurrentShop();
		for(String id : ids.split(CommonConstant.DH_REGEX)){
			sourceCateService.deleteSourceCateById(shop, Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}
	
	@ApiOperation(value = "素材列表")
	@ResponseBody
	@RequestMapping(value="/source/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsShopSource>> getList(
			Integer cateId,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws Exception {
		
		List<UmsShopSource> list = sourceService.getSourceList(getCurrentShop(), cateId, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}
	
	@OperationLog
	@ApiOperation(value = "上传素材")
	@ResponseBody
	@RequestMapping(value="/source/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(String sources, Integer cateId, String type) throws Exception {
		sourceService.addSources(getCurrentShop(), sources, cateId, type);
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

		UmsShop shop = getCurrentShop();
		for(String id : ids.split(CommonConstant.DH_REGEX)){
			sourceService.deleteSourceById(shop, Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}
}