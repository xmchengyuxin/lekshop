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

	@Getter
	public enum StockType{
		/**库存计算方式>>1下单减库存>>2付款减库存**/
		ORDER_REDUCE(1),
		PAY_REDUCE(2);

		private final int value;

		StockType(int value){
			this.value = value;
		}
	}

	@Getter
	public enum GoodsType{
		/**1普通商品>>2秒杀商品>>3拼团商品**/
		NORMAL_GOODS(1),
		SECKILL_GOODS(2),
		GROUP_GOODS(3);

		private final int value;

		GoodsType(int value){
			this.value = value;
		}
	}
}
