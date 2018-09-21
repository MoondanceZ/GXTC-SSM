package com.rk.entity;


/**
 * product_type 实体类
 * Thu Sep 20 15:49:27 CST 2018 Qin_Yikai
 */ 

public class ProductType {

   /**
     * id
     */ 
	private int id;

   /**
     * parentTypeId
     */ 
	private Integer parentTypeId;

   /**
     * typeName
     */ 
	private String typeName;

   /**
     * typeCode
     */ 
	private String typeCode;

   /**
     * 0:停用， 1: 正常 
     */ 
	private short status;
	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public Integer getParentTypeId(){
		return parentTypeId;
	}

	public void setParentTypeId(Integer parentTypeId){
		this.parentTypeId = parentTypeId;
	}

	public String getTypeName(){
		return typeName;
	}

	public void setTypeName(String typeName){
		this.typeName = typeName;
	}

	public String getTypeCode(){
		return typeCode;
	}

	public void setTypeCode(String typeCode){
		this.typeCode = typeCode;
	}

	public short getStatus(){
		return status;
	}

	public void setStatus(short status){
		this.status = status;
	}

}

