package com.weeds.aoi.auth.user.web;

import com.weeds.aoi.auth.log.OperateLog;
import com.weeds.aoi.auth.log.OperateType;
import com.weeds.aoi.auth.user.domain.Menu;
import com.weeds.aoi.auth.user.service.MenuService;
import com.weeds.aoi.auth.user.service.RoleService;
import com.weeds.aoi.auth.user.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/menu")
public class MenuManagerController {
	
	@Resource
	private UserService userService;
	
	@Resource
	private MenuService menuService;

	@Resource
	private RoleService roleService;

	@OperateLog(desc="查询菜单", type = OperateType.QUERY, moudle="菜单管理", menu="menumanager")
	@RequestMapping("/list")
	public ModelAndView subMenuList(String menuName) {
		if(menuName == null){
			menuName = "root";
		}
		ModelAndView view = new ModelAndView();
		
		List<Menu> menus =  menuService.getSubmenu(menuName, userService.isAdmin());
		
		if (!menuName.equals("root")) {
			Menu menu = menuService.getMenu(menuName);
			List<Menu> parenNames = menuService.getParenNames(menu,new ArrayList<Menu>(100));
			Collections.reverse(parenNames);
			view.addObject("parenNames",parenNames);
		}
		
		view.addObject("menus", menus);
		view.addObject("pName", menuName);
		view.setViewName("menu/menulist");
		return view;
	}

	@RequestMapping("/delete")
	@OperateLog(desc="删除菜单", type = OperateType.DELETE, moudle="删除菜单", menu="deletemenu")
	public ModelAndView deleteMenu(@RequestParam(name = "menuName") String menuName) {
		int isSuc = 0;
		String parentName = menuService.getMenu(menuName).getParentName();
		isSuc = menuService.delByMenuName(menuName);
		ModelAndView view = subMenuList(parentName);
		view.addObject("isSuc", isSuc > 0);
		return view;
	}

	@RequestMapping("/add")
	public ModelAndView addMenu(@RequestParam(name = "parentName", required = true) String parentName) {
		ModelAndView view = new ModelAndView();
		Menu menu = new Menu();
		menu.setParentName(parentName);
		view.addObject("pName", parentName);
		view.addObject("menu", menu);
		view.setViewName("menu/addmenu");
		return view;

	}

	@RequestMapping("/edit")
	public ModelAndView editMenu(@RequestParam(name = "menuName", required = true) String menuName) {
		ModelAndView view = new ModelAndView();
		Menu menu = menuService.getMenu(menuName);
		view.addObject("menu", menu);
		view.setViewName("menu/updatemenu");
		return view;
	}

	@RequestMapping("/update")
	@OperateLog(desc="修改菜单", type = OperateType.UPDATE, moudle="修改菜单",menu="updatemenu")
	public ModelAndView updateMenu(Menu menu) {
		menuService.updateMenu(menu);
		return subMenuList(menu.getParentName());
	}

	@RequestMapping("/save")
	@OperateLog(desc="增加菜单", type = OperateType.ADD, moudle="新增菜单",menu="addmenu")
	public ModelAndView save(Menu menu) {				
		menuService.saveMenu(menu);
		return subMenuList(menu.getParentName());
	}

	@ResponseBody
	@RequestMapping("/checkMenuName")
	public Map<String, Boolean> checkMenuName(@RequestParam("menuName") String menuName) {
		Menu menu = menuService.getMenu(menuName);
		boolean result = false;
		if (menu == null) {
			result = true;
		}
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("valid", result);
		return map;
	}

	@RequestMapping(value = "/select")
	public @ResponseBody List<Map<String, String>> getRoleTree(@RequestParam(name = "roleId") int roleId) {
		return roleService.getRoleMenuTree(roleId);
	}

	@RequestMapping("/back")
	public ModelAndView backMenu(String parentName) {
		if ("root".equals(parentName)) {
			return subMenuList(null);
		} else {
			return subMenuList(menuService.getMenu(parentName).getParentName());
		}

	}

}
