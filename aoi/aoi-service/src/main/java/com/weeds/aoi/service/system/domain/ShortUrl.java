package com.weeds.aoi.service.system.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat; 
/**
 * short_url 实体类
 * 短连接表
 * 由GenEntityMysql类自动生成
 * Wed Sep 26 16:20:27 CST 2018
 * @xuanxy
 */ 
@Entity
@Table(name="short_url")
public class ShortUrl {

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
	* 原文地址
	*/ 
	@Column(name="url")
	private String url;

	/**
	* 短连接地址
	*/ 
	@Column(name="short_url")
	private String shortUrl;

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

	public String getUrl(){
		return url;
	}

	public void setUrl(String url){
		this.url=url;
	}

	public String getShortUrl(){
		return shortUrl;
	}

	public void setShortUrl(String shortUrl){
		this.shortUrl=shortUrl;
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

