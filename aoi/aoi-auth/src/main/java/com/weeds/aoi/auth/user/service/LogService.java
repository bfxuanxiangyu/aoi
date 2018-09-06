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
package com.weeds.aoi.auth.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.weeds.aoi.auth.log.OperateLog;
import com.weeds.aoi.auth.user.domain.Log;
import com.weeds.aoi.auth.user.vo.LogQueryParam;

/**
 * @author Jetory
 * @date 2017年9月22日 上午11:37:08	
 */
public interface LogService {

	void addLog(Log log);
	
	/**
	 * 实现定时任务每5分钟保存日志
	 */
	void pushLog();
	void pushLog(List<Log> list);
	
	void requestLogin(String userName, HttpServletRequest request, HttpServletResponse response, 
			long requestTime, long executeTime, Exception ex);
		
	void requestLogs(HttpServletRequest request, HttpServletResponse response, 
			long requestTime, long executeTime, OperateLog operate, Exception ex);
	
	PageInfo<Log> getLogs(LogQueryParam param);
	
}
