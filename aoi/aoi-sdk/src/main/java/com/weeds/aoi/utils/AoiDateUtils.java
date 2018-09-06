package com.weeds.aoi.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AoiDateUtils {
	
	private static Logger logger = LoggerFactory.getLogger(AoiDateUtils.class);
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
    private static Calendar startDate = Calendar.getInstance();  
    private static Calendar endDate = Calendar.getInstance();  
    private static DateFormat df = DateFormat.getDateInstance();  
    private static Date earlydate = new Date();  
    private static Date latedate = new Date();
	
public static final String format_day = "yyyy-MM-dd";
	
	public static final String format_senconde = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 字符串转日期
	 * @param strDate
	 * @param pattern
	 * @return
	 */
	public static Date parseStrToDate(String strDate,String pattern) {
	    SimpleDateFormat formatter = new SimpleDateFormat(pattern);
	    Date strtodate = null;
		try {
			strtodate = formatter.parse(strDate);
		} catch (ParseException e) {
			logger.error("日期获取异常"+e.getMessage(),e);
		}
	    return strtodate;
	}

	/**
	 * 日期转字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateToStr(Date date,String pattern) {
	    SimpleDateFormat formatter = new SimpleDateFormat(pattern);
	    String dateString = formatter.format(date);
	    return dateString;
	}
	
	/** 
     * 计算两个时间相差多少个年 
     *  
     * @param early 
     * @param late 
     * @return 
     * @throws ParseException 
     */  
    public static int yearsBetween(String start, String end) throws ParseException {  
        startDate.setTime(sdf.parse(start));  
        endDate.setTime(sdf.parse(end));  
        return (endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR));  
    } 
	 /** 
     * 计算两个时间相差多少个月 
     *  
     * @param date1 
     *            <String> 
     * @param date2 
     *            <String> 
     * @return int 
     * @throws ParseException 
     */  
    public static int getMonthSpace(String start, String end) throws ParseException { 
    	int result =  0;
    	try {
    		startDate.setTime(sdf.parse(start));  
    		endDate.setTime(sdf.parse(end));  
    		result = yearsBetween(start, end) * 12 + endDate.get(Calendar.MONTH) - startDate.get(Calendar.MONTH);  
		} catch (Exception e) {
			logger.error(""+e.getMessage(),e);
		}
        return result == 0 ? 1 : Math.abs(result);  
  
    } 
    /** 
     * 计算两个时间相差多少个天 
     *  
     * @param early 
     * @param late 
     * @return 
     * @throws ParseException 
     */  
    public static int daysBetween(String start, String end) throws ParseException {  
        // 得到两个日期相差多少天  
        return hoursBetween(start, end) / 24;  
    } 
    /** 
     * 计算两个时间相差多少小时 
     *  
     * @param early 
     * @param late 
     * @return 
     * @throws ParseException 
     */  
    public static int hoursBetween(String start, String end) throws ParseException {  
        // 得到两个日期相差多少小时  
        return minutesBetween(start, end) / 60;  
    }  
  
    /** 
     * 计算两个时间相差多少分 
     *  
     * @param early 
     * @param late 
     * @return 
     * @throws ParseException 
     */  
    public static int minutesBetween(String start, String end) throws ParseException {  
        // 得到两个日期相差多少分  
        return secondesBetween(start, end) / 60;  
    }  
  
    /** 
     * 计算两个时间相差多少秒 
     *  
     * @param early 
     * @param late 
     * @return 
     * @throws ParseException 
     */  
    public static int secondesBetween(String start, String end) throws ParseException {  
        earlydate = df.parse(start);  
        latedate = df.parse(end);  
        startDate.setTime(earlydate);  
        endDate.setTime(latedate);  
        // 设置时间为0时  
        startDate.set(Calendar.HOUR_OF_DAY, 0);  
        startDate.set(Calendar.MINUTE, 0);  
        startDate.set(Calendar.SECOND, 0);  
        endDate.set(Calendar.HOUR_OF_DAY, 0);  
        endDate.set(Calendar.MINUTE, 0);  
        endDate.set(Calendar.SECOND, 0);  
        // 得到两个日期相差多少秒  
        return ((int) (endDate.getTime().getTime() / 1000) - (int) (startDate.getTime().getTime() / 1000));  
    }  
    
	
	public static void main(String[] args) throws Exception{
		System.out.println(getMonthSpace("2013-11-05 12:22:24","2016-04-30 09:57:01"));
		
	}
}
