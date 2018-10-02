package com.rk.controller;

import com.rk.common.exception.NotFoundException;
import com.rk.dto.LayPage;
import com.rk.dto.ReturnResult;
import com.rk.dto.request.PageRequest;
import com.rk.entity.ProductType;
import com.rk.service.interfaces.ProductTypeService;
import com.rk.util.ValidatorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-20.
 */
@Controller
@RequestMapping("/productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping(value = "/pageList", method = RequestMethod.GET)
    @ResponseBody
    public LayPage<List<ProductType>> ProductType(PageRequest pageRequest) {
        LayPage<List<ProductType>> productTypes = productTypeService.getPageList(pageRequest);
        return productTypes;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editProductType(@RequestParam(value = "id", required = false) Integer id, Model model) throws NotFoundException {
        if (id != null) {
            ProductType productType = productTypeService.getProductType(id);
            if (productType == null)
                throw new NotFoundException();
            model.addAttribute("productType", productType);
        } else {
            model.addAttribute("productType", new ProductType());
        }
        return "admin/productType/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody()
    public ReturnResult Save(@RequestBody @Valid ProductType productType, Errors errors) {
        //校验
        ReturnResult errorReturnResult = ValidatorHelper.GetErrorReturnResult(errors);
        if (errorReturnResult != null) return errorReturnResult;

        return productTypeService.updateOrAdd(productType);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody()
    public ReturnResult Delete(@RequestParam("ids[]") Integer[] ids) {
        return productTypeService.delete(ids);
    }
}
