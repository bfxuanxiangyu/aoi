package com.weeds.aoi.service.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.weeds.aoi.service.system.domain.ShortString;


@Mapper
public interface ShortStringMapper {

	List<ShortString> selectAll(Map<String, Object> parameters);
	
	ShortString getObject(Map<String, Object> parameters);
	
}
