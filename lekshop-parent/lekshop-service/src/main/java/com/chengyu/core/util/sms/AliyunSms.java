package com.chengyu.core.util.sms;

import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.map.MapUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.chengyu.core.utils.JsonUtils;

/**
 * @类描述   	阿里云通讯
 * @作者   		LeGreen
 * @创建时间  	2018年11月30日
 * @版本   		1.00
 * @修改记录
 * <pre>
 *     版本          修改人    		修改日期 			修改内容描述
 *     -----------------------------------------------------------
 *     1.00   	LeGreen    	2018年11月30日             新建
 *     -----------------------------------------------------------
 * </pre>
 */
public class AliyunSms {

    public String sendMsg(String phone, String account, String password,
                          String appId, String sendUrl, String port, String templateId, Map<String, String> templateParam){
        DefaultProfile profile = DefaultProfile.getProfile("default", account, password);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(sendUrl);
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", appId);
        request.putQueryParameter("TemplateCode", templateId);
        Map<String, String> params = new HashMap<>();
        params.put("code", MapUtil.getStr(templateParam, "code"));
        request.putQueryParameter("TemplateParam", JsonUtils.object2JsonString(params));
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            AliyunSmsResult result = JsonUtils.fromJson(response.getData(), AliyunSmsResult.class);
            if("OK".equals(result.getCode())){
                return null;
            }else{
                return result.getMessage();
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }
}
