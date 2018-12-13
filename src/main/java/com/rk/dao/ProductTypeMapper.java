package com.rk.dao;

import com.rk.entity.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-20.
 */
public interface ProductTypeMapper extends BaseMapper<ProductType, Integer> {

    ProductType getProductTypeByNameOrCode(@Param("typeName") String typeName, @Param("typeCode") String typeCode);

    List<ProductType> getEnableTypes();
}
