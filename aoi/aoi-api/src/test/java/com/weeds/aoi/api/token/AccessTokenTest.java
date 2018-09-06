package com.weeds.aoi.api.token;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import com.weeds.aoi.api.token.domain.AccessToken;
import com.weeds.aoi.api.token.mapper.AccessTokenMapper;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class AccessTokenTest {
	
	@Resource
	AccessTokenMapper tAccessTokenMapper;
	
	@Test
	public void testUsers(){
		AccessToken token = new AccessToken();
		/*OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(
				new MD5Generator());
		try {
			String accessToken = oauthIssuerImpl.accessToken();
			String refreshToken = oauthIssuerImpl.refreshToken();
			System.out.println(accessToken+"--"+refreshToken);
			
		} catch (OAuthSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
}
