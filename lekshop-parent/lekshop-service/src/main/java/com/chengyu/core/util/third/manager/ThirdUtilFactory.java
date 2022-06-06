package com.chengyu.core.util.third.manager;

import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.result.GoodsThirdResult;
import com.chengyu.core.exception.ServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @title  第三方工具工厂
 * @author LeGreen
 * @date   2021/10/22
 */
@Component
public abstract class ThirdUtilFactory {

	/**
	 * 身份证校验
	 * @author LeGreen
	 * @date   2021/10/22
	 * @param  idCard
	 * @param  name
	 * @return java.lang.String
	 */
	public abstract String approveRealname(String idCard, String name) throws ServiceException;

	/**
	 * 银行卡校验
	 * @author LeGreen
	 * @date   2021/10/22
	 * @param  accountNo
	 * @param  accountName
	 * @param  idCard
	 * @return java.lang.String
	 * @throws ServiceException 业务异常
	 */
	public abstract String approveBank(String accountNo, String accountName, String idCard) throws ServiceException;

	/**
	 * 上传
	 * @author LeGreen
	 * @date   2022/4/22
	 * @param  file
	 * @param  fileName
	 * @param  type
	 * @return String
	 * @throws ServiceException 业务异常
	 */
	public abstract String upload(MultipartFile file, String fileName, int type) throws ServiceException;

	/**
	 * 获取商品详情
	 * @author LeGreen
	 * @date   2022/6/6
	 * @param  url
	 * @return GoodsThirdResult
	 * @throws ServiceException 业务异常
	 */
    public abstract GoodsThirdResult getGoodsDetail(String url) throws ServiceException;
}