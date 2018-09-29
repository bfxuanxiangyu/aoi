package com.weeds.aoi.service.system.service;

import java.util.List;
import java.util.Map;

import com.weeds.aoi.service.system.domain.ShortString;
import com.weeds.aoi.service.system.domain.ShortUrl;

public interface ShortStringService {
	
	ShortUrl getObject(Map<String, Object> parameters);
	
	List<ShortUrl> selectAll(Map<String, Object> parameters);
	
	String saveShortUrl(ShortUrl shortUrl);
	
	ShortString getObjectShortString(Map<String, Object> parameters);
	
	List<ShortString> selectAllShortString(Map<String, Object> parameters);
	
	String saveShortString(ShortString shortString);

}
