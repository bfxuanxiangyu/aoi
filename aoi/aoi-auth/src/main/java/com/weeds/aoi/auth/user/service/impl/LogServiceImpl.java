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
package com.weeds.aoi.auth.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weeds.aoi.auth.log.OperateLog;
import com.weeds.aoi.auth.log.OperateType;
import com.weeds.aoi.auth.user.domain.Log;
import com.weeds.aoi.auth.user.domain.Users;
import com.weeds.aoi.auth.user.mapper.LogMapper;
import com.weeds.aoi.auth.user.mapper.LogQuery;
import com.weeds.aoi.auth.user.service.LogService;
import com.weeds.aoi.auth.user.vo.LogQueryParam;

/**
 * @author Jetory
 * @date 2017年9月22日 上午11:39:04	
 */
@Service
public class LogServiceImpl implements LogService {

	private static Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	@Value("#{ @environment['shiro.loginUrl'] ?: '/login' }")
	private String loginUrl;
	
	@Resource
	private LogMapper logMapper;
	
	private LinkedBlockingQueue<Log> logQueue = new LinkedBlockingQueue<Log>(); 
	
	@Override
	public void addLog(Log log) {
		boolean result = logQueue.add(log);
		if(!result){
			logger.warn(log.toString());
		}
	}

	@Scheduled(cron = "0 */1 *  * * * ")
	@Override
	public void pushLog() {
		Log log;
		List<Log> list = new ArrayList<Log>(1000);
		while((log = logQueue.poll()) != null){
			list.add(log);
			if(list.size() == 1000){
				this.pushLog(list);
			}
		}
		
		this.pushLog(list);
		
	}
	
	@Override
	public void pushLog(List<Log> list){
		if(!list.isEmpty()){
			logMapper.batchInsert(list);
			if(logger.isDebugEnabled()){
				logger.debug("保存操作日志{}条", list.size());
			}
			list.clear();
		}
	}

	public void requestLogin(String userName, HttpServletRequest request, HttpServletResponse response, 
			long requestTime, long executeTime, Exception ex){
		
		String requrl = request.getRequestURI();

		Log log = new Log();
		log.setClientIp(this.getIpAddress(request));
		log.setCreater(userName);
		log.setRequestMethod(request.getMethod());
		
		log.setDescription("用户登录");
		log.setMenuName("login");
		log.setMoudleName("系统管理");
		log.setOperateType(OperateType.LOGIN.getType());
		
		if(ex != null){
			log.setErrorMessage(ex.getMessage());
		}
		
		log.setHttpCode(response.getStatus());
		log.setRequestUrl(requrl);

		log.setUserAgent(request.getHeader("User-Agent"));
		log.setRequestTime(new Date(requestTime));
		log.setExecuteTime((int) (executeTime));

		if(logger.isDebugEnabled()){
			logger.debug("login: " + log.toString());
		}
		
		this.addLog(log);
		
	}
	
	@Override
	public void requestLogs(HttpServletRequest request, HttpServletResponse response, 
			long requestTime, long executeTime, OperateLog operate, Exception ex){

		if(operate == null){
			return;
		}
		
		String requrl = request.getRequestURI();
		if(requrl.equals(loginUrl)){
			return;
		}
		
		Subject subject = SecurityUtils.getSubject();

		Users user = (Users) subject.getPrincipal();
		if (user == null) {
			return;
		}

		if("admin".equals(user.getUserName())){
			return;
		}
		
		Log log = new Log();
		log.setClientIp(this.getIpAddress(request));
		log.setCreater(user.getUserName());
		log.setRequestMethod(request.getMethod());
		
		log.setDescription(operate.desc());
		log.setMenuName(operate.menu());
		log.setMoudleName(operate.moudle());
		log.setOperateType(operate.type().getType());
		
		if(ex != null){
			log.setErrorMessage(ex.getMessage());
		}
		
		try {
			if(operate.param()){
				log.setRequestParam(mapper.writeValueAsString(request.getParameterMap()));
			}
		} catch (JsonProcessingException e) {
			logger.error("日志记录出错!", e);
		}
		
		log.setHttpCode(response.getStatus());
		log.setRequestUrl(requrl);

		log.setUserAgent(request.getHeader("User-Agent"));
		log.setRequestTime(new Date(requestTime));
		log.setExecuteTime((int) (executeTime));

		if(logger.isDebugEnabled()){
			logger.debug("logs : " + log.toString());
		}
		
		this.addLog(log);
	}
	
	private String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("http_client_ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		}
		if ("0:0:0:0:0:0:0:1".equals(ip)) {
			ip = "127.0.0.1";
		}
		return ip;
	}

	@Override
	public PageInfo<Log> getLogs(LogQueryParam param) {
		
		LogQuery example = new LogQuery();
		
		LogQuery.LogCriteria criteria1 = example.or();

		criteria1.andRequestTimeBetween(param.getStartTime(), param.getEndTime());
		criteria1.andOperateTypeEqualTo(param.getOperatorType());
		
		if(param.getDescription() != null){
			criteria1.andDescriptionLike("%"+param.getDescription()+"%");
		}
		example.setOrderByClause("REQUEST_TIME DESC");
		PageHelper.offsetPage(param.getStart(), param.getLength());
		List<Log> list = logMapper.selectByExample(example);
		
		PageInfo<Log> page = new PageInfo<Log>(list);
		
		return page;
	
	}




}
