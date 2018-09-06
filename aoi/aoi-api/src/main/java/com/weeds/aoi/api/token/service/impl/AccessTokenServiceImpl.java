package com.weeds.aoi.api.token.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.weeds.aoi.api.token.domain.AccessToken;
import com.weeds.aoi.api.token.mapper.AccessTokenMapper;
import com.weeds.aoi.api.token.service.AccessTokenService;

@Service
public class AccessTokenServiceImpl implements AccessTokenService{
	
	@Resource
	private AccessTokenMapper tAccessTokenMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return tAccessTokenMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AccessToken record) {
		return tAccessTokenMapper.insert(record);
	}

	@Override
	public AccessToken selectByPrimaryKey(Long id) {
		return tAccessTokenMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<AccessToken> selectAll(Map<String, Object> parameters) {
		return tAccessTokenMapper.selectAll(parameters);
	}

	@Override
	public int updateByPrimaryKey(AccessToken record) {
		return tAccessTokenMapper.updateByPrimaryKey(record);
	}

}
