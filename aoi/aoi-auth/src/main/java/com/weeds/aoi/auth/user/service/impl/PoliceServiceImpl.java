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

import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.weeds.aoi.auth.user.domain.Police;
import com.weeds.aoi.auth.user.mapper.AreaMapper;
import com.weeds.aoi.auth.user.mapper.PoliceMapper;
import com.weeds.aoi.auth.user.service.PoliceService;

/**
 * @author sunxiaopeng
 * @date 2017年10月31日 下午7:51:52
 */
@Service
@CacheConfig(cacheNames = "menu")
public class PoliceServiceImpl implements PoliceService {

	@Resource
	private PoliceMapper policeMapper;
	@Resource
	private AreaMapper areaMapper;

	@Override
//	@Cacheable(key="'getPoliceTree-' + #areaCode")
	public List<Map<String, String>> getPoliceTree(String areaCode) {
		List<Police> list=null;
		if (StringUtils.isEmpty(areaCode)) {
			list = policeMapper.selectAll();
		}else {
			list = policeMapper.selectAllByAreaCode(areaCode);
		}
		List<Police> subList = new ArrayList<Police>();
		getPoliceList(subList,list);
		subList.addAll(list);
		List<Map<String, String>> policeList = new ArrayList<Map<String, String>>();
		
		for (int i = 0; i < subList.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", subList.get(i).getPoliceCode());
			map.put("pId", subList.get(i).getpPoliceCode());
			map.put("name", subList.get(i).getPoliceName());
			map.put("code", subList.get(i).getPoliceCode());
			map.put("open", "false");
			policeList.add(map);
		}
		return policeList;
	}
	public List<Police> getPoliceList(List<Police> sublist,List<Police> list) {
		for(Police police:list){
			if(list!=null){
				List<Police> policeCode = policeMapper.selectAllByPPoliceCode(police.getPoliceCode());
				getPoliceList(sublist, policeCode);
				sublist.addAll(policeCode);
			}
		}
		return sublist;
	}
	@Override
	public String getPoliceNamebyPoliceCode(String policeCode) {
		Police police = policeMapper.selectPoliceByPoliceCode(policeCode);
		if (police!=null) {
			return police.getPoliceName();
		}
		return null;
	}

}
