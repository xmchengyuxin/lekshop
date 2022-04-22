package com.chengyu.core.controller.common;

import cn.hutool.core.date.DateUtil;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.MemberTypes;
import com.chengyu.core.mapper.UmsMemberMapper;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberExample;
import com.chengyu.core.service.system.ConfigService;
import com.chengyu.core.util.weixin.WechatUtil;
import com.chengyu.core.utils.StringUtils;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @title  公共管理类
 * @author LeGreen
 * @date   2021/1/8
 */
@Log4j2
@Api(tags = "公共管理类")
@Controller
@RequestMapping("/common")
public class CommonController extends UserBaseController {

	@Autowired
	private UmsMemberMapper memberMapper;
	@Autowired
	private ConfigService configService;

	@RequestMapping("/gzh/answer")
	@ResponseBody
	public String answer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String echostr = request.getParameter("echostr");
		if (StringUtils.isNotBlank(echostr)) {
			return echostr;
		}

		String success = "success";
		InputStream inStream = request.getInputStream();
		int _buffer_size = 1024;
		if (inStream != null) {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] tempBytes = new byte[_buffer_size];
			int count = -1;
			while ((count = inStream.read(tempBytes, 0, _buffer_size)) != -1) {
				outStream.write(tempBytes, 0, count);
			}
			tempBytes = null;
			outStream.flush();
			//将流转换成字符串
			String result = new String(outStream.toByteArray(), "UTF-8");
			if (StringUtils.isBlank(result)) {
				return success;
			}
			System.out.println("微信公众号被动消息回复:" + result);
			WeixinMsgResponse msgResponse = WechatUtil.xmlToBean(result, WeixinMsgResponse.class);
			if (msgResponse == null) {
				return success;
			}
			String kefuWeixin = configService.getValueByNid("fs_kefu_wx");
			if("subscribe".equals(msgResponse.getEvent())){
				log.info("是否进入关注公众号");
				return "<xml>\n" +
						"  <ToUserName><![CDATA["+msgResponse.getFromUserName()+"]]></ToUserName>\n" +
						"  <FromUserName><![CDATA["+msgResponse.getToUserName()+"]]></FromUserName>\n" +
						"  <CreateTime>"+DateUtil.currentSeconds()+"</CreateTime>\n" +
						"  <MsgType><![CDATA[text]]></MsgType>\n" +
						"  <Content><![CDATA[感谢您的关注,\n" +
						"绑定账号请直接回复平台【手机号】,例: 13444555666 \n" +
						"操作中有任何问题请及时联系客服微信【"+kefuWeixin+"】。" +
						"]]></Content>\n" +
						"</xml>";
			}
			if (StringUtils.isBlank(msgResponse.getContent())) {
				return success;
			}
			if("我要解绑".equals(msgResponse.getContent())){
				UmsMemberExample example = new UmsMemberExample();
				example.createCriteria().andGzhOpenIdEqualTo(msgResponse.getFromUserName());

				UmsMember updateMember = new UmsMember();
				updateMember.setGzhOpenId("");
				updateMember.setGzhFocusStatus(CommonConstant.NO_INT);
				memberMapper.updateByExampleSelective(updateMember, example);

				if(StringUtils.isNotBlank(kefuWeixin)){
					return "<xml>\n" +
							"  <ToUserName><![CDATA["+msgResponse.getFromUserName()+"]]></ToUserName>\n" +
							"  <FromUserName><![CDATA["+msgResponse.getToUserName()+"]]></FromUserName>\n" +
							"  <CreateTime>"+DateUtil.currentSeconds()+"</CreateTime>\n" +
							"  <MsgType><![CDATA[text]]></MsgType>\n" +
							"  <Content><![CDATA[欢迎来到我们的大家庭，\n" +
							"您的微信已解绑成功, \n" +
							"操作中有任何问题请及时联系客服【"+kefuWeixin+"】。" +
							"]]></Content>\n" +
							"</xml>";
				}else{
					return "<xml>\n" +
							"  <ToUserName><![CDATA["+msgResponse.getFromUserName()+"]]></ToUserName>\n" +
							"  <FromUserName><![CDATA["+msgResponse.getToUserName()+"]]></FromUserName>\n" +
							"  <CreateTime>"+DateUtil.currentSeconds()+"</CreateTime>\n" +
							"  <MsgType><![CDATA[text]]></MsgType>\n" +
							"  <Content><![CDATA[欢迎来到我们的大家庭，\n" +
							"您的微信已解绑成功, \n" +
							"操作中有任何问题请及时联系客服。]]></Content>\n" +
							"</xml>";
				}
			}else{
				//判断该微信号是否已绑定
				UmsMember wxMember = memberService.getMemberByParams(msgResponse.getFromUserName(), MemberTypes.GZH_OPEN_ID);
				if(wxMember != null){
					if(StringUtils.isNotBlank(kefuWeixin)){
						return "<xml>\n" +
								"  <ToUserName><![CDATA["+msgResponse.getFromUserName()+"]]></ToUserName>\n" +
								"  <FromUserName><![CDATA["+msgResponse.getToUserName()+"]]></FromUserName>\n" +
								"  <CreateTime>"+DateUtil.currentSeconds()+"</CreateTime>\n" +
								"  <MsgType><![CDATA[text]]></MsgType>\n" +
								"  <Content><![CDATA[欢迎来到我们的大家庭，\n" +
								"您的微信已绑定了账号："+wxMember.getCode()+",\n" +
								"不能重复绑定，\n" +
								"如需解绑请发送'我要解绑'。\n" +
								"操作中有任何问题请及时联系客服【"+kefuWeixin+"】。" +
								"]]></Content>\n" +
								"</xml>";
					}else{
						return "<xml>\n" +
								"  <ToUserName><![CDATA["+msgResponse.getFromUserName()+"]]></ToUserName>\n" +
								"  <FromUserName><![CDATA["+msgResponse.getToUserName()+"]]></FromUserName>\n" +
								"  <CreateTime>"+DateUtil.currentSeconds()+"</CreateTime>\n" +
								"  <MsgType><![CDATA[text]]></MsgType>\n" +
								"  <Content><![CDATA[欢迎来到我们的大家庭，\n" +
								"您的微信已绑定了账号："+wxMember.getCode()+",\n" +
								"不能重复绑定，\n" +
								"如需解绑请发送'我要解绑'。\n" +
								"操作中有任何问题请及时联系客服。]]></Content>\n" +
								"</xml>";
					}
				}
				UmsMember haveMember = memberService.getMemberByParams(msgResponse.getContent(), MemberTypes.CODE);
				if(haveMember != null){
					UmsMember updateMember = new UmsMember();
					updateMember.setId(haveMember.getId());
					updateMember.setGzhOpenId(msgResponse.getFromUserName());
					updateMember.setGzhFocusStatus(CommonConstant.YES_INT);
					memberService.updateMember(updateMember);

					if(StringUtils.isNotBlank(kefuWeixin)){
						return "<xml>\n" +
								"  <ToUserName><![CDATA["+msgResponse.getFromUserName()+"]]></ToUserName>\n" +
								"  <FromUserName><![CDATA["+msgResponse.getToUserName()+"]]></FromUserName>\n" +
								"  <CreateTime>"+DateUtil.currentSeconds()+"</CreateTime>\n" +
								"  <MsgType><![CDATA[text]]></MsgType>\n" +
								"  <Content><![CDATA[欢迎来到我们的大家庭，\n" +
								"您的用户名是："+haveMember.getCode()+",\n" +
								"注意保管好您的账户信息，\n" +
								"密码打死也不要告诉任何人。\n" +
								"操作中有任何问题请及时联系客服【"+kefuWeixin+"】。" +
								"]]></Content>\n" +
								"</xml>";
					}else{
						return "<xml>\n" +
								"  <ToUserName><![CDATA["+msgResponse.getFromUserName()+"]]></ToUserName>\n" +
								"  <FromUserName><![CDATA["+msgResponse.getToUserName()+"]]></FromUserName>\n" +
								"  <CreateTime>"+DateUtil.currentSeconds()+"</CreateTime>\n" +
								"  <MsgType><![CDATA[text]]></MsgType>\n" +
								"  <Content><![CDATA[欢迎来到我们的大家庭，\n" +
								"您的用户名是："+haveMember.getCode()+",\n" +
								"注意保管好您的账户信息，\n" +
								"密码打死也不要告诉任何人。\n" +
								"操作中有任何问题请及时联系客服。]]></Content>\n" +
								"</xml>";
					}
				}
			}
		}

		return success;
	}

}