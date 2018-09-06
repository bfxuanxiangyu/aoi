package com.weeds.aoi.auth.bi.service;

import java.util.List;

import com.weeds.aoi.auth.bi.domain.TableVo;

public interface DbService {
	
	List<TableVo> getTables(String sql);
	
	List<String[]> resultSet(String sql);
	
	void execute(String sql);
	
}
