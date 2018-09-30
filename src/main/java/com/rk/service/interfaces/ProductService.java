package com.rk.service.interfaces;


import com.rk.dto.LayPage;
import com.rk.dto.request.PageRequest;
import com.rk.entity.Product;

import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-20.
 */
public interface ProductService {
    List<Product> getAllProduct();
    LayPage<List<Product>> getPageList(PageRequest pageRequest);
}
