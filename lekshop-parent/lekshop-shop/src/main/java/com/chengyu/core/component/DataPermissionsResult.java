package com.chengyu.core.component;

import lombok.Data;

@Data
public class DataPermissionsResult {

    //是否管理员
    private Boolean isAdmin = false;

    //当前shopId
    private Integer shopId;

    //shopId对应的数据库字段
    private String queryShopIdField;
}
