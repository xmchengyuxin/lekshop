package com.chengyu.core.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class JsonUtils {
	public static final Gson gson = new Gson();
	
	public static final GsonBuilder gsonBuilder = new GsonBuilder();

	public static <T> T json2Object(Object data, Class<T> clazz) {
		return gson.fromJson(gson.toJson(data), clazz);
	}

	public static String object2JsonString(Object obj) {
		if(obj == null) {
			return null;
		}
		return gson.toJson(obj).toString();
	}

	public static <T> T fromJson(String data, Class<T> clazz) {
		return gson.fromJson(data, clazz);
	}
	
	/**
	 * 转化成Json，不转化hmtl字符及不忽略空值
	 * @param obj
	 * @return
	 */
	public static String object2JsonNoEscaping(Object obj){
		gsonBuilder.disableHtmlEscaping();
		gsonBuilder.serializeNulls();
		return gsonBuilder.create().toJson(obj);
	}
 }