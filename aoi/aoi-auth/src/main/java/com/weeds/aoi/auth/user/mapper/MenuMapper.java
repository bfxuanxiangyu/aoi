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

import com.weeds.aoi.auth.user.domain.Menu;

@Mapper
public interface MenuMapper {
	
    @Delete({
        "delete from menu",
        "where MENU_ID = #{menuId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer menuId);

    @Insert({
        "insert into menu (MENU_ID, MENU_NAME, ",
        "PARENT_NAME, ACTION, ",
        "ODER_BY, STATUS, AREA, ",
        "ICON, CREATOR, MENU_TITLE,MENU_FUNCTION , MENU_STYLE, MENU_STYLE_CLASS)",
        "values (#{menuId,jdbcType=INTEGER}, #{menuName,jdbcType=VARCHAR}, ",
        "#{parentName,jdbcType=VARCHAR}, #{action,jdbcType=VARCHAR}, ",
        "#{oderBy,jdbcType=INTEGER}, #{status,jdbcType=INTEGER}, #{area,jdbcType=VARCHAR}, ",
        "#{icon,jdbcType=VARCHAR}, #{creator,jdbcType=VARCHAR},#{menuTitle,jdbcType=VARCHAR},",
        "#{menuFunction,jdbcType=VARCHAR}, #{menuStyle,jdbcType=VARCHAR}, #{menuStyleClass,jdbcType=VARCHAR})"
    })
    int insert(Menu record);

    @Select({
        "select",
        "MENU_ID, MENU_NAME, PARENT_NAME, ACTION, ODER_BY, STATUS, AREA, ICON, CREATE_TIME, CREATOR, MENU_TITLE, MENU_FUNCTION , MENU_STYLE, MENU_STYLE_CLASS",
        "from menu",
        "where MENU_NAME = #{menuName,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="MENU_ID", property="menuId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="MENU_NAME", property="menuName", jdbcType=JdbcType.VARCHAR),
        @Result(column="PARENT_NAME", property="parentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="ACTION", property="action", jdbcType=JdbcType.VARCHAR),
        @Result(column="ODER_BY", property="oderBy", jdbcType=JdbcType.INTEGER),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="AREA", property="area", jdbcType=JdbcType.VARCHAR),
        @Result(column="ICON", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATOR", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_TITLE", property="menuTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_FUNCTION", property="menuFunction", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_STYLE", property="menuStyle", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_STYLE_CLASS", property="menuStyleClass", jdbcType=JdbcType.VARCHAR)
    })
    Menu selectByMenuName(String menuName);
    
    @Select({
    	"select",
    	"MENU_ID, MENU_NAME, PARENT_NAME, ACTION, ODER_BY, STATUS, AREA, ICON, CREATE_TIME, CREATOR, MENU_TITLE, MENU_FUNCTION , MENU_STYLE, MENU_STYLE_CLASS",
    	"from menu ",
    	"where PARENT_NAME = #{parentName,jdbcType=VARCHAR} and STATUS != 2"
    })
    @Results({
    	@Result(column="MENU_ID", property="menuId", jdbcType=JdbcType.INTEGER, id=true),
    	@Result(column="MENU_NAME", property="menuName", jdbcType=JdbcType.VARCHAR),
    	@Result(column="PARENT_NAME", property="parentName", jdbcType=JdbcType.VARCHAR),
    	@Result(column="ACTION", property="action", jdbcType=JdbcType.VARCHAR),
    	@Result(column="ODER_BY", property="oderBy", jdbcType=JdbcType.INTEGER),
    	@Result(column="STATUS", property="status", jdbcType=JdbcType.INTEGER),
    	@Result(column="AREA", property="area", jdbcType=JdbcType.VARCHAR),
    	@Result(column="ICON", property="icon", jdbcType=JdbcType.VARCHAR),
    	@Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
    	@Result(column="CREATOR", property="creator", jdbcType=JdbcType.VARCHAR),
    	@Result(column="MENU_TITLE", property="menuTitle", jdbcType=JdbcType.VARCHAR),
    	@Result(column="MENU_FUNCTION", property="menuFunction", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_STYLE", property="menuStyle", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_STYLE_CLASS", property="menuStyleClass", jdbcType=JdbcType.VARCHAR)
    })
    List<Menu> selectSubmenu(String parentName);
    
    @Select({
        "select",
        "MENU_ID, MENU_NAME, PARENT_NAME, ACTION, ODER_BY, STATUS, AREA, ICON, CREATE_TIME, CREATOR, MENU_TITLE, MENU_FUNCTION , MENU_STYLE, MENU_STYLE_CLASS",
        "from menu ",
        "where PARENT_NAME = #{parentName,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="MENU_ID", property="menuId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="MENU_NAME", property="menuName", jdbcType=JdbcType.VARCHAR),
        @Result(column="PARENT_NAME", property="parentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="ACTION", property="action", jdbcType=JdbcType.VARCHAR),
        @Result(column="ODER_BY", property="oderBy", jdbcType=JdbcType.INTEGER),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="AREA", property="area", jdbcType=JdbcType.VARCHAR),
        @Result(column="ICON", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATOR", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_TITLE", property="menuTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_FUNCTION", property="menuFunction", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_STYLE", property="menuStyle", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_STYLE_CLASS", property="menuStyleClass", jdbcType=JdbcType.VARCHAR)
    })
    List<Menu> selectSubmenuAll(String parentName);
 
    @Select({
        "select",
        "MENU_ID, MENU_NAME, PARENT_NAME, ACTION, ODER_BY, STATUS, AREA, ICON, CREATE_TIME, CREATOR, MENU_TITLE, MENU_FUNCTION , MENU_STYLE, MENU_STYLE_CLASS",
        "from menu where STATUS != 2"
    })
    @Results({
        @Result(column="MENU_ID", property="menuId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="MENU_NAME", property="menuName", jdbcType=JdbcType.VARCHAR),
        @Result(column="PARENT_NAME", property="parentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="ACTION", property="action", jdbcType=JdbcType.VARCHAR),
        @Result(column="ODER_BY", property="oderBy", jdbcType=JdbcType.INTEGER),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="AREA", property="area", jdbcType=JdbcType.VARCHAR),
        @Result(column="ICON", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATOR", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_TITLE", property="menuTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_FUNCTION", property="menuFunction", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_STYLE", property="menuStyle", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_STYLE_CLASS", property="menuStyleClass", jdbcType=JdbcType.VARCHAR)
    })
    List<Menu> selectAll();

    @Update({
        "update menu",
        "set PARENT_NAME = #{parentName,jdbcType=VARCHAR},",
          "ACTION = #{action,jdbcType=VARCHAR},",
          "ODER_BY = #{oderBy,jdbcType=INTEGER},",
          "STATUS = #{status,jdbcType=INTEGER},",
          "AREA = #{area,jdbcType=VARCHAR},",
          "ICON = #{icon,jdbcType=VARCHAR},",
          "MENU_FUNCTION = #{menuFunction,jdbcType=VARCHAR},",
          "MENU_STYLE = #{menuStyle,jdbcType=VARCHAR},",
          "MENU_STYLE_CLASS = #{menuStyleClass,jdbcType=VARCHAR}",
        "where MENU_ID = #{menuId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Menu record);
    
    @Select({
    	"select m.* from ",
    	"(select rolem.* from (select ROLE_ID from user_roles where USER_ID = #{userId, jdbcType=INTEGER})",
    	"as ur inner join role_menus as rolem on ur.ROLE_ID=rolem.ROLE_ID) as urm left join menu as m on urm.MENU_ID = m.MENU_ID where m.STATUS != 2"
    })
    @Results({
        @Result(column="MENU_ID", property="menuId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="MENU_NAME", property="menuName", jdbcType=JdbcType.VARCHAR),
        @Result(column="PARENT_NAME", property="parentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="ACTION", property="action", jdbcType=JdbcType.VARCHAR),
        @Result(column="ODER_BY", property="oderBy", jdbcType=JdbcType.INTEGER),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="AREA", property="area", jdbcType=JdbcType.VARCHAR),
        @Result(column="ICON", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATOR", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_TITLE", property="menuTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_FUNCTION", property="menuFunction", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_STYLE", property="menuStyle", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_STYLE_CLASS", property="menuStyleClass", jdbcType=JdbcType.VARCHAR)
    })
    List<Menu> selectMenusByUserId(Integer userId);
    
    @Select({
    	"select m.* from",
    	"(select * from role_menus where ROLE_ID=#{roleId,jdbcType=INTEGER} ) as rm inner join  menu as m on rm.MENU_ID=m.MENU_ID"

    })
    @Results({
        @Result(column="MENU_ID", property="menuId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="MENU_NAME", property="menuName", jdbcType=JdbcType.VARCHAR),
        @Result(column="PARENT_NAME", property="parentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="ACTION", property="action", jdbcType=JdbcType.VARCHAR),
        @Result(column="ODER_BY", property="oderBy", jdbcType=JdbcType.INTEGER),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="AREA", property="area", jdbcType=JdbcType.VARCHAR),
        @Result(column="ICON", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATOR", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_TITLE", property="menuTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_FUNCTION", property="menuFunction", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_STYLE", property="menuStyle", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_STYLE_CLASS", property="menuStyleClass", jdbcType=JdbcType.VARCHAR)
    })
    List<Menu> selectMenusByRoleId(Integer roleId);
    
    @Delete({
        "delete from menu",
        "where MENU_NAME = #{MENU_NAME,jdbcType=VARCHAR} OR PARENT_NAME=#{MENU_NAME,jdbcType=VARCHAR}"
    })
    int deleteByMenuName(String menuName);
    
}