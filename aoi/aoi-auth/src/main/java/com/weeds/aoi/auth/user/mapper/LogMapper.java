package com.weeds.aoi.auth.user.mapper;

import com.weeds.aoi.auth.user.domain.Log;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface LogMapper {
    /**
     *
     * @param example
     */
    @SelectProvider(type=LogSqlProvider.class, method="countByExample")
    int countByExample(LogQuery example);

    /**
     *
     * @param example
     */
    @DeleteProvider(type=LogSqlProvider.class, method="deleteByExample")
    int deleteByExample(LogQuery example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    @Delete({
        "delete from log",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    @Insert({
        "insert into log (ID, CREATER, ",
        "REQUEST_URL, REQUEST_TIME, ",
        "REQUEST_METHOD, REQUEST_PARAM, ",
        "EXECUTE_TIME, HTTP_CODE, ",
        "USER_AGENT, CLIENT_IP, ",
        "DESCRIPTION, ERROR_MESSAGE, ",
        "OPERATE_TYPE, MOUDLE_NAME, ",
        "MENU_NAME)",
        "values (#{id,jdbcType=INTEGER}, #{creater,jdbcType=VARCHAR}, ",
        "#{requestUrl,jdbcType=VARCHAR}, #{requestTime,jdbcType=TIMESTAMP}, ",
        "#{requestMethod,jdbcType=VARCHAR}, #{requestParam,jdbcType=VARCHAR}, ",
        "#{executeTime,jdbcType=INTEGER}, #{httpCode,jdbcType=INTEGER}, ",
        "#{userAgent,jdbcType=VARCHAR}, #{clientIp,jdbcType=VARCHAR}, ",
        "#{description,jdbcType=VARCHAR}, #{errorMessage,jdbcType=VARCHAR}, ",
        "#{operateType,jdbcType=INTEGER}, #{moudleName,jdbcType=VARCHAR}, ",
        "#{menuName,jdbcType=VARCHAR})"
    })
    int insert(Log record);
    
    /**
     * 批量插入
     *
     * @param record
     */
    @Insert({
    	"<script>",
    	"insert into log (ID, CREATER, ",
        "REQUEST_URL, REQUEST_TIME, ",
        "REQUEST_METHOD, REQUEST_PARAM, ",
        "EXECUTE_TIME, HTTP_CODE, ",
        "USER_AGENT, CLIENT_IP, ",
        "DESCRIPTION, ERROR_MESSAGE, ",
        "OPERATE_TYPE, MOUDLE_NAME, ",
        "MENU_NAME) values ",
        "<foreach collection='logList' item='log' separator=','>",
        "(#{log.id,jdbcType=INTEGER}, #{log.creater,jdbcType=VARCHAR}, ",
        "#{log.requestUrl,jdbcType=VARCHAR}, #{log.requestTime,jdbcType=TIMESTAMP}, ",
        "#{log.requestMethod,jdbcType=VARCHAR}, #{log.requestParam,jdbcType=VARCHAR}, ",
        "#{log.executeTime,jdbcType=INTEGER}, #{log.httpCode,jdbcType=INTEGER}, ",
        "#{log.userAgent,jdbcType=VARCHAR}, #{log.clientIp,jdbcType=VARCHAR}, ",
        "#{log.description,jdbcType=VARCHAR}, #{log.errorMessage,jdbcType=VARCHAR}, ",
        "#{log.operateType,jdbcType=INTEGER}, #{log.moudleName,jdbcType=VARCHAR}, ",
        "#{log.menuName,jdbcType=VARCHAR})",
        "</foreach>",
        "</script>"
    })
    
    int batchInsert(@Param("logList") List<Log> logList);

    /**
     *
     * @param record
     */
    @InsertProvider(type=LogSqlProvider.class, method="insertSelective")
    int insertSelective(Log record);

    /**
     *
     * @param example
     */
    @SelectProvider(type=LogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CREATER", property="creater", jdbcType=JdbcType.VARCHAR),
        @Result(column="REQUEST_URL", property="requestUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="REQUEST_TIME", property="requestTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REQUEST_METHOD", property="requestMethod", jdbcType=JdbcType.VARCHAR),
        @Result(column="REQUEST_PARAM", property="requestParam", jdbcType=JdbcType.VARCHAR),
        @Result(column="EXECUTE_TIME", property="executeTime", jdbcType=JdbcType.INTEGER),
        @Result(column="HTTP_CODE", property="httpCode", jdbcType=JdbcType.INTEGER),
        @Result(column="USER_AGENT", property="userAgent", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLIENT_IP", property="clientIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="DESCRIPTION", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="ERROR_MESSAGE", property="errorMessage", jdbcType=JdbcType.VARCHAR),
        @Result(column="OPERATE_TYPE", property="operateType", jdbcType=JdbcType.INTEGER),
        @Result(column="MOUDLE_NAME", property="moudleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_NAME", property="menuName", jdbcType=JdbcType.VARCHAR)
    })
    List<Log> selectByExample(LogQuery example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    @Select({
        "select",
        "ID, CREATER, REQUEST_URL, REQUEST_TIME, REQUEST_METHOD, REQUEST_PARAM, EXECUTE_TIME, ",
        "HTTP_CODE, USER_AGENT, CLIENT_IP, DESCRIPTION, ERROR_MESSAGE, OPERATE_TYPE, ",
        "MOUDLE_NAME, MENU_NAME",
        "from log",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CREATER", property="creater", jdbcType=JdbcType.VARCHAR),
        @Result(column="REQUEST_URL", property="requestUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="REQUEST_TIME", property="requestTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REQUEST_METHOD", property="requestMethod", jdbcType=JdbcType.VARCHAR),
        @Result(column="REQUEST_PARAM", property="requestParam", jdbcType=JdbcType.VARCHAR),
        @Result(column="EXECUTE_TIME", property="executeTime", jdbcType=JdbcType.INTEGER),
        @Result(column="HTTP_CODE", property="httpCode", jdbcType=JdbcType.INTEGER),
        @Result(column="USER_AGENT", property="userAgent", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLIENT_IP", property="clientIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="DESCRIPTION", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="ERROR_MESSAGE", property="errorMessage", jdbcType=JdbcType.VARCHAR),
        @Result(column="OPERATE_TYPE", property="operateType", jdbcType=JdbcType.INTEGER),
        @Result(column="MOUDLE_NAME", property="moudleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_NAME", property="menuName", jdbcType=JdbcType.VARCHAR)
    })
    Log selectByPrimaryKey(Integer id);

    /**
     *
     * @param record
     * @param example
     */
    @UpdateProvider(type=LogSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Log record, @Param("example") LogQuery example);

    /**
     *
     * @param record
     * @param example
     */
    @UpdateProvider(type=LogSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Log record, @Param("example") LogQuery example);

    /**
     *
     * @param record
     */
    @UpdateProvider(type=LogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Log record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    @Update({
        "update log",
        "set CREATER = #{creater,jdbcType=VARCHAR},",
          "REQUEST_URL = #{requestUrl,jdbcType=VARCHAR},",
          "REQUEST_TIME = #{requestTime,jdbcType=TIMESTAMP},",
          "REQUEST_METHOD = #{requestMethod,jdbcType=VARCHAR},",
          "REQUEST_PARAM = #{requestParam,jdbcType=VARCHAR},",
          "EXECUTE_TIME = #{executeTime,jdbcType=INTEGER},",
          "HTTP_CODE = #{httpCode,jdbcType=INTEGER},",
          "USER_AGENT = #{userAgent,jdbcType=VARCHAR},",
          "CLIENT_IP = #{clientIp,jdbcType=VARCHAR},",
          "DESCRIPTION = #{description,jdbcType=VARCHAR},",
          "ERROR_MESSAGE = #{errorMessage,jdbcType=VARCHAR},",
          "OPERATE_TYPE = #{operateType,jdbcType=INTEGER},",
          "MOUDLE_NAME = #{moudleName,jdbcType=VARCHAR},",
          "MENU_NAME = #{menuName,jdbcType=VARCHAR}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Log record);
}