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
package com.weeds.aoi.auth.log;

/**
 * @author Jetory
 * @date 2017年10月16日 下午1:31:20	
 */
public enum OperateType {

	ADD(1), DELETE(2), UPDATE(3), QUERY(4), LOGIN(98), OTHER(99);
	
	private int type;
	OperateType(int type){
		this.type = type;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
}
