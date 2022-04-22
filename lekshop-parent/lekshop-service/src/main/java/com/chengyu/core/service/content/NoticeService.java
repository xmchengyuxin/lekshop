package com.chengyu.core.service.content;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.CmsNotice;

import java.util.List;

/**
 * @title  公告弹出框
 * @author LeGreen
 * @date   2021/1/7
 */
public interface NoticeService {

    /**
     * 公告列表
     * @author LeGreen
     * @date   2021/1/7
     * @param  cate
     * @param  page
     * @param  pageSize
     * @return java.util.List<com.chengyu.core.model.CmsNotice>
     */
    List<CmsNotice> getNoticeList(String cate, Integer page, Integer pageSize);

    /**
     * 添加公告
     * @author LeGreen
     * @date   2021/1/7
     * @param  notice
     * @throws ServiceException 业务异常
     */
    void addNotice(CmsNotice notice) throws ServiceException;

    /**
     * 根据标识获取公告
     * @author LeGreen
     * @date   2021/1/7
     * @param  cate
     * @return com.chengyu.core.model.CmsNotice
     */
    CmsNotice getNoticeByCate(String cate);

    /**
     * 删除公告
     * @author LeGreen
     * @date   2021/1/7
     * @param  id
     */
    void deleteNotice(Integer id);

    /**
     * 获取公告详情
     * @author LeGreen
     * @date   2021/1/9
     * @param  noticeId
     * @return com.chengyu.core.model.CmsNotice
     */
    CmsNotice getNoticeById(Integer noticeId);

}
