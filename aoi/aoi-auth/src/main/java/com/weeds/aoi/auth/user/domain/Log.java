package com.weeds.aoi.auth.user.domain;

import java.util.Date;

public class Log {
    /**
     * <pre>
     * 
     * 表字段 : log.ID
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 操作用户名称
     * 表字段 : log.CREATER
     * </pre>
     */
    private String creater;

    /**
     * <pre>
     * URL
     * 表字段 : log.REQUEST_URL
     * </pre>
     */
    private String requestUrl;

    /**
     * <pre>
     * 日志生成时间
     * 表字段 : log.REQUEST_TIME
     * </pre>
     */
    private Date requestTime;

    /**
     * <pre>
     * 提交类型POST GET等
     * 表字段 : log.REQUEST_METHOD
     * </pre>
     */
    private String requestMethod;

    /**
     * <pre>
     * 请求参数
     * 表字段 : log.REQUEST_PARAM
     * </pre>
     */
    private String requestParam;

    /**
     * <pre>
     * 执行时间
     * 表字段 : log.EXECUTE_TIME
     * </pre>
     */
    private Integer executeTime;

    /**
     * <pre>
     * HTTP响应状态
     * 表字段 : log.HTTP_CODE
     * </pre>
     */
    private Integer httpCode;

    /**
     * <pre>
     * 浏览器类型
     * 表字段 : log.USER_AGENT
     * </pre>
     */
    private String userAgent;

    /**
     * <pre>
     * IP地址
     * 表字段 : log.CLIENT_IP
     * </pre>
     */
    private String clientIp;

    /**
     * <pre>
     * 详细描述，如方法名称
     * 表字段 : log.DESCRIPTION
     * </pre>
     */
    private String description;

    /**
     * <pre>
     * 异常信息
     * 表字段 : log.ERROR_MESSAGE
     * </pre>
     */
    private String errorMessage;

    /**
     * <pre>
     * 1:新增，2：删除，3：修改，4：查询，98:登录，99：其他
     * 表字段 : log.OPERATE_TYPE
     * </pre>
     */
    private Integer operateType;

    /**
     * <pre>
     * 操作模块名称
     * 表字段 : log.MOUDLE_NAME
     * </pre>
     */
    private String moudleName;

    /**
     * <pre>
     * 操作菜单名称
     * 表字段 : log.MENU_NAME
     * </pre>
     */
    private String menuName;

    /**
     * <pre>
     * 获取：
     * 表字段：log.ID
     * </pre>
     *
     * @return log.ID：
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * 设置：
     * 表字段：log.ID
     * </pre>
     *
     * @param id
     *            log.ID：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 获取：操作用户名称
     * 表字段：log.CREATER
     * </pre>
     *
     * @return log.CREATER：操作用户名称
     */
    public String getCreater() {
        return creater;
    }

    /**
     * <pre>
     * 设置：操作用户名称
     * 表字段：log.CREATER
     * </pre>
     *
     * @param creater
     *            log.CREATER：操作用户名称
     */
    public void setCreater(String creater) {
        this.creater = creater;
    }

    /**
     * <pre>
     * 获取：URL
     * 表字段：log.REQUEST_URL
     * </pre>
     *
     * @return log.REQUEST_URL：URL
     */
    public String getRequestUrl() {
        return requestUrl;
    }

    /**
     * <pre>
     * 设置：URL
     * 表字段：log.REQUEST_URL
     * </pre>
     *
     * @param requestUrl
     *            log.REQUEST_URL：URL
     */
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    /**
     * <pre>
     * 获取：日志生成时间
     * 表字段：log.REQUEST_TIME
     * </pre>
     *
     * @return log.REQUEST_TIME：日志生成时间
     */
    public Date getRequestTime() {
        return requestTime;
    }

    /**
     * <pre>
     * 设置：日志生成时间
     * 表字段：log.REQUEST_TIME
     * </pre>
     *
     * @param requestTime
     *            log.REQUEST_TIME：日志生成时间
     */
    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * <pre>
     * 获取：提交类型POST GET等
     * 表字段：log.REQUEST_METHOD
     * </pre>
     *
     * @return log.REQUEST_METHOD：提交类型POST GET等
     */
    public String getRequestMethod() {
        return requestMethod;
    }

    /**
     * <pre>
     * 设置：提交类型POST GET等
     * 表字段：log.REQUEST_METHOD
     * </pre>
     *
     * @param requestMethod
     *            log.REQUEST_METHOD：提交类型POST GET等
     */
    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    /**
     * <pre>
     * 获取：请求参数
     * 表字段：log.REQUEST_PARAM
     * </pre>
     *
     * @return log.REQUEST_PARAM：请求参数
     */
    public String getRequestParam() {
        return requestParam;
    }

    /**
     * <pre>
     * 设置：请求参数
     * 表字段：log.REQUEST_PARAM
     * </pre>
     *
     * @param requestParam
     *            log.REQUEST_PARAM：请求参数
     */
    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    /**
     * <pre>
     * 获取：执行时间
     * 表字段：log.EXECUTE_TIME
     * </pre>
     *
     * @return log.EXECUTE_TIME：执行时间
     */
    public Integer getExecuteTime() {
        return executeTime;
    }

    /**
     * <pre>
     * 设置：执行时间
     * 表字段：log.EXECUTE_TIME
     * </pre>
     *
     * @param executeTime
     *            log.EXECUTE_TIME：执行时间
     */
    public void setExecuteTime(Integer executeTime) {
        this.executeTime = executeTime;
    }

    /**
     * <pre>
     * 获取：HTTP响应状态
     * 表字段：log.HTTP_CODE
     * </pre>
     *
     * @return log.HTTP_CODE：HTTP响应状态
     */
    public Integer getHttpCode() {
        return httpCode;
    }

    /**
     * <pre>
     * 设置：HTTP响应状态
     * 表字段：log.HTTP_CODE
     * </pre>
     *
     * @param httpCode
     *            log.HTTP_CODE：HTTP响应状态
     */
    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    /**
     * <pre>
     * 获取：浏览器类型
     * 表字段：log.USER_AGENT
     * </pre>
     *
     * @return log.USER_AGENT：浏览器类型
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * <pre>
     * 设置：浏览器类型
     * 表字段：log.USER_AGENT
     * </pre>
     *
     * @param userAgent
     *            log.USER_AGENT：浏览器类型
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * <pre>
     * 获取：IP地址
     * 表字段：log.CLIENT_IP
     * </pre>
     *
     * @return log.CLIENT_IP：IP地址
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * <pre>
     * 设置：IP地址
     * 表字段：log.CLIENT_IP
     * </pre>
     *
     * @param clientIp
     *            log.CLIENT_IP：IP地址
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * <pre>
     * 获取：详细描述，如方法名称
     * 表字段：log.DESCRIPTION
     * </pre>
     *
     * @return log.DESCRIPTION：详细描述，如方法名称
     */
    public String getDescription() {
        return description;
    }

    /**
     * <pre>
     * 设置：详细描述，如方法名称
     * 表字段：log.DESCRIPTION
     * </pre>
     *
     * @param description
     *            log.DESCRIPTION：详细描述，如方法名称
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * <pre>
     * 获取：异常信息
     * 表字段：log.ERROR_MESSAGE
     * </pre>
     *
     * @return log.ERROR_MESSAGE：异常信息
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * <pre>
     * 设置：异常信息
     * 表字段：log.ERROR_MESSAGE
     * </pre>
     *
     * @param errorMessage
     *            log.ERROR_MESSAGE：异常信息
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * <pre>
     * 获取：1:新增，2：删除，3：修改，4：查询，98:登录，99：其他
     * 表字段：log.OPERATE_TYPE
     * </pre>
     *
     * @return log.OPERATE_TYPE：1:新增，2：删除，3：修改，4：查询，98:登录，99：其他
     */
    public Integer getOperateType() {
        return operateType;
    }

    /**
     * <pre>
     * 设置：1:新增，2：删除，3：修改，4：查询，98:登录，99：其他
     * 表字段：log.OPERATE_TYPE
     * </pre>
     *
     * @param operateType
     *            log.OPERATE_TYPE：1:新增，2：删除，3：修改，4：查询，98:登录，99：其他
     */
    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    /**
     * <pre>
     * 获取：操作模块名称
     * 表字段：log.MOUDLE_NAME
     * </pre>
     *
     * @return log.MOUDLE_NAME：操作模块名称
     */
    public String getMoudleName() {
        return moudleName;
    }

    /**
     * <pre>
     * 设置：操作模块名称
     * 表字段：log.MOUDLE_NAME
     * </pre>
     *
     * @param moudleName
     *            log.MOUDLE_NAME：操作模块名称
     */
    public void setMoudleName(String moudleName) {
        this.moudleName = moudleName;
    }

    /**
     * <pre>
     * 获取：操作菜单名称
     * 表字段：log.MENU_NAME
     * </pre>
     *
     * @return log.MENU_NAME：操作菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * <pre>
     * 设置：操作菜单名称
     * 表字段：log.MENU_NAME
     * </pre>
     *
     * @param menuName
     *            log.MENU_NAME：操作菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}