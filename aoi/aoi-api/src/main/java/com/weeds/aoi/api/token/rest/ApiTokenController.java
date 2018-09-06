package com.weeds.aoi.api.token.rest;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weeds.aoi.api.token.domain.AccessToken;
import com.weeds.aoi.api.token.service.AccessTokenService;
import com.weeds.aoi.utils.AoiStringUtils;
import com.weeds.aoi.utils.AoiValidationUtils;

@Controller
@RequestMapping("/api/token")
public class ApiTokenController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiTokenController.class);
	@Resource
	private AccessTokenService accessTokenService;
	
	@ResponseBody
	@RequestMapping("/token")
	public String addUser(){
		AccessToken token = new AccessToken();
		try {
			String accessToken = AoiValidationUtils.getRandomString(16);
			String refreshToken = AoiValidationUtils.getRandomString(16);
			System.out.println(accessToken+"--"+refreshToken);
			token.setAccessToken(accessToken);
			token.setDeviceId("23");
			token.setCreatedTime(System.currentTimeMillis());
			token.setExpiresIn(System.currentTimeMillis());
			token.setPlatCode("1001");
			token.setRefreshToken(refreshToken);
			accessTokenService.insert(token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return AoiStringUtils.getJsonObj("success");
	}

}
