package com.chengyu.core.util.sms;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @title  中正云通讯
 * @author LeGreen
 * @date   2021/1/29
 */
public class ZhongZhengYunSms {

	public String sendMsg(String phone, String account, String password, String content,  String sendUrl) throws Exception {
        String HTTP_BACK_MESSAGE = HTTP_POST(sendUrl, "id="+account+"&pwd="+password+"&to="+phone+"&content=" + URLEncoder.encode(content, "gb2312") + "&time=");
        System.out.println(HTTP_BACK_MESSAGE);
        if(HTTP_BACK_MESSAGE.split("/")[0].equals("000")){
            return null;
        }else{
            return "短信发送失败";
        }
	}

    public static String HTTP_POST(String URL, String Data) throws Exception {
        BufferedReader In = null;
        PrintWriter Out = null;
        HttpURLConnection HttpConn = null;
        try {
            java.net.URL url=new URL(URL);
            HttpConn=(HttpURLConnection)url.openConnection();
            HttpConn.setRequestMethod("POST");
            HttpConn.setDoInput(true);
            HttpConn.setDoOutput(true);

            Out=new PrintWriter(HttpConn.getOutputStream());
            Out.println(Data);
            Out.flush();

            if(HttpConn.getResponseCode() == HttpURLConnection.HTTP_OK){
                StringBuffer content = new StringBuffer();
                String tempStr = "";
                In = new BufferedReader(new InputStreamReader(HttpConn.getInputStream()));
                while((tempStr = In.readLine()) != null){
                    content.append(tempStr);
                }
                In.close();
                return content.toString();
            }
            else
            {
                throw new Exception("HTTP_POST_ERROR_RETURN_STATUS：" + HttpConn.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            Out.close();
            HttpConn.disconnect();
        }
        return null;
    }

    public static void main(String[] args) throws Exception{
        String HTTP_BACK_MESSAGE = "";
        HTTP_BACK_MESSAGE = HTTP_POST("https://service.winic.org/sys_port/gateway/index.asp", "id=1taoocom&pwd=1taoocom&to=13666011848&content=" + URLEncoder.encode("测试一下是不是什么内容都能发【乐淘免单】", "gb2312") + "&time=");
        System.out.println(HTTP_BACK_MESSAGE);
    }
}
