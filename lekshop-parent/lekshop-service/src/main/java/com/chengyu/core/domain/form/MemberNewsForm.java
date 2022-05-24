package com.chengyu.core.domain.form;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.domain.enums.MemberNewsEnums;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
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

	public MemberNewsForm(UmsShop shop, MemberNewsEnums.MemberNewsTypes newsType, String turnParams, String... replace){
		this.shop = shop;
		this.type = newsType.getType();
		this.turnParams = turnParams;
		this.title = newsType.getTitle();
		if(replace != null && replace.length > 0){
			//#goodsName#=莆田超级跑鞋;
			for(String rep : replace){
				this.content = newsType.getContent().replace(rep.split("=")[0], rep.split("=")[1]);
			}
		}else{
			this.content = newsType.getContent();
		}
	}
}