package com.chengyu.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author LeGreen
 * @date 2021/6/21
 */
public class IpAddressUtil {
    public static void main(String[] args) {
        getIpAddressTwice("119.137.52.13");
    }

    /**
     * 先通过淘宝api获取地址，如果没有获取再去从新浪获取地址
     *
     * @param ip
     * @return
     */
    public static String getIpAddressTwice(String ip) {
        String taobaoIpAddress = getIpAddressByTaoBaoAPI(ip);
        if (StringUtils.isNotEmpty(taobaoIpAddress)) {
            return taobaoIpAddress;
        }
        return getIpAddressBySinaAPI(ip);
    }

    /**
     * 淘宝获取
     *
     * @param ip
     * @return
     */
    public static String getIpAddressByTaoBaoAPI(String ip) {
        String apiUrl = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;

        return getIpAddress(apiUrl, ip);
    }

    /**
     * 新浪获取
     *
     * @param ip
     * @return
     */
    public static String getIpAddressBySinaAPI(String ip) {
        String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=" + ip;
        String provinceName = "", cityName = "", countryName = "", isp = "";
        StringBuilder address = new StringBuilder();
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        try {
            HttpResponse response = client.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                String strResult = EntityUtils.toString(response.getEntity());
                try {

                    JSONObject jsonResult = JSON.parseObject(strResult);
                    if (jsonResult == null) {
                        return null;
                    }
                    countryName = jsonResult.getString("country");
                    provinceName = jsonResult.getString("province");
                    cityName = jsonResult.getString("city");
                    isp = jsonResult.getString("isp");
                    address.append(countryName).append(provinceName).append(cityName);
                    if (StringUtils.isNotEmpty(isp)) {
                        address.append(":").append(isp);
                    }
                    System.out.println(address);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address.toString();
    }

    public static String getIpAddress(String apiUrl, String ip) {
        String provinceName = "", cityName = "", countryName = "", isp = "";
        StringBuilder address = new StringBuilder();
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(apiUrl);
        try {
            HttpResponse response = client.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                String strResult = EntityUtils.toString(response.getEntity());
                try {
                    JSONObject jsonResult = JSON.parseObject(strResult);
                    JSONObject dataJson = jsonResult.getJSONObject("data");
                    if (dataJson == null) {
                        return null;
                    }
                    countryName = dataJson.getString("country");
                    provinceName = dataJson.getString("region");
                    cityName = dataJson.getString("city");
                    isp = jsonResult.getString("isp");
                    address.append(countryName).append(provinceName).append(cityName);
                    if (StringUtils.isNotEmpty(isp)) {
                        address.append(":").append(isp);
                    }
                    //System.out.println(JSON.toJSONString(jsonResult, true));
                    System.out.println(address);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address.toString();
    }
}
