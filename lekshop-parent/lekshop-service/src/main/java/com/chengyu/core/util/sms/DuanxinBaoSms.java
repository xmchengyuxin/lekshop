package com.chengyu.core.util.sms;

import cn.hutool.core.net.URLEncoder;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import com.chengyu.core.domain.CommonConstant;

import java.nio.charset.Charset;

/**
 * @title  短信宝
 * @author LeGreen
 * @date   2021/1/29
 */
public class DuanxinBaoSms {

	public String sendMsg(String phone, String account, String password, String content,  String sendUrl) throws Exception {
        password = SecureUtil.md5(password);
        System.out.println("手机号码>>"+phone);
//        content = new URLEncoder().encode(content, Charset.defaultCharset());
        content = encodeUrlString(content, "UTF-8");
        phone = new URLEncoder().encode(phone, Charset.defaultCharset());
        String message = HttpUtil.get("http://api.smsbao.com/sms?u="+account+"&p="+password+"&m="+phone+"&c="+content);
        System.out.println("短信宝返回>>"+message);
        if("0".equals(message)){
            return null;
        }else{
            return "短信发送失败";
        }
	}

    public String getBalance(String account, String password) {
        password = SecureUtil.md5(password);
        String message = HttpUtil.get("https://api.smsbao.com/query?u="+account+"&p="+password);
        System.out.println("短信宝查询余额返回>>"+message);
        String msg1 = message.split("\n")[0];
        if("0".equals(msg1)){
            return message.split("\n")[1].split(CommonConstant.DH_REGEX)[1];
        }else{
            return "短信余额查询失败";
        }
    }

    public static String encodeUrlString(String str, String charset) {
        String strret = null;
        if (str == null) {
            return str;
        }
        try {
            strret = java.net.URLEncoder.encode(str, charset);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return strret;
    }

    public static void main(String[] args) throws Exception{
//        String password = SecureUtil.md5("ZL20212021");
//        String phone = new URLEncoder().encode("13666011848", Charset.defaultCharset());
//        String content = "【犀牛】" +"验证码: "+155678+", 如非本人操作, 请忽略本短信。";
//        String message = HttpUtil.get("http://api.smsbao.com/sms?u=jiakuaijiaobu&p="+password+"&m="+phone+"&c="+content);

        String message = new DuanxinBaoSms().getBalance("a13135387619", "qwer123456.");
        System.out.println(message);
    }
}
