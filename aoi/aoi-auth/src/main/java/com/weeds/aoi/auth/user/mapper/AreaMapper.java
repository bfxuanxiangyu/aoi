package com.weeds.aoi.auth.user.mapper;

import com.weeds.aoi.auth.user.domain.Area;
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
public interface AreaMapper {
    @Delete({
        "delete from area",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into area (ID, AREA_CODE, ",
        "AREA_NAME, P_AREA_CODE, ",
        "REMARK, SORT, FLAG)",
        "values (#{id,jdbcType=INTEGER}, #{areaCode,jdbcType=VARCHAR}, ",
        "#{areaName,jdbcType=VARCHAR}, #{pAreaCode,jdbcType=VARCHAR}, ",
        "#{remark,jdbcType=VARCHAR}, #{sort,jdbcType=INTEGER}, #{flag,jdbcType=INTEGER})"
    })
    int insert(Area record);

    @Select({
        "select",
        "ID, AREA_CODE, AREA_NAME, P_AREA_CODE, REMARK, SORT, FLAG",
        "from area",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="AREA_CODE", property="areaCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="AREA_NAME", property="areaName", jdbcType=JdbcType.VARCHAR),
        @Result(column="P_AREA_CODE", property="pAreaCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="SORT", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="FLAG", property="flag", jdbcType=JdbcType.INTEGER)
    })
    Area selectByPrimaryKey(Integer id);
    @Select({
    	"select",
    	"ID, AREA_CODE, AREA_NAME, P_AREA_CODE, REMARK, SORT, FLAG",
    	"from area",
    	"where AREA_NAME = #{areaName,jdbcType=VARCHAR} limit 1"
    })
    @Results({
    	@Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
    	@Result(column="AREA_CODE", property="areaCode", jdbcType=JdbcType.VARCHAR),
    	@Result(column="AREA_NAME", property="areaName", jdbcType=JdbcType.VARCHAR),
    	@Result(column="P_AREA_CODE", property="pAreaCode", jdbcType=JdbcType.VARCHAR),
    	@Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
    	@Result(column="SORT", property="sort", jdbcType=JdbcType.INTEGER),
    	@Result(column="FLAG", property="flag", jdbcType=JdbcType.INTEGER)
    })
    Area selectByAreaName(String areaName);
    @Select({
    	"select",
    	"ID, AREA_CODE, AREA_NAME, P_AREA_CODE, REMARK, SORT, FLAG",
    	"from area",
    	"where AREA_CODE = #{areaCode,jdbcType=VARCHAR} limit 1"
    })
    @Results({
    	@Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
    	@Result(column="AREA_CODE", property="areaCode", jdbcType=JdbcType.VARCHAR),
    	@Result(column="AREA_NAME", property="areaName", jdbcType=JdbcType.VARCHAR),
    	@Result(column="P_AREA_CODE", property="pAreaCode", jdbcType=JdbcType.VARCHAR),
    	@Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
    	@Result(column="SORT", property="sort", jdbcType=JdbcType.INTEGER),
    	@Result(column="FLAG", property="flag", jdbcType=JdbcType.INTEGER)
    })
    Area selectAreaNameByAreaCode(String areaCode);
    @Select({
    	"select",
    	"ID, AREA_CODE, AREA_NAME, P_AREA_CODE, REMARK, SORT, FLAG",
    	"from area",
    	"where AREA_NAME = #{areaName,jdbcType=VARCHAR} limit 1"
    })
    @Results({
    	@Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
    	@Result(column="AREA_CODE", property="areaCode", jdbcType=JdbcType.VARCHAR),
    	@Result(column="AREA_NAME", property="areaName", jdbcType=JdbcType.VARCHAR),
    	@Result(column="P_AREA_CODE", property="pAreaCode", jdbcType=JdbcType.VARCHAR),
    	@Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
    	@Result(column="SORT", property="sort", jdbcType=JdbcType.INTEGER),
    	@Result(column="FLAG", property="flag", jdbcType=JdbcType.INTEGER)
    })
    Area selectAreaByAreaName(String areaName);

    @Select({
        "select",
        "ID, AREA_CODE, AREA_NAME, P_AREA_CODE, REMARK, SORT, FLAG",
        "from area"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="AREA_CODE", property="areaCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="AREA_NAME", property="areaName", jdbcType=JdbcType.VARCHAR),
        @Result(column="P_AREA_CODE", property="pAreaCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="SORT", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="FLAG", property="flag", jdbcType=JdbcType.INTEGER)
    })
    List<Area> selectAll();
    @Select({
        "select",
        "ID, AREA_CODE, AREA_NAME, P_AREA_CODE, REMARK, SORT, FLAG",
        "from area where P_AREA_CODE = #{pAreaCode,jdbcType=VARCHAR} or AREA_CODE=#{pAreaCode,jdbcType=VARCHAR}"
    })
    
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="AREA_CODE", property="areaCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="AREA_NAME", property="areaName", jdbcType=JdbcType.VARCHAR),
        @Result(column="P_AREA_CODE", property="pAreaCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="SORT", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="FLAG", property="flag", jdbcType=JdbcType.INTEGER)
    })
    List<Area> selectSubAll(String pAreaCode);

    @Update({
        "update area",
        "set AREA_CODE = #{areaCode,jdbcType=VARCHAR},",
          "AREA_NAME = #{areaName,jdbcType=VARCHAR},",
          "P_AREA_CODE = #{pAreaCode,jdbcType=VARCHAR},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "SORT = #{sort,jdbcType=INTEGER},",
          "FLAG = #{flag,jdbcType=INTEGER}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Area record);
}