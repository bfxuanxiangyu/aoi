package com.weeds.aoi.auth.bi.domain;

public class CloumnVo {
	
	private String Field;//列名
	
	private String Comment;//列注释

	private String Type;//列类型

	public String getField() {
		return Field;
	}

	public void setField(String field) {
		Field = field;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}
}
