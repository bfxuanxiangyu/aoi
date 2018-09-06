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
package com.weeds.aoi.auth.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.weeds.aoi.auth.log.OperateLog;
import com.weeds.aoi.auth.user.service.LogService;

/**
 * @author Jetory
 * @date 2017年9月20日 下午2:07:51
 */
@Component
public class LogHandlerInterceptor extends WebMvcConfigurerAdapter implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(LogHandlerInterceptor.class);
	
	private long requestTime;

	@Resource
	private LogService logService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		requestTime = System.currentTimeMillis();
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		String nolog = request.getParameter("nolog");
		if("true".equals(nolog)){
			return;
		}
		
		if(!(handler instanceof HandlerMethod)){
			return;
		}
		
		HandlerMethod hm = (HandlerMethod) handler;
		
		
		OperateLog log = hm.getMethodAnnotation(OperateLog.class);
		if(log == null){
			if(logger.isTraceEnabled()){
				logger.trace("Igonre operate log " + request.getRequestURI());
			}
			return;
		}
		
		this.logService.requestLogs(request, response, requestTime, (System.currentTimeMillis() - requestTime), log, ex);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this).addPathPatterns("/**");
	}

}
