package com.rk.dao;

import com.rk.dto.request.PageRequest;
import com.rk.entity.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-20.
 */
public interface ProductTypeMapper {
    List<ProductType> getPageList(PageRequest pageRequest);

    int getPageListTotalCount();

    ProductType getProductTypeById(int id);

    int update(ProductType productType);

    ProductType getProductTypeByNameOrCode(@Param("typeName") String typeName, @Param("typeCode") String typeCode);

    int delete(int id);

    int insert(ProductType productType);
}
