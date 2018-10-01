package com.rk.dao;

import com.rk.dto.request.ProductPageRequest;
import com.rk.entity.Product;

import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-20.
 */
public interface ProductMapper {
    List<Product> queryAll();

    List<Product> getPageList(ProductPageRequest pageRequest);

    int getPageListTotalCount(ProductPageRequest pageRequest);

    Product getProductById(long id);

    int insert(Product product);

    int update(Product product);
}
