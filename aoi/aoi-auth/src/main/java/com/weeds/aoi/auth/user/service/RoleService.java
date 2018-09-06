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

import com.weeds.aoi.auth.user.domain.Role;
import com.weeds.aoi.auth.user.domain.RolesUser;

/**
 * @author sunxiaopeng
 * @date 2017年9月22日 下午4:32:55	
 */
public interface RoleService {	

	
	/**
	 * 获取角色列表
	 * @param 
	 * @return
	 */
	List<Role> getRoleList();
	

	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	int insertRole(Role role);

	/**
	 * 通过roleId获取角色
	 * @param roleId
	 * @return
	 */
	Role getRole(Integer roleId);
	
	/**
	 * 删除角色
	 * @param roleName
	 * @return
	 */
	int delRole(int roleId);
	
	/**
	 * 通过用户id查找该用户所有角色
	 * @param userId
	 * @return
	 */
	List<RolesUser> getRolesByUser(Integer userId);
	
	
	/**
	 * 更新角色菜单
	 * @param menus
	 * @return
	 */
	int setRoleMenus(String[] menus, int roleId);
	
	
	/**
	 * 获取一个角色的菜单树
	 * @param roleName
	 * @return
	 */
	List<Map<String, String>> getRoleMenuTree(int roleId);

	
}
