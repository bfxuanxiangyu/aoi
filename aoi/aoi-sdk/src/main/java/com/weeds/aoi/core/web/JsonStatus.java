package com.weeds.aoi.core.web;

import java.io.Serializable;

/**
 * 
 * json页面返回对象
 * @author user
 *
 */
public class JsonStatus implements Serializable {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1183067331227947474L;

	private boolean flag;
	
	private Object data;//结果集
	
	private String message;//描述
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
