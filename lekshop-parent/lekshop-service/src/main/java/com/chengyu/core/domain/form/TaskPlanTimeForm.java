package com.chengyu.core.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @title  任务计划时间表单
 * @author LeGreen
 * @date   2021/1/11
 */
@Getter
@Setter
public class TaskPlanTimeForm {

    @ApiModelProperty(value = "关键词")
    private String goodsKeyword;

    @ApiModelProperty(value = "关键词位置")
    private String goodsLocation;

    @ApiModelProperty(value = "发布时间")
    private Date publishTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "过期时间")
    private Date expiredTime;

    @ApiModelProperty(value = "发布数量")
    private Integer num;

    @ApiModelProperty(value = "关键字数量")
    private String keywordNum;

    @ApiModelProperty(value = "放出数量")
    private Integer outNum;

    @ApiModelProperty(value = "间隔分钟数")
    private Integer intervalMinutes;

    @ApiModelProperty(value = "1间隔模式>>2时间段模式")
    private Integer timeType;

    @ApiModelProperty(value = "收藏店铺>>0关>>1开")
    private Integer zzCollectShop = 0;

    @ApiModelProperty(value = "收藏商品>>0关>>1开")
    private Integer zzCollectShopGoods = 0;

    @ApiModelProperty(value = "加购物车>>0关>>1开")
    private Integer zzAddCar = 0;

    @ApiModelProperty(value = "点击推荐>>0关>>1开")
    private Integer zzPointSpread = 0;

    @ApiModelProperty(value = "浏览任务收藏店铺>>0关>>1开")
    private Integer llZzCollectShop = 0;

    @ApiModelProperty(value = "浏览任务收藏商品>>0关>>1开")
    private Integer llZzCollectShopGoods = 0;

    @ApiModelProperty(value = "浏览任务加购物车>>0关>>1开")
    private Integer llZzAddCar = 0;

    @ApiModelProperty(value = "浏览任务点击推荐>>0关>>1开")
    private Integer llZzPointSpread = 0;

    @ApiModelProperty(value = "浏览任务发布数量")
    private Integer llNum = 0;

    @ApiModelProperty(value = "浏览任务增值数量")
    private Integer llZzNum = 0;
}