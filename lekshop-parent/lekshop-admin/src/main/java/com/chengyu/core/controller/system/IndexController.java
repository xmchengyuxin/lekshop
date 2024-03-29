package com.chengyu.core.controller.system;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.form.ReportSearchForm;
import com.chengyu.core.domain.form.WithdrawSearchForm;
import com.chengyu.core.domain.result.AdminIndexCountResult;
import com.chengyu.core.domain.result.WithdrawCountResult;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.CustomAdminIndexMapper;
import com.chengyu.core.mapper.SysAdminLoginLogMapper;
import com.chengyu.core.model.RepConversionDay;
import com.chengyu.core.model.SysAdminLoginLog;
import com.chengyu.core.model.SysAdminLoginLogExample;
import com.chengyu.core.service.member.MemberWithdrawService;
import com.chengyu.core.service.report.ReportLogService;
import com.chengyu.core.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;

/**
 * @title  首页统计
 * @author LeGreen
 * @date   2021/1/28
 */
@Controller
@Api(tags = "首页统计")
@RequestMapping("/system")
public class IndexController extends AdminBaseController {

    @Autowired
    private CustomAdminIndexMapper adminIndexMapper;
    @Autowired
    private SysAdminLoginLogMapper adminLoginLogMapper;
    @Autowired
    private MemberWithdrawService withdrawService;
    @Autowired
    private ReportLogService reportLogService;

    @ApiOperation(value = "获取登录信息")
    @RequestMapping(value = "/getLoginInfo", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Map<String, Object>> getLoginInfo() throws ServiceException {
        SysAdminLoginLogExample example = new SysAdminLoginLogExample();
        example.setOrderByClause("add_time desc limit 2");
        example.createCriteria().andAdminIdEqualTo(getSessionAdmin().getId());
        List<SysAdminLoginLog> list = adminLoginLogMapper.selectByExample(example);

        Map<String,Object> result = new HashMap<>();
        if(CollectionUtil.isNotEmpty(list)){
            result.put("loginIp", list.get(0).getIp());
            if(list.size() >= 2){
                result.put("lastLoginIp", list.get(1).getIp() + " "+ (StringUtils.isNotBlank(list.get(1).getAddress()) ? list.get(1).getAddress() : ""));
                result.put("lastLoginTime", list.get(1).getAddTime());
            }
        }

        return CommonResult.success(result);
    }

    @ApiOperation(value = "获取用户统计")
    @RequestMapping(value = "/countMember", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Map<String, Object>> countMember() {
        //会员数量,今日注册,店铺数量, 商品数量
        Map<String,Object> result = adminIndexMapper.countMember();
        Map<String,Object> goodsMap = adminIndexMapper.countGoods();
        result.putAll(goodsMap);
        return CommonResult.success(result);
    }

    @ApiOperation(value = "近十天会员数量")
    @RequestMapping(value = "/getMemberAddList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Map<String, Object>> getOrderAddList() {
        Date now = new Date();
        //近10天用户数量
        List<String> dayList = new ArrayList<String>();
        Date date = com.chengyu.core.utils.DateUtil.addDay(now, -9);
        List<Map<String, Object>> list = adminIndexMapper.countMemberNumByLastTenDays();

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
    public CommonResult<List<AdminIndexCountResult>> countNumber() {
        List<AdminIndexCountResult> list = new ArrayList<>();

        Map<String,Object> map = new HashMap<>();
        map.putAll(adminIndexMapper.countVerifyWithdrawNum());
        map.putAll(adminIndexMapper.countOrder());
        map.putAll(adminIndexMapper.countRefund());
        map.putAll(adminIndexMapper.countViewGoods());
        map.putAll(adminIndexMapper.countSku());
       for(AdminIndexCountResult.IndexCountEnums enums :AdminIndexCountResult.IndexCountEnums.values()){
           AdminIndexCountResult result = new AdminIndexCountResult();
           result.setName(enums.getName());
           result.setRouter(enums.getRouter());
           result.setNum(MapUtil.getInt(map, enums.getKey()));
           list.add(result);
       }
        return CommonResult.success(list);
    }

    @ApiOperation(value = "获取金额统计")
    @RequestMapping(value = "/countAmount", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<AdminIndexCountResult>> countAmount() throws ServiceException {
        List<AdminIndexCountResult> list = new ArrayList<>();

        /**
         * 后台首页统计参数
         * 充值总额, 提现总额, 所有用户账号总余额
         */
        Map<String,Object> map = new HashMap<>();
        map.putAll(adminIndexMapper.countRechargeAmount());
        map.putAll(adminIndexMapper.countWithdrawAmount());
        map.putAll(adminIndexMapper.countMemberAccount());

        Date now = DateUtil.date();
        WithdrawSearchForm form = new WithdrawSearchForm();
        form.setStatusList(Collections.singletonList("1"));
        form.setDateFrom(DateUtil.beginOfDay(now));
        form.setDateTo(DateUtil.endOfDay(now));
        WithdrawCountResult todayWithdraw = withdrawService.countWithdraw(form);
        map.put("todayWithdrawAmount", todayWithdraw == null ? BigDecimal.ZERO : todayWithdraw.getTotalAmount());

        /*form.setDateFrom(DateUtil.beginOfDay(DateUtil.offsetDay(now, -1)));
        form.setDateTo(DateUtil.endOfDay(DateUtil.offsetDay(now, -1)));
        WithdrawCountResult yesTodayWithdraw = withdrawService.countWithdraw(form);
        map.put("yestodayWithdrawAmount", yesTodayWithdraw == null ? BigDecimal.ZERO : yesTodayWithdraw.getTotalAmount());*/

        for(AdminIndexCountResult.IndexAmountEnums enums :AdminIndexCountResult.IndexAmountEnums.values()){
            if(StringUtils.isNotBlank(MapUtil.getStr(map, enums.getKey()))){
                AdminIndexCountResult result = new AdminIndexCountResult();
                result.setName(enums.getName());
                result.setTips(enums.getTips());
                result.setAmount(new BigDecimal(MapUtil.getStr(map, enums.getKey())));
                list.add(result);
            }
        }
        return CommonResult.success(list);
    }

    @ApiOperation(value = "获取审核统计")
    @RequestMapping(value = "/countVerifyNum", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<String> countVerifyNum() {
        return CommonResult.success(null);
    }

    @ApiOperation(value = "转化率报表")
    @ResponseBody
    @RequestMapping(value="/getConversionReport", method=RequestMethod.GET)
    public CommonResult<List<RepConversionDay>> getRepConversionDay() throws ServiceException {
        Date now = new Date();
        Date beginTime = DateUtil.beginOfMonth(now);
        Date endTime = DateUtil.endOfMonth(now);

        ReportSearchForm form = new ReportSearchForm();
        form.setDateFrom(beginTime);
        form.setDateTo(endTime);

        List<RepConversionDay> repconversionDayList = reportLogService.getRepConversionDayList(form);
        Collections.reverse(repconversionDayList);
        return CommonResult.success(repconversionDayList);
    }

    @ApiOperation(value = "获取本月门店销售排行")
    @RequestMapping(value = "/getShopSellList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Map<String,Object>>> getShopSellList() {
        Date now = new Date();
        Date beginTime = DateUtil.beginOfMonth(now);
        Date endTime = DateUtil.endOfMonth(now);
        List<Map<String,Object>> list = adminIndexMapper.getShopSellRankList(beginTime, endTime);
        return CommonResult.success(list);
    }

    @ApiOperation(value = "获取本月商品销售排行")
    @RequestMapping(value = "/getGoodsSellList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Map<String,Object>>> getFoodSellList() {
        Date now = new Date();
        Date beginTime = DateUtil.beginOfMonth(now);
        Date endTime = DateUtil.endOfMonth(now);
        List<Map<String,Object>> list = adminIndexMapper.getGoodsSellRankList(beginTime, endTime);
        return CommonResult.success(list);
    }

}
