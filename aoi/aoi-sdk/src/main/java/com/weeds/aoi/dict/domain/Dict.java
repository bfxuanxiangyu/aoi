package com.weeds.aoi.dict.domain;

import java.io.Serializable;

public class Dict implements Serializable {
    /**
	 *
	 */
	private static final long serialVersionUID = 1881750399772768519L;

	/**
     * <pre>
     * 
     * 表字段 : dict.ID
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 字典名称
     * 表字段 : dict.DICT_NAME
     * </pre>
     */
    private String dictName;

    /**
     * <pre>
     * 字典ID
     * 表字段 : dict.DICT_ID
     * </pre>
     */
    private String dictId;

    /**
     * <pre>
     * 所属类型ID
     * 表字段 : dict.TYPE_ID
     * </pre>
     */
    private Integer typeId;

    /**
     * <pre>
     * 获取：
     * 表字段：dict.ID
     * </pre>
     *
     * @return dict.ID：
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * 设置：
     * 表字段：dict.ID
     * </pre>
     *
     * @param id
     *            dict.ID：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 获取：字典名称
     * 表字段：dict.DICT_NAME
     * </pre>
     *
     * @return dict.DICT_NAME：字典名称
     */
    public String getDictName() {
        return dictName;
    }

    /**
     * <pre>
     * 设置：字典名称
     * 表字段：dict.DICT_NAME
     * </pre>
     *
     * @param dictName
     *            dict.DICT_NAME：字典名称
     */
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    /**
     * <pre>
     * 获取：字典ID
     * 表字段：dict.DICT_ID
     * </pre>
     *
     * @return dict.DICT_ID：字典ID
     */
    public String getDictId() {
        return dictId;
    }

    /**
     * <pre>
     * 设置：字典ID
     * 表字段：dict.DICT_ID
     * </pre>
     *
     * @param dictId
     *            dict.DICT_ID：字典ID
     */
    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    /**
     * <pre>
     * 获取：所属类型ID
     * 表字段：dict.TYPE_ID
     * </pre>
     *
     * @return dict.TYPE_ID：所属类型ID
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * <pre>
     * 设置：所属类型ID
     * 表字段：dict.TYPE_ID
     * </pre>
     *
     * @param typeId
     *            dict.TYPE_ID：所属类型ID
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}