package com.weeds.aoi.auth.user.domain;

import java.util.Date;

public class Role {
    /**
     * <pre>
     * 角色编号
     * 表字段 : role.ROLE_ID
     * </pre>
     */
    private Integer roleId;

    /**
     * <pre>
     * 角色名
     * 表字段 : role.ROLE_NAME
     * </pre>
     */
    private String roleName;

    /**
     * <pre>
     * 
     * 表字段 : role.ROLE_TITLE
     * </pre>
     */
    private String roleTitle;

    /**
     * <pre>
     * 创建时间
     * 表字段 : role.CREATE_TIME
     * </pre>
     */
    private Date createTime;

    /**
     * <pre>
     * 
     * 表字段 : role.REMARK
     * </pre>
     */
    private String remark;

    /**
     * <pre>
     * 
     * 表字段 : role.CREATOR
     * </pre>
     */
    private String creator;

    /**
     * <pre>
     * 获取：角色编号
     * 表字段：role.ROLE_ID
     * </pre>
     *
     * @return role.ROLE_ID：角色编号
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * <pre>
     * 设置：角色编号
     * 表字段：role.ROLE_ID
     * </pre>
     *
     * @param roleId
     *            role.ROLE_ID：角色编号
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * <pre>
     * 获取：角色名
     * 表字段：role.ROLE_NAME
     * </pre>
     *
     * @return role.ROLE_NAME：角色名
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * <pre>
     * 设置：角色名
     * 表字段：role.ROLE_NAME
     * </pre>
     *
     * @param roleName
     *            role.ROLE_NAME：角色名
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * <pre>
     * 获取：
     * 表字段：role.ROLE_TITLE
     * </pre>
     *
     * @return role.ROLE_TITLE：
     */
    public String getRoleTitle() {
        return roleTitle;
    }

    /**
     * <pre>
     * 设置：
     * 表字段：role.ROLE_TITLE
     * </pre>
     *
     * @param roleTitle
     *            role.ROLE_TITLE：
     */
    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    /**
     * <pre>
     * 获取：创建时间
     * 表字段：role.CREATE_TIME
     * </pre>
     *
     * @return role.CREATE_TIME：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * <pre>
     * 设置：创建时间
     * 表字段：role.CREATE_TIME
     * </pre>
     *
     * @param createTime
     *            role.CREATE_TIME：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * <pre>
     * 获取：
     * 表字段：role.REMARK
     * </pre>
     *
     * @return role.REMARK：
     */
    public String getRemark() {
        return remark;
    }

    /**
     * <pre>
     * 设置：
     * 表字段：role.REMARK
     * </pre>
     *
     * @param remark
     *            role.REMARK：
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * <pre>
     * 获取：
     * 表字段：role.CREATOR
     * </pre>
     *
     * @return role.CREATOR：
     */
    public String getCreator() {
        return creator;
    }

    /**
     * <pre>
     * 设置：
     * 表字段：role.CREATOR
     * </pre>
     *
     * @param creator
     *            role.CREATOR：
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }
}