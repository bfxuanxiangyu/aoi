package com.weeds.aoi.arcface.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.weeds.aoi.arcface.domain.UserFaceInfo;


@Mapper
public interface UserFaceInfoMapper {

	List<UserFaceInfo> selectAll(Map<String, Object> parameters);
	
	UserFaceInfo getObject(Map<String, Object> parameters);
	
}
