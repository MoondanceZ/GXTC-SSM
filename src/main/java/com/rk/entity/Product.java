package com.rk.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rk.common.validation.MaxLength;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * product 实体类
 * Sat Sep 29 22:51:00 CST 2018 Qin_Yikai
 */
@JsonIgnoreProperties(value = {"handler"})
public class Product {

    /**
     * id
     */
    private Long id;

    /**
     * name
     */
    @NotBlank(message = "产品名称不能为空。")
    @MaxLength(value = 100, message = "产品名称不能超过100个字符。")
    private String name;

    /**
     * 封面1
     */
    private String image1;

    /**
     * 封面2
     */
    private String image2;

    /**
     * 封面3
     */
    private String image3;

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
     * 数量
     */
    @Max(value = Integer.MAX_VALUE, message = "数量不能超出" + Integer.MAX_VALUE)
    @Min(value = 1, message = "数量需大于0。")
    private Integer count;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
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
}

