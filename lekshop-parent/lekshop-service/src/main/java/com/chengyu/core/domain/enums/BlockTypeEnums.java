package com.chengyu.core.domain.enums;

import lombok.Getter;

/**
 * @title  黑名单类型
 * @author LeGreen
 * @date   2021/1/6
 */
public class BlockTypeEnums{

	@Getter
	public enum BlockTypes {
		/**
		 * 身份证
		 */
		ID_CARD(1),
		/**
		 * 手机号
		 */
		PHONE(2),
		/**
		 * QQ
		 */
		QQ(3),
		/**
		 * 旺旺
		 */
		WANGWANG(4);

		private final int key;

		BlockTypes(int key){
			this.key = key;
		}
	}

	@Getter
	public enum BlockAccountType {
		/**
		 * 账号
		 */
		ACCOUNT_CODE(1),
		/**
		 * 买号
		 */
		ACCOUNT_BUYER(2);

		private final int key;

		BlockAccountType(int key){
			this.key = key;
		}
	}
}

