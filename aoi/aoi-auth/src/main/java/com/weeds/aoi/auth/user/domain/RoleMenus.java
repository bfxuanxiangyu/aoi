package com.weeds.aoi.auth.user.domain;

public class RoleMenus {
    /**
     * <pre>
     * 
     * 表字段 : role_menus.ID
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 菜单编号
     * 表字段 : role_menus.MENU_ID
     * </pre>
     */
    private Integer menuId;

    /**
     * <pre>
     * 角色编号
     * 表字段 : role_menus.ROLE_ID
     * </pre>
     */
    private Integer roleId;

    /**
     * <pre>
     * 获取：
     * 表字段：role_menus.ID
     * </pre>
     *
     * @return role_menus.ID：
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * 设置：
     * 表字段：role_menus.ID
     * </pre>
     *
     * @param id
     *            role_menus.ID：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 获取：菜单编号
     * 表字段：role_menus.MENU_ID
     * </pre>
     *
     * @return role_menus.MENU_ID：菜单编号
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * <pre>
     * 设置：菜单编号
     * 表字段：role_menus.MENU_ID
     * </pre>
     *
     * @param menuId
     *            role_menus.MENU_ID：菜单编号
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    /**
     * <pre>
     * 获取：角色编号
     * 表字段：role_menus.ROLE_ID
     * </pre>
     *
     * @return role_menus.ROLE_ID：角色编号
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * <pre>
     * 设置：角色编号
     * 表字段：role_menus.ROLE_ID
     * </pre>
     *
     * @param roleId
     *            role_menus.ROLE_ID：角色编号
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}