package com.weeds.aoi.auth.user.mapper;

import com.weeds.aoi.auth.user.domain.Police;
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
public interface PoliceMapper {
    @Delete({
        "delete from police",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into police (ID, POLICE_CODE, ",
        "POLICE_NAME, POLICE_NAME_PINYIN, ",
        "P_POLICE_CODE, REMARK, ",
        "AREA_CODE, SORT, ",
        "FLAG, POINT_X, POINT_Y)",
        "values (#{id,jdbcType=INTEGER}, #{policeCode,jdbcType=VARCHAR}, ",
        "#{policeName,jdbcType=VARCHAR}, #{policeNamePinyin,jdbcType=VARCHAR}, ",
        "#{pPoliceCode,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, ",
        "#{areaCode,jdbcType=VARCHAR}, #{sort,jdbcType=INTEGER}, ",
        "#{flag,jdbcType=INTEGER}, #{pointX,jdbcType=VARCHAR}, #{pointY,jdbcType=VARCHAR})"
    })
    int insert(Police record);

    @Select({
        "select",
        "ID, POLICE_CODE, POLICE_NAME, POLICE_NAME_PINYIN, P_POLICE_CODE, REMARK, AREA_CODE, ",
        "SORT, FLAG, POINT_X, POINT_Y",
        "from police",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="POLICE_CODE", property="policeCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="POLICE_NAME", property="policeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="POLICE_NAME_PINYIN", property="policeNamePinyin", jdbcType=JdbcType.VARCHAR),
        @Result(column="P_POLICE_CODE", property="pPoliceCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="AREA_CODE", property="areaCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="SORT", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="FLAG", property="flag", jdbcType=JdbcType.INTEGER),
        @Result(column="POINT_X", property="pointX", jdbcType=JdbcType.VARCHAR),
        @Result(column="POINT_Y", property="pointY", jdbcType=JdbcType.VARCHAR)
    })
    Police selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "ID, POLICE_CODE, POLICE_NAME, POLICE_NAME_PINYIN, P_POLICE_CODE, REMARK, AREA_CODE, ",
        "SORT, FLAG, POINT_X, POINT_Y",
        "from police"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="POLICE_CODE", property="policeCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="POLICE_NAME", property="policeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="POLICE_NAME_PINYIN", property="policeNamePinyin", jdbcType=JdbcType.VARCHAR),
        @Result(column="P_POLICE_CODE", property="pPoliceCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="AREA_CODE", property="areaCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="SORT", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="FLAG", property="flag", jdbcType=JdbcType.INTEGER),
        @Result(column="POINT_X", property="pointX", jdbcType=JdbcType.VARCHAR),
        @Result(column="POINT_Y", property="pointY", jdbcType=JdbcType.VARCHAR)
    })
    List<Police> selectAll();
    @Select({
    	"select",
    	"ID, POLICE_CODE, POLICE_NAME, POLICE_NAME_PINYIN, P_POLICE_CODE, REMARK, AREA_CODE, ",
    	"SORT, FLAG, POINT_X, POINT_Y",
    	"from police where AREA_CODE=  #{areaCode,jdbcType=INTEGER}"
    })
    @Results({
    	@Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
    	@Result(column="POLICE_CODE", property="policeCode", jdbcType=JdbcType.VARCHAR),
    	@Result(column="POLICE_NAME", property="policeName", jdbcType=JdbcType.VARCHAR),
    	@Result(column="POLICE_NAME_PINYIN", property="policeNamePinyin", jdbcType=JdbcType.VARCHAR),
    	@Result(column="P_POLICE_CODE", property="pPoliceCode", jdbcType=JdbcType.VARCHAR),
    	@Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
    	@Result(column="AREA_CODE", property="areaCode", jdbcType=JdbcType.VARCHAR),
    	@Result(column="SORT", property="sort", jdbcType=JdbcType.INTEGER),
    	@Result(column="FLAG", property="flag", jdbcType=JdbcType.INTEGER),
    	@Result(column="POINT_X", property="pointX", jdbcType=JdbcType.VARCHAR),
    	@Result(column="POINT_Y", property="pointY", jdbcType=JdbcType.VARCHAR)
    })
    List<Police> selectAllByAreaCode(String areaCode);
    @Select({
    	"select",
    	"ID, POLICE_CODE, POLICE_NAME, POLICE_NAME_PINYIN, P_POLICE_CODE, REMARK, AREA_CODE, ",
    	"SORT, FLAG, POINT_X, POINT_Y",
    	"from police where P_POLICE_CODE=  #{pPoliceCode,jdbcType=INTEGER}"
    })
    @Results({
    	@Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
    	@Result(column="POLICE_CODE", property="policeCode", jdbcType=JdbcType.VARCHAR),
    	@Result(column="POLICE_NAME", property="policeName", jdbcType=JdbcType.VARCHAR),
    	@Result(column="POLICE_NAME_PINYIN", property="policeNamePinyin", jdbcType=JdbcType.VARCHAR),
    	@Result(column="P_POLICE_CODE", property="pPoliceCode", jdbcType=JdbcType.VARCHAR),
    	@Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
    	@Result(column="AREA_CODE", property="areaCode", jdbcType=JdbcType.VARCHAR),
    	@Result(column="SORT", property="sort", jdbcType=JdbcType.INTEGER),
    	@Result(column="FLAG", property="flag", jdbcType=JdbcType.INTEGER),
    	@Result(column="POINT_X", property="pointX", jdbcType=JdbcType.VARCHAR),
    	@Result(column="POINT_Y", property="pointY", jdbcType=JdbcType.VARCHAR)
    })
    List<Police> selectAllByPPoliceCode(String pPoliceCode);
    @Select({
    	"select",
    	"ID, POLICE_CODE, POLICE_NAME, POLICE_NAME_PINYIN, P_POLICE_CODE, REMARK, AREA_CODE, ",
    	"SORT, FLAG, POINT_X, POINT_Y",
    	"from police where POLICE_CODE=  #{policeCode,jdbcType=INTEGER} limit 1"
    })
    @Results({
    	@Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
    	@Result(column="POLICE_CODE", property="policeCode", jdbcType=JdbcType.VARCHAR),
    	@Result(column="POLICE_NAME", property="policeName", jdbcType=JdbcType.VARCHAR),
    	@Result(column="POLICE_NAME_PINYIN", property="policeNamePinyin", jdbcType=JdbcType.VARCHAR),
    	@Result(column="P_POLICE_CODE", property="pPoliceCode", jdbcType=JdbcType.VARCHAR),
    	@Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
    	@Result(column="AREA_CODE", property="areaCode", jdbcType=JdbcType.VARCHAR),
    	@Result(column="SORT", property="sort", jdbcType=JdbcType.INTEGER),
    	@Result(column="FLAG", property="flag", jdbcType=JdbcType.INTEGER),
    	@Result(column="POINT_X", property="pointX", jdbcType=JdbcType.VARCHAR),
    	@Result(column="POINT_Y", property="pointY", jdbcType=JdbcType.VARCHAR)
    })
    Police selectPoliceByPoliceCode(String policeCode);
    @Select({
    	"select",
    	"ID, POLICE_CODE, POLICE_NAME, POLICE_NAME_PINYIN, P_POLICE_CODE, REMARK, AREA_CODE, ",
    	"SORT, FLAG, POINT_X, POINT_Y",
    	"from police where POLICE_NAME=  #{policeName,jdbcType=INTEGER} limit 1"
    })
    @Results({
    	@Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
    	@Result(column="POLICE_CODE", property="policeCode", jdbcType=JdbcType.VARCHAR),
    	@Result(column="POLICE_NAME", property="policeName", jdbcType=JdbcType.VARCHAR),
    	@Result(column="POLICE_NAME_PINYIN", property="policeNamePinyin", jdbcType=JdbcType.VARCHAR),
    	@Result(column="P_POLICE_CODE", property="pPoliceCode", jdbcType=JdbcType.VARCHAR),
    	@Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
    	@Result(column="AREA_CODE", property="areaCode", jdbcType=JdbcType.VARCHAR),
    	@Result(column="SORT", property="sort", jdbcType=JdbcType.INTEGER),
    	@Result(column="FLAG", property="flag", jdbcType=JdbcType.INTEGER),
    	@Result(column="POINT_X", property="pointX", jdbcType=JdbcType.VARCHAR),
    	@Result(column="POINT_Y", property="pointY", jdbcType=JdbcType.VARCHAR)
    })
    Police selectPoliceByPoliceName(String policeName);

    @Update({
        "update police",
        "set POLICE_CODE = #{policeCode,jdbcType=VARCHAR},",
          "POLICE_NAME = #{policeName,jdbcType=VARCHAR},",
          "POLICE_NAME_PINYIN = #{policeNamePinyin,jdbcType=VARCHAR},",
          "P_POLICE_CODE = #{pPoliceCode,jdbcType=VARCHAR},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "AREA_CODE = #{areaCode,jdbcType=VARCHAR},",
          "SORT = #{sort,jdbcType=INTEGER},",
          "FLAG = #{flag,jdbcType=INTEGER},",
          "POINT_X = #{pointX,jdbcType=VARCHAR},",
          "POINT_Y = #{pointY,jdbcType=VARCHAR}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Police record);
}