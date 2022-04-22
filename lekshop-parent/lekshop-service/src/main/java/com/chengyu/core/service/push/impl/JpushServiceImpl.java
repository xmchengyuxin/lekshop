package com.chengyu.core.service.push.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.service.push.JpushService;
import com.chengyu.core.service.push.domain.NotifyEnum;
import com.chengyu.core.util.jiguang.JiguangUtil;
import com.chengyu.core.utils.JsonUtils;
import com.chengyu.core.utils.StringUtils;

import cn.hutool.core.collection.CollectionUtil;
import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

@Service
public class JpushServiceImpl implements JpushService {

	protected static final Logger LOG = LoggerFactory.getLogger(JpushServiceImpl.class);
	 
//	@Autowired
//	private ConfigService configService;
	
	private JPushClient jPushClient = null;
	
	@Override
	public void sendPushForAll(String title, String content, Map<String, String> extras) throws ServiceException {
		PushPayload.Builder payloadBuilder = new PushPayload.Builder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.all())
                .setNotification(this.getNotifucation(title, content, null, extras));
		
		this.sendPush(payloadBuilder.build());
	}

	@Override
	public void sendPushByAlias(String title, String content, Map<String, String> extras, String[] aliasList)
			throws ServiceException {
		System.out.println("别名集合:"+aliasList);
		PushPayload.Builder payloadBuilder = PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(aliasList))
                .setNotification(this.getNotifucation(title, content, null, extras));
		
		this.sendPush(payloadBuilder.build());
	}

	@Override
	public void sendPushByTags(String title, String content, Map<String, String> extras, String[] tagsList)
			throws ServiceException {
		if(tagsList == null || tagsList.length <= 0) {
			return;
		}
		
		PushPayload.Builder payloadBuilder = new PushPayload.Builder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.tag(tagsList))
                .setNotification(this.getNotifucation(title, content, null, extras));
		
		this.sendPush(payloadBuilder.build());
	}

	@Override
	public void sendPushByRegistrationId(String title, String content, Map<String, String> extras,
			Collection<String> list) throws ServiceException {
		this.sendPushByRegistrationId(title, content, null, extras, list);
	}
	
	@Override
	public void sendPushByRegistrationId(String title, String content, String sound, Map<String, String> extras,
			Collection<String> list) throws ServiceException {
		if(list == null || list.isEmpty()) {
			return;
		}
		list = list.stream().distinct().collect(Collectors.toList());
		if(StringUtils.isEmpty(content)) {
			content = title;
		}
		
		//不支持自定义声音
//		PushPayload.Builder	payloadBuilder = new PushPayload.Builder()
//                .setPlatform(Platform.android_ios())
//                .setAudience(Audience.registrationId(list))
//                .setNotification(this.getNotifucation(title, content, sound, extras));
		
		//支持自定义声音
		String rids = "";
		for(String rid : list){
			rids = rids + ","+ "\""+rid+"\"";
		}
		String payloadString = "{\"platform\":\"all\",\"audience\":{\"registration_id\":["+rids.substring(1)+"]},\"notification\":{"
				+ "\"android\":{\"alert\" : \""+title+"\", \"title\" : \""+content+"\", \"sound\":\""+sound+"\", \"extras\":"+JsonUtils.object2JsonString(extras)+"},"
				+ "\"ios\":{\"alert\" : \""+content+"\", \"badge\" : \"+1\", \"sound\":\""+sound+".wav\", \"extras\":"+JsonUtils.object2JsonString(extras)+"}"
				+ "},\"options\": {\"apns_production\": true}}";
		 
		try {
			this.sendPush(payloadString);
		} catch (Exception e) {
			LOG.info(e.getMessage());
		}
	}
	
	 public void sendPush(String payloadString) throws ServiceException {
//		 String jpushSwich = configService.getValueByNid("jpush_swich");
//		 if(StringUtils.isBlank(jpushSwich) || "0".equals(jpushSwich)) return;
		 
		 if(jPushClient == null){
	 		jPushClient = new JPushClient(JiguangUtil.getAppSecret(), JiguangUtil.getAppKey(), null, ClientConfig.getInstance());
	 	}
        try {
            PushResult result = jPushClient.sendPush(payloadString);
            LOG.info("Got result - " + result);
            System.out.println(result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }
	 
	 public void sendPush(PushPayload pushPayload) throws ServiceException {
//		 String jpushSwich = configService.getValueByNid("jpush_swich");
//		 if(StringUtils.isBlank(jpushSwich) || "0".equals(jpushSwich)) return;
		 
		 if(jPushClient == null){
	 		jPushClient = new JPushClient(JiguangUtil.getAppSecret(), JiguangUtil.getAppKey(), null, ClientConfig.getInstance());
	 	}
        try {
            PushResult result = jPushClient.sendPush(pushPayload);
            LOG.info("Got result - " + result);
            System.out.println(result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }
	 
	 private Notification getNotifucation(String title, String content, String sound, Map<String,String> extras){
		 if(StringUtils.isNotBlank(sound)){
			 Notification notification = Notification.newBuilder()
	         .setAlert(content)
	         .addPlatformNotification(AndroidNotification.newBuilder()
	                 .setTitle(title)
	                 .addExtra("sound", sound)
	                 .addExtras(extras).build())
	         .addPlatformNotification(IosNotification.newBuilder()
	        		 .setAlert(title)
	                 .incrBadge(1)
	                 .setSound(sound+".caf")
	                 .addExtras(extras).build())
	         .build();
			return notification;
		 }else{
			 Notification notification = Notification.newBuilder()
			         .setAlert(content)
			         .addPlatformNotification(AndroidNotification.newBuilder()
			                 .setTitle(title)
			                 .addExtras(extras).build())
			         .addPlatformNotification(IosNotification.newBuilder()
			        		 .setAlert(content)
			                 .incrBadge(1)
			                 .addExtras(extras).build())
			         .build();
					return notification;
		 }
	 }

	@Override
	public void deleteAlias(String registrationId) throws ServiceException {
		if(jPushClient == null){
	 		jPushClient = new JPushClient(JiguangUtil.getAppSecret(), JiguangUtil.getAppKey(), null, ClientConfig.getInstance());
	 	}
		try {
			jPushClient.updateDeviceTagAlias(registrationId, true, true);
		} catch (APIConnectionException | APIRequestException e) {
			 LOG.error("clear alias failed", e);
		}
	}

	@Override
	public void sendPushByRegistrationId(String title, String content, String registrationId, NotifyEnum notifyEnum)
			throws ServiceException {
		if(StringUtils.isBlank(registrationId)) {
			return;
		}
		List<String> ridList = new ArrayList<>();
		ridList.add(registrationId);
		Map<String,String> extras = new HashMap<>();
		extras.put("key", notifyEnum.getKey());
		extras.put("name", notifyEnum.getName());
		this.sendPushByRegistrationId(title, content, notifyEnum.getSound(), extras, ridList);
	}
	
	@Override
	public void sendPushByRegistrationId(String title, String content, List<String> ridList, NotifyEnum notifyEnum)
			throws ServiceException {
		if(CollectionUtil.isEmpty(ridList)) {
			return;
		}
		Map<String,String> extras = new HashMap<>();
		extras.put("key", notifyEnum.getKey());
		extras.put("name", notifyEnum.getName());
		this.sendPushByRegistrationId(title, content, notifyEnum.getSound(), extras, ridList);
	}
	
}