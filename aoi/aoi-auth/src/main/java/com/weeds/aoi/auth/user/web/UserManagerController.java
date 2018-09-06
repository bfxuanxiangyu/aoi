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
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.weeds.aoi.auth.log.OperateLog;
import com.weeds.aoi.auth.log.OperateType;
import com.weeds.aoi.auth.user.domain.Role;
import com.weeds.aoi.auth.user.domain.RolesUser;
import com.weeds.aoi.auth.user.domain.Users;
import com.weeds.aoi.auth.user.service.AreaService;
import com.weeds.aoi.auth.user.service.PoliceService;
import com.weeds.aoi.auth.user.service.RoleService;
import com.weeds.aoi.auth.user.service.UserService;
import com.weeds.aoi.auth.user.vo.UserParam;

/**
 * @author user
 * @date 2017年9月21日 下午5:47:40	
 */
@Controller
@RequestMapping("/admin/user")
public class UserManagerController {
	
	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private AreaService areaService;
	
	@Resource
	private PoliceService policeService;
	
	@RequestMapping("/add")
	public ModelAndView addUser(){
		ModelAndView mv = new ModelAndView();
		List<Role> roles = roleService.getRoleList();
		mv.addObject("roles", roles);
		mv.setViewName("user/adduser");
		return mv;
	}
	
	@RequestMapping("/save")
	@OperateLog(desc="添加用户", type=OperateType.ADD,moudle="新增用户",menu="adduser")
	public ModelAndView save(UserParam user){		 
		userService.saveUser(user);
		return userList();
	}
	
	@RequestMapping("/update")
	@OperateLog(desc="修改用户", type=OperateType.UPDATE,moudle="修改用户",menu="updateuser")
	public ModelAndView updateUser(UserParam user){
		userService.updateUser(user);
		return userList();
	}

	@RequestMapping("/edit")
	public ModelAndView editUser(@RequestParam(name="userName", required=true) String userName){
		ModelAndView view = new ModelAndView();
		Users user = userService.getUser(userName);
		List<RolesUser> roles=roleService.getRolesByUser(user.getUserId());
		String areaName = areaService.getAreaNameByAreaCode(user.getAreaCode());
		String policeName = policeService.getPoliceNamebyPoliceCode(user.getPolice());
		view.addObject("areaName",areaName);
		view.addObject("policeName",policeName);
		view.addObject("user", user);
		view.addObject("roles", roles);		
		view.setViewName("user/updateuser");
		return view;
	}
	@RequestMapping("/detailed")
	public ModelAndView userDetailed(@RequestParam(name="userName", required=true) String userName){
		ModelAndView view = new ModelAndView();
		Users user = userService.getUser(userName);
		List<RolesUser> roles=roleService.getRolesByUser(user.getUserId());
		view.addObject("user", user);
		view.addObject("roles", roles);		
		view.setViewName("user/userdetailed");
		return view;
	}
	
	@ResponseBody
	@RequestMapping("/checkUserName")
    public Map<String, Boolean> checkUserName(@RequestParam("user.userName") String userName) {      
		Users users = userService.getUser(userName);
		boolean result = false;
		if(users == null){
			result = true;
		}
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("valid", result);
        return map;
    }
	
    @RequestMapping("/delete")
	@OperateLog(desc="删除用户", type=OperateType.DELETE,moudle="删除用户",menu="deleteuser")
    public ModelAndView deleteUser(@Param("userName") String userName) {
    	int isSuc = 0;
    	isSuc = userService.delUserByUserName(userName);
		ModelAndView view = userList();
		view.addObject("isSuc", isSuc > 0);		
        return view;
    }
    
	
	@RequestMapping("/list")
	@OperateLog(desc="查询用户", type=OperateType.QUERY,moudle="用户管理",menu="usermanager")
	public ModelAndView userList (){
		ModelAndView view = new ModelAndView();
		List<Users> users = userService.getUserList();
		view.addObject("userList",users);
		view.setViewName("user/userlist");
		return view;
	}
	
	@ResponseBody
	@RequestMapping("/checkpwd")
	public Map<String,Boolean> checkpwd(@RequestParam("oldPassword") String oldPassword){
		Boolean isSuc= userService.checkOldPwd(oldPassword);
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		map.put("valid", isSuc);
		return map;
	} 
	
	@ResponseBody
	@RequestMapping("/changepwd")
	public Map<String,Boolean> changPwd(String oldPassword,String newPassword,String checkPassword){
		Boolean isSuc= userService.checkUpdateUserPwd(oldPassword, newPassword);
		Boolean result = false;
		if(isSuc==true){
			result	= userService.updateUserPwd(oldPassword, newPassword);
		}
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		map.put("isSuc", result);
		return map;
	} 
}
