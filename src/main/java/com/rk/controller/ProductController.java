package com.rk.controller;

import com.rk.dto.LayPage;
import com.rk.dto.ReturnResult;
import com.rk.dto.request.PageRequest;
import com.rk.entity.Product;
import com.rk.entity.ProductType;
import com.rk.service.interfaces.ProductService;
import com.rk.service.interfaces.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by Qin_Yikai on 2018-09-29.
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping(value = "/pageList", method = RequestMethod.GET)
    @ResponseBody
    public LayPage<List<Product>> ProductType(PageRequest pageRequest) {
        LayPage<List<Product>> productTypes = productService.getPageList(pageRequest);
        return productTypes;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editProduct(@RequestParam(value = "id", required = false) Integer id, Model model) {
        /*if (id != null) {
            Product product = productTypeService.getProductType(id);
            model.addAttribute("product", product);
        }else{
            model.addAttribute("product", new ProductType());
        }*/


        model.addAttribute("product", new Product());
        model.addAttribute("enableTypes", productTypeService.getEnableTypes());
        return "admin/product/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody()
    public ReturnResult Save(HttpServletRequest request, Product product,
                             @RequestParam(value = "imgFile1", required = false) MultipartFile imgFile1,
                             @RequestParam(value = "imgFile2", required = false) MultipartFile imgFile2,
                             @RequestParam(value = "imgFile3", required = false) MultipartFile imgFile3) {
        return ReturnResult.Error("提交失败");
    }
}
