package com.chengyu.core.service.goods;

import com.chengyu.core.domain.result.GoodsRelateResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.model.PmsGoodsRelate;

import java.util.List;

/**
 * @title  关联商品
 * @author LeGreen
 * @date   2022/9/19
 */
public interface GoodsRelateService {

    /**
     * 关联商品
     * @author LeGreen
     * @date   2022/9/19
     * @param  relate 关联关系
     */
    void addGoodsRelate(PmsGoodsRelate relate);

    /**
     * 删除关联关系
     * @author LeGreen
     * @date   2022/9/19
     * @param  idList 关联ID
     */
    void deleteGoodsRelate(List<Integer> idList);

    /**
     * 修改优先级
     * @author LeGreen
     * @date   2022/9/19
     * @param  relateId 关联关系
     * @param  sort 排序
     */
    void updateSort(Integer relateId, Integer sort);

    /**
     * 查询关联商品
     * @author LeGreen
     * @date   2022/9/19
     * @param  page 分页
     * @param  pageSize 分页
     * @return CommonPage<GoodsRelateResult>
     */
    CommonPage<GoodsRelateResult> getGoodsRelateList(Integer page, Integer pageSize);
}
