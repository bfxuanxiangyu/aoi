package com.weeds.aoi.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

/**
 * 距离计算公式类
 * @author simon
 * 2018年7月11日
 * The spirit is full of spirit
 */
public class LocationUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(LocationUtils.class);
	private static final DecimalFormat df=new DecimalFormat("0.000");
	
	private static final double EARTH_RADIUS = 6378.137;

    private static double rad(double d){
        return d * Math.PI / 180.0;
    }

    public static Double getmeter(double long1, double lat1, double long2, double lat2) {
    	try {
    		double a, b, d, sa2, sb2;
    		lat1 = rad(lat1);
    		lat2 = rad(lat2);
    		a = lat1 - lat2;
    		b = rad(long1 - long2);
    		
    		sa2 = Math.sin(a / 2.0);
    		sb2 = Math.sin(b / 2.0);
    		d = 2   * EARTH_RADIUS
    				* Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)
    				* Math.cos(lat2) * sb2 * sb2));
    		d= d * 1000;
    		BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.UP);
    		
    		return bg.doubleValue();
		} catch (Exception e) {
			return null;
		}
    }

    public static String getStringmeter(double long1, double lat1, double long2, double lat2) {
        double a, b, d, sa2, sb2;
        lat1 = rad(lat1);
        lat2 = rad(lat2);
        a = lat1 - lat2;
        b = rad(long1 - long2);

        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2   * EARTH_RADIUS
                * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)
                * Math.cos(lat2) * sb2 * sb2));
        d= d * 1000;
        BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.UP);

        return String.valueOf(bg.doubleValue());

    }
    
    /**
     * 时分秒转经纬度
     * Decimal Degrees = Degrees + minutes/60 + seconds/3600
	       例：57°55'56.6" =57+55/60+56.6/3600     114°65'24.6"=114+65/60+24.6/3600
     * @param minute
     * @return
     */
    public static String getLnglat(String minute){
    	String value = "";
    	try {
    		String array[] = minute.split(",");
    		Double d = Double.valueOf(array[0])+Double.valueOf(array[1])/60+Double.valueOf(array[2])/3600;
    		value = String.valueOf(d);
    	} catch (Exception e) {
    		logger.error("时分秒转经纬度异常"+e.getMessage(),e);
    	}
    	return value;
    }
    /**
     * 经纬度转时分秒
     *  Decimal Degrees = Degrees + minutes/60 + seconds/3600
		例：
     * @param lnglat
     * @return
     */
    public static String getMinute(String lnglat){
    	String value = "";
    	try {
    		Map<String, Object> map = Maps.newHashMap();
    		String[] array=lnglat.toString().split("[.]");
            String degrees=array[0];//得到度
            map.put("degrees", degrees);

            Double m=Double.parseDouble("0."+array[1])*60;
            String[] array1=m.toString().split("[.]");
            String minutes=array1[0];//得到分
            map.put("minutes", minutes);

            Double s=Double.parseDouble("0."+array1[1])*60;
            String seconds=df.format(s);//得到秒
            map.put("seconds", seconds);
            value = AoiStringUtils.getJsonObj(map);
		} catch (Exception e) {
			logger.error("经纬度转时分秒异常"+e.getMessage(),e);
		}
    	return value;
    }
    
    public static void main(String[] args) {
		System.out.println(getmeter(113.658575, 34.79018, 113.660434, 34.788854));
		System.out.println(getMinute("113.658575"));
		String minute = "113,39,30.870";
		System.out.println(getLnglat(minute));
	}

}
