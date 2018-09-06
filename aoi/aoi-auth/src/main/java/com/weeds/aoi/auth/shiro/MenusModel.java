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
package com.weeds.aoi.auth.shiro;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.weeds.aoi.auth.user.domain.Menu;
import com.weeds.aoi.auth.user.domain.Users;
import com.weeds.aoi.auth.user.service.MenuService;

/**
 * @author Jetory
 * @date 2017年10月13日 下午1:33:16	
 */
public class MenusModel {

	@Resource
	private MenuService menuService;
	
	public List<Menu> children(String menuName){
		if(menuName == null){
			return Collections.emptyList();
		}
		
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			Users user = (Users) subject.getPrincipal();
			return menuService.getRoleSubmenu(user.getUserName(), menuName);
		}
		
		return null;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
}
