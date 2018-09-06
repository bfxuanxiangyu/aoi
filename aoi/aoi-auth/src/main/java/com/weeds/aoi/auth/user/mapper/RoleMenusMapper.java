package com.weeds.aoi.auth.user.mapper;

import com.weeds.aoi.auth.user.domain.RoleMenus;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface RoleMenusMapper {
    @Delete({
        "delete from role_menus",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);
    @Delete({
        "delete from role_menus",
        "where MENU_ID = #{menuId,jdbcType=INTEGER}"
    })
    int deleteByMenuId(Integer menuId);
    @Delete({
        "delete from role_menus",
        "where ROLE_ID = #{roleId,jdbcType=INTEGER}"
    })
    int deleteByRoleId(Integer roleId);

    @Insert({
        "insert into role_menus (ID, MENU_ID, ",
        "ROLE_ID)",
        "values (#{id,jdbcType=INTEGER}, #{menuId,jdbcType=INTEGER}, ",
        "#{roleId,jdbcType=INTEGER})"
    })
    int insert(RoleMenus record);

    @Select({
        "select",
        "ID, MENU_ID, ROLE_ID",
        "from role_menus",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="MENU_ID", property="menuId", jdbcType=JdbcType.INTEGER),
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.INTEGER)
    })
    RoleMenus selectByPrimaryKey(Integer id);
    
    @Select({
        "select",
        "ID, MENU_ID, ROLE_ID",
        "from role_menus",
        "where  ROLE_ID= #{roleId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="MENU_ID", property="menuId", jdbcType=JdbcType.INTEGER),
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.INTEGER)
    })
    List<RoleMenus> selectByRoleId(Integer roleId);
    
    @Select({
        "select",
        "ID, MENU_ID, ROLE_ID",
        "from role_menus",
        "where MENU_ID = #{menuId,jdbcType=INTEGER} and ROLE_ID = #{roleId,jdbcType=INTEGER}"
    })
    Integer selectIdByMenuIdAndRoleId(Integer menuId,Integer roleId);

    @Select({
        "select",
        "ID, MENU_ID, ROLE_ID",
        "from role_menus"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="MENU_ID", property="menuId", jdbcType=JdbcType.INTEGER),
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.INTEGER)
    })
    List<RoleMenus> selectAll();

    @Update({
        "update role_menus",
        "set MENU_ID = #{menuId,jdbcType=INTEGER},",
          "ROLE_ID = #{roleId,jdbcType=INTEGER}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RoleMenus record);
    
}