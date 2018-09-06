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

import com.weeds.aoi.auth.user.domain.Menu;
import com.weeds.aoi.auth.user.domain.Role;
import com.weeds.aoi.auth.user.domain.Users;
import com.weeds.aoi.auth.user.vo.UserParam;

/**
 * @author Jetory
 * @date 2017年9月21日 下午5:18:14	
 */
public interface UserService {

	/**
	 * 通过userID获取角色
	 * @param userId
	 * @return
	 */
	List<Role> getRole(Integer userId);
	
	/**
	 * 通过userID获取菜单
	 * @param userId
	 * @return
	 */
	List<Menu> getMenu(Integer userId);
	
	/**
	 * 通过用户名获取用户信息
	 * @param userName
	 * @return
	 */
	Users getUser(String userName);
	
	/**
	 * 更新用户最后登录时间和次数
	 * @param user
	 */
	void updateUserLastTime(Users user);


	/**
	 * 更新用户
	 * @param user
	 */
	void updateUser(UserParam userParam);
	
	/**
	 * 获取用户列表
	 * @param 
	 * @return
	 */
	List<Users> getUserList();
	
	/**
	 * 删除用户通过用户名
	 * @param 用户名
	 * @return
	 */
	int delUserByUserName(String userName);
	
	/**
	 * 保存用户
	 * @param userParam
	 * @return
	 */
	boolean saveUser(UserParam userParam);
	
	/**
	 * 是否是管理员
	 * @return
	 */
	boolean isAdmin();
	
	boolean checkOldPwd(String oldPassword);
	
	boolean updateUserPwd(String oldPassword, String newPassword);

	boolean checkUpdateUserPwd(String oldPassword, String newPassword);
	
	
}
