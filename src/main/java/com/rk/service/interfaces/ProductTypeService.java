package com.rk.service.interfaces;

import com.rk.dto.LayPage;
import com.rk.dto.ReturnResult;
import com.rk.dto.request.PageRequest;
import com.rk.entity.ProductType;

import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-20.
 */
public interface ProductTypeService {
    LayPage<List<ProductType>> getPageList(PageRequest pageRequest);
    ProductType getProductType(int id);
    ReturnResult updateOrAdd(ProductType productType);
    ReturnResult delete(Integer[] ids);
}
