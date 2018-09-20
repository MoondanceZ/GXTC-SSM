package com.rk.service;

import com.rk.dao.ProductMapper;

import com.rk.entity.Product;
import com.rk.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-20.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;


    /**
     * @return
     */
    @Override
    public List<Product> getAllProduct() {
        return productMapper.queryAll();
    }
}
