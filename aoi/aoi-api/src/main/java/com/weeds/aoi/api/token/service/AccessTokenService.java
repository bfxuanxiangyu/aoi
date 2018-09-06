package com.weeds.aoi.api.token.service;

import java.util.List;
import java.util.Map;

import com.weeds.aoi.api.token.domain.AccessToken;

public interface AccessTokenService {
	
	int deleteByPrimaryKey(Long id);

    int insert(AccessToken record);

    AccessToken selectByPrimaryKey(Long id);

    List<AccessToken> selectAll(Map<String, Object> parameters);

    int updateByPrimaryKey(AccessToken record);

}
