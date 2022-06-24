package com.chengyu.core.service.floor;

import com.chengyu.core.model.FloorGlobalStyle;

import java.util.Map;

/**
 * @title  全局样式
 * @author LeGreen
 * @date   2022/6/24
 */
public interface FloorGlobalStyleService {

    void addGlobalStyle(FloorGlobalStyle style);

    Map<String, String> getGlobalStyleByCache();

    Map<String, String> getGlobalStyleByNoCache();
}
