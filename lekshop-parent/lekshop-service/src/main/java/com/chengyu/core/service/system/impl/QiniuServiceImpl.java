package com.chengyu.core.service.system.impl;

import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.mapper.SysQiniuConfigMapper;
import com.chengyu.core.model.SysQiniuConfig;
import com.chengyu.core.service.system.QiniuService;
import com.chengyu.core.util.RedisUtil;
import com.chengyu.core.util.qiniu.QiniuUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QiniuServiceImpl implements QiniuService {
	
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private SysQiniuConfigMapper qiniuConfigMapper;
	
	@Override
	public SysQiniuConfig getQiniuConfig(){
		SysQiniuConfig config = (SysQiniuConfig)redisUtil.getRedisValue(RedisEnums.QINIU_CONFIG_KEY.getKey());
		if(config != null) {
			return config;
		}
		
		List<SysQiniuConfig> configList = qiniuConfigMapper.selectByExample(null);
		if(configList == null || configList.isEmpty()) {
			return null;
		}
		
		config = configList.get(0);
		redisUtil.setRedisValue(RedisEnums.QINIU_CONFIG_KEY.getKey(), config);
		
		return config;
	}

	@Override
	public void updateQiniuConfig(SysQiniuConfig config){
		if(config.getId() == null){
			SysQiniuConfig cf = getQiniuConfig();
			config.setId(cf.getId());
		}
		qiniuConfigMapper.updateByPrimaryKeySelective(config);
		redisUtil.delete(RedisEnums.QINIU_CONFIG_KEY.getKey());
	}

	@Override
	public String uploadByImg(byte[] file, String fileName){
		SysQiniuConfig config = this.getQiniuConfig();
		String ak = config.getAccessKey();
		String sk = config.getSecretKey();
		String bucketName = config.getImgBucket();
		String area = config.getImgZone();
		boolean isPublic = true;
		String viewUrl = config.getImgViewUrl();
		
		String upToken = QiniuUploadUtil.getUpToken(ak, sk, bucketName, fileName);
		String pathKey = QiniuUploadUtil.upload(file, fileName, bucketName, upToken, area);
		return QiniuUploadUtil.getFilePath(isPublic, viewUrl, pathKey, ak, sk);
	}

	@Override
	public String uploadByVideo(byte[] file, String fileName){
		SysQiniuConfig config = this.getQiniuConfig();
		String ak = config.getAccessKey();
		String sk = config.getSecretKey();
		String bucketName = config.getVideoBucket();
		String area = config.getVideoZone();
		boolean isPublic = true;
		String viewUrl = config.getVideoViewUrl();
		
		String upToken = QiniuUploadUtil.getUpToken(ak, sk, bucketName, fileName);
		String pathKey = QiniuUploadUtil.upload(file, fileName, bucketName, upToken, area);
		return QiniuUploadUtil.getFilePath(isPublic, viewUrl, pathKey, ak, sk);
	}
	

}