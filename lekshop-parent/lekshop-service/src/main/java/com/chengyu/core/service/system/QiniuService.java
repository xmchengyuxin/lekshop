package com.chengyu.core.service.system;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.SysQiniuConfig;

/**
 * @类描述  		七牛接口 	
 * @作者   		LeGreen
 * @创建时间  	2018年11月28日
 * @版本   		1.00
 * @修改记录
 * <pre>
 *     版本          修改人    		修改日期 			修改内容描述
 *     -----------------------------------------------------------
 *     1.00   	LeGreen    	2018年11月28日             新建
 *     -----------------------------------------------------------
 * </pre>
 */
public interface QiniuService {
	
	/**
	 * @功能描述    获取七牛配置信息
	 * @作者      LeGreen
	 * @创建时间  2018年11月28日
	 * @return	  QiniuConfig
	 * @throws 	  ServiceException
	 */
	SysQiniuConfig getQiniuConfig() throws ServiceException;
	
	/**
	 * @功能描述    更新七牛配置
	 * @作者      LeGreen
	 * @创建时间  2018年11月28日
	 * @throws 	  ServiceException
	 */
	void updateQiniuConfig(SysQiniuConfig config) throws ServiceException;
	
	/**
	 * @功能描述    文件上传
	 * @作者      LeGreen
	 * @创建时间  2018年12月1日
	 * @param 	  file		文件
	 * @return	  String	图片路径
	 * @throws 	  ServiceException
	 */
	String uploadByImg(byte[] file, String fileName) throws ServiceException;
	
	/**
	 * @功能描述		视频上传
	 * @作者			LeGreen
	 * @创建时间		2020年2月27日
	 * @param 		file
	 * @param 		fileName
	 * @return
	 * @throws 		ServiceException
	 */
	String uploadByVideo(byte[] file, String fileName) throws ServiceException;

}