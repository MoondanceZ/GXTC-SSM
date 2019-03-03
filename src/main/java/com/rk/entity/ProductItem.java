package com.rk.entity;

/**
 * Fri Mar 01 22:10:56 CST 2019 Qin_Yikai
 */ 

public class ProductItem extends BaseEntity<Long> {

   /**
     * 名称
     */ 
	private String itemName;

   /**
     * 规格代码
     */ 
	private String itemCode;

   /**
     * itemInfo
     */ 
	private String itemInfo;

   /**
     * 图片
     */ 
	private String image;

   /**
     * productId
     */ 
	private Long productId;

	private Product product;

	/**
	 * 价格
	 */
	private Double price;

	public String getItemName(){
		return itemName;
	}

	public void setItemName(String itemName){
		this.itemName = itemName;
	}

	public String getItemCode(){
		return itemCode;
	}

	public void setItemCode(String itemCode){
		this.itemCode = itemCode;
	}

	public String getItemInfo(){
		return itemInfo;
	}

	public void setItemInfo(String itemInfo){
		this.itemInfo = itemInfo;
	}

	public String getImage(){
		return image;
	}

	public void setImage(String image){
		this.image = image;
	}

	public Long getProductId(){
		return productId;
	}

	public void setProductId(Long productId){
		this.productId = productId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}

