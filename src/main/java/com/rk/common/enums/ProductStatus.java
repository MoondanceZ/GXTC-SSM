package com.rk.common.enums;

import com.rk.common.web.SelectItem;

import java.util.ArrayList;
import java.util.List;

public enum ProductStatus {
    DELETED(-1, "已删除"),
    DISABLED(0, "禁用"),
    ENABLED(1, "启用");

    private Integer code;
    private String desc;

    ProductStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static List<SelectItem> toList() {
        List<SelectItem> selectItems = new ArrayList<>();
        for (ProductStatus status : ProductStatus.values()) {
            selectItems.add(new SelectItem(status.getCode(), status.getDesc()));
        }
        /*selectItems.add(new SelectItem(ProductStatus.DELETED.getCode(), ProductStatus.DELETED.getDesc()));
        selectItems.add(new SelectItem(ProductStatus.DISABLED.getCode(), ProductStatus.DISABLED.getDesc()));
        selectItems.add(new SelectItem(ProductStatus.ENABLED.getCode(), ProductStatus.ENABLED.getDesc()));*/
        return selectItems;
    }
}
