package com.rk.entity;

import java.util.Date;

/**
 * product 实体类
 * Thu Sep 20 14:58:30 CST 2018 Qin_Yikai
 */ 

public class Product {

   /**
     * id
     */ 
	private long id;

   /**
     * name
     */ 
	private String name;

   /**
     * image1
     */ 
	private String image1;

   /**
     * image2
     */ 
	private String image2;

   /**
     * image3
     */ 
	private String image3;

   /**
     * description
     */ 
	private String description;

   /**
     * price
     */ 
	private double price;

   /**
     * oldPrice
     */ 
	private double oldPrice;

   /**
     * count
     */ 
	private int count;

   /**
     * typeId
     */ 
	private int typeId;

   /**
     * createDate
     */ 
	private Date createDate;

   /**
     * modifyDate
     */ 
	private Date modifyDate;

   /**
     * deleteDate
     */ 
	private Date deleteDate;

   /**
     * 0: 删除，1: 在售， 2: 下架
     */ 
	private short status;

    /**
     * productType
     */
	private ProductType productType;
	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getImage1(){
		return image1;
	}

	public void setImage1(String image1){
		this.image1 = image1;
	}

	public String getImage2(){
		return image2;
	}

	public void setImage2(String image2){
		this.image2 = image2;
	}

	public String getImage3(){
		return image3;
	}

	public void setImage3(String image3){
		this.image3 = image3;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public double getPrice(){
		return price;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public double getOldPrice(){
		return oldPrice;
	}

	public void setOldPrice(double oldPrice){
		this.oldPrice = oldPrice;
	}

	public int getCount(){
		return count;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getTypeId(){
		return typeId;
	}

	public void setTypeId(int typeId){
		this.typeId = typeId;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}

	public Date getModifyDate(){
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate){
		this.modifyDate = modifyDate;
	}

	public Date getDeleteDate(){
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate){
		this.deleteDate = deleteDate;
	}

	public short getStatus(){
		return status;
	}

	public void setStatus(short status){
		this.status = status;
	}

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}

