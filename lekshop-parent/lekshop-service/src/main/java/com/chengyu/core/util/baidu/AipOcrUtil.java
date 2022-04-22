package com.chengyu.core.util.baidu;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.utils.StringUtils;

public class AipOcrUtil {

	public static final String APP_ID = "17711538";
	public static final String API_KEY = "aAyh5ng6LgY57tRfyO5GZ6Oz";
	public static final String SECRET_KEY = "Pb1Gh9G7GCRpymvrfINY4WtIlHszp6dL";
	
	public static AipOcr client = null;
	
	public static AipOcr getAipOcrClient(){
		if(client == null){
			// 初始化一个AipOcr
	        client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
	
	        // 可选：设置网络连接参数
	        client.setConnectionTimeoutInMillis(2000);
	        client.setSocketTimeoutInMillis(60000);
		}
		return client;
	}
	
	public static JSONObject iocrAi(byte[] file, String templateSign, String classifierId){
		// 传入可选参数调用接口
	    HashMap<String, String> options = new HashMap<String, String>();
	    options.put("templateSign", templateSign);
	    options.put("classifierId", classifierId);
	    
	    
	    // 参数为本地路径
	    JSONObject res = getAipOcrClient().custom(file, options);
	    return res;
	}
	
	public static JSONObject getIdCard(byte[] file, String idCardSide) throws ServiceException {
	    // 传入可选参数调用接口
	    HashMap<String, String> options = new HashMap<String, String>();
	    options.put("detect_direction", "true");
	    options.put("detect_risk", "true");
	    
	    // 参数为二进制数组
	    JSONObject res = getAipOcrClient().idcard(file, idCardSide, options);
	    System.out.println(res.toString(2));
	    if(!res.has("image_status")) throw new ServiceException("身份证识别失败,请手动填写");
	    String imageStatus = res.getString("image_status");
	    if(!"normal".equals(imageStatus)){
	    	if("reversed_side".equals(imageStatus)){
	    		throw new ServiceException("未摆正身份证");	    		
	    	}else if("non_idcard".equals(imageStatus)){
	    		throw new ServiceException("上传的图片中不包含身份证");	    
	    	}else if("blurred".equals(imageStatus)){
	    		throw new ServiceException("身份证模糊");	    
	    	}else if("over_exposure".equals(imageStatus)){
	    		throw new ServiceException("身份证关键字段反光或过曝");	    
	    	}else if("unknown".equals(imageStatus)){
	    		throw new ServiceException("未摆正身份证");	    
	    	}
	    }
	    
	    String risktype = res.getString("risk_type");
	    if(StringUtils.isNotBlank(risktype) && !"normal".equals(risktype)){
	    	throw new ServiceException("请上传身份证原件");
	    }
	    return res;
	}
	
	public static JSONObject getBusinessLicense(byte[] file) throws ServiceException {
	    // 传入可选参数调用接口
	    HashMap<String, String> options = new HashMap<String, String>();
	    
	    // 参数为二进制数组
	    JSONObject res = getAipOcrClient().businessLicense(file, options);
	    if(res.has("error_code") && res.getInt("error_code") == 216201) {
			throw new ServiceException("自动识别失败");
		}
	    System.out.println(res.toString(2));
	    return res;
	}

	public static JSONObject getBank(byte[] file) {
		// 传入可选参数调用接口
	    HashMap<String, String> options = new HashMap<String, String>();
	    
	    // 参数为二进制数组
	    JSONObject res = getAipOcrClient().bankcard(file, options);
	    System.out.println(res.toString(2));
	    return res;
	}
	
}
