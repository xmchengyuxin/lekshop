package com.chengyu.core.util.kuaidi;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryDeliveryData implements Serializable {

    private String time;
    private String context;
    private String ftime;
    private String areaCode;
    private String areaName;
    private String status;
    private String areaCenter;
    private String areaPinYin;
    private String statusCode;
}
