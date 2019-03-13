package com.rk.dao;

import com.rk.dto.request.PageRequest;
import com.rk.entity.ProductItem;

import java.util.List;

/**
 * Thu Mar 07 21:07:40 CST 2019 Qin_Yikai
 */ 

public interface ProductItemMapper extends BaseMapper<ProductItem, Long> {
    List<ProductItem> getByProductId(Long productId);
}

