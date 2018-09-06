package com.weeds.aoi.web.main;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.weeds.aoi.utils.AoiStringUtils;
import com.weeds.aoi.utils.LocationUtils;
import com.weeds.aoi.utils.NetUtils;
import com.weeds.aoi.utils.SmsBankPatternUtils;

@Controller
@RequestMapping("/api")
public class WebmainDataController {

	private Logger logger = LoggerFactory.getLogger(WebmainDataController.class);
	
	
	@ResponseBody
	@RequestMapping("/extract/money")
	public String extractMoney(String content){
		String value = "";
		if(AoiStringUtils.isBlank(content)){
			return AoiStringUtils.failJson("不接收空数据", null);
		}
		try {
			content =new String(Base64Utils.decode(content.getBytes()),"UTF-8");
		} catch (Exception e) {
			return AoiStringUtils.failJson("参数异常", null);
		}
		value = SmsBankPatternUtils.singleExtract(content);
		return value;
	}
	@ResponseBody
	@RequestMapping("/extract/batch/money")
	public String batchextractMoney(String content[]){
		String value = "";
		if(content == null){
			return AoiStringUtils.failJson("不接收空数据", null);
		}
		if(content.length>100){
			return AoiStringUtils.failJson("批处理支持100条/次", null);
		}
		value = SmsBankPatternUtils.singleExtract(content);
		return value;
	}
	@ResponseBody
	@RequestMapping("/extract/unitip")
	public String localIp(){
		try {
			Map<String, String> maps = Maps.newHashMap();
			maps.put("intranet_ip", NetUtils.INTRANET_IP);
			maps.put("internet_ip", NetUtils.INTERNET_IP);
			return AoiStringUtils.printJson("获取成功", maps);
		} catch (Exception e) {
			logger.error(""+e.getMessage(),e);
			return AoiStringUtils.failJson("获取异常", null);
		}
	}
	@ResponseBody
	@RequestMapping("/extract/parsingip")
	public String parsingip(String ip){
		try {
			if(AoiStringUtils.isBlank(ip)){
				return AoiStringUtils.failJson("不接收空数据", null);
			}
			String value = NetUtils.getIpInfo(new String(Base64Utils.decode(ip.getBytes()),"UTF-8"));
			return value;
		} catch (Exception e) {
			logger.error(""+e.getMessage(),e);
			return AoiStringUtils.failJson("解析异常", null);
		}
	}
	/**
	 * 两点间距离计算
	 * @param startLnglat
	 * @param endLnglat
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/lnglat/calculate")
	public String calculate(String startLnglat,String endLnglat){
		try {
			if(AoiStringUtils.isBlank(startLnglat) || AoiStringUtils.isBlank(endLnglat)){
				return AoiStringUtils.failJson("不接收空数据", null);
			}
			startLnglat = startLnglat.replace("，", ",").replaceAll(" ","");
			endLnglat = endLnglat.replace("，", ",").replaceAll(" ","");
			if(!startLnglat.contains(",") && !endLnglat.contains(",")){
				return AoiStringUtils.failJson("不符合规范，请查看示例", null);
			}
			String startArray[] = startLnglat.split(",");
			String endArray[] = endLnglat.split(",");
			if(startArray.length != 2 || endArray.length != 2){
				return AoiStringUtils.failJson("不符合规范，请查看示例", null);
			}
			Double long1 = Double.valueOf(startArray[0]);
			Double lat1 = Double.valueOf(startArray[1]);
			Double long2 = Double.valueOf(endArray[0]);
			Double lat2 = Double.valueOf(endArray[1]);
			Double value = LocationUtils.getmeter(long1, lat1, long2, lat2);
			if(value==null){
				return AoiStringUtils.failJson("不符合解析规则", null);
			}
			return AoiStringUtils.printJson("计算成功", value);
		} catch (Exception e) {
			logger.error(""+e.getMessage(),e);
			return AoiStringUtils.failJson("解析异常", null);
		}
	}
	/**
	 * 经纬度时分秒转换
	 * @param startLnglat
	 * @param endLnglat
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/lnglat/transmute/lnglat")
	public String transmute(String lnglat){
		try {
			if(AoiStringUtils.isBlank(lnglat)){
				return AoiStringUtils.failJson("不接收空数据", null);
			}
			if(!lnglat.contains(".")){
				return AoiStringUtils.failJson("不符合经纬度规则", null);
			}
			String value = LocationUtils.getMinute(lnglat);
			if(AoiStringUtils.isBlank(value)){
				return AoiStringUtils.failJson("不符合解析规则", null);
			}
			return AoiStringUtils.printJson("sucess", value);
		} catch (Exception e) {
			logger.error(""+e.getMessage(),e);
			return AoiStringUtils.failJson("解析异常", null);
		}
	}
	/**
	 * 经纬度时分秒转换
	 * @param startLnglat
	 * @param endLnglat
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/lnglat/transmute/minute")
	public String transmuteMinute(String minute){
		try {
			if(AoiStringUtils.isBlank(minute)){
				return AoiStringUtils.failJson("不接收空数据", null);
			}
			String value = LocationUtils.getLnglat(minute);
			if(AoiStringUtils.isBlank(value)){
				return AoiStringUtils.failJson("不符合解析规则", null);
			}
			return AoiStringUtils.printJson("sucess", value);
		} catch (Exception e) {
			logger.error(""+e.getMessage(),e);
			return AoiStringUtils.failJson("解析异常", null);
		}
	}
	
	
}
