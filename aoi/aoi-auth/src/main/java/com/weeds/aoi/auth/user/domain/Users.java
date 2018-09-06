package com.weeds.aoi.auth.user.domain;

import java.util.Date;

public class Users {
    /**
     * <pre>
     * 
     * 表字段 : users.USER_ID
     * </pre>
     */
    private Integer userId;

    /**
     * <pre>
     * 用户名
     * 表字段 : users.USER_NAME
     * </pre>
     */
    private String userName;

    /**
     * <pre>
     * 用户密码
     * 表字段 : users.USER_PWD
     * </pre>
     */
    private String userPwd;

    /**
     * <pre>
     * 用户手机号
     * 表字段 : users.PHONE
     * </pre>
     */
    private String phone;

    /**
     * <pre>
     * 用户邮箱
     * 表字段 : users.EMAIL
     * </pre>
     */
    private String email;

    /**
     * <pre>
     * 姓名
     * 表字段 : users.NAME
     * </pre>
     */
    private String name;

    /**
     * <pre>
     * 警号
     * 表字段 : users.POLICE_CODE
     * </pre>
     */
    private String policeCode;
    
    /**
     * 区域编号
     * 表字段:users.AREA_CODE
     */
    private String areaCode;
    /**
	 * areaCode.
	 * @return  the areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * @param   areaCode  the areaCode to set
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
     * <pre>
     * 证件号码
     * 表字段 : users.IDCARD
     * </pre>
     */
    private String idcard;

    /**
     * <pre>
     * 派出所编码
     * 表字段 : users.POLICE
     * </pre>
     */
    private String police;

    /**
     * <pre>
     * 默认页面
     * 表字段 : users.ACTION
     * </pre>
     */
    private String action;

    /**
     * <pre>
     * 创建时间
     * 表字段 : users.CREATE_TIME
     * </pre>
     */
    private Date createTime;

    /**
     * <pre>
     * 修改时间
     * 表字段 : users.UPDATE_TIME
     * </pre>
     */
    private Date updateTime;

    /**
     * <pre>
     * 最后登录时间
     * 表字段 : users.LOGIN_LAST
     * </pre>
     */
    private Date loginLast;

    /**
     * <pre>
     * 总登陆次数
     * 表字段 : users.LOGIN_TOTAL
     * </pre>
     */
    private Integer loginTotal;

    /**
     * <pre>
     * 用户类型1：管理账号 2：普通用户
     * 表字段 : users.USER_TYPE
     * </pre>
     */
    private Integer userType;

    /**
     * <pre>
     * 用户状态 1为启用 0为禁用
     * 表字段 : users.STATUS
     * </pre>
     */
    private Integer status;

    /**
     * <pre>
     * 创建者
     * 表字段 : users.CREATOR
     * </pre>
     */
    private String creator;

    /**
     * <pre>
     * 获取：
     * 表字段：users.USER_ID
     * </pre>
     *
     * @return users.USER_ID：
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * <pre>
     * 设置：
     * 表字段：users.USER_ID
     * </pre>
     *
     * @param userId
     *            users.USER_ID：
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * <pre>
     * 获取：用户名
     * 表字段：users.USER_NAME
     * </pre>
     *
     * @return users.USER_NAME：用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * <pre>
     * 设置：用户名
     * 表字段：users.USER_NAME
     * </pre>
     *
     * @param userName
     *            users.USER_NAME：用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * <pre>
     * 获取：用户密码
     * 表字段：users.USER_PWD
     * </pre>
     *
     * @return users.USER_PWD：用户密码
     */
    public String getUserPwd() {
        return userPwd;
    }

    /**
     * <pre>
     * 设置：用户密码
     * 表字段：users.USER_PWD
     * </pre>
     *
     * @param userPwd
     *            users.USER_PWD：用户密码
     */
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    /**
     * <pre>
     * 获取：用户手机号
     * 表字段：users.PHONE
     * </pre>
     *
     * @return users.PHONE：用户手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * <pre>
     * 设置：用户手机号
     * 表字段：users.PHONE
     * </pre>
     *
     * @param phone
     *            users.PHONE：用户手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * <pre>
     * 获取：用户邮箱
     * 表字段：users.EMAIL
     * </pre>
     *
     * @return users.EMAIL：用户邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * <pre>
     * 设置：用户邮箱
     * 表字段：users.EMAIL
     * </pre>
     *
     * @param email
     *            users.EMAIL：用户邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * <pre>
     * 获取：姓名
     * 表字段：users.NAME
     * </pre>
     *
     * @return users.NAME：姓名
     */
    public String getName() {
        return name;
    }

    /**
     * <pre>
     * 设置：姓名
     * 表字段：users.NAME
     * </pre>
     *
     * @param name
     *            users.NAME：姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <pre>
     * 获取：警号
     * 表字段：users.POLICE_CODE
     * </pre>
     *
     * @return users.POLICE_CODE：警号
     */
    public String getPoliceCode() {
        return policeCode;
    }

    /**
     * <pre>
     * 设置：警号
     * 表字段：users.POLICE_CODE
     * </pre>
     *
     * @param policeCode
     *            users.POLICE_CODE：警号
     */
    public void setPoliceCode(String policeCode) {
        this.policeCode = policeCode;
    }

    /**
     * <pre>
     * 获取：证件号码
     * 表字段：users.IDCARD
     * </pre>
     *
     * @return users.IDCARD：证件号码
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * <pre>
     * 设置：证件号码
     * 表字段：users.IDCARD
     * </pre>
     *
     * @param idcard
     *            users.IDCARD：证件号码
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    /**
     * <pre>
     * 获取：派出所编码
     * 表字段：users.POLICE
     * </pre>
     *
     * @return users.POLICE：派出所编码
     */
    public String getPolice() {
        return police;
    }

    /**
     * <pre>
     * 设置：派出所编码
     * 表字段：users.POLICE
     * </pre>
     *
     * @param police
     *            users.POLICE：派出所编码
     */
    public void setPolice(String police) {
        this.police = police;
    }

    /**
     * <pre>
     * 获取：默认页面
     * 表字段：users.ACTION
     * </pre>
     *
     * @return users.ACTION：默认页面
     */
    public String getAction() {
        return action;
    }

    /**
     * <pre>
     * 设置：默认页面
     * 表字段：users.ACTION
     * </pre>
     *
     * @param action
     *            users.ACTION：默认页面
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * <pre>
     * 获取：创建时间
     * 表字段：users.CREATE_TIME
     * </pre>
     *
     * @return users.CREATE_TIME：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * <pre>
     * 设置：创建时间
     * 表字段：users.CREATE_TIME
     * </pre>
     *
     * @param createTime
     *            users.CREATE_TIME：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * <pre>
     * 获取：修改时间
     * 表字段：users.UPDATE_TIME
     * </pre>
     *
     * @return users.UPDATE_TIME：修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * <pre>
     * 设置：修改时间
     * 表字段：users.UPDATE_TIME
     * </pre>
     *
     * @param updateTime
     *            users.UPDATE_TIME：修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * <pre>
     * 获取：最后登录时间
     * 表字段：users.LOGIN_LAST
     * </pre>
     *
     * @return users.LOGIN_LAST：最后登录时间
     */
    public Date getLoginLast() {
        return loginLast;
    }

    /**
     * <pre>
     * 设置：最后登录时间
     * 表字段：users.LOGIN_LAST
     * </pre>
     *
     * @param loginLast
     *            users.LOGIN_LAST：最后登录时间
     */
    public void setLoginLast(Date loginLast) {
        this.loginLast = loginLast;
    }

    /**
     * <pre>
     * 获取：总登陆次数
     * 表字段：users.LOGIN_TOTAL
     * </pre>
     *
     * @return users.LOGIN_TOTAL：总登陆次数
     */
    public Integer getLoginTotal() {
        return loginTotal;
    }

    /**
     * <pre>
     * 设置：总登陆次数
     * 表字段：users.LOGIN_TOTAL
     * </pre>
     *
     * @param loginTotal
     *            users.LOGIN_TOTAL：总登陆次数
     */
    public void setLoginTotal(Integer loginTotal) {
        this.loginTotal = loginTotal;
    }

    /**
     * <pre>
     * 获取：用户类型1：管理账号 2：普通用户
     * 表字段：users.USER_TYPE
     * </pre>
     *
     * @return users.USER_TYPE：用户类型1：管理账号 2：普通用户
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * <pre>
     * 设置：用户类型1：管理账号 2：普通用户
     * 表字段：users.USER_TYPE
     * </pre>
     *
     * @param userType
     *            users.USER_TYPE：用户类型1：管理账号 2：普通用户
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * <pre>
     * 获取：用户状态 1为启用 0为禁用
     * 表字段：users.STATUS
     * </pre>
     *
     * @return users.STATUS：用户状态 1为启用 0为禁用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <pre>
     * 设置：用户状态 1为启用 0为禁用
     * 表字段：users.STATUS
     * </pre>
     *
     * @param status
     *            users.STATUS：用户状态 1为启用 0为禁用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * <pre>
     * 获取：创建者
     * 表字段：users.CREATOR
     * </pre>
     *
     * @return users.CREATOR：创建者
     */
    public String getCreator() {
        return creator;
    }

    /**
     * <pre>
     * 设置：创建者
     * 表字段：users.CREATOR
     * </pre>
     *
     * @param creator
     *            users.CREATOR：创建者
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }
}