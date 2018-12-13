package com.rk.dto.request;


public class CustomerPageRequest extends PageRequest {
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private Integer level;
    private Integer status;
}
