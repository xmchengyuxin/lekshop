package com.chengyu.core.domain.form;

import cn.hutool.json.JSONUtil;
import com.chengyu.core.domain.enums.MemberNewsEnums;
import com.chengyu.core.model.UmsShop;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class MemberNewsForm {

	private UmsShop shop;

	private Integer type;

	private String img;

	private String turnParams;

	private String content;

	private String title;

	public MemberNewsForm(MemberNewsEnums.MemberNewsTypes newsType){
		this.type = newsType.getType();
		this.title = newsType.getTitle();
		this.content = newsType.getContent();
	}

	public void initTurnParams(String key, String value){
		Map<String, String> params = new HashMap<>();
		params.put(key, value);
		this.turnParams = JSONUtil.toJsonStr(params);
	}

	public void replace(String key, String value){
		Map<String,String> params = new HashMap<>();
		params.put(key, value);
		this.replace(params);
	}

	public void replace(Map<String,String> params){
		for(String key : params.keySet()){
			this.content = this.content.replace(key, params.get(key));
		}
	}
}