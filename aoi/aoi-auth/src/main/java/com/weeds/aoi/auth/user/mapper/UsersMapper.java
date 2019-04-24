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
        "where USER_ID = #{userId}"
    })
    int deleteByPrimaryKey(Integer userId);

    @Insert({
        "insert into users (USER_NAME,USER_PWD, PHONE,EMAIL, NAME, nationality_code, province,city,IDCARD, ACTION, ",
        "UPDATE_TIME, STATUS, CREATOR,trade_type,business,member_type)",
        "values "
        + "(#{userName},#{userPwd}, #{phone},#{email}, #{name}, #{nationalityCode}, #{province},#{city},#{idcard}, #{action}, ",
        "#{updateTime}, #{status}, #{creator},#{tradeType},#{business},#{memberType})"
    })
    @SelectKey(statement="select USER_ID from users where USER_NAME = #{userName}", keyProperty="userId", before=false, resultType=int.class)
    int insert(Users record);
    
    @Select({
        "select",
        "USER_ID,CREATE_TIME, UPDATE_TIME, USER_NAME, USER_PWD, PHONE, EMAIL, NAME,IDCARD,nationality_code,province,city,",
        "ACTION,LOGIN_LAST, LOGIN_TOTAL, USER_TYPE,STATUS, CREATOR,trade_type,business,member_type ",
        "from users",
        "where USER_ID = #{userId}"
    })
    @Results({
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="USER_NAME", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="USER_PWD", property="userPwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="PHONE", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="EMAIL", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="IDCARD", property="idcard", jdbcType=JdbcType.VARCHAR),
        @Result(column="nationality_code", property="nationalityCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="ACTION", property="action", jdbcType=JdbcType.VARCHAR),
        @Result(column="LOGIN_LAST", property="loginLast", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOGIN_TOTAL", property="loginTotal", jdbcType=JdbcType.INTEGER),
        @Result(column="USER_TYPE", property="userType", jdbcType=JdbcType.INTEGER),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATOR", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="trade_type", property="tradeType", jdbcType=JdbcType.INTEGER),
        @Result(column="business", property="business", jdbcType=JdbcType.VARCHAR),
        @Result(column="member_type", property="memberType", jdbcType=JdbcType.INTEGER)
    })
    Users selectByPrimaryKey(Integer userId);
    
    @Select({
        "select",
        "USER_ID,CREATE_TIME, UPDATE_TIME, USER_NAME, USER_PWD, PHONE, EMAIL, NAME,IDCARD,nationality_code,province,city,",
        "ACTION,LOGIN_LAST, LOGIN_TOTAL, USER_TYPE,STATUS, CREATOR,trade_type,business,member_type ",
        "from users",
        "where USER_NAME = #{userName}"
    })
    @Results({
    	@Result(column="USER_ID", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="USER_NAME", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="USER_PWD", property="userPwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="PHONE", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="EMAIL", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="IDCARD", property="idcard", jdbcType=JdbcType.VARCHAR),
        @Result(column="nationality_code", property="nationalityCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="ACTION", property="action", jdbcType=JdbcType.VARCHAR),
        @Result(column="LOGIN_LAST", property="loginLast", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOGIN_TOTAL", property="loginTotal", jdbcType=JdbcType.INTEGER),
        @Result(column="USER_TYPE", property="userType", jdbcType=JdbcType.INTEGER),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATOR", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="trade_type", property="tradeType", jdbcType=JdbcType.INTEGER),
        @Result(column="business", property="business", jdbcType=JdbcType.VARCHAR),
        @Result(column="member_type", property="memberType", jdbcType=JdbcType.INTEGER)
    })
    Users selectByUserName(String userName);

    @Select({
        "select",
        "USER_ID,CREATE_TIME, UPDATE_TIME, USER_NAME, USER_PWD, PHONE, EMAIL, NAME,IDCARD,nationality_code,province,city,",
        "ACTION,LOGIN_LAST, LOGIN_TOTAL, USER_TYPE,STATUS, CREATOR,trade_type,business,member_type ",
        "from users where USER_TYPE != 1"
    })
    @Results({
    	@Result(column="USER_ID", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="USER_NAME", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="USER_PWD", property="userPwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="PHONE", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="EMAIL", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="IDCARD", property="idcard", jdbcType=JdbcType.VARCHAR),
        @Result(column="nationality_code", property="nationalityCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="ACTION", property="action", jdbcType=JdbcType.VARCHAR),
        @Result(column="LOGIN_LAST", property="loginLast", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LOGIN_TOTAL", property="loginTotal", jdbcType=JdbcType.INTEGER),
        @Result(column="USER_TYPE", property="userType", jdbcType=JdbcType.INTEGER),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATOR", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="trade_type", property="tradeType", jdbcType=JdbcType.INTEGER),
        @Result(column="business", property="business", jdbcType=JdbcType.VARCHAR),
        @Result(column="member_type", property="memberType", jdbcType=JdbcType.INTEGER)
    })
    List<Users> selectAll();

    @Update({
        "update users",
        "set PHONE = #{phone},",
          "EMAIL = #{email},",
          "NAME = #{name},",
          "USER_PWD = #{userPwd},",
          "nationality_code = #{nationalityCode},",
          "province = #{province},",
          "city = #{city},",
          "IDCARD = #{idcard},",
          "ACTION = #{action},",
          "UPDATE_TIME = #{updateTime},",
          "member_type = #{memberType},",
          "business = #{business},",
          "trade_type = #{tradeType} ",
        "where USER_ID = #{userId}"
    })
    int updateByPrimaryKey(Users record);
    
    @Update({
        "update users",
        "set LOGIN_TOTAL = #{loginTotal},",
          "LOGIN_LAST = #{loginLast}",
        "where USER_ID = #{userId}"
    })
    int updateUserLastByPrimaryKey(Users record);
    
}