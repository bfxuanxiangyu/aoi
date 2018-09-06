package com.weeds.aoi.auth.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.weeds.aoi.auth.user.domain.Log;
import com.weeds.aoi.auth.user.mapper.LogQuery.LogCriteria;
import com.weeds.aoi.core.dao.Criterion;

public class LogSqlProvider {

    /**
     *
     * @param example
     */
    public String countByExample(LogQuery example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("log");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     *
     * @param example
     */
    public String deleteByExample(LogQuery example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("log");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     *
     * @param record
     */
    public String insertSelective(Log record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("log");
        
        if (record.getId() != null) {
            sql.VALUES("ID", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getCreater() != null) {
            sql.VALUES("CREATER", "#{creater,jdbcType=VARCHAR}");
        }
        
        if (record.getRequestUrl() != null) {
            sql.VALUES("REQUEST_URL", "#{requestUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getRequestTime() != null) {
            sql.VALUES("REQUEST_TIME", "#{requestTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getRequestMethod() != null) {
            sql.VALUES("REQUEST_METHOD", "#{requestMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getRequestParam() != null) {
            sql.VALUES("REQUEST_PARAM", "#{requestParam,jdbcType=VARCHAR}");
        }
        
        if (record.getExecuteTime() != null) {
            sql.VALUES("EXECUTE_TIME", "#{executeTime,jdbcType=INTEGER}");
        }
        
        if (record.getHttpCode() != null) {
            sql.VALUES("HTTP_CODE", "#{httpCode,jdbcType=INTEGER}");
        }
        
        if (record.getUserAgent() != null) {
            sql.VALUES("USER_AGENT", "#{userAgent,jdbcType=VARCHAR}");
        }
        
        if (record.getClientIp() != null) {
            sql.VALUES("CLIENT_IP", "#{clientIp,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.VALUES("DESCRIPTION", "#{description,jdbcType=VARCHAR}");
        }
        
        if (record.getErrorMessage() != null) {
            sql.VALUES("ERROR_MESSAGE", "#{errorMessage,jdbcType=VARCHAR}");
        }
        
        if (record.getOperateType() != null) {
            sql.VALUES("OPERATE_TYPE", "#{operateType,jdbcType=INTEGER}");
        }
        
        if (record.getMoudleName() != null) {
            sql.VALUES("MOUDLE_NAME", "#{moudleName,jdbcType=VARCHAR}");
        }
        
        if (record.getMenuName() != null) {
            sql.VALUES("MENU_NAME", "#{menuName,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    /**
     *
     * @param example
     */
    public String selectByExample(LogQuery example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("ID");
        } else {
            sql.SELECT("ID");
        }
        sql.SELECT("CREATER");
        sql.SELECT("REQUEST_URL");
        sql.SELECT("REQUEST_TIME");
        sql.SELECT("REQUEST_METHOD");
        sql.SELECT("REQUEST_PARAM");
        sql.SELECT("EXECUTE_TIME");
        sql.SELECT("HTTP_CODE");
        sql.SELECT("USER_AGENT");
        sql.SELECT("CLIENT_IP");
        sql.SELECT("DESCRIPTION");
        sql.SELECT("ERROR_MESSAGE");
        sql.SELECT("OPERATE_TYPE");
        sql.SELECT("MOUDLE_NAME");
        sql.SELECT("MENU_NAME");
        sql.FROM("log");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     *
     * @param parameter
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        Log record = (Log) parameter.get("record");
        LogQuery example = (LogQuery) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("log");
        
        if (record.getId() != null) {
            sql.SET("ID = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getCreater() != null) {
            sql.SET("CREATER = #{record.creater,jdbcType=VARCHAR}");
        }
        
        if (record.getRequestUrl() != null) {
            sql.SET("REQUEST_URL = #{record.requestUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getRequestTime() != null) {
            sql.SET("REQUEST_TIME = #{record.requestTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getRequestMethod() != null) {
            sql.SET("REQUEST_METHOD = #{record.requestMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getRequestParam() != null) {
            sql.SET("REQUEST_PARAM = #{record.requestParam,jdbcType=VARCHAR}");
        }
        
        if (record.getExecuteTime() != null) {
            sql.SET("EXECUTE_TIME = #{record.executeTime,jdbcType=INTEGER}");
        }
        
        if (record.getHttpCode() != null) {
            sql.SET("HTTP_CODE = #{record.httpCode,jdbcType=INTEGER}");
        }
        
        if (record.getUserAgent() != null) {
            sql.SET("USER_AGENT = #{record.userAgent,jdbcType=VARCHAR}");
        }
        
        if (record.getClientIp() != null) {
            sql.SET("CLIENT_IP = #{record.clientIp,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("DESCRIPTION = #{record.description,jdbcType=VARCHAR}");
        }
        
        if (record.getErrorMessage() != null) {
            sql.SET("ERROR_MESSAGE = #{record.errorMessage,jdbcType=VARCHAR}");
        }
        
        if (record.getOperateType() != null) {
            sql.SET("OPERATE_TYPE = #{record.operateType,jdbcType=INTEGER}");
        }
        
        if (record.getMoudleName() != null) {
            sql.SET("MOUDLE_NAME = #{record.moudleName,jdbcType=VARCHAR}");
        }
        
        if (record.getMenuName() != null) {
            sql.SET("MENU_NAME = #{record.menuName,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     *
     * @param parameter
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("log");
        
        sql.SET("ID = #{record.id,jdbcType=INTEGER}");
        sql.SET("CREATER = #{record.creater,jdbcType=VARCHAR}");
        sql.SET("REQUEST_URL = #{record.requestUrl,jdbcType=VARCHAR}");
        sql.SET("REQUEST_TIME = #{record.requestTime,jdbcType=TIMESTAMP}");
        sql.SET("REQUEST_METHOD = #{record.requestMethod,jdbcType=VARCHAR}");
        sql.SET("REQUEST_PARAM = #{record.requestParam,jdbcType=VARCHAR}");
        sql.SET("EXECUTE_TIME = #{record.executeTime,jdbcType=INTEGER}");
        sql.SET("HTTP_CODE = #{record.httpCode,jdbcType=INTEGER}");
        sql.SET("USER_AGENT = #{record.userAgent,jdbcType=VARCHAR}");
        sql.SET("CLIENT_IP = #{record.clientIp,jdbcType=VARCHAR}");
        sql.SET("DESCRIPTION = #{record.description,jdbcType=VARCHAR}");
        sql.SET("ERROR_MESSAGE = #{record.errorMessage,jdbcType=VARCHAR}");
        sql.SET("OPERATE_TYPE = #{record.operateType,jdbcType=INTEGER}");
        sql.SET("MOUDLE_NAME = #{record.moudleName,jdbcType=VARCHAR}");
        sql.SET("MENU_NAME = #{record.menuName,jdbcType=VARCHAR}");
        
        LogQuery example = (LogQuery) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     *
     * @param record
     */
    public String updateByPrimaryKeySelective(Log record) {
        SQL sql = new SQL();
        sql.UPDATE("log");
        
        if (record.getCreater() != null) {
            sql.SET("CREATER = #{creater,jdbcType=VARCHAR}");
        }
        
        if (record.getRequestUrl() != null) {
            sql.SET("REQUEST_URL = #{requestUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getRequestTime() != null) {
            sql.SET("REQUEST_TIME = #{requestTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getRequestMethod() != null) {
            sql.SET("REQUEST_METHOD = #{requestMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getRequestParam() != null) {
            sql.SET("REQUEST_PARAM = #{requestParam,jdbcType=VARCHAR}");
        }
        
        if (record.getExecuteTime() != null) {
            sql.SET("EXECUTE_TIME = #{executeTime,jdbcType=INTEGER}");
        }
        
        if (record.getHttpCode() != null) {
            sql.SET("HTTP_CODE = #{httpCode,jdbcType=INTEGER}");
        }
        
        if (record.getUserAgent() != null) {
            sql.SET("USER_AGENT = #{userAgent,jdbcType=VARCHAR}");
        }
        
        if (record.getClientIp() != null) {
            sql.SET("CLIENT_IP = #{clientIp,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("DESCRIPTION = #{description,jdbcType=VARCHAR}");
        }
        
        if (record.getErrorMessage() != null) {
            sql.SET("ERROR_MESSAGE = #{errorMessage,jdbcType=VARCHAR}");
        }
        
        if (record.getOperateType() != null) {
            sql.SET("OPERATE_TYPE = #{operateType,jdbcType=INTEGER}");
        }
        
        if (record.getMoudleName() != null) {
            sql.SET("MOUDLE_NAME = #{moudleName,jdbcType=VARCHAR}");
        }
        
        if (record.getMenuName() != null) {
            sql.SET("MENU_NAME = #{menuName,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("ID = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    /**
     *
     * @param sql
     * @param example
     * @param includeExamplePhrase
     */
    protected void applyWhere(SQL sql, LogQuery example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<LogCriteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            LogCriteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}