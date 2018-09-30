package com.rk.dto.request;

/**
 * Created by Qin_Yikai on 2018-09-29.
 */
public class ProductPageRequest extends PageRequest {
    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private Double minPrice;
    private Double maxPrice;
    private String originPlace;
    private Integer status;
}
