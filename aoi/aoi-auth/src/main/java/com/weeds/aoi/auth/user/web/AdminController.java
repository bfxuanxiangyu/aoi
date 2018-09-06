/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.weeds.aoi.auth.user.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.weeds.aoi.auth.user.domain.Users;


@Controller
@RequestMapping("/")
public class AdminController {

	@Value("#{ @environment['shiro.successUrl'] ?: '/main' }")
	protected String successUrl;
	
	@RequestMapping("/login")
	public String login() {
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			Users user = (Users) subject.getPrincipal();
			if(user == null){
				return "login";
			}
			String successAction = successUrl;
			if(!StringUtils.isEmpty(user.getAction())){
				successAction = user.getAction();
			}
			return "redirect:" + successAction;
		}else{
			return "login";
		}
	}
	
	@RequestMapping("/logout")
    public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "login";
    }

	/**
	 * 重定向到指定页面
	 * @return
	 */
	/*@RequestMapping("/")
	public String root() {
		return "redirect:main";
	}*/
	@RequestMapping("")
	public ModelAndView webmain(){
		ModelAndView view = new ModelAndView();
		view.setViewName("main");
		return view;
	}

	@RequestMapping("/admin")
    public String index() {
		return "admin";
    }
	
	/*@RequestMapping("/main")
    public String main() {
		return "main";
    }*/
	
}
