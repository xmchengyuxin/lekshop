package com.chengyu.core.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author LeGreen
 * @date 2021/7/10
 */
@Getter
@Setter
public class GiftDeliveryForm {
    @ApiModelProperty(value = "快递平台类型， 0 淘宝系； 1 拼多多系； 2 京东系")
    private Integer belongTerrace;
    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    @ApiModelProperty(value = "快递ID")
    private Integer expressSheetId;
    @ApiModelProperty(value = "平台订单号")
    private String platformNo;
    @ApiModelProperty(value = "收货地址")
    private List<ReceiveAddressForm> addressFormList;
    @ApiModelProperty(value = "寄件人信息")
    private ReceiveAddressForm sendAddressForm;
}
