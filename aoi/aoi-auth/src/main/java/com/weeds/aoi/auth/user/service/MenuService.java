package com.weeds.aoi.auth.user.service;

import com.weeds.aoi.auth.user.domain.Menu;

import java.util.List;

public interface MenuService {

	/**
	 * 获取所有可见菜单
	 * @param menuName
	 * @return
	 */
	List<Menu> getAllMenu();
	
	/**
	 * 通过菜单名获得菜单
	 * 
	 * @param menuName
	 * @return
	 */
	Menu getMenu(String menuName);

	/**
	 * 更新菜单
	 * 
	 * @param menu
	 * @return
	 */
	int updateMenu(Menu menu);

	/**
	 * 获取子菜单
	 * 
	 * @param parentName
	 * @return
	 */
	List<Menu> getSubmenu(String parentName, boolean all);
	
	/**
	 * 根据父菜单获取有权限的子菜单
	 * @param parentName
	 * @return
	 */
	List<Menu> getRoleSubmenu(String userName, String parentName);

	/**
	 * 保存菜单
	 * 
	 * @param parentName
	 * @return
	 */
	int saveMenu(Menu menu);

	/**
	 * 通过菜单名删除菜单
	 * 
	 * @param menuName
	 * @return
	 */
	int delByMenuName(String menuName);
	
	/**
	 * 通过菜单名查找该菜单的所有上级菜单
	 * 
	 * @param menuName
	 * @return
	 */
	List<Menu> getParenNames(Menu menu, List<Menu> parentNames);
}
