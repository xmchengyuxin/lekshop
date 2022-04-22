package com.chengyu.core.util.third.manager;

import com.chengyu.core.util.third.logic.AliLogic;
import com.chengyu.core.util.third.logic.LocalLogic;
import com.chengyu.core.util.third.logic.QiniuLogic;
import com.chengyu.core.util.third.logic.WwywLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @title  第三方工具管理
 * @author LeGreen
 * @date   2021/10/22
 */
@Component
public class ThirdManager {
	
	public static final String WWYW = "wwyw";
	public static final String ALI = "ali";
	public static final String LOCAL = "local";
	public static final String QINIU = "qiniu";

	public static final String DXB = "dxb";


	@Autowired
	private WwywLogic wwywLogic;
	@Autowired
	private AliLogic aliLogic;
	@Autowired
	private LocalLogic localLogic;
	@Autowired
	private QiniuLogic qiniuLogic;

	public ThirdUtilFactory getThidFactory(String mode) {
		switch (mode) {
		case WWYW:
			return wwywLogic;
		case ALI:
			return aliLogic;
		case LOCAL:
			return localLogic;
		case QINIU:
			return qiniuLogic;
		default:
			return null;
		}
	}

}
