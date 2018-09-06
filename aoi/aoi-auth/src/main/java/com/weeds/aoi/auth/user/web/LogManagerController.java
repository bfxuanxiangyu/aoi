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
package com.weeds.aoi.auth.user.web;

import java.util.Date;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.weeds.aoi.auth.log.OperateLog;
import com.weeds.aoi.auth.log.OperateType;
import com.weeds.aoi.auth.user.domain.Log;
import com.weeds.aoi.auth.user.service.LogService;
import com.weeds.aoi.auth.user.vo.LogQueryParam;
import com.weeds.aoi.core.web.DataTablesResult;

/**
 * @author sunxiaopeng
 * @date 2017年10月16日 下午4:32:08	
 */
@Controller
@RequestMapping("/admin/log")
public class LogManagerController {
	
	@Resource
	private LogService logService;	
	
	@RequestMapping("/list")
	@OperateLog(desc="查询日志", type = OperateType.QUERY, moudle="日志管理", menu="menumanager")
	public ModelAndView userList (){
		ModelAndView view = new ModelAndView();
		view.setViewName("log/loglist");
		Date startTime = DateTime.now().minusMonths(1).toDate();
		Date endTime = new Date();
		view.addObject("startTime", startTime);
		view.addObject("endTime", endTime);
		return view;
	}
	
	@ResponseBody
	@RequestMapping("/datas")
	public DataTablesResult<Log> dataList(LogQueryParam params){
		PageInfo<Log> page = logService.getLogs(params);
		DataTablesResult<Log> result = new DataTablesResult<Log>();
		result.setData(page.getList());
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		return result;
	}
	
}
