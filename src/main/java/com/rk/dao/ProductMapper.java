package com.rk.dao;

import com.rk.entity.Product;

/**
 * Created by Qin_Yikai on 2018-09-20.
 */
public interface ProductMapper extends BaseMapper<Product, Long> {
    Integer[] getByTypeIds(Integer[] keys);
}
