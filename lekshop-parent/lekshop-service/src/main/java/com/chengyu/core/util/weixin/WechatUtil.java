package com.chengyu.core.util.weixin;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.chengyu.core.model.SysWeixinConfig;
import com.chengyu.core.service.system.WeixinConfigService;
import com.chengyu.core.util.RedisUtil;
import com.chengyu.core.utils.MD5Util;
import com.chengyu.core.utils.StringUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Writer;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class WechatUtil {
	
	private static final String ACCESS_TOKEN_KEY = "access_token";
	public static final String JS_CODE_URL="https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
	public static final String AUTH2_URL="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	public static final String USERINFO_URL="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
	public static final String GEN_ORDER_URL="https://api.mch.weixin.qq.com/pay/unifiedorder";
	public static final String REFUND_ORDER_URL="https://api.mch.weixin.qq.com/secapi/pay/refund";
	public static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";
	public static final String WXACODE="https://api.weixin.qq.com/wxa/getwxacode?access_token=ACCESS_TOKEN";
	public static final String TEMPLATE_URL="https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=ACCESS_TOKEN";
	public static final String GZH_TEMPLATE_URL="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private WeixinConfigService weixinService;

	private static SysWeixinConfig config;

	@PostConstruct
	public void init() {
		config = weixinService.getWeixinConfig();
	}
	
	public static String MD5(Map<String,String> paramMap, String mchKey){
		StringBuffer signA = new StringBuffer();
		for(String key : paramMap.keySet()){
			signA.append(key+"="+paramMap.get(key)+"&");
		}
		String signTemp = signA.toString()+"key="+mchKey;
		return MD5Util.MD5Encode(signTemp,"utf-8").toUpperCase();
	}
	
	/**
     * bean转成微信的xml消息格式
     * @param object
     * @return
     */
    public static String beanToXml( Object object) {
        XStream xStream = getMyXStream();
        xStream.alias("xml", object.getClass());
        xStream.processAnnotations(object.getClass());
        String xml = xStream.toXML(object);
        if (!StringUtils.isEmpty(xml)){
            return xml;
        }else{
            return null;
        }
    }

	/**
     * xml转成bean泛型方法
     * @param resultXml
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T xmlToBean(String resultXml, Class clazz) {
        // XStream对象设置默认安全防护，同时设置允许的类
        XStream stream = new XStream(new DomDriver());
//        XStream.setupDefaultSecurity(stream);
//        stream.allowTypes(new Class[]{clazz});
        stream.processAnnotations(new Class[]{clazz});
//        stream.setMode(XStream.NO_REFERENCES);
        stream.alias("xml", clazz);
        return (T) stream.fromXML(resultXml);
    }

  //xstream扩展,bean转xml自动加上![CDATA[]]
    public static XStream getMyXStream() {
        return new XStream(new XppDriver() {
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) {
                    // 对所有xml节点都增加CDATA标记
                    boolean cdata = true;
 
                    @SuppressWarnings("rawtypes")
					@Override
                    public void startNode(String name, Class clazz) {
                        super.startNode(name, clazz);
                    }
 
                    @Override
                    protected void writeText(QuickWriter writer, String text) {
                        if (cdata) {
                            writer.write("<![CDATA[");
                            writer.write(text);
                            writer.write("]]>");
                        } else {
                            writer.write(text);
                        }
                    }
                };
            }
        });
    }
    
    
    public void sendWxMessage(JSONObject paramsMap) {
		String accessToken = getAccessToken();
		if(StringUtils.isBlank(accessToken)){
			return;
		}
		String message;
		try {
			message = HttpUtil.post(GZH_TEMPLATE_URL.replace("ACCESS_TOKEN", accessToken), paramsMap.toString());
			log.info("微信模板消息>>"+message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public String getAccessToken() {
    	String key = ACCESS_TOKEN_KEY;
		String access_token = (String) redisUtil.getRedisValue(key);
		if(StringUtils.isEmpty(access_token)){
			SysWeixinConfig config = WechatUtil.config;
			JSONObject jsonObject = com.chengyu.core.utils.HttpUtil.doGet(
					ACCESS_TOKEN_URL
					.replace("APPID", config.getGzhAppId())
					.replace("SECRET", config.getGzhSecret()));
			log.info("jsonObject>>"+jsonObject);
			access_token = jsonObject.getString("access_token");
			if(!StringUtils.isEmpty(access_token)){
				redisUtil.delete(key);
				redisUtil.setRedisValue(key, access_token, jsonObject.getLong("expires_in"), TimeUnit.SECONDS);
			}
		}
		log.info("access_token>>"+access_token);
		return access_token;
	}
    
}