package com.weeds.aoi.service.system.domain;

import java.util.Date;
import java.sql.*;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat; 
/**
 * short_string 实体类
 * 短连接表
 * 由GenEntityMysql类自动生成
 * Fri Sep 28 10:47:52 CST 2018
 * @xuanxy
 */ 
@Entity
@Table(name="short_string")
public class ShortString {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;

	/**
	* 创建日期
	*/ 
	@Column(name="create_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;

	/**
	* 用户id
	*/ 
	@Column(name="user_id")
	private String userId;

	/**
	* 原文
	*/ 
	@Column(name="content")
	private String content;

	/**
	* 短连接地址
	*/ 
	@Column(name="short_string")
	private String shortString;

	/**
	* 用户id
	*/ 
	@Column(name="app_id")
	private String appId;

	/**
	* 用户密钥
	*/ 
	@Column(name="app_secret")
	private String appSecret;


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

	public String getUserId(){
		return userId;
	}

	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content=content;
	}

	public String getShortString(){
		return shortString;
	}

	public void setShortString(String shortString){
		this.shortString=shortString;
	}

	public String getAppId(){
		return appId;
	}

	public void setAppId(String appId){
		this.appId=appId;
	}

	public String getAppSecret(){
		return appSecret;
	}

	public void setAppSecret(String appSecret){
		this.appSecret=appSecret;
	}

}

