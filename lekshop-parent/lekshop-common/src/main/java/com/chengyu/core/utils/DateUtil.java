package com.chengyu.core.utils;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATE_SHORT_FORMAT = "yyyyMMdd";
	public static final String DATE_SLASH_FORMAT = "yyyy/MM/dd";
	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String TIME_SHORT_FORMAT="yyyyMMddHHmmss";
	public static final String ORDER_TIME_FORMAT="yy-MM-dd HH:mm:ss";
	public static final String SHORT_TIME_FORMAT="HH:mm:ss";
	
	private static ThreadLocal<SimpleDateFormat> orderTimeFormat = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(ORDER_TIME_FORMAT);
		}
	};
	
	private static ThreadLocal<SimpleDateFormat> dateFormat = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(DATE_FORMAT);
		}
	};
	
	private static ThreadLocal<SimpleDateFormat> dateShortFormat = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(DATE_SHORT_FORMAT);
		}
	};
	
	private static ThreadLocal<SimpleDateFormat> dateTimeFormat = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(TIME_FORMAT);
		}
	};
	
	private static ThreadLocal<SimpleDateFormat> dateFormatSlash = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(DATE_SLASH_FORMAT);
		}
	};
	private static ThreadLocal<SimpleDateFormat> ShortTimeFormat = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(SHORT_TIME_FORMAT);
		}
	};
	
	private static ThreadLocal<SimpleDateFormat> TimeShortFormat = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(TIME_SHORT_FORMAT);
		}
	};
	
	public static String orderTimeFormat(Date date) {
		if (date == null) {
			return "";
		}

		return orderTimeFormat.get().format(date);
	}
	
	/**
	 * Date --> String(yyyy-MM-dd)
	 * @param date
	 * @return
	 */
	public static String dateFormat(Date date) {
		if (date == null) {
			return "";
		}

		return dateFormat.get().format(date);
	}
	
	public static String shortTimeFormat(Date date) {
		if (date == null) {
			return "";
		}

		return ShortTimeFormat.get().format(date);
	}
	
	/**
	 * Double/Integer/Long --> String(yyyy-MM-dd)
	 * @param time
	 * @return
	 */
	public static String dateFormat(Object time){
		if(time == null) return "";
		
		Long value = null;
		if(time instanceof Double) {
			value = ((Double)time).longValue();
		} else if(time instanceof Integer) {
			value = ((Integer)time).longValue();
		} else if(time instanceof BigInteger) {
			value = ((BigInteger)time).longValue();
		} else {
			value = (Long) time;
		}
		if(value.toString().length() == 10) value = value*1000;
		
		return dateFormat.get().format(new Date(value));
	}
	
	/**
	 * Double/Integer/Long --> String(yyyyMMdd)
	 * @param time
	 * @return
	 */
	public static String dateShortFormat(Object time) {
		if(time == null) return "";
		if(time instanceof Date) {
			return dateShortFormat.get().format((Date)time);
		}

		Long value = null;
		if (time instanceof Double) {
			value = ((Double) time).longValue();
		} else if (time instanceof Integer) {
			value = ((Integer) time).longValue();
		} else {
			value = (Long) time;
		}
		if (value.toString().length() == 10) {
			value = value * 1000;
		}

		return dateShortFormat.get().format(new Date(value));
	}
	
	/**
	 * Double/Integer/Long --> String(yyyyMMddHHmmss)
	 * @param time
	 * @return
	 */
	public static String dateTimeShortFormat(Object time) {
		if(time == null) return "";
		if(time instanceof Date) {
			return TimeShortFormat.get().format((Date)time);
		}

		Long value = null;
		if (time instanceof Double) {
			value = ((Double) time).longValue();
		} else if (time instanceof Integer) {
			value = ((Integer) time).longValue();
		} else {
			value = (Long) time;
		}
		if (value.toString().length() == 10) {
			value = value * 1000;
		}

		return dateShortFormat.get().format(new Date(value));
	}
	
	/**
	 * Double/Integer/Long --> String(yyyy/MM/dd)
	 * @param time
	 * @return
	 */
	public static String dateFormatSlash(Object time){
		if(time == null) return "";
		
		Long value = null;
		if(time instanceof Double) {
			value = ((Double)time).longValue();
		} else if(time instanceof Integer) {
			value = ((Integer)time).longValue();
		} else {
			value = (Long) time;
		}
		if(value.toString().length() == 10) value = value*1000;
		
		return dateFormatSlash.get().format(new Date(value));
	}
	
	/**
	 * Date --> String(yyyy-MM-dd HH:mm:ss)
	 * @param date
	 * @return
	 */
	public static String dateTimeFormat(Date date) {
		if (date == null){
			return "";
		}
		return dateTimeFormat.get().format(date);
	}
	
	/**
	 * Double/Integer/Long --> String(yyyy-MM-dd HH:mm:ss)
	 * @param time
	 * @return
	 */
	public static String dateTimeFormat(Object time){
		if(time == null) return "";
		
		Long value = null;
		if(time instanceof Double) {
			value = ((Double)time).longValue();
		} else if(time instanceof Integer) {
			value = ((Integer)time).longValue();
		} else if(time instanceof BigInteger) {
			value = ((BigInteger)time).longValue();
		} else {
			value = (Long) time;
		}
		
		if(value.toString().length() == 10) value = value*1000;
		
		return dateTimeFormat.get().format(new Date(value));
	}
	
	@SuppressWarnings("deprecation")
	public static Date dateParse(Long date) {
		if(date == null) return null;
		
		Date newDate = new Date(date*1000);
		newDate.setHours(0);
		newDate.setMinutes(0);
		newDate.setSeconds(0);
		
		return newDate;
	}
	
	public static Date dateParse(String date) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		try {
			return dateFormat.get().parse(date);
		} catch (ParseException e) {
		}
		
		return null;
	}
	
	public static Date shortDateParse(String date) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		try {
			return dateShortFormat.get().parse(date);
		} catch (ParseException e) {
		}
		
		return null;
	}

	public static Date timeParse(String date) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		try {
			return dateTimeFormat.get().parse(date);
		} catch (ParseException e) {
		}
		return null;
	}
	
	public static Long getCurrentTime() {
		return System.currentTimeMillis()/1000;
	}
	
	public static Date getCurrentDate() {
		try {
			return dateFormat.get().parse(dateFormat.get().format(new Date()));
		} catch (ParseException e) {
		}
		return null;
	}
	
	/**
	 * @return yyyy-MM-dd
	 */
	public static String getCurrentDateStr() {
		return dateFormat.get().format(new Date());
	}
	
	/**
	 * @return yyyyMMdd
	 */
	public static String getShortCurrentDateStr() {
		return dateShortFormat.get().format(new Date());
	}
	
	/**
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentTimeStr(){
		return dateTimeFormat.get().format(new Date());
	}
	
	/**
	 * @return yyyyMMddHHmmss
	 */
	public static String getShortCurrentTimeStr(){
		return TimeShortFormat.get().format(new Date());
	}
	
	/**
	 * 日期装long
	 * @param date
	 * @return
	 */
	public static Long convert(Date date) {
		if(date == null) return null;
		
		return date.getTime()/1000;
	}
	
	/**
	 * 日期字符串转long
	 * @param date
	 * @return
	 */
	public static Long convert(String date) {
		if(date == null) return null;
		
		try {
			if(date.length() > 10) 
				return (dateTimeFormat.get().parse(date)).getTime()/1000;
			
			return (dateFormat.get().parse(date)).getTime()/1000;
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 在给定的时间上加/减XX天
	 * @param date 当前时间
	 * @param days 要加/减的天数
	 * @return
	 */
	public static Date addDay(Date date, Integer days) {
		if(date == null || days == null || days == 0) return date;
		
		Calendar calendar = Calendar.getInstance();   
		calendar.setTime(date); 
		calendar.add(Calendar.DATE, days);
		
		return calendar.getTime();
	}
	
	/**
	 * 在给定的时间上加/减XX秒
	 * @param date 当前时间
	 * @param days 要加/减的秒数
	 * @return
	 */
	public static Date addSeconds(Date date, Integer seconds) {
		if(date == null || seconds == null || seconds == 0) return date;
		
		Calendar calendar = Calendar.getInstance();   
		calendar.setTime(date); 
		calendar.add(Calendar.SECOND, seconds);
		
		return calendar.getTime();
	}
	
	/**
	 * 在给定的时间上加/减XX天
	 * @param date 当前时间
	 * @param days 要加/减的天数
	 * @return
	 */
	public static Long addDay(Long date, Integer days) {
		if(date == null || days == null || days == 0) return date;
		
		Calendar calendar = Calendar.getInstance();   
		calendar.setTimeInMillis(date*1000);
		calendar.add(Calendar.DATE, days);
		
		return calendar.getTimeInMillis()/1000;
	}
	
	/**
	 * 在给定的时间上加/减XX个月
	 * @param date 当前时间
	 * @param months 要加/减的月数
	 * @return
	 */
	public static Date addMonth(Date date, Integer months) {
		if(date == null || months == null || months == 0) return date;
		
		Calendar calendar = Calendar.getInstance();   
		calendar.setTime(date); 
		calendar.add(Calendar.MONTH, months);
		
		return calendar.getTime();
	}
	
	/**
	 * 在给定的时间上加/减XX个月
	 * @param date 当前时间
	 * @param months 要加/减的月数
	 * @return
	 */
	public static Long addMonth(Long date, Integer months) {
		if(date == null || months == null || months == 0) return date;
		
		Calendar calendar = Calendar.getInstance();   
		calendar.setTimeInMillis(date*1000); 
		calendar.add(Calendar.MONTH, months);
		
		return calendar.getTimeInMillis()/1000;
	}
	


	/**
	 * 计算两个日期之间相差的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daysBetween(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}
	
	public static long minutesBetween(Date date) {
		return minutesBetween(dateParse(dateFormat(date)), date);
	}
	
	public static long minutesBetween(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long millsBetween = (time2 - time1);
		
		long secondsBetween = millsBetween/1000 + (millsBetween%1000 > 0 ? 1 : 0);
		
		return secondsBetween/60 + (secondsBetween/60 > 0 ? 1 : 0);
	}
	
	/**
	 * 计算两个日期之间相差的天数,超过30天的月份按30天算(计算利息用)
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daysBetweenMore(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		int date1Year = cal.get(Calendar.YEAR);//得到年
		int date1Month = cal.get(Calendar.MONTH) + 1;//得到月
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		int date2Year = cal.get(Calendar.YEAR);//得到年
		int date2Month = cal.get(Calendar.MONTH) + 1;//得到月
		int date2Day = cal.get(Calendar.DAY_OF_MONTH);//得到日
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		int days = 0;
		if(date1Year == date2Year){//相同年份的时候
			for(int i = date1Month;i <= date2Month; i++){
				if("1,3,5,7,8,10,12".contains(String.valueOf(i))){
					days = days + 1;	
				}
			}
			if(date2Day <= 30){
				days = days -1;
			}
		}else{//不同年份的时候，如2015-6-1 至2016-5-1
			//计算两个时间相差月数
			int months = (date2Year - date1Year)*12 + (date2Month - date1Month);
			for(int i = date1Month; i <= (date1Month + months); i++){
				int j = 0;
				if(i/12 > 0){
					j = i - 12*(i/12);
				}
				if("1,3,5,7,8,10,12".contains(String.valueOf(j == 0 ? i : j))){
					days = days + 1;	
				}
			}
			if(date2Day <= 30){
				days = days -1;
			}
		}
		return Integer.parseInt(String.valueOf(between_days-days));
	} 

	/**
	 * 计算两个日期之间相差的月数
	 */
	public static int monthBetween(Date date1, Date date2){
		Calendar bef = Calendar.getInstance();
		Calendar aft = Calendar.getInstance();
		bef.setTime(date1);
		aft.setTime(date2);
		int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
		return result;
	}
	
	/**
	 * 计算两个日期之间相差几分钟
	 */
	public static long minuteBetween(Long date1,Long date2){
		if(date1 == null || date2 == null) return 0;
		
		long s = (date2 - date1) / 60;
		return s;
	}
	
	/**
	 * 计算两个日期之间相差几分钟
	 */
	public static long minuteBetween(Date date1, Date date2){
		if(date1 == null || date2 == null) return 0;
		
		long s = (date2.getTime() - date1.getTime()) /1000 / 60;
		return s;
	}
	
	/**
	 * 获取当前月份的第一天
	 * @param date
	 * @return
	 */
	public static Long getMonthFirstDay(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));     
	    calendar.set(Calendar.HOUR_OF_DAY, 0);  
	    calendar.set(Calendar.MINUTE, 0);  
	    calendar.set(Calendar.SECOND,0);  
	    return calendar.getTimeInMillis()/1000; 
	}
	
	/**
	 * 获取当前月份的最后一天
	 * @param date
	 * @return
	 */
	public static Long getMonthLastDay(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));  
	    return calendar.getTimeInMillis()/1000;
	}
	
	/**
	 * 获取当前周的第一天
	 * @param date
	 * @return
	 */
	public static Long getWeekFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			calendar.add(Calendar.DATE, -1);
		}
       return calendar.getTimeInMillis()/1000; 
	}
	
	/**
	 * 获取当前周的最后一天
	 * @param date
	 * @return
	 */
	public static Long getWeekLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			calendar.add(Calendar.DATE, -1);
		}
		calendar.add(Calendar.DATE, 6);
      return calendar.getTimeInMillis()/1000; 
	}
	
	/**
	 * 获取指定时间(0点0分0秒)
	 * @param date
	 * @param type -1：上一天 0：当天 1：下一天
	 * @return
	 */
	public static Long getDateTime(Date date, int type) {
		if(date == null) date = new Date();
		date = addDay(date, type);
		
		String strDate = dateFormat.get().format(date) + " 00:00:00";
		try {
			return dateTimeFormat.get().parse(strDate).getTime()/1000;
		} catch (ParseException e) {
		}
		
		return null;
	}
	
	/**
	 * 获取当天00：00：00
	 * @param date
	 * @return
	 */
	public static Long getDateIntiTime(Long time){
		String strTime=dateTimeFormat(time).substring(0, 10);
		return convert(strTime);
	}
	/**
	 * 获取当天23：59：59
	 * @param date
	 * @return
	 */
	public static Long getDateLastTime(Long time){
		String strTime=dateTimeFormat(time).substring(0, 10);
		return convert(strTime)+(3600*24L)-1L;
	}
	
	/**
	 * 计算当月有多少天
	 */
	public static Integer getMonthDays(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE,1-dayOfMonth);
        calendar.add(Calendar.MONTH,1);
        calendar.add(Calendar.DATE,   -1);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        return dayOfMonth;
	}
	
	public static String getWeekOfDate(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(w < 0) w = 0;
        
        return weekDays[w];
    }
}