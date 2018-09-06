package com.weeds.aoi.auth.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.weeds.aoi.auth.user.domain.Role;
import com.weeds.aoi.auth.user.domain.RolesUser;

@Mapper
public interface RoleMapper {
    @Delete({
        "delete from role",
        "where ROLE_ID = #{roleId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer roleId);

    @Insert({
        "insert into role (ROLE_ID, ROLE_NAME, ",
        "ROLE_TITLE, ",
        "REMARK, CREATOR)",
        "values (#{roleId,jdbcType=INTEGER}, #{roleName,jdbcType=VARCHAR}, ",
        "#{roleTitle,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, #{creator,jdbcType=VARCHAR})"
    })
    int insert(Role record);

    @Select({
        "select",
        "ROLE_ID, ROLE_NAME, ROLE_TITLE, CREATE_TIME, REMARK, CREATOR",
        "from role",
        "where ROLE_ID = #{roleId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ROLE_NAME", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="ROLE_TITLE", property="roleTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATOR", property="creator", jdbcType=JdbcType.VARCHAR)
    })
    Role selectByPrimaryKey(Integer roleId);
    
    @Select({
        "select",
        "ROLE_ID, ROLE_NAME, ROLE_TITLE, CREATE_TIME, REMARK, CREATOR",
        "from role"
    })
    @Results({
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ROLE_NAME", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="ROLE_TITLE", property="roleTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATOR", property="creator", jdbcType=JdbcType.VARCHAR)
    })
    List<Role> selectAll();
    
    @Select({
    	"SELECT r.*, ur.USER_ID, (CASE  WHEN ur.USER_ID is null THEN 0 ELSE 1 END) HAS_ROLE",
    	"FROM role r left join (select * from user_roles where USER_ID = #{userId,jdbcType=INTEGER}) ur",
    	"on r.ROLE_ID = ur.ROLE_ID"
    })
    @Results({
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ROLE_NAME", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="ROLE_TITLE", property="roleTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="HAS_ROLE", property="hasRole", jdbcType=JdbcType.BOOLEAN),
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.INTEGER)
    })
    List<RolesUser> selectRoleUsersByUserId(Integer userId);
    

    @Update({
        "update role",
        "set ROLE_NAME = #{roleName,jdbcType=VARCHAR},",
          "ROLE_TITLE = #{roleTitle,jdbcType=VARCHAR},",
          "REMARK = #{remark,jdbcType=VARCHAR}",
        "where ROLE_ID = #{roleId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Role role);
    
    @Select({
    	"select r.* from",
    	"(select * from user_roles where USER_ID=#{userId,jdbcType=INTEGER}) as ur left join role as r on ur.ROLE_ID=r.ROLE_ID"
    })
    @Results({
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ROLE_NAME", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="ROLE_TITLE", property="roleTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATOR", property="creator", jdbcType=JdbcType.VARCHAR)
    })
    List<Role> selectRolesByUserId(Integer userId);
}