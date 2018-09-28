package com.rk.controller;

import com.rk.dto.LayPage;
import com.rk.dto.ReturnResult;
import com.rk.dto.request.PageRequest;
import com.rk.entity.ProductType;
import com.rk.service.interfaces.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-20.
 */
@Component
@RequestMapping("/productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping(value = "/pageList", method = RequestMethod.GET)
    @ResponseBody
    public LayPage<List<ProductType>> ProductType(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        LayPage<List<ProductType>> productTypes = productTypeService.getPageList(new PageRequest(page, limit));
        return productTypes;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editProductType(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id != null) {
            ProductType productType = productTypeService.getProductType(id);
            model.addAttribute("productType", productType);
        }else{
            model.addAttribute("productType", new ProductType());
        }
        return "admin/productType/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody()
    public ReturnResult Save(@RequestBody ProductType productType) {
        return productTypeService.updateOrAdd(productType);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody()
    public ReturnResult Delete(@RequestParam("ids[]") Integer[] ids) {
        return productTypeService.delete(ids);
    }
}
