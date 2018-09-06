package com.weeds.aoi.api.token.mapper;

import com.weeds.aoi.api.token.domain.AccessToken;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccessTokenMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AccessToken record);

    AccessToken selectByPrimaryKey(Long id);
    
    AccessToken selectAccessToken(Map<String, Object> parameters);

    List<AccessToken> selectAll(Map<String, Object> parameters);

    int updateByPrimaryKey(AccessToken record);
}