package com.chengyu.core.util.map;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class TencentMapApiUtil {

	//访问腾讯地图接口KEY
    private static final String KEY = "GTOBZ-JCAC4-Z6XUQ-D24D4-QK3G6-ZYFUQ";
 
 
    /**
     * 计算腾讯地图距离
     * @param max 几个终点
     * @param from 起点起点坐标，格式：lat,lng;lat,lng...
     * （经度与纬度用英文逗号分隔，坐标间用英文分号分隔）
     * @param to 终点(可以传入多个终点坐标，格式：lat,lng;lat,lng...
     * （经度与纬度用英文逗号分隔，坐标间用英文分号分隔）
     * 注意：本服务支持单起点到多终点，或多起点到单终点，from和to参数仅可有一个为多坐标)
     * 例如：传入22.566939,114.072762;22.540940,114.061432;22.556635,114.086151;22.518266,114.068813
     * @return 距离
     */
    public static Double[] getLocation(int max,String from,String to) {
 
        Double[] distances = new Double[max];
 
        //请求路径
        String getURL = "http://apis.map.qq.com/ws/distance/v1/";
        //计算方式：driving（驾车）、walking（步行）默认：driving
        String mode ="driving";
        //返回格式：支持JSON/JSONP，默认JSON (非必填项)
        //String output = "";
        //JSONP方式回调函数  (非必填项)
        //String callback = "";
        //请求路径
        String urlString =getURL+"?mode="+mode+"&from="+from+"&to="+to+"&key="+KEY;
        //接收腾讯回传的地址解析结果
        String result = "";
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            // 腾讯地图使用GET
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            // 获取地址解析结果
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            // 转JSON格式
            JSONObject jsonObject = JSONUtil.parseObj(result).getJSONObject("result");
            //elements是[](数组格式)所以使用JSONArray获取
            JSONArray adInfo = jsonObject.getJSONArray("elements");
            //for数组
            for (int j = 0; j < adInfo.size(); j++) {
            	JSONObject jsonObject2 = adInfo.getJSONObject(j);
            	//获取距离(获取到的是m为单位)
            	String distance = jsonObject2.getStr("distance");
            	double distanceD = Double.valueOf(distance);
            	distances[j] = distanceD;
            }
            return distances;
        } catch (Exception e) {
            e.getMessage();
        }
		return new Double[]{0D};
    }
    
    public static void main(String[] args) {
    	Double[]  a = getLocation(6, "24.465766,118.104722", "24.465766,118.104722;24.465766,118.104722;24.465766,118.104722;24.465766,118.104722;24.465766,118.104722;24.465766,118.104722");
    	System.out.println(a[0]);
	}
}
