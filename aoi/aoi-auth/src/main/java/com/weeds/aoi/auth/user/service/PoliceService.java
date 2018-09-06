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
import java.util.Map;

/**
 * @author sunxiaopeng
 * @date 2017年10月31日 下午5:28:44	
 */
public interface PoliceService {

	/**
	 * 获得Police树
	 * @return
	 */
	List<Map<String, String>> getPoliceTree(String areaName);
	
	/**
	 * 获得派出所名字通过派出所编号
	 * @param policeCode
	 * @return
	 */
	String getPoliceNamebyPoliceCode(String policeCode);
}
