package com.chengyu.core.domain.result;

import com.chengyu.core.model.UmsShopAccountRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopAccountRoleResult extends UmsShopAccountRole {

    private String roleName;

    private String deptName;
}
