package com.chengyu.core.service.goods;

import com.chengyu.core.domain.form.GoodsPublishGroupForm;
import com.chengyu.core.model.UmsShop;

import java.util.List;

public interface GoodsGroupService {

    /**
     * 发布拼团活动
     * @author LeGreen
     * @date   2022/10/9
     * @param  shop 店铺
     * @param  publishForm 活动信息
     */
    void addGroupGoods(UmsShop shop, GoodsPublishGroupForm publishForm);

    /**
     * 删除拼团活动
     * @author LeGreen
     * @date   2022/10/9
     * @param  goodsIdList 商品ID集合
     */
    void deleteGroup(List<Integer> goodsIdList);
}
