package com.chengyu.core.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @title  任务查询表单
 * @author LeGreen
 * @date   2021/1/8
 */
@Getter
@Setter
public class TaskSearchForm {

    @ApiModelProperty(value = "订单编号")
    private String taskNo;

    @ApiModelProperty(value = "任务编号")
    private String parentTaskNo;

    @ApiModelProperty(value = "快递单号")
    private String expressNo;

    @ApiModelProperty(value = "订单号(第三方)")
    private String orderNo;

    @ApiModelProperty(value = "子任务编号")
    private String buyerTaskNo;

    @ApiModelProperty(value = "任务Id")
    private Integer taskId;

    @ApiModelProperty(value = "用户")
    private Integer memberId;

    @ApiModelProperty(value = "卖家")
    private Integer sellerMemberId;

    @ApiModelProperty(value = "用户")
    private String memberName;

    @ApiModelProperty(value = "任务区栏目ID")
    private Integer taskColumId;

    @ApiModelProperty(value = "开始时间")
    private Date dateFrom;

    @ApiModelProperty(value = "结束时间")
    private Date dateTo;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "卖家号")
    private String sellerName;

    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "任务状态(公共字段)")
    private Integer status;

    @ApiModelProperty(value = "垫付任务状态>>0待支付>>1等待接手>>2等待支付>>3待发货/待返款>>4待收货>>5已收货评价>>6已完成>>7已取消>>8已锁定>>9已下架")
    private Integer advancesStatus;

    @ApiModelProperty(value = "流量任务状态>>1进行中>>2已到期>>3已完成>>4已锁定>>5已下架")
    private Integer trafficStatus;

    @ApiModelProperty(value = "锁定状态>>0未锁>>1已锁")
    private Integer lockStatus;

    @ApiModelProperty(value = "排序条件>>综合:传空>>佣金:buyer_mission desc/asc>>最新:start_time desc")
    private String orderByCondion;

    @ApiModelProperty(value = "买号")
    private Integer buyerMemberId;

    @ApiModelProperty(value = "刷手")
    private String buyerMemberName;

    @ApiModelProperty(value = "买号")
    private Integer buyerId;

    @ApiModelProperty(value = "买号")
    private String buyerName;

    @ApiModelProperty(value = "卖家")
    private Integer sellerId;

    @ApiModelProperty(value = "代理")
    private Integer agentId;

    @ApiModelProperty(value = "0未插旗或失败>>1插旗成功")
    private Integer flagStatus;

    @ApiModelProperty(value = "插旗状态")
    private Integer flag;

    private Date startTime;

    @ApiModelProperty(value = "0待审核>>1已审核")
    private Integer verifyStatus;

    @ApiModelProperty(value = "0未驳回>>1商家提交驳回>>2支付驳回>>3发货驳回")
    private Integer rejectStatus;

    @ApiModelProperty(value = "0未删除>>1已删除")
    private Integer delInd;

    @ApiModelProperty(value = "自行审核买号>>0待审核>1已通过")
    private Integer buyerVerifyStatus;

    @ApiModelProperty(value = "分站ID")
    private Integer subId;

    @ApiModelProperty(value = "是否购买礼品>>0未购买>>1已购买")
    private Integer giftStatus;

    @ApiModelProperty(value = "0未获取单号>>1已获取单号>>2获取失败")
    private Integer getExpressNoStatus;

    @ApiModelProperty(value = "0图文>>1文字>>2视频>>3普通")
    private Integer commentType;

    @ApiModelProperty(value = "商品ID")
    private String goodsId;

    @ApiModelProperty(value = "返款方式")
    private Integer rebatesType;

    @ApiModelProperty(value = "员工ID")
    private Integer staffId;

    @ApiModelProperty(value = "接单开始时间")
    private Date takeDateFrom;

    @ApiModelProperty(value = "接单结束时间")
    private Date takeDateTo;

    @ApiModelProperty(value = "支付开始时间")
    private Date payDateFrom;

    @ApiModelProperty(value = "支付结束时间")
    private Date payDateTo;

    private List<Integer> statusList;
}