package com.weeds.aoi.arcface.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat; 
/**
 * user_face_info 实体类
 * 由GenEntityMysql类自动生成
 * Wed Dec 19 12:40:58 CST 2018
 * @xuanxy
 */ 
@Entity
@Table(name="user_face_info")
public class UserFaceInfo {

	/**
	* 主键
	*/ 
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;

	/**
	* 分组id
	*/ 
	@Column(name="group_id")
	private String groupId;

	/**
	* 人脸唯一Id
	*/ 
	@Column(name="face_id")
	private String faceId;

	/**
	* 名字
	*/ 
	@Column(name="name")
	private String name;

	/**
	* 年纪
	*/ 
	@Column(name="age")
	private Integer age;

	/**
	* 邮箱地址
	*/ 
	@Column(name="email")
	private String email;

	/**
	* 性别，1=男，2=女
	*/ 
	@Column(name="gender")
	private Integer gender;

	/**
	* 电话号码
	*/ 
	@Column(name="phone_number")
	private String phoneNumber;

	/**
	* 人脸特征
	*/ 
	@Column(name="face_feature")
	private byte[] faceFeature;

	/**
	* 创建时间
	*/ 
	@Column(name="create_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;

	/**
	* 更新时间
	*/ 
	@Column(name="update_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date updateTime;


	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	public String getGroupId(){
		return groupId;
	}

	public void setGroupId(String groupId){
		this.groupId=groupId;
	}

	public String getFaceId(){
		return faceId;
	}

	public void setFaceId(String faceId){
		this.faceId=faceId;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name=name;
	}

	public Integer getAge(){
		return age;
	}

	public void setAge(Integer age){
		this.age=age;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public Integer getGender(){
		return gender;
	}

	public void setGender(Integer gender){
		this.gender=gender;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber=phoneNumber;
	}

	public byte[] getFaceFeature(){
		return faceFeature;
	}

	public void setFaceFeature(byte[] faceFeature){
		this.faceFeature=faceFeature;
	}

	public Date getCreateTime(){
		return createTime;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getUpdateTime(){
		return updateTime;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

}

