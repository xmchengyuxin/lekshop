package com.chengyu.core.service.shop;

import com.chengyu.core.domain.form.ShopSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsShopInfo;

import java.util.List;

/**
 * @title  店铺管理
 * @author LeGreen
 * @date   2022/4/23
 */
public interface ShopInfoService {

    /**
     * 申请入驻-提交资质
     * @author LeGreen
     * @date   2022/4/23
     * @param  member
     * @param  shopInfo
     * @throws ServiceException 业务异常
     */
    Integer applyShopCert(UmsMember member, UmsShopInfo shopInfo) throws ServiceException;

    /**
     * 申请入驻-完善店铺
     * @author LeGreen
     * @date   2022/4/23
     * @param  shopInfo
     * @throws ServiceException 业务异常
     */
    void applyShopDetail(UmsShopInfo shopInfo) throws ServiceException;

    /**
     * 申请入驻-提交审核
     * @author LeGreen
     * @date   2022/4/23
     * @param  shopInfo
     * @throws ServiceException 业务异常
     */
    void submitShopInfo(UmsShopInfo shopInfo) throws ServiceException;

    /**
     * 获取当前用户店铺审核信息
     * @author LeGreen
     * @date   2022/4/23
     * @param  member
     * @return UmsShopInfo
     */
    UmsShopInfo getShopInfo(UmsMember member);

    /**
     * 查询店铺审核列表
     * @author LeGreen
     * @date   2022/4/23
     * @param  form
     * @param  page
     * @param  pageSize
     * @return List<UmsShopInfo>
     * @throws ServiceException 业务异常
     */
    List<UmsShopInfo> getShopInfoList(ShopSearchForm form, Integer page, Integer pageSize);

    /**
     * 审核店铺
     * @author LeGreen
     * @date   2022/4/23
     * @param  shopInfoId
     * @param  status
     * @param  reason
     * @throws ServiceException 业务异常
     */
    void verifyShop(Integer shopInfoId, Integer status, String reason) throws ServiceException;

    /**
     * 删除店铺信息
     * @author LeGreen
     * @date   2022/4/23
     * @param  shopInfoId
     * @return void
     * @throws ServiceException 业务异常
     */
    void deleteShopInfo(Integer shopInfoId) throws ServiceException;
}
