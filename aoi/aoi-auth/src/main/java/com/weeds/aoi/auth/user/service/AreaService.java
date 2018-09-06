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
public interface AreaService {

	/**
	 * 获得Area树
	 * @return
	 */
	List<Map<String, String>> getAreaTree(String areaCode);
	
	/**
	 * 获得区域名
	 * @param areaCode
	 * @return
	 */
	String getAreaNameByAreaCode(String areaCode);
}
