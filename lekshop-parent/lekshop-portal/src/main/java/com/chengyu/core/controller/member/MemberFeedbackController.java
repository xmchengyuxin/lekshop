 package com.chengyu.core.controller.member;

 import com.chengyu.core.controller.UserBaseController;
 import com.chengyu.core.entity.CommonResult;
 import com.chengyu.core.exception.ServiceException;
 import com.chengyu.core.model.CmsFeedback;
 import com.chengyu.core.model.UmsMember;
 import com.chengyu.core.service.content.FeedbackService;
 import io.swagger.annotations.Api;
 import io.swagger.annotations.ApiOperation;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestMethod;
 import org.springframework.web.bind.annotation.ResponseBody;

 @Api(tags = {"意见反馈"})
 @Controller
 @RequestMapping({"/member"})
 public class MemberFeedbackController
   extends UserBaseController
 {
   @Autowired
   private FeedbackService feedbackService;
   
   @ApiOperation("提交反馈")
   @RequestMapping(value = {"/feedback/save"}, method = {RequestMethod.POST})
   @ResponseBody
   public CommonResult<String> saveAddress(String content, String imgs) throws ServiceException {
     UmsMember member = getCurrentMember();
     CmsFeedback feedback = new CmsFeedback();
     feedback.setMemberId(member.getId());
     feedback.setMemberName(member.getNickname());
     feedback.setContent(content);
     feedback.setImgs(imgs);
     feedback.setStatus("0");
     this.feedbackService.addFeedback(feedback);
     return CommonResult.success(null);
   }
 }


