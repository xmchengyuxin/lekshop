package com.chengyu.core.service.member.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.MemberNewsEnums;
import com.chengyu.core.model.ConfigGzh;
import com.chengyu.core.model.SysWeixinTemplate;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberWithdraw;
import com.chengyu.core.service.config.ConfigGzhService;
import com.chengyu.core.service.member.MemberService;
import com.chengyu.core.service.member.WeixinNoticeService;
import com.chengyu.core.service.system.WeixinTemplateService;
import com.chengyu.core.util.sms.SmsUtil;
import com.chengyu.core.util.weixin.WechatUtil;
import com.chengyu.core.utils.NumberUtils;
import com.chengyu.core.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @title  微信通知
 * @author LeGreen
 * @date   2021/2/5
 */
@Service
public class WeixinNoticeServiceImpl implements WeixinNoticeService {

	@Autowired
	private MemberService memberService;
	@Autowired
	private WechatUtil wechatUtil;
	@Autowired
	private WeixinTemplateService weixinTemplateService;
	@Autowired
	private SmsUtil smsUtil;
	@Autowired
	private ConfigGzhService configGzhService;

	@Override
	public void closeAccount(UmsMember member, String reason) {
		NoticeStatus noticeConfig = getNoticeConfig();
		if(noticeConfig.isSmsCloseAccount()){
			this.sendSms(member.getPhone(), "你的账号被禁用，如果疑问，可咨询客服");
		}
		if(noticeConfig.isGzh()) {
			if (member == null || StringUtils.isBlank(member.getGzhOpenId())) {
				return;
			}

			JSONObject paramsMap = new JSONObject();
			paramsMap.put("touser", member.getGzhOpenId());
			SysWeixinTemplate template = weixinTemplateService.getWeixinTemplate(MemberNewsEnums.WeixinNoticeTypes.NOTICE_ACCOUNT_CLOSE.getTemplateId());
			if (template == null) {
				return;
			}
			paramsMap.put("template_id", template.getTemplateId());

			Map<String, Object> dataMap = new HashMap<>(16);

			Map<String, Object> valueDataMap = new HashMap<>(16);
			valueDataMap.put("value", "账号禁用通知");
			dataMap.put("first", valueDataMap);

			valueDataMap = new HashMap<>(16);
			valueDataMap.put("value", member.getCode());
			dataMap.put("keyword1", valueDataMap);

		/*valueDataMap = new HashMap<>(16);
		valueDataMap.put("value", "您的账号已被禁用");
		dataMap.put("keyword2", valueDataMap);*/


			valueDataMap = new HashMap<>(16);
			valueDataMap.put("value", reason);
			dataMap.put("keyword2", valueDataMap);

			valueDataMap = new HashMap<>(16);
			valueDataMap.put("value", DateUtil.formatDate(DateUtil.date()));
			dataMap.put("keyword3", valueDataMap);

		/*valueDataMap = new HashMap<>(16);
		valueDataMap.put("value", "违反平台规则");
		dataMap.put("keyword5", valueDataMap);*/

			valueDataMap = new HashMap<>(16);
			valueDataMap.put("value", "你的账号已于" + DateUtil.now() + "被禁用，如果疑问，可咨询客服");
			valueDataMap.put("color", "#58ACFA");
			dataMap.put("remark", valueDataMap);

			paramsMap.put("data", dataMap);
			senWxMessage(paramsMap);
		}
	}

	@Override
	public void withdrawSusNotice(UmsMemberWithdraw withdraw) {
		UmsMember member = memberService.getMemberById(withdraw.getMemberId());
		NoticeStatus noticeConfig = getNoticeConfig();
		if(noticeConfig.isSmsWithdrawSus()){
			this.sendSms(member.getPhone(), "尊敬的用户，您的提现已经成功到账，请及时查收。");
		}
		if(noticeConfig.isGzh()) {
			if (member == null || StringUtils.isBlank(member.getGzhOpenId())) {
				return;
			}

			JSONObject paramsMap = new JSONObject();
			paramsMap.put("touser", member.getGzhOpenId());
			SysWeixinTemplate template = weixinTemplateService.getWeixinTemplate(MemberNewsEnums.WeixinNoticeTypes.NOTICE_WITHDRAW_SUS.getTemplateId());
			if (template == null) {
				return;
			}
			paramsMap.put("template_id", template.getTemplateId());

			Map<String, Object> dataMap = new HashMap<>(16);

			Map<String, Object> valueDataMap = new HashMap<>(16);
			valueDataMap.put("value", "尊敬的用户，您的提现已经成功到账，请及时查收。");
			dataMap.put("first", valueDataMap);

			valueDataMap = new HashMap<>(16);
			valueDataMap.put("value", NumberUtils.format2(withdraw.getTotalAmount()));
			dataMap.put("keyword1", valueDataMap);

			valueDataMap = new HashMap<>(16);
			valueDataMap.put("value", DateUtil.formatDateTime(withdraw.getAddTime()));
			dataMap.put("keyword2", valueDataMap);

			valueDataMap = new HashMap<>(16);
			valueDataMap.put("value", "您好，您的申请已经到账，请留意查看。");
			valueDataMap.put("color", "#58ACFA");
			dataMap.put("remark", valueDataMap);

			paramsMap.put("data", dataMap);
			senWxMessage(paramsMap);
		}
	}

	@Override
	public void withdrawFailNotice(UmsMemberWithdraw withdraw) {
		UmsMember member = memberService.getMemberById(withdraw.getMemberId());
		NoticeStatus noticeConfig = getNoticeConfig();
		if(noticeConfig.isSmsWithdrawFail()){
			this.sendSms(member.getPhone(), "尊敬的用户，您的提现未能完成，请及时登录账号处理。");
		}
		if(noticeConfig.isGzh()) {
			if (member == null || StringUtils.isBlank(member.getGzhOpenId())) {
				return;
			}

			JSONObject paramsMap = new JSONObject();
			paramsMap.put("touser", member.getGzhOpenId());
			SysWeixinTemplate template = weixinTemplateService.getWeixinTemplate(MemberNewsEnums.WeixinNoticeTypes.NOTICE_WITHDRAW_FAIL.getTemplateId());
			if (template == null) {
				return;
			}
			paramsMap.put("template_id", template.getTemplateId());

			Map<String, Object> dataMap = new HashMap<>(16);

			Map<String, Object> valueDataMap = new HashMap<>(16);
			valueDataMap.put("value", "尊敬的用户，您的提现未能完成，请及时登录账号处理。");
			dataMap.put("first", valueDataMap);

			valueDataMap = new HashMap<>(16);
			valueDataMap.put("value", NumberUtils.format2(withdraw.getTotalAmount()));
			dataMap.put("keyword1", valueDataMap);

			valueDataMap = new HashMap<>(16);
			valueDataMap.put("value", DateUtil.formatDateTime(DateUtil.date()));
			dataMap.put("keyword2", valueDataMap);

			valueDataMap = new HashMap<>(16);
			if (withdraw.getType() == 1) {
				valueDataMap.put("value", "银行");
			} else if (withdraw.getType() == 2) {
				valueDataMap.put("value", "支付宝");
			} else if (withdraw.getType() == 3) {
				valueDataMap.put("value", "微信");
			}
			dataMap.put("keyword3", valueDataMap);

			valueDataMap = new HashMap<>(16);
			valueDataMap.put("value", withdraw.getRemark());
			dataMap.put("keyword4", valueDataMap);

			valueDataMap = new HashMap<>(16);
			valueDataMap.put("value", StringUtils.isBlank(withdraw.getRemark()) ? "请认真核对好提现的账号与名字是否正确" : withdraw.getRemark());
			valueDataMap.put("color", "#58ACFA");
			dataMap.put("remark", valueDataMap);

			paramsMap.put("data", dataMap);
			senWxMessage(paramsMap);
		}
	}

	private String getPrivacyStr(String content){
		StringBuilder stringBuffer = new StringBuilder();
		for(int i = 0; i < content.length(); i++){
			if((i & 1) != 0){
				stringBuffer.append("*");
			}else{
				stringBuffer.append(content.charAt(i));
			}
		}
		return stringBuffer.toString();
	}

	private void senWxMessage(JSONObject paramsMap){
		try {
			wechatUtil.sendWxMessage(paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Getter
	@Setter
	private class  NoticeStatus{
		boolean isGzh;
		boolean smsNewTask;
		boolean smsNeedComment;
		boolean smsCommentReject;
		boolean smsOrderWillQuit;
		boolean smsCloseAccount;
		boolean smsWithdrawSus;
		boolean smsWithdrawFail;
		boolean smsOrderReject;
		boolean smsConfirmOrder;

		NoticeStatus(boolean isGzh,
					 boolean smsNewTask,
					 boolean smsNeedComment,
					 boolean smsCommentReject,
					 boolean smsOrderWillQuit,
					 boolean smsCloseAccount,
					 boolean smsWithdrawSus,
					 boolean smsWithdrawFail,
					 boolean smsOrderReject,
					 boolean smsConfirmOrder){
			this.isGzh = isGzh;
			this.smsNewTask = smsNewTask;
			this.smsNeedComment = smsNeedComment;
			this.smsCommentReject = smsCommentReject;
			this.smsOrderWillQuit = smsOrderWillQuit;
			this.smsCloseAccount = smsCloseAccount;
			this.smsWithdrawSus = smsWithdrawSus;
			this.smsWithdrawFail = smsWithdrawFail;
			this.smsOrderReject = smsOrderReject;
			this.smsConfirmOrder = smsConfirmOrder;
		}
	}

	private NoticeStatus getNoticeConfig(){
		ConfigGzh config = configGzhService.getGzh();
		boolean isGzh = config != null && config.getBuyerStatus() != CommonConstant.NO_INT;
		boolean isSms = config != null && config.getSmsNoticeStatus() != CommonConstant.NO_INT;
		return new NoticeStatus(isGzh,
				isSms && config.getSmsNewTask() == CommonConstant.YES_INT,
				isSms && config.getSmsNeedComment() == CommonConstant.YES_INT,
				isSms && config.getSmsCommentReject() == CommonConstant.YES_INT,
				isSms && config.getSmsOrderWillQuit() == CommonConstant.YES_INT,
				isSms && config.getSmsCloseAccount() == CommonConstant.YES_INT,
				isSms && config.getSmsWithdrawSus() == CommonConstant.YES_INT,
				isSms && config.getSmsWithdrawFail() == CommonConstant.YES_INT,
				isSms && config.getSmsOrderReject() == CommonConstant.YES_INT,
				isSms && config.getSmsConfirmOrder() == CommonConstant.YES_INT);
	}

	private void sendSms(String phone, String content) {
		try {
			Map<String, String> templateParam = new HashMap<>(16);
			templateParam.put("content", content);
			smsUtil.send(phone, "1", templateParam);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}