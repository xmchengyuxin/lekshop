package com.chengyu.core.utils;

public class LocationUtils {
	private static double EARTH_RADIUS = 6378.137;
	 
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}
 
	/**
	 * 通过经纬度获取距离(单位：米)
	 * 
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return 距离
	 */
	public static double getDistance(double lat1, double lng1, double lat2,
			double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000d) / 10000d;
		s = s * 1000;
		return s;
	}
	
	public static double getDistance(String lat1, String lng1, String lat2,
			String lng2) {
		return getDistance(Double.valueOf(lat1), Double.valueOf(lng1), Double.valueOf(lat2), Double.valueOf(lng2));
	}
 
	public static void main(String[] args) {
		double distance = getDistance(34.2675560000, 108.9534750000,
				34.2464320000, 108.9534750000);
		System.out.println("距离" + distance / 1000 + "公里");
	}
}
