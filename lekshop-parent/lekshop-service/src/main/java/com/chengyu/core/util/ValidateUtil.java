package com.chengyu.core.util;

import com.chengyu.core.exception.ServiceException;

/**
 * @title  校验Util
 * @author LeGreen
 * @date   2021/1/6
 */
public class ValidateUtil {

	public static void validateNull(Object[] validator, String...errors) throws ServiceException{
		for(int i = 0; i < validator.length; i++){
			if(validator[i] == null || org.springframework.util.StringUtils.isEmpty(validator[i])){
				throw new ServiceException(errors[i]);
			}
		}
	}
	
}
