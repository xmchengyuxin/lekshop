package com.chengyu.core.util.map;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class MapPolygonUtil {

	/**
	 * @功能描述		判断是否在多边形区域内
	 * @作者			LeGreen
	 * @创建时间		2020年9月10日
	 * @param 		lng	经度
	 * @param 		lat 纬度
	 * @param 		polygonLngLats 118.007254,24.213934|118.010945,24.207593|118.02545,24.214521|118.020644,24.219217
	 * @return
	 */
	public static boolean isInPolygon(String lng, String lat, String polygonLngLats){
		String[] lngLatArr = polygonLngLats.split("\\|");
		double[] lonArr = new double[lngLatArr.length];
		double[] latArr = new double[lngLatArr.length];
		int i = 0;
		for(String lngLat : lngLatArr){
			lonArr[i] = Double.valueOf(lngLat.split(",")[0]);
			latArr[i] = Double.valueOf(lngLat.split(",")[1]);
			i++;
		}
		return isInPolygon(Double.valueOf(lng), Double.valueOf(lat), lonArr, latArr);
	}
	
	/**
     * 判断是否在多边形区域内
     * 
     * @param pointLon
     *            要判断的点的纵坐标
     * @param pointLat
     *            要判断的点的横坐标
     * @param lon
     *            区域各顶点的纵坐标数组
     * @param lat
     *            区域各顶点的横坐标数组
     * @return
     */
    public static boolean isInPolygon(double pointLon, double pointLat, double[] lon,
            double[] lat) {
        // 将要判断的横纵坐标组成一个点
        Point2D.Double point = new Point2D.Double(pointLon, pointLat);
        // 将区域各顶点的横纵坐标放到一个点集合里面
        List<Point2D.Double> pointList = new ArrayList<Point2D.Double>();
        double polygonPoint_x = 0.0, polygonPoint_y = 0.0;
        for (int i = 0; i < lon.length; i++) {
            polygonPoint_x = lon[i];
            polygonPoint_y = lat[i];
            Point2D.Double polygonPoint = new Point2D.Double(polygonPoint_x, polygonPoint_y);
            pointList.add(polygonPoint);
        }
        return check(point, pointList);
    }
 
    /**
     * 一个点是否在多边形内
     * 
     * @param point
     *            要判断的点的横纵坐标
     * @param polygon
     *            组成的顶点坐标集合
     * @return
     */
    private static boolean check(Point2D.Double point, List<Point2D.Double> polygon) {
        java.awt.geom.GeneralPath peneralPath = new java.awt.geom.GeneralPath();
 
        Point2D.Double first = polygon.get(0);
        // 通过移动到指定坐标（以双精度指定），将一个点添加到路径中
        peneralPath.moveTo(first.x, first.y);
        polygon.remove(0);
        for (Point2D.Double d : polygon) {
            // 通过绘制一条从当前坐标到新指定坐标（以双精度指定）的直线，将一个点添加到路径中。
            peneralPath.lineTo(d.x, d.y);
        }
        // 将几何多边形封闭
        peneralPath.lineTo(first.x, first.y);
        peneralPath.closePath();
        // 测试指定的 Point2D 是否在 Shape 的边界内。
        return peneralPath.contains(point);
    }
    
    public static void main(String[] args) {
//		118.013410427,24.205013854
		boolean isInPlolygon = MapPolygonUtil.isInPolygon("118.013410427", "24.205013854", "117.991523,24.212675|117.998207,24.19874|118.014713,24.203302|118.024989,24.199611|118.030991,24.212053|118.029309,24.217485|118.011592,24.229578");
		System.out.println(isInPlolygon);
	}
}
