package com.chengyu.core.controller.trends;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.ThirdEnums;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.WalkMember;
import com.chengyu.core.model.WalkMemberCollection;
import com.chengyu.core.service.walk.WalkMemberCollectService;
import com.chengyu.core.service.walk.WalkMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "创作人主页")
@Controller
public class WalkMemberController extends UserBaseController {
	
	@Autowired
	private WalkMemberService walkMemberService;
	@Autowired
	private WalkMemberCollectService walkMemberCollectService;


	@ApiOperation(value = "创作人主页")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "walkMemberId", value = "创作人ID"),
	})
	@ResponseBody
	@RequestMapping(value="/common/walkMember/get", method=RequestMethod.GET)
	public CommonResult<Map<String,Object>> get(Integer walkMemberId) {
		Map<String,Object> result = new HashMap<>();
		WalkMember walkMember = walkMemberService.getWalkMemberById(walkMemberId);
		result.put("walkMember", walkMember);
		//是否已关注他
		result.put("isCollect", walkMemberCollectService.isCollectWalkMember(getCurrentWalkMember(), walkMemberId));
		return CommonResult.success(result);
	}

	@ApiOperation(value = "创作人的关注列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "walkMemberId", value = "创作人")
	})
	@ResponseBody
	@RequestMapping(value={"/common/walkMember/getCollectionList"}, method=RequestMethod.GET)
	public CommonResult<CommonPage<WalkMemberCollection>> getCollectionList(Integer walkMemberId, Integer page, Integer pageSize) {
		List<WalkMemberCollection> list = walkMemberCollectService.getWalkMemberCollectionList(walkMemberId, null, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "创作人的粉丝列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "walkMemberId", value = "创作人")
	})
	@ResponseBody
	@RequestMapping(value={"/common/walkMember/getFansList"}, method=RequestMethod.GET)
	public CommonResult<CommonPage<WalkMemberCollection>> getFansList(Integer walkMemberId, Integer page, Integer pageSize) {
		List<WalkMemberCollection> list = walkMemberCollectService.getWalkMemberCollectionList(null, walkMemberId, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}


	@OperationLog
	@ApiOperation(value = "关注取关创作人")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "walkMemberId", value = "创作人")
	})
	@ResponseBody
	@RequestMapping(value={"/member/walkMember/collectOrNo"}, method=RequestMethod.POST)
	public CommonResult<String> collectOrNoGoods(Integer walkMemberId) {
		walkMemberCollectService.collectOrNoWalkMember(getCurrentWalkMember(), walkMemberId);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "上传头像")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "file", value = "图片文件"),
	})
	@RequestMapping(value={"/member/walkMember/updateHeadImg"}, method=RequestMethod.POST)
	@ResponseBody
	public CommonResult<String> upload(MultipartFile file) throws Exception {
		byte[] bs = file.getBytes();
		String imgPath = null;
		if (bs.length > 0) {
			// 将字符串转换成二进制，用于显示图片
			// 将上面生成的图片格式字符串 imgStr，还原成图片显示
			long currentTime = System.currentTimeMillis();
			String filename = currentTime + ".jpeg";
			imgPath = thirdManager.getThidFactory(this.getThirdMod(ThirdEnums.UPLOAD.getKey())).upload(file, filename, CommonConstant.IMG);
		}

		//更新头像
		UmsMember updateMember = new UmsMember();
		updateMember.setId(getCurrentMemberId());
		updateMember.setHeadImg(imgPath);
		memberService.updateMember(updateMember);
		return CommonResult.success(imgPath);
	}

	@OperationLog
	@ApiOperation(value = "编辑创作号资料")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "headImg", value = "头像"),
		@ApiImplicitParam(name = "gender", value = "男女"),
		@ApiImplicitParam(name = "nickname", value = "昵称"),
		@ApiImplicitParam(name = "background", value = "背景"),
		@ApiImplicitParam(name = "personSign", value = "个性前面"),
	})
	@ResponseBody
	@RequestMapping(value={"/member/walkMember/update"}, method=RequestMethod.POST)
	public CommonResult<String> update(String headImg, String gender, String nickname, String background, String personSign) {
		WalkMember walkMember = new WalkMember();
		walkMember.setId(getCurrentWalkMember().getId());
		walkMember.setHeadImg(headImg);
		walkMember.setGender(gender);
		walkMember.setNickname(nickname);
		walkMember.setBackground(background);
		walkMember.setPersonSign(personSign);
		walkMemberService.updateWalkMember(walkMember);
		return CommonResult.success(null);
	}

}