package com.chengyu.core.util.map;

import java.util.HashMap;
import java.util.Map;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class AMapApiUtil {

	/**
	 * 访问高德地图接口KEY
	 */
    private static final String KEY = "44c6fe0a50a0ffa67be3dbc9adfe1f00";
    
    public static String getAreaPolygon(String areaCode){
		String url = "https://restapi.amap.com/v3/config/district";
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("key", KEY);
		paramMap.put("keywords", areaCode);
		paramMap.put("subdistrict", 0);
		paramMap.put("extensions", "all");
		String message = HttpUtil.get(url, paramMap);
		JSONObject json = JSONUtil.parseObj(message);
		if(json != null && "1".equals(json.getStr("status"))){
			JSONArray arrays = json.getJSONArray("districts");
			JSONObject districts = (JSONObject) arrays.get(0);
			return districts.getStr("polyline").replaceAll(";", "|");
		}else {
			return null;
		}
	}
    
    public static void main(String[] args) {
		System.out.println(getAreaPolygon("350203"));
	}
}
