package com.weeds.aoi.service.sinnren.domain;

import java.util.Date;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat; 
/**
 * sinnren_log 实体类
 * 由GenEntityMysql类自动生成
 * Fri Jul 20 11:37:54 CST 2018
 * @hanyf
 */ 
@Entity
@Table(name="sinnren_log")
public class SinnrenLog {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;

	@Column(name="create_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;

	@Column(name="ip")
	private String ip;

	@Column(name="username")
	private String username;

	@Column(name="address")
	private String address;

	@Column(name="remark")
	private String remark;

	/**
	* 国家
	*/ 
	@Column(name="country")
	private String country;

	/**
	* 省会
	*/ 
	@Column(name="region")
	private String region;

	/**
	* 城市
	*/ 
	@Column(name="city")
	private String city;

	/**
	* 运营商
	*/ 
	@Column(name="isp")
	private String isp;

	/**
	* 区域
	*/ 
	@Column(name="area")
	private String area;

	/**
	* 版本
	*/ 
	@Column(name="version_s")
	private String versionS;


	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	public Date getCreateTime(){
		return createTime;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public String getIp(){
		return ip;
	}

	public void setIp(String ip){
		this.ip=ip;
	}

	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username=username;
	}

	public String getAddress(){
		return address;
	}

	public void setAddress(String address){
		this.address=address;
	}

	public String getRemark(){
		return remark;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	public String getCountry(){
		return country;
	}

	public void setCountry(String country){
		this.country=country;
	}

	public String getRegion(){
		return region;
	}

	public void setRegion(String region){
		this.region=region;
	}

	public String getCity(){
		return city;
	}

	public void setCity(String city){
		this.city=city;
	}

	public String getIsp(){
		return isp;
	}

	public void setIsp(String isp){
		this.isp=isp;
	}

	public String getArea(){
		return area;
	}

	public void setArea(String area){
		this.area=area;
	}

	public String getVersionS(){
		return versionS;
	}

	public void setVersionS(String versionS){
		this.versionS=versionS;
	}

}

