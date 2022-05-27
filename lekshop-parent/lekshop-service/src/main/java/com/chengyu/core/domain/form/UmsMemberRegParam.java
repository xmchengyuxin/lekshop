package com.chengyu.core.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @title  注册参数
 * @author LeGreen
 * @date   2021/1/8
 */
@Getter
@Setter
public class UmsMemberRegParam {

    @ApiModelProperty(value = "用户名", required = true)
    @NotNull(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "用户类型>>1用户>>2商家", required = true)
    @NotNull(message = "用户类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "密码", required = true)
    @NotNull(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "确认密码", required = true)
    @NotNull(message = "确认密码不能为空")
    private String confirmPassword;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "验证码", required = true)
    @NotNull(message = "验证码不能为空")
    private String code;

    @ApiModelProperty(value = "QQ")
    private String qq;

    @ApiModelProperty(value = "邀请码")
    private String inviteCode;
}
