package com.weeds.aoi.service.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.weeds.aoi.service.system.domain.ShortString;
import com.weeds.aoi.service.system.domain.ShortUrl;
import com.weeds.aoi.service.system.mapper.ShortStringJpaDao;
import com.weeds.aoi.service.system.mapper.ShortStringMapper;
import com.weeds.aoi.service.system.mapper.ShortUrlJpaDao;
import com.weeds.aoi.service.system.mapper.ShortUrlMapper;
import com.weeds.aoi.service.system.service.ShortStringService;
import com.weeds.aoi.utils.AoiStringUtils;
import com.weeds.aoi.utils.GlobalParameters;
import com.weeds.aoi.utils.arith.ShortTextUtil;

@Service
public class ShortStringServiceImpl implements ShortStringService{
	
	@Resource
	ShortUrlJpaDao shortUrlJpaDao;
	@Resource
	ShortUrlMapper shortUrlMapper; 
	@Resource
	ShortStringJpaDao shortStringJpaDao;
	@Resource
	ShortStringMapper shortStringMapper;
	 
	@Override
	public ShortUrl getObject(Map<String, Object> parameters) {
		return shortUrlMapper.getObject(parameters);
	}

	@Override
	public List<ShortUrl> selectAll(Map<String, Object> parameters) {
		return shortUrlMapper.selectAll(parameters);
	}

	@Override
	public String saveShortUrl(ShortUrl shortUrl) {
		Map<String, Object> map = getShortStr(shortUrl.getUrl());
		if(map==null){
			return null;
		}
		String shortStr = (String) map.get("shortStr");
		boolean save = (boolean) map.get("save");
		try {
			if(save){
				shortStr = GlobalParameters.netUrl+shortStr;
				shortUrl.setShortUrl(shortStr);
				shortUrl.setCreateTime(new Date());
				shortUrlJpaDao.save(shortUrl);
			}
		} catch (Exception e) {
			return null;
		}
		return shortStr;
	}
	
	private Map<String, Object> getShortStr(String urlStr){
		String shortStr = null;
		Map<String, Object> map = null;
		try {
			String crc32 = AoiStringUtils.getCrc32(urlStr);
			if(AoiStringUtils.isBlank(crc32)){
				return null;
			}
			map = Maps.newHashMap();
			map.put("save", true);
			Map<String, Object> parameters = Maps.newHashMap();
			parameters.put("url", crc32);
			ShortUrl su = shortUrlMapper.getObject(parameters);//首先检测是否加密过，如果加密过不重复加密
			if(su != null){
				shortStr = su.getShortUrl();
				//还要保证6位长度唯一
				map.put("save", false);
			}else{
				shortStr = getSingle(urlStr);
			}
			map.put("shortStr", shortStr);
		} catch (Exception e) {
		}
		return map;
	}
	private Map<String, Object> getShortStrForShortString(String content){
		String shortStr = null;
		Map<String, Object> map = null;
		try {
			String crc32 = AoiStringUtils.getCrc32(content);
			if(AoiStringUtils.isBlank(crc32)){
				return null;
			}
			map = Maps.newHashMap();
			map.put("save", true);
			Map<String, Object> parameters = Maps.newHashMap();
			parameters.put("content", crc32);
			ShortString ss = shortStringMapper.getObject(parameters);//首先检测是否加密过，如果加密过不重复加密
			if(ss != null){
				shortStr = ss.getShortString();
				//还要保证6位长度唯一
				map.put("save", false);
			}else{
				shortStr = getSingleForShortString(content);
			}
			map.put("shortStr", shortStr);
		} catch (Exception e) {
		}
		return map;
	}
	
	/**
	 * 保证六位唯一性
	 * @param urlStr
	 * @param shortStr
	 * @return
	 */
	public String getSingleForShortString(String content){
		String shortStr = ShortTextUtil.getShortUrl(content, false, 8);
		Map<String, Object> parameters = Maps.newHashMap();
		parameters.put("shortString",shortStr);
		ShortString ss = shortStringMapper.getObject(parameters);
		if(ss != null){
			getSingleForShortString(content);//递归检测
		}
		return shortStr;
	}
	/**
	 * 保证六位唯一性
	 * @param urlStr
	 * @param shortStr
	 * @return
	 */
	public String getSingle(String urlStr){
		String shortStr = ShortTextUtil.getShortUrl(urlStr, false, 6);
		Map<String, Object> parameters = Maps.newHashMap();
		parameters.put("shortUrl",GlobalParameters.netUrl+shortStr);
		ShortUrl su = shortUrlMapper.getObject(parameters);
		if(su != null){
			getSingle(urlStr);//递归检测
		}
		return shortStr;
	}

	@Override
	public ShortString getObjectShortString(Map<String, Object> parameters) {
		return shortStringMapper.getObject(parameters);
	}

	@Override
	public List<ShortString> selectAllShortString(Map<String, Object> parameters) {
		return shortStringMapper.selectAll(parameters);
	}

	@Override
	public String saveShortString(ShortString shortString) {
		Map<String, Object> map = getShortStrForShortString(shortString.getContent());
		if(map==null){
			return null;
		}
		String shortStr = (String) map.get("shortStr");
		boolean save = (boolean) map.get("save");
		try {
			if(save){
				shortString.setShortString(shortStr);
				shortString.setCreateTime(new Date());
				shortStringJpaDao.save(shortString);
			}
		} catch (Exception e) {
			return null;
		}
		return shortStr;
	}

}
