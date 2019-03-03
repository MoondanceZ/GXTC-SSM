package com.rk.dao;

import com.rk.entity.Product;
import com.rk.entity.ProductItem;

import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-20.
 */
public interface ProductMapper extends BaseMapper<Product, Long> {
    Integer[] getByTypeIds(Integer[] keys);
    List<ProductItem> getProductItems(Long id);
}
