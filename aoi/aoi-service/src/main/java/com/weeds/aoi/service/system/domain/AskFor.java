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
 * ask_for 实体类
 * 由GenEntityMysql类自动生成
 * Mon Aug 27 15:23:22 CST 2018
 * @xuanxy
 */ 
@Entity
@Table(name="ask_for")
public class AskFor {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;

	/**
	* 创建时间
	*/ 
	@Column(name="create_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;

	/**
	* 其他联系方式
	*/ 
	@Column(name="contact")
	private String contact;

	/**
	* 手机
	*/ 
	@Column(name="phone")
	private String phone;

	/**
	* 姓名
	*/ 
	@Column(name="name")
	private String name;

	/**
	* 邮箱
	*/ 
	@Column(name="email")
	private String email;

	/**
	* 索要内容
	*/ 
	@Column(name="content")
	private String content;


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

	public String getContact(){
		return contact;
	}

	public void setContact(String contact){
		this.contact=contact;
	}

	public String getPhone(){
		return phone;
	}

	public void setPhone(String phone){
		this.phone=phone;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content=content;
	}

}

