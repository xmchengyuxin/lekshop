package com.chengyu.core.service.order;

import com.chengyu.core.domain.form.OrderMergeSearchForm;
import com.chengyu.core.domain.result.OrderMergeResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OmsOrderMerge;
import com.chengyu.core.model.UmsShopAccount;

import java.util.List;

/**
 * @title  订单合并
 * @author LeGreen
 * @date   2022/8/22
 */
public interface OrderMergeService {

    /**
     * 合并订单
     * @author LeGreen
     * @date   2022/8/22
     * @param  shopAccount 仓储文员
     * @param  orderIdList 订单ID集合
     * @throws ServiceException 业务异常
     */
    void mergeOrder(UmsShopAccount shopAccount, List<Integer> orderIdList) throws ServiceException;

    /**
     * 订单合并列表
     * @author LeGreen
     * @date   2022/8/22
     * @param  form 查询表单
     * @param  page 分页
     * @param  pageSize 分页
     * @return List<OmsOrderMerge>
     */
    List<OmsOrderMerge> getOrderMergeList(OrderMergeSearchForm form, Integer page, Integer pageSize);

    /**
     * 订单合并详情
     * @author LeGreen
     * @date   2022/8/22
     * @param  orderMergeId 订单合并ID
     * @return OmsOrderMergeResult
     */
    OrderMergeResult getOrderMerge(Integer orderMergeId);

    /**
     * 配货
     * @author LeGreen
     * @date   2022/9/20
     * @param  account 子账号
     * @param  mergeOrderId 合并ID
     * @throws ServiceException 业务异常
     */
    void distribution(UmsShopAccount account, Integer mergeOrderId) throws ServiceException;

    /**
     * 分拣
     * @author LeGreen
     * @date   2022/9/20
     * @param  account 子账号
     * @param  mergeOrderId 合并ID
     * @throws ServiceException 业务异常
     */
    void sortOut(UmsShopAccount account, Integer mergeOrderId) throws ServiceException;

    /**
     * 取消合并
     * @author LeGreen
     * @date   2022/9/20
     * @param  account 子账号
     * @param  mergeOrderId 合并ID
     * @throws ServiceException 业务异常
     */
    void cancel(UmsShopAccount account, Integer mergeOrderId) throws ServiceException;
}
