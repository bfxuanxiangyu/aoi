package com.weeds.aoi.auth.user.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.weeds.aoi.core.dao.Criterion;

public class LogQuery {
    protected String orderByClause;

    protected boolean distinct;

    protected List<LogCriteria> oredCriteria;

    /**
     *
     */
    public LogQuery() {
        oredCriteria = new ArrayList<LogCriteria>();
    }

    /**
     *
     * @param orderByClause
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     *
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     *
     * @param distinct
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     *
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     *
     */
    public List<LogCriteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * @param criteria
     */
    public void or(LogCriteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     */
    public LogCriteria or() {
        LogCriteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *
     */
    public LogCriteria createCriteria() {
        LogCriteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     *
     */
    protected LogCriteria createCriteriaInternal() {
        LogCriteria criteria = new LogCriteria();
        return criteria;
    }

    /**
     *
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * 类注释
     * GeneratedCriteria
     * 数据库表：log
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                //throw new RuntimeException("Value for condition cannot be null");
            	return;
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                //throw new RuntimeException("Value for " + property + " cannot be null");
            	return;
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
               //throw new RuntimeException("Between values for " + property + " cannot be null");
            	return;
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public LogCriteria andIdIsNull() {
            addCriterion("ID is null");
            return (LogCriteria) this;
        }

        public LogCriteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (LogCriteria) this;
        }

        public LogCriteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (LogCriteria) this;
        }

        public LogCriteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (LogCriteria) this;
        }

        public LogCriteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (LogCriteria) this;
        }

        public LogCriteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (LogCriteria) this;
        }

        public LogCriteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (LogCriteria) this;
        }

        public LogCriteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (LogCriteria) this;
        }

        public LogCriteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (LogCriteria) this;
        }

        public LogCriteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (LogCriteria) this;
        }

        public LogCriteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (LogCriteria) this;
        }

        public LogCriteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (LogCriteria) this;
        }

        public LogCriteria andCreaterIsNull() {
            addCriterion("CREATER is null");
            return (LogCriteria) this;
        }

        public LogCriteria andCreaterIsNotNull() {
            addCriterion("CREATER is not null");
            return (LogCriteria) this;
        }

        public LogCriteria andCreaterEqualTo(String value) {
            addCriterion("CREATER =", value, "creater");
            return (LogCriteria) this;
        }

        public LogCriteria andCreaterNotEqualTo(String value) {
            addCriterion("CREATER <>", value, "creater");
            return (LogCriteria) this;
        }

        public LogCriteria andCreaterGreaterThan(String value) {
            addCriterion("CREATER >", value, "creater");
            return (LogCriteria) this;
        }

        public LogCriteria andCreaterGreaterThanOrEqualTo(String value) {
            addCriterion("CREATER >=", value, "creater");
            return (LogCriteria) this;
        }

        public LogCriteria andCreaterLessThan(String value) {
            addCriterion("CREATER <", value, "creater");
            return (LogCriteria) this;
        }

        public LogCriteria andCreaterLessThanOrEqualTo(String value) {
            addCriterion("CREATER <=", value, "creater");
            return (LogCriteria) this;
        }

        public LogCriteria andCreaterLike(String value) {
            addCriterion("CREATER like", value, "creater");
            return (LogCriteria) this;
        }

        public LogCriteria andCreaterNotLike(String value) {
            addCriterion("CREATER not like", value, "creater");
            return (LogCriteria) this;
        }

        public LogCriteria andCreaterIn(List<String> values) {
            addCriterion("CREATER in", values, "creater");
            return (LogCriteria) this;
        }

        public LogCriteria andCreaterNotIn(List<String> values) {
            addCriterion("CREATER not in", values, "creater");
            return (LogCriteria) this;
        }

        public LogCriteria andCreaterBetween(String value1, String value2) {
            addCriterion("CREATER between", value1, value2, "creater");
            return (LogCriteria) this;
        }

        public LogCriteria andCreaterNotBetween(String value1, String value2) {
            addCriterion("CREATER not between", value1, value2, "creater");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestUrlIsNull() {
            addCriterion("REQUEST_URL is null");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestUrlIsNotNull() {
            addCriterion("REQUEST_URL is not null");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestUrlEqualTo(String value) {
            addCriterion("REQUEST_URL =", value, "requestUrl");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestUrlNotEqualTo(String value) {
            addCriterion("REQUEST_URL <>", value, "requestUrl");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestUrlGreaterThan(String value) {
            addCriterion("REQUEST_URL >", value, "requestUrl");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestUrlGreaterThanOrEqualTo(String value) {
            addCriterion("REQUEST_URL >=", value, "requestUrl");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestUrlLessThan(String value) {
            addCriterion("REQUEST_URL <", value, "requestUrl");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestUrlLessThanOrEqualTo(String value) {
            addCriterion("REQUEST_URL <=", value, "requestUrl");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestUrlLike(String value) {
            addCriterion("REQUEST_URL like", value, "requestUrl");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestUrlNotLike(String value) {
            addCriterion("REQUEST_URL not like", value, "requestUrl");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestUrlIn(List<String> values) {
            addCriterion("REQUEST_URL in", values, "requestUrl");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestUrlNotIn(List<String> values) {
            addCriterion("REQUEST_URL not in", values, "requestUrl");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestUrlBetween(String value1, String value2) {
            addCriterion("REQUEST_URL between", value1, value2, "requestUrl");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestUrlNotBetween(String value1, String value2) {
            addCriterion("REQUEST_URL not between", value1, value2, "requestUrl");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestTimeIsNull() {
            addCriterion("REQUEST_TIME is null");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestTimeIsNotNull() {
            addCriterion("REQUEST_TIME is not null");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestTimeEqualTo(Date value) {
            addCriterion("REQUEST_TIME =", value, "requestTime");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestTimeNotEqualTo(Date value) {
            addCriterion("REQUEST_TIME <>", value, "requestTime");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestTimeGreaterThan(Date value) {
            addCriterion("REQUEST_TIME >", value, "requestTime");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("REQUEST_TIME >=", value, "requestTime");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestTimeLessThan(Date value) {
            addCriterion("REQUEST_TIME <", value, "requestTime");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestTimeLessThanOrEqualTo(Date value) {
            addCriterion("REQUEST_TIME <=", value, "requestTime");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestTimeIn(List<Date> values) {
            addCriterion("REQUEST_TIME in", values, "requestTime");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestTimeNotIn(List<Date> values) {
            addCriterion("REQUEST_TIME not in", values, "requestTime");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestTimeBetween(Date value1, Date value2) {
            addCriterion("REQUEST_TIME between", value1, value2, "requestTime");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestTimeNotBetween(Date value1, Date value2) {
            addCriterion("REQUEST_TIME not between", value1, value2, "requestTime");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestMethodIsNull() {
            addCriterion("REQUEST_METHOD is null");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestMethodIsNotNull() {
            addCriterion("REQUEST_METHOD is not null");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestMethodEqualTo(String value) {
            addCriterion("REQUEST_METHOD =", value, "requestMethod");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestMethodNotEqualTo(String value) {
            addCriterion("REQUEST_METHOD <>", value, "requestMethod");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestMethodGreaterThan(String value) {
            addCriterion("REQUEST_METHOD >", value, "requestMethod");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestMethodGreaterThanOrEqualTo(String value) {
            addCriterion("REQUEST_METHOD >=", value, "requestMethod");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestMethodLessThan(String value) {
            addCriterion("REQUEST_METHOD <", value, "requestMethod");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestMethodLessThanOrEqualTo(String value) {
            addCriterion("REQUEST_METHOD <=", value, "requestMethod");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestMethodLike(String value) {
            addCriterion("REQUEST_METHOD like", value, "requestMethod");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestMethodNotLike(String value) {
            addCriterion("REQUEST_METHOD not like", value, "requestMethod");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestMethodIn(List<String> values) {
            addCriterion("REQUEST_METHOD in", values, "requestMethod");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestMethodNotIn(List<String> values) {
            addCriterion("REQUEST_METHOD not in", values, "requestMethod");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestMethodBetween(String value1, String value2) {
            addCriterion("REQUEST_METHOD between", value1, value2, "requestMethod");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestMethodNotBetween(String value1, String value2) {
            addCriterion("REQUEST_METHOD not between", value1, value2, "requestMethod");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestParamIsNull() {
            addCriterion("REQUEST_PARAM is null");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestParamIsNotNull() {
            addCriterion("REQUEST_PARAM is not null");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestParamEqualTo(String value) {
            addCriterion("REQUEST_PARAM =", value, "requestParam");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestParamNotEqualTo(String value) {
            addCriterion("REQUEST_PARAM <>", value, "requestParam");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestParamGreaterThan(String value) {
            addCriterion("REQUEST_PARAM >", value, "requestParam");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestParamGreaterThanOrEqualTo(String value) {
            addCriterion("REQUEST_PARAM >=", value, "requestParam");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestParamLessThan(String value) {
            addCriterion("REQUEST_PARAM <", value, "requestParam");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestParamLessThanOrEqualTo(String value) {
            addCriterion("REQUEST_PARAM <=", value, "requestParam");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestParamLike(String value) {
            addCriterion("REQUEST_PARAM like", value, "requestParam");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestParamNotLike(String value) {
            addCriterion("REQUEST_PARAM not like", value, "requestParam");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestParamIn(List<String> values) {
            addCriterion("REQUEST_PARAM in", values, "requestParam");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestParamNotIn(List<String> values) {
            addCriterion("REQUEST_PARAM not in", values, "requestParam");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestParamBetween(String value1, String value2) {
            addCriterion("REQUEST_PARAM between", value1, value2, "requestParam");
            return (LogCriteria) this;
        }

        public LogCriteria andRequestParamNotBetween(String value1, String value2) {
            addCriterion("REQUEST_PARAM not between", value1, value2, "requestParam");
            return (LogCriteria) this;
        }

        public LogCriteria andExecuteTimeIsNull() {
            addCriterion("EXECUTE_TIME is null");
            return (LogCriteria) this;
        }

        public LogCriteria andExecuteTimeIsNotNull() {
            addCriterion("EXECUTE_TIME is not null");
            return (LogCriteria) this;
        }

        public LogCriteria andExecuteTimeEqualTo(Integer value) {
            addCriterion("EXECUTE_TIME =", value, "executeTime");
            return (LogCriteria) this;
        }

        public LogCriteria andExecuteTimeNotEqualTo(Integer value) {
            addCriterion("EXECUTE_TIME <>", value, "executeTime");
            return (LogCriteria) this;
        }

        public LogCriteria andExecuteTimeGreaterThan(Integer value) {
            addCriterion("EXECUTE_TIME >", value, "executeTime");
            return (LogCriteria) this;
        }

        public LogCriteria andExecuteTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("EXECUTE_TIME >=", value, "executeTime");
            return (LogCriteria) this;
        }

        public LogCriteria andExecuteTimeLessThan(Integer value) {
            addCriterion("EXECUTE_TIME <", value, "executeTime");
            return (LogCriteria) this;
        }

        public LogCriteria andExecuteTimeLessThanOrEqualTo(Integer value) {
            addCriterion("EXECUTE_TIME <=", value, "executeTime");
            return (LogCriteria) this;
        }

        public LogCriteria andExecuteTimeIn(List<Integer> values) {
            addCriterion("EXECUTE_TIME in", values, "executeTime");
            return (LogCriteria) this;
        }

        public LogCriteria andExecuteTimeNotIn(List<Integer> values) {
            addCriterion("EXECUTE_TIME not in", values, "executeTime");
            return (LogCriteria) this;
        }

        public LogCriteria andExecuteTimeBetween(Integer value1, Integer value2) {
            addCriterion("EXECUTE_TIME between", value1, value2, "executeTime");
            return (LogCriteria) this;
        }

        public LogCriteria andExecuteTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("EXECUTE_TIME not between", value1, value2, "executeTime");
            return (LogCriteria) this;
        }

        public LogCriteria andHttpCodeIsNull() {
            addCriterion("HTTP_CODE is null");
            return (LogCriteria) this;
        }

        public LogCriteria andHttpCodeIsNotNull() {
            addCriterion("HTTP_CODE is not null");
            return (LogCriteria) this;
        }

        public LogCriteria andHttpCodeEqualTo(Integer value) {
            addCriterion("HTTP_CODE =", value, "httpCode");
            return (LogCriteria) this;
        }

        public LogCriteria andHttpCodeNotEqualTo(Integer value) {
            addCriterion("HTTP_CODE <>", value, "httpCode");
            return (LogCriteria) this;
        }

        public LogCriteria andHttpCodeGreaterThan(Integer value) {
            addCriterion("HTTP_CODE >", value, "httpCode");
            return (LogCriteria) this;
        }

        public LogCriteria andHttpCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("HTTP_CODE >=", value, "httpCode");
            return (LogCriteria) this;
        }

        public LogCriteria andHttpCodeLessThan(Integer value) {
            addCriterion("HTTP_CODE <", value, "httpCode");
            return (LogCriteria) this;
        }

        public LogCriteria andHttpCodeLessThanOrEqualTo(Integer value) {
            addCriterion("HTTP_CODE <=", value, "httpCode");
            return (LogCriteria) this;
        }

        public LogCriteria andHttpCodeIn(List<Integer> values) {
            addCriterion("HTTP_CODE in", values, "httpCode");
            return (LogCriteria) this;
        }

        public LogCriteria andHttpCodeNotIn(List<Integer> values) {
            addCriterion("HTTP_CODE not in", values, "httpCode");
            return (LogCriteria) this;
        }

        public LogCriteria andHttpCodeBetween(Integer value1, Integer value2) {
            addCriterion("HTTP_CODE between", value1, value2, "httpCode");
            return (LogCriteria) this;
        }

        public LogCriteria andHttpCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("HTTP_CODE not between", value1, value2, "httpCode");
            return (LogCriteria) this;
        }

        public LogCriteria andUserAgentIsNull() {
            addCriterion("USER_AGENT is null");
            return (LogCriteria) this;
        }

        public LogCriteria andUserAgentIsNotNull() {
            addCriterion("USER_AGENT is not null");
            return (LogCriteria) this;
        }

        public LogCriteria andUserAgentEqualTo(String value) {
            addCriterion("USER_AGENT =", value, "userAgent");
            return (LogCriteria) this;
        }

        public LogCriteria andUserAgentNotEqualTo(String value) {
            addCriterion("USER_AGENT <>", value, "userAgent");
            return (LogCriteria) this;
        }

        public LogCriteria andUserAgentGreaterThan(String value) {
            addCriterion("USER_AGENT >", value, "userAgent");
            return (LogCriteria) this;
        }

        public LogCriteria andUserAgentGreaterThanOrEqualTo(String value) {
            addCriterion("USER_AGENT >=", value, "userAgent");
            return (LogCriteria) this;
        }

        public LogCriteria andUserAgentLessThan(String value) {
            addCriterion("USER_AGENT <", value, "userAgent");
            return (LogCriteria) this;
        }

        public LogCriteria andUserAgentLessThanOrEqualTo(String value) {
            addCriterion("USER_AGENT <=", value, "userAgent");
            return (LogCriteria) this;
        }

        public LogCriteria andUserAgentLike(String value) {
            addCriterion("USER_AGENT like", value, "userAgent");
            return (LogCriteria) this;
        }

        public LogCriteria andUserAgentNotLike(String value) {
            addCriterion("USER_AGENT not like", value, "userAgent");
            return (LogCriteria) this;
        }

        public LogCriteria andUserAgentIn(List<String> values) {
            addCriterion("USER_AGENT in", values, "userAgent");
            return (LogCriteria) this;
        }

        public LogCriteria andUserAgentNotIn(List<String> values) {
            addCriterion("USER_AGENT not in", values, "userAgent");
            return (LogCriteria) this;
        }

        public LogCriteria andUserAgentBetween(String value1, String value2) {
            addCriterion("USER_AGENT between", value1, value2, "userAgent");
            return (LogCriteria) this;
        }

        public LogCriteria andUserAgentNotBetween(String value1, String value2) {
            addCriterion("USER_AGENT not between", value1, value2, "userAgent");
            return (LogCriteria) this;
        }

        public LogCriteria andClientIpIsNull() {
            addCriterion("CLIENT_IP is null");
            return (LogCriteria) this;
        }

        public LogCriteria andClientIpIsNotNull() {
            addCriterion("CLIENT_IP is not null");
            return (LogCriteria) this;
        }

        public LogCriteria andClientIpEqualTo(String value) {
            addCriterion("CLIENT_IP =", value, "clientIp");
            return (LogCriteria) this;
        }

        public LogCriteria andClientIpNotEqualTo(String value) {
            addCriterion("CLIENT_IP <>", value, "clientIp");
            return (LogCriteria) this;
        }

        public LogCriteria andClientIpGreaterThan(String value) {
            addCriterion("CLIENT_IP >", value, "clientIp");
            return (LogCriteria) this;
        }

        public LogCriteria andClientIpGreaterThanOrEqualTo(String value) {
            addCriterion("CLIENT_IP >=", value, "clientIp");
            return (LogCriteria) this;
        }

        public LogCriteria andClientIpLessThan(String value) {
            addCriterion("CLIENT_IP <", value, "clientIp");
            return (LogCriteria) this;
        }

        public LogCriteria andClientIpLessThanOrEqualTo(String value) {
            addCriterion("CLIENT_IP <=", value, "clientIp");
            return (LogCriteria) this;
        }

        public LogCriteria andClientIpLike(String value) {
            addCriterion("CLIENT_IP like", value, "clientIp");
            return (LogCriteria) this;
        }

        public LogCriteria andClientIpNotLike(String value) {
            addCriterion("CLIENT_IP not like", value, "clientIp");
            return (LogCriteria) this;
        }

        public LogCriteria andClientIpIn(List<String> values) {
            addCriterion("CLIENT_IP in", values, "clientIp");
            return (LogCriteria) this;
        }

        public LogCriteria andClientIpNotIn(List<String> values) {
            addCriterion("CLIENT_IP not in", values, "clientIp");
            return (LogCriteria) this;
        }

        public LogCriteria andClientIpBetween(String value1, String value2) {
            addCriterion("CLIENT_IP between", value1, value2, "clientIp");
            return (LogCriteria) this;
        }

        public LogCriteria andClientIpNotBetween(String value1, String value2) {
            addCriterion("CLIENT_IP not between", value1, value2, "clientIp");
            return (LogCriteria) this;
        }

        public LogCriteria andDescriptionIsNull() {
            addCriterion("DESCRIPTION is null");
            return (LogCriteria) this;
        }

        public LogCriteria andDescriptionIsNotNull() {
            addCriterion("DESCRIPTION is not null");
            return (LogCriteria) this;
        }

        public LogCriteria andDescriptionEqualTo(String value) {
            addCriterion("DESCRIPTION =", value, "description");
            return (LogCriteria) this;
        }

        public LogCriteria andDescriptionNotEqualTo(String value) {
            addCriterion("DESCRIPTION <>", value, "description");
            return (LogCriteria) this;
        }

        public LogCriteria andDescriptionGreaterThan(String value) {
            addCriterion("DESCRIPTION >", value, "description");
            return (LogCriteria) this;
        }

        public LogCriteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION >=", value, "description");
            return (LogCriteria) this;
        }

        public LogCriteria andDescriptionLessThan(String value) {
            addCriterion("DESCRIPTION <", value, "description");
            return (LogCriteria) this;
        }

        public LogCriteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION <=", value, "description");
            return (LogCriteria) this;
        }

        public LogCriteria andDescriptionLike(String value) {
            addCriterion("DESCRIPTION like", value, "description");
            return (LogCriteria) this;
        }

        public LogCriteria andDescriptionNotLike(String value) {
            addCriterion("DESCRIPTION not like", value, "description");
            return (LogCriteria) this;
        }

        public LogCriteria andDescriptionIn(List<String> values) {
            addCriterion("DESCRIPTION in", values, "description");
            return (LogCriteria) this;
        }

        public LogCriteria andDescriptionNotIn(List<String> values) {
            addCriterion("DESCRIPTION not in", values, "description");
            return (LogCriteria) this;
        }

        public LogCriteria andDescriptionBetween(String value1, String value2) {
            addCriterion("DESCRIPTION between", value1, value2, "description");
            return (LogCriteria) this;
        }

        public LogCriteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("DESCRIPTION not between", value1, value2, "description");
            return (LogCriteria) this;
        }

        public LogCriteria andErrorMessageIsNull() {
            addCriterion("ERROR_MESSAGE is null");
            return (LogCriteria) this;
        }

        public LogCriteria andErrorMessageIsNotNull() {
            addCriterion("ERROR_MESSAGE is not null");
            return (LogCriteria) this;
        }

        public LogCriteria andErrorMessageEqualTo(String value) {
            addCriterion("ERROR_MESSAGE =", value, "errorMessage");
            return (LogCriteria) this;
        }

        public LogCriteria andErrorMessageNotEqualTo(String value) {
            addCriterion("ERROR_MESSAGE <>", value, "errorMessage");
            return (LogCriteria) this;
        }

        public LogCriteria andErrorMessageGreaterThan(String value) {
            addCriterion("ERROR_MESSAGE >", value, "errorMessage");
            return (LogCriteria) this;
        }

        public LogCriteria andErrorMessageGreaterThanOrEqualTo(String value) {
            addCriterion("ERROR_MESSAGE >=", value, "errorMessage");
            return (LogCriteria) this;
        }

        public LogCriteria andErrorMessageLessThan(String value) {
            addCriterion("ERROR_MESSAGE <", value, "errorMessage");
            return (LogCriteria) this;
        }

        public LogCriteria andErrorMessageLessThanOrEqualTo(String value) {
            addCriterion("ERROR_MESSAGE <=", value, "errorMessage");
            return (LogCriteria) this;
        }

        public LogCriteria andErrorMessageLike(String value) {
            addCriterion("ERROR_MESSAGE like", value, "errorMessage");
            return (LogCriteria) this;
        }

        public LogCriteria andErrorMessageNotLike(String value) {
            addCriterion("ERROR_MESSAGE not like", value, "errorMessage");
            return (LogCriteria) this;
        }

        public LogCriteria andErrorMessageIn(List<String> values) {
            addCriterion("ERROR_MESSAGE in", values, "errorMessage");
            return (LogCriteria) this;
        }

        public LogCriteria andErrorMessageNotIn(List<String> values) {
            addCriterion("ERROR_MESSAGE not in", values, "errorMessage");
            return (LogCriteria) this;
        }

        public LogCriteria andErrorMessageBetween(String value1, String value2) {
            addCriterion("ERROR_MESSAGE between", value1, value2, "errorMessage");
            return (LogCriteria) this;
        }

        public LogCriteria andErrorMessageNotBetween(String value1, String value2) {
            addCriterion("ERROR_MESSAGE not between", value1, value2, "errorMessage");
            return (LogCriteria) this;
        }

        public LogCriteria andOperateTypeIsNull() {
            addCriterion("OPERATE_TYPE is null");
            return (LogCriteria) this;
        }

        public LogCriteria andOperateTypeIsNotNull() {
            addCriterion("OPERATE_TYPE is not null");
            return (LogCriteria) this;
        }

        public LogCriteria andOperateTypeEqualTo(Integer value) {
            addCriterion("OPERATE_TYPE =", value, "operateType");
            return (LogCriteria) this;
        }

        public LogCriteria andOperateTypeNotEqualTo(Integer value) {
            addCriterion("OPERATE_TYPE <>", value, "operateType");
            return (LogCriteria) this;
        }

        public LogCriteria andOperateTypeGreaterThan(Integer value) {
            addCriterion("OPERATE_TYPE >", value, "operateType");
            return (LogCriteria) this;
        }

        public LogCriteria andOperateTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("OPERATE_TYPE >=", value, "operateType");
            return (LogCriteria) this;
        }

        public LogCriteria andOperateTypeLessThan(Integer value) {
            addCriterion("OPERATE_TYPE <", value, "operateType");
            return (LogCriteria) this;
        }

        public LogCriteria andOperateTypeLessThanOrEqualTo(Integer value) {
            addCriterion("OPERATE_TYPE <=", value, "operateType");
            return (LogCriteria) this;
        }

        public LogCriteria andOperateTypeIn(List<Integer> values) {
            addCriterion("OPERATE_TYPE in", values, "operateType");
            return (LogCriteria) this;
        }

        public LogCriteria andOperateTypeNotIn(List<Integer> values) {
            addCriterion("OPERATE_TYPE not in", values, "operateType");
            return (LogCriteria) this;
        }

        public LogCriteria andOperateTypeBetween(Integer value1, Integer value2) {
            addCriterion("OPERATE_TYPE between", value1, value2, "operateType");
            return (LogCriteria) this;
        }

        public LogCriteria andOperateTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("OPERATE_TYPE not between", value1, value2, "operateType");
            return (LogCriteria) this;
        }

        public LogCriteria andMoudleNameIsNull() {
            addCriterion("MOUDLE_NAME is null");
            return (LogCriteria) this;
        }

        public LogCriteria andMoudleNameIsNotNull() {
            addCriterion("MOUDLE_NAME is not null");
            return (LogCriteria) this;
        }

        public LogCriteria andMoudleNameEqualTo(String value) {
            addCriterion("MOUDLE_NAME =", value, "moudleName");
            return (LogCriteria) this;
        }

        public LogCriteria andMoudleNameNotEqualTo(String value) {
            addCriterion("MOUDLE_NAME <>", value, "moudleName");
            return (LogCriteria) this;
        }

        public LogCriteria andMoudleNameGreaterThan(String value) {
            addCriterion("MOUDLE_NAME >", value, "moudleName");
            return (LogCriteria) this;
        }

        public LogCriteria andMoudleNameGreaterThanOrEqualTo(String value) {
            addCriterion("MOUDLE_NAME >=", value, "moudleName");
            return (LogCriteria) this;
        }

        public LogCriteria andMoudleNameLessThan(String value) {
            addCriterion("MOUDLE_NAME <", value, "moudleName");
            return (LogCriteria) this;
        }

        public LogCriteria andMoudleNameLessThanOrEqualTo(String value) {
            addCriterion("MOUDLE_NAME <=", value, "moudleName");
            return (LogCriteria) this;
        }

        public LogCriteria andMoudleNameLike(String value) {
            addCriterion("MOUDLE_NAME like", value, "moudleName");
            return (LogCriteria) this;
        }

        public LogCriteria andMoudleNameNotLike(String value) {
            addCriterion("MOUDLE_NAME not like", value, "moudleName");
            return (LogCriteria) this;
        }

        public LogCriteria andMoudleNameIn(List<String> values) {
            addCriterion("MOUDLE_NAME in", values, "moudleName");
            return (LogCriteria) this;
        }

        public LogCriteria andMoudleNameNotIn(List<String> values) {
            addCriterion("MOUDLE_NAME not in", values, "moudleName");
            return (LogCriteria) this;
        }

        public LogCriteria andMoudleNameBetween(String value1, String value2) {
            addCriterion("MOUDLE_NAME between", value1, value2, "moudleName");
            return (LogCriteria) this;
        }

        public LogCriteria andMoudleNameNotBetween(String value1, String value2) {
            addCriterion("MOUDLE_NAME not between", value1, value2, "moudleName");
            return (LogCriteria) this;
        }

        public LogCriteria andMenuNameIsNull() {
            addCriterion("MENU_NAME is null");
            return (LogCriteria) this;
        }

        public LogCriteria andMenuNameIsNotNull() {
            addCriterion("MENU_NAME is not null");
            return (LogCriteria) this;
        }

        public LogCriteria andMenuNameEqualTo(String value) {
            addCriterion("MENU_NAME =", value, "menuName");
            return (LogCriteria) this;
        }

        public LogCriteria andMenuNameNotEqualTo(String value) {
            addCriterion("MENU_NAME <>", value, "menuName");
            return (LogCriteria) this;
        }

        public LogCriteria andMenuNameGreaterThan(String value) {
            addCriterion("MENU_NAME >", value, "menuName");
            return (LogCriteria) this;
        }

        public LogCriteria andMenuNameGreaterThanOrEqualTo(String value) {
            addCriterion("MENU_NAME >=", value, "menuName");
            return (LogCriteria) this;
        }

        public LogCriteria andMenuNameLessThan(String value) {
            addCriterion("MENU_NAME <", value, "menuName");
            return (LogCriteria) this;
        }

        public LogCriteria andMenuNameLessThanOrEqualTo(String value) {
            addCriterion("MENU_NAME <=", value, "menuName");
            return (LogCriteria) this;
        }

        public LogCriteria andMenuNameLike(String value) {
            addCriterion("MENU_NAME like", value, "menuName");
            return (LogCriteria) this;
        }

        public LogCriteria andMenuNameNotLike(String value) {
            addCriterion("MENU_NAME not like", value, "menuName");
            return (LogCriteria) this;
        }

        public LogCriteria andMenuNameIn(List<String> values) {
            addCriterion("MENU_NAME in", values, "menuName");
            return (LogCriteria) this;
        }

        public LogCriteria andMenuNameNotIn(List<String> values) {
            addCriterion("MENU_NAME not in", values, "menuName");
            return (LogCriteria) this;
        }

        public LogCriteria andMenuNameBetween(String value1, String value2) {
            addCriterion("MENU_NAME between", value1, value2, "menuName");
            return (LogCriteria) this;
        }

        public LogCriteria andMenuNameNotBetween(String value1, String value2) {
            addCriterion("MENU_NAME not between", value1, value2, "menuName");
            return (LogCriteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：log
     */
    public static class LogCriteria extends GeneratedCriteria {

        protected LogCriteria() {
            super();
        }
    }

}