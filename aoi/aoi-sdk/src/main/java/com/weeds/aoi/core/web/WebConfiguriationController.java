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

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Jetory
 * @date 2017年10月30日 下午3:58:07	
 */
@Controller
public final class WebConfiguriationController {

	private ObjectMapper mapper = new ObjectMapper();  
	
	@Autowired
	protected JsonConfiguriation jsonConfiguriation;
	
	@RequestMapping("/config.js")
	public void configuraction(HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		response.setContentType("application/javascript;charset=UTF-8"); 
		PrintWriter writer = response.getWriter();
		writer.write("var appContext =");
		mapper.writeValue(writer, jsonConfiguriation);
		writer.write(";\n");
		writer.flush();
	}
	
}
