package com.rk.entity;

import java.util.Date;
/**
 * Thu Dec 13 17:21:59 CST 2018 Qin_Yikai
 */ 

public class Customer extends BaseEntity<Long> {

   /**
     * 微信uid
     */ 
	private String uid;

   /**
     * showId
     */ 
	private String showId;

   /**
     * account
     */ 
	private String account;

   /**
     * password
     */ 
	private String password;

   /**
     * name
     */ 
	private String name;

   /**
     * 积分
     */ 
	private Integer point;

   /**
     * 会员等级
     */ 
	private Short level;

   /**
     * 余额
     */ 
	private Double balance;

   /**
     * 消费金额
     */ 
	private Double consumptionAmount;

   /**
     * headImg
     */ 
	private String headImg;

   /**
     * email
     */ 
	private String email;

   /**
     * phone
     */ 
	private String phone;

   /**
     * birthday
     */ 
	private Date birthday;

   /**
     * joinTime
     */ 
	private Date joinTime;

   /**
     * lastLoginTime
     */ 
	private Date lastLoginTime;
	public String getUid(){
		return uid;
	}

	public void setUid(String uid){
		this.uid = uid;
	}

	public String getShowId(){
		return showId;
	}

	public void setShowId(String showId){
		this.showId = showId;
	}

	public String getAccount(){
		return account;
	}

	public void setAccount(String account){
		this.account = account;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Integer getPoint(){
		return point;
	}

	public void setPoint(Integer point){
		this.point = point;
	}

	public Short getLevel(){
		return level;
	}

	public void setLevel(Short level){
		this.level = level;
	}

	public Double getBalance(){
		return balance;
	}

	public void setBalance(Double balance){
		this.balance = balance;
	}

	public Double getConsumptionAmount(){
		return consumptionAmount;
	}

	public void setConsumptionAmount(Double consumptionAmount){
		this.consumptionAmount = consumptionAmount;
	}

	public String getHeadImg(){
		return headImg;
	}

	public void setHeadImg(String headImg){
		this.headImg = headImg;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getPhone(){
		return phone;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public Date getBirthday(){
		return birthday;
	}

	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}

	public Date getJoinTime(){
		return joinTime;
	}

	public void setJoinTime(Date joinTime){
		this.joinTime = joinTime;
	}

	public Date getLastLoginTime(){
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime){
		this.lastLoginTime = lastLoginTime;
	}

}

