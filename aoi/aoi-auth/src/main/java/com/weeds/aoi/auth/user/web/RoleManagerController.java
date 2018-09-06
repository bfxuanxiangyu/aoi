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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.weeds.aoi.auth.log.OperateLog;
import com.weeds.aoi.auth.log.OperateType;
import com.weeds.aoi.auth.user.domain.Role;
import com.weeds.aoi.auth.user.service.RoleService;

/**
 * @author sunxiaopeng
 * @date 2017年9月22日 下午4:29:59
 */
@Controller
@RequestMapping("/admin/role")
public class RoleManagerController {
	@Resource
	private RoleService roleService;

	@RequestMapping("/list")
	@OperateLog(desc="查询角色", type = OperateType.QUERY, moudle="角色管理", menu="rolemanager")
	public ModelAndView roleList() {
		ModelAndView view = new ModelAndView();
		List<Role> roles = roleService.getRoleList();
		view.addObject("roleList", roles);
		view.setViewName("role/rolelist");
		return view;
	}

	@RequestMapping("/add")
	public ModelAndView addRole() {
		ModelAndView view = new ModelAndView();
		view.setViewName("role/addrole");
		return view;
	}

	@RequestMapping("/save")
	@OperateLog(desc="新增角色", type = OperateType.ADD, moudle="新增角色", menu="addrole")
	public ModelAndView saveRole(Role role) {
		roleService.insertRole(role);
		return roleList();
	}

	@RequestMapping("/delete")
	@OperateLog(desc="删除角色", type = OperateType.DELETE, moudle="删除角色", menu="delrole")
	public ModelAndView deleteRole(@RequestParam(name = "roleId") int roleId) {
		int isSuc = 0;
		isSuc = roleService.delRole(roleId);
		ModelAndView view = roleList();
		view.addObject("isSuc", isSuc > 0);
		return view;
	}

	@ResponseBody
	@RequestMapping("/checkRoleName")
	public Map<String, Boolean> checkMenuName(@RequestParam("roleId") int roleId) {
		Role role = roleService.getRole(roleId);
		boolean result = false;
		if (role == null) {
			result = true;
		}
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("valid", result);
		return map;
	}

	@RequestMapping("/editrolemenu")
	@OperateLog(desc="编辑角色", type = OperateType.UPDATE, moudle="修改角色", menu="updaterole")
	public ModelAndView editRoleMenu(String[] menus, int roleId) {
		roleService.setRoleMenus(menus, roleId);
		return roleList();
	}

}
