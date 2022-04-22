package com.chengyu.core.service.config;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.ConfigSearch;

import java.util.List;

/**
 * @title  搜索关键词配置
 * @author LeGreen
 * @date   2022/4/22
 */
public interface ConfigSearchService {

    /**
     * 添加搜索关键词
     * @author LeGreen
     * @date   2022/4/22
     * @param  search
     * @throws ServiceException 业务异常
     */
    void addConfigSearch(ConfigSearch search) throws ServiceException;

    /**
     * 更新搜索关键词
     * @author LeGreen
     * @date   2022/4/22
     * @param  search
     * @throws ServiceException 业务异常
     */
    void updateConfigSearch(ConfigSearch search) throws ServiceException;

    /**
     * 删除搜索关键词
     * @author LeGreen
     * @date   2022/4/22
     * @param  id
     * @throws ServiceException 业务异常
     */
    void deleteConfigSearchById(Integer id) throws ServiceException;

    /**
     * 根据ID获取搜索关键词
     * @author LeGreen
     * @date   2022/4/22
     * @param  id
     * @return com.chengyu.core.model.ConfigSearch
     * @throws ServiceException 业务异常
     */
    ConfigSearch getConfigSearchById(Integer id) throws ServiceException;

    /**
     * 搜索关键词列表
     * @author LeGreen
     * @date   2022/4/22
     * @param  keyword
     * @param  page
     * @param  pageSize
     * @return List<ConfigSearch>
     * @throws ServiceException 业务异常
     */
    List<ConfigSearch> getConfigSearchList(Integer status, String keyword, Integer page, Integer pageSize) throws ServiceException;

    /**
     * 获取前十个热门关键词
     * @author LeGreen
     * @date   2022/4/22
     * @return List<ConfigSearch>
     */
    List<ConfigSearch> getTenSearchList();

    /**
     * 更新关键词搜索次数
     * @author LeGreen
     * @date   2022/4/22
     * @param  keyword
     */
    void addSearch(String keyword);
}
