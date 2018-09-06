package com.weeds.aoi.service.system.domain;

import java.util.Date;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat; 
/**
 * t_system_version_info 实体类
 * 系统定义版本信息
 * 由GenEntityMysql类自动生成
 * Wed Dec 13 22:34:17 CST 2017
 * @hanyf
 */ 
@Entity
@Table(name="t_system_version_info")
public class SystemVersionInfo {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;

	/**
	* 系统版本编号（和手机端版本号一致）
	*/ 
	@Column(name="version_code")
	private Integer versionCode;

	/**
	* 系统版本名称1.1.2
	*/ 
	@Column(name="system_version")
	private String systemVersion;

	/**
	* 版本说明
	*/ 
	@Column(name="version_desc")
	private String versionDesc;

	/**
	* 版本上线时间
	*/ 
	@Column(name="create_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;

	/**
	* 0 android、1 ios  2.pad
	*/ 
	@Column(name="devicetype")
	private Integer devicetype;

	/**
	* 是否强制更新
	*/ 
	@Column(name="compel")
	private String compel;

	@Column(name="plat_code")
	private String platCode;

	@Column(name="city_code")
	private String cityCode;


	public Integer getVersionCode(){
		return versionCode;
	}

	public void setVersionCode(Integer versionCode){
		this.versionCode=versionCode;
	}

	public String getSystemVersion(){
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion){
		this.systemVersion=systemVersion;
	}

	public String getVersionDesc(){
		return versionDesc;
	}

	public void setVersionDesc(String versionDesc){
		this.versionDesc=versionDesc;
	}

	public Date getCreateTime(){
		return createTime;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Integer getDevicetype(){
		return devicetype;
	}

	public void setDevicetype(Integer devicetype){
		this.devicetype=devicetype;
	}

	public String getCompel(){
		return compel;
	}

	public void setCompel(String compel){
		this.compel=compel;
	}

	public String getPlatCode(){
		return platCode;
	}

	public void setPlatCode(String platCode){
		this.platCode=platCode;
	}

	public String getCityCode(){
		return cityCode;
	}

	public void setCityCode(String cityCode){
		this.cityCode=cityCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

