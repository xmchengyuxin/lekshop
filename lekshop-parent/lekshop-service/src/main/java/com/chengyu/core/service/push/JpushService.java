package com.chengyu.core.service.push;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.service.push.domain.NotifyEnum;

/**
 * @title  极光推送接口
 * @author LeGreen
 * @date   2021/1/5
 */
public interface JpushService {

	/**
	 * @功能描述	发送给所有人
	 * @作者		LeGreen
	 * @创建时间	2019年10月26日
	 * @param 	title
	 * @param 	content
	 * @throws 	ServiceException
	 */
	public void sendPushForAll(String title, String content, Map<String, String> extras) throws ServiceException;
	
	/**
	 * @功能描述	发送特定人
	 * @作者		LeGreen
	 * @创建时间	2019年10月26日
	 * @param 	title
	 * @param 	content
	 * @param 	extras
	 * @throws 	ServiceException
	 */
	public void sendPushByAlias(String title, String content, Map<String, String> extras, String[] aliasList) throws ServiceException;
	
	/**
	 * @功能描述	发送特定人
	 * @作者		LeGreen
	 * @创建时间	2019年10月26日
	 * @param 	title
	 * @param 	content
	 * @param 	extras
	 * @param 	tagsList
	 * @throws 	ServiceException
	 */
	public void sendPushByTags(String title, String content, Map<String, String> extras, String[] tagsList) throws ServiceException;
	
	/**
	 * @功能描述	发送特定人
	 * @作者		LeGreen
	 * @创建时间	2019年10月26日
	 * @param 	title
	 * @param 	content
	 * @param 	extras
	 * @param 	list
	 * @throws 	ServiceException
	 */
	public void sendPushByRegistrationId(String title, String content, Map<String, String> extras, Collection<String> list) throws ServiceException;
	
	/**
	 * @功能描述	发送特定人
	 * @作者		LeGreen
	 * @创建时间	2019年10月26日
	 * @param 	title
	 * @param 	content
	 * @param 	extras
	 * @param 	list
	 * @throws 	ServiceException
	 */
	public void sendPushByRegistrationId(String title, String content, String sound, Map<String, String> extras, Collection<String> list) throws ServiceException;
	
	/**
	 * @功能描述	删除别名
	 * @作者		LeGreen
	 * @创建时间	2020年1月14日
	 * @param 	registrationId
	 * @throws 	ServiceException
	 */
	public void deleteAlias(String registrationId) throws ServiceException;
	
	public void sendPushByRegistrationId(String title, String content, String registrationId, NotifyEnum notifyEnum) throws ServiceException;
	
	public void sendPushByRegistrationId(String title, String content, List<String> registrationIdList, NotifyEnum notifyEnum) throws ServiceException;
}