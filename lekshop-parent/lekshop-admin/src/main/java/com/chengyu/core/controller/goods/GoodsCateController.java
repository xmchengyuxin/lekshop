package com.chengyu.core.controller.goods;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.ThirdEnums;
import com.chengyu.core.domain.result.GoodsCateResult;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.PmsGoodsCate;
import com.chengyu.core.service.goods.GoodsCateService;
import com.chengyu.core.service.system.ThirdConfigService;
import com.chengyu.core.util.third.manager.ThirdManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title  电商分类
 * @author LeGreen
 * @date   2022/5/7
 */
@Api(tags = "电商分类")
@Controller
@RequestMapping("/system")
public class GoodsCateController extends AdminBaseController {
	
	@Autowired
	private GoodsCateService goodsCateService;
	@Autowired
	protected ThirdManager thirdManager;
	@Autowired
	private ThirdConfigService thirdConfigService;
	
	@ApiOperation(value = "电商分类列表")
	@ResponseBody
	@RequestMapping(value="/goodsCate/getList", method=RequestMethod.GET)
	public CommonResult<List<GoodsCateResult>> getList(Integer status) throws ServiceException {
		List<GoodsCateResult> list = goodsCateService.getGoodsCateResults(status);
		return CommonResult.success(list);
	}

	@OperationLog
	@ApiOperation(value = "添加电商分类")
	@ResponseBody
	@RequestMapping(value="/goodsCate/add", method=RequestMethod.POST)
	public CommonResult<String> add(Integer pid, String name, Integer sort, String img, String target) {
		goodsCateService.addGoodsCate(pid, name, sort, img, target);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "编辑电商分类")
	@ResponseBody
	@RequestMapping(value="/goodsCate/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(PmsGoodsCate cate) throws ServiceException {
		if(cate.getId() == null){
			goodsCateService.addGoodsCate(cate.getPid(), cate.getName(), cate.getSort(), cate.getImg(), cate.getTarget());
		}else {
			goodsCateService.updateGoodsCate(cate);
		}
		return CommonResult.success(null);
	}

	@ApiOperation(value = "上传电商分类图片")
	@ResponseBody
	@RequestMapping(value="/goodsCate/updateImg", method=RequestMethod.POST)
	public CommonResult<Map<String, Object>> updateMainFile(Integer id, Integer index, MultipartHttpServletRequest request) throws Exception {
		Map<String, MultipartFile> fileMap = request.getFileMap();
		if(fileMap.size() <= 0) {
			return CommonResult.success(null);
		}

		String imgUrl = "";
		for(String field : fileMap.keySet()) {
			MultipartFile file = fileMap.get(field);
			if(file == null || file.getSize() <= 0 || StringUtils.isEmpty(file.getOriginalFilename())) continue;

			long currentTime = System.currentTimeMillis();
			String fileName = currentTime + "" + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			imgUrl = thirdManager.getThidFactory(thirdConfigService.getThirdConfigByNid(ThirdEnums.UPLOAD.getKey())).upload(file, fileName, CommonConstant.IMG);
			break;
		}

		if(StringUtils.isEmpty(imgUrl)) {
			return CommonResult.failed("上传失败");
		}

		PmsGoodsCate cate = new PmsGoodsCate();
		cate.setId(id);
		cate.setImg(imgUrl);
		goodsCateService.updateGoodsCate(cate);

		Map<String, Object> result = new HashMap<>();
		result.put("imgUrl", imgUrl);
		result.put("index", index);
		return CommonResult.success(result);
	}

	@OperationLog
	@ApiOperation(value = "删除电商分类")
	@ResponseBody
	@RequestMapping(value="/goodsCate/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) throws ServiceException {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		for(String id : ids.split(CommonConstant.DH_REGEX)){
			goodsCateService.deleteGoodsCate(Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}
}