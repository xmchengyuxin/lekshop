package com.chengyu.core.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpUtil {
    public static Log logger = LogFactory.getLog(HttpUtil.class);
    //get请求
    public static JSONObject doGet(String requestUrl) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String responseContent  = null;
        JSONObject result = null;
        try {
            //创建Get请求，
            HttpGet httpGet = new HttpGet(requestUrl);
            //执行Get请求，
            response = httpClient.execute(httpGet);
            //得到响应体
            HttpEntity entity = response.getEntity();
            //获取响应内容
            responseContent  = EntityUtils.toString(entity,"UTF-8");
            //转换为map
            result = JSON.parseObject(responseContent);
        } catch (IOException e) {
            logger.error("HttpUtil=====Start");
            logger.error(e.getMessage(),e);
            logger.error("HttpUtil=====End");
        }
        return result;
    }
    
    public static String doPostByXml(String path, String xmlString) throws Exception {
		HttpClient client = new HttpClient(); 
        PostMethod myPost = new PostMethod(path); 
        client.getParams().setSoTimeout(300*1000); 
        String responseString = null; 
        try{ 
            myPost.setRequestEntity(new StringRequestEntity(xmlString,"text/xml","utf-8")); 
            myPost.setRequestHeader("charset","utf-8");
            int statusCode = client.executeMethod(myPost); 
            if(statusCode == HttpStatus.SC_OK){ 
                BufferedInputStream bis = new BufferedInputStream(myPost.getResponseBodyAsStream()); 
                byte[] bytes = new byte[1024]; 
                ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
                int count = 0; 
                while((count = bis.read(bytes))!= -1){ 
                    bos.write(bytes, 0, count); 
                } 
                byte[] strByte = bos.toByteArray(); 
                responseString = new String(strByte,0,strByte.length,"utf-8"); 
                bos.close(); 
                bis.close(); 
            } 
        }catch (Exception e) { 
        	logger.error(e.getMessage(), e);
        } 
        myPost.releaseConnection(); 
        client.getHttpConnectionManager().closeIdleConnections(0);
        return responseString;
	}
}