package com.chengyu.core.util.sms;

import cn.hutool.core.map.MapUtil;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.SysAlismsConfig;
import com.chengyu.core.service.system.AlismsConfigService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 短信工具类
 * @author msi-
 */
@Repository
public class SmsUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmsUtil.class);

	/**
	 * 短信服务商--阿里云
	 */
	public static final String SMS_ALIYUN = "Aliyun";
	/**
	 * 短信服务商--短信宝
	 */
	public static final String SMS_DXB = "dxb";
	/**
	 * 短信服务商--中正云
	 */
	public static final String SMS_ZZY = "zzy";

	@Autowired
	private AlismsConfigService smsService;

	/**
	 * 发送短信
	 * @param phone 要发送的电话号码，多个用,隔开
	 * @param sendType 1注册  2绑定  3忘记密码 4登录
	 * @param templateParam 模板中需要替换的参数列表，如#code# -- 123456
	 * @param sendType 发送类型：0:重新发送 1：立即发送 2:延迟发送
	 * @return 发送信息的短信服务商
	 * @throws ServiceException
	 */
	public void send(String phone, String sendType, Map<String, String> templateParam) throws ServiceException {
		if(StringUtils.isEmpty(phone)) {
			throw new ServiceException("Phone can not be null");
		}
		if(StringUtils.isEmpty(sendType)) {
			throw new ServiceException("SendType can not be null");
		}

		SysAlismsConfig smsConfig = smsService.getAlismsConfig();
		if(smsConfig == null) {
			return;
		}
		String registerTemplateId = smsConfig.getRegisterTemplateId();
		String bindTemplateId = smsConfig.getBindTemplateId();
		String forgetTemplateId = smsConfig.getForgetTemplateId();
		String commonTemplateId = smsConfig.getCommonTemplateId();
		String passwordTemplateId = smsConfig.getPasswordTemplateId();
		String deliveryTemplateId = smsConfig.getDeliveryTemplateId();
		String account = smsConfig.getAccount();
		String password = smsConfig.getPassword();
		String signName = smsConfig.getSignName();
		String sendUrl = smsConfig.getSendUrl();
		String serverPort = smsConfig.getServerPort();
		String smsType = smsConfig.getSmsType();

		String templateId = null;
		switch (sendType) {
			case "1":
				templateId = registerTemplateId;
				break;

			case "2":
				templateId = bindTemplateId;
				break;

			case "3":
				templateId = forgetTemplateId;
				break;

			case "4":
				templateId = commonTemplateId;
				break;

			case "5":
				templateId = passwordTemplateId;
				break;

			case "6":
				templateId = deliveryTemplateId;
				break;

			default:
				break;
		}

		String result = null;
		try {
			if(com.chengyu.core.utils.StringUtils.isBlank(smsType) || SMS_ALIYUN.equals(smsType)){
				result = new AliyunSms().sendMsg(phone,
						account,
						password,
						signName,
						sendUrl,
						serverPort,
						templateId,
						templateParam);
			}else if(smsType.equals(SMS_DXB)){
				result = new DuanxinBaoSms().sendMsg(phone, account, password, "【"+signName+"】" + MapUtil.getStr(templateParam, "content"), sendUrl);
			}else if(smsType.equals(SMS_ZZY)){
				result = new ZhongZhengYunSms().sendMsg(phone, account, password, "【"+signName+"】" + MapUtil.getStr(templateParam, "content"), sendUrl);
			}
		} catch(Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		if(StringUtils.isNotBlank(result)){
			throw new ServiceException(result);
		}

	}


	/**
	 * 发送短信
	 * @param phone 要发送的电话号码，多个用,隔开
	 * @param content 短信内容
	 * @throws ServiceException
	 */
	public String send(String phone, String content, String smsType) throws ServiceException {
		if(StringUtils.isEmpty(phone)) {
			throw new ServiceException("Phone can not be null");
		}

		SysAlismsConfig smsConfig = smsService.getAlismsConfig();
		if(smsConfig == null) {
			return "获取短信配置失败";
		}
		String account = smsConfig.getAccount();
		String password = smsConfig.getPassword();
		String signName = smsConfig.getSignName();
		String sendUrl = smsConfig.getSendUrl();

		String result = null;
		try {
			if(smsType.equals(SMS_DXB)){
				result = new DuanxinBaoSms().sendMsg(phone, account, password, "【"+signName+"】" + content, sendUrl);
			}else if(smsType.equals(SMS_ZZY)){
				result = new ZhongZhengYunSms().sendMsg(phone, account, password, "【"+signName+"】" + content, sendUrl);
			}
		} catch(Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		return result;
	}

}