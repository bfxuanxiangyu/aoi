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
package com.weeds.aoi.auth.user.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.weeds.aoi.core.web.DataTablesParams;

/**
 * @author Jetory
 * @date 2017年10月18日 下午3:34:42	
 */
public class LogQueryParam extends DataTablesParams{

	private static final long serialVersionUID = 8910076059891771782L;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	
	private Integer operatorType;
	
	private String description;

	/**
	 * startTime.
	 * @return  the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param   startTime  the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * endTime.
	 * @return  the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param   endTime  the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * description.
	 * @return  the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param   description  the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * operatorType.
	 * @return  the operatorType
	 */
	public Integer getOperatorType() {
		return operatorType;
	}

	/**
	 * @param   operatorType  the operatorType to set
	 */
	public void setOperatorType(Integer operatorType) {
		this.operatorType = operatorType;
	}

	
}
