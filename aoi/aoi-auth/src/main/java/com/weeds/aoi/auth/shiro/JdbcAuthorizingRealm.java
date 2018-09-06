/*
 * Copyright (C) 2017 Shanghai sinnren soft Co., Ltd
 *
 * All copyrights reserved by Shanghai sinnren.
 * Any copying, transferring or any other usage is prohibited.
 * Or else, Shanghai sinnren possesses the right to require legal 
 * responsibilities from the violator.
 * All third-party contributions are distributed under license by
 * Shanghai sinnren soft Co., Ltd.
 */
package com.weeds.aoi.auth.shiro;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.weeds.aoi.auth.user.domain.Menu;
import com.weeds.aoi.auth.user.domain.Role;
import com.weeds.aoi.auth.user.domain.Users;
import com.weeds.aoi.auth.user.service.MenuService;
import com.weeds.aoi.auth.user.service.UserService;

/**
 * @author Jetory
 * @date 2017年9月20日 下午1:52:50	
 */
public class JdbcAuthorizingRealm extends AuthorizingRealm {

	private final static Logger logger = LoggerFactory.getLogger(JdbcAuthorizingRealm.class);
	
	@Resource
	private UserService userService;
	
	@Resource
	private MenuService menuService;
	
	@Override
	protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
		Users user = (Users)principals.getPrimaryPrincipal();
        return user.getUserName() + ".authorization";
    }
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Users user = (Users) principals.getPrimaryPrincipal();
		
		if(user == null){
			return null;
		}
		
		boolean hasAdmin = false;

		List<Role> roleList = userService.getRole(user.getUserId());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		if(roleList != null){
			for(Role role : roleList){
				if(!StringUtils.isEmpty(role.getRoleName())){
					if("admin".equals(role.getRoleName())){
						hasAdmin = true;
					}
					info.addRole(role.getRoleName());
				}
			}
		}
		
		List<Menu> menuList = null;

		if(hasAdmin){
			menuList = menuService.getAllMenu();
		}else{
			menuList = userService.getMenu(user.getUserId());
		}
		
		if(menuList != null){
			for(Menu menu : menuList){
				if(!StringUtils.isEmpty(menu.getMenuName())){
					info.addStringPermission(menu.getMenuName());
				}
			}
		}
		
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken authcToken = (UsernamePasswordToken) token;
		
		if(StringUtils.isEmpty(authcToken.getPassword()) 
				|| StringUtils.isEmpty(authcToken.getUsername())){
			return null;
		}
		
		Users user = null;
		try{
			user = userService.getUser(authcToken.getUsername());
		}catch(Exception ex){
			logger.error("User login error !", ex);
		}
		if(user == null){
			throw new UnknownAccountException("用户不存在");
		}
		
		if(user.getStatus() == 0){
			throw new DisabledAccountException("用户已被禁用");
		}
		
		SimpleAuthenticationInfo info = 
				new SimpleAuthenticationInfo(user, 
						user.getUserPwd(), this.getName());
		return info;
        
	}

	@Override
	protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws AuthenticationException {
        CredentialsMatcher cm = getCredentialsMatcher();
        if (cm != null) {
            if (!cm.doCredentialsMatch(token, info)) {
                throw new IncorrectCredentialsException("密码错误");
            }
        } else {
            throw new AuthenticationException("认证异常");
        }
    }
	
	@Override
	protected Object getAuthenticationCacheKey(AuthenticationToken token) {
		UsernamePasswordToken authcToken = (UsernamePasswordToken) token;
		return authcToken.getUsername() + ".authentication";
	}
	
	@Override
	protected Object getAuthenticationCacheKey(PrincipalCollection principals) {
		Users user = (Users) principals.getPrimaryPrincipal();
		return user.getUserName() + ".authentication";
	}
	
}
