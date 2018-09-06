package com.weeds.aoi.auth.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.weeds.aoi.auth.user.domain.Users;

@Mapper
public interface UsersMapper {
	
    @Delete({
        "delete from users",
        "where USER_ID = #{userId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userId);

    @Insert({
        "insert into users (USER_NAME, ",
        "USER_PWD, PHONE, ",
        "EMAIL, NAME, POLICE_CODE, AREA_CODE,",
        "IDCARD, POLICE, ",
        "ACTION, ",
        "UPDATE_TIME, STATUS, CREATOR)",
        "values "
        + "(#{userName,jdbcType=VARCHAR}, ",
        "#{userPwd,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, #{policeCode,jdbcType=VARCHAR}, #{areaCode,jdbcType=VARCHAR},",
        "#{idcard,jdbcType=VARCHAR}, #{police,jdbcType=VARCHAR}, ",
        "#{action,jdbcType=VARCHAR}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{status,jdbcType=INTEGER}, #{creator,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="select USER_ID from users where USER_NAME = #{userName,jdbcType=VARCHAR}", keyProperty="userId", before=false, resultType=int.class)
    int insert(Users record);
    
    /*@Insert({"<script>",
    	"insert into users (USER_ID, USER_NAME, ",
        "USER_PWD, PHONE, ",
        "EMAIL, NAME, POLICE_CODE, ",
        "IDCARD, POLICE, ",
        "ACTION, CREATE_TIME, ",
        "UPDATE_TIME, LOGIN_LAST, ",
        "LOGIN_TOTAL)",
        "values",
        "<foreach collection='userlist' item='user'  separator=','>",
        "(#{user.userId,jdbcType=INTEGER}, #{user.userName,jdbcType=VARCHAR}, ",
        "#{user.userPwd,jdbcType=VARCHAR}, #{user.phone,jdbcType=VARCHAR}, ",
        "#{user.email,jdbcType=VARCHAR}, #{user.name,jdbcType=VARCHAR}, #{user.policeCode,jdbcType=VARCHAR}, ",
        "#{user.idcard,jdbcType=VARCHAR}, #{user.police,jdbcType=VARCHAR}, ",
        "#{user.action,jdbcType=VARCHAR}, #{user.createTime,jdbcType=TIMESTAMP}, ",
        "#{user.updateTime,jdbcType=TIMESTAMP}, #{user.loginLast,jdbcType=TIMESTAMP}, ",
        "#{user.loginTotal,jdbcType=INTEGER})",
        "</foreach>",
        "</script>"})
    int batchInsert(@Param("userlist") List<Users> userlist);*/
      
    @Select({
        "select",
        "USER_ID, USER_NAME, USER_PWD, PHONE, EMAIL, NAME, POLICE_CODE, AREA_CODE, IDCARD, POLICE, ",
        "ACTION, CREATE_TIME, UPDATE_TIME, LOGIN_LAST, LOGIN_TOTAL, STATUS, CREATOR",
        "from users",
        "where USER_ID = #{userId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="USER_NAME", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="USER_PWD", property="userPwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="PHONE", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="EMAIL", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="POLICE_CODE", property="policeCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="AREA_CODE", property="areaCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="IDCARD", property="idcard", jdbcType=JdbcType.VARCHAR),
        @Result(column="POLICE", property="police", jdbcType=JdbcType.VARCHAR),
        @Result(column="ACTION", property="action", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOGIN_LAST", property="loginLast", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOGIN_TOTAL", property="loginTotal", jdbcType=JdbcType.INTEGER),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATOR", property="creator", jdbcType=JdbcType.VARCHAR)
    })
    Users selectByPrimaryKey(Integer userId);
    
    @Select({
        "select",
        "USER_ID, USER_NAME, USER_PWD, PHONE, EMAIL, NAME, POLICE_CODE, AREA_CODE, IDCARD, POLICE, ",
        "ACTION, CREATE_TIME, UPDATE_TIME, LOGIN_LAST, LOGIN_TOTAL, USER_TYPE, STATUS, CREATOR",
        "from users",
        "where USER_NAME = #{userName,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="USER_NAME", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="USER_PWD", property="userPwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="PHONE", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="EMAIL", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="POLICE_CODE", property="policeCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="AREA_CODE", property="areaCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="IDCARD", property="idcard", jdbcType=JdbcType.VARCHAR),
        @Result(column="POLICE", property="police", jdbcType=JdbcType.VARCHAR),
        @Result(column="ACTION", property="action", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOGIN_LAST", property="loginLast", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOGIN_TOTAL", property="loginTotal", jdbcType=JdbcType.INTEGER),
        @Result(column="USER_TYPE", property="userType", jdbcType=JdbcType.INTEGER),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATOR", property="creator", jdbcType=JdbcType.VARCHAR)
    })
    Users selectByUserName(String userName);

    @Select({
        "select",
        "USER_ID, USER_NAME, USER_PWD, PHONE, EMAIL, NAME, POLICE_CODE, AREA_CODE, IDCARD, POLICE, ",
        "ACTION, CREATE_TIME, UPDATE_TIME, LOGIN_LAST, LOGIN_TOTAL, USER_TYPE",
        "from users where USER_TYPE != 1"
    })
    @Results({
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="USER_NAME", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="USER_PWD", property="userPwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="PHONE", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="EMAIL", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="POLICE_CODE", property="policeCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="AREA_CODE", property="areaCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="IDCARD", property="idcard", jdbcType=JdbcType.VARCHAR),
        @Result(column="POLICE", property="police", jdbcType=JdbcType.VARCHAR),
        @Result(column="ACTION", property="action", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOGIN_LAST", property="loginLast", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOGIN_TOTAL", property="loginTotal", jdbcType=JdbcType.INTEGER),
        @Result(column="USER_TYPE", property="userType", jdbcType=JdbcType.INTEGER)
    })
    List<Users> selectAll();

    @Update({
        "update users",
        "set PHONE = #{phone,jdbcType=VARCHAR},",
          "EMAIL = #{email,jdbcType=VARCHAR},",
          "NAME = #{name,jdbcType=VARCHAR},",
          "USER_PWD = #{userPwd,jdbcType=VARCHAR},",
          "POLICE_CODE = #{policeCode,jdbcType=VARCHAR},",
          "AREA_CODE = #{areaCode,jdbcType=VARCHAR},",
          "IDCARD = #{idcard,jdbcType=VARCHAR},",
          "POLICE = #{police,jdbcType=VARCHAR},",
          "ACTION = #{action,jdbcType=VARCHAR},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP},",
          "STATUS = #{status,jdbcType=INTEGER}",
        "where USER_ID = #{userId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Users record);
    
    @Update({
        "update users",
        "set LOGIN_TOTAL = #{loginTotal,jdbcType=INTEGER},",
          "LOGIN_LAST = #{loginLast,jdbcType=TIMESTAMP}",
        "where USER_ID = #{userId,jdbcType=INTEGER}"
    })
    int updateUserLastByPrimaryKey(Users record);
    
}