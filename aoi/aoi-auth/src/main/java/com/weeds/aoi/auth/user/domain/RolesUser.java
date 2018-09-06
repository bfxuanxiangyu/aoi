package com.weeds.aoi.auth.user.domain;

public class RolesUser extends Role{
	
    private Integer userId;
    
    private Boolean hasRole;

	/**
	 * userId.
	 * @return  the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param   userId  the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * hasRole.
	 * @return  the hasRole
	 */
	public Boolean getHasRole() {
		return hasRole;
	}

	/**
	 * @param   hasRole  the hasRole to set
	 */
	public void setHasRole(Boolean hasRole) {
		this.hasRole = hasRole;
	}
    
    
    
}