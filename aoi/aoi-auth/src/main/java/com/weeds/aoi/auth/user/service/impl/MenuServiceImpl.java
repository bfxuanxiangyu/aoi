package com.weeds.aoi.auth.user.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.weeds.aoi.auth.user.domain.Menu;
import com.weeds.aoi.auth.user.domain.Users;
import com.weeds.aoi.auth.user.mapper.MenuMapper;
import com.weeds.aoi.auth.user.mapper.RoleMenusMapper;
import com.weeds.aoi.auth.user.mapper.UsersMapper;
import com.weeds.aoi.auth.user.service.MenuService;

@Service
@CacheConfig(cacheNames = "menu")
public class MenuServiceImpl implements MenuService {
	
	@Resource
	private MenuMapper menuMapper;
	
	@Resource
	private RoleMenusMapper roleMenusMapper;
	
	@Resource
	private UsersMapper usersMapper;

	@Override
	@Cacheable(key="'getMenu-' + #menuName")
	public Menu getMenu(String menuName) {
		return menuMapper.selectByMenuName(menuName);
	}

	@Override
	@CacheEvict(allEntries=true, cacheNames="menu")
	public int updateMenu(Menu menu) {
		menu.setMenuId(menuMapper.selectByMenuName(menu.getMenuName()).getMenuId());
		return menuMapper.updateByPrimaryKey(menu);
	}
	
	@Override
	@Cacheable(key="'getSubmenu-' + #parentName + #all")
	public List<Menu> getSubmenu(String parentName, boolean all) {
		if(all){
			return menuMapper.selectSubmenuAll(parentName);
		}else{
			return menuMapper.selectSubmenu(parentName);
		}
	}

	@Override
	@CacheEvict(allEntries=true, cacheNames="menu")
	public int saveMenu(Menu menu) {
		Users u = (Users) SecurityUtils.getSubject().getPrincipal();
        menu.setCreator(u.getUserName());
		return menuMapper.insert(menu);
	}
	
	@Override
	@CacheEvict(allEntries=true, cacheNames="menu")
	public int delByMenuName(String menuName){	
		Menu selectByMenuName = menuMapper.selectByMenuName(menuName);
		if(selectByMenuName!=null){
			
			List<Menu> subMenus=menuMapper.selectSubmenu(menuName);
			for(Menu menu:subMenus){
				delByMenuName(menu.getMenuName());
			}
			
			roleMenusMapper.deleteByMenuId(selectByMenuName.getMenuId());
			menuMapper.deleteByMenuName(menuName);
			return 1;
		}else{
			return 0;
		}
		
	}
	
	@Override
	public List<Menu> getAllMenu() {
		return menuMapper.selectAll();
	}
	
	@Override
	public List<Menu> getParenNames(Menu menu, List<Menu> parentNames) {
		if(menu != null){
			parentNames.add(menu);
			if (!menu.getParentName().equals("root")) {
				Menu pm = menuMapper.selectByMenuName(menu.getParentName());
				getParenNames(pm, parentNames);
			}
		}
		return parentNames;
	}

	@Override
	@Cacheable(key="#userName + '-' + #parentName")
	public List<Menu> getRoleSubmenu(String userName, String parentName) {
		List<Menu> menus = this.getSubmenu(parentName, false);
		if(menus == null){
			return Collections.emptyList();
		}
		
		List<Menu> roleMenus = new ArrayList<Menu>(50);
		Subject subject = SecurityUtils.getSubject();
		for(Menu m : menus){
			if(StringUtils.isNotEmpty(m.getMenuName())){
				if(subject.isPermitted(m.getMenuName())){
					roleMenus.add(m);
				}
			}
		}
		return roleMenus;
	}
	
}
