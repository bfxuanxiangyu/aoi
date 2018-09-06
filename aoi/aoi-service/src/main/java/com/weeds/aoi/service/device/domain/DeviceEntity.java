package com.weeds.aoi.service.device.domain;

public class DeviceEntity {

	private String userId;// 用户id
	private String deviceId;// 设备唯一ID号
	private String version;// 操作系统版本号
	private String osName;// 操作系统名称 ....
	private String devicetype;// 0 android、1 ios.pad ....
	private String model;// 终端型号iphone,itouch,ipad
	private String appId;// 应用ID
	private String appVer;// 当前APP的版本号
	private String lang;// 当前用户使用的语言

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppVer() {
		return appVer;
	}

	public void setAppVer(String appVer) {
		this.appVer = appVer;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
