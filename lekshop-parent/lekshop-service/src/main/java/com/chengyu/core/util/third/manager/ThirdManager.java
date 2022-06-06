package com.chengyu.core.util.third.manager;

import com.chengyu.core.util.third.logic.*;
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
	public static final String TFK = "tfk";
	public static final String JDWX = "jdwx";

	public static final String DXB = "dxb";


	@Autowired
	private TaofakeLogic taofakeLogic;
	@Autowired
	private WwywLogic wwywLogic;
	@Autowired
	private AliLogic aliLogic;
	@Autowired
	private LocalLogic localLogic;
	@Autowired
	private QiniuLogic qiniuLogic;
	@Autowired
	private JdwxLogic jdwxLogic;

	public ThirdUtilFactory getThidFactory(String mode) {
		switch (mode) {
		case TFK:
			return taofakeLogic;
		case JDWX:
			return jdwxLogic;
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
