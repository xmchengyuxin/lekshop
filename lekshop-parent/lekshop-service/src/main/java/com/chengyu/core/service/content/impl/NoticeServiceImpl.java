package com.chengyu.core.service.content.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.CmsNoticeMapper;
import com.chengyu.core.model.CmsNotice;
import com.chengyu.core.model.CmsNoticeExample;
import com.chengyu.core.service.content.NoticeService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @title  公告实现类
 * @author LeGreen
 * @date   2021/1/7
 */
@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private CmsNoticeMapper noticeMapper;

	@Override
	public List<CmsNotice> getNoticeList(String cate, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		CmsNoticeExample example = new CmsNoticeExample();
		example.setOrderByClause("add_time desc");
		CmsNoticeExample.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(cate)){
			criteria.andCateEqualTo(cate);
		}
		return noticeMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addNotice(CmsNotice notice) throws ServiceException {
		notice.setUpdTime(DateUtil.date());
		if(notice.getId() == null){
			CmsNotice haveNotice = this.getNoticeByCate(notice.getCate());
			if(haveNotice != null){
				throw new ServiceException("公告唯一标识重复");
			}
			notice.setAddTime(notice.getUpdTime());
			noticeMapper.insertSelective(notice);
		}else{
			noticeMapper.updateByPrimaryKeyWithBLOBs(notice);
		}
	}

	@Override
	public CmsNotice getNoticeByCate(String cate) {
		CmsNoticeExample example = new CmsNoticeExample();
		CmsNoticeExample.Criteria criteria = example.createCriteria();
		criteria.andCateEqualTo(cate).andStatusEqualTo(CommonConstant.YES_INT);
		List<CmsNotice> list = noticeMapper.selectByExampleWithBLOBs(example);
		return CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteNotice(Integer id) {
		noticeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public CmsNotice getNoticeById(Integer noticeId) {
		return noticeMapper.selectByPrimaryKey(noticeId);
	}

}