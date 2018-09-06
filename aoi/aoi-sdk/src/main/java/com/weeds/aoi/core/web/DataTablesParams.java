/*
 * Copyright (C) 2017 Shanghai sinnren soft Co., Ltd
 *
 * All copyrights reserved by Shanghai sinnren.
 * Any copying, transferring or any other usage is prohibited.
 * Or else, Shanghai sinnren possesses the right to require legal 
 * responsibilities from the violator.
 * All third-party contributions are distributed under license by
 * Shanghai sinnren soft Co., Ltd.
 */
package com.weeds.aoi.core.web;

import java.io.Serializable;

/**
 * Jquery DataTables 页面参数
 * @author Jetory
 * @date 2017年10月18日 下午3:27:57	
 */
public class DataTablesParams implements Serializable {

	private static final long serialVersionUID = 7138388309335883101L;

	protected int draw;
	
	protected int start;
	
	protected int length;

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + length;
		result = prime * result + start;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataTablesParams other = (DataTablesParams) obj;
		if (length != other.length)
			return false;
		if (start != other.start)
			return false;
		return true;
	}
	
	
	
}
