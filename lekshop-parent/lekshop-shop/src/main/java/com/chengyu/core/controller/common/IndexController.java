package com.chengyu.core.controller.common;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.result.ShopIndexCountResult;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.CustomShopIndexMapper;
import com.chengyu.core.mapper.UmsMemberLoginLogMapper;
import com.chengyu.core.model.UmsMemberLoginLog;
import com.chengyu.core.model.UmsMemberLoginLogExample;
import com.chengyu.core.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @title  首页统计
 * @author LeGreen
 * @date   2021/1/28
 */
@Controller
@Api(tags = "首页统计")
@RequestMapping("/member")
public class IndexController extends ShopBaseController {

    @Autowired
    private CustomShopIndexMapper shopIndexMapper;
    @Autowired
    private UmsMemberLoginLogMapper memberLoginLogMapper;

    @ApiOperation(value = "获取登录信息")
    @RequestMapping(value = "/getLoginInfo", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Map<String, Object>> getLoginInfo() throws ServiceException {
        UmsMemberLoginLogExample example = new UmsMemberLoginLogExample();
        example.setOrderByClause("add_time desc limit 2");
        example.createCriteria().andMemberIdEqualTo(getCurrentMemberId());
        List<UmsMemberLoginLog> list = memberLoginLogMapper.selectByExample(example);

        Map<String,Object> result = new HashMap<>();
        if(CollectionUtil.isNotEmpty(list)){
            result.put("loginIp", list.get(0).getIp()+ " "+ (StringUtils.isNotBlank(list.get(0).getAddress()) ? list.get(0).getAddress() : ""));
            if(list.size() >= 2){
                result.put("lastLoginIp", list.get(1).getIp() + " "+ (StringUtils.isNotBlank(list.get(1).getAddress()) ? list.get(1).getAddress() : ""));
                result.put("lastLoginTime", list.get(1).getAddTime());
            }
        }

        return CommonResult.success(result);
    }

    @ApiOperation(value = "获取商品订单统计")
    @RequestMapping(value = "/countGoods", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Map<String, Object>> countMember() throws ServiceException {
        Integer shopId = getCurrentShop().getId();
        //总商品数,总下单人数,总订单数,总金额
        Map<String,Object> result = new HashMap<>();
        result.putAll(shopIndexMapper.countGoods(shopId));
        result.putAll(shopIndexMapper.countOrder(shopId, null, null));
        return CommonResult.success(result);
    }

    @ApiOperation(value = "近十天订单数量")
    @RequestMapping(value = "/getOrderAddList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Map<String, Object>> getOrderAddList() throws ServiceException {
        Date now = new Date();
        //近10天用户数量
        List<String> dayList = new ArrayList<String>();
        Date date = com.chengyu.core.utils.DateUtil.addDay(now, -9);
        List<Map<String, Object>> list = shopIndexMapper.countOrderNumByLastTenDays(getCurrentShop().getId());

        Map<String,Long> numMap = new HashMap<>();
        if(CollectionUtil.isNotEmpty(list)){
            for(Map<String, Object> map : list){
                String key = DateUtil.formatDate(MapUtil.getDate(map, "addTime"));
                numMap.put(key.substring(5), MapUtil.getLong(map, "num"));
            }
        }

        List<Long> userNumList = new ArrayList<>();
        for(int i=0;i<=9;i++) {
            date = i == 0 ? date : com.chengyu.core.utils.DateUtil.addDay(date, +1);

            String dateStr = DateUtil.formatDate(date).substring(5);
            dayList.add(dateStr);
            Long num = numMap.get(dateStr);
            if(num != null){
                userNumList.add(num);
            }else{
                userNumList.add(0L);
            }

        }
        Map<String,Object> map = new HashMap<>();
        map.put("userNum", userNumList);
        map.put("dayList", dayList);
        return CommonResult.success(map);
    }

    @ApiOperation(value = "获取数量统计")
    @RequestMapping(value = "/countNumber", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<ShopIndexCountResult>> countNumber() throws ServiceException {
        List<ShopIndexCountResult> list = new ArrayList<>();

        Integer shopId = getCurrentShop().getId();
        Date now = DateUtil.date();
        /*未处理订单，店铺收藏，商品收藏，今日订单，加购物车，今日总额*/
        Map<String,Object> map = new HashMap<>();
        map.putAll(shopIndexMapper.countWaitDealOrderNum(shopId));
        map.putAll(shopIndexMapper.countCollectShopNum(shopId));
        map.putAll(shopIndexMapper.countCollectGoodsNum(shopId));
        map.putAll(shopIndexMapper.countOrder(shopId, DateUtil.beginOfDay(now), DateUtil.endOfDay(now)));
        map.putAll(shopIndexMapper.countCarNum(shopId));
       for(ShopIndexCountResult.IndexCountEnums enums :ShopIndexCountResult.IndexCountEnums.values()){
           ShopIndexCountResult result = new ShopIndexCountResult();
           result.setName(enums.getName());
           result.setRouter(enums.getRouter());
           result.setNum(MapUtil.getInt(map, enums.getKey()));
           list.add(result);
       }
        return CommonResult.success(list);
    }

    @ApiOperation(value = "获取本月商品销售排行")
    @RequestMapping(value = "/getGoodsSellList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Map<String,Object>>> getFoodSellList() throws ServiceException {
        Date now = new Date();
        Date beginTime = DateUtil.beginOfMonth(now);
        Date endTime = DateUtil.endOfMonth(now);
        List<Map<String,Object>> list = shopIndexMapper.getGoodsSellRankList(this.getCurrentShop().getId(), beginTime, endTime);
        return CommonResult.success(list);
    }



}
