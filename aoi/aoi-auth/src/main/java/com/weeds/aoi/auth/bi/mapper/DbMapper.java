package com.weeds.aoi.auth.bi.mapper;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DbMapper {

	
	List<LinkedHashMap<String, Object>> resultSet(String sql);
	
	void execute(String sql);
	
}
