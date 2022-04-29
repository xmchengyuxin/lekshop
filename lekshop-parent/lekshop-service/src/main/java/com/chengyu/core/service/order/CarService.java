package com.chengyu.core.service.order;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OmsCar;
import com.chengyu.core.model.UmsMember;

import java.util.List;
import java.util.Map;

/**
 * @title  购物车
 * @author LeGreen
 * @date   2022/4/29
 */
public interface CarService {

    /**
     * 根据所选SKU加入购物车
     * @author LeGreen
     * @date   2022/4/29
     * @param  member
     * @param  goodsId
     * @param  attrSymbolPath
     * @param  num
     * @throws ServiceException 业务异常
     */
    void addCar(UmsMember member, Integer goodsId, String attrSymbolPath, Integer num) throws ServiceException;

    /**
     * 根据现有购物车加减数量
     * @author LeGreen
     * @date   2022/4/29
     * @param  member
     * @param  carId
     * @param  addNum
     * @throws ServiceException 业务异常
     */
    void addCarNum(UmsMember member, Integer carId, Integer addNum) throws ServiceException;

    /**
     * 获取购物车列表>>根据店铺分组
     * @author LeGreen
     * @date   2022/4/29
     * @param  memberId
     * @return Map<Integer,List<OmsCar>>
     */
    Map<Integer, List<OmsCar>> getCarList(Integer memberId);

    /**
     * 清空用户购物车
     * @author LeGreen
     * @date   2022/4/29
     * @param  member
     */
    void clearCarByMember(UmsMember member);

    /**
     * 删除购物车
     * @author LeGreen
     * @date   2022/4/29
     * @param  carIdList
     */
    void deleteCar(UmsMember member, List<Integer> carIdList);

    /**
     * 统计购物车宝贝数量,以一个SKU为单位
     * @author LeGreen
     * @date   2022/4/29
     * @param  memberId
     * @return Long
     */
    Long countCarNum(Integer memberId);
}