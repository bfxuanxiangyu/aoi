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

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import com.weeds.aoi.auth.user.domain.Users;
import com.weeds.aoi.auth.user.service.LogService;
import com.weeds.aoi.auth.user.service.UserService;

/**
 * @author Jetory
 * @date 2017年9月21日 下午2:59:16	
 */
public class UserFormAuthenticationFilter extends FormAuthenticationFilter {

	@Resource
	private LogService logService;
	
	@Resource
	private UserService userService;
	
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		
		Users user = (Users) subject.getPrincipal();
		String successUrl = super.getSuccessUrl();
		
		long time = 0;
		if(user.getLoginLast() != null){
			if(!StringUtils.isEmpty(user.getAction())){
				successUrl = user.getAction();
			}
			time = user.getLoginLast().getTime();
		}else{
			//跳转到修改默认密码页面
			time = System.currentTimeMillis();
		}

		userService.updateUserLastTime(user);
		logService.requestLogin(user.getUserName(), (HttpServletRequest)request, (HttpServletResponse)response, 
				time, System.currentTimeMillis() - time, null);
		
		WebUtils.redirectToSavedRequest(request, response, successUrl);
		
		return false;
	}
	
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		
		logService.requestLogin(request.getParameter("username"), (HttpServletRequest)request, (HttpServletResponse)response, 
				System.currentTimeMillis(), 0, e);
		
		return super.onLoginFailure(token, e, request, response);
	}
	
	
	@Override
	protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
		request.setAttribute(getFailureKeyAttribute(), ae.getMessage());
	}
	
}
