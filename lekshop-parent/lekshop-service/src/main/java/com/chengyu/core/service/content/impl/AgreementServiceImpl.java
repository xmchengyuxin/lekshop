package com.chengyu.core.service.content.impl;

import com.chengyu.core.mapper.CmsAgreementMapper;
import com.chengyu.core.model.CmsAgreement;
import com.chengyu.core.model.CmsAgreementExample;
import com.chengyu.core.service.content.AgreementService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @title  协议文案
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class AgreementServiceImpl implements AgreementService {
	
	@Autowired
	private CmsAgreementMapper agreementMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public int updateAgreement(CmsAgreement agreement) {
		agreement.setUpdTime(new Date());
		agreementMapper.updateByPrimaryKeySelective(agreement);
		return agreement.getId();
	}

	@Override
	public CmsAgreement getAgreementByCate(String cate) {
		CmsAgreementExample example = new CmsAgreementExample();
		example.createCriteria().andCateEqualTo(cate);
		return agreementMapper.selectByExampleWithBLOBs(example).get(0);
	}

	@Override
	public List<CmsAgreement> getAgreementList(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		CmsAgreementExample example = new CmsAgreementExample();
		example.setOrderByClause("cate asc");
        return agreementMapper.selectByExampleWithBLOBs(example);
	}
}