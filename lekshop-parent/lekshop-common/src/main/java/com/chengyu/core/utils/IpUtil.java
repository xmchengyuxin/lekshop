package com.chengyu.core.utils;

import org.apache.commons.lang.StringUtils;

public class IpUtil {
	/**
	 * IP -- 字符串转Long
	 * @param ipAddress
	 * @return
	 */
	public static Long ipStrToLong(String ipAddress) {
		if (StringUtils.isEmpty(ipAddress))
			return 0L;

		String[] ipArray = ipAddress.split("\\.");
		if (ipArray.length < 4)
			return 0L;

		Long ip = Long.parseLong(ipArray[0]) << 24;
		ip += Long.parseLong(ipArray[1]) << 16;
		ip += Long.parseLong(ipArray[2]) << 8;
		ip += Long.parseLong(ipArray[3]);

		return ip;
	}
	
	/**
	 * IP -- Long转字符串
	 * @param ipAddress
	 * @return
	 */
	public static String ipLongToStr(Long ipAddress) {
		String ip = ((ipAddress & 0xFF000000) >> 24) + ".";
		ip += ((ipAddress & 0x00FF0000) >> 16) + ".";
		ip += ((ipAddress & 0x0000FF00) >> 8) + ".";
		ip += (ipAddress & 0x000000FF);
		return ip;
	}
}