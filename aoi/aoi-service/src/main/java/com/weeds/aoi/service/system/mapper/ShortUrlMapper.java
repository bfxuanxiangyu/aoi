package com.weeds.aoi.service.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.weeds.aoi.service.system.domain.ShortUrl;


@Mapper
public interface ShortUrlMapper {

	List<ShortUrl> selectAll(Map<String, Object> parameters);
	
	ShortUrl getObject(Map<String, Object> parameters);
	
}
