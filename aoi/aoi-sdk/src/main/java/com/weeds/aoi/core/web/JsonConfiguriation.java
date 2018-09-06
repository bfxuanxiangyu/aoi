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
package com.weeds.aoi.core.web;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Jetory
 * @date 2017年10月30日 下午4:03:05	
 */
@Component
@PropertySource(value={"classpath:web.properties", "./web.properties"}, ignoreResourceNotFound = true, encoding="UTF-8")
@ConfigurationProperties(prefix="app")
public class JsonConfiguriation implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -378654465429123861L;

	/**
	 * 前端采集地址
	 */
	@Value("${app.collect.host}")
	private String collectHost;

	public String getCollectHost() {
		return collectHost;
	}

	public void setCollectHost(String collectHost) {
		this.collectHost = collectHost;
	}
	
}
