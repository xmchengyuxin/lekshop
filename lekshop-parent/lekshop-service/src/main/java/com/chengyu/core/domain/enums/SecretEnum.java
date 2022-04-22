package com.chengyu.core.domain.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum SecretEnum {

	/**
	 * 密保问题
	 */
	Q_1("Q1", "您的出生地是？"),
	Q_2("Q2", "您父亲的姓名是？"),
	Q_3("Q3", "您母亲的姓名是？"),
	Q_4("Q4", "您配偶的姓名是？"),
	Q_5("Q5", "您小学的学校名称是？"),
	Q_6("Q6", "您的旺旺账号是？"),
	Q_7("Q7", "您的QQ账号是？"),
	Q_8("Q8", "您父亲的生日是？"),
	Q_9("Q9", "您母亲的生日是？"),
	Q_10("Q10", "您配偶的生日是？"),
	Q_11("Q11", "我的宠物名字？"),
	Q_12("Q12", "我最好的朋友是谁？"),
	Q_13("Q13", "我最喜爱的颜色？"),
	Q_14("Q14", "我最喜爱的电影？"),
	Q_15("Q15", "我最喜爱的影星？"),
	Q_16("Q16", "我最喜爱的歌曲？"),
	Q_17("Q17", "我最喜爱的食物？"),
	Q_18("Q18", "我最大的爱好？"),
	Q_19("Q19", "我的座右铭是？");

	private String key;
    private String question;

    SecretEnum(String key, String question) {
    	this.key = key;
        this.question = question;
    }
}
