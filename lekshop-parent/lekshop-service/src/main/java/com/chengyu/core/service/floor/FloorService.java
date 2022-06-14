package com.chengyu.core.service.floor;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.FloorMobilePages;
import com.chengyu.core.model.FloorMobileTemplate;

import java.util.List;

/**
 * @title  装修商城
 * @author LeGreen
 * @date   2022/6/13
 */
public interface FloorService {

    /**
     * 添加装修模板
     * @author LeGreen
     * @date   2022/4/22
     * @param  template
     * @throws ServiceException 业务异常
     */
    void addFloorMobileTemplate(FloorMobileTemplate template) throws ServiceException;

    /**
     * 更新装修模板
     * @author LeGreen
     * @date   2022/4/22
     * @param  template
     * @throws ServiceException 业务异常
     */
    void updateFloorMobileTemplate(FloorMobileTemplate template) throws ServiceException;

    /**
     * 删除装修模板
     * @author LeGreen
     * @date   2022/4/22
     * @param  id
     * @throws ServiceException 业务异常
     */
    void deleteFloorMobileTemplateById(Integer id) throws ServiceException;

    /**
     * 装修商城列表
     * @author LeGreen
     * @date   2022/4/22
     * @param  page
     * @param  pageSize
     * @return List<FloorMobileTemplate>
     * @throws ServiceException 业务异常
     */
    List<FloorMobileTemplate> getFloorMobileTemplateList(Integer type, Integer page, Integer pageSize) throws ServiceException;

    /**
     * 保存模板
     * @author LeGreen
     * @date   2022/6/13
     * @param  templateId
     * @param  name
     * @param  pageData
     */
    void saveMobileData(Integer templateId, String name, Integer type, Integer status, String pageData, String oriData);

    /**
     * 获取当前首页模板配置
     * @author LeGreen
     * @date   2022/6/13
     * @return List<FloorMobilePages>
     */
    List<FloorMobilePages> getCurrentIndexFloor();

    /**
     * 获取模板装修数据
     * @author LeGreen
     * @date   2022/6/13
     * @param  templateId
     * @return List<FloorMobilePages>
     */
    List<FloorMobilePages> getFloorMobileDataList(Integer templateId);

    /**
     * 獲取裝修模板
     * @author LeGreen
     * @date   2022/6/14
     * @param  templateId
     * @return FloorMobileTemplate
     */
    FloorMobileTemplate getFloorMobileTemplate(Integer templateId);
}
