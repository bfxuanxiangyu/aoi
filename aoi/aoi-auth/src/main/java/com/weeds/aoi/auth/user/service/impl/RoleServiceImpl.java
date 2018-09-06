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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.weeds.aoi.auth.user.domain.Menu;
import com.weeds.aoi.auth.user.domain.Role;
import com.weeds.aoi.auth.user.domain.RoleMenus;
import com.weeds.aoi.auth.user.domain.RolesUser;
import com.weeds.aoi.auth.user.domain.Users;
import com.weeds.aoi.auth.user.mapper.MenuMapper;
import com.weeds.aoi.auth.user.mapper.RoleMapper;
import com.weeds.aoi.auth.user.mapper.RoleMenusMapper;
import com.weeds.aoi.auth.user.mapper.UserRolesMapper;
import com.weeds.aoi.auth.user.mapper.UsersMapper;
import com.weeds.aoi.auth.user.service.RoleService;
import com.weeds.aoi.auth.utils.PinyinUtils;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author sunxiaopeng
 * @date 2017年9月22日 下午4:34:15
 */
@Service
public class RoleServiceImpl implements RoleService {

	private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Resource
	private RoleMapper roleMapper;

	@Resource
	private UsersMapper usersMapper;

	@Resource
	private UserRolesMapper userRolesMapper;

	@Resource
	private MenuMapper menuMapper;

	@Resource
	private RoleMenusMapper roleMenusMapper;

	private PinyinUtils pinyin = new PinyinUtils();
	
	@Override
	public List<Role> getRoleList() {
		return roleMapper.selectAll();
	}

	@Override
	public int insertRole(Role role) {
		Users u = (Users) SecurityUtils.getSubject().getPrincipal();
		role.setCreator(u.getUserName());
		try {
			role.setRoleName(pinyin.toPinYin(role.getRoleTitle()));
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			logger.warn("数据转换错误!", e);
		}
		
		return roleMapper.insert(role);
	}

	@Override
	public Role getRole(Integer roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}


	@Override
	public int delRole(int roleId) {
		roleMenusMapper.deleteByRoleId(roleId);
		userRolesMapper.deleteByRoleId(roleId);
		return roleMapper.deleteByPrimaryKey(roleId);
	}

	@Override
	public List<RolesUser> getRolesByUser(Integer userId) {
		return roleMapper.selectRoleUsersByUserId(userId);
	}

	
	@Override
	public int setRoleMenus(String[] menus, int roleId) {
		int isSuc = 0;
		Role role = roleMapper.selectByPrimaryKey(roleId);
		roleMenusMapper.deleteByRoleId(role.getRoleId());
		if (menus != null) {
			for (String menu : menus) {
				Integer menuId = menuMapper.selectByMenuName(menu).getMenuId();
				RoleMenus roleMenus = new RoleMenus();
				roleMenus.setMenuId(menuId);
				roleMenus.setRoleId(role.getRoleId());
				isSuc=roleMenusMapper.insert(roleMenus);
			}
		}
		return isSuc;
	}


	@Override
	public List<Map<String, String>> getRoleMenuTree(int roleId) {
		
		List<Menu> list = menuMapper.selectAll();
		List<Menu> roleMenus = menuMapper.selectMenusByRoleId(roleId);
		List<Map<String, String>> moduleList = new ArrayList<Map<String, String>>();
		for (int i = 0; i < list.size(); i++) {
			
			Menu menu = list.get(i);
			if(menu == null){
				continue;
			}
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", menu.getMenuName());
			map.put("pId", menu.getParentName());
			map.put("name", menu.getMenuTitle());
			map.put("open", "true");
			
			if(menu.getStatus() == 0){
				map.put("chkDisabled", "true");
			}else{
				
				map.put("checked", "false");
				for (int j = 0; j < roleMenus.size(); j++) {			
					if (menu.getMenuName().equals(roleMenus.get(j).getMenuName())) {
						map.put("checked", "true");
						break;
					}
				}
				
			}
			
			moduleList.add(map);
		}		
		
		return moduleList;
		
	}

}
