/*
 * Copyright (C) 2017 Shanghai sinnren soft Co., Ltd
 *
 * All copyrights reserved by Shanghai sinnren.
 * Any copying, transferring or any other usage is prohibited.
 * Or else, Shanghai sinnren possesses the right to require legal 
 * responsibilities from the violator.
 * All third-party contributions are distributed under license by
 * Shanghai sinnren soft Co., Ltd.
 */
package com.weeds.aoi.auth.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.weeds.aoi.auth.user.domain.Area;
import com.weeds.aoi.auth.user.mapper.AreaMapper;
import com.weeds.aoi.auth.user.service.AreaService;

/**
 * @author sunxiaopeng
 * @date 2017年10月31日 下午5:29:14	
 */
@Service
@CacheConfig(cacheNames = "menu")
public class AreaServiceImpl implements AreaService{
	
	@Resource
	private AreaMapper areaMapper;
	
	@Override
	@Cacheable(key="'getAreaTree-' + #areaCode")
	public List<Map<String, String>> getAreaTree(String areaCode) {
		
		List<Area> list = areaMapper.selectSubAll(areaCode);
		List<Map<String, String>> areaList = new ArrayList<Map<String, String>>();
		
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", list.get(i).getAreaCode());
			map.put("pId", list.get(i).getpAreaCode());
			map.put("name", list.get(i).getAreaName());
			map.put("code", list.get(i).getAreaCode());
			map.put("open", "true");
			areaList.add(map);
		}	
		return areaList;
	}


	@Override
	public String getAreaNameByAreaCode(String areaCode) {
		Area area = areaMapper.selectAreaNameByAreaCode(areaCode);
		if (area!=null) {
			return area.getAreaName();
		}
		return null;
	}
}
