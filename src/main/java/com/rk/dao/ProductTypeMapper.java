package com.rk.dao;

import com.rk.dto.request.PageRequest;
import com.rk.entity.ProductType;

import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-20.
 */
public interface ProductTypeMapper {
    List<ProductType> getPageList(PageRequest pageRequest);
    int getPageListTotalCount();
}
