package com.chengyu.core.domain.result;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @title  后台首页统计参数
 * @author LeGreen
 * @date   2021/1/28
 */
@Getter
@Setter
public class ShopIndexCountResult {

    private String name;

    private Integer num;

    private BigDecimal amount;

    private String router;

    private String tips;

    @Getter
    public enum IndexCountEnums{
        /**
         * 商家后台首页统计参数
         * 未处理订单，店铺收藏，商品收藏，今日订单，加购物车，今日总额
         */
        WAIT_DEAL_ORDER("waitDealOrderNum", "未处理订单", "/order/list"),
        COLLECT_SHOP_NUM("collectShopNum", "店铺收藏", ""),
        COLLECT_GOODS_NUM("collectGoodsNum","商品收藏", ""),
        TODAY_ORDER_NUM("orderNum","今日订单", ""),
        TODAY_ORDER_AMOUNT("orderAmount","今日总额", ""),
        ADD_CAR_NUM("addCarNum","加购物车", "");

        private final String key;
        private final String name;
        private final String router;

        IndexCountEnums(String key, String name, String router){
            this.key = key;
            this.name = name;
            this.router = router;
        }
    }
}
