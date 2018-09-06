package com.weeds.aoi.auth.user.domain;

public class UserRoles {
    /**
     * <pre>
     * 
     * 表字段 : user_roles.ID
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 角色编号
     * 表字段 : user_roles.ROLE_ID
     * </pre>
     */
    private Integer roleId;

    /**
     * <pre>
     * 用户名
     * 表字段 : user_roles.USER_ID
     * </pre>
     */
    private Integer userId;

    
    /**
	 * 
	 */
	
	public UserRoles() {
		super();
	}

	/**
	 * @param roleId
	 * @param userId
	 */
	
	public UserRoles(Integer roleId, Integer userId) {
		super();
		this.roleId = roleId;
		this.userId = userId;
	}

	/**
     * <pre>
     * 获取：
     * 表字段：user_roles.ID
     * </pre>
     *
     * @return user_roles.ID：
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * 设置：
     * 表字段：user_roles.ID
     * </pre>
     *
     * @param id
     *            user_roles.ID：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 获取：角色编号
     * 表字段：user_roles.ROLE_ID
     * </pre>
     *
     * @return user_roles.ROLE_ID：角色编号
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * <pre>
     * 设置：角色编号
     * 表字段：user_roles.ROLE_ID
     * </pre>
     *
     * @param roleId
     *            user_roles.ROLE_ID：角色编号
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * <pre>
     * 获取：用户名
     * 表字段：user_roles.USER_ID
     * </pre>
     *
     * @return user_roles.USER_ID：用户名
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * <pre>
     * 设置：用户名
     * 表字段：user_roles.USER_ID
     * </pre>
     *
     * @param userId
     *            user_roles.USER_ID：用户名
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}