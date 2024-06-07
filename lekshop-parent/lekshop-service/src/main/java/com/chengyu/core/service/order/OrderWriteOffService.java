package com.chengyu.core.service.order;

import com.chengyu.core.domain.form.OrderSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OmsOrderWriteOff;
import com.chengyu.core.model.UmsShopAccount;

import java.util.List;

/**
 * @title  订单核销
 * @author LeGreen
 * @date   2022/9/20
 */
public interface OrderWriteOffService {

    /**
     * 订单核销列表
     * @author LeGreen
     * @date   2022/9/20
     * @param  form 查询表单
     * @param  page 分页
     * @param  pageSize 分页
     * @return List<OmsOrderWriteOff>
     */
    List<OmsOrderWriteOff> getWriteOffOrderList(OrderSearchForm form, Integer page, Integer pageSize);

    /**
     * 核销
     * @author LeGreen
     * @date   2022/9/20
     * @param  shopAccount 核销人
     * @param  verifyCode 核销码
     */
    void writeOff(UmsShopAccount shopAccount, String verifyCode) throws ServiceException;
}
