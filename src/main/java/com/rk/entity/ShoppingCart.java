package com.rk.entity;

import java.util.Date;

/**
 * Sat Dec 15 17:05:46 CST 2018 Qin_Yikai
 */

public class ShoppingCart extends BaseEntity<Long> {

    /**
     * customerId
     */
    private Long customerId;

    /**
     * productId
     */
    private Long productId;

    /**
     * count
     */
    private Integer count;

    /**
     * createDate
     */
    private Date createDate;

    private Product product;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

