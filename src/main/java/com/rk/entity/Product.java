package com.rk.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rk.common.validation.MaxLength;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * product 实体类
 * Sat Sep 29 22:51:00 CST 2018 Qin_Yikai
 */
@JsonIgnoreProperties(value = {"handler"})
public class Product extends BaseEntity<Long> {
    /**
     * name
     */
    @NotBlank(message = "产品名称不能为空。")
    @MaxLength(value = 100, message = "产品名称不能超过100个字符。")
    private String name;

    /**
     * 图片库
     */
    private String image;

    /**
     * 产地
     */
    @MaxLength(value = 40, message = "产地不能超过40个字符。")
    private String originPlace;

    /**
     * 描述
     */
    private String description;

    /**
     * 价格
     */
    @NotNull(message = "产品价格不能为空。")
    @DecimalMax(value = "9999999.99", message = "产品价格不能超过9999999.99。")
    @DecimalMin(value = "0.01", message = "产品价格不能低于0.01。")
    private Double price;

    /**
     * 旧价格
     */
    @DecimalMax(value = "9999999.99", message = "产品原价不能超过9999999.99。")
    @DecimalMin(value = "0.01", message = "产品原价不能低于0.01。")
    private Double oldPrice;

    /**
     * 是否统一售价
     */
    private Boolean unifiedPrice;

    /**
     * 数量
     */
    @Max(value = Integer.MAX_VALUE, message = "数量不能超出" + Integer.MAX_VALUE)
    @Min(value = 1, message = "数量需大于0。")
    private Integer count;

    /**
     * 购买数量
     */
    @Max(value = Integer.MAX_VALUE, message = "数量不能超出" + Integer.MAX_VALUE)
    @Min(value = 0, message = "数量需大于等于0。")
    private Integer limitCount;

    /**
     * 商品类型
     */
    private int typeId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 删除时间
     */
    private Date deleteDate;

    /**
     * -1: 删除，0: 下架， 1: 在售
     */
    private short status;
    /**
     * productType
     */
    private ProductType productType;

    /**
     * productItems
     */
    private List<ProductItem> productItems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(Integer limitCount) {
        this.limitCount = limitCount;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public List<ProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(List<ProductItem> productItems) {
        this.productItems = productItems;
    }

    public Boolean getUnifiedPrice() {
        return unifiedPrice;
    }

    public void setUnifiedPrice(Boolean unifiedPrice) {
        this.unifiedPrice = unifiedPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

