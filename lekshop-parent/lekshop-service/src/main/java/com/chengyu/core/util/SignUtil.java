package com.chengyu.core.util;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SignUtil {

    private static final String FIELD_SIGN = "sign";
    private static final String TIMESTAMP = "timestamp";
    private static final String APP_KEY = "appKey";

    public static final String KEY = "15f4200c968b1e0a";
    public static final String SECRET = "31593886409a3f86804a12ec9e4ff176";
    /**
     * 默认超时时间
     */
    private static final Long DEFAULT_TIME_OUT = 5000L;

    private static String md5(String data) {
        return SecureUtil.md5(data);
    }


    /**
     * 构建签名 Map
     *
     * @param appKey       appKey
     * @param appSecret    appSecret
     * @return 构建签名后的 Map
     */
    public static Map<String, String> createSign(Map<String, String> params, String appKey, String appSecret) {
        return buildSign(params, appKey, appSecret);
    }


    /**
     * 构建签名
     *
     * @param params       需要签名的参数
     * @param appKey       appKey
     * @param appSecret    密钥
     * @return 签名后的 Map
     */
    private static Map<String, String> buildSign(Map<String, String> params, String appKey, String appSecret) {
        params.put(APP_KEY, appKey);
        params.put(TIMESTAMP, String.valueOf(System.currentTimeMillis()));
        String sign = createSign(params, appSecret);
        params.put(FIELD_SIGN, sign);
        return params;
    }

    /**
     * 生成签名
     *
     * @param params    需要签名的参数
     * @param appSecret 密钥
     * @return 签名后的数据
     */
    private static String createSign(Map<String, String> params, String appSecret) {
        // 生成签名前先去除sign
        params.remove(FIELD_SIGN);
        String tempStr = createLinkString(params);
        String stringSignTemp = tempStr + "&key=" + appSecret;
        System.out.println(stringSignTemp);
        return md5(stringSignTemp);
    }

    /**
     * 把所有元素排序
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    private static String createLinkString(Map<String, String> params) {
        return createLinkString(params, false);
    }

    /**
     * @param params 需要排序并参与字符拼接的参数组
     * @param encode 是否进行URLEncoder
     * @return 拼接后字符串
     */
    private static String createLinkString(Map<String, String> params, boolean encode) {
        return createLinkString(params, "&", encode);
    }

    /**
     * @param params  需要排序并参与字符拼接的参数组
     * @param connStr 连接符号
     * @param encode  是否进行URLEncoder
     * @return 拼接后字符串
     */
    private static String createLinkString(Map<String, String> params, String connStr, boolean encode) {
        return createLinkString(params, connStr, encode, false);
    }

    private static String createLinkString(Map<String, String> params, String connStr, boolean encode, boolean quotes) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            // 拼接时，不包括最后一个&字符
            if (i == keys.size() - 1) {
                if (quotes) {
                    content.append(key).append("=").append('"').append(encode ? urlEncode(value) : value).append('"');
                } else {
                    content.append(key).append("=").append(encode ? urlEncode(value) : value);
                }
            } else {
                if (quotes) {
                    content.append(key).append("=").append('"').append(encode ? urlEncode(value) : value).append('"').append(connStr);
                } else {
                    content.append(key).append("=").append(encode ? urlEncode(value) : value).append(connStr);
                }
            }
        }
        return content.toString();
    }


    /**
     * URL 编码
     *
     * @param src 需要编码的字符串
     * @return 编码后的字符串
     */
    private static String urlEncode(String src) {
        try {
            return URLEncoder.encode(src, CharsetUtil.UTF_8).replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }


    //验签

    /**
     * 校验 sign
     *
     * @param params    参数
     * @param appSecret appSecret
     * @return {@link Boolean} 验证签名结果
     */
    public static boolean verifyNotify(Map<String, String> params, String appSecret) {
        String sign = params.get("sign");
        String localSign = createSign(params, appSecret);
        return sign.equals(localSign);
    }


    /**
     * 验签 签名 自带默认验签超时时间默认5s
     *
     * @param params    参数
     * @param appSecret secret
     * @return {@link Boolean} 验证签名结果
     */
    public static boolean verifyTimeStampNotify(Map<String, String> params, String appSecret) {
        return verifyTimeStampNotify(params, appSecret, DEFAULT_TIME_OUT);
    }

    /**
     * 验签 签名
     *
     * @param params    参数
     * @param appSecret secret
     * @param timeout   超时时间
     * @return {@link Boolean} 验证签名结果
     */
    public static boolean verifyTimeStampNotify(Map<String, String> params, String appSecret, long timeout) {
        String timeStamp = params.get(TIMESTAMP);
        Assert.notNull(timeStamp, "{}不能为空", TIMESTAMP);
        Assert.state(System.currentTimeMillis() - Long.parseLong(timeStamp) < timeout, "签名过期");
        return verifyNotify(params, appSecret);
    }
}
