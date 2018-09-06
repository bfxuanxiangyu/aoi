package com.weeds.aoi.auth.user.mapper;

import com.weeds.aoi.auth.user.domain.UserRoles;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface UserRolesMapper {
    @Delete({
        "delete from user_roles",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);
    
    @Delete({
        "delete from user_roles",
        "where USER_ID = #{userId,jdbcType=INTEGER}"
    })
    int deleteByUserId(Integer userId);
    @Delete({
        "delete from user_roles",
        "where ROLE_ID = #{roleId,jdbcType=INTEGER}"
    })
    int deleteByRoleId(Integer roleId);
    
    
    @Insert({
        "insert into user_roles (ROLE_ID, USER_ID)",
        "values (#{roleId,jdbcType=INTEGER}, ",
        "#{userId,jdbcType=INTEGER})"
    })
    int insert(UserRoles record);
    
    
    @Insert({
    	"<script>",
        "insert into user_roles (ROLE_ID, USER_ID) values",
        "<foreach collection='recordList' item='role' separator=','>",
        "(#{role.roleId,jdbcType=INTEGER}, #{role.userId,jdbcType=INTEGER})",
        "</foreach>",
        "</script>"
    })
    int batchInsert(@Param("recordList") List<UserRoles> recordList);

    @Select({
        "select",
        "ID, ROLE_ID, USER_ID",
        "from user_roles",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.INTEGER),
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.INTEGER)
    })
    UserRoles selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "ID, ROLE_ID, USER_ID",
        "from user_roles",
        "where USER_ID = #{userId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.INTEGER),
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.INTEGER)
    })
    List<UserRoles> selectByUserId(Integer userId);
    
    @Select({
        "select",
        "ID",
        "from user_roles",
        "where ROLE_ID = #{roleId,jdbcType=INTEGER} and #{userId,jdbcType=INTEGER}"
    })
    Integer selectIdByRoleIdAndUserID(Integer roleId,Integer userId);

    @Select({
    	"select",
    	"ID, ROLE_ID, USER_ID",
    	"from user_roles"
    })
    @Results({
    	@Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
    	@Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.INTEGER),
    	@Result(column="USER_ID", property="userId", jdbcType=JdbcType.INTEGER)
    })
    List<UserRoles> selectAll();
    

    @Update({
        "update user_roles",
        "set ROLE_ID = #{roleId,jdbcType=INTEGER},",
          "USER_ID = #{userId,jdbcType=INTEGER}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserRoles record);
}