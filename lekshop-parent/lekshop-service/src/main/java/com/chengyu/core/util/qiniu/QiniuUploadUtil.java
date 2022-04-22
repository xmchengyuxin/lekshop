package com.chengyu.core.util.qiniu;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chengyu.core.utils.JsonUtils;
import com.google.protobuf.ServiceException;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

/**
 * @类描述           七牛上传工具
 * @作者   		LeGreen
 * @创建时间  	2018年11月27日
 * @版本   		1.00
 * @修改记录
 * <pre>
 *     版本          修改人    		修改日期 			修改内容描述
 *     -----------------------------------------------------------
 *     1.00   	LeGreen    	2018年11月27日             新建
 *     -----------------------------------------------------------
 * </pre>
 */
public class QiniuUploadUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QiniuUploadUtil.class);

	// 区域名称：z0 华东  z1 华北  z2 华南  na0 北美  as0 东南亚
	public static UploadManager getUploadManager(String area){
		Configuration cfg = null;
		switch (area) {
		case "z0":
			cfg = new Configuration(Region.huadong());
			break;
		case "z1":
			cfg = new Configuration(Region.huabei());
			break;

		case "z2":
			cfg = new Configuration(Region.huanan());
			break;

		case "na0":
			cfg = new Configuration(Region.beimei());
			break;

		case "as0":
			cfg = new Configuration(Region.xinjiapo());
			break;
			
		default:
			cfg = new Configuration(Region.huadong());
			break;
		}
		UploadManager uploadManager = new UploadManager(cfg);
		return uploadManager;
	}
    
    /**
     * @功能描述    获取凭证
     * @作者      LeGreen
     * @创建时间  2018年11月27日
     * @param 	  accessKey		AK
     * @param 	  secretKey		SK
     * @param 	  bucketName	空间名称
     * @param 	  key		        如果需要覆盖上传则设置此参数
     * @return	    凭证
     */
    public static String getUpToken(String accessKey, String secretKey, String bucketName, String key) {
    	Auth auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucketName, key);
    }
 
    /**
     * @功能描述    路径上传
     * @作者      LeGreen
     * @创建时间  2018年11月27日
     * @param 	  filePath		文件路径  （也可以是字节数组、或者File对象）
     * @param 	  key			上传到七牛上的文件的名称  （同一个空间下，名称【key】是唯一的）
     * @param 	  bucketName	空间名称 
     * @param 	  upToken	       上传凭证
     * @param 	  area			空间区域
     * @throws 	  ServiceException 
     */
    public static String upload(String filePath, String key, String bucketName, String upToken, String area) {
		try {
			Response res = getUploadManager(area).put(filePath, key, upToken);
			QiniuResponse response = JsonUtils.json2Object(res.bodyString(), QiniuResponse.class);
			return response.getKey();
		} catch (QiniuException e) {
			 LOGGER.warn("上传异常", e.getMessage());
		}
		return null;
    }
    
    /**
     * @功能描述    文件上传
     * @作者      LeGreen
     * @创建时间  2018年11月27日
     * @param 	  file			文件
     * @param 	  key			上传到七牛上的文件的名称  （同一个空间下，名称【key】是唯一的）
     * @param 	  bucketName	空间名称
     * @param 	  upToken		上传凭证
     * @param 	  area			空间区域
     * @return	  String		路径
     * @throws	  ServiceException 
     */
    public static String upload(byte[] file, String key, String bucketName, String upToken, String area){
		try {
			Response res = getUploadManager(area).put(file, key, upToken);
			QiniuResponse response =  JsonUtils.fromJson(res.bodyString(), QiniuResponse.class);
			return response.getKey();
		} catch (QiniuException e) {
			e.printStackTrace();
			LOGGER.warn("上传异常", e.getMessage());
		}
		return null;
    }
 
    /**
     * @功能描述    获取文件
     * @作者      LeGreen
     * @创建时间  2018年11月27日
     * @param 	  isPublic		是否公共空间获取
     * @param 	  domain		获取域名链接
     * @param 	  fileName		文件名称
     * @param 	  accessKey		AK
     * @param 	  secretKey		SK
     * @return	  String		url
     * @throws 	  ServiceException
     */
    public static String getFilePath(boolean isPublic, String domain, String fileName, String accessKey, String secretKey){
    	String encodedFileName = null;
		try {
			encodedFileName = URLEncoder.encode(fileName, "utf-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.warn("获取图片链接", e.getMessage());
		}
    	String url = String.format("%s/%s", domain, encodedFileName);
    	if(isPublic){
    		return url;
    	}else{
    		Auth auth = Auth.create(accessKey, secretKey);
			//1小时，可以自定义链接过期时间
    		long expireInSeconds = 3600;
    		String finalUrl = auth.privateDownloadUrl(url, expireInSeconds);
    		return finalUrl;
    	}
    }
    
}
