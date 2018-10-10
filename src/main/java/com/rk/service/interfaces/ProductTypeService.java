package com.rk.service.interfaces;

import com.rk.entity.ProductType;

import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-20.
 */
public interface ProductTypeService extends BaseService<ProductType, Integer> {
    List<ProductType> getEnableTypes();
}
