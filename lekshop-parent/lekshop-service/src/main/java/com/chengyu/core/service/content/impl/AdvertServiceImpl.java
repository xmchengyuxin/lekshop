package com.chengyu.core.service.content.impl;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.CmsAdvertMapper;
import com.chengyu.core.mapper.CustomAdvertMapper;
import com.chengyu.core.model.CmsAdvert;
import com.chengyu.core.model.CmsAdvertExample;
import com.chengyu.core.service.content.AdvertService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @title  广告实现类
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class AdvertServiceImpl implements AdvertService {
	
	@Autowired
	private CmsAdvertMapper advertMapper;
	@Autowired
	private CustomAdvertMapper customAdvertMapper;
	

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateAdvert(CmsAdvert advert) throws ServiceException {
		CmsAdvert haveAdvert = this.getAdvertByCate(advert.getCate());
		if(haveAdvert != null && !haveAdvert.getId().equals(advert.getId())) {
			throw new ServiceException("同一个位置只能有一个广告");
		}
		
		advert.setUpdTime(new Date());
		advertMapper.updateByPrimaryKeySelective(advert);
	}

	@Override
	public CmsAdvert getAdvertByCate(String cate) {
		CmsAdvertExample example = new CmsAdvertExample();
		example.createCriteria().andCateEqualTo(cate);
		List<CmsAdvert> advertList = advertMapper.selectByExample(example);
		return (advertList == null || advertList.isEmpty()) ? null : advertList.get(0);
	}

	@Override
	public List<CmsAdvert> getAdvertList(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		CmsAdvertExample example = new CmsAdvertExample();
        return advertMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addNums(Integer id) {
		customAdvertMapper.updateAdvertNums(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addAdvert(CmsAdvert advert) throws ServiceException {
		CmsAdvert haveAdvert = this.getAdvertByCate(advert.getCate());
		if(haveAdvert != null) {
			throw new ServiceException("同一品牌同一个位置只能有一个广告");
		}
		advert.setAddTime(new Date());
		advert.setNums(0);
		advertMapper.insert(advert);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteAdvert(Integer id) {
		advertMapper.deleteByPrimaryKey(id);
	}
	
}