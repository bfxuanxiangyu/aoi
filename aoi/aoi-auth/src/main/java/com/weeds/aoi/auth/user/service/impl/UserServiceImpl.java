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
package com.weeds.aoi.auth.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.weeds.aoi.auth.user.domain.Menu;
import com.weeds.aoi.auth.user.domain.Role;
import com.weeds.aoi.auth.user.domain.UserRoles;
import com.weeds.aoi.auth.user.domain.Users;
import com.weeds.aoi.auth.user.mapper.AreaMapper;
import com.weeds.aoi.auth.user.mapper.LogMapper;
import com.weeds.aoi.auth.user.mapper.MenuMapper;
import com.weeds.aoi.auth.user.mapper.PoliceMapper;
import com.weeds.aoi.auth.user.mapper.RoleMapper;
import com.weeds.aoi.auth.user.mapper.RoleMenusMapper;
import com.weeds.aoi.auth.user.mapper.UserRolesMapper;
import com.weeds.aoi.auth.user.mapper.UsersMapper;
import com.weeds.aoi.auth.user.service.UserService;
import com.weeds.aoi.auth.user.vo.UserParam;;

/**
 * @author Jetory
 * @date 2017年9月21日 下午5:40:55
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource
	private UsersMapper usersMapper;

	@Resource
	private UserRolesMapper userRolesMapper;

	@Resource
	private LogMapper logMapper;

	@Resource
	private PasswordMatcher passwordMatcher;

	@Resource
	private RoleMapper roleMapper;

	@Resource
	private RoleMenusMapper roleMenusMapper;

	@Resource
	private MenuMapper menuMapper;
	
	@Resource
	private AreaMapper areaMapper;
	
	@Resource
	private PoliceMapper policeMapper;

	@Override
	public List<Role> getRole(Integer userId) {
		return roleMapper.selectRolesByUserId(userId);
	}

	@Override
	public List<Menu> getMenu(Integer userId) {
		return menuMapper.selectMenusByUserId(userId);
	}

	@Override
	public Users getUser(String userName) {
		Users users = usersMapper.selectByUserName(userName);
		if (users == null) {
			return null;
		}
		return users;
	}

	@Override
	public void updateUserLastTime(Users user) {
		Users users = new Users();
		users.setUserId(user.getUserId());
		users.setUserName(user.getUserName());
		users.setLoginLast(new Date());
		if (user.getLoginTotal() == null) {
			users.setLoginTotal(1);
		} else {
			users.setLoginTotal(user.getLoginTotal() + 1);
		}
		usersMapper.updateUserLastByPrimaryKey(users);
	}

	@Override
	public List<Users> getUserList() {
		return usersMapper.selectAll();
	}

	@Override
	public int delUserByUserName(String userName) {
		int isSuc = 0;
		if (StringUtils.isNotEmpty(userName)) {
			Users user = usersMapper.selectByUserName(userName);
			if (user == null || user.getUserType() == 1) {
				return 0;
			}
			userRolesMapper.deleteByUserId(user.getUserId());
			isSuc = usersMapper.deleteByPrimaryKey(user.getUserId());
		}

		return isSuc;
	}

	@Override
	public boolean saveUser(UserParam user) {		
		Users u = (Users) SecurityUtils.getSubject().getPrincipal();
			
		boolean isSuc = false;
		if (user != null) {
			
			Users users = user.getUser();
			
			users.setCreator(u.getUserName());
			users.setUserPwd(passwordMatcher.getPasswordService().encryptPassword(users.getUserPwd()));
			users.setUpdateTime(new Date());
			users.setUserType(2);

			logger.info("保存用户" + users.toString());

			usersMapper.insert(users);
			this.saveUserRole(user.getRoelIds(), users);
			
			isSuc = true;
		}
		
		return isSuc;
	}

	@Override
	public void updateUser(UserParam userParam) {
		Users users = userParam.getUser();
		
		userRolesMapper.deleteByUserId(users.getUserId());
		
		users.setUserPwd(usersMapper.selectByPrimaryKey(users.getUserId()).getUserPwd());
		usersMapper.updateByPrimaryKey(users);
		this.saveUserRole(userParam.getRoelIds(), users);
		
	}

	private void saveUserRole(Integer[] roelIds, Users users) {
		List<UserRoles> roles = new ArrayList<UserRoles>(roelIds.length);
		if (roelIds != null) {
			for (Integer roleId : roelIds) {
				roles.add(new UserRoles(roleId, users.getUserId()));
			}
			userRolesMapper.batchInsert(roles);
		}
	}

	@Override
	public boolean isAdmin() {
		Subject subject = SecurityUtils.getSubject();
		return subject.hasRole("admin");
	}

	@Override
	public boolean checkUpdateUserPwd(String oldPassword, String newPassword) {
		
		Users u = (Users) SecurityUtils.getSubject().getPrincipal();
		
		PasswordService passwordService = passwordMatcher.getPasswordService();

        boolean result = passwordService.passwordsMatch(oldPassword, u.getUserPwd());
		
        return result;
        
	}
	
	@Override
	public boolean updateUserPwd(String oldPassword, String newPassword) {
		
		Users u = (Users) SecurityUtils.getSubject().getPrincipal();
		
		PasswordService passwordService = passwordMatcher.getPasswordService();

        boolean result = passwordService.passwordsMatch(oldPassword, u.getUserPwd());
        
		String password = passwordMatcher.getPasswordService().encryptPassword(newPassword);
		
		u.setUserPwd(password);
		
        usersMapper.updateByPrimaryKey(u);
        
        return result;
        
	}

	@Override
	public boolean checkOldPwd(String oldPassword) {
		Users u = (Users) SecurityUtils.getSubject().getPrincipal();
		PasswordService passwordService = passwordMatcher.getPasswordService();
        boolean result = passwordService.passwordsMatch(oldPassword, u.getUserPwd());
		return result;
	}
	
}