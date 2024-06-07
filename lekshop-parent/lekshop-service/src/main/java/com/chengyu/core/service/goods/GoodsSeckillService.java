package com.chengyu.core.service.goods;

import com.chengyu.core.domain.form.GoodsPublishSeckillForm;
import com.chengyu.core.model.UmsShop;

import java.util.List;

public interface GoodsSeckillService {

    /**
     * 发布秒杀活动
     * @author LeGreen
     * @date   2022/10/9
     * @param  shop 店铺
     * @param  publishForm 活动信息
     */
    void addSeckillGoods(UmsShop shop, GoodsPublishSeckillForm publishForm);

    /**
     * 删除秒杀活动
     * @author LeGreen
     * @date   2022/10/9
     * @param  goodsIdList 商品ID集合
     */
    void deleteSeckill(List<Integer> goodsIdList);
}
