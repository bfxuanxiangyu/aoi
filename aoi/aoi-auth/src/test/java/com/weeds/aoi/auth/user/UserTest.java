package com.weeds.aoi.auth.user;
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


import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import com.weeds.aoi.auth.user.mapper.MenuMapper;
import com.weeds.aoi.auth.user.mapper.RoleMapper;
import com.weeds.aoi.auth.user.mapper.RoleMenusMapper;
import com.weeds.aoi.auth.user.mapper.UserRolesMapper;
import com.weeds.aoi.auth.user.mapper.UsersMapper;

/**
 * @author Jetory
 * @date 2017年9月19日 下午2:02:36	
 */
@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class UserTest {

	@Resource
	private UsersMapper usersMapper;
	@Resource
	private RoleMapper roleMapper;
	@Resource
	private MenuMapper menuMapper;
	@Resource
	private UserRolesMapper userRolesMapper;
	@Resource
	private RoleMenusMapper roleMenusMapper;
	
//	@Test
//	public void testUsers(){
//		Users users=new Users();
//		users.setUserId(1);
//		users.setUserName("mrzhang");
//		users.setUserPwd("123456");
//		users.setPhone("13788888888");
//		users.setEmail("sinner@sin.com");
//		users.setName("sinner");
//		users.setPoliceCode("p123456");
//		users.setIdcard("123456789");
//		users.setPolice("普陀");
//		users.setAction("www.test.com");
//		usersMapper.insert(users);
//
//		Role role=new Role();
//		role.setRoleId(1);
//		role.setRoleName("管理员");
//		role.setRoleTitle("sinner");
//		role.setCreateTime(new Date());
//		roleMapper.insert(role);
//		
//		Menu menu=new Menu();
//		menu.setMenuId(1);
//		menu.setMenuName("menu");
//		menu.setParentName("sinnren");
//		menu.setAction("www");
//		menu.setOderBy(1);
//		menu.setStatus(1);
//		menu.setArea("普陀");
//		menu.setIcon("icon");
//		menu.setCreateTime(new Date());
//		menuMapper.insert(menu);
//		
//		UserRoles userRole=new UserRoles();
//		
//		userRole.setId(1);
//		userRole.setRoleId(1);
//		userRole.setUserId(1);
//		userRolesMapper.insert(userRole);
//
//		
//		RoleMenus roleMenus=new RoleMenus();		
//		roleMenus.setId(1);
//		roleMenus.setMenuId(1);
//		roleMenus.setRoleId(1);
//		roleMenusMapper.insert(roleMenus);
//		
//		
//	}
//	
//	@Test
//	public void testBatchInsert(){
//		for (int i = 2; i < 10; i++) {
//			Users users=new Users();
//			users.setUserId(i);
//			users.setUserName("mrzhang"+i);
//			users.setUserPwd("123456");
//			users.setPhone("13788888888");
//			users.setEmail("sinner@sin.com");
//			users.setName("sinner");
//			users.setPoliceCode("p123456");
//			users.setIdcard("123456789");
//			users.setPolice("普陀");
//			users.setAction("www.test.com");
//			usersMapper.insert(users);
//		}
//		usersMapper.batchInsert(userList);
//	}
		
		/*List<Menu> menuList=new ArrayList<Menu>();
		for (int i = 2; i < 10; i++) {
			Menu menu=new Menu();
			menu.setMenuId(i);
			menu.setMenuName("menu");
			menu.setParentName("sinnren");
			menu.setAction("www");
			menu.setOderBy(1);
			menu.setStatus(1);
			menu.setArea("普陀");
			menu.setIcon("icon");
			menu.setCreateTime(new Date());
			menuList.add(menu);
		}
		menuMapper.batchInsert(menuList);
		
		List<Role> roleList=new ArrayList<Role>();
		for (int i = 2; i < 10; i++) {
			Role role=new Role();
			role.setRoleId(i);
			role.setRoleName("管理员");
			role.setRoleTitle("sinner");
			role.setCreateTime(new Date());
			roleList.add(role);
		}
		roleMapper.batchInsert(roleList);
		System.out.println(roleList.size());
		
		
		List<UserRoles> userRolesList=new ArrayList<UserRoles>();
		for (int i = 2; i < 10; i++) {
			UserRoles userRoles=new UserRoles();
			userRoles.setId(i);
			userRoles.setRoleId(i);
			userRoles.setUserId(i);
			userRolesList.add(userRoles);
		}
		userRolesMapper.batchInsert(userRolesList);
		
		List<RoleMenus> roleMenusList=new ArrayList<RoleMenus>();
		for (int i = 2; i < 10; i++) {
			RoleMenus roleMenus=new RoleMenus();
			roleMenus.setId(i);
			roleMenus.setMenuId(i);
			roleMenus.setRoleId(i);
			roleMenusList.add(roleMenus);
		}
		roleMenusMapper.batchInsert(roleMenusList);*/
	


}
