package com.rk.dao;

import com.rk.dto.request.PageRequest;
import com.rk.entity.Product;

import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-20.
 */
public interface ProductMapper {
    List<Product> queryAll();
    List<Product> getPageList(PageRequest pageRequest);
    int getPageListTotalCount(PageRequest pageRequest);
}
