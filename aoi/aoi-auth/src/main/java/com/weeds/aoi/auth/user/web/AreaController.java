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

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.weeds.aoi.auth.user.service.AreaService;


/**
 * @author sunxiaopeng
 * @date 2017年10月31日 下午5:24:10	
 */
@Controller
@RequestMapping("/admin/area")
public class AreaController {
	
	@Value("${app.area.code}")
	private String areaCode;
	@Resource
	private AreaService areaService;
	@RequestMapping(value = "/gettree")
	public @ResponseBody List<Map<String, String>> getAreaTree() {
		return areaService.getAreaTree(areaCode);
	}


}
