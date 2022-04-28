package com.chengyu.core.domain.enums;

import lombok.Getter;

/**
 * @title  商品枚举
 * @author LeGreen
 * @date   2022/4/28
 */
public class GoodsEnums {

	/**
	 * 店铺入驻状态枚举
	 */
	@Getter
	public enum GoodsStatus{
		/**0待审核>>1上架>>2仓库>>3删除>>4驳回**/
		WAI_VERIFY(0),
		SELL(1),
		IN_WAREHOUSE(2),
		DEL(3),
		REJECTED(4);

		private final int value;

		GoodsStatus(int value){
			this.value = value;
		}
	}

}
