package com.chengyu.core.domain.enums;

import com.kuaidi100.sdk.contant.CompanyConstant;
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
		SHUNFENG(CompanyConstant.SF, "顺丰速运"),
		YOUZHENGGUONEI(CompanyConstant.EMS, "邮政快递包裹"),
		JD(CompanyConstant.JD, "京东物流"),
		YUNDA(CompanyConstant.YD, "韵达快递"),
		EMS(CompanyConstant.EMS, "EMS"),
		YUANTONG(CompanyConstant.YT, "圆通速递"),
		ZHONGTONG(CompanyConstant.ZT, "中通快递"),
		HUITONGKUAIDI("huitongkuaidi", "百世快递"),
		SHENTONG(CompanyConstant.ST, "申通快递"),
		YOUZHENGBK("youzhengbk", "邮政标准快递"),
		ZHAIJISONG(CompanyConstant.ZJS, "宅急送"),
		TIANTIAN("tiantian", "天天快递"),
		TRANSRUSH("transrush", "TransRush"),
		DEBANGWULIU(CompanyConstant.DB, "德邦物流"),
		BAISHIWULIU("baishiwuliu", "百世快运"),
		YOUZHENGGUOJI("youzhengguoji", "国际包裹"),
		EMSGUOJI("emsguoji", "EMS-国际件"),
		DANNIAO("danniao", "丹鸟"),
		QUANFENGKUAIDI("quanfengkuaidi", "全峰快递"),
		SUNING("suning", "苏宁物流"),
		EWE("ewe", "EWE全球快递"),
		USPS("usps", "USPS"),
		YOUSHUWULIU("youshuwuliu", "优速快递"),
		DEBANGKUAIDI(CompanyConstant.DB, "德邦快递"),
		ZITI("ziti", "自提");

		private final String key;
		private final String name;

		DeliveryType(String key, String name){
			this.key = key;
			this.name = name;
		}
	}

}
