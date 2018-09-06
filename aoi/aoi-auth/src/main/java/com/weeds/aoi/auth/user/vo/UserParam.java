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
package com.weeds.aoi.auth.user.vo;

import java.io.Serializable;

import com.weeds.aoi.auth.user.domain.Users;

/**
 * @author user
 * @date 2017年9月22日 上午9:59:07	
 */
public class UserParam implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 2572412033929371888L;
	
	private Users user;
	private Integer[] roelIds;
	
	/**
	 * user.
	 * @return  the user
	 */
	public Users getUser() {
		return user;
	}
	/**
	 * @param   user  the user to set
	 */
	public void setUser(Users user) {
		this.user = user;
	}
	/**
	 * roelIds.
	 * @return  the roelIds
	 */
	public Integer[] getRoelIds() {
		return roelIds;
	}
	/**
	 * @param   roelIds  the roelIds to set
	 */
	public void setRoelIds(Integer[] roelIds) {
		this.roelIds = roelIds;
	}

}
