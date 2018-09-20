package com.rk.controller;

import com.rk.entity.Product;
import com.rk.entity.ProductType;
import com.rk.service.interfaces.ProductService;
import com.rk.service.interfaces.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-16.
 */
@Component
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/main")
    public String Main() {
        return "main";
    }

    @RequestMapping("/product")
    public String Product() {
        List<Product> products = productService.getAllProduct();
        return "admin/product";
    }

    @RequestMapping("/productType")
    public String ProductType() {
        return "admin/productType";
    }
}
