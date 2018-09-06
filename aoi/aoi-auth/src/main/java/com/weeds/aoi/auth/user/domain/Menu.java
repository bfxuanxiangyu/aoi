package com.weeds.aoi.auth.user.domain;

import java.util.Date;

import org.thymeleaf.standard.processor.text.StandardTextInliningTextProcessor;

public class Menu {
    /**
     * <pre>
     * 菜单编号
     * 表字段 : menu.MENU_ID
     * </pre>
     */
    private Integer menuId;

    /**
     * <pre>
     * 菜单名
     * 表字段 : menu.MENU_NAME
     * </pre>
     */
    private String menuName;

    /**
     * <pre>
     * 上级菜单
     * 表字段 : menu.PARENT_NAME
     * </pre>
     */
    private String parentName;

    /**
     * <pre>
     * 请求地址
     * 表字段 : menu.ACTION
     * </pre>
     */
    private String action;

    /**
     * <pre>
     * 排序
     * 表字段 : menu.ODER_BY
     * </pre>
     */
    private Integer oderBy;

    /**
     * <pre>
     * 状态1为可用，0为不可用
     * 表字段 : menu.STATUS
     * </pre>
     */
    private Integer status;

    /**
     * <pre>
     * 区域
     * 表字段 : menu.AREA
     * </pre>
     */
    private String area;

    /**
     * <pre>
     * 图标
     * 表字段 : menu.ICON
     * </pre>
     */
    private String icon;

    /**
     * <pre>
     * 创建时间
     * 表字段 : menu.CREATE_TIME
     * </pre>
     */
    private Date createTime;

    /**
     * <pre>
     * 创建者
     * 表字段 : menu.CREATOR
     * </pre>
     */
    private String creator;

    /**
     * <pre>
     * 
     * 表字段 : menu.MENU_TITLE
     * </pre>
     */
    private String menuTitle;
    
    /**
     * 表字段MENU_FUNCTION
     */
    private String menuFunction;
    
    /**
     * 表字段MENU_STYLE
     */
    private String menuStyle;
    
    /**
	 * menuFunction.
	 * @return  the menuFunction
	 */
	public String getMenuFunction() {
		return menuFunction;
	}

	/**
	 * @param   menuFunction  the menuFunction to set
	 */
	public void setMenuFunction(String menuFunction) {
		this.menuFunction = menuFunction;
	}

	/**
	 * menuStyle.
	 * @return  the menuStyle
	 */
	public String getMenuStyle() {
		return menuStyle;
	}

	/**
	 * @param   menuStyle  the menuStyle to set
	 */
	public void setMenuStyle(String menuStyle) {
		this.menuStyle = menuStyle;
	}

	/**
	 * menuStyleClass.
	 * @return  the menuStyleClass
	 */
	public String getMenuStyleClass() {
		return menuStyleClass;
	}

	/**
	 * @param   menuStyleClass  the menuStyleClass to set
	 */
	public void setMenuStyleClass(String menuStyleClass) {
		this.menuStyleClass = menuStyleClass;
	}

	/**
     * 表字段MENU_STYLE_CLASS
     */
    private String menuStyleClass;

    /**
     * <pre>
     * 获取：菜单编号
     * 表字段：menu.MENU_ID
     * </pre>
     *
     * @return menu.MENU_ID：菜单编号
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * <pre>
     * 设置：菜单编号
     * 表字段：menu.MENU_ID
     * </pre>
     *
     * @param menuId
     *            menu.MENU_ID：菜单编号
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    /**
     * <pre>
     * 获取：菜单名
     * 表字段：menu.MENU_NAME
     * </pre>
     *
     * @return menu.MENU_NAME：菜单名
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * <pre>
     * 设置：菜单名
     * 表字段：menu.MENU_NAME
     * </pre>
     *
     * @param menuName
     *            menu.MENU_NAME：菜单名
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * <pre>
     * 获取：上级菜单
     * 表字段：menu.PARENT_NAME
     * </pre>
     *
     * @return menu.PARENT_NAME：上级菜单
     */
    public String getParentName() {
        return parentName;
    }

    /**
     * <pre>
     * 设置：上级菜单
     * 表字段：menu.PARENT_NAME
     * </pre>
     *
     * @param parentName
     *            menu.PARENT_NAME：上级菜单
     */
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    /**
     * <pre>
     * 获取：请求地址
     * 表字段：menu.ACTION
     * </pre>
     *
     * @return menu.ACTION：请求地址
     */
    public String getAction() {
        return action;
    }

    /**
     * <pre>
     * 设置：请求地址
     * 表字段：menu.ACTION
     * </pre>
     *
     * @param action
     *            menu.ACTION：请求地址
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * <pre>
     * 获取：排序
     * 表字段：menu.ODER_BY
     * </pre>
     *
     * @return menu.ODER_BY：排序
     */
    public Integer getOderBy() {
        return oderBy;
    }

    /**
     * <pre>
     * 设置：排序
     * 表字段：menu.ODER_BY
     * </pre>
     *
     * @param oderBy
     *            menu.ODER_BY：排序
     */
    public void setOderBy(Integer oderBy) {
        this.oderBy = oderBy;
    }

    /**
     * <pre>
     * 获取：状态1为可用，0为不可用
     * 表字段：menu.STATUS
     * </pre>
     *
     * @return menu.STATUS：状态1为可用，0为不可用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <pre>
     * 设置：状态1为可用，0为不可用
     * 表字段：menu.STATUS
     * </pre>
     *
     * @param status
     *            menu.STATUS：状态1为可用，0为不可用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * <pre>
     * 获取：区域
     * 表字段：menu.AREA
     * </pre>
     *
     * @return menu.AREA：区域
     */
    public String getArea() {
        return area;
    }

    /**
     * <pre>
     * 设置：区域
     * 表字段：menu.AREA
     * </pre>
     *
     * @param area
     *            menu.AREA：区域
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * <pre>
     * 获取：图标
     * 表字段：menu.ICON
     * </pre>
     *
     * @return menu.ICON：图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * <pre>
     * 设置：图标
     * 表字段：menu.ICON
     * </pre>
     *
     * @param icon
     *            menu.ICON：图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * <pre>
     * 获取：创建时间
     * 表字段：menu.CREATE_TIME
     * </pre>
     *
     * @return menu.CREATE_TIME：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * <pre>
     * 设置：创建时间
     * 表字段：menu.CREATE_TIME
     * </pre>
     *
     * @param createTime
     *            menu.CREATE_TIME：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * <pre>
     * 获取：创建者
     * 表字段：menu.CREATOR
     * </pre>
     *
     * @return menu.CREATOR：创建者
     */
    public String getCreator() {
        return creator;
    }

    /**
     * <pre>
     * 设置：创建者
     * 表字段：menu.CREATOR
     * </pre>
     *
     * @param creator
     *            menu.CREATOR：创建者
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * <pre>
     * 获取：
     * 表字段：menu.MENU_TITLE
     * </pre>
     *
     * @return menu.MENU_TITLE：
     */
    public String getMenuTitle() {
        return menuTitle;
    }

    /**
     * <pre>
     * 设置：
     * 表字段：menu.MENU_TITLE
     * </pre>
     *
     * @param menuTitle
     *            menu.MENU_TITLE：
     */
    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }
}