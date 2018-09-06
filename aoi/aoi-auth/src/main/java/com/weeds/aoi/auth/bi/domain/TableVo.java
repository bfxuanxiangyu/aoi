package com.weeds.aoi.auth.bi.domain;

import java.util.List;

public class TableVo {
	
	private String tableName;//表名
	
	private String Collation;//表编码
	
	private String Comment;//表注释
	
	private List<CloumnVo> cloumnList;//列集合
	
	private String jsonValue;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getCollation() {
		return Collation;
	}

	public void setCollation(String collation) {
		Collation = collation;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	public List<CloumnVo> getCloumnList() {
		return cloumnList;
	}

	public void setCloumnList(List<CloumnVo> cloumnList) {
		this.cloumnList = cloumnList;
	}

	public String getJsonValue() {
		return jsonValue;
	}

	public void setJsonValue(String jsonValue) {
		this.jsonValue = jsonValue;
	}
	
	

}
