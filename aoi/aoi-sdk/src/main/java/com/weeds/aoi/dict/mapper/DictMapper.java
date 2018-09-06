package com.weeds.aoi.dict.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.weeds.aoi.dict.domain.Dict;


@Mapper
public interface DictMapper {
    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    @Delete({
        "delete from dict",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    @Insert({
        "insert into dict (ID, DICT_NAME, ",
        "DICT_ID, TYPE_ID)",
        "values (#{id,jdbcType=INTEGER}, #{dictName,jdbcType=VARCHAR}, ",
        "#{dictId,jdbcType=VARCHAR}, #{typeId,jdbcType=INTEGER})"
    })
    int insert(Dict record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    @Select({
        "select",
        "ID, DICT_NAME, DICT_ID, TYPE_ID",
        "from dict",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="DICT_NAME", property="dictName", jdbcType=JdbcType.VARCHAR),
        @Result(column="DICT_ID", property="dictId", jdbcType=JdbcType.VARCHAR),
        @Result(column="TYPE_ID", property="typeId", jdbcType=JdbcType.INTEGER)
    })
    Dict selectByPrimaryKey(Integer id);

    /**
     * 获取全部数据库记录
     *
     */
    @Select({
        "select",
        "ID, DICT_NAME, DICT_ID, TYPE_ID",
        "from dict"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="DICT_NAME", property="dictName", jdbcType=JdbcType.VARCHAR),
        @Result(column="DICT_ID", property="dictId", jdbcType=JdbcType.VARCHAR),
        @Result(column="TYPE_ID", property="typeId", jdbcType=JdbcType.INTEGER)
    })
    List<Dict> selectAll();

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    @Update({
        "update dict",
        "set DICT_NAME = #{dictName,jdbcType=VARCHAR},",
          "DICT_ID = #{dictId,jdbcType=INTEGER},",
          "TYPE_ID = #{typeId,jdbcType=INTEGER}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Dict record);
    
    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    @Select({
        "select",
        "ID, DICT_NAME, DICT_ID, TYPE_ID",
        "from dict",
        "where TYPE_ID = #{typeId,jdbcType=INTEGER} AND DICT_ID= #{dictId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="DICT_NAME", property="dictName", jdbcType=JdbcType.VARCHAR),
        @Result(column="DICT_ID", property="dictId", jdbcType=JdbcType.VARCHAR),
        @Result(column="TYPE_ID", property="typeId", jdbcType=JdbcType.INTEGER)
    })
    List<Dict> selectByTypeIdDictId(Dict dict);
    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    @Select({
        "select",
        "ID, DICT_NAME, DICT_ID, TYPE_ID",
        "from dict",
        "where TYPE_ID = #{typeId,jdbcType=INTEGER} order by DICT_ID"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="DICT_NAME", property="dictName", jdbcType=JdbcType.VARCHAR),
        @Result(column="DICT_ID", property="dictId", jdbcType=JdbcType.VARCHAR),
        @Result(column="TYPE_ID", property="typeId", jdbcType=JdbcType.INTEGER)
    })
    List<Dict> selectByTypeId(Integer typeId);
    
    @Select({
    	"SELECT max(dict_Id) FROM srdb.dict where TYPE_ID= #{typeId,jdbcType=INTEGER};"
    })
    int selectMaxDictId(Integer typeId);
}