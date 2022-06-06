package com.chengyu.core.domain.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterfaceConfig {

    @ApiModelProperty(value = "商户ID")
    private String appId;

    @ApiModelProperty(value = "密钥")
    private String secret;

    @ApiModelProperty(value = "请求地址")
    private String apiUrl;

}