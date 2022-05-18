package com.chengyu.core.domain.enums;

import lombok.Getter;

/**
 * @title  物流
 * @author LeGreen
 * @date   2022/5/18
 */
public class DeliveryEnums {

	/**
	 * 物流类型
	 */
	@Getter
	public enum DeliveryType{
		/**快递**/
		NONE("none", "无需物流"),
		SHUNFENG("shunfeng", "顺丰速运"),
		YOUZHENGGUONEI("youzhengguonei", "邮政快递包裹"),
		JD("jd", "京东物流"),
		YUNDA("yunda", "韵达快递"),
		EMS("ems", "EMS"),
		YUANTONG("yuantong", "圆通速递"),
		ZHONGTONG("zhongtong", "中通快递"),
		HUITONGKUAIDI("huitongkuaidi", "百世快递"),
		SHENTONG("shentong", "申通快递"),
		YOUZHENGBK("youzhengbk", "邮政标准快递"),
		ZHAIJISONG("zhaijisong", "宅急送"),
		TIANTIAN("tiantian", "天天快递"),
		TRANSRUSH("transrush", "TransRush"),
		DEBANGWULIU("debangwuliu", "德邦物流"),
		BAISHIWULIU("baishiwuliu", "百世快运"),
		YOUZHENGGUOJI("youzhengguoji", "国际包裹"),
		EMSGUOJI("emsguoji", "EMS-国际件"),
		DANNIAO("danniao", "丹鸟"),
		QUANFENGKUAIDI("quanfengkuaidi", "全峰快递"),
		SUNING("suning", "苏宁物流"),
		EWE("ewe", "EWE全球快递"),
		USPS("usps", "USPS"),
		YOUSHUWULIU("youshuwuliu", "优速快递"),
		DEBANGKUAIDI("debangkuaidi", "德邦快递"),
		ZITI("ziti", "自提");

		private final String key;
		private final String name;

		DeliveryType(String key, String name){
			this.key = key;
			this.name = name;
		}
	}

}
