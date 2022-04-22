package com.chengyu.core.service.content;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.CmsAgreement;

import java.util.List;

/**
 * @title  协议文案
 * @author LeGreen
 * @date   2021/1/6
 */
public interface AgreementService {

	/**
	 * 更新协议文案
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  agreement
	 * @return int
	 * @throws ServiceException
	 */
	int updateAgreement(CmsAgreement agreement) throws ServiceException;

	/**
	 * 根据类型查询协议文案
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  cate
	 * @return com.chengyu.core.model.CmsAgreement
	 * @throws ServiceException
	 */
	CmsAgreement getAgreementByCate(String cate) throws ServiceException;

	/**
	 * 协议文案列表
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  page
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.CmsAgreement>
	 * @throws ServiceException
	 */
	List<CmsAgreement> getAgreementList(Integer page, Integer pageSize) throws ServiceException;
}