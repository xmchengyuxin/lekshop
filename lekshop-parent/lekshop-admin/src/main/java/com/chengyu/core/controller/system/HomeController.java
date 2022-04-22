package com.chengyu.core.controller.system;

import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.ThirdEnums;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.service.system.ConfigService;
import com.chengyu.core.service.system.ThirdConfigService;
import com.chengyu.core.util.third.manager.ThirdManager;
import com.chengyu.core.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @类描述   	主页
 * @作者   		LeGreen
 * @创建时间  	2020年3月6日
 * @版本   		1.00
 * @修改记录
 * <pre>
 *     版本          修改人    		修改日期 			修改内容描述
 *     -----------------------------------------------------------
 *     1.00   	LeGreen    	2020年3月6日             
 *     -----------------------------------------------------------
 * </pre>
 */
@Controller
@Api(tags = "HomeController", description = "首页管理")
@RequestMapping("/system")
public class HomeController extends AdminBaseController {
	
	@Autowired
	private ConfigService configService;
	@Autowired
	protected ThirdManager thirdManager;
	@Autowired
	private ThirdConfigService thirdConfigService;
	
    @ApiOperation(value = "获取网站基础信息")
    @RequestMapping(value = "/getWebInfo", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Map<String, Object>> getWebInfo() throws Exception{
    	Map<String, Object> data = new HashMap<String, Object>();
		data.put("webName", configService.getValueByNid("web_name"));
		data.put("logo", configService.getValueByNid("logo"));
        return CommonResult.success(data);
    }

    @ApiOperation(value = "上传图片")
    @ResponseBody
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public CommonResult<Map<String,Object>> upload(MultipartHttpServletRequest request) throws Exception {
		Map<String, MultipartFile> fileMap = request.getFileMap();
		if(fileMap.size() <= 0) {
			return CommonResult.success(null);
		}
		
		Map<String,Object> result = new HashMap<>();
		for(String field : fileMap.keySet()) {
			MultipartFile file = fileMap.get(field);
			if(file == null || file.getSize() <= 0 || StringUtils.isBlank(file.getOriginalFilename())) {
				continue;
			}
			
			long currentTime = System.currentTimeMillis();
			String fileName = currentTime + "" + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String imgUrl = thirdManager.getThidFactory(thirdConfigService.getThirdConfigByNid(ThirdEnums.UPLOAD.getKey())).upload(file, fileName, CommonConstant.VIDEO);
			result.put("imgUrl", imgUrl);
			
			break;
		}
		
		return CommonResult.success(result);
	}
	
    @ApiOperation(value = "上传视频")
    @RequestMapping(value="/uploadVideo", method=RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<String,Object>> uploadVideo(MultipartHttpServletRequest request) throws Exception {
		Map<String, MultipartFile> fileMap = request.getFileMap();
		if(fileMap.size() <= 0) {
			return CommonResult.success(null);
		}
		
		Map<String,Object> result = new HashMap<>();
		for(String field : fileMap.keySet()) {
			MultipartFile file = fileMap.get(field);
			if(file == null || file.getSize() <= 0 || StringUtils.isBlank(file.getOriginalFilename())) {
				continue;
			}
			
			long currentTime = System.currentTimeMillis();
			String fileName = currentTime + ".mp4";
			String imgUrl = thirdManager.getThidFactory(thirdConfigService.getThirdConfigByNid(ThirdEnums.UPLOAD.getKey())).upload(file, fileName, CommonConstant.VIDEO);
			result.put("videoUrl", imgUrl);
			
			break;
		}
		return CommonResult.success(result);
	}
    
}
