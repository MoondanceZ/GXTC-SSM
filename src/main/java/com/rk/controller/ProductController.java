package com.rk.controller;

import com.rk.common.exception.DataNotFoundException;
import com.rk.dto.LayPage;
import com.rk.dto.ReturnResult;
import com.rk.dto.request.ProductPageRequest;
import com.rk.entity.Product;
import com.rk.service.interfaces.ProductService;
import com.rk.service.interfaces.ProductTypeService;
import com.rk.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
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
    public LayPage<List<Product>> ProductType(ProductPageRequest pageRequest) {
        LayPage<List<Product>> productTypes = productService.getPageList(pageRequest);
        return productTypes;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editProduct(@RequestParam(value = "id", required = false) Long id, Model model) throws DataNotFoundException {
        if (id != null) {
            Product product = productService.getProduct(id);
            if (product == null)
                throw new DataNotFoundException();
            model.addAttribute("product", product);
        } else {
            model.addAttribute("product", new Product());
        }

        model.addAttribute("enableTypes", productTypeService.getEnableTypes());
        return "admin/product/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody()
    public ReturnResult Save(HttpServletRequest request, @Valid Product product,
                             @RequestParam(value = "imgFile1", required = false) MultipartFile imgFile1,
                             @RequestParam(value = "imgFile2", required = false) MultipartFile imgFile2,
                             @RequestParam(value = "imgFile3", required = false) MultipartFile imgFile3)
            throws IOException {

        //采用这种校验方式是因为前段为 multipart/form-data 方式提交时, 添加 @Valid 会报400错误, 暂时无法解决
        //ReturnResult validateResult = ValidatorHelper.validate(product);
        //if (validateResult != null) return validateResult;

        if (!imgFile1.isEmpty()) {
            String imgFile1Name = FileUtil.saveImage(request, imgFile1);
            if (imgFile1Name != null) {
                String[] nameStrings = imgFile1Name.split("/");
                product.setImage1(nameStrings[nameStrings.length - 1]);
            }
        }

        if (!imgFile2.isEmpty()) {
            String imgFile2Name = FileUtil.saveImage(request, imgFile2);
            if (imgFile2Name != null) {
                String[] nameStrings = imgFile2Name.split("/");
                product.setImage2(nameStrings[nameStrings.length - 1]);
            }
        }

        if (!imgFile3.isEmpty()) {
            String imgFile3Name = FileUtil.saveImage(request, imgFile3);
            if (imgFile3Name != null) {
                String[] nameStrings = imgFile3Name.split("/");
                product.setImage3(nameStrings[nameStrings.length - 1]);
            }
        }

        return productService.updateOrAdd(product);
    }
}
