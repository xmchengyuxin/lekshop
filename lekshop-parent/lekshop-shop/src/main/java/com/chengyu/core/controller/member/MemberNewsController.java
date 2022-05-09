package com.chengyu.core.controller.member;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.enums.MemberNewsEnums;
import com.chengyu.core.domain.result.MemberNewsResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.UmsMemberNews;
import com.chengyu.core.service.member.MemberNewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @title  用户消息
 * @author LeGreen
 * @date   2021/1/9
 */
@Api(tags = "用户消息")
@Controller
@RequestMapping("/member")
public class MemberNewsController extends UserBaseController {
	
	@Autowired
	private MemberNewsService newsService;
	
	@ApiOperation(value = "消息分类")
	@ResponseBody
	@RequestMapping(value="/news/getTypeList", method=RequestMethod.GET)
    public CommonResult<List<MemberNewsResult>> getTypeList() throws Exception{
		Integer memberId = getCurrentMemberId();
		List<MemberNewsResult> list = new ArrayList<>();
		List<MemberNewsResult> lastList = new ArrayList<>();
		for(MemberNewsEnums.MemberNewsTypes type : MemberNewsEnums.MemberNewsTypes.values()){
			MemberNewsResult result = new MemberNewsResult();
			result.setType(type.getType());
			result.setTypeName(type.getTypeName());
			result.setImg(type.getImg());
			result.setUnReadNums(newsService.countUnReadNews(memberId, type.getType()));
			
			List<UmsMemberNews> newsList = newsService.getMemberNewsList(memberId, result.getType(), null, 1, 1);
			if(CollectionUtil.isNotEmpty(newsList)){
				UmsMemberNews news = newsList.get(0);
				result.setLastNews(news.getContent());
				result.setLastNewsTime(news.getAddTime());
				list.add(result);
			}else{
				lastList.add(result);
			}
		}
		list = list.stream().sorted(Comparator.comparing(MemberNewsResult::getLastNewsTime)).collect(Collectors.toList());
		CollectionUtil.reverse(list);
		list.addAll(lastList);
		return CommonResult.success(list);
    }
	
	@ApiOperation(value = "消息列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "readStatus", value = "0未读 1已读"),
		@ApiImplicitParam(name = "type", value = "消息类型"),
	})
	@ResponseBody
	@RequestMapping(value="/news/getList", method=RequestMethod.GET)
    public CommonResult<CommonPage<UmsMemberNews>> getNews(String readStatus, Integer type, Integer page, Integer pageSize) throws Exception{
		List<UmsMemberNews> list = newsService.getMemberNewsList(getCurrentMemberId(), type, readStatus, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
    }
	
	@ResponseBody
	@ApiOperation(value = "统计未读消息数量")
	@RequestMapping(value="/news/countNews", method=RequestMethod.GET)
    public CommonResult<Map<String,Object>> countNews() throws Exception{
		Long num = newsService.countUnReadNews(getCurrentMemberId(), null);
		Map<String,Object> result = new HashMap<>(16);
		result.put("num", num);
		return CommonResult.success(result);
    }
	
	@ResponseBody
	@ApiOperation(value = "全部置为已读")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "type", value = "消息类型"),
	})
	@RequestMapping(value="/news/readAll", method=RequestMethod.POST)
    public CommonResult<String> readAll(Integer type) throws Exception{
		newsService.readAll(getCurrentMemberId(), type);
		return CommonResult.success(null);
    }
	
	@ApiOperation(value = "单条消息置为已读")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "消息ID"),
	})
	@ResponseBody
	@RequestMapping(value="/news/read", method=RequestMethod.POST)
    public CommonResult<String> read(Integer id) throws Exception{
		newsService.readById(id);
		return CommonResult.success(null);
    }
}