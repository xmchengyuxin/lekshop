package com.chengyu.core.domain.enums;

import lombok.Getter;

@Getter
public class PayEnum {

	/**
	 * 支付方式枚举
	 */
	@Getter
	public enum PayMethod{
		/**aliPay>>wxPay>>balance>>cash**/
		ALI_PAY("aliPay"),
		WX_PAY("wxPay"),
		BALANCE("balance"),
		CASH("cash");

		private final String value;

		PayMethod(String value){
			this.value = value;
		}
	}

	/**
	 * 支付渠道枚举
	 */
	@Getter
	public enum PayChannel{
		/**xcx>>gzh>>app**/
		XCX("xcx"),
		GZH("gzh"),
		APP("app"),
		H5("h5"),
		NATIVE("native");

		private final String value;

		PayChannel(String value){
			this.value = value;
		}
	}

}
